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
package com.expediagroup.sdk.xap.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for Hotel policy information.
 * @param checkInStartTime Beginning of the standard check-in window on the check in date, and in the local time of the hotel.
 * @param checkInEndTime End of the standard check-in window on the check in date, and in the local time of the hotel.
 * @param specialCheckInInstructions Some special instructions needed care by customer when check in.
 * @param checkOutTime Customers must check out before this time on the check out date, expressed in the local time of the hotel.
 * @param petPolicies The policy of the property toward having pets stay with guests.
 * @param childrenAndExtraBedsPolicies The policy of the hotel for having children stay at the hotel, as well as for including extra beds in the room.
 */
data class HotelPolicies(
    // Beginning of the standard check-in window on the check in date, and in the local time of the hotel.
    @JsonProperty("CheckInStartTime")
    val checkInStartTime: kotlin.String? = null,
    // End of the standard check-in window on the check in date, and in the local time of the hotel.
    @JsonProperty("CheckInEndTime")
    val checkInEndTime: kotlin.String? = null,
    // Some special instructions needed care by customer when check in.
    @JsonProperty("SpecialCheckInInstructions")
    val specialCheckInInstructions: kotlin.collections.List<kotlin.String>? = null,
    // Customers must check out before this time on the check out date, expressed in the local time of the hotel.
    @JsonProperty("CheckOutTime")
    val checkOutTime: kotlin.String? = null,
    // The policy of the property toward having pets stay with guests.
    @JsonProperty("PetPolicies")
    val petPolicies: kotlin.collections.List<kotlin.String>? = null,
    // The policy of the hotel for having children stay at the hotel, as well as for including extra beds in the room.
    @JsonProperty("ChildrenAndExtraBedsPolicies")
    val childrenAndExtraBedsPolicies: kotlin.collections.List<kotlin.String>? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var checkInStartTime: kotlin.String? = null,
        private var checkInEndTime: kotlin.String? = null,
        private var specialCheckInInstructions: kotlin.collections.List<kotlin.String>? = null,
        private var checkOutTime: kotlin.String? = null,
        private var petPolicies: kotlin.collections.List<kotlin.String>? = null,
        private var childrenAndExtraBedsPolicies: kotlin.collections.List<kotlin.String>? = null,
    ) {
        fun checkInStartTime(checkInStartTime: kotlin.String?) = apply { this.checkInStartTime = checkInStartTime }

        fun checkInEndTime(checkInEndTime: kotlin.String?) = apply { this.checkInEndTime = checkInEndTime }

        fun specialCheckInInstructions(specialCheckInInstructions: kotlin.collections.List<kotlin.String>?) = apply { this.specialCheckInInstructions = specialCheckInInstructions }

        fun checkOutTime(checkOutTime: kotlin.String?) = apply { this.checkOutTime = checkOutTime }

        fun petPolicies(petPolicies: kotlin.collections.List<kotlin.String>?) = apply { this.petPolicies = petPolicies }

        fun childrenAndExtraBedsPolicies(childrenAndExtraBedsPolicies: kotlin.collections.List<kotlin.String>?) = apply { this.childrenAndExtraBedsPolicies = childrenAndExtraBedsPolicies }

        fun build(): HotelPolicies {
            val instance =
                HotelPolicies(
                    checkInStartTime = checkInStartTime,
                    checkInEndTime = checkInEndTime,
                    specialCheckInInstructions = specialCheckInInstructions,
                    checkOutTime = checkOutTime,
                    petPolicies = petPolicies,
                    childrenAndExtraBedsPolicies = childrenAndExtraBedsPolicies,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            checkInStartTime = checkInStartTime,
            checkInEndTime = checkInEndTime,
            specialCheckInInstructions = specialCheckInInstructions,
            checkOutTime = checkOutTime,
            petPolicies = petPolicies,
            childrenAndExtraBedsPolicies = childrenAndExtraBedsPolicies,
        )
}
