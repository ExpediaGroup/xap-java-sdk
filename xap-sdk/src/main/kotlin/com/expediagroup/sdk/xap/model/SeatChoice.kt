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
 * Contains seat choice information.
 * @param availability Availability of Amenity
 * @param restriction Restriction if any on SeatChoice Amenity.
 */
@ConsistentCopyVisibility data class SeatChoice private constructor(
    /* Availability of Amenity */
    @JsonProperty("Availability")
    val availability: SeatChoice.Availability? = null,

    /* Restriction if any on SeatChoice Amenity. */
    @JsonProperty("Restriction")
    val restriction: SeatChoice.Restriction? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var availability: SeatChoice.Availability? = null,
        private var restriction: SeatChoice.Restriction? = null,
    ) {
        fun availability(availability: SeatChoice.Availability?) = apply { this.availability = availability }

        fun restriction(restriction: SeatChoice.Restriction?) = apply { this.restriction = restriction }

        fun build(): SeatChoice {
            val instance = SeatChoice(
                availability = availability,
                restriction = restriction,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        availability = availability,
        restriction = restriction,
    )

    /**
     * Availability of Amenity
     * Values: INCLUDED,NOT_AVAILABLE,AVAILABLE_FOR_FEE
     */
    enum class Availability(val value: kotlin.String) {
        @JsonProperty("INCLUDED")
        INCLUDED("INCLUDED"),

        @JsonProperty("NOT_AVAILABLE")
        NOT_AVAILABLE("NOT_AVAILABLE"),

        @JsonProperty("AVAILABLE_FOR_FEE")
        AVAILABLE_FOR_FEE("AVAILABLE_FOR_FEE"),
    }

    /**
     * Restriction if any on SeatChoice Amenity.
     * Values: ONLY_AT_CHECKIN
     */
    enum class Restriction(val value: kotlin.String) {
        @JsonProperty("ONLY_AT_CHECKIN")
        ONLY_AT_CHECKIN("ONLY_AT_CHECKIN"),
    }
}
