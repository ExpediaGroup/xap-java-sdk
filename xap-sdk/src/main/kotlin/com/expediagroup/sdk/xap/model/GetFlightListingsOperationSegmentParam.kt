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
package com.expediagroup.sdk.xap.models

data class GetFlightListingsOperationSegmentParam(
    val origin: String,
    val destination: String,
    val departureDate: java.time.LocalDate,
    val departureStartTime: String?,
    val departureEndTime: String?,
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var origin: String? = null,
        private var destination: String? = null,
        private var departureDate: java.time.LocalDate? = null,
        private var departureStartTime: String? = null,
        private var departureEndTime: String? = null,
    ) {
        fun origin(origin: String?) = apply { this.origin = origin }

        fun destination(destination: String?) = apply { this.destination = destination }

        fun departureDate(departureDate: java.time.LocalDate?) = apply { this.departureDate = departureDate }

        fun departureStartTime(departureStartTime: String?) = apply { this.departureStartTime = departureStartTime }

        fun departureEndTime(departureEndTime: String?) = apply { this.departureEndTime = departureEndTime }

        fun build(): GetFlightListingsOperationSegmentParam =
            GetFlightListingsOperationSegmentParam(
                origin = this.origin!!,
                destination = this.destination!!,
                departureDate = this.departureDate!!,
                departureStartTime = this.departureStartTime,
                departureEndTime = this.departureEndTime,
            )
    }
}
