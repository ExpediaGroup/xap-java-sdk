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

import com.expediagroup.sdk.core.http.Headers
import com.expediagroup.sdk.rest.exception.service.ExpediaGroupApiException
import com.expediagroup.sdk.rest.trait.operation.HeadersTrait
import com.expediagroup.sdk.rest.trait.operation.JacksonModelOperationResponseBodyTrait
import com.expediagroup.sdk.rest.trait.operation.OperationRequestTrait
import com.expediagroup.sdk.rest.trait.operation.UrlPathTrait
import com.expediagroup.sdk.rest.trait.operation.UrlQueryParamsTrait
import com.expediagroup.sdk.xap.model.APIMError
import com.expediagroup.sdk.xap.model.ActivitiesErrors
import com.expediagroup.sdk.xap.model.ActivityDetailsResponse
import com.expediagroup.sdk.xap.model.exception.GetActivityDetails400Exception
import com.expediagroup.sdk.xap.model.exception.GetActivityDetails401Exception
import com.expediagroup.sdk.xap.model.exception.GetActivityDetails403Exception
import com.expediagroup.sdk.xap.model.exception.GetActivityDetails404Exception
import com.expediagroup.sdk.xap.model.exception.GetActivityDetails429Exception
import com.expediagroup.sdk.xap.model.exception.GetActivityDetails500Exception
import com.expediagroup.sdk.xap.model.exception.GetActivityDetails503Exception
import com.expediagroup.sdk.xap.model.exception.GetActivityDetails504Exception
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.UUID

/**
 *
 *
 * The Activity Details API provides detailed information about one selected activity.
 *
 * Executing the operation returns [com.expediagroup.sdk.rest.model.Response] of type [ActivityDetailsResponse]
 *
 * The operation may result in the following exceptions:
 * - [GetActivityDetails400Exception]
 * - [GetActivityDetails401Exception]
 * - [GetActivityDetails403Exception]
 * - [GetActivityDetails404Exception]
 * - [GetActivityDetails429Exception]
 * - [GetActivityDetails500Exception]
 * - [GetActivityDetails503Exception]
 * - [GetActivityDetails504Exception]
 *
 * @property params [GetActivityDetailsOperationParams]
 */
class GetActivityDetailsOperation(
    private val params: GetActivityDetailsOperationParams,
) : OperationRequestTrait,
    UrlPathTrait,
    JacksonModelOperationResponseBodyTrait<ActivityDetailsResponse>,
    UrlQueryParamsTrait,
    HeadersTrait {
    override fun getOperationId(): String = "getActivityDetails"

    override fun getHttpMethod(): String = "GET"

    override fun getRequestInfo(): OperationRequestTrait = this

    override fun getUrlPath(): String {
        var url = "/activities/details/{offerToken}"

        url = url.replace(
            oldValue = "{" + "offerToken" + "}",
            newValue = this.params.offerToken,
            ignoreCase = true,
        )

        return url
    }

    override fun getTypeIdentifier(): TypeReference<ActivityDetailsResponse> = jacksonTypeRef()

    override fun getHeaders(): Headers = this.params.getHeaders()

    override fun getUrlQueryParams() = this.params.getQueryParams()

    override fun getExceptionForCode(
        code: Int,
        errorResponseStr: String?,
        requestId: UUID?,
        message: String?,
        cause: Throwable?,
    ): ExpediaGroupApiException = when (code) {
        400 -> GetActivityDetails400Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, ActivitiesErrors::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        401 -> GetActivityDetails401Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, APIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        403 -> GetActivityDetails403Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, APIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        404 -> GetActivityDetails404Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, APIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        429 -> GetActivityDetails429Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, APIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        500 -> GetActivityDetails500Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, ActivitiesErrors::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        503 -> GetActivityDetails503Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, APIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        504 -> GetActivityDetails504Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, String::class.java)
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
