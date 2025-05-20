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

import com.expediagroup.sdk.xap.model.FlightsV3GeoLocation
import com.expediagroup.sdk.xap.model.GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInnerLocationCountry
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for list of possible locations that could be used to disambiguate the query.
 * @param name Location Name
 * @param type The type of location code (MULTICITY | METROCODE).
 * @param id Expedia Region ID of the specified airport.
 * @param code Location Code
 * @param address Street address of the location (if available)
 * @param geoLocation
 * @param country
 */
data class GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInnerLocation(
    // Location Name
    @JsonProperty("Name")
    val name: kotlin.String,
    // The type of location code (MULTICITY | METROCODE).
    @JsonProperty("Type")
    val type: kotlin.String? = null,
    // Expedia Region ID of the specified airport.
    @JsonProperty("Id")
    val id: kotlin.String? = null,
    // Location Code
    @JsonProperty("Code")
    val code: kotlin.String? = null,
    // Street address of the location (if available)
    @JsonProperty("Address")
    val address: kotlin.String? = null,
    @JsonProperty("GeoLocation")
    val geoLocation: FlightsV3GeoLocation? = null,
    @JsonProperty("Country")
    val country: GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInnerLocationCountry? = null,
) {
    init {
        require(name != null) { "name must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var name: kotlin.String? = null,
        private var type: kotlin.String? = null,
        private var id: kotlin.String? = null,
        private var code: kotlin.String? = null,
        private var address: kotlin.String? = null,
        private var geoLocation: FlightsV3GeoLocation? = null,
        private var country: GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInnerLocationCountry? = null,
    ) {
        fun name(name: kotlin.String) = apply { this.name = name }

        fun type(type: kotlin.String?) = apply { this.type = type }

        fun id(id: kotlin.String?) = apply { this.id = id }

        fun code(code: kotlin.String?) = apply { this.code = code }

        fun address(address: kotlin.String?) = apply { this.address = address }

        fun geoLocation(geoLocation: FlightsV3GeoLocation?) = apply { this.geoLocation = geoLocation }

        fun country(country: GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInnerLocationCountry?) = apply { this.country = country }

        fun build(): GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInnerLocation {
            val instance =
                GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInnerLocation(
                    name = name!!,
                    type = type,
                    id = id,
                    code = code,
                    address = address,
                    geoLocation = geoLocation,
                    country = country,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            name = name!!,
            type = type,
            id = id,
            code = code,
            address = address,
            geoLocation = geoLocation,
            country = country,
        )
}
