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

import com.expediagroup.sdk.xap.models.AgeClassRestriction
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Room occupancy policy.
 * @param maxGuestCount The maximum number of guests allowed to stay in a room.
 * @param minCheckInAge The minimum age required for check-in.
 * @param includedGuestCount The number of guests included in base rate.
 * @param minGuestAge The minimum age required for any guest staying in the room.
 * @param ageClassRestrictions Container for room occupancy rules based on the age of the guests.
 */
data class RoomOccupancyPolicy(
    // The maximum number of guests allowed to stay in a room.
    @JsonProperty("MaxGuestCount")
    val maxGuestCount: kotlin.Int? = null,
    // The minimum age required for check-in.
    @JsonProperty("MinCheckInAge")
    val minCheckInAge: kotlin.Int? = null,
    // The number of guests included in base rate.
    @JsonProperty("IncludedGuestCount")
    val includedGuestCount: kotlin.Int? = null,
    // The minimum age required for any guest staying in the room.
    @JsonProperty("MinGuestAge")
    val minGuestAge: kotlin.Int? = null,
    // Container for room occupancy rules based on the age of the guests.
    @JsonProperty("AgeClassRestrictions")
    val ageClassRestrictions: kotlin.collections.List<AgeClassRestriction>? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var maxGuestCount: kotlin.Int? = null,
        private var minCheckInAge: kotlin.Int? = null,
        private var includedGuestCount: kotlin.Int? = null,
        private var minGuestAge: kotlin.Int? = null,
        private var ageClassRestrictions: kotlin.collections.List<AgeClassRestriction>? = null,
    ) {
        fun maxGuestCount(maxGuestCount: kotlin.Int?) = apply { this.maxGuestCount = maxGuestCount }

        fun minCheckInAge(minCheckInAge: kotlin.Int?) = apply { this.minCheckInAge = minCheckInAge }

        fun includedGuestCount(includedGuestCount: kotlin.Int?) = apply { this.includedGuestCount = includedGuestCount }

        fun minGuestAge(minGuestAge: kotlin.Int?) = apply { this.minGuestAge = minGuestAge }

        fun ageClassRestrictions(ageClassRestrictions: kotlin.collections.List<AgeClassRestriction>?) = apply { this.ageClassRestrictions = ageClassRestrictions }

        fun build(): RoomOccupancyPolicy {
            val instance =
                RoomOccupancyPolicy(
                    maxGuestCount = maxGuestCount,
                    minCheckInAge = minCheckInAge,
                    includedGuestCount = includedGuestCount,
                    minGuestAge = minGuestAge,
                    ageClassRestrictions = ageClassRestrictions,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            maxGuestCount = maxGuestCount,
            minCheckInAge = minCheckInAge,
            includedGuestCount = includedGuestCount,
            minGuestAge = minGuestAge,
            ageClassRestrictions = ageClassRestrictions,
        )
}
