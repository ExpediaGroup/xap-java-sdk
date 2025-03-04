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

import com.expediagroup.sdk.rest.exception.client.PropertyConstraintViolationException
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
* Container for distance information.  Only returned for city/address search or `geoLocation` search or single `regionId` search.
 * @param `value` The distance between the center of the search and the hotel.
 * @param unit The unit of distance.
 * @param direction The direction to the hotel from the center point of the search.
*/
data class Distance(
    // The distance between the center of the search and the hotel.
    @JsonProperty("Value")
    @field:Valid
    val `value`: kotlin.String? = null,
    // The unit of distance.
    @JsonProperty("Unit")
    val unit: Distance.Unit? = null,
    // The direction to the hotel from the center point of the search.
    @JsonProperty("Direction")
    val direction: Distance.Direction? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var `value`: kotlin.String? = null,
        private var unit: Distance.Unit? = null,
        private var direction: Distance.Direction? = null
    ) {
        fun `value`(`value`: kotlin.String?) = apply { this.`value` = `value` }

        fun unit(unit: Distance.Unit?) = apply { this.unit = unit }

        fun direction(direction: Distance.Direction?) = apply { this.direction = direction }

        fun build(): Distance {
            val instance =
                Distance(
                    `value` = `value`,
                    unit = unit,
                    direction = direction
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Distance) {
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
            `value` = `value`,
            unit = unit,
            direction = direction
        )

    /**
     * The unit of distance.
     * Values: KM,MI
     */
    enum class Unit(val value: kotlin.String) {
        @JsonProperty("km")
        KM("km"),

        @JsonProperty("mi")
        MI("mi")
    }

    /**
     * The direction to the hotel from the center point of the search.
     * Values: N,S,W,E,NW,NE,SW,SE
     */
    enum class Direction(val value: kotlin.String) {
        @JsonProperty("N")
        N("N"),

        @JsonProperty("S")
        S("S"),

        @JsonProperty("W")
        W("W"),

        @JsonProperty("E")
        E("E"),

        @JsonProperty("NW")
        NW("NW"),

        @JsonProperty("NE")
        NE("NE"),

        @JsonProperty("SW")
        SW("SW"),

        @JsonProperty("SE")
        SE("SE")
    }
}
