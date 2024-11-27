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
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 *
 * @param adults Specifies the number of adults staying in each room.
 * @param childAges Specifies the age(s) of each of the children staying in the room, as well as the number of children in the room.
 */
data class HotelDetailsResponseOccupantsInner(
    // Specifies the number of adults staying in each room.
    @JsonProperty("Adults")
    @field:Valid
    val adults: kotlin.Any? = null,
    // Specifies the age(s) of each of the children staying in the room, as well as the number of children in the room.
    @JsonProperty("ChildAges")
    @field:Valid
    val childAges: kotlin.Any? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var adults: kotlin.Any? = null,
        private var childAges: kotlin.Any? = null
    ) {
        fun adults(adults: kotlin.Any?) = apply { this.adults = adults }

        fun childAges(childAges: kotlin.Any?) = apply { this.childAges = childAges }

        fun build(): HotelDetailsResponseOccupantsInner {
            val instance =
                HotelDetailsResponseOccupantsInner(
                    adults = adults,
                    childAges = childAges
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: HotelDetailsResponseOccupantsInner) {
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
            adults = adults,
            childAges = childAges
        )
}