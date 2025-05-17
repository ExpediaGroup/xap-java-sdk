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

import com.expediagroup.sdk.xap.models.Airport
import com.expediagroup.sdk.xap.models.FareRule
import com.expediagroup.sdk.xap.models.FlightsV1Link
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for flight segment information.
 * @param airlineCode Specifies the 2 letter IATA airline code of the Most Significant Carrier for the flight. In the case of flights with multiple airlines involves this is the airline who will be charging for the baggage.
 * @param departureAirport
 * @param arrivalAirport
 * @param links Contains the deeplink URLs of api services and websites
 * @param fareRules Contains the list of fare rule details
 */
data class FlightFareRuleItemFlightSegment(
    // Specifies the 2 letter IATA airline code of the Most Significant Carrier for the flight. In the case of flights with multiple airlines involves this is the airline who will be charging for the baggage.
    @JsonProperty("AirlineCode")
    val airlineCode: kotlin.String,
    @JsonProperty("DepartureAirport")
    val departureAirport: Airport,
    @JsonProperty("ArrivalAirport")
    val arrivalAirport: Airport,
    // Contains the deeplink URLs of api services and websites
    @JsonProperty("Links")
    val links: kotlin.collections.Map<kotlin.String, FlightsV1Link>? = null,
    // Contains the list of fare rule details
    @JsonProperty("FareRules")
    val fareRules: kotlin.collections.List<FareRule>? = null,
) {
    init {
        require(airlineCode != null) { "airlineCode must not be null" }

        require(departureAirport != null) { "departureAirport must not be null" }

        require(arrivalAirport != null) { "arrivalAirport must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var airlineCode: kotlin.String? = null,
        private var departureAirport: Airport? = null,
        private var arrivalAirport: Airport? = null,
        private var links: kotlin.collections.Map<kotlin.String, FlightsV1Link>? = null,
        private var fareRules: kotlin.collections.List<FareRule>? = null,
    ) {
        fun airlineCode(airlineCode: kotlin.String) = apply { this.airlineCode = airlineCode }

        fun departureAirport(departureAirport: Airport) = apply { this.departureAirport = departureAirport }

        fun arrivalAirport(arrivalAirport: Airport) = apply { this.arrivalAirport = arrivalAirport }

        fun links(links: kotlin.collections.Map<kotlin.String, FlightsV1Link>?) = apply { this.links = links }

        fun fareRules(fareRules: kotlin.collections.List<FareRule>?) = apply { this.fareRules = fareRules }

        fun build(): FlightFareRuleItemFlightSegment {
            val instance =
                FlightFareRuleItemFlightSegment(
                    airlineCode = airlineCode!!,
                    departureAirport = departureAirport!!,
                    arrivalAirport = arrivalAirport!!,
                    links = links,
                    fareRules = fareRules,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            airlineCode = airlineCode!!,
            departureAirport = departureAirport!!,
            arrivalAirport = arrivalAirport!!,
            links = links,
            fareRules = fareRules,
        )
}
