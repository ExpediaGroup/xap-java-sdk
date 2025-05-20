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
 *
 * @param adult
 * @param senior
 * @param childrenAges
 * @param infantInSeat
 * @param infantInLap
 */
data class PassengerDetails(
    @JsonProperty("Adult")
    val adult: kotlin.String,
    @JsonProperty("Senior")
    val senior: kotlin.String,
    @JsonProperty("ChildrenAges")
    val childrenAges: kotlin.String? = null,
    @JsonProperty("InfantInSeat")
    val infantInSeat: kotlin.String? = null,
    @JsonProperty("InfantInLap")
    val infantInLap: kotlin.String? = null,
) {
    init {
        require(adult != null) { "adult must not be null" }

        require(senior != null) { "senior must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var adult: kotlin.String? = null,
        private var senior: kotlin.String? = null,
        private var childrenAges: kotlin.String? = null,
        private var infantInSeat: kotlin.String? = null,
        private var infantInLap: kotlin.String? = null,
    ) {
        fun adult(adult: kotlin.String) = apply { this.adult = adult }

        fun senior(senior: kotlin.String) = apply { this.senior = senior }

        fun childrenAges(childrenAges: kotlin.String?) = apply { this.childrenAges = childrenAges }

        fun infantInSeat(infantInSeat: kotlin.String?) = apply { this.infantInSeat = infantInSeat }

        fun infantInLap(infantInLap: kotlin.String?) = apply { this.infantInLap = infantInLap }

        fun build(): PassengerDetails {
            val instance =
                PassengerDetails(
                    adult = adult!!,
                    senior = senior!!,
                    childrenAges = childrenAges,
                    infantInSeat = infantInSeat,
                    infantInLap = infantInLap,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            adult = adult!!,
            senior = senior!!,
            childrenAges = childrenAges,
            infantInSeat = infantInSeat,
            infantInLap = infantInLap,
        )
}
