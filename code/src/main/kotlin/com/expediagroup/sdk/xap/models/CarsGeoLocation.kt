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
 * Container for Geo location.
 * @param latitude Latitude of the location.
 * @param longitude Longitude of the location.
 * @param obfuscated
 */
data class CarsGeoLocation(
    // Latitude of the location.
    @JsonProperty("Latitude")
    @field:NotNull
    @field:Valid
    val latitude: kotlin.String,
    // Longitude of the location.
    @JsonProperty("Longitude")
    @field:NotNull
    @field:Valid
    val longitude: kotlin.String,
    @JsonProperty("Obfuscated")
    @field:Valid
    val obfuscated: kotlin.Boolean? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var latitude: kotlin.String? = null,
        private var longitude: kotlin.String? = null,
        private var obfuscated: kotlin.Boolean? = null
    ) {
        fun latitude(latitude: kotlin.String) = apply { this.latitude = latitude }

        fun longitude(longitude: kotlin.String) = apply { this.longitude = longitude }

        fun obfuscated(obfuscated: kotlin.Boolean?) = apply { this.obfuscated = obfuscated }

        fun build(): CarsGeoLocation {
            val instance =
                CarsGeoLocation(
                    latitude = latitude!!,
                    longitude = longitude!!,
                    obfuscated = obfuscated
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: CarsGeoLocation) {
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
            latitude = latitude!!,
            longitude = longitude!!,
            obfuscated = obfuscated
        )
}
