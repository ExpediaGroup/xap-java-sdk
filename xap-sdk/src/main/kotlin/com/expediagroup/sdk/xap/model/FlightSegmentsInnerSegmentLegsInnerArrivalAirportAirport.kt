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
package com.expediagroup.sdk.xap.model

import com.expediagroup.sdk.core.common.getOrThrow
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param code
 */
@ConsistentCopyVisibility data class FlightSegmentsInnerSegmentLegsInnerArrivalAirportAirport private constructor(
    @JsonProperty("Code")
    val code: kotlin.String,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: kotlin.String? = null,
    ) {
        fun code(code: kotlin.String) = apply { this.code = code }

        fun build(): FlightSegmentsInnerSegmentLegsInnerArrivalAirportAirport {
            val code = this.code.getOrThrow {
                IllegalArgumentException("code must not be null")
            }

            val instance = FlightSegmentsInnerSegmentLegsInnerArrivalAirportAirport(
                code = code,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        code = code,
    )
}
