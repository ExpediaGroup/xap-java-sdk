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
import com.expediagroup.sdk.xap.models.FlightSegmentsInnerSegmentLegsInnerArrivalAirport
import com.expediagroup.sdk.xap.models.FlightSegmentsInnerSegmentLegsInnerDepartureAirport
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 *
 * @param departureAirport
 * @param arrivalAirport
 * @param departureDateTime
 * @param arrivalDateTime
 * @param flightNumber
 * @param marketingAirlineCode
 * @param bookingCode
 * @param cabinClass
 */
data class FlightSegmentsInnerSegmentLegsInner(
    @JsonProperty("DepartureAirport")
    @field:NotNull
    @field:Valid
    val departureAirport: FlightSegmentsInnerSegmentLegsInnerDepartureAirport,
    @JsonProperty("ArrivalAirport")
    @field:NotNull
    @field:Valid
    val arrivalAirport: FlightSegmentsInnerSegmentLegsInnerArrivalAirport,
    @JsonProperty("DepartureDateTime")
    val departureDateTime: java.time.OffsetDateTime,
    @JsonProperty("ArrivalDateTime")
    val arrivalDateTime: java.time.OffsetDateTime,
    @JsonProperty("FlightNumber")
    @field:NotNull
    @field:Valid
    val flightNumber: kotlin.String,
    @JsonProperty("MarketingAirlineCode")
    @field:NotNull
    @field:Valid
    val marketingAirlineCode: kotlin.String,
    @JsonProperty("BookingCode")
    @field:NotNull
    @field:Valid
    val bookingCode: kotlin.String,
    @JsonProperty("CabinClass")
    @field:NotNull
    val cabinClass: FlightSegmentsInnerSegmentLegsInner.CabinClass
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var departureAirport: FlightSegmentsInnerSegmentLegsInnerDepartureAirport? = null,
        private var arrivalAirport: FlightSegmentsInnerSegmentLegsInnerArrivalAirport? = null,
        private var departureDateTime: java.time.OffsetDateTime? = null,
        private var arrivalDateTime: java.time.OffsetDateTime? = null,
        private var flightNumber: kotlin.String? = null,
        private var marketingAirlineCode: kotlin.String? = null,
        private var bookingCode: kotlin.String? = null,
        private var cabinClass: FlightSegmentsInnerSegmentLegsInner.CabinClass? = null
    ) {
        fun departureAirport(departureAirport: FlightSegmentsInnerSegmentLegsInnerDepartureAirport) = apply { this.departureAirport = departureAirport }

        fun arrivalAirport(arrivalAirport: FlightSegmentsInnerSegmentLegsInnerArrivalAirport) = apply { this.arrivalAirport = arrivalAirport }

        fun departureDateTime(departureDateTime: java.time.OffsetDateTime) = apply { this.departureDateTime = departureDateTime }

        fun arrivalDateTime(arrivalDateTime: java.time.OffsetDateTime) = apply { this.arrivalDateTime = arrivalDateTime }

        fun flightNumber(flightNumber: kotlin.String) = apply { this.flightNumber = flightNumber }

        fun marketingAirlineCode(marketingAirlineCode: kotlin.String) = apply { this.marketingAirlineCode = marketingAirlineCode }

        fun bookingCode(bookingCode: kotlin.String) = apply { this.bookingCode = bookingCode }

        fun cabinClass(cabinClass: FlightSegmentsInnerSegmentLegsInner.CabinClass) = apply { this.cabinClass = cabinClass }

        fun build(): FlightSegmentsInnerSegmentLegsInner {
            val instance =
                FlightSegmentsInnerSegmentLegsInner(
                    departureAirport = departureAirport!!,
                    arrivalAirport = arrivalAirport!!,
                    departureDateTime = departureDateTime!!,
                    arrivalDateTime = arrivalDateTime!!,
                    flightNumber = flightNumber!!,
                    marketingAirlineCode = marketingAirlineCode!!,
                    bookingCode = bookingCode!!,
                    cabinClass = cabinClass!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightSegmentsInnerSegmentLegsInner) {
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
            bookingCode = bookingCode!!,
            cabinClass = cabinClass!!
        )

    /**
     *
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
