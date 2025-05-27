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

import com.expediagroup.sdk.core.common.getOrThrow
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for list of Information about each search locations
 * @param code Three-letter IATA airport code for departure location
 * @param name Name of departure airport
 * @param city City where departure airport resides
 * @param province Province or State where departure airport resides
 * @param country Country where departure airport resides
 * @param latitude Latitude where departure airport resides
 * @param longitude Longitude where departure airport resides
 */
@ConsistentCopyVisibility data class FlightsV3Airport private constructor(
    /* Three-letter IATA airport code for departure location */
    @JsonProperty("Code")
    val code: kotlin.String,

    /* Name of departure airport */
    @JsonProperty("Name")
    val name: kotlin.String? = null,

    /* City where departure airport resides */
    @JsonProperty("City")
    val city: kotlin.String? = null,

    /* Province or State where departure airport resides */
    @JsonProperty("Province")
    val province: kotlin.String? = null,

    /* Country where departure airport resides */
    @JsonProperty("Country")
    val country: kotlin.String? = null,

    /* Latitude where departure airport resides */
    @JsonProperty("Latitude")
    val latitude: kotlin.String? = null,

    /* Longitude where departure airport resides */
    @JsonProperty("Longitude")
    val longitude: kotlin.String? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: kotlin.String? = null,
        private var name: kotlin.String? = null,
        private var city: kotlin.String? = null,
        private var province: kotlin.String? = null,
        private var country: kotlin.String? = null,
        private var latitude: kotlin.String? = null,
        private var longitude: kotlin.String? = null,
    ) {
        fun code(code: kotlin.String) = apply { this.code = code }

        fun name(name: kotlin.String?) = apply { this.name = name }

        fun city(city: kotlin.String?) = apply { this.city = city }

        fun province(province: kotlin.String?) = apply { this.province = province }

        fun country(country: kotlin.String?) = apply { this.country = country }

        fun latitude(latitude: kotlin.String?) = apply { this.latitude = latitude }

        fun longitude(longitude: kotlin.String?) = apply { this.longitude = longitude }

        fun build(): FlightsV3Airport {
            val code = this.code.getOrThrow {
                IllegalArgumentException("code must not be null")
            }

            val instance = FlightsV3Airport(
                code = code,
                name = name,
                city = city,
                province = province,
                country = country,
                latitude = latitude,
                longitude = longitude,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        code = code,
        name = name,
        city = city,
        province = province,
        country = country,
        latitude = latitude,
        longitude = longitude,
    )
}
