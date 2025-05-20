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
 * @param code 3-letter code for the country
 */
data class ActivitiesCountry(
    // country name
    @JsonProperty("Name")
    val name: kotlin.String,
    // 2-letter code for the country
    @JsonProperty("IsoCode2")
    val isoCode2: kotlin.String,
    // 3-letter code for the country
    @JsonProperty("IsoCode3")
    val isoCode3: kotlin.String,
    // 3-letter code for the country
    @JsonProperty("Code")
    val code: kotlin.String? = null,
) {
    init {
        require(name != null) { "name must not be null" }

        require(isoCode2 != null) { "isoCode2 must not be null" }

        require(isoCode3 != null) { "isoCode3 must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var name: kotlin.String? = null,
        private var isoCode2: kotlin.String? = null,
        private var isoCode3: kotlin.String? = null,
        private var code: kotlin.String? = null,
    ) {
        fun name(name: kotlin.String) = apply { this.name = name }

        fun isoCode2(isoCode2: kotlin.String) = apply { this.isoCode2 = isoCode2 }

        fun isoCode3(isoCode3: kotlin.String) = apply { this.isoCode3 = isoCode3 }

        fun code(code: kotlin.String?) = apply { this.code = code }

        fun build(): ActivitiesCountry {
            val instance =
                ActivitiesCountry(
                    name = name!!,
                    isoCode2 = isoCode2!!,
                    isoCode3 = isoCode3!!,
                    code = code,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            name = name!!,
            isoCode2 = isoCode2!!,
            isoCode3 = isoCode3!!,
            code = code,
        )
}
