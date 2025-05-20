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

import com.expediagroup.sdk.xap.model.FlightLinksResponseLinksInnerApiDetails
import com.expediagroup.sdk.xap.model.FlightLinksResponseLinksInnerWebDetails
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param flightId
 * @param webDetails
 * @param apiDetails
 */
data class FlightLinksResponseLinksInner(
    @JsonProperty("FlightId")
    val flightId: kotlin.String,
    @JsonProperty("WebDetails")
    val webDetails: FlightLinksResponseLinksInnerWebDetails? = null,
    @JsonProperty("ApiDetails")
    val apiDetails: FlightLinksResponseLinksInnerApiDetails? = null,
) {
    init {
        require(flightId != null) { "flightId must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var flightId: kotlin.String? = null,
        private var webDetails: FlightLinksResponseLinksInnerWebDetails? = null,
        private var apiDetails: FlightLinksResponseLinksInnerApiDetails? = null,
    ) {
        fun flightId(flightId: kotlin.String) = apply { this.flightId = flightId }

        fun webDetails(webDetails: FlightLinksResponseLinksInnerWebDetails?) = apply { this.webDetails = webDetails }

        fun apiDetails(apiDetails: FlightLinksResponseLinksInnerApiDetails?) = apply { this.apiDetails = apiDetails }

        fun build(): FlightLinksResponseLinksInner {
            val instance =
                FlightLinksResponseLinksInner(
                    flightId = flightId!!,
                    webDetails = webDetails,
                    apiDetails = apiDetails,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            flightId = flightId!!,
            webDetails = webDetails,
            apiDetails = apiDetails,
        )
}
