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
import com.expediagroup.sdk.xap.model.FlexSearchResponse
import com.expediagroup.sdk.xap.model.FlightsV3APIMError
import com.expediagroup.sdk.xap.model.GetFlightFlexsearch400Response
import com.expediagroup.sdk.xap.model.exception.GetFlightFlexsearch400Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightFlexsearch401Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightFlexsearch403Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightFlexsearch404Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightFlexsearch429Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightFlexsearch500Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightFlexsearch503Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightFlexsearch504Exception
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.UUID

/**
 *
 *
 * request for flexsearch information
 *
 * Executing the operation returns [com.expediagroup.sdk.rest.model.Response] of type [FlexSearchResponse]
 *
 * The operation may result in the following exceptions:
 * - [GetFlightFlexsearch400Exception]
 * - [GetFlightFlexsearch401Exception]
 * - [GetFlightFlexsearch403Exception]
 * - [GetFlightFlexsearch404Exception]
 * - [GetFlightFlexsearch429Exception]
 * - [GetFlightFlexsearch500Exception]
 * - [GetFlightFlexsearch503Exception]
 * - [GetFlightFlexsearch504Exception]
 *
 * @property params [GetFlightFlexsearchOperationParams]
 */
class GetFlightFlexsearchOperation(
    private val params: GetFlightFlexsearchOperationParams,
) : OperationRequestTrait,
    UrlPathTrait,
    JacksonModelOperationResponseBodyTrait<FlexSearchResponse>,
    UrlQueryParamsTrait,
    HeadersTrait {
    override fun getOperationId(): String = "getFlightFlexsearch"

    override fun getHttpMethod(): String = "GET"

    override fun getRequestInfo(): OperationRequestTrait = this

    override fun getUrlPath(): String {
        var url = "/flights/flexsearch"

        return url
    }

    override fun getTypeIdentifier(): TypeReference<FlexSearchResponse> = jacksonTypeRef()

    override fun getHeaders(): Headers = this.params.getHeaders()

    override fun getUrlQueryParams() = this.params.getQueryParams()

    override fun getExceptionForCode(
        code: Int,
        errorResponseStr: String?,
        requestId: UUID?,
        message: String?,
        cause: Throwable?,
    ): ExpediaGroupApiException = when (code) {
        400 -> GetFlightFlexsearch400Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, GetFlightFlexsearch400Response::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        401 -> GetFlightFlexsearch401Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, FlightsV3APIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        403 -> GetFlightFlexsearch403Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, FlightsV3APIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        404 -> GetFlightFlexsearch404Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, FlightsV3APIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        429 -> GetFlightFlexsearch429Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, FlightsV3APIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        500 -> GetFlightFlexsearch500Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, GetFlightFlexsearch400Response::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        503 -> GetFlightFlexsearch503Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, FlightsV3APIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        504 -> GetFlightFlexsearch504Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, String::class.java)
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
