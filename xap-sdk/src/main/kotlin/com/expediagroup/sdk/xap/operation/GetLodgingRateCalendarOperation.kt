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
import com.expediagroup.sdk.xap.model.RateCalendarResponse
import com.expediagroup.sdk.xap.model.exception.GetLodgingRateCalendar400Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingRateCalendar401Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingRateCalendar403Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingRateCalendar409Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingRateCalendar429Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingRateCalendar500Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingRateCalendar503Exception
import com.expediagroup.sdk.xap.model.exception.GetLodgingRateCalendar504Exception
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.UUID

/**
 * Get rate calendar of a property
 *
 * The Rate Calendar API will return the lowest rate plan for a range of days for one selected Expedia lodging property.
 *
 * Executing the operation returns [com.expediagroup.sdk.rest.model.Response] of type [RateCalendarResponse]
 *
 * The operation may result in the following exceptions:
 * - [GetLodgingRateCalendar400Exception]
 * - [GetLodgingRateCalendar401Exception]
 * - [GetLodgingRateCalendar403Exception]
 * - [GetLodgingRateCalendar409Exception]
 * - [GetLodgingRateCalendar429Exception]
 * - [GetLodgingRateCalendar500Exception]
 * - [GetLodgingRateCalendar503Exception]
 * - [GetLodgingRateCalendar504Exception]
 *
 * @property params [GetLodgingRateCalendarOperationParams]
 */
class GetLodgingRateCalendarOperation(
    private val params: GetLodgingRateCalendarOperationParams,
) : OperationRequestTrait,
    UrlPathTrait,
    JacksonModelOperationResponseBodyTrait<RateCalendarResponse>,
    UrlQueryParamsTrait,
    HeadersTrait {
    override fun getOperationId(): String = "getLodgingRateCalendar"

    override fun getHttpMethod(): String = "GET"

    override fun getRequestInfo(): OperationRequestTrait = this

    override fun getUrlPath(): String {
        var url = "/hotels/rateCalendar"

        return url
    }

    override fun getTypeIdentifier(): TypeReference<RateCalendarResponse> = jacksonTypeRef()

    override fun getHeaders(): Headers = this.params.getHeaders()

    override fun getUrlQueryParams() = this.params.getQueryParams()

    override fun getExceptionForCode(
        code: Int,
        errorResponseStr: String?,
        requestId: UUID?,
        message: String?,
        cause: Throwable?,
    ): ExpediaGroupApiException = when (code) {
        400 -> GetLodgingRateCalendar400Exception(
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
        401 -> GetLodgingRateCalendar401Exception(
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
        403 -> GetLodgingRateCalendar403Exception(
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
        409 -> GetLodgingRateCalendar409Exception(
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
        429 -> GetLodgingRateCalendar429Exception(
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
        500 -> GetLodgingRateCalendar500Exception(
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
        503 -> GetLodgingRateCalendar503Exception(
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
        504 -> GetLodgingRateCalendar504Exception(
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
