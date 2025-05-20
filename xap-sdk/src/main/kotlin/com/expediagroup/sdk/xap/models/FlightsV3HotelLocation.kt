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
package com.expediagroup.sdk.xap.models

import com.expediagroup.sdk.xap.models.FlightsV3Address
import com.expediagroup.sdk.xap.models.FlightsV3GeoLocation
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Hotel address information
 * @param address
 * @param pointOfInterest
 * @param geoLocation
 */
data class FlightsV3HotelLocation(
    @JsonProperty("Address")
    val address: FlightsV3Address,
    @JsonProperty("PointOfInterest")
    val pointOfInterest: kotlin.String,
    @JsonProperty("GeoLocation")
    val geoLocation: FlightsV3GeoLocation,
) {
    init {
        require(address != null) { "address must not be null" }

        require(pointOfInterest != null) { "pointOfInterest must not be null" }

        require(geoLocation != null) { "geoLocation must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var address: FlightsV3Address? = null,
        private var pointOfInterest: kotlin.String? = null,
        private var geoLocation: FlightsV3GeoLocation? = null,
    ) {
        fun address(address: FlightsV3Address) = apply { this.address = address }

        fun pointOfInterest(pointOfInterest: kotlin.String) = apply { this.pointOfInterest = pointOfInterest }

        fun geoLocation(geoLocation: FlightsV3GeoLocation) = apply { this.geoLocation = geoLocation }

        fun build(): FlightsV3HotelLocation {
            val instance =
                FlightsV3HotelLocation(
                    address = address!!,
                    pointOfInterest = pointOfInterest!!,
                    geoLocation = geoLocation!!,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            address = address!!,
            pointOfInterest = pointOfInterest!!,
            geoLocation = geoLocation!!,
        )
}
