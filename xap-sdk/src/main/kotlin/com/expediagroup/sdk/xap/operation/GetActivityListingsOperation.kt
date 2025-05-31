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
import com.expediagroup.sdk.xap.model.ActivityListingsResponse
import com.expediagroup.sdk.xap.model.exception.GetActivityListings400Exception
import com.expediagroup.sdk.xap.model.exception.GetActivityListings401Exception
import com.expediagroup.sdk.xap.model.exception.GetActivityListings403Exception
import com.expediagroup.sdk.xap.model.exception.GetActivityListings404Exception
import com.expediagroup.sdk.xap.model.exception.GetActivityListings429Exception
import com.expediagroup.sdk.xap.model.exception.GetActivityListings500Exception
import com.expediagroup.sdk.xap.model.exception.GetActivityListings503Exception
import com.expediagroup.sdk.xap.model.exception.GetActivityListings504Exception
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.UUID

/**
 *
 *
 * The Activities Search API allows partners to search for Expedia Activity inventory.
 *
 * Executing the operation returns [com.expediagroup.sdk.rest.model.Response] of type [ActivityListingsResponse]
 *
 * The operation may result in the following exceptions:
 * - [GetActivityListings400Exception]
 * - [GetActivityListings401Exception]
 * - [GetActivityListings403Exception]
 * - [GetActivityListings404Exception]
 * - [GetActivityListings429Exception]
 * - [GetActivityListings500Exception]
 * - [GetActivityListings503Exception]
 * - [GetActivityListings504Exception]
 *
 * @property params [GetActivityListingsOperationParams]
 */
class GetActivityListingsOperation(
    private val params: GetActivityListingsOperationParams,
) : OperationRequestTrait,
    UrlPathTrait,
    JacksonModelOperationResponseBodyTrait<ActivityListingsResponse>,
    UrlQueryParamsTrait,
    HeadersTrait {
    override fun getOperationId(): String = "getActivityListings"

    override fun getHttpMethod(): String = "GET"

    override fun getRequestInfo(): OperationRequestTrait = this

    override fun getUrlPath(): String {
        var url = "/activities/listings"

        return url
    }

    override fun getTypeIdentifier(): TypeReference<ActivityListingsResponse> = jacksonTypeRef()

    override fun getHeaders(): Headers = this.params.getHeaders()

    override fun getUrlQueryParams() = this.params.getQueryParams()

    override fun getExceptionForCode(
        code: Int,
        errorResponseStr: String?,
        requestId: UUID?,
        message: String?,
        cause: Throwable?,
    ): ExpediaGroupApiException = when (code) {
        400 -> GetActivityListings400Exception(
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
        401 -> GetActivityListings401Exception(
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
        403 -> GetActivityListings403Exception(
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
        404 -> GetActivityListings404Exception(
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
        429 -> GetActivityListings429Exception(
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
        500 -> GetActivityListings500Exception(
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
        503 -> GetActivityListings503Exception(
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
        504 -> GetActivityListings504Exception(
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
