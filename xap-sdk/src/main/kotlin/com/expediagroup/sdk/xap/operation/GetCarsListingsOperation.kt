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
import com.expediagroup.sdk.xap.model.CarListingsResponse
import com.expediagroup.sdk.xap.model.CarsErrors
import com.expediagroup.sdk.xap.model.exception.GetCarsListings400Exception
import com.expediagroup.sdk.xap.model.exception.GetCarsListings401Exception
import com.expediagroup.sdk.xap.model.exception.GetCarsListings403Exception
import com.expediagroup.sdk.xap.model.exception.GetCarsListings404Exception
import com.expediagroup.sdk.xap.model.exception.GetCarsListings429Exception
import com.expediagroup.sdk.xap.model.exception.GetCarsListings503Exception
import com.expediagroup.sdk.xap.model.exception.GetCarsListings504Exception
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.UUID

/**
 * Search Expedia car inventory
 *
 * Search Expedia car inventory by date, pickup, and dropoff location to return a listing of available cars for hire.
 *
 * Executing the operation returns [com.expediagroup.sdk.rest.model.Response] of type [CarListingsResponse]
 *
 * The operation may result in the following exceptions:
 * - [GetCarsListings400Exception]
 * - [GetCarsListings401Exception]
 * - [GetCarsListings403Exception]
 * - [GetCarsListings404Exception]
 * - [GetCarsListings429Exception]
 * - [GetCarsListings503Exception]
 * - [GetCarsListings504Exception]
 *
 * @property params [GetCarsListingsOperationParams]
 */
class GetCarsListingsOperation(
    private val params: GetCarsListingsOperationParams,
) : OperationRequestTrait,
    UrlPathTrait,
    JacksonModelOperationResponseBodyTrait<CarListingsResponse>,
    UrlQueryParamsTrait,
    HeadersTrait {
    override fun getOperationId(): String = "getCarsListings"

    override fun getHttpMethod(): String = "GET"

    override fun getRequestInfo(): OperationRequestTrait = this

    override fun getUrlPath(): String {
        var url = "/cars/listings"

        return url
    }

    override fun getTypeIdentifier(): TypeReference<CarListingsResponse> = jacksonTypeRef()

    override fun getHeaders(): Headers = this.params.getHeaders()

    override fun getUrlQueryParams() = this.params.getQueryParams()

    override fun getExceptionForCode(
        code: Int,
        errorResponseStr: String?,
        requestId: UUID?,
        message: String?,
        cause: Throwable?,
    ): ExpediaGroupApiException = when (code) {
        400 -> GetCarsListings400Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, CarsErrors::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        401 -> GetCarsListings401Exception(
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
        403 -> GetCarsListings403Exception(
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
        404 -> GetCarsListings404Exception(
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
        429 -> GetCarsListings429Exception(
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
        503 -> GetCarsListings503Exception(
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
        504 -> GetCarsListings504Exception(
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
