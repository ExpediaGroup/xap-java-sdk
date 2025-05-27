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
import com.expediagroup.sdk.rest.trait.operation.ContentTypeTrait
import com.expediagroup.sdk.rest.trait.operation.HeadersTrait
import com.expediagroup.sdk.rest.trait.operation.JacksonModelOperationResponseBodyTrait
import com.expediagroup.sdk.rest.trait.operation.OperationRequestBodyTrait
import com.expediagroup.sdk.rest.trait.operation.OperationRequestTrait
import com.expediagroup.sdk.rest.trait.operation.UrlPathTrait
import com.expediagroup.sdk.xap.model.ErrorResponse
import com.expediagroup.sdk.xap.model.FlightLinksRequest
import com.expediagroup.sdk.xap.model.FlightLinksResponse
import com.expediagroup.sdk.xap.model.PostFlightLinks401Response
import com.expediagroup.sdk.xap.model.PostFlightLinks403Response
import com.expediagroup.sdk.xap.model.PostFlightLinks404Response
import com.expediagroup.sdk.xap.model.PostFlightLinks405Response
import com.expediagroup.sdk.xap.model.PostFlightLinks429Response
import com.expediagroup.sdk.xap.model.PostFlightLinks503Response
import com.expediagroup.sdk.xap.model.PostFlightLinks504Response
import com.expediagroup.sdk.xap.model.exception.PostFlightLinks400Exception
import com.expediagroup.sdk.xap.model.exception.PostFlightLinks401Exception
import com.expediagroup.sdk.xap.model.exception.PostFlightLinks403Exception
import com.expediagroup.sdk.xap.model.exception.PostFlightLinks404Exception
import com.expediagroup.sdk.xap.model.exception.PostFlightLinks405Exception
import com.expediagroup.sdk.xap.model.exception.PostFlightLinks429Exception
import com.expediagroup.sdk.xap.model.exception.PostFlightLinks500Exception
import com.expediagroup.sdk.xap.model.exception.PostFlightLinks503Exception
import com.expediagroup.sdk.xap.model.exception.PostFlightLinks504Exception
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.UUID

/**
 * Request for flight links
 *
 * The API inputs define a particular flight itinerary. The API response will include deeplinks to Expedia Flight Infosite and/or an API query for details for the selected flight.
 *
 * Executing the operation returns [com.expediagroup.sdk.rest.model.Response] of type [FlightLinksResponse]
 *
 * The operation may result in the following exceptions:
 * - [PostFlightLinks400Exception]
 * - [PostFlightLinks401Exception]
 * - [PostFlightLinks403Exception]
 * - [PostFlightLinks404Exception]
 * - [PostFlightLinks405Exception]
 * - [PostFlightLinks429Exception]
 * - [PostFlightLinks500Exception]
 * - [PostFlightLinks503Exception]
 * - [PostFlightLinks504Exception]
 *
 * @property requestBody [FlightLinksRequest]
 * @property params [PostFlightLinksOperationParams]
 */
class PostFlightLinksOperation(
    private val params: PostFlightLinksOperationParams,
    private val requestBody: FlightLinksRequest,
) : OperationRequestTrait,
    UrlPathTrait,
    OperationRequestBodyTrait<FlightLinksRequest>,
    JacksonModelOperationResponseBodyTrait<FlightLinksResponse>,
    ContentTypeTrait,
    HeadersTrait {
    override fun getOperationId(): String = "postFlightLinks"

    override fun getHttpMethod(): String = "POST"

    override fun getRequestInfo(): OperationRequestTrait = this

    override fun getUrlPath(): String {
        var url = "/flights/links"

        return url
    }

    override fun getRequestBody(): FlightLinksRequest = requestBody

    override fun getTypeIdentifier(): TypeReference<FlightLinksResponse> = jacksonTypeRef()

    override fun getHeaders(): Headers = this.params.getHeaders()

    override fun getContentType(): String = "application/json"

    override fun getExceptionForCode(
        code: Int,
        errorResponseStr: String?,
        requestId: UUID?,
        message: String?,
        cause: Throwable?,
    ): ExpediaGroupApiException = when (code) {
        400 -> PostFlightLinks400Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, ErrorResponse::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        401 -> PostFlightLinks401Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, PostFlightLinks401Response::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        403 -> PostFlightLinks403Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, PostFlightLinks403Response::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        404 -> PostFlightLinks404Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, PostFlightLinks404Response::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        405 -> PostFlightLinks405Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, PostFlightLinks405Response::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        429 -> PostFlightLinks429Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, PostFlightLinks429Response::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        500 -> PostFlightLinks500Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, ErrorResponse::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        503 -> PostFlightLinks503Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, PostFlightLinks503Response::class.java)
                } catch (e: Exception) {
                    null
                }
            },
            message = message,
            cause = cause,
        )
        504 -> PostFlightLinks504Exception(
            code = code,
            requestId = requestId,
            errorResponse = errorResponseStr?.let {
                try {
                    com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER.readValue(errorResponseStr, PostFlightLinks504Response::class.java)
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
