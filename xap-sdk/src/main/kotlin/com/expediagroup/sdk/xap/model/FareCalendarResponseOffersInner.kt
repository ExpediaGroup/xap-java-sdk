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
import com.expediagroup.sdk.xap.model.FareCalendarResponseOffersInnerOfferPrice
import com.expediagroup.sdk.xap.model.FareCalendarResponseOffersInnerSegmentsInner
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for list of air offers. An offer gives total trip details including flight and pricing information.
 * @param departureDate Flight departure date in ISO 8601 format.
 * @param offerPrice
 * @param segments Container of information about each flight offer Segments (the trip from one stopping place to another) are made up of Legs This will be given back in response if includeSegmentDetails=true
 */
@ConsistentCopyVisibility data class FareCalendarResponseOffersInner private constructor(
    /* Flight departure date in ISO 8601 format. */
    @JsonProperty("DepartureDate")
    val departureDate: java.time.OffsetDateTime,

    @JsonProperty("OfferPrice")
    val offerPrice: FareCalendarResponseOffersInnerOfferPrice,

    /* Container of information about each flight offer Segments (the trip from one stopping place to another) are made up of Legs This will be given back in response if includeSegmentDetails=true */
    @JsonProperty("Segments")
    val segments: kotlin.collections.List<FareCalendarResponseOffersInnerSegmentsInner>? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var departureDate: java.time.OffsetDateTime? = null,
        private var offerPrice: FareCalendarResponseOffersInnerOfferPrice? = null,
        private var segments: kotlin.collections.List<FareCalendarResponseOffersInnerSegmentsInner>? = null,
    ) {
        fun departureDate(departureDate: java.time.OffsetDateTime) = apply { this.departureDate = departureDate }

        fun offerPrice(offerPrice: FareCalendarResponseOffersInnerOfferPrice) = apply { this.offerPrice = offerPrice }

        fun segments(segments: kotlin.collections.List<FareCalendarResponseOffersInnerSegmentsInner>?) = apply { this.segments = segments }

        fun build(): FareCalendarResponseOffersInner {
            val departureDate = this.departureDate.getOrThrow {
                IllegalArgumentException("departureDate must not be null")
            }

            val offerPrice = this.offerPrice.getOrThrow {
                IllegalArgumentException("offerPrice must not be null")
            }

            val instance = FareCalendarResponseOffersInner(
                departureDate = departureDate,
                offerPrice = offerPrice,
                segments = segments,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        departureDate = departureDate,
        offerPrice = offerPrice,
        segments = segments,
    )
}
