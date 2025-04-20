/*
 * Copyright (C) 2022 Expedia, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.expediagroup.sdk.core.plugin.serialization

import com.expediagroup.sdk.core.plugin.logging.ExpediaGroupLoggerFactory
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentConverterException
import io.ktor.client.plugins.contentnegotiation.JsonContentTypeMatcher
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.statement.HttpResponseContainer
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.client.utils.EmptyContent
import io.ktor.http.ContentType
import io.ktor.http.ContentTypeMatcher
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.http.charset
import io.ktor.http.content.NullBody
import io.ktor.http.content.OutgoingContent
import io.ktor.http.contentType
import io.ktor.serialization.Configuration
import io.ktor.serialization.ContentConverter
import io.ktor.serialization.deserialize
import io.ktor.serialization.suitableCharset
import io.ktor.util.AttributeKey
import io.ktor.util.InternalAPI
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.charsets.Charset
import java.io.InputStream
import kotlin.reflect.KClass

internal val DefaultCommonIgnoredTypes: Set<KClass<*>> =
    setOf(
        ByteArray::class,
        String::class,
        HttpStatusCode::class,
        ByteReadChannel::class,
        OutgoingContent::class
    )

internal val DefaultIgnoredTypes: Set<KClass<*>> = mutableSetOf(InputStream::class)

internal class ContentNegotiation(
    private val registrations: List<Config.ConverterRegistration>,
    private val ignoredTypes: Set<KClass<*>>
) {
    internal class Config : Configuration {
        internal class ConverterRegistration(
            val converter: ContentConverter,
            val contentTypeToSend: ContentType,
            val contentTypeMatcher: ContentTypeMatcher
        )

        val registrations = mutableListOf<ConverterRegistration>()
        val ignoredTypes: MutableSet<KClass<*>> =
            (DefaultIgnoredTypes + DefaultCommonIgnoredTypes).toMutableSet()

        override fun <T : ContentConverter> register(
            contentType: ContentType,
            converter: T,
            configuration: T.() -> Unit
        ) {
            val matcher =
                when (contentType) {
                    ContentType.Application.Json -> JsonContentTypeMatcher
                    else -> defaultMatcher(contentType)
                }
            register(contentType, converter, matcher, configuration)
        }

        private fun <T : ContentConverter> register(
            contentTypeToSend: ContentType,
            converter: T,
            contentTypeMatcher: ContentTypeMatcher,
            configuration: T.() -> Unit
        ) {
            val registration =
                ConverterRegistration(
                    converter.apply(configuration),
                    contentTypeToSend,
                    contentTypeMatcher
                )
            registrations.add(registration)
        }

        private fun defaultMatcher(pattern: ContentType): ContentTypeMatcher =
            object : ContentTypeMatcher {
                override fun contains(contentType: ContentType): Boolean = contentType.match(pattern)
            }
    }

    private val log = ExpediaGroupLoggerFactory.getLogger(this::class.java)

    internal suspend fun convertRequest(
        request: HttpRequestBuilder,
        body: Any
    ): Any? {
        if (body is OutgoingContent || ignoredTypes.any { it.isInstance(body) }) {
            log.trace(
                "Body type ${body::class} is in ignored types. " +
                    "Skipping ContentNegotiation for ${request.url}."
            )
            return null
        }
        val contentType =
            request.contentType() ?: run {
                log.trace("Request doesn't have Content-Type header. Skipping ContentNegotiation for ${request.url}.")
                return null
            }

        if (body is Unit) {
            log.trace("Sending empty body for ${request.url}")
            request.headers.remove(HttpHeaders.ContentType)
            return EmptyContent
        }

        val matchingRegistrations =
            registrations.filter { it.contentTypeMatcher.contains(contentType) }
                .takeIf { it.isNotEmpty() } ?: run {
                log.trace(
                    "None of the registered converters match request Content-Type=$contentType. " +
                        "Skipping ContentNegotiation for ${request.url}."
                )
                return null
            }
        if (request.bodyType == null) {
            log.trace("Request has unknown body type. Skipping ContentNegotiation for ${request.url}.")
            return null
        }
        request.headers.remove(HttpHeaders.ContentType)

        // Pick the first one that can convert the subject successfully
        val serializedContent =
            matchingRegistrations.firstNotNullOfOrNull { registration ->
                val result =
                    registration.converter.serializeNullable(
                        contentType,
                        contentType.charset() ?: Charsets.UTF_8,
                        request.bodyType!!,
                        body.takeIf { it != NullBody }
                    )
                if (result != null) {
                    log.trace("Converted request body using ${registration.converter} for ${request.url}")
                }
                result
            } ?: throw ContentConverterException(
                "Can't convert $body with contentType $contentType using converters " +
                    matchingRegistrations.joinToString { it.converter.toString() }
            )

        return serializedContent
    }

    @OptIn(InternalAPI::class)
    internal suspend fun convertResponse(
        requestUrl: Url,
        info: TypeInfo,
        body: Any,
        responseContentType: ContentType,
        charset: Charset = Charsets.UTF_8
    ): Any? {
        if (body !is ByteReadChannel) {
            log.trace("Response body is already transformed. Skipping ContentNegotiation for $requestUrl.")
            return null
        }
        if (info.type in ignoredTypes) {
            log.trace(
                "Response body type ${info.type} is in ignored types. " +
                    "Skipping ContentNegotiation for $requestUrl."
            )
            return null
        }

        log.debug("Test: ${registrations.size}")
        val suitableConverters =
            registrations
                .filter { it.contentTypeMatcher.contains(responseContentType) }
                .map { it.converter }
                .takeIf { it.isNotEmpty() }
                ?: run {
                    log.trace(
                        "None of the registered converters match response with Content-Type=$responseContentType. " +
                            "Skipping ContentNegotiation for $requestUrl."
                    )
                    return null
                }

        val result = suitableConverters.deserialize(body, info, charset)
        if (result !is ByteReadChannel) {
            log.trace("Response body was converted to ${result::class} for $requestUrl.")
        }
        return result
    }

    companion object Plugin : HttpClientPlugin<Config, ContentNegotiation> {
        override val key: AttributeKey<ContentNegotiation> = AttributeKey("ContentNegotiation")
        private val log = ExpediaGroupLoggerFactory.getLogger(this::class.java)

        override fun install(
            plugin: ContentNegotiation,
            scope: HttpClient
        ) {
            scope.requestPipeline.intercept(HttpRequestPipeline.Transform) {
                val result = plugin.convertRequest(context, subject) ?: return@intercept
                proceedWith(result)
            }

            scope.responsePipeline.intercept(HttpResponsePipeline.Transform) { (info, body) ->
                val contentType =
                    context.response.contentType() ?: run {
                        log.trace("Response doesn't have \"Content-Type\" header, skipping ContentNegotiation plugin")
                        return@intercept
                    }
                val charset = context.request.headers.suitableCharset()

                val deserializedBody =
                    plugin.convertResponse(context.request.url, info, body, contentType, charset)
                        ?: return@intercept
                val result = HttpResponseContainer(info, deserializedBody)
                proceedWith(result)
            }
        }

        override fun prepare(block: Config.() -> Unit): ContentNegotiation {
            val config = Config().apply(block)
            return ContentNegotiation(config.registrations, config.ignoredTypes)
        }
    }
}
