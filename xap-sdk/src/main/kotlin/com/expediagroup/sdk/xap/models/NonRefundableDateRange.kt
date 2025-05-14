/*
 * Copyright (C) 2022 Expedia, Inc.
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
 *
 * @param startDate Start date of a non-refundable date range.
 * @param endDate End date of a non-refundable date range.
 */
data class NonRefundableDateRange(
    // Start date of a non-refundable date range.
    @JsonProperty("StartDate")
    val startDate: java.time.LocalDate? = null,
    // End date of a non-refundable date range.
    @JsonProperty("EndDate")
    val endDate: java.time.LocalDate? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var startDate: java.time.LocalDate? = null,
        private var endDate: java.time.LocalDate? = null,
    ) {
        fun startDate(startDate: java.time.LocalDate?) = apply { this.startDate = startDate }

        fun endDate(endDate: java.time.LocalDate?) = apply { this.endDate = endDate }

        fun build(): NonRefundableDateRange {
            val instance =
                NonRefundableDateRange(
                    startDate = startDate,
                    endDate = endDate,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            startDate = startDate,
            endDate = endDate,
        )
}
