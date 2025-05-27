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
import com.expediagroup.sdk.xap.model.APIGatewayError
import com.expediagroup.sdk.xap.model.Errors
import com.expediagroup.sdk.xap.model.HotelDetailsResponse
import com.expediagroup.sdk.xap.model.exception.GetLodgingDetails400Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingDetails401Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingDetails403Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingDetails429Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingDetails500Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingDetails503Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingDetails504Exception
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.UUID

/**
 * Get Extended information with a single property offer
 *
 * Extended information about the rate, charges, fees, and financial terms associated with booking a single lodging rate plan offer.
 *
 * Executing the operation returns [com.expediagroup.sdk.rest.model.Response] of type [HotelDetailsResponse]
 *
 * The operation may result in the following exceptions:
 * - [GetLodgingDetails400Exception]
 * - [GetLodgingDetails401Exception]
 * - [GetLodgingDetails403Exception]
 * - [GetLodgingDetails429Exception]
 * - [GetLodgingDetails500Exception]
 * - [GetLodgingDetails503Exception]
 * - [GetLodgingDetails504Exception]
 *
 * @property params [GetLodgingDetailsOperationParams]
 */
class GetLodgingDetailsOperation(
    private val params: GetLodgingDetailsOperationParams,
) : OperationRequestTrait,
    UrlPathTrait,
    JacksonModelOperationResponseBodyTrait<HotelDetailsResponse>,
    UrlQueryParamsTrait,
    HeadersTrait {
    override fun getOperationId(): String = "getLodgingDetails"

    override fun getHttpMethod(): String = "GET"

    override fun getRequestInfo(): OperationRequestTrait = this

    override fun getUrlPath(): String {
        var url = "/hotels/details/{offerToken}"

        url = url.replace(
            oldValue = "{" + "offerToken" + "}",
            newValue = this.params.offerToken,
            ignoreCase = true,
        )

        return url
    }

    override fun getTypeIdentifier(): TypeReference<HotelDetailsResponse> = jacksonTypeRef()

    override fun getHeaders(): Headers = this.params.getHeaders()

    override fun getUrlQueryParams() = this.params.getQueryParams()

    override fun getExceptionForCode(
        code: Int,
        errorResponseStr: String?,
        requestId: UUID?,
        message: String?,
        cause: Throwable?,
    ): ExpediaGroupApiException = when (code) {
        400 -> GetLodgingDetails400Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, Errors::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        401 -> GetLodgingDetails401Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, APIGatewayError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        403 -> GetLodgingDetails403Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, APIGatewayError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        429 -> GetLodgingDetails429Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, APIGatewayError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        500 -> GetLodgingDetails500Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, Errors::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        503 -> GetLodgingDetails503Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, APIGatewayError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        504 -> GetLodgingDetails504Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, APIGatewayError::class.java)
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
