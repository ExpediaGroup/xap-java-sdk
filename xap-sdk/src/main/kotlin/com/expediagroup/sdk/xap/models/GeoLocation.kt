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


import com.fasterxml.jackson.annotation.JsonProperty

/**
* Container for Geo location.
    * @param latitude Latitude of the location.
    * @param longitude Longitude of the location.
    * @param obfuscated 
*/
data class GeoLocation(
            /* Latitude of the location. */
@JsonProperty("Latitude")
val latitude: kotlin.String? = null,

            /* Longitude of the location. */
@JsonProperty("Longitude")
val longitude: kotlin.String? = null,

        @JsonProperty("Obfuscated")
val obfuscated: kotlin.Boolean? = null
) {
    


    init {
        





















    }

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

    fun build(): GeoLocation {
    val instance = GeoLocation(
            latitude = latitude,
            longitude = longitude,
            obfuscated = obfuscated
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            latitude = latitude,
            longitude = longitude,
            obfuscated = obfuscated
    )
}
