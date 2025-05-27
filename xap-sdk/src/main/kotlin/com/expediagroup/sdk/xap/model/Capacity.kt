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
 * Capacity for car's properties.
 * @param adultCount The typical number of adults that can fit comfortably in the car.
 * @param childCount The typical number of children that can fit comfortably in the car.
 * @param smallLuggageCount The typical number of small pieces of luggage that fit in the cargo space.
 * @param largeLuggageCount The typical number of large pieces of luggage that fit in the cargo space.
 */
@ConsistentCopyVisibility data class Capacity private constructor(
    /* The typical number of adults that can fit comfortably in the car. */
    @JsonProperty("AdultCount")
    val adultCount: kotlin.Long,

    /* The typical number of children that can fit comfortably in the car. */
    @JsonProperty("ChildCount")
    val childCount: kotlin.Long? = null,

    /* The typical number of small pieces of luggage that fit in the cargo space. */
    @JsonProperty("SmallLuggageCount")
    val smallLuggageCount: kotlin.Long? = null,

    /* The typical number of large pieces of luggage that fit in the cargo space. */
    @JsonProperty("LargeLuggageCount")
    val largeLuggageCount: kotlin.Long? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var adultCount: kotlin.Long? = null,
        private var childCount: kotlin.Long? = null,
        private var smallLuggageCount: kotlin.Long? = null,
        private var largeLuggageCount: kotlin.Long? = null,
    ) {
        fun adultCount(adultCount: kotlin.Long) = apply { this.adultCount = adultCount }

        fun childCount(childCount: kotlin.Long?) = apply { this.childCount = childCount }

        fun smallLuggageCount(smallLuggageCount: kotlin.Long?) = apply { this.smallLuggageCount = smallLuggageCount }

        fun largeLuggageCount(largeLuggageCount: kotlin.Long?) = apply { this.largeLuggageCount = largeLuggageCount }

        fun build(): Capacity {
            val adultCount = this.adultCount.getOrThrow {
                IllegalArgumentException("adultCount must not be null")
            }

            val instance = Capacity(
                adultCount = adultCount,
                childCount = childCount,
                smallLuggageCount = smallLuggageCount,
                largeLuggageCount = largeLuggageCount,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        adultCount = adultCount,
        childCount = childCount,
        smallLuggageCount = smallLuggageCount,
        largeLuggageCount = largeLuggageCount,
    )
}
