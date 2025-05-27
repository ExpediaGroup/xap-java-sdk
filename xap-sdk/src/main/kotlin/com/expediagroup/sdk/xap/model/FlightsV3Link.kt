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
 * Container for deeplink URL information.
 * @param method HTTP method to connect.
 * @param href HATEOAS URL to fetch details.
 * @param accept Accept header.
 */
@ConsistentCopyVisibility data class FlightsV3Link private constructor(
    /* HTTP method to connect. */
    @JsonProperty("Method")
    val method: kotlin.String,

    /* HATEOAS URL to fetch details. */
    @JsonProperty("Href")
    val href: kotlin.String,

    /* Accept header. */
    @JsonProperty("Accept")
    val accept: kotlin.String? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var method: kotlin.String? = null,
        private var href: kotlin.String? = null,
        private var accept: kotlin.String? = null,
    ) {
        fun method(method: kotlin.String) = apply { this.method = method }

        fun href(href: kotlin.String) = apply { this.href = href }

        fun accept(accept: kotlin.String?) = apply { this.accept = accept }

        fun build(): FlightsV3Link {
            val method = this.method.getOrThrow {
                IllegalArgumentException("method must not be null")
            }

            val href = this.href.getOrThrow {
                IllegalArgumentException("href must not be null")
            }

            val instance = FlightsV3Link(
                method = method,
                href = href,
                accept = accept,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        method = method,
        href = href,
        accept = accept,
    )
}
