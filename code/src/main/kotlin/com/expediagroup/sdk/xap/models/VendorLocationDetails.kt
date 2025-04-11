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
import com.expediagroup.sdk.xap.models.CarsDistance
import com.expediagroup.sdk.xap.models.CarsLocation
import com.expediagroup.sdk.xap.models.DateTimePeriod
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Drop off information
 * @param dateTime Pickup date and time.
 * @param location
 * @param shuttleCategory The category of shuttle from the terminal to the rental car counter. Please find list of Shuttle Categories in the Related Links Section below.
 * @param distance
 * @param openSchedule A List of date time periods to indicate the vendor business hours for the pickup time.
 */
data class VendorLocationDetails(
    // Pickup date and time.
    @JsonProperty("DateTime")
    val dateTime: java.time.LocalDateTime,
    @JsonProperty("Location")
    @field:NotNull
    @field:Valid
    val location: CarsLocation,
    // The category of shuttle from the terminal to the rental car counter. Please find list of Shuttle Categories in the Related Links Section below.
    @JsonProperty("ShuttleCategory")
    @field:Valid
    val shuttleCategory: kotlin.String? = null,
    @JsonProperty("Distance")
    @field:Valid
    val distance: CarsDistance? = null,
    // A List of date time periods to indicate the vendor business hours for the pickup time.
    @JsonProperty("OpenSchedule")
    @field:Valid
    val openSchedule: kotlin.collections.List<DateTimePeriod>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var dateTime: java.time.LocalDateTime? = null,
        private var location: CarsLocation? = null,
        private var shuttleCategory: kotlin.String? = null,
        private var distance: CarsDistance? = null,
        private var openSchedule: kotlin.collections.List<DateTimePeriod>? = null
    ) {
        fun dateTime(dateTime: java.time.LocalDateTime) = apply { this.dateTime = dateTime }

        fun location(location: CarsLocation) = apply { this.location = location }

        fun shuttleCategory(shuttleCategory: kotlin.String?) = apply { this.shuttleCategory = shuttleCategory }

        fun distance(distance: CarsDistance?) = apply { this.distance = distance }

        fun openSchedule(openSchedule: kotlin.collections.List<DateTimePeriod>?) = apply { this.openSchedule = openSchedule }

        fun build(): VendorLocationDetails {
            val instance =
                VendorLocationDetails(
                    dateTime = dateTime!!,
                    location = location!!,
                    shuttleCategory = shuttleCategory,
                    distance = distance,
                    openSchedule = openSchedule
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: VendorLocationDetails) {
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
            dateTime = dateTime!!,
            location = location!!,
            shuttleCategory = shuttleCategory,
            distance = distance,
            openSchedule = openSchedule
        )
}
