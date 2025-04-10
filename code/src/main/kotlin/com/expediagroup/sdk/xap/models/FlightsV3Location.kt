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
import com.expediagroup.sdk.xap.models.FlightsV3Country
import com.expediagroup.sdk.xap.models.FlightsV3GeoLocation
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Container for list of possible locations that could be used to disambiguate the query.
 * @param type The type of location code (MULTICITY | METROCODE).
 * @param id Expedia Region ID of the specified airport.
 * @param name Location Name
 * @param code Location Code
 * @param address Street address of the location (if available)
 * @param geoLocation
 * @param country
 */
data class FlightsV3Location(
    // The type of location code (MULTICITY | METROCODE).
    @JsonProperty("Type")
    @field:Valid
    val type: kotlin.String? = null,
    // Expedia Region ID of the specified airport.
    @JsonProperty("Id")
    @field:Valid
    val id: kotlin.String? = null,
    // Location Name
    @JsonProperty("Name")
    @field:Valid
    val name: kotlin.String? = null,
    // Location Code
    @JsonProperty("Code")
    @field:Valid
    val code: kotlin.String? = null,
    // Street address of the location (if available)
    @JsonProperty("Address")
    @field:Valid
    val address: kotlin.String? = null,
    @JsonProperty("GeoLocation")
    @field:Valid
    val geoLocation: FlightsV3GeoLocation? = null,
    @JsonProperty("Country")
    @field:Valid
    val country: FlightsV3Country? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: kotlin.String? = null,
        private var id: kotlin.String? = null,
        private var name: kotlin.String? = null,
        private var code: kotlin.String? = null,
        private var address: kotlin.String? = null,
        private var geoLocation: FlightsV3GeoLocation? = null,
        private var country: FlightsV3Country? = null
    ) {
        fun type(type: kotlin.String?) = apply { this.type = type }

        fun id(id: kotlin.String?) = apply { this.id = id }

        fun name(name: kotlin.String?) = apply { this.name = name }

        fun code(code: kotlin.String?) = apply { this.code = code }

        fun address(address: kotlin.String?) = apply { this.address = address }

        fun geoLocation(geoLocation: FlightsV3GeoLocation?) = apply { this.geoLocation = geoLocation }

        fun country(country: FlightsV3Country?) = apply { this.country = country }

        fun build(): FlightsV3Location {
            val instance =
                FlightsV3Location(
                    type = type,
                    id = id,
                    name = name,
                    code = code,
                    address = address,
                    geoLocation = geoLocation,
                    country = country
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightsV3Location) {
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
            type = type,
            id = id,
            name = name,
            code = code,
            address = address,
            geoLocation = geoLocation,
            country = country
        )
}
