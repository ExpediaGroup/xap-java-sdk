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
import com.expediagroup.sdk.xap.models.FlightsV3StayDates
import com.expediagroup.sdk.xap.models.HotelReference
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container for Package Offer
 * @param hotelReference Container for Hotel Reference information
 * @param flightReferenceId Flight offer id
 * @param stayDates
 * @param numberOfNights Number of nights guest staying in the hotel
 */
data class PackagedOffer(
    // Container for Hotel Reference information
    @JsonProperty("HotelReference")
    @field:NotNull
    @field:Valid
    val hotelReference: kotlin.collections
        .List<
            HotelReference
        >,
    // Flight offer id
    @JsonProperty("FlightReferenceId")
    @field:NotNull
    @field:Valid
    val flightReferenceId: kotlin.String,
    @JsonProperty("StayDates")
    @field:Valid
    val stayDates: FlightsV3StayDates? = null,
    // Number of nights guest staying in the hotel
    @JsonProperty("NumberOfNights")
    val numberOfNights: kotlin.Int? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var hotelReference: kotlin.collections.List<HotelReference>? = null,
        private var flightReferenceId: kotlin.String? = null,
        private var stayDates: FlightsV3StayDates? = null,
        private var numberOfNights: kotlin.Int? = null
    ) {
        fun hotelReference(hotelReference: kotlin.collections.List<HotelReference>) = apply { this.hotelReference = hotelReference }

        fun flightReferenceId(flightReferenceId: kotlin.String) = apply { this.flightReferenceId = flightReferenceId }

        fun stayDates(stayDates: FlightsV3StayDates?) = apply { this.stayDates = stayDates }

        fun numberOfNights(numberOfNights: kotlin.Int?) = apply { this.numberOfNights = numberOfNights }

        fun build(): PackagedOffer {
            val instance =
                PackagedOffer(
                    hotelReference = hotelReference!!,
                    flightReferenceId = flightReferenceId!!,
                    stayDates = stayDates,
                    numberOfNights = numberOfNights
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: PackagedOffer) {
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
            hotelReference = hotelReference!!,
            flightReferenceId = flightReferenceId!!,
            stayDates = stayDates,
            numberOfNights = numberOfNights
        )
}
