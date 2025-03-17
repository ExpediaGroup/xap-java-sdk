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
 * @param adults Specifies the number of adults staying in each room.
 * @param childAges Specifies the age(s) of each of the children staying in the room, as well as the number of children in the room.
*/
data class HotelDetailsResponseOccupantsInner(
    // Specifies the number of adults staying in each room.
    @JsonProperty("Adults")
    val adults: kotlin.Any? = null,
    // Specifies the age(s) of each of the children staying in the room, as well as the number of children in the room.
    @JsonProperty("ChildAges")
    val childAges: kotlin.Any? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var adults: kotlin.Any? = null,
        private var childAges: kotlin.Any? = null,
    ) {
        fun adults(adults: kotlin.Any?) = apply { this.adults = adults }

        fun childAges(childAges: kotlin.Any?) = apply { this.childAges = childAges }

        fun build(): HotelDetailsResponseOccupantsInner {
            val instance =
                HotelDetailsResponseOccupantsInner(
                    adults = adults,
                    childAges = childAges,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            adults = adults,
            childAges = childAges,
        )
}
