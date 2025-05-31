/**
 * Copyright (C) 2025 Expedia, Inc.
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
package com.expediagroup.sdk.xap.core.executor

import com.expediagroup.sdk.core.auth.basic.BasicAuthCredentials
import com.expediagroup.sdk.core.auth.basic.BasicAuthManager
import com.expediagroup.sdk.core.auth.oauth.OAuthAsyncManager
import com.expediagroup.sdk.core.exception.client.ExpediaGroupConfigurationException
import com.expediagroup.sdk.core.logging.LoggerDecorator
import com.expediagroup.sdk.core.logging.masking.MaskHeaders
import com.expediagroup.sdk.core.pipeline.ExecutionPipeline
import com.expediagroup.sdk.core.pipeline.RequestPipelineStep
import com.expediagroup.sdk.core.pipeline.ResponsePipelineStep
import com.expediagroup.sdk.core.pipeline.step.BasicAuthStep
import com.expediagroup.sdk.core.pipeline.step.OAuthStep
import com.expediagroup.sdk.core.pipeline.step.RequestHeadersStep
import com.expediagroup.sdk.core.pipeline.step.RequestLoggingStep
import com.expediagroup.sdk.core.pipeline.step.ResponseLoggingStep
import com.expediagroup.sdk.core.transport.AbstractAsyncRequestExecutor
import com.expediagroup.sdk.xap.configuration.AsyncXapClientConfiguration
import com.expediagroup.sdk.xap.configuration.Constant.AUTH_ENDPOINT
import com.expediagroup.sdk.xap.core.model.XapOAuthCredentials
import com.expediagroup.sdk.xap.core.pipeline.ApiKeyHeaderStep
import org.slf4j.LoggerFactory

/**
 * Executor for handling asynchronous requests with XAP client configuration.
 *
 * @param configuration The configuration for the asynchronous XAP client.
 */
class AsyncRequestExecutor(
    private val configuration: AsyncXapClientConfiguration,
) : AbstractAsyncRequestExecutor(configuration.asyncTransport) {
    private val headersMask = MaskHeaders(listOf("authorization"))

    override val executionPipeline = ExecutionPipeline(
        requestPipeline = getRequestPipeline(),
        responsePipeline = getResponsePipeline(),
    )

    private fun getRequestPipeline(): List<RequestPipelineStep> = when (configuration.credentials) {
        is BasicAuthCredentials ->
            listOf(
                RequestHeadersStep(),
                ApiKeyHeaderStep(configuration.credentials.username),
                BasicAuthStep(BasicAuthManager(configuration.credentials)),
                RequestLoggingStep(
                    logger = logger,
                    maskHeaders = headersMask,
                ),
            )

        is XapOAuthCredentials ->
            listOf(
                RequestHeadersStep(),
                ApiKeyHeaderStep(configuration.credentials.partnerKey),
                OAuthStep(
                    OAuthAsyncManager(
                        credentials = configuration.credentials.oAuthCredentials,
                        asyncTransport = asyncTransport,
                        authUrl = AUTH_ENDPOINT,
                    ),
                ),
                RequestLoggingStep(
                    logger = logger,
                    maskHeaders = headersMask,
                ),
            )

        else -> throw ExpediaGroupConfigurationException(
            "Unsupported credentials type: ${configuration.credentials.javaClass.name}",
        )
    }

    private fun getResponsePipeline(): List<ResponsePipelineStep> = listOf(
        ResponseLoggingStep(logger),
    )

    companion object {
        private val logger = LoggerDecorator(LoggerFactory.getLogger(this::class.java.enclosingClass))
    }
}
