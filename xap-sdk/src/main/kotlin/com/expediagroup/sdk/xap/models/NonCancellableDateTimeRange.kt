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
 * Container for non-cancellable date and time range element
 * @param startDateTime The time of this non-cancellable window starts
 * @param endDateTime The time of this non-cancellable window ends
 */
data class NonCancellableDateTimeRange(
    // The time of this non-cancellable window starts
    @JsonProperty("StartDateTime")
    val startDateTime: java.time.LocalDateTime,
    // The time of this non-cancellable window ends
    @JsonProperty("EndDateTime")
    val endDateTime: java.time.LocalDateTime,
) {
    init {
        require(startDateTime != null) { "startDateTime must not be null" }

        require(endDateTime != null) { "endDateTime must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var startDateTime: java.time.LocalDateTime? = null,
        private var endDateTime: java.time.LocalDateTime? = null,
    ) {
        fun startDateTime(startDateTime: java.time.LocalDateTime) = apply { this.startDateTime = startDateTime }

        fun endDateTime(endDateTime: java.time.LocalDateTime) = apply { this.endDateTime = endDateTime }

        fun build(): NonCancellableDateTimeRange {
            val instance =
                NonCancellableDateTimeRange(
                    startDateTime = startDateTime!!,
                    endDateTime = endDateTime!!,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            startDateTime = startDateTime!!,
            endDateTime = endDateTime!!,
        )
}
