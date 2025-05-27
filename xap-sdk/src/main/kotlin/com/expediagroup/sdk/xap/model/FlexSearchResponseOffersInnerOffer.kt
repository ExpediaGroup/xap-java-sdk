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
import com.expediagroup.sdk.xap.model.FlexSearchResponseOffersInnerOfferOfferPrice
import com.expediagroup.sdk.xap.model.FlexSearchResponseOffersInnerOfferSegmentsInner
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for list of air offers. An offer gives trip details including flight and pricing information.
 * @param departureDates Container for the list of departure dates for the first Leg in each segment in chronological order.
 * @param offerPrice
 * @param segments Container of information about each flight offer Segments (the trip from one stopping place to another) are made up of Legs. This will be given back in response if includeSegmentDetails=true
 */
@ConsistentCopyVisibility data class FlexSearchResponseOffersInnerOffer private constructor(
    /* Container for the list of departure dates for the first Leg in each segment in chronological order. */
    @JsonProperty("DepartureDates")
    val departureDates: kotlin.collections
        .List<
            java.time.OffsetDateTime,
            >,

    @JsonProperty("OfferPrice")
    val offerPrice: FlexSearchResponseOffersInnerOfferOfferPrice,

    /* Container of information about each flight offer Segments (the trip from one stopping place to another) are made up of Legs. This will be given back in response if includeSegmentDetails=true */
    @JsonProperty("Segments")
    val segments: kotlin.collections.List<FlexSearchResponseOffersInnerOfferSegmentsInner>? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var departureDates: kotlin.collections.List<java.time.OffsetDateTime>? = null,
        private var offerPrice: FlexSearchResponseOffersInnerOfferOfferPrice? = null,
        private var segments: kotlin.collections.List<FlexSearchResponseOffersInnerOfferSegmentsInner>? = null,
    ) {
        fun departureDates(departureDates: kotlin.collections.List<java.time.OffsetDateTime>) = apply { this.departureDates = departureDates }

        fun offerPrice(offerPrice: FlexSearchResponseOffersInnerOfferOfferPrice) = apply { this.offerPrice = offerPrice }

        fun segments(segments: kotlin.collections.List<FlexSearchResponseOffersInnerOfferSegmentsInner>?) = apply { this.segments = segments }

        fun build(): FlexSearchResponseOffersInnerOffer {
            val departureDates = this.departureDates.getOrThrow {
                IllegalArgumentException("departureDates must not be null")
            }

            val offerPrice = this.offerPrice.getOrThrow {
                IllegalArgumentException("offerPrice must not be null")
            }

            val instance = FlexSearchResponseOffersInnerOffer(
                departureDates = departureDates,
                offerPrice = offerPrice,
                segments = segments,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        departureDates = departureDates,
        offerPrice = offerPrice,
        segments = segments,
    )
}
