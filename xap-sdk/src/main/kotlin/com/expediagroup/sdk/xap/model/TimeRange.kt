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

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * A list of time range to indicate the operation hours of the date range.
 * @param startTime Start time at pickup location of the date range.
 * @param endTime End time at pickup location of the date range.
 */
data class TimeRange(
    // Start time at pickup location of the date range.
    @JsonProperty("StartTime")
    val startTime: kotlin.String,
    // End time at pickup location of the date range.
    @JsonProperty("EndTime")
    val endTime: kotlin.String,
) {
    init {
        require(startTime != null) { "startTime must not be null" }

        require(endTime != null) { "endTime must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var startTime: kotlin.String? = null,
        private var endTime: kotlin.String? = null,
    ) {
        fun startTime(startTime: kotlin.String) = apply { this.startTime = startTime }

        fun endTime(endTime: kotlin.String) = apply { this.endTime = endTime }

        fun build(): TimeRange {
            val instance =
                TimeRange(
                    startTime = startTime!!,
                    endTime = endTime!!,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            startTime = startTime!!,
            endTime = endTime!!,
        )
}
