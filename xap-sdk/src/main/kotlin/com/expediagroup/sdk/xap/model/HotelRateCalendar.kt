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

import com.expediagroup.sdk.xap.model.RateCalendar
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for all hotel rate calendar data.
 * @param ecomHotelId The unique, Expedia-specific hotel property identifier used to designate a single hotel.
 * @param hcomHotelId The unique, Hotels.com-specific hotel property identifier used to designate a single hotel.  This will be returned if searching via `hcomHotelId` in request or the request is coming from Hcom partner.
 * @param rateCalendar Container for all rate calendar data.
 */
@ConsistentCopyVisibility data class HotelRateCalendar private constructor(
    /* The unique, Expedia-specific hotel property identifier used to designate a single hotel. */
    @JsonProperty("EcomHotelId")
    val ecomHotelId: kotlin.String? = null,

    /* The unique, Hotels.com-specific hotel property identifier used to designate a single hotel.  This will be returned if searching via `hcomHotelId` in request or the request is coming from Hcom partner.  */
    @JsonProperty("HcomHotelId")
    val hcomHotelId: kotlin.String? = null,

    /* Container for all rate calendar data. */
    @JsonProperty("RateCalendar")
    val rateCalendar: kotlin.collections.List<RateCalendar>? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var ecomHotelId: kotlin.String? = null,
        private var hcomHotelId: kotlin.String? = null,
        private var rateCalendar: kotlin.collections.List<RateCalendar>? = null,
    ) {
        fun ecomHotelId(ecomHotelId: kotlin.String?) = apply { this.ecomHotelId = ecomHotelId }

        fun hcomHotelId(hcomHotelId: kotlin.String?) = apply { this.hcomHotelId = hcomHotelId }

        fun rateCalendar(rateCalendar: kotlin.collections.List<RateCalendar>?) = apply { this.rateCalendar = rateCalendar }

        fun build(): HotelRateCalendar {
            val instance = HotelRateCalendar(
                ecomHotelId = ecomHotelId,
                hcomHotelId = hcomHotelId,
                rateCalendar = rateCalendar,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        ecomHotelId = ecomHotelId,
        hcomHotelId = hcomHotelId,
        rateCalendar = rateCalendar,
    )
}
