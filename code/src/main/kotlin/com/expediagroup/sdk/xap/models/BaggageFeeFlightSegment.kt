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

import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.Airport
import com.expediagroup.sdk.xap.models.FlightsV1Link
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

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
    @field:NotNull
    @field:Valid
    val airlineCode: kotlin.String,
    @JsonProperty("DepartureAirport")
    @field:NotNull
    @field:Valid
    val departureAirport: Airport,
    @JsonProperty("ArrivalAirport")
    @field:NotNull
    @field:Valid
    val arrivalAirport: Airport,
    // Specifies the name of the airline. Where possible this is localized to the LangID supplied in the request.
    @JsonProperty("AirlineName")
    @field:Valid
    val airlineName: kotlin.String? = null,
    // Cabin class name of airline.
    @JsonProperty("CabinClass")
    @field:Valid
    val cabinClass: kotlin.String? = null,
    // Links to airline's baggage policies. Where possible this is localized to the LangID supplied in the request. It is possible that special characters will be present in the URL
    @JsonProperty("Links")
    @field:Valid
    val links: kotlin.collections.Map<kotlin.String, FlightsV1Link>? = null
) {
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
        private var links: kotlin.collections.Map<kotlin.String, FlightsV1Link>? = null
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
                    links = links
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: BaggageFeeFlightSegment) {
            val validator =
                Validation
                    .byDefaultProvider()
                    .configure()
                    .messageInterpolator(ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .validator

            val violations = validator.validate(instance)

            if (violations.isNotEmpty()) {
                throw PropertyConstraintViolationException(
                    constraintViolations = violations.map { "${it.propertyPath}: ${it.message}" }
                )
            }
        }
    }

    fun toBuilder() =
        Builder(
            airlineCode = airlineCode!!,
            departureAirport = departureAirport!!,
            arrivalAirport = arrivalAirport!!,
            airlineName = airlineName,
            cabinClass = cabinClass,
            links = links
        )
}
