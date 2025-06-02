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
package com.expediagroup.sdk.xap.configuration

import com.expediagroup.sdk.core.http.MediaType

internal val loggableMediaTypes: List<MediaType> = listOf(
    MediaType.parse("application/vnd.exp-activity.v3+json"),
    MediaType.parse("application/vnd.exp-hotel.v3+json"),
    MediaType.parse("application/vnd.exp-lodging.v3+json"),
    MediaType.parse("application/vnd.exp-car.v3+json"),
    MediaType.parse("application/vnd.exp-lodging.v1+json"),
    MediaType.parse("application/vnd.exp-flight.v1+json"),
    MediaType.parse("application/vnd.exp-flight.v2+json"),
    MediaType.parse("application/vnd.exp-flight.v3+json"),
)
