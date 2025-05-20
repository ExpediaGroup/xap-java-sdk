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

import com.expediagroup.sdk.xap.model.FlightsV3BaggageFee
import com.expediagroup.sdk.xap.model.FlightsV3Link
import com.expediagroup.sdk.xap.model.Leg
import com.expediagroup.sdk.xap.model.OfferPrice
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container of information about each flight offer (Less information shown if the offer is and opaque flight offer) Flights (the complete journey to your final destination by air) are made up of:  Segments (the trip from one stopping place to another) are made up of:  Legs (take off at one airport and land at another)
 * @param segmentId Unique identifier for a single flight segment. Shown in case of opaque flight offer as well. Can be alphanumeric or numeric.
 * @param departureArrivalDayDifference This is an indicator (can have values of -1, 0, 1, 2, etc.) which depends on the relative difference between the arrival and departure dates.
 * @param links Container for deeplink URL information.
 * @param price
 * @param includesNonFlightLeg True if any Leg associated with this Segment is via a non-flight type of transit (Bus, Train or Boat)
 * @param legs Container information on each flight leg.
 * @param baggageFees Container for baggage fee information of each bag type. The baggage fee can vary for each bag type. The amount can be zero, fixed amount or will be in a range.
 * @param accountCode Lists any Account Code that has been applied to this segment.
 * @param basicEconomy True if the corresponding fare ticket type associated with the segment is Basic Economy
 * @param seatsLeft Remaining number of seats available for this segment offer.
 * @param fareType Type of fare.
 * @param airportChange True if there the Segment is having a different Departure airport compared to the Arrival airport of previous Segment.
 * @param totalStops The total number of stops in this segment(Nullable in case of Opaque Flights)
 * @param flightDuration Total duration of the flight segment (Nullable in case of Opaque Flights)
 * @param opaqueDepartureTime Contains the name of the Fare tagged against the specific fare options.
 * @param opaqueNumberOfStops Departure time range will be shown only for opaque flight offers.
 */
