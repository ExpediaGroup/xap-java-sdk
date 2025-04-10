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
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container for showing the arrival airport details
 * @param code Three-letter IATA airport code for departure location
 * @param name Name of departure airport
 * @param city City where departure airport resides
 * @param province Province or State where departure airport resides
 * @param country Country where departure airport resides
 * @param latitude Latitude where departure airport resides
 * @param longitude Longitude where departure airport resides
 */
data class FlightsV2Airport(
    // Three-letter IATA airport code for departure location
    @JsonProperty("Code")
    @field:NotNull
    @field:Valid
    val code: kotlin.String,
    // Name of departure airport
    @JsonProperty("Name")
    @field:Valid
    val name: kotlin.String? = null,
    // City where departure airport resides
    @JsonProperty("City")
    @field:Valid
    val city: kotlin.String? = null,
    // Province or State where departure airport resides
    @JsonProperty("Province")
    @field:Valid
    val province: kotlin.String? = null,
    // Country where departure airport resides
    @JsonProperty("Country")
    @field:Valid
    val country: kotlin.String? = null,
    // Latitude where departure airport resides
    @JsonProperty("Latitude")
    @field:Valid
    val latitude: kotlin.String? = null,
    // Longitude where departure airport resides
    @JsonProperty("Longitude")
    @field:Valid
    val longitude: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: kotlin.String? = null,
        private var name: kotlin.String? = null,
        private var city: kotlin.String? = null,
        private var province: kotlin.String? = null,
        private var country: kotlin.String? = null,
        private var latitude: kotlin.String? = null,
        private var longitude: kotlin.String? = null
    ) {
        fun code(code: kotlin.String) = apply { this.code = code }

        fun name(name: kotlin.String?) = apply { this.name = name }

        fun city(city: kotlin.String?) = apply { this.city = city }

        fun province(province: kotlin.String?) = apply { this.province = province }

        fun country(country: kotlin.String?) = apply { this.country = country }

        fun latitude(latitude: kotlin.String?) = apply { this.latitude = latitude }

        fun longitude(longitude: kotlin.String?) = apply { this.longitude = longitude }

        fun build(): FlightsV2Airport {
            val instance =
                FlightsV2Airport(
                    code = code!!,
                    name = name,
                    city = city,
                    province = province,
                    country = country,
                    latitude = latitude,
                    longitude = longitude
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightsV2Airport) {
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
            code = code!!,
            name = name,
            city = city,
            province = province,
            country = country,
            latitude = latitude,
            longitude = longitude
        )
}
