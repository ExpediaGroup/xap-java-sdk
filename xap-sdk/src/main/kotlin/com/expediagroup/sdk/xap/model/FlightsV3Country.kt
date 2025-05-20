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
 * @param code 3-letter code for the country
 */
data class FlightsV3Country(
    // country name
    @JsonProperty("Name")
    val name: kotlin.String,
    // 3-letter code for the country
    @JsonProperty("Code")
    val code: kotlin.String? = null,
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
        private var code: kotlin.String? = null,
    ) {
        fun name(name: kotlin.String) = apply { this.name = name }

        fun code(code: kotlin.String?) = apply { this.code = code }

        fun build(): FlightsV3Country {
            val instance =
                FlightsV3Country(
                    name = name!!,
                    code = code,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            name = name!!,
            code = code,
        )
}
