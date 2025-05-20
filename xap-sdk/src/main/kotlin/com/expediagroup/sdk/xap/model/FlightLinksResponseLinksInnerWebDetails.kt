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
 *
 * @param method
 * @param href
 */
data class FlightLinksResponseLinksInnerWebDetails(
    @JsonProperty("Method")
    val method: kotlin.String,
    @JsonProperty("Href")
    val href: kotlin.String,
) {
    init {
        require(method != null) { "method must not be null" }

        require(href != null) { "href must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var method: kotlin.String? = null,
        private var href: kotlin.String? = null,
    ) {
        fun method(method: kotlin.String) = apply { this.method = method }

        fun href(href: kotlin.String) = apply { this.href = href }

        fun build(): FlightLinksResponseLinksInnerWebDetails {
            val instance =
                FlightLinksResponseLinksInnerWebDetails(
                    method = method!!,
                    href = href!!,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            method = method!!,
            href = href!!,
        )
}
