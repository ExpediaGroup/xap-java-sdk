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

import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLegDepartureAirport
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container information on each flight leg.
 * @param departureAirport
 * @param arrivalAirport
 * @param departureDateTime Flight departure date and time in ISO 8601 format
 * @param arrivalDateTime Flight landing date and time in ISO 8601 format
 * @param flightNumber Flight Number assigned by Carrier.
 * @param marketingAirlineCode The two-letter code of the Airline that is marketing the flight.
 * @param flightDuration The total travel duration for this leg, expressed in ISO 8601 standard.
 * @param cabinClass Class of service for the air leg.
 * @param operatingAirlineCode The two-letter code of the Airline actually operating the plane.
 */
data class FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLeg(
    @JsonProperty("DepartureAirport")
    @field:NotNull
    @field:Valid
    val departureAirport: FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLegDepartureAirport,
    @JsonProperty("ArrivalAirport")
    @field:NotNull
    @field:Valid
    val arrivalAirport: FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLegDepartureAirport,
    // Flight departure date and time in ISO 8601 format
    @JsonProperty("DepartureDateTime")
    val departureDateTime: java.time.OffsetDateTime,
    // Flight landing date and time in ISO 8601 format
    @JsonProperty("ArrivalDateTime")
    val arrivalDateTime: java.time.OffsetDateTime,
    // Flight Number assigned by Carrier.
    @JsonProperty("FlightNumber")
    @field:NotNull
    @field:Valid
    val flightNumber: kotlin.String,
    // The two-letter code of the Airline that is marketing the flight.
    @JsonProperty("MarketingAirlineCode")
    @field:NotNull
    @field:Valid
    val marketingAirlineCode: kotlin.String,
    // The total travel duration for this leg, expressed in ISO 8601 standard.
    @JsonProperty("FlightDuration")
    @field:NotNull
    @field:Valid
    val flightDuration: kotlin.String,
    // Class of service for the air leg.
    @JsonProperty("CabinClass")
    @field:NotNull
    val cabinClass: FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLeg.CabinClass,
    // The two-letter code of the Airline actually operating the plane.
    @JsonProperty("OperatingAirlineCode")
    @field:Valid
    val operatingAirlineCode: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var departureAirport: FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLegDepartureAirport? = null,
        private var arrivalAirport: FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLegDepartureAirport? = null,
        private var departureDateTime: java.time.OffsetDateTime? = null,
        private var arrivalDateTime: java.time.OffsetDateTime? = null,
        private var flightNumber: kotlin.String? = null,
        private var marketingAirlineCode: kotlin.String? = null,
        private var flightDuration: kotlin.String? = null,
        private var cabinClass: FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLeg.CabinClass? = null,
        private var operatingAirlineCode: kotlin.String? = null
    ) {
        fun departureAirport(departureAirport: FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLegDepartureAirport) = apply { this.departureAirport = departureAirport }

        fun arrivalAirport(arrivalAirport: FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLegDepartureAirport) = apply { this.arrivalAirport = arrivalAirport }

        fun departureDateTime(departureDateTime: java.time.OffsetDateTime) = apply { this.departureDateTime = departureDateTime }

        fun arrivalDateTime(arrivalDateTime: java.time.OffsetDateTime) = apply { this.arrivalDateTime = arrivalDateTime }

        fun flightNumber(flightNumber: kotlin.String) = apply { this.flightNumber = flightNumber }

        fun marketingAirlineCode(marketingAirlineCode: kotlin.String) = apply { this.marketingAirlineCode = marketingAirlineCode }

        fun flightDuration(flightDuration: kotlin.String) = apply { this.flightDuration = flightDuration }

        fun cabinClass(cabinClass: FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLeg.CabinClass) = apply { this.cabinClass = cabinClass }

        fun operatingAirlineCode(operatingAirlineCode: kotlin.String?) = apply { this.operatingAirlineCode = operatingAirlineCode }

        fun build(): FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLeg {
            val instance =
                FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLeg(
                    departureAirport = departureAirport!!,
                    arrivalAirport = arrivalAirport!!,
                    departureDateTime = departureDateTime!!,
                    arrivalDateTime = arrivalDateTime!!,
                    flightNumber = flightNumber!!,
                    marketingAirlineCode = marketingAirlineCode!!,
                    flightDuration = flightDuration!!,
                    cabinClass = cabinClass!!,
                    operatingAirlineCode = operatingAirlineCode
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLeg) {
            val validator =
                Validation
                    .byDefaultProvider()
                    .configure()
                    .messageInterpolator(ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .validator

            val violations = validator.validate(instance)

            if (violations.isNotEmpty()) {
                throw PropertyConstraintViolationException(
                    constraintViolations = violations.map { "${it.propertyPath}: ${it.message}" }
                )
            }
        }
    }

    fun toBuilder() =
        Builder(
            departureAirport = departureAirport!!,
            arrivalAirport = arrivalAirport!!,
            departureDateTime = departureDateTime!!,
            arrivalDateTime = arrivalDateTime!!,
            flightNumber = flightNumber!!,
            marketingAirlineCode = marketingAirlineCode!!,
            flightDuration = flightDuration!!,
            cabinClass = cabinClass!!,
            operatingAirlineCode = operatingAirlineCode
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
        PREMIUM_ECONOMY("PREMIUM_ECONOMY")
    }
}
