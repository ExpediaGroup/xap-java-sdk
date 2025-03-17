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
* A map of links to other Car APIs. possible link name: ApiBooking
    * @param href HATEOAS URL to fetch details.
    * @param accept Accept header.
    * @param method HTTP method to connect.
*/
data class CarsLink(
            /* HATEOAS URL to fetch details. */
@JsonProperty("Href")
val href:
    kotlin.String
,

            /* Accept header. */
@JsonProperty("Accept")
val accept: kotlin.String? = null,

            /* HTTP method to connect. */
@JsonProperty("Method")
val method: kotlin.String? = null
) {
    


    init {
                require(href != null) { "href must not be null" }






















    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var href: kotlin.String? = null,
                private var accept: kotlin.String? = null,
                private var method: kotlin.String? = null
        ) {
                fun href(href: kotlin.String) = apply { this.href = href }
                fun accept(accept: kotlin.String?) = apply { this.accept = accept }
                fun method(method: kotlin.String?) = apply { this.method = method }

    fun build(): CarsLink {
    val instance = CarsLink(
            href = href!!,
            accept = accept,
            method = method
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            href = href!!,
            accept = accept,
            method = method
    )
}
