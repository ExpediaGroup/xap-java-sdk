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

import com.expediagroup.sdk.xap.models.Country
import com.expediagroup.sdk.xap.models.GeoLocation
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * Container for possible matches to your ambiguous `locationKeyword` query.
 * @param type Type of the location.
 * @param regionId RegionId the location resides in.
 * @param shortName The name of the location which matches the location keyword.
 * @param airportCode Indicates the nearest major airport to the location.
 * @param address The address of the location.
 * @param country
 * @param geoLocation
 */
data class LocationOption(
    // Type of the location.
    @JsonProperty("Type")
    @field:Valid
    val type: kotlin.String? = null,
    // RegionId the location resides in.
    @JsonProperty("RegionId")
    @field:Valid
    val regionId: kotlin.String? = null,
    // The name of the location which matches the location keyword.
    @JsonProperty("ShortName")
    @field:Valid
    val shortName: kotlin.String? = null,
    // Indicates the nearest major airport to the location.
    @JsonProperty("AirportCode")
    @field:Valid
    val airportCode: kotlin.String? = null,
    // The address of the location.
    @JsonProperty("Address")
    @field:Valid
    val address: kotlin.String? = null,
    @JsonProperty("Country")
    @field:Valid
    val country: Country? = null,
    @JsonProperty("GeoLocation")
    @field:Valid
    val geoLocation: GeoLocation? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: kotlin.String? = null,
        private var regionId: kotlin.String? = null,
        private var shortName: kotlin.String? = null,
        private var airportCode: kotlin.String? = null,
        private var address: kotlin.String? = null,
        private var country: Country? = null,
        private var geoLocation: GeoLocation? = null
    ) {
        fun type(type: kotlin.String?) = apply { this.type = type }

        fun regionId(regionId: kotlin.String?) = apply { this.regionId = regionId }

        fun shortName(shortName: kotlin.String?) = apply { this.shortName = shortName }

        fun airportCode(airportCode: kotlin.String?) = apply { this.airportCode = airportCode }

        fun address(address: kotlin.String?) = apply { this.address = address }

        fun country(country: Country?) = apply { this.country = country }

        fun geoLocation(geoLocation: GeoLocation?) = apply { this.geoLocation = geoLocation }

        fun build(): LocationOption {
            return LocationOption(
                type = type,
                regionId = regionId,
                shortName = shortName,
                airportCode = airportCode,
                address = address,
                country = country,
                geoLocation = geoLocation
            )
        }
    }

    fun toBuilder() =
        Builder(
            type = type,
            regionId = regionId,
            shortName = shortName,
            airportCode = airportCode,
            address = address,
            country = country,
            geoLocation = geoLocation
        )
}
