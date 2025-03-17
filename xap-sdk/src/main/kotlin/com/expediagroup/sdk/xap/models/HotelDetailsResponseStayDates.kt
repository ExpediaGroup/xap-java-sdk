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
 * @param checkInDate Check-in date for property stay in an ISO 8601 Date format [YYYY-MM-DD].  This parameter should be used in combination with the `checkOut` parameter.  The maximum advanced search window is 330 days in the future.  The maximum length of stay is 28 days.
 * @param checkOutDate Checkout date for property stay in an ISO 8601 Date format [YYYY-MM-DD].  This parameter should be used in combination with the `checkIn` parameter.  The maximum advanced search window is 330 days in the future.  The maximum length of stay is 28 days.
*/
data class HotelDetailsResponseStayDates(
    // Check-in date for property stay in an ISO 8601 Date format [YYYY-MM-DD].  This parameter should be used in combination with the `checkOut` parameter.  The maximum advanced search window is 330 days in the future.  The maximum length of stay is 28 days.
    @JsonProperty("CheckInDate")
    val checkInDate: kotlin.Any? = null,
    // Checkout date for property stay in an ISO 8601 Date format [YYYY-MM-DD].  This parameter should be used in combination with the `checkIn` parameter.  The maximum advanced search window is 330 days in the future.  The maximum length of stay is 28 days.
    @JsonProperty("CheckOutDate")
    val checkOutDate: kotlin.Any? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var checkInDate: kotlin.Any? = null,
        private var checkOutDate: kotlin.Any? = null,
    ) {
        fun checkInDate(checkInDate: kotlin.Any?) = apply { this.checkInDate = checkInDate }

        fun checkOutDate(checkOutDate: kotlin.Any?) = apply { this.checkOutDate = checkOutDate }

        fun build(): HotelDetailsResponseStayDates {
            val instance =
                HotelDetailsResponseStayDates(
                    checkInDate = checkInDate,
                    checkOutDate = checkOutDate,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            checkInDate = checkInDate,
            checkOutDate = checkOutDate,
        )
}
