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

import com.expediagroup.sdk.xap.models.Flight
import com.expediagroup.sdk.xap.models.PassengerDetails
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param flights
 * @param passengers
 * @param links
 */
data class FlightLinksRequest(
    @JsonProperty("Flights")
    val flights: kotlin.collections
        .List<
            Flight,
        >,
    @JsonProperty("Passengers")
    val passengers: kotlin.collections
        .List<
            PassengerDetails,
        >,
    @JsonProperty("Links")
    val links: kotlin.collections.List<kotlin.String>? = null,
) {
    init {
        require(flights != null) { "flights must not be null" }

        require(passengers != null) { "passengers must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var flights: kotlin.collections.List<Flight>? = null,
        private var passengers: kotlin.collections.List<PassengerDetails>? = null,
        private var links: kotlin.collections.List<kotlin.String>? = null,
    ) {
        fun flights(flights: kotlin.collections.List<Flight>) = apply { this.flights = flights }

        fun passengers(passengers: kotlin.collections.List<PassengerDetails>) = apply { this.passengers = passengers }

        fun links(links: kotlin.collections.List<kotlin.String>?) = apply { this.links = links }

        fun build(): FlightLinksRequest {
            val instance =
                FlightLinksRequest(
                    flights = flights!!,
                    passengers = passengers!!,
                    links = links,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            flights = flights!!,
            passengers = passengers!!,
            links = links,
        )
}
