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

/**
 *
 * @param latitude The geographic coordinates of the hotel property, based on a horizontal angular measurement relative to The Equator.  North latitude will be represented by a positive value.  South latitude will be represented by a negative value.
 * @param longitude The geographic coordinates of the hotel property, based on a vertical angular measurement relative to the universal Prime Meridian (Royal Observatory, Greenwich).  East longitude will be represented by a positive value.  West longitude will be represented by a negative value.
 * @param obfuscated Indicates whether the displayed Latitude/Longitude information is obfuscated.  Note: Exact Lat/Long values for Vacation Rental properties will not be shown in either XAPv3 Search or Details responses to respect the security of the homeowner. Instead an 'obfuscated' Lat/Long value will be returned that will indicate the general area within which the property is located, but not the exact location of the property itself.
 */
data class LocationGeoLocation(
    // The geographic coordinates of the hotel property, based on a horizontal angular measurement relative to The Equator.  North latitude will be represented by a positive value.  South latitude will be represented by a negative value.
    @JsonProperty("Latitude")
    @field:Valid
    val latitude: kotlin.String? = null,
    // The geographic coordinates of the hotel property, based on a vertical angular measurement relative to the universal Prime Meridian (Royal Observatory, Greenwich).  East longitude will be represented by a positive value.  West longitude will be represented by a negative value.
    @JsonProperty("Longitude")
    @field:Valid
    val longitude: kotlin.String? = null,
    // Indicates whether the displayed Latitude/Longitude information is obfuscated.  Note: Exact Lat/Long values for Vacation Rental properties will not be shown in either XAPv3 Search or Details responses to respect the security of the homeowner. Instead an 'obfuscated' Lat/Long value will be returned that will indicate the general area within which the property is located, but not the exact location of the property itself.
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
        fun latitude(latitude: kotlin.String?) = apply { this.latitude = latitude }

        fun longitude(longitude: kotlin.String?) = apply { this.longitude = longitude }

        fun obfuscated(obfuscated: kotlin.Boolean?) = apply { this.obfuscated = obfuscated }

        fun build(): LocationGeoLocation {
            val instance =
                LocationGeoLocation(
                    latitude = latitude,
                    longitude = longitude,
                    obfuscated = obfuscated
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: LocationGeoLocation) {
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
            latitude = latitude,
            longitude = longitude,
            obfuscated = obfuscated
        )
}
