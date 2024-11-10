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
/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.expediagroup.sdk.xap.models

import com.expediagroup.sdk.xap.models.Hotel
import com.expediagroup.sdk.xap.models.HotelDetailsResponseOccupantsInner
import com.expediagroup.sdk.xap.models.HotelDetailsResponseStayDates
import com.expediagroup.sdk.xap.models.HotelDetailsResponseWarningsInner
import com.expediagroup.sdk.xap.models.ValidFormsOfPayment
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 *
 * @param warnings There were some errors or events during the transaction, but the API has still returned a response.  Container for all warnings.
 * @param transactionId Unique identifier for the transaction.
 * @param stayDates
 * @param lengthOfStay The number of stay nights.
 * @param numberOfRooms Number of rooms requested.
 * @param occupants Container for the list of rooms requested by the traveler.  Occupancy for each room is specified in this node.
 * @param validFormsOfPayment Container for payment information.
 * @param hotelDetails
 */
data class HotelDetailsResponse(
    // There were some errors or events during the transaction, but the API has still returned a response.  Container for all warnings.
    @JsonProperty("Warnings")
    @field:Valid
    val warnings: kotlin.collections.List<HotelDetailsResponseWarningsInner>? = null,
    // Unique identifier for the transaction.
    @JsonProperty("TransactionId")
    @field:Valid
    val transactionId: kotlin.String? = null,
    @JsonProperty("StayDates")
    @field:Valid
    val stayDates: HotelDetailsResponseStayDates? = null,
    // The number of stay nights.
    @JsonProperty("LengthOfStay")
    val lengthOfStay: kotlin.Int? = null,
    // Number of rooms requested.
    @JsonProperty("NumberOfRooms")
    val numberOfRooms: kotlin.Int? = null,
    // Container for the list of rooms requested by the traveler.  Occupancy for each room is specified in this node.
    @JsonProperty("Occupants")
    @field:Valid
    val occupants: kotlin.collections.List<HotelDetailsResponseOccupantsInner>? = null,
    // Container for payment information.
    @JsonProperty("ValidFormsOfPayment")
    @field:Valid
    val validFormsOfPayment: kotlin.collections.List<ValidFormsOfPayment>? = null,
    @JsonProperty("HotelDetails")
    @field:Valid
    val hotelDetails: Hotel? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var warnings: kotlin.collections.List<HotelDetailsResponseWarningsInner>? = null,
        private var transactionId: kotlin.String? = null,
        private var stayDates: HotelDetailsResponseStayDates? = null,
        private var lengthOfStay: kotlin.Int? = null,
        private var numberOfRooms: kotlin.Int? = null,
        private var occupants: kotlin.collections.List<HotelDetailsResponseOccupantsInner>? = null,
        private var validFormsOfPayment: kotlin.collections.List<ValidFormsOfPayment>? = null,
        private var hotelDetails: Hotel? = null
    ) {
        fun warnings(warnings: kotlin.collections.List<HotelDetailsResponseWarningsInner>?) = apply { this.warnings = warnings }

        fun transactionId(transactionId: kotlin.String?) = apply { this.transactionId = transactionId }

        fun stayDates(stayDates: HotelDetailsResponseStayDates?) = apply { this.stayDates = stayDates }

        fun lengthOfStay(lengthOfStay: kotlin.Int?) = apply { this.lengthOfStay = lengthOfStay }

        fun numberOfRooms(numberOfRooms: kotlin.Int?) = apply { this.numberOfRooms = numberOfRooms }

        fun occupants(occupants: kotlin.collections.List<HotelDetailsResponseOccupantsInner>?) = apply { this.occupants = occupants }

        fun validFormsOfPayment(validFormsOfPayment: kotlin.collections.List<ValidFormsOfPayment>?) = apply { this.validFormsOfPayment = validFormsOfPayment }

        fun hotelDetails(hotelDetails: Hotel?) = apply { this.hotelDetails = hotelDetails }

        fun build(): HotelDetailsResponse {
            return HotelDetailsResponse(
                warnings = warnings,
                transactionId = transactionId,
                stayDates = stayDates,
                lengthOfStay = lengthOfStay,
                numberOfRooms = numberOfRooms,
                occupants = occupants,
                validFormsOfPayment = validFormsOfPayment,
                hotelDetails = hotelDetails
            )
        }
    }

    fun toBuilder() =
        Builder(
            warnings = warnings,
            transactionId = transactionId,
            stayDates = stayDates,
            lengthOfStay = lengthOfStay,
            numberOfRooms = numberOfRooms,
            occupants = occupants,
            validFormsOfPayment = validFormsOfPayment,
            hotelDetails = hotelDetails
        )
}