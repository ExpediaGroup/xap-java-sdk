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
import com.expediagroup.sdk.xap.model.FlightFareRulesResponse
import com.expediagroup.sdk.xap.model.FlightsV1Errors
import com.expediagroup.sdk.xap.model.exception.GetFlightFarerules400Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightFarerules401Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightFarerules403Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightFarerules404Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightFarerules429Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightFarerules500Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightFarerules503Exception
import com.expediagroup.sdk.xap.model.exception.GetFlightFarerules504Exception
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.UUID

/**
 *
 *
 * request for farerule information
 *
 * Executing the operation returns [com.expediagroup.sdk.rest.model.Response] of type [FlightFareRulesResponse]
 *
 * The operation may result in the following exceptions:
 * - [GetFlightFarerules400Exception]
 * - [GetFlightFarerules401Exception]
 * - [GetFlightFarerules403Exception]
 * - [GetFlightFarerules404Exception]
 * - [GetFlightFarerules429Exception]
 * - [GetFlightFarerules500Exception]
 * - [GetFlightFarerules503Exception]
 * - [GetFlightFarerules504Exception]
 *
 * @property params [GetFlightFarerulesOperationParams]
 */
class GetFlightFarerulesOperation(
    private val params: GetFlightFarerulesOperationParams,
) : OperationRequestTrait,
    UrlPathTrait,
    JacksonModelOperationResponseBodyTrait<FlightFareRulesResponse>,
    UrlQueryParamsTrait,
    HeadersTrait {
    override fun getOperationId(): String = "getFlightFarerules"

    override fun getHttpMethod(): String = "GET"

    override fun getRequestInfo(): OperationRequestTrait = this

    override fun getUrlPath(): String {
        var url = "/flights/farerules/{offerToken}"

        url = url.replace(
            oldValue = "{" + "offerToken" + "}",
            newValue = this.params.offerToken,
            ignoreCase = true,
        )

        return url
    }

    override fun getTypeIdentifier(): TypeReference<FlightFareRulesResponse> = jacksonTypeRef()

    override fun getHeaders(): Headers = this.params.getHeaders()

    override fun getUrlQueryParams() = this.params.getQueryParams()

    override fun getExceptionForCode(
        code: Int,
        errorResponseStr: String?,
        requestId: UUID?,
        message: String?,
        cause: Throwable?,
    ): ExpediaGroupApiException = when (code) {
        400 -> GetFlightFarerules400Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, FlightsV1Errors::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        401 -> GetFlightFarerules401Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, APIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        403 -> GetFlightFarerules403Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, APIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        404 -> GetFlightFarerules404Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, APIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        429 -> GetFlightFarerules429Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, APIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        500 -> GetFlightFarerules500Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, FlightsV1Errors::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        503 -> GetFlightFarerules503Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, APIMError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        504 -> GetFlightFarerules504Exception(
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
