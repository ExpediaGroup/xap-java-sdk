/**
 * Copyright (C) 2025 Expedia, Inc.
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
package com.expediagroup.sdk.xap.model

import com.expediagroup.sdk.xap.model.CarsCountry
import com.expediagroup.sdk.xap.model.CarsGeoLocation
import com.expediagroup.sdk.xap.model.CarsLocation
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * List for possible locations from which the customer must choose the best one to be re-submitted in the request.
 * @param requestedLocation Location used in partner request.
 * @param locations Container for list of possible locations that could be used to disambiguate the query.
 * @param type Type of the location.
 * @param regionId RegionId the location resides in.
 * @param shortName The name of the location which matches the location keyword.
 * @param airportCode Indicates the nearest major airport to the location.
 * @param address The address of the location.
 * @param country
 * @param geoLocation
 */
data class CarsLocationOption(
    // Location used in partner request.
    @JsonProperty("RequestedLocation")
    val requestedLocation: kotlin.String,
    // Container for list of possible locations that could be used to disambiguate the query.
    @JsonProperty("Locations")
    val locations: kotlin.collections
        .List<
            CarsLocation,
        >,
    // Type of the location.
    @JsonProperty("Type")
    val type: kotlin.String? = null,
    // RegionId the location resides in.
    @JsonProperty("RegionId")
    val regionId: kotlin.String? = null,
    // The name of the location which matches the location keyword.
    @JsonProperty("ShortName")
    val shortName: kotlin.String? = null,
    // Indicates the nearest major airport to the location.
    @JsonProperty("AirportCode")
    val airportCode: kotlin.String? = null,
    // The address of the location.
    @JsonProperty("Address")
    val address: kotlin.String? = null,
    @JsonProperty("Country")
    val country: CarsCountry? = null,
    @JsonProperty("GeoLocation")
    val geoLocation: CarsGeoLocation? = null,
) {
    init {
        require(requestedLocation != null) { "requestedLocation must not be null" }

        require(locations != null) { "locations must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var requestedLocation: kotlin.String? = null,
        private var locations: kotlin.collections.List<CarsLocation>? = null,
        private var type: kotlin.String? = null,
        private var regionId: kotlin.String? = null,
        private var shortName: kotlin.String? = null,
        private var airportCode: kotlin.String? = null,
        private var address: kotlin.String? = null,
        private var country: CarsCountry? = null,
        private var geoLocation: CarsGeoLocation? = null,
    ) {
        fun requestedLocation(requestedLocation: kotlin.String) = apply { this.requestedLocation = requestedLocation }

        fun locations(locations: kotlin.collections.List<CarsLocation>) = apply { this.locations = locations }

        fun type(type: kotlin.String?) = apply { this.type = type }

        fun regionId(regionId: kotlin.String?) = apply { this.regionId = regionId }

        fun shortName(shortName: kotlin.String?) = apply { this.shortName = shortName }

        fun airportCode(airportCode: kotlin.String?) = apply { this.airportCode = airportCode }

        fun address(address: kotlin.String?) = apply { this.address = address }

        fun country(country: CarsCountry?) = apply { this.country = country }

        fun geoLocation(geoLocation: CarsGeoLocation?) = apply { this.geoLocation = geoLocation }

        fun build(): CarsLocationOption {
            val instance =
                CarsLocationOption(
                    requestedLocation = requestedLocation!!,
                    locations = locations!!,
                    type = type,
                    regionId = regionId,
                    shortName = shortName,
                    airportCode = airportCode,
                    address = address,
                    country = country,
                    geoLocation = geoLocation,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            requestedLocation = requestedLocation!!,
            locations = locations!!,
            type = type,
            regionId = regionId,
            shortName = shortName,
            airportCode = airportCode,
            address = address,
            country = country,
            geoLocation = geoLocation,
        )
}
