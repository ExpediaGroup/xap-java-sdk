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
 * @param adults The number of adults in a room.
 * @param childAges The ages of children in a room.
 */
@ConsistentCopyVisibility data class LodgingOccupant private constructor(
    /* The number of adults in a room. */
    @JsonProperty("Adults")
    val adults: kotlin.Int? = null,

    /* The ages of children in a room. */
    @JsonProperty("ChildAges")
    val childAges: kotlin.collections.List<kotlin.Int>? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var adults: kotlin.Int? = null,
        private var childAges: kotlin.collections.List<kotlin.Int>? = null,
    ) {
        fun adults(adults: kotlin.Int?) = apply { this.adults = adults }

        fun childAges(childAges: kotlin.collections.List<kotlin.Int>?) = apply { this.childAges = childAges }

        fun build(): LodgingOccupant {
            val instance = LodgingOccupant(
                adults = adults,
                childAges = childAges,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        adults = adults,
        childAges = childAges,
    )
}
