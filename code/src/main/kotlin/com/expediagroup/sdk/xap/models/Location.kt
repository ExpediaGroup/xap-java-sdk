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
import com.expediagroup.sdk.xap.models.Address
import com.expediagroup.sdk.xap.models.LocationGeoLocation
import com.expediagroup.sdk.xap.models.Neighborhood
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Container for list of possible locations that could be used to disambiguate the query.
 * @param address
 * @param geoLocation
 * @param neighborhood
 */
data class Location(
    @JsonProperty("Address")
    @field:Valid
    val address: Address? = null,
    @JsonProperty("GeoLocation")
    @field:Valid
    val geoLocation: LocationGeoLocation? = null,
    @JsonProperty("Neighborhood")
    @field:Valid
    val neighborhood: Neighborhood? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var address: Address? = null,
        private var geoLocation: LocationGeoLocation? = null,
        private var neighborhood: Neighborhood? = null
    ) {
        fun address(address: Address?) = apply { this.address = address }

        fun geoLocation(geoLocation: LocationGeoLocation?) = apply { this.geoLocation = geoLocation }

        fun neighborhood(neighborhood: Neighborhood?) = apply { this.neighborhood = neighborhood }

        fun build(): Location {
            val instance =
                Location(
                    address = address,
                    geoLocation = geoLocation,
                    neighborhood = neighborhood
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Location) {
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
            address = address,
            geoLocation = geoLocation,
            neighborhood = neighborhood
        )
}
