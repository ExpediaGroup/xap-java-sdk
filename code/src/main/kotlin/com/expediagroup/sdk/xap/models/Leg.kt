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
import com.expediagroup.sdk.xap.models.Amenities
import com.expediagroup.sdk.xap.models.FlightsV3Airport
import com.expediagroup.sdk.xap.models.FlightsV3Distance
import com.expediagroup.sdk.xap.models.FlightsV3Link
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
 * @param seatMapAvailable If True seat map is available
 * @param bookingCode Carrier-specific code used for booking (class of service).
 * @param cabinClass Class of service for the air leg.
 * @param statusCode Air segment status.
 * @param links Container for deeplink URL information.
 * @param marketingAirlineName The display name of the Airline that is marketing the flight.
 * @param operatingAirlineCode The two-letter code of the Airline actually operating the plane.
 * @param operatingAirlineName The display name of the airline actually operating the plane.
 * @param equipmentCode IATA Equipment type codes.
 * @param flightOnTimePercentage Percentage of time that this flight on time.
 * @param equipmentName The name of equipment that is scheduled for the flight.
 * @param connectionTime Connection time between current leg flight landed time to next flight departure time, expressed in ISO 8601 standard.
 * @param operationalDisclosure OperationalDisclosure.
 * @param flightDistance
 * @param equipmentChange True if the upcoming travel Leg will have different equipment (airplane) compared to the previous Leg.
 * @param loungeKeys List of keys referring to lounge details for the particular leg.
 * @param fareBasisCode Fare Basis Code for the corresponding Leg of Flight Offer.
 * @param mealOptions Different meal options available in this particular Leg.
 * @param amenities
 */
