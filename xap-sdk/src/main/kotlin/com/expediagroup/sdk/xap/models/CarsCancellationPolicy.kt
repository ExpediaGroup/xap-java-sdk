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

import com.expediagroup.sdk.xap.models.NonCancellableDateTimeRange
import com.expediagroup.sdk.xap.models.PenaltyRule
import com.fasterxml.jackson.annotation.JsonProperty

/**
* Cancellation Policy Container.
 * @param cancellable Indicates if this car can be cancelled (free cancel or penalty cancel)
 * @param freeCancellation Indicates if this car may be cancelled without a penalty.
 * @param freeCancellationEndDateTime Indicates the latest time that the car can be cancelled for free.
 * @param penaltyRules Container for penalty rules
 * @param nonCancellableDateTimeRange
*/
data class CarsCancellationPolicy(
    // Indicates if this car can be cancelled (free cancel or penalty cancel)
    @JsonProperty("Cancellable")
    val cancellable: kotlin.Boolean? = null,
    // Indicates if this car may be cancelled without a penalty.
    @JsonProperty("FreeCancellation")
    val freeCancellation: kotlin.Boolean? = null,
    // Indicates the latest time that the car can be cancelled for free.
    @JsonProperty("FreeCancellationEndDateTime")
    val freeCancellationEndDateTime: java.time.LocalDateTime? = null,
    // Container for penalty rules
    @JsonProperty("PenaltyRules")
    val penaltyRules: kotlin.collections.List<PenaltyRule>? = null,
    @JsonProperty("NonCancellableDateTimeRange")
    val nonCancellableDateTimeRange: NonCancellableDateTimeRange? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var cancellable: kotlin.Boolean? = null,
        private var freeCancellation: kotlin.Boolean? = null,
        private var freeCancellationEndDateTime: java.time.LocalDateTime? = null,
        private var penaltyRules: kotlin.collections.List<PenaltyRule>? = null,
        private var nonCancellableDateTimeRange: NonCancellableDateTimeRange? = null,
    ) {
        fun cancellable(cancellable: kotlin.Boolean?) = apply { this.cancellable = cancellable }

        fun freeCancellation(freeCancellation: kotlin.Boolean?) = apply { this.freeCancellation = freeCancellation }

        fun freeCancellationEndDateTime(freeCancellationEndDateTime: java.time.LocalDateTime?) = apply { this.freeCancellationEndDateTime = freeCancellationEndDateTime }

        fun penaltyRules(penaltyRules: kotlin.collections.List<PenaltyRule>?) = apply { this.penaltyRules = penaltyRules }

        fun nonCancellableDateTimeRange(nonCancellableDateTimeRange: NonCancellableDateTimeRange?) = apply { this.nonCancellableDateTimeRange = nonCancellableDateTimeRange }

        fun build(): CarsCancellationPolicy {
            val instance =
                CarsCancellationPolicy(
                    cancellable = cancellable,
                    freeCancellation = freeCancellation,
                    freeCancellationEndDateTime = freeCancellationEndDateTime,
                    penaltyRules = penaltyRules,
                    nonCancellableDateTimeRange = nonCancellableDateTimeRange,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            cancellable = cancellable,
            freeCancellation = freeCancellation,
            freeCancellationEndDateTime = freeCancellationEndDateTime,
            penaltyRules = penaltyRules,
            nonCancellableDateTimeRange = nonCancellableDateTimeRange,
        )
}
