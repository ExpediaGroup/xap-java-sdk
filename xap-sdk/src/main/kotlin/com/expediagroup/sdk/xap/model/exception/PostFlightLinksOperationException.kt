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
import com.expediagroup.sdk.xap.model.ErrorResponse
import com.expediagroup.sdk.xap.model.PostFlightLinks401Response
import com.expediagroup.sdk.xap.model.PostFlightLinks403Response
import com.expediagroup.sdk.xap.model.PostFlightLinks404Response
import com.expediagroup.sdk.xap.model.PostFlightLinks405Response
import com.expediagroup.sdk.xap.model.PostFlightLinks429Response
import com.expediagroup.sdk.xap.model.PostFlightLinks503Response
import com.expediagroup.sdk.xap.model.PostFlightLinks504Response
import java.util.UUID

class PostFlightLinks400Exception(
    code: Int = 400,
    val errorResponse: ErrorResponse?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class PostFlightLinks401Exception(
    code: Int = 401,
    val errorResponse: PostFlightLinks401Response?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class PostFlightLinks403Exception(
    code: Int = 403,
    val errorResponse: PostFlightLinks403Response?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class PostFlightLinks404Exception(
    code: Int = 404,
    val errorResponse: PostFlightLinks404Response?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class PostFlightLinks405Exception(
    code: Int = 405,
    val errorResponse: PostFlightLinks405Response?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class PostFlightLinks429Exception(
    code: Int = 429,
    val errorResponse: PostFlightLinks429Response?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class PostFlightLinks500Exception(
    code: Int = 500,
    val errorResponse: ErrorResponse?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class PostFlightLinks503Exception(
    code: Int = 503,
    val errorResponse: PostFlightLinks503Response?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)

class PostFlightLinks504Exception(
    code: Int = 504,
    val errorResponse: PostFlightLinks504Response?,
    requestId: UUID?,
    message: String?,
    cause: Throwable?,
) : ExpediaGroupApiException(
    code = code,
    requestId = requestId,
    message = message,
    cause = cause,
)
