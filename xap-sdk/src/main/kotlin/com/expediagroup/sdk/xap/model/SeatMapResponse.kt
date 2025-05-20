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

import com.expediagroup.sdk.xap.model.FlightsV2Airport
import com.expediagroup.sdk.xap.model.FlightsV2Warning
import com.expediagroup.sdk.xap.model.SeatMap
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param flightNumber Specifies the requested flight number.
 * @param departureDate Specifies the departure date of the requested flight
 * @param marketingAirLineCode The IATA or Expedia assigned airline codes of the carrier. IATA codes must contain at least one alphabetic character.
 * @param equipmentCode IATA Equipment type codes.
 * @param departureAirport
 * @param arrivalAirport
 * @param seatMap
 * @param transactionId Unique identifier for the transaction.
 * @param marketingAirLineName The airline name of the marketing airline
 * @param equipmentName Equipment type name.
 * @param warnings Container for Warning messages.
 */
data class SeatMapResponse(
    // Specifies the requested flight number.
    @JsonProperty("FlightNumber")
    val flightNumber: kotlin.String,
    // Specifies the departure date of the requested flight
    @JsonProperty("DepartureDate")
    val departureDate: java.time.LocalDate,
    // The IATA or Expedia assigned airline codes of the carrier. IATA codes must contain at least one alphabetic character.
    @JsonProperty("MarketingAirLineCode")
    val marketingAirLineCode: kotlin.String,
    // IATA Equipment type codes.
    @JsonProperty("EquipmentCode")
    val equipmentCode: kotlin.String,
    @JsonProperty("DepartureAirport")
    val departureAirport: FlightsV2Airport,
    @JsonProperty("ArrivalAirport")
    val arrivalAirport: FlightsV2Airport,
    @JsonProperty("SeatMap")
    val seatMap: SeatMap,
    // Unique identifier for the transaction.
    @JsonProperty("TransactionId")
    val transactionId: kotlin.String,
    // The airline name of the marketing airline
    @JsonProperty("MarketingAirLineName")
    val marketingAirLineName: kotlin.String? = null,
    // Equipment type name.
    @JsonProperty("EquipmentName")
    val equipmentName: kotlin.String? = null,
    // Container for Warning messages.
    @JsonProperty("Warnings")
    val warnings: kotlin.collections.List<FlightsV2Warning>? = null,
) {
    init {
        require(flightNumber != null) { "flightNumber must not be null" }

        require(departureDate != null) { "departureDate must not be null" }

        require(marketingAirLineCode != null) { "marketingAirLineCode must not be null" }

        require(equipmentCode != null) { "equipmentCode must not be null" }

        require(departureAirport != null) { "departureAirport must not be null" }

        require(arrivalAirport != null) { "arrivalAirport must not be null" }

        require(seatMap != null) { "seatMap must not be null" }

        require(transactionId != null) { "transactionId must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var flightNumber: kotlin.String? = null,
        private var departureDate: java.time.LocalDate? = null,
        private var marketingAirLineCode: kotlin.String? = null,
        private var equipmentCode: kotlin.String? = null,
        private var departureAirport: FlightsV2Airport? = null,
        private var arrivalAirport: FlightsV2Airport? = null,
        private var seatMap: SeatMap? = null,
        private var transactionId: kotlin.String? = null,
        private var marketingAirLineName: kotlin.String? = null,
        private var equipmentName: kotlin.String? = null,
        private var warnings: kotlin.collections.List<FlightsV2Warning>? = null,
    ) {
        fun flightNumber(flightNumber: kotlin.String) = apply { this.flightNumber = flightNumber }

        fun departureDate(departureDate: java.time.LocalDate) = apply { this.departureDate = departureDate }

        fun marketingAirLineCode(marketingAirLineCode: kotlin.String) = apply { this.marketingAirLineCode = marketingAirLineCode }

        fun equipmentCode(equipmentCode: kotlin.String) = apply { this.equipmentCode = equipmentCode }

        fun departureAirport(departureAirport: FlightsV2Airport) = apply { this.departureAirport = departureAirport }

        fun arrivalAirport(arrivalAirport: FlightsV2Airport) = apply { this.arrivalAirport = arrivalAirport }

        fun seatMap(seatMap: SeatMap) = apply { this.seatMap = seatMap }

        fun transactionId(transactionId: kotlin.String) = apply { this.transactionId = transactionId }

        fun marketingAirLineName(marketingAirLineName: kotlin.String?) = apply { this.marketingAirLineName = marketingAirLineName }

        fun equipmentName(equipmentName: kotlin.String?) = apply { this.equipmentName = equipmentName }

        fun warnings(warnings: kotlin.collections.List<FlightsV2Warning>?) = apply { this.warnings = warnings }

        fun build(): SeatMapResponse {
            val instance =
                SeatMapResponse(
                    flightNumber = flightNumber!!,
                    departureDate = departureDate!!,
                    marketingAirLineCode = marketingAirLineCode!!,
                    equipmentCode = equipmentCode!!,
                    departureAirport = departureAirport!!,
                    arrivalAirport = arrivalAirport!!,
                    seatMap = seatMap!!,
                    transactionId = transactionId!!,
                    marketingAirLineName = marketingAirLineName,
                    equipmentName = equipmentName,
                    warnings = warnings,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            flightNumber = flightNumber!!,
            departureDate = departureDate!!,
            marketingAirLineCode = marketingAirLineCode!!,
            equipmentCode = equipmentCode!!,
            departureAirport = departureAirport!!,
            arrivalAirport = arrivalAirport!!,
            seatMap = seatMap!!,
            transactionId = transactionId!!,
            marketingAirLineName = marketingAirLineName,
            equipmentName = equipmentName,
            warnings = warnings,
        )
}
