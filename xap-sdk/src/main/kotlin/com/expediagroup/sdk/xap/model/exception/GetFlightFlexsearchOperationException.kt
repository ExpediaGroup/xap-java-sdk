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
package com.expediagroup.sdk.xap.model.exception

import com.expediagroup.sdk.rest.exception.service.ExpediaGroupApiException
import com.expediagroup.sdk.xap.model.FlightsV3APIMError
import com.expediagroup.sdk.xap.model.GetFlightFlexsearch400Response
import java.util.UUID

class GetFlightFlexsearch400Exception(
    code: Int = 400,
    val errorResponse: GetFlightFlexsearch400Response?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetFlightFlexsearch401Exception(
    code: Int = 401,
    val errorResponse: FlightsV3APIMError?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetFlightFlexsearch403Exception(
    code: Int = 403,
    val errorResponse: FlightsV3APIMError?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetFlightFlexsearch404Exception(
    code: Int = 404,
    val errorResponse: FlightsV3APIMError?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetFlightFlexsearch429Exception(
    code: Int = 429,
    val errorResponse: FlightsV3APIMError?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetFlightFlexsearch500Exception(
    code: Int = 500,
    val errorResponse: GetFlightFlexsearch400Response?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetFlightFlexsearch503Exception(
    code: Int = 503,
    val errorResponse: FlightsV3APIMError?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetFlightFlexsearch504Exception(
    code: Int = 504,
    val errorResponse: String?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)
