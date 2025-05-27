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
 * Container for disambiguation country information
 * @param name country name
 * @param isoCode2 2-letter code for the country
 * @param isoCode3 3-letter code for the country
 */
@ConsistentCopyVisibility data class Country private constructor(
    /* country name */
    @JsonProperty("Name")
    val name: kotlin.String? = null,

    /* 2-letter code for the country */
    @JsonProperty("IsoCode2")
    val isoCode2: kotlin.String? = null,

    /* 3-letter code for the country */
    @JsonProperty("IsoCode3")
    val isoCode3: kotlin.String? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var name: kotlin.String? = null,
        private var isoCode2: kotlin.String? = null,
        private var isoCode3: kotlin.String? = null,
    ) {
        fun name(name: kotlin.String?) = apply { this.name = name }

        fun isoCode2(isoCode2: kotlin.String?) = apply { this.isoCode2 = isoCode2 }

        fun isoCode3(isoCode3: kotlin.String?) = apply { this.isoCode3 = isoCode3 }

        fun build(): Country {
            val instance = Country(
                name = name,
                isoCode2 = isoCode2,
                isoCode3 = isoCode3,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        name = name,
        isoCode2 = isoCode2,
        isoCode3 = isoCode3,
    )
}
