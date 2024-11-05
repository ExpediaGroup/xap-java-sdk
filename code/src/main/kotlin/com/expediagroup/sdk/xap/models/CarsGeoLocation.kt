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

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * Container for Geo location.
 * @param latitude Latitude of the location.
 * @param longitude Longitude of the location.
 * @param obfuscated
 */
data class CarsGeoLocation(
    // Latitude of the location.
    @JsonProperty("Latitude")
    @field:Valid
    val latitude: kotlin.String,
    // Longitude of the location.
    @JsonProperty("Longitude")
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
            // Check required params
            validateNullity()
            return CarsGeoLocation(
                latitude = latitude!!,
                longitude = longitude!!,
                obfuscated = obfuscated
            )
        }

        private fun validateNullity() {
            if (latitude == null) {
                throw NullPointerException("Required parameter latitude is missing")
            }
            if (longitude == null) {
                throw NullPointerException("Required parameter longitude is missing")
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
