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
import com.expediagroup.sdk.xap.model.FlightSearchResponse
import com.expediagroup.sdk.xap.model.FlightsV3APIMError
import com.expediagroup.sdk.xap.model.FlightsV3Errors
import com.expediagroup.sdk.xap.model.exception.GetFlightListings400Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightListings401Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightListings403Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightListings404Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightListings409Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightListings429Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightListings500Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightListings503Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightListings504Exception
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.UUID

/**
 *
 *
 * search flight products
 *
 * Executing the operation returns [com.expediagroup.sdk.rest.model.Response] of type [FlightSearchResponse]
 *
 * The operation may result in the following exceptions:
 * - [GetFlightListings400Exception]
 * - [GetFlightListings401Exception]
 * - [GetFlightListings403Exception]
 * - [GetFlightListings404Exception]
 * - [GetFlightListings409Exception]
 * - [GetFlightListings429Exception]
 * - [GetFlightListings500Exception]
 * - [GetFlightListings503Exception]
 * - [GetFlightListings504Exception]
 *
 * @property params [GetFlightListingsOperationParams]
 */
class GetFlightListingsOperation(
    private val params: GetFlightListingsOperationParams,
) : OperationRequestTrait,
    UrlPathTrait,
    JacksonModelOperationResponseBodyTrait<FlightSearchResponse>,
    UrlQueryParamsTrait,
    HeadersTrait {
    override fun getOperationId(): String = "getFlightListings"

    override fun getHttpMethod(): String = "GET"

    override fun getRequestInfo(): OperationRequestTrait = this

    override fun getUrlPath(): String {
        var url = "/flights/listings"

        return url
    }

    override fun getTypeIdentifier(): TypeReference<FlightSearchResponse> = jacksonTypeRef()

    override fun getHeaders(): Headers = this.params.getHeaders()

    override fun getUrlQueryParams() = this.params.getQueryParams()

    override fun getExceptionForCode(
        code: Int,
        errorResponseStr: String?,
        requestId: UUID?,
        message: String?,
        cause: Throwable?,
    ): ExpediaGroupApiException = when (code) {
        400 -> GetFlightListings400Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, FlightsV3Errors::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        401 -> GetFlightListings401Exception(
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
        403 -> GetFlightListings403Exception(
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
        404 -> GetFlightListings404Exception(
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
        409 -> GetFlightListings409Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, FlightsV3Errors::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        429 -> GetFlightListings429Exception(
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
        500 -> GetFlightListings500Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, FlightsV3Errors::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        503 -> GetFlightListings503Exception(
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
        504 -> GetFlightListings504Exception(
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
