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
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Validation

/**
 * List of Checked Bag Amenity.
 * @param bagNumber Bag number of CarryOnBag amenity for which the Availability applies.
 * @param availability Availability of Amenity
 */
data class Bag(
    // Bag number of CarryOnBag amenity for which the Availability applies.
    @JsonProperty("BagNumber")
    val bagNumber: kotlin.Int? = null,
    // Availability of Amenity
    @JsonProperty("Availability")
    val availability: Bag.Availability? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var bagNumber: kotlin.Int? = null,
        private var availability: Bag.Availability? = null
    ) {
        fun bagNumber(bagNumber: kotlin.Int?) = apply { this.bagNumber = bagNumber }

        fun availability(availability: Bag.Availability?) = apply { this.availability = availability }

        fun build(): Bag {
            val instance =
                Bag(
                    bagNumber = bagNumber,
                    availability = availability
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Bag) {
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
            bagNumber = bagNumber,
            availability = availability
        )

    /**
     * Availability of Amenity
     * Values: INCLUDED,NOT_AVAILABLE,AVAILABLE_FOR_FEE
     */
    enum class Availability(val value: kotlin.String) {
        @JsonProperty("INCLUDED")
        INCLUDED("INCLUDED"),

        @JsonProperty("NOT_AVAILABLE")
        NOT_AVAILABLE("NOT_AVAILABLE"),

        @JsonProperty("AVAILABLE_FOR_FEE")
        AVAILABLE_FOR_FEE("AVAILABLE_FOR_FEE")
    }
}
