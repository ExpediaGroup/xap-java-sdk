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

import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.FlightsV3Hotel
import com.expediagroup.sdk.xap.models.FlightsV3Occupant
import com.expediagroup.sdk.xap.models.FlightsV3StayDates
import com.expediagroup.sdk.xap.models.PackageOffers
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container for cross sell information
 * @param hotelCount Number of hotels available.
 * @param stayDates
 * @param numberOfRooms Number of rooms requested.
 * @param numberOfNights Number of nights guest staying in the hotel.
 * @param occupants Container for occupants list.
 * @param hotels Container for information on each hotel offer.
 * @param offers Container for information about the hotel used in this package offer.
 */
data class CrossSell(
    // Number of hotels available.
    @JsonProperty("HotelCount")
    val hotelCount: kotlin.Int,
    @JsonProperty("StayDates")
    @field:NotNull
    @field:Valid
    val stayDates: FlightsV3StayDates,
    // Number of rooms requested.
    @JsonProperty("NumberOfRooms")
    val numberOfRooms: kotlin.Int =
        1,
    // Number of nights guest staying in the hotel.
    @JsonProperty("NumberOfNights")
    val numberOfNights: kotlin.Int,
    // Container for occupants list.
    @JsonProperty("Occupants")
    @field:NotNull
    @field:Valid
    val occupants: kotlin.collections
        .List<
            FlightsV3Occupant
        >,
    // Container for information on each hotel offer.
    @JsonProperty("Hotels")
    @field:NotNull
    @field:Valid
    val hotels: kotlin.collections
        .List<
            FlightsV3Hotel
        >,
    // Container for information about the hotel used in this package offer.
    @JsonProperty("Offers")
    @field:NotNull
    @field:Valid
    val offers: kotlin.collections
        .List<
            PackageOffers
        >
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var hotelCount: kotlin.Int? = null,
        private var stayDates: FlightsV3StayDates? = null,
        private var numberOfRooms: kotlin.Int? = null,
        private var numberOfNights: kotlin.Int? = null,
        private var occupants: kotlin.collections.List<FlightsV3Occupant>? = null,
        private var hotels: kotlin.collections.List<FlightsV3Hotel>? = null,
        private var offers: kotlin.collections.List<PackageOffers>? = null
    ) {
        fun hotelCount(hotelCount: kotlin.Int) = apply { this.hotelCount = hotelCount }

        fun stayDates(stayDates: FlightsV3StayDates) = apply { this.stayDates = stayDates }

        fun numberOfRooms(numberOfRooms: kotlin.Int) = apply { this.numberOfRooms = numberOfRooms }

        fun numberOfNights(numberOfNights: kotlin.Int) = apply { this.numberOfNights = numberOfNights }

        fun occupants(occupants: kotlin.collections.List<FlightsV3Occupant>) = apply { this.occupants = occupants }

        fun hotels(hotels: kotlin.collections.List<FlightsV3Hotel>) = apply { this.hotels = hotels }

        fun offers(offers: kotlin.collections.List<PackageOffers>) = apply { this.offers = offers }

        fun build(): CrossSell {
            val instance =
                CrossSell(
                    hotelCount = hotelCount!!,
                    stayDates = stayDates!!,
                    numberOfRooms = numberOfRooms!!,
                    numberOfNights = numberOfNights!!,
                    occupants = occupants!!,
                    hotels = hotels!!,
                    offers = offers!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: CrossSell) {
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
            hotelCount = hotelCount!!,
            stayDates = stayDates!!,
            numberOfRooms = numberOfRooms!!,
            numberOfNights = numberOfNights!!,
            occupants = occupants!!,
            hotels = hotels!!,
            offers = offers!!
        )
}