data class Leg(
    @JsonProperty("DepartureAirport")
    @field:NotNull
    @field:Valid
    val departureAirport: FlightsV3Airport,
    @JsonProperty("ArrivalAirport")
    @field:NotNull
    @field:Valid
    val arrivalAirport: FlightsV3Airport,
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
    // If True seat map is available
    @JsonProperty("SeatMapAvailable")
    @field:NotNull
    @field:Valid
    val seatMapAvailable: kotlin.Boolean,
    // Carrier-specific code used for booking (class of service).
    @JsonProperty("BookingCode")
    @field:NotNull
    @field:Valid
    val bookingCode: kotlin.String,
    // Class of service for the air leg.
    @JsonProperty("CabinClass")
    @field:NotNull
    val cabinClass: Leg.CabinClass,
    // Air segment status.
    @JsonProperty("StatusCode")
    @field:Valid
    val statusCode: kotlin.String? = null,
    // Container for deeplink URL information.
    @JsonProperty("Links")
    @field:Valid
    val links: kotlin.collections.Map<kotlin.String, FlightsV3Link>? = null,
    // The display name of the Airline that is marketing the flight.
    @JsonProperty("MarketingAirlineName")
    @field:Valid
    val marketingAirlineName: kotlin.String? = null,
    // The two-letter code of the Airline actually operating the plane.
    @JsonProperty("OperatingAirlineCode")
    @field:Valid
    val operatingAirlineCode: kotlin.String? = null,
    // The display name of the airline actually operating the plane.
    @JsonProperty("OperatingAirlineName")
    @field:Valid
    val operatingAirlineName: kotlin.String? = null,
    // IATA Equipment type codes.
    @JsonProperty("EquipmentCode")
    @field:Valid
    val equipmentCode: kotlin.String? = null,
    // Percentage of time that this flight on time.
    @JsonProperty("FlightOnTimePercentage")
    @field:Valid
    val flightOnTimePercentage: kotlin.String? = null,
    // The name of equipment that is scheduled for the flight.
    @JsonProperty("EquipmentName")
    @field:Valid
    val equipmentName: kotlin.String? = null,
    // Connection time between current leg flight landed time to next flight departure time, expressed in ISO 8601 standard.
    @JsonProperty("ConnectionTime")
    @field:Valid
    val connectionTime: kotlin.String? = null,
    // OperationalDisclosure.
    @JsonProperty("OperationalDisclosure")
    @field:Valid
    val operationalDisclosure: kotlin.String? = null,
    @JsonProperty("FlightDistance")
    @field:Valid
    val flightDistance: FlightsV3Distance? = null,
    // True if the upcoming travel Leg will have different equipment (airplane) compared to the previous Leg.
    @JsonProperty("EquipmentChange")
    @field:Valid
    val equipmentChange: kotlin.Boolean? = null,
    // List of keys referring to lounge details for the particular leg.
    @JsonProperty("LoungeKeys")
    @field:Valid
    val loungeKeys: kotlin.collections.List<kotlin.String>? = null,
    // Fare Basis Code for the corresponding Leg of Flight Offer.
    @JsonProperty("FareBasisCode")
    @field:Valid
    val fareBasisCode: kotlin.String? = null,
    // Different meal options available in this particular Leg.
    @JsonProperty("MealOptions")
    @field:Valid
    val mealOptions: kotlin.collections.List<kotlin.String>? = null,
    @JsonProperty("Amenities")
    @field:Valid
    val amenities: Amenities? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var departureAirport: FlightsV3Airport? = null,
        private var arrivalAirport: FlightsV3Airport? = null,
        private var departureDateTime: java.time.OffsetDateTime? = null,
        private var arrivalDateTime: java.time.OffsetDateTime? = null,
        private var flightNumber: kotlin.String? = null,
        private var marketingAirlineCode: kotlin.String? = null,
        private var flightDuration: kotlin.String? = null,
        private var seatMapAvailable: kotlin.Boolean? = null,
        private var bookingCode: kotlin.String? = null,
        private var cabinClass: Leg.CabinClass? = null,
        private var statusCode: kotlin.String? = null,
        private var links: kotlin.collections.Map<kotlin.String, FlightsV3Link>? = null,
        private var marketingAirlineName: kotlin.String? = null,
        private var operatingAirlineCode: kotlin.String? = null,
        private var operatingAirlineName: kotlin.String? = null,
        private var equipmentCode: kotlin.String? = null,
        private var flightOnTimePercentage: kotlin.String? = null,
        private var equipmentName: kotlin.String? = null,
        private var connectionTime: kotlin.String? = null,
        private var operationalDisclosure: kotlin.String? = null,
        private var flightDistance: FlightsV3Distance? = null,
        private var equipmentChange: kotlin.Boolean? = null,
        private var loungeKeys: kotlin.collections.List<kotlin.String>? = null,
        private var fareBasisCode: kotlin.String? = null,
        private var mealOptions: kotlin.collections.List<kotlin.String>? = null,
        private var amenities: Amenities? = null
    ) {
        fun departureAirport(departureAirport: FlightsV3Airport) = apply { this.departureAirport = departureAirport }

        fun arrivalAirport(arrivalAirport: FlightsV3Airport) = apply { this.arrivalAirport = arrivalAirport }

        fun departureDateTime(departureDateTime: java.time.OffsetDateTime) = apply { this.departureDateTime = departureDateTime }

        fun arrivalDateTime(arrivalDateTime: java.time.OffsetDateTime) = apply { this.arrivalDateTime = arrivalDateTime }

        fun flightNumber(flightNumber: kotlin.String) = apply { this.flightNumber = flightNumber }

        fun marketingAirlineCode(marketingAirlineCode: kotlin.String) = apply { this.marketingAirlineCode = marketingAirlineCode }

        fun flightDuration(flightDuration: kotlin.String) = apply { this.flightDuration = flightDuration }

        fun seatMapAvailable(seatMapAvailable: kotlin.Boolean) = apply { this.seatMapAvailable = seatMapAvailable }

        fun bookingCode(bookingCode: kotlin.String) = apply { this.bookingCode = bookingCode }

        fun cabinClass(cabinClass: Leg.CabinClass) = apply { this.cabinClass = cabinClass }

        fun statusCode(statusCode: kotlin.String?) = apply { this.statusCode = statusCode }

        fun links(links: kotlin.collections.Map<kotlin.String, FlightsV3Link>?) = apply { this.links = links }

        fun marketingAirlineName(marketingAirlineName: kotlin.String?) = apply { this.marketingAirlineName = marketingAirlineName }

        fun operatingAirlineCode(operatingAirlineCode: kotlin.String?) = apply { this.operatingAirlineCode = operatingAirlineCode }

        fun operatingAirlineName(operatingAirlineName: kotlin.String?) = apply { this.operatingAirlineName = operatingAirlineName }

        fun equipmentCode(equipmentCode: kotlin.String?) = apply { this.equipmentCode = equipmentCode }

        fun flightOnTimePercentage(flightOnTimePercentage: kotlin.String?) = apply { this.flightOnTimePercentage = flightOnTimePercentage }

        fun equipmentName(equipmentName: kotlin.String?) = apply { this.equipmentName = equipmentName }

        fun connectionTime(connectionTime: kotlin.String?) = apply { this.connectionTime = connectionTime }

        fun operationalDisclosure(operationalDisclosure: kotlin.String?) = apply { this.operationalDisclosure = operationalDisclosure }

        fun flightDistance(flightDistance: FlightsV3Distance?) = apply { this.flightDistance = flightDistance }

        fun equipmentChange(equipmentChange: kotlin.Boolean?) = apply { this.equipmentChange = equipmentChange }

        fun loungeKeys(loungeKeys: kotlin.collections.List<kotlin.String>?) = apply { this.loungeKeys = loungeKeys }

        fun fareBasisCode(fareBasisCode: kotlin.String?) = apply { this.fareBasisCode = fareBasisCode }

        fun mealOptions(mealOptions: kotlin.collections.List<kotlin.String>?) = apply { this.mealOptions = mealOptions }

        fun amenities(amenities: Amenities?) = apply { this.amenities = amenities }

        fun build(): Leg {
            val instance =
                Leg(
                    departureAirport = departureAirport!!,
                    arrivalAirport = arrivalAirport!!,
                    departureDateTime = departureDateTime!!,
                    arrivalDateTime = arrivalDateTime!!,
                    flightNumber = flightNumber!!,
                    marketingAirlineCode = marketingAirlineCode!!,
                    flightDuration = flightDuration!!,
                    seatMapAvailable = seatMapAvailable!!,
                    bookingCode = bookingCode!!,
                    cabinClass = cabinClass!!,
                    statusCode = statusCode,
                    links = links,
                    marketingAirlineName = marketingAirlineName,
                    operatingAirlineCode = operatingAirlineCode,
                    operatingAirlineName = operatingAirlineName,
                    equipmentCode = equipmentCode,
                    flightOnTimePercentage = flightOnTimePercentage,
                    equipmentName = equipmentName,
                    connectionTime = connectionTime,
                    operationalDisclosure = operationalDisclosure,
                    flightDistance = flightDistance,
                    equipmentChange = equipmentChange,
                    loungeKeys = loungeKeys,
                    fareBasisCode = fareBasisCode,
                    mealOptions = mealOptions,
                    amenities = amenities
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Leg) {
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
            seatMapAvailable = seatMapAvailable!!,
            bookingCode = bookingCode!!,
            cabinClass = cabinClass!!,
            statusCode = statusCode,
            links = links,
            marketingAirlineName = marketingAirlineName,
            operatingAirlineCode = operatingAirlineCode,
            operatingAirlineName = operatingAirlineName,
            equipmentCode = equipmentCode,
            flightOnTimePercentage = flightOnTimePercentage,
            equipmentName = equipmentName,
            connectionTime = connectionTime,
            operationalDisclosure = operationalDisclosure,
            flightDistance = flightDistance,
            equipmentChange = equipmentChange,
            loungeKeys = loungeKeys,
            fareBasisCode = fareBasisCode,
            mealOptions = mealOptions,
            amenities = amenities
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
