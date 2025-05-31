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
import com.expediagroup.sdk.xap.model.AvailabilityCalendarResponse
import com.expediagroup.sdk.xap.model.LodgingErrors
import com.expediagroup.sdk.xap.model.exception.GetLodgingAvailabilityCalendars400Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingAvailabilityCalendars401Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingAvailabilityCalendars403Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingAvailabilityCalendars429Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingAvailabilityCalendars500Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingAvailabilityCalendars503Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingAvailabilityCalendars504Exception
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.UUID

/**
 * Get availability calendars of properties
 *
 * Returns the availability of each day for a range of dates for given Expedia lodging properties.
 *
 * Executing the operation returns [com.expediagroup.sdk.rest.model.Response] of type [AvailabilityCalendarResponse]
 *
 * The operation may result in the following exceptions:
 * - [GetLodgingAvailabilityCalendars400Exception]
 * - [GetLodgingAvailabilityCalendars401Exception]
 * - [GetLodgingAvailabilityCalendars403Exception]
 * - [GetLodgingAvailabilityCalendars429Exception]
 * - [GetLodgingAvailabilityCalendars500Exception]
 * - [GetLodgingAvailabilityCalendars503Exception]
 * - [GetLodgingAvailabilityCalendars504Exception]
 *
 * @property params [GetLodgingAvailabilityCalendarsOperationParams]
 */
class GetLodgingAvailabilityCalendarsOperation(
    private val params: GetLodgingAvailabilityCalendarsOperationParams,
) : OperationRequestTrait,
    UrlPathTrait,
    JacksonModelOperationResponseBodyTrait<AvailabilityCalendarResponse>,
    UrlQueryParamsTrait,
    HeadersTrait {
    override fun getOperationId(): String = "getLodgingAvailabilityCalendars"

    override fun getHttpMethod(): String = "GET"

    override fun getRequestInfo(): OperationRequestTrait = this

    override fun getUrlPath(): String {
        var url = "/lodging/availabilityCalendars"

        return url
    }

    override fun getTypeIdentifier(): TypeReference<AvailabilityCalendarResponse> = jacksonTypeRef()

    override fun getHeaders(): Headers = this.params.getHeaders()

    override fun getUrlQueryParams() = this.params.getQueryParams()

    override fun getExceptionForCode(
        code: Int,
        errorResponseStr: String?,
        requestId: UUID?,
        message: String?,
        cause: Throwable?,
    ): ExpediaGroupApiException = when (code) {
        400 -> GetLodgingAvailabilityCalendars400Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, LodgingErrors::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        401 -> GetLodgingAvailabilityCalendars401Exception(
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
        403 -> GetLodgingAvailabilityCalendars403Exception(
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
        429 -> GetLodgingAvailabilityCalendars429Exception(
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
        500 -> GetLodgingAvailabilityCalendars500Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE.readValue(errorResponseStr, LodgingErrors::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        503 -> GetLodgingAvailabilityCalendars503Exception(
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
        504 -> GetLodgingAvailabilityCalendars504Exception(
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
