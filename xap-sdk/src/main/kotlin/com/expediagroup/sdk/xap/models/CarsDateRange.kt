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

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Date range of the period.
 * @param startDate Start date at pickup location of the period.
 * @param endDate End date at pickup location of the period.
 */
data class CarsDateRange(
    // Start date at pickup location of the period.
    @JsonProperty("StartDate")
    val startDate: java.time.LocalDate,
    // End date at pickup location of the period.
    @JsonProperty("EndDate")
    val endDate: java.time.LocalDate,
) {
    init {
        require(startDate != null) { "startDate must not be null" }

        require(endDate != null) { "endDate must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var startDate: java.time.LocalDate? = null,
        private var endDate: java.time.LocalDate? = null,
    ) {
        fun startDate(startDate: java.time.LocalDate) = apply { this.startDate = startDate }

        fun endDate(endDate: java.time.LocalDate) = apply { this.endDate = endDate }

        fun build(): CarsDateRange {
            val instance =
                CarsDateRange(
                    startDate = startDate!!,
                    endDate = endDate!!,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            startDate = startDate!!,
            endDate = endDate!!,
        )
}
