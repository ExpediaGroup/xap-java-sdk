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
 *
 * @param accept The Accept request header for API queries only.  Note: this value will only be returned if the link is an API query, as website URLs do not require an `Accept` header.
 * @param method Method of request.
 * @param href The URL of the destination web page or API query.
 */
data class LodgingLink(
    // The Accept request header for API queries only.  Note: this value will only be returned if the link is an API query, as website URLs do not require an `Accept` header.
    @JsonProperty("Accept")
    val accept: kotlin.String? = null,
    // Method of request.
    @JsonProperty("Method")
    val method: kotlin.String? = null,
    // The URL of the destination web page or API query.
    @JsonProperty("Href")
    val href: kotlin.String? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var accept: kotlin.String? = null,
        private var method: kotlin.String? = null,
        private var href: kotlin.String? = null,
    ) {
        fun accept(accept: kotlin.String?) = apply { this.accept = accept }

        fun method(method: kotlin.String?) = apply { this.method = method }

        fun href(href: kotlin.String?) = apply { this.href = href }

        fun build(): LodgingLink {
            val instance =
                LodgingLink(
                    accept = accept,
                    method = method,
                    href = href,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            accept = accept,
            method = method,
            href = href,
        )
}
