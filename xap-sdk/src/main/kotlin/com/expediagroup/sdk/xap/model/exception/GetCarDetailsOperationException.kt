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
import com.expediagroup.sdk.xap.model.APIMError
import com.expediagroup.sdk.xap.model.CarsErrors
import java.util.UUID

class GetCarDetails400Exception(
    code: Int = 400,
    val errorResponse: CarsErrors?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetCarDetails401Exception(
    code: Int = 401,
    val errorResponse: APIMError?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetCarDetails403Exception(
    code: Int = 403,
    val errorResponse: APIMError?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetCarDetails404Exception(
    code: Int = 404,
    val errorResponse: APIMError?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetCarDetails429Exception(
    code: Int = 429,
    val errorResponse: APIMError?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetCarDetails500Exception(
    code: Int = 500,
    val errorResponse: CarsErrors?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetCarDetails503Exception(
    code: Int = 503,
    val errorResponse: APIMError?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class GetCarDetails504Exception(
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
