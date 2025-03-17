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

import com.expediagroup.sdk.xap.models.Address
import com.expediagroup.sdk.xap.models.LocationGeoLocation
import com.expediagroup.sdk.xap.models.Neighborhood
import com.fasterxml.jackson.annotation.JsonProperty

/**
*
 * @param address
 * @param geoLocation
 * @param neighborhood
*/
data class HotelLocation(
    @JsonProperty("Address")
    val address: Address? = null,
    @JsonProperty("GeoLocation")
    val geoLocation: LocationGeoLocation? = null,
    @JsonProperty("Neighborhood")
    val neighborhood: Neighborhood? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var address: Address? = null,
        private var geoLocation: LocationGeoLocation? = null,
        private var neighborhood: Neighborhood? = null,
    ) {
        fun address(address: Address?) = apply { this.address = address }

        fun geoLocation(geoLocation: LocationGeoLocation?) = apply { this.geoLocation = geoLocation }

        fun neighborhood(neighborhood: Neighborhood?) = apply { this.neighborhood = neighborhood }

        fun build(): HotelLocation {
            val instance =
                HotelLocation(
                    address = address,
                    geoLocation = geoLocation,
                    neighborhood = neighborhood,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            address = address,
            geoLocation = geoLocation,
            neighborhood = neighborhood,
        )
}
