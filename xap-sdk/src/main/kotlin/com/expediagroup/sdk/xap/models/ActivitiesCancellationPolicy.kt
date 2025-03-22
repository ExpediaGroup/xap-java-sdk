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
* Container for the Cancellation Policy information.
 * @param freeCancellation Indicates whether the activity can be canceled free of charge within the cancellation window or not.
 * @param cancelPolicyDescription The description of Cancellation Policy.
 * @param freeCancellationMinHours The minimum number of hours before activity when the activity can still be cancelled for free.
 * @param freeCancellationEndDateTime The date and time after which the activity will not be able to be cancelled for free, stated in the local time to where the activity takes place.
*/
data class ActivitiesCancellationPolicy(
    // Indicates whether the activity can be canceled free of charge within the cancellation window or not.
    @JsonProperty("FreeCancellation")
    val freeCancellation: kotlin.Boolean,
    // The description of Cancellation Policy.
    @JsonProperty("CancelPolicyDescription")
    val cancelPolicyDescription: kotlin.String? = null,
    // The minimum number of hours before activity when the activity can still be cancelled for free.
    @JsonProperty("FreeCancellationMinHours")
    val freeCancellationMinHours: kotlin.Int? = null,
    // The date and time after which the activity will not be able to be cancelled for free, stated in the local time to where the activity takes place.
    @JsonProperty("FreeCancellationEndDateTime")
    val freeCancellationEndDateTime: java.time.LocalDateTime? = null,
) {
    init {
        require(freeCancellation != null) { "freeCancellation must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var freeCancellation: kotlin.Boolean? = null,
        private var cancelPolicyDescription: kotlin.String? = null,
        private var freeCancellationMinHours: kotlin.Int? = null,
        private var freeCancellationEndDateTime: java.time.LocalDateTime? = null,
    ) {
        fun freeCancellation(freeCancellation: kotlin.Boolean) = apply { this.freeCancellation = freeCancellation }

        fun cancelPolicyDescription(cancelPolicyDescription: kotlin.String?) = apply { this.cancelPolicyDescription = cancelPolicyDescription }

        fun freeCancellationMinHours(freeCancellationMinHours: kotlin.Int?) = apply { this.freeCancellationMinHours = freeCancellationMinHours }

        fun freeCancellationEndDateTime(freeCancellationEndDateTime: java.time.LocalDateTime?) = apply { this.freeCancellationEndDateTime = freeCancellationEndDateTime }

        fun build(): ActivitiesCancellationPolicy {
            val instance =
                ActivitiesCancellationPolicy(
                    freeCancellation = freeCancellation!!,
                    cancelPolicyDescription = cancelPolicyDescription,
                    freeCancellationMinHours = freeCancellationMinHours,
                    freeCancellationEndDateTime = freeCancellationEndDateTime,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            freeCancellation = freeCancellation!!,
            cancelPolicyDescription = cancelPolicyDescription,
            freeCancellationMinHours = freeCancellationMinHours,
            freeCancellationEndDateTime = freeCancellationEndDateTime,
        )
}
