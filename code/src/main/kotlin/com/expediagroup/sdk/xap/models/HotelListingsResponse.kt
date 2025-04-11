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
import com.expediagroup.sdk.xap.models.Hotel
import com.expediagroup.sdk.xap.models.HotelListingsResponseStayDates
import com.expediagroup.sdk.xap.models.Occupant
import com.expediagroup.sdk.xap.models.Warning
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 *
 * @param warnings There were some errors or events during the transaction, but the API has still returned a response.  Container for all warnings.
 * @param count The number of hotels actually returned in the response.
 * @param totalHotelCount The number of hotels present in the location.
 * @param transactionId Unique identifier for the transaction.
 * @param stayDates
 * @param lengthOfStay The number of stay nights.
 * @param numberOfRooms The number of the rooms requested on behalf of the user.
 * @param occupants Container of occupants. It is an array including occupants of each room.
 * @param hotels Container for all hotels.
 */
data class HotelListingsResponse(
    // There were some errors or events during the transaction, but the API has still returned a response.  Container for all warnings.
    @JsonProperty("Warnings")
    @field:Valid
    val warnings: kotlin.collections.List<Warning>? = null,
    // The number of hotels actually returned in the response.
    @JsonProperty("Count")
    val count: kotlin.Int? = null,
    // The number of hotels present in the location.
    @JsonProperty("TotalHotelCount")
    val totalHotelCount: kotlin.Int? = null,
    // Unique identifier for the transaction.
    @JsonProperty("TransactionId")
    @field:Valid
    val transactionId: kotlin.String? = null,
    @JsonProperty("StayDates")
    @field:Valid
    val stayDates: HotelListingsResponseStayDates? = null,
    // The number of stay nights.
    @JsonProperty("LengthOfStay")
    val lengthOfStay: kotlin.Int? = null,
    // The number of the rooms requested on behalf of the user.
    @JsonProperty("NumberOfRooms")
    val numberOfRooms: kotlin.Int? = null,
    // Container of occupants. It is an array including occupants of each room.
    @JsonProperty("Occupants")
    @field:Valid
    val occupants: kotlin.collections.List<Occupant>? = null,
    // Container for all hotels.
    @JsonProperty("Hotels")
    @field:Valid
    val hotels: kotlin.collections.List<Hotel>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var warnings: kotlin.collections.List<Warning>? = null,
        private var count: kotlin.Int? = null,
        private var totalHotelCount: kotlin.Int? = null,
        private var transactionId: kotlin.String? = null,
        private var stayDates: HotelListingsResponseStayDates? = null,
        private var lengthOfStay: kotlin.Int? = null,
        private var numberOfRooms: kotlin.Int? = null,
        private var occupants: kotlin.collections.List<Occupant>? = null,
        private var hotels: kotlin.collections.List<Hotel>? = null
    ) {
        fun warnings(warnings: kotlin.collections.List<Warning>?) = apply { this.warnings = warnings }

        fun count(count: kotlin.Int?) = apply { this.count = count }

        fun totalHotelCount(totalHotelCount: kotlin.Int?) = apply { this.totalHotelCount = totalHotelCount }

        fun transactionId(transactionId: kotlin.String?) = apply { this.transactionId = transactionId }

        fun stayDates(stayDates: HotelListingsResponseStayDates?) = apply { this.stayDates = stayDates }

        fun lengthOfStay(lengthOfStay: kotlin.Int?) = apply { this.lengthOfStay = lengthOfStay }

        fun numberOfRooms(numberOfRooms: kotlin.Int?) = apply { this.numberOfRooms = numberOfRooms }

        fun occupants(occupants: kotlin.collections.List<Occupant>?) = apply { this.occupants = occupants }

        fun hotels(hotels: kotlin.collections.List<Hotel>?) = apply { this.hotels = hotels }

        fun build(): HotelListingsResponse {
            val instance =
                HotelListingsResponse(
                    warnings = warnings,
                    count = count,
                    totalHotelCount = totalHotelCount,
                    transactionId = transactionId,
                    stayDates = stayDates,
                    lengthOfStay = lengthOfStay,
                    numberOfRooms = numberOfRooms,
                    occupants = occupants,
                    hotels = hotels
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: HotelListingsResponse) {
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
            warnings = warnings,
            count = count,
            totalHotelCount = totalHotelCount,
            transactionId = transactionId,
            stayDates = stayDates,
            lengthOfStay = lengthOfStay,
            numberOfRooms = numberOfRooms,
            occupants = occupants,
            hotels = hotels
        )
}
