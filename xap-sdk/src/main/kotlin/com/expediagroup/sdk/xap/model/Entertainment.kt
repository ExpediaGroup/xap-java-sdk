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

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for entertainment amenities available in this flight leg
 * @param available Indication of whether this amenity is available.
 * @param types Type of entertainment
 */
@ConsistentCopyVisibility data class Entertainment private constructor(
    /* Indication of whether this amenity is available. */
    @JsonProperty("Available")
    val available: Entertainment.Available? = null,

    /* Type of entertainment */
    @JsonProperty("Types")
    val types: kotlin.collections.List<Entertainment.Types>? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var available: Entertainment.Available? = null,
        private var types: kotlin.collections.List<Entertainment.Types>? = null,
    ) {
        fun available(available: Entertainment.Available?) = apply { this.available = available }

        fun types(types: kotlin.collections.List<Entertainment.Types>?) = apply { this.types = types }

        fun build(): Entertainment {
            val instance = Entertainment(
                available = available,
                types = types,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        available = available,
        types = types,
    )

    /**
     * Indication of whether this amenity is available.
     * Values: YES,NO,UNKNOWN
     */
    enum class Available(val value: kotlin.String) {
        @JsonProperty("YES")
        YES("YES"),

        @JsonProperty("NO")
        NO("NO"),

        @JsonProperty("UNKNOWN")
        UNKNOWN("UNKNOWN"),
    }

    /**
     * Type of entertainment
     * Values: LIVE_TV,ON_DEMAND,STREAMING
     */
    enum class Types(val value: kotlin.String) {
        @JsonProperty("LIVE_TV")
        LIVE_TV("LIVE_TV"),

        @JsonProperty("ON_DEMAND")
        ON_DEMAND("ON_DEMAND"),

        @JsonProperty("STREAMING")
        STREAMING("STREAMING"),
    }
}
