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

import com.expediagroup.sdk.xap.model.FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInner
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container of information about each flight offer Segments (the trip from one stopping place to another) are made up of Legs. This will be given back in response if includeSegmentDetails=true
 * @param departureArrivalDayDifference This is an indicator (can have values of -1, 0, 1, 2, etc.) which depends on the relative difference between the arrival and departure dates.
 * @param legs Container information on each flight leg.
 * @param airportChange True if there the Segment is having a different Departure airport compared to the Arrival airport of previous Segment.
 */
data class FlexSearchResponseOffersInnerOfferSegmentsInnerSegment(
    // This is an indicator (can have values of -1, 0, 1, 2, etc.) which depends on the relative difference between the arrival and departure dates.
    @JsonProperty("DepartureArrivalDayDifference")
    val departureArrivalDayDifference: kotlin.Int,
    // Container information on each flight leg.
    @JsonProperty("Legs")
    val legs: kotlin.collections
        .List<
            FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInner,
        >,
    // True if there the Segment is having a different Departure airport compared to the Arrival airport of previous Segment.
    @JsonProperty("AirportChange")
    val airportChange: kotlin.Boolean? = null,
) {
    init {
        require(departureArrivalDayDifference != null) { "departureArrivalDayDifference must not be null" }

        require(legs != null) { "legs must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var departureArrivalDayDifference: kotlin.Int? = null,
        private var legs: kotlin.collections.List<FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInner>? = null,
        private var airportChange: kotlin.Boolean? = null,
    ) {
        fun departureArrivalDayDifference(departureArrivalDayDifference: kotlin.Int) = apply { this.departureArrivalDayDifference = departureArrivalDayDifference }

        fun legs(legs: kotlin.collections.List<FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInner>) = apply { this.legs = legs }

        fun airportChange(airportChange: kotlin.Boolean?) = apply { this.airportChange = airportChange }

        fun build(): FlexSearchResponseOffersInnerOfferSegmentsInnerSegment {
            val instance =
                FlexSearchResponseOffersInnerOfferSegmentsInnerSegment(
                    departureArrivalDayDifference = departureArrivalDayDifference!!,
                    legs = legs!!,
                    airportChange = airportChange,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            departureArrivalDayDifference = departureArrivalDayDifference!!,
            legs = legs!!,
            airportChange = airportChange,
        )
}
