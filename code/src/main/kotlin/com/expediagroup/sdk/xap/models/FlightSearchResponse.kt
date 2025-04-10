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
import com.expediagroup.sdk.xap.models.AirValidFormsOfPayment
import com.expediagroup.sdk.xap.models.FlightsV3Airport
import com.expediagroup.sdk.xap.models.FlightsV3Offer
import com.expediagroup.sdk.xap.models.FlightsV3Warning
import com.expediagroup.sdk.xap.models.Insights
import com.expediagroup.sdk.xap.models.Lounge
import com.expediagroup.sdk.xap.models.Segment
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 *
 * @param offers Container for list of air offers. An offer gives total trip details including flight and pricing information.
 * @param searchCities Container for list of Information about each search locations
 * @param transactionId Unique Id to identify one individual API response.
 * @param insights
 * @param warnings Container for warning messages.
 * @param segments Container of information about each flight offer (Less information shown if the offer is and opaque flight offer) Flights (the complete journey to your final destination by air) are made up of:  Segments (the trip from one stopping place to another) are made up of:  Legs (take off at one airport and land at another)
 * @param validFormsOfPayment Container for fees that are charged for using certain payment methods. Since the method of payment is not known until time of booking, these fees are returned separately and not included in the total price
 * @param lounges List of lounges
 */
data class FlightSearchResponse(
    // Container for list of air offers. An offer gives total trip details including flight and pricing information.
    @JsonProperty("Offers")
    @field:NotNull
    @field:Valid
    val offers: kotlin.collections
        .List<
            FlightsV3Offer
        >,
    // Container for list of Information about each search locations
    @JsonProperty("SearchCities")
    @field:NotNull
    @field:Valid
    val searchCities: kotlin.collections
        .List<
            FlightsV3Airport
        >,
    // Unique Id to identify one individual API response.
    @JsonProperty("TransactionId")
    @field:NotNull
    @field:Valid
    val transactionId: kotlin.String,
    @JsonProperty("Insights")
    @field:Valid
    val insights: Insights? = null,
    // Container for warning messages.
    @JsonProperty("Warnings")
    @field:Valid
    val warnings: kotlin.collections.List<FlightsV3Warning>? = null,
    // Container of information about each flight offer (Less information shown if the offer is and opaque flight offer) Flights (the complete journey to your final destination by air) are made up of:  Segments (the trip from one stopping place to another) are made up of:  Legs (take off at one airport and land at another)
    @JsonProperty("Segments")
    @field:Valid
    val segments: kotlin.collections.List<Segment>? = null,
    // Container for fees that are charged for using certain payment methods. Since the method of payment is not known until time of booking, these fees are returned separately and not included in the total price
    @JsonProperty("ValidFormsOfPayment")
    @field:Valid
    val validFormsOfPayment: kotlin.collections.Map<kotlin.String, kotlin.collections.List<AirValidFormsOfPayment>>? = null,
    // List of lounges
    @JsonProperty("Lounges")
    @field:Valid
    val lounges: kotlin.collections.Map<kotlin.String, kotlin.collections.List<Lounge>>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var offers: kotlin.collections.List<FlightsV3Offer>? = null,
        private var searchCities: kotlin.collections.List<FlightsV3Airport>? = null,
        private var transactionId: kotlin.String? = null,
        private var insights: Insights? = null,
        private var warnings: kotlin.collections.List<FlightsV3Warning>? = null,
        private var segments: kotlin.collections.List<Segment>? = null,
        private var validFormsOfPayment: kotlin.collections.Map<kotlin.String, kotlin.collections.List<AirValidFormsOfPayment>>? = null,
        private var lounges: kotlin.collections.Map<kotlin.String, kotlin.collections.List<Lounge>>? = null
    ) {
        fun offers(offers: kotlin.collections.List<FlightsV3Offer>) = apply { this.offers = offers }

        fun searchCities(searchCities: kotlin.collections.List<FlightsV3Airport>) = apply { this.searchCities = searchCities }

        fun transactionId(transactionId: kotlin.String) = apply { this.transactionId = transactionId }

        fun insights(insights: Insights?) = apply { this.insights = insights }

        fun warnings(warnings: kotlin.collections.List<FlightsV3Warning>?) = apply { this.warnings = warnings }

        fun segments(segments: kotlin.collections.List<Segment>?) = apply { this.segments = segments }

        fun validFormsOfPayment(validFormsOfPayment: kotlin.collections.Map<kotlin.String, kotlin.collections.List<AirValidFormsOfPayment>>?) =
            apply {
                this.validFormsOfPayment =
                    validFormsOfPayment
            }

        fun lounges(lounges: kotlin.collections.Map<kotlin.String, kotlin.collections.List<Lounge>>?) = apply { this.lounges = lounges }

        fun build(): FlightSearchResponse {
            val instance =
                FlightSearchResponse(
                    offers = offers!!,
                    searchCities = searchCities!!,
                    transactionId = transactionId!!,
                    insights = insights,
                    warnings = warnings,
                    segments = segments,
                    validFormsOfPayment = validFormsOfPayment,
                    lounges = lounges
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightSearchResponse) {
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
            offers = offers!!,
            searchCities = searchCities!!,
            transactionId = transactionId!!,
            insights = insights,
            warnings = warnings,
            segments = segments,
            validFormsOfPayment = validFormsOfPayment,
            lounges = lounges
        )
}
