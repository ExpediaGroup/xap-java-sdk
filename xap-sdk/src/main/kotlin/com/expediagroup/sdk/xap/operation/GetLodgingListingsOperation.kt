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
import com.expediagroup.sdk.xap.model.HotelListingsResponse
import com.expediagroup.sdk.xap.model.exception.GetLodgingListings400Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingListings401Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingListings403Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingListings429Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingListings500Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingListings503Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingListings504Exception
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.UUID

/**
 * Search lodging inventory
 *
 * Search Expedia lodging inventory by Location Keyword, Region ID, Lat/Long, or Hotel ID(s) and return up to 1,000 offers in response.  Provides deeplink to Expedia site to book, or rate plan info to enable API booking.
 *
 * Executing the operation returns [com.expediagroup.sdk.rest.model.Response] of type [HotelListingsResponse]
 *
 * The operation may result in the following exceptions:
 * - [GetLodgingListings400Exception]
 * - [GetLodgingListings401Exception]
 * - [GetLodgingListings403Exception]
 * - [GetLodgingListings429Exception]
 * - [GetLodgingListings500Exception]
 * - [GetLodgingListings503Exception]
 * - [GetLodgingListings504Exception]
 *
 * @property params [GetLodgingListingsOperationParams]
 */
class GetLodgingListingsOperation(
    private val params: GetLodgingListingsOperationParams,
) : OperationRequestTrait,
    UrlPathTrait,
    JacksonModelOperationResponseBodyTrait<HotelListingsResponse>,
    UrlQueryParamsTrait,
    HeadersTrait {
    override fun getOperationId(): String = "getLodgingListings"

    override fun getHttpMethod(): String = "GET"

    override fun getRequestInfo(): OperationRequestTrait = this

    override fun getUrlPath(): String {
        var url = "/hotels/listings"

        return url
    }

    override fun getTypeIdentifier(): TypeReference<HotelListingsResponse> = jacksonTypeRef()

    override fun getHeaders(): Headers = this.params.getHeaders()

    override fun getUrlQueryParams() = this.params.getQueryParams()

    override fun getExceptionForCode(
        code: Int,
        errorResponseStr: String?,
        requestId: UUID?,
        message: String?,
        cause: Throwable?,
    ): ExpediaGroupApiException = when (code) {
        400 -> GetLodgingListings400Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, Errors::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        401 -> GetLodgingListings401Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, APIGatewayError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        403 -> GetLodgingListings403Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, APIGatewayError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        429 -> GetLodgingListings429Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, APIGatewayError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        500 -> GetLodgingListings500Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, Errors::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        503 -> GetLodgingListings503Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, APIGatewayError::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        504 -> GetLodgingListings504Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, APIGatewayError::class.java)
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
