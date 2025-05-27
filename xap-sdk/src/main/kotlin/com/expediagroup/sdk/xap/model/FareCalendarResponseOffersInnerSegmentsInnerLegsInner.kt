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
import com.expediagroup.sdk.xap.model.FareCalendarResponseOffersInnerSegmentsInnerLegsInnerDepartureAirport
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container information on each flight leg.
 * @param departureAirport
 * @param arrivalAirport
 * @param departureDateTime Flight departure date and time in ISO 8601 format
 * @param arrivalDateTime Flight landing date and time in ISO 8601 format
 * @param flightNumber Flight Number assigned by Carrier.
 * @param cabinClass Class of service for the air leg.
 * @param marketingAirlineCode The two-letter code of the Airline that is marketing the flight.
 * @param operatingAirlineCode The two-letter code of the Airline actually operating the plane.
 */
@ConsistentCopyVisibility data class FareCalendarResponseOffersInnerSegmentsInnerLegsInner private constructor(
    @JsonProperty("DepartureAirport")
    val departureAirport: FareCalendarResponseOffersInnerSegmentsInnerLegsInnerDepartureAirport,

    @JsonProperty("ArrivalAirport")
    val arrivalAirport: FareCalendarResponseOffersInnerSegmentsInnerLegsInnerDepartureAirport,

    /* Flight departure date and time in ISO 8601 format */
    @JsonProperty("DepartureDateTime")
    val departureDateTime: java.time.OffsetDateTime,

    /* Flight landing date and time in ISO 8601 format */
    @JsonProperty("ArrivalDateTime")
    val arrivalDateTime: java.time.OffsetDateTime,

    /* Flight Number assigned by Carrier. */
    @JsonProperty("FlightNumber")
    val flightNumber: kotlin.String,

    /* Class of service for the air leg. */
    @JsonProperty("CabinClass")
    val cabinClass: FareCalendarResponseOffersInnerSegmentsInnerLegsInner.CabinClass,

    /* The two-letter code of the Airline that is marketing the flight. */
    @JsonProperty("MarketingAirlineCode")
    val marketingAirlineCode: kotlin.String? = null,

    /* The two-letter code of the Airline actually operating the plane. */
    @JsonProperty("OperatingAirlineCode")
    val operatingAirlineCode: kotlin.String? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var departureAirport: FareCalendarResponseOffersInnerSegmentsInnerLegsInnerDepartureAirport? = null,
        private var arrivalAirport: FareCalendarResponseOffersInnerSegmentsInnerLegsInnerDepartureAirport? = null,
        private var departureDateTime: java.time.OffsetDateTime? = null,
        private var arrivalDateTime: java.time.OffsetDateTime? = null,
        private var flightNumber: kotlin.String? = null,
        private var cabinClass: FareCalendarResponseOffersInnerSegmentsInnerLegsInner.CabinClass? = null,
        private var marketingAirlineCode: kotlin.String? = null,
        private var operatingAirlineCode: kotlin.String? = null,
    ) {
        fun departureAirport(departureAirport: FareCalendarResponseOffersInnerSegmentsInnerLegsInnerDepartureAirport) = apply { this.departureAirport = departureAirport }

        fun arrivalAirport(arrivalAirport: FareCalendarResponseOffersInnerSegmentsInnerLegsInnerDepartureAirport) = apply { this.arrivalAirport = arrivalAirport }

        fun departureDateTime(departureDateTime: java.time.OffsetDateTime) = apply { this.departureDateTime = departureDateTime }

        fun arrivalDateTime(arrivalDateTime: java.time.OffsetDateTime) = apply { this.arrivalDateTime = arrivalDateTime }

        fun flightNumber(flightNumber: kotlin.String) = apply { this.flightNumber = flightNumber }

        fun cabinClass(cabinClass: FareCalendarResponseOffersInnerSegmentsInnerLegsInner.CabinClass) = apply { this.cabinClass = cabinClass }

        fun marketingAirlineCode(marketingAirlineCode: kotlin.String?) = apply { this.marketingAirlineCode = marketingAirlineCode }

        fun operatingAirlineCode(operatingAirlineCode: kotlin.String?) = apply { this.operatingAirlineCode = operatingAirlineCode }

        fun build(): FareCalendarResponseOffersInnerSegmentsInnerLegsInner {
            val departureAirport = this.departureAirport.getOrThrow {
                IllegalArgumentException("departureAirport must not be null")
            }

            val arrivalAirport = this.arrivalAirport.getOrThrow {
                IllegalArgumentException("arrivalAirport must not be null")
            }

            val departureDateTime = this.departureDateTime.getOrThrow {
                IllegalArgumentException("departureDateTime must not be null")
            }

            val arrivalDateTime = this.arrivalDateTime.getOrThrow {
                IllegalArgumentException("arrivalDateTime must not be null")
            }

            val flightNumber = this.flightNumber.getOrThrow {
                IllegalArgumentException("flightNumber must not be null")
            }

            val cabinClass = this.cabinClass.getOrThrow {
                IllegalArgumentException("cabinClass must not be null")
            }

            val instance = FareCalendarResponseOffersInnerSegmentsInnerLegsInner(
                departureAirport = departureAirport,
                arrivalAirport = arrivalAirport,
                departureDateTime = departureDateTime,
                arrivalDateTime = arrivalDateTime,
                flightNumber = flightNumber,
                cabinClass = cabinClass,
                marketingAirlineCode = marketingAirlineCode,
                operatingAirlineCode = operatingAirlineCode,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        departureAirport = departureAirport,
        arrivalAirport = arrivalAirport,
        departureDateTime = departureDateTime,
        arrivalDateTime = arrivalDateTime,
        flightNumber = flightNumber,
        cabinClass = cabinClass,
        marketingAirlineCode = marketingAirlineCode,
        operatingAirlineCode = operatingAirlineCode,
    )

    /**
     * Class of service for the air leg.
     * Values: ECONOMY,FIRST,BUSINESS,PREMIUM_ECONOMY
     */
    enum class CabinClass(val value: kotlin.String) {
        @JsonProperty("ECONOMY")
        ECONOMY("ECONOMY"),

        @JsonProperty("FIRST")
        FIRST("FIRST"),

        @JsonProperty("BUSINESS")
        BUSINESS("BUSINESS"),

        @JsonProperty("PREMIUM_ECONOMY")
        PREMIUM_ECONOMY("PREMIUM_ECONOMY"),
    }
}
