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
package com.expediagroup.sdk.xap.operation

import com.expediagroup.sdk.rest.exception.service.ExpediaGroupApiException
import com.expediagroup.sdk.rest.trait.operation.JacksonModelOperationResponseBodyTrait
import com.expediagroup.sdk.rest.trait.operation.OperationRequestTrait
import com.expediagroup.sdk.rest.trait.operation.UrlPathTrait
import com.expediagroup.sdk.rest.trait.operation.UrlQueryParamsTrait
import com.expediagroup.sdk.xap.model.PresignedUrlResponse
import com.expediagroup.sdk.xap.model.SdpAPIMError
import com.expediagroup.sdk.xap.model.exception.GetFeedDownloadUrl400Exception
import com.expediagroup.sdk.xap.model.exception.GetFeedDownloadUrl401Exception
import com.expediagroup.sdk.xap.model.exception.GetFeedDownloadUrl403Exception
import com.expediagroup.sdk.xap.model.exception.GetFeedDownloadUrl404Exception
import com.expediagroup.sdk.xap.model.exception.GetFeedDownloadUrl429Exception
import com.expediagroup.sdk.xap.model.exception.GetFeedDownloadUrl500Exception
import com.expediagroup.sdk.xap.model.exception.GetFeedDownloadUrl503Exception
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.UUID

/**
 *
 *
 * Get the Download URL and other details of the static files.
 *
 * Executing the operation returns [com.expediagroup.sdk.rest.model.Response] of type [PresignedUrlResponse]
 *
 * The operation may result in the following exceptions:
 * - [GetFeedDownloadUrl400Exception]
 * - [GetFeedDownloadUrl401Exception]
 * - [GetFeedDownloadUrl403Exception]
 * - [GetFeedDownloadUrl404Exception]
 * - [GetFeedDownloadUrl429Exception]
 * - [GetFeedDownloadUrl500Exception]
 * - [GetFeedDownloadUrl503Exception]
 *
 * @property params [GetFeedDownloadUrlOperationParams]
 */
class GetFeedDownloadUrlOperation(
    private val params: GetFeedDownloadUrlOperationParams,
) : OperationRequestTrait,
    UrlPathTrait,
    JacksonModelOperationResponseBodyTrait<PresignedUrlResponse>,
    UrlQueryParamsTrait {
    override fun getOperationId(): String = "getFeedDownloadUrl"

    override fun getHttpMethod(): String = "GET"

    override fun getRequestInfo(): OperationRequestTrait = this

    override fun getUrlPath(): String {
        var url = "/feed/v1/download-url"

        return url
    }

    override fun getTypeIdentifier(): TypeReference<PresignedUrlResponse> = jacksonTypeRef()

    override fun getUrlQueryParams() = this.params.getQueryParams()

    override fun getExceptionForCode(
        code: Int,
        errorResponseStr: String?,
        requestId: UUID?,
        message: String?,
        cause: Throwable?,
    ): ExpediaGroupApiException = when (code) {
        400 -> GetFeedDownloadUrl400Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, PresignedUrlResponse::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        401 -> GetFeedDownloadUrl401Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, SdpAPIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        403 -> GetFeedDownloadUrl403Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, SdpAPIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        404 -> GetFeedDownloadUrl404Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, SdpAPIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        429 -> GetFeedDownloadUrl429Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, SdpAPIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        500 -> GetFeedDownloadUrl500Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, PresignedUrlResponse::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        503 -> GetFeedDownloadUrl503Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, SdpAPIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        else -> ExpediaGroupApiException(
            code = code,
            requestId = requestId,
            message = errorResponseStr,
            cause = cause,
        )
    }
}
