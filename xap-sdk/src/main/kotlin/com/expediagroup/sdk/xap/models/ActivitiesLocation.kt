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

import com.expediagroup.sdk.xap.models.ActivitiesAddress
import com.expediagroup.sdk.xap.models.ActivitiesCountry
import com.expediagroup.sdk.xap.models.ActivitiesGeoLocation
import com.expediagroup.sdk.xap.models.ActivitiesNeighborhood
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * List of location(s) where the activity will happen.
 * @param type The type of location code (MULTICITY | METROCODE).
 * @param id Expedia Region ID of the specified airport.
 * @param name Location Name
 * @param code Location Code
 * @param locationId Location id.
 * @param address
 * @param pointOfInterest
 * @param geoLocation
 * @param neighborhood
 * @param regionId RegionId the location resides in.
 * @param country
 */
data class ActivitiesLocation(
    // The type of location code (MULTICITY | METROCODE).
    @JsonProperty("Type")
    val type: kotlin.String? = null,
    // Expedia Region ID of the specified airport.
    @JsonProperty("Id")
    val id: kotlin.String? = null,
    // Location Name
    @JsonProperty("Name")
    val name: kotlin.String? = null,
    // Location Code
    @JsonProperty("Code")
    val code: kotlin.String? = null,
    // Location id.
    @JsonProperty("LocationId")
    val locationId: kotlin.String? = null,
    @JsonProperty("Address")
    val address: ActivitiesAddress? = null,
    @JsonProperty("PointOfInterest")
    val pointOfInterest: kotlin.String? = null,
    @JsonProperty("GeoLocation")
    val geoLocation: ActivitiesGeoLocation? = null,
    @JsonProperty("Neighborhood")
    val neighborhood: ActivitiesNeighborhood? = null,
    // RegionId the location resides in.
    @JsonProperty("RegionId")
    val regionId: kotlin.Long? = null,
    @JsonProperty("Country")
    val country: ActivitiesCountry? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: kotlin.String? = null,
        private var id: kotlin.String? = null,
        private var name: kotlin.String? = null,
        private var code: kotlin.String? = null,
        private var locationId: kotlin.String? = null,
        private var address: ActivitiesAddress? = null,
        private var pointOfInterest: kotlin.String? = null,
        private var geoLocation: ActivitiesGeoLocation? = null,
        private var neighborhood: ActivitiesNeighborhood? = null,
        private var regionId: kotlin.Long? = null,
        private var country: ActivitiesCountry? = null,
    ) {
        fun type(type: kotlin.String?) = apply { this.type = type }

        fun id(id: kotlin.String?) = apply { this.id = id }

        fun name(name: kotlin.String?) = apply { this.name = name }

        fun code(code: kotlin.String?) = apply { this.code = code }

        fun locationId(locationId: kotlin.String?) = apply { this.locationId = locationId }

        fun address(address: ActivitiesAddress?) = apply { this.address = address }

        fun pointOfInterest(pointOfInterest: kotlin.String?) = apply { this.pointOfInterest = pointOfInterest }

        fun geoLocation(geoLocation: ActivitiesGeoLocation?) = apply { this.geoLocation = geoLocation }

        fun neighborhood(neighborhood: ActivitiesNeighborhood?) = apply { this.neighborhood = neighborhood }

        fun regionId(regionId: kotlin.Long?) = apply { this.regionId = regionId }

        fun country(country: ActivitiesCountry?) = apply { this.country = country }

        fun build(): ActivitiesLocation {
            val instance =
                ActivitiesLocation(
                    type = type,
                    id = id,
                    name = name,
                    code = code,
                    locationId = locationId,
                    address = address,
                    pointOfInterest = pointOfInterest,
                    geoLocation = geoLocation,
                    neighborhood = neighborhood,
                    regionId = regionId,
                    country = country,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            type = type,
            id = id,
            name = name,
            code = code,
            locationId = locationId,
            address = address,
            pointOfInterest = pointOfInterest,
            geoLocation = geoLocation,
            neighborhood = neighborhood,
            regionId = regionId,
            country = country,
        )
}
