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
import com.expediagroup.sdk.xap.models.PropertyManager
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Container for information about the hotel property detail. It will be shown for Vacation Rental hotel only.
 * @param propertyRegistryNumber The registry number of property.
 * @param hostLanguages The languages the host of property will speak.
 * @param maxOccupancy The maximum number of guests allowed to stay in the property.
 * @param numberOfBedrooms The number of bedrooms in the property.
 * @param numberOfBathrooms The number of bathrooms in the property.
 * @param squareFeet Property area in square feet.
 * @param propertyManager
 */
data class PropertyDetails(
    // The registry number of property.
    @JsonProperty("PropertyRegistryNumber")
    @field:Valid
    val propertyRegistryNumber: kotlin.String? = null,
    // The languages the host of property will speak.
    @JsonProperty("HostLanguages")
    @field:Valid
    val hostLanguages: kotlin.collections.List<kotlin.String>? = null,
    // The maximum number of guests allowed to stay in the property.
    @JsonProperty("MaxOccupancy")
    val maxOccupancy: kotlin.Int? = null,
    // The number of bedrooms in the property.
    @JsonProperty("NumberOfBedrooms")
    val numberOfBedrooms: kotlin.Int? = null,
    // The number of bathrooms in the property.
    @JsonProperty("NumberOfBathrooms")
    val numberOfBathrooms: kotlin.Int? = null,
    // Property area in square feet.
    @JsonProperty("SquareFeet")
    @field:Valid
    val squareFeet: kotlin.String? = null,
    @JsonProperty("PropertyManager")
    @field:Valid
    val propertyManager: PropertyManager? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var propertyRegistryNumber: kotlin.String? = null,
        private var hostLanguages: kotlin.collections.List<kotlin.String>? = null,
        private var maxOccupancy: kotlin.Int? = null,
        private var numberOfBedrooms: kotlin.Int? = null,
        private var numberOfBathrooms: kotlin.Int? = null,
        private var squareFeet: kotlin.String? = null,
        private var propertyManager: PropertyManager? = null
    ) {
        fun propertyRegistryNumber(propertyRegistryNumber: kotlin.String?) = apply { this.propertyRegistryNumber = propertyRegistryNumber }

        fun hostLanguages(hostLanguages: kotlin.collections.List<kotlin.String>?) = apply { this.hostLanguages = hostLanguages }

        fun maxOccupancy(maxOccupancy: kotlin.Int?) = apply { this.maxOccupancy = maxOccupancy }

        fun numberOfBedrooms(numberOfBedrooms: kotlin.Int?) = apply { this.numberOfBedrooms = numberOfBedrooms }

        fun numberOfBathrooms(numberOfBathrooms: kotlin.Int?) = apply { this.numberOfBathrooms = numberOfBathrooms }

        fun squareFeet(squareFeet: kotlin.String?) = apply { this.squareFeet = squareFeet }

        fun propertyManager(propertyManager: PropertyManager?) = apply { this.propertyManager = propertyManager }

        fun build(): PropertyDetails {
            val instance =
                PropertyDetails(
                    propertyRegistryNumber = propertyRegistryNumber,
                    hostLanguages = hostLanguages,
                    maxOccupancy = maxOccupancy,
                    numberOfBedrooms = numberOfBedrooms,
                    numberOfBathrooms = numberOfBathrooms,
                    squareFeet = squareFeet,
                    propertyManager = propertyManager
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: PropertyDetails) {
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
            propertyRegistryNumber = propertyRegistryNumber,
            hostLanguages = hostLanguages,
            maxOccupancy = maxOccupancy,
            numberOfBedrooms = numberOfBedrooms,
            numberOfBathrooms = numberOfBathrooms,
            squareFeet = squareFeet,
            propertyManager = propertyManager
        )
}
