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
* Container for requested dates of stay.
 * @param checkInDate The initial day of the property stay in an ISO 8601 Date format [YYYY-MM-DD].
 * @param checkOutDate The final day of the property stay in an ISO 8601 Date format [YYYY-MM-DD].
*/
data class LodgingStayDates(
    // The initial day of the property stay in an ISO 8601 Date format [YYYY-MM-DD].
    @JsonProperty("CheckInDate")
    val checkInDate: java.time.LocalDate? = null,
    // The final day of the property stay in an ISO 8601 Date format [YYYY-MM-DD].
    @JsonProperty("CheckOutDate")
    val checkOutDate: java.time.LocalDate? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var checkInDate: java.time.LocalDate? = null,
        private var checkOutDate: java.time.LocalDate? = null,
    ) {
        fun checkInDate(checkInDate: java.time.LocalDate?) = apply { this.checkInDate = checkInDate }

        fun checkOutDate(checkOutDate: java.time.LocalDate?) = apply { this.checkOutDate = checkOutDate }

        fun build(): LodgingStayDates {
            val instance =
                LodgingStayDates(
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
