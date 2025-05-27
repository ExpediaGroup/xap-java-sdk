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
import com.expediagroup.sdk.xap.model.APIGatewayError
import com.expediagroup.sdk.xap.model.Errors
import java.util.UUID

class GetLodgingRateCalendar400Exception(
    code: Int = 400,
    val errorResponse: Errors?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetLodgingRateCalendar401Exception(
    code: Int = 401,
    val errorResponse: APIGatewayError?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetLodgingRateCalendar403Exception(
    code: Int = 403,
    val errorResponse: APIGatewayError?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetLodgingRateCalendar409Exception(
    code: Int = 409,
    val errorResponse: Errors?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetLodgingRateCalendar429Exception(
    code: Int = 429,
    val errorResponse: APIGatewayError?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetLodgingRateCalendar500Exception(
    code: Int = 500,
    val errorResponse: Errors?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetLodgingRateCalendar503Exception(
    code: Int = 503,
    val errorResponse: APIGatewayError?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetLodgingRateCalendar504Exception(
    code: Int = 504,
    val errorResponse: APIGatewayError?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)