data class Segment(
    // Unique identifier for a single flight segment. Shown in case of opaque flight offer as well. Can be alphanumeric or numeric.
    @JsonProperty("SegmentId")
    val segmentId: kotlin.String,
    // This is an indicator (can have values of -1, 0, 1, 2, etc.) which depends on the relative difference between the arrival and departure dates.
    @JsonProperty("DepartureArrivalDayDifference")
    val departureArrivalDayDifference: kotlin.Int,
    // Container for deeplink URL information.
    @JsonProperty("Links")
    val links: kotlin.collections.Map<kotlin.String, FlightsV3Link>? = null,
    @JsonProperty("Price")
    val price: OfferPrice? = null,
    // True if any Leg associated with this Segment is via a non-flight type of transit (Bus, Train or Boat)
    @JsonProperty("IncludesNonFlightLeg")
    val includesNonFlightLeg: kotlin.Boolean? = null,
    // Container information on each flight leg.
    @JsonProperty("Legs")
    val legs: kotlin.collections.List<Leg>? = null,
    // Container for baggage fee information of each bag type. The baggage fee can vary for each bag type. The amount can be zero, fixed amount or will be in a range.
    @JsonProperty("BaggageFees")
    val baggageFees: kotlin.collections.List<FlightsV3BaggageFee>? = null,
    // Lists any Account Code that has been applied to this segment.
    @JsonProperty("AccountCode")
    val accountCode: kotlin.String? = null,
    // True if the corresponding fare ticket type associated with the segment is Basic Economy
    @JsonProperty("BasicEconomy")
    val basicEconomy: kotlin.Boolean? = null,
    // Remaining number of seats available for this segment offer.
    @JsonProperty("SeatsLeft")
    val seatsLeft: kotlin.Int? = null,
    // Type of fare.
    @JsonProperty("FareType")
    val fareType: Segment.FareType? = null,
    // True if there the Segment is having a different Departure airport compared to the Arrival airport of previous Segment.
    @JsonProperty("AirportChange")
    val airportChange: kotlin.Boolean? = null,
    // The total number of stops in this segment(Nullable in case of Opaque Flights)
    @JsonProperty("TotalStops")
    val totalStops: kotlin.Int? = null,
    // Total duration of the flight segment (Nullable in case of Opaque Flights)
    @JsonProperty("FlightDuration")
    val flightDuration: kotlin.String? = null,
    // Contains the name of the Fare tagged against the specific fare options.
    @JsonProperty("OpaqueDepartureTime")
    val opaqueDepartureTime: kotlin.String? = null,
    // Departure time range will be shown only for opaque flight offers.
    @JsonProperty("OpaqueNumberOfStops")
    val opaqueNumberOfStops: kotlin.String? = null,
) {
    init {
        require(segmentId != null) { "segmentId must not be null" }

        require(departureArrivalDayDifference != null) { "departureArrivalDayDifference must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var segmentId: kotlin.String? = null,
        private var departureArrivalDayDifference: kotlin.Int? = null,
        private var links: kotlin.collections.Map<kotlin.String, FlightsV3Link>? = null,
        private var price: OfferPrice? = null,
        private var includesNonFlightLeg: kotlin.Boolean? = null,
        private var legs: kotlin.collections.List<Leg>? = null,
        private var baggageFees: kotlin.collections.List<FlightsV3BaggageFee>? = null,
        private var accountCode: kotlin.String? = null,
        private var basicEconomy: kotlin.Boolean? = null,
        private var seatsLeft: kotlin.Int? = null,
        private var fareType: Segment.FareType? = null,
        private var airportChange: kotlin.Boolean? = null,
        private var totalStops: kotlin.Int? = null,
        private var flightDuration: kotlin.String? = null,
        private var opaqueDepartureTime: kotlin.String? = null,
        private var opaqueNumberOfStops: kotlin.String? = null,
    ) {
        fun segmentId(segmentId: kotlin.String) = apply { this.segmentId = segmentId }

        fun departureArrivalDayDifference(departureArrivalDayDifference: kotlin.Int) = apply { this.departureArrivalDayDifference = departureArrivalDayDifference }

        fun links(links: kotlin.collections.Map<kotlin.String, FlightsV3Link>?) = apply { this.links = links }

        fun price(price: OfferPrice?) = apply { this.price = price }

        fun includesNonFlightLeg(includesNonFlightLeg: kotlin.Boolean?) = apply { this.includesNonFlightLeg = includesNonFlightLeg }

        fun legs(legs: kotlin.collections.List<Leg>?) = apply { this.legs = legs }

        fun baggageFees(baggageFees: kotlin.collections.List<FlightsV3BaggageFee>?) = apply { this.baggageFees = baggageFees }

        fun accountCode(accountCode: kotlin.String?) = apply { this.accountCode = accountCode }

        fun basicEconomy(basicEconomy: kotlin.Boolean?) = apply { this.basicEconomy = basicEconomy }

        fun seatsLeft(seatsLeft: kotlin.Int?) = apply { this.seatsLeft = seatsLeft }

        fun fareType(fareType: Segment.FareType?) = apply { this.fareType = fareType }

        fun airportChange(airportChange: kotlin.Boolean?) = apply { this.airportChange = airportChange }

        fun totalStops(totalStops: kotlin.Int?) = apply { this.totalStops = totalStops }

        fun flightDuration(flightDuration: kotlin.String?) = apply { this.flightDuration = flightDuration }

        fun opaqueDepartureTime(opaqueDepartureTime: kotlin.String?) = apply { this.opaqueDepartureTime = opaqueDepartureTime }

        fun opaqueNumberOfStops(opaqueNumberOfStops: kotlin.String?) = apply { this.opaqueNumberOfStops = opaqueNumberOfStops }

        fun build(): Segment {
            val instance =
                Segment(
                    segmentId = segmentId!!,
                    departureArrivalDayDifference = departureArrivalDayDifference!!,
                    links = links,
                    price = price,
                    includesNonFlightLeg = includesNonFlightLeg,
                    legs = legs,
                    baggageFees = baggageFees,
                    accountCode = accountCode,
                    basicEconomy = basicEconomy,
                    seatsLeft = seatsLeft,
                    fareType = fareType,
                    airportChange = airportChange,
                    totalStops = totalStops,
                    flightDuration = flightDuration,
                    opaqueDepartureTime = opaqueDepartureTime,
                    opaqueNumberOfStops = opaqueNumberOfStops,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            segmentId = segmentId!!,
            departureArrivalDayDifference = departureArrivalDayDifference!!,
            links = links,
            price = price,
            includesNonFlightLeg = includesNonFlightLeg,
            legs = legs,
            baggageFees = baggageFees,
            accountCode = accountCode,
            basicEconomy = basicEconomy,
            seatsLeft = seatsLeft,
            fareType = fareType,
            airportChange = airportChange,
            totalStops = totalStops,
            flightDuration = flightDuration,
            opaqueDepartureTime = opaqueDepartureTime,
            opaqueNumberOfStops = opaqueNumberOfStops,
        )

    /**
     * Type of fare.
     * Values: NET,PUBLISHED,WEB
     */
    enum class FareType(
        val value: kotlin.String,
    ) {
        @JsonProperty("NET")
        NET("NET"),

        @JsonProperty("PUBLISHED")
        PUBLISHED("PUBLISHED"),

        @JsonProperty("WEB")
        WEB("WEB"),
    }
}
