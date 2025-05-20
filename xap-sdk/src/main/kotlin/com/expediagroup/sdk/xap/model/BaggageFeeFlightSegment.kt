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

import com.expediagroup.sdk.xap.model.Airport
import com.expediagroup.sdk.xap.model.FlightsV1Link
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for information of Segment.
 * @param airlineCode Specifies the 2 letter IATA airline code of the most significant carrier for the flight. In the case of flights with multiple airlines involves this is the airline who will be charging for the baggage.
 * @param departureAirport
 * @param arrivalAirport
 * @param airlineName Specifies the name of the airline. Where possible this is localized to the LangID supplied in the request.
 * @param cabinClass Cabin class name of airline.
 * @param links Links to airline's baggage policies. Where possible this is localized to the LangID supplied in the request. It is possible that special characters will be present in the URL
 */
data class BaggageFeeFlightSegment(
    // Specifies the 2 letter IATA airline code of the most significant carrier for the flight. In the case of flights with multiple airlines involves this is the airline who will be charging for the baggage.
    @JsonProperty("AirlineCode")
    val airlineCode: kotlin.String,
    @JsonProperty("DepartureAirport")
    val departureAirport: Airport,
    @JsonProperty("ArrivalAirport")
    val arrivalAirport: Airport,
    // Specifies the name of the airline. Where possible this is localized to the LangID supplied in the request.
    @JsonProperty("AirlineName")
    val airlineName: kotlin.String? = null,
    // Cabin class name of airline.
    @JsonProperty("CabinClass")
    val cabinClass: kotlin.String? = null,
    // Links to airline's baggage policies. Where possible this is localized to the LangID supplied in the request. It is possible that special characters will be present in the URL
    @JsonProperty("Links")
    val links: kotlin.collections.Map<kotlin.String, FlightsV1Link>? = null,
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
        private var airlineName: kotlin.String? = null,
        private var cabinClass: kotlin.String? = null,
        private var links: kotlin.collections.Map<kotlin.String, FlightsV1Link>? = null,
    ) {
        fun airlineCode(airlineCode: kotlin.String) = apply { this.airlineCode = airlineCode }

        fun departureAirport(departureAirport: Airport) = apply { this.departureAirport = departureAirport }

        fun arrivalAirport(arrivalAirport: Airport) = apply { this.arrivalAirport = arrivalAirport }

        fun airlineName(airlineName: kotlin.String?) = apply { this.airlineName = airlineName }

        fun cabinClass(cabinClass: kotlin.String?) = apply { this.cabinClass = cabinClass }

        fun links(links: kotlin.collections.Map<kotlin.String, FlightsV1Link>?) = apply { this.links = links }

        fun build(): BaggageFeeFlightSegment {
            val instance =
                BaggageFeeFlightSegment(
                    airlineCode = airlineCode!!,
                    departureAirport = departureAirport!!,
                    arrivalAirport = arrivalAirport!!,
                    airlineName = airlineName,
                    cabinClass = cabinClass,
                    links = links,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            airlineCode = airlineCode!!,
            departureAirport = departureAirport!!,
            arrivalAirport = arrivalAirport!!,
            airlineName = airlineName,
            cabinClass = cabinClass,
            links = links,
        )
}
