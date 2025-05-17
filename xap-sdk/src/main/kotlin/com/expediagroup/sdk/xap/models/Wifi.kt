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

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for WiFi amenities available in this flight leg
 * @param available Indication of the availability of WIFI amenity.
 */
data class Wifi(
    // Indication of the availability of WIFI amenity.
    @JsonProperty("Available")
    val available: Wifi.Available? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var available: Wifi.Available? = null,
    ) {
        fun available(available: Wifi.Available?) = apply { this.available = available }

        fun build(): Wifi {
            val instance =
                Wifi(
                    available = available,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            available = available,
        )

    /**
     * Indication of the availability of WIFI amenity.
     * Values: YES,NO,UNKNOWN
     */
    enum class Available(
        val value: kotlin.String,
    ) {
        @JsonProperty("YES")
        YES("YES"),

        @JsonProperty("NO")
        NO("NO"),

        @JsonProperty("UNKNOWN")
        UNKNOWN("UNKNOWN"),
    }
}
