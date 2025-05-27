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
 * Container for non-cancellable date and time range element
 * @param startDateTime The time of this non-cancellable window starts
 * @param endDateTime The time of this non-cancellable window ends
 */
@ConsistentCopyVisibility data class NonCancellableDateTimeRange private constructor(
    /* The time of this non-cancellable window starts */
    @JsonProperty("StartDateTime")
    val startDateTime: java.time.LocalDateTime,

    /* The time of this non-cancellable window ends */
    @JsonProperty("EndDateTime")
    val endDateTime: java.time.LocalDateTime,

) {

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
            val startDateTime = this.startDateTime.getOrThrow {
                IllegalArgumentException("startDateTime must not be null")
            }

            val endDateTime = this.endDateTime.getOrThrow {
                IllegalArgumentException("endDateTime must not be null")
            }

            val instance = NonCancellableDateTimeRange(
                startDateTime = startDateTime,
                endDateTime = endDateTime,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        startDateTime = startDateTime,
        endDateTime = endDateTime,
    )
}
