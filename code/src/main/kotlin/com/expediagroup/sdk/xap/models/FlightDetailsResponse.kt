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
import com.expediagroup.sdk.xap.models.CrossSell
import com.expediagroup.sdk.xap.models.FlightDetailsOffer
import com.expediagroup.sdk.xap.models.FlightDetailsWarning
import com.expediagroup.sdk.xap.models.Lounge
import com.expediagroup.sdk.xap.models.Segment
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 *
 * @param offer
 * @param lounges
 * @param transactionId Unique identifier for the transaction.
 * @param warnings Container for Warning Codes.
 * @param segments Container for flight segments.
 * @param allOffers Contains list of all the available alternate fare upsell/downsell offers.
 * @param validFormsOfPayment Container for fees that are charged for using certain payment methods.
 * @param crossSell
 */
data class FlightDetailsResponse(
    @JsonProperty("Offer")
    @field:NotNull
    @field:Valid
    val offer: FlightDetailsOffer,
    @JsonProperty("Lounges")
    @field:NotNull
    @field:Valid
    val lounges: kotlin.collections.Map<kotlin.String, kotlin.collections.List<Lounge>>,
    // Unique identifier for the transaction.
    @JsonProperty("TransactionId")
    @field:NotNull
    @field:Valid
    val transactionId: kotlin.String,
    // Container for Warning Codes.
    @JsonProperty("Warnings")
    @field:Valid
    val warnings: kotlin.collections.List<FlightDetailsWarning>? = null,
    // Container for flight segments.
    @JsonProperty("Segments")
    @field:Valid
    val segments: kotlin.collections.List<Segment>? = null,
    // Contains list of all the available alternate fare upsell/downsell offers.
    @JsonProperty("AllOffers")
    @field:Valid
    val allOffers: kotlin.collections.List<FlightDetailsOffer>? = null,
    // Container for fees that are charged for using certain payment methods.
    @JsonProperty("ValidFormsOfPayment")
    @field:Valid
    val validFormsOfPayment: kotlin.collections.List<AirValidFormsOfPayment>? = null,
    @JsonProperty("CrossSell")
    @field:Valid
    val crossSell: CrossSell? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var offer: FlightDetailsOffer? = null,
        private var lounges: kotlin.collections.Map<kotlin.String, kotlin.collections.List<Lounge>>? = null,
        private var transactionId: kotlin.String? = null,
        private var warnings: kotlin.collections.List<FlightDetailsWarning>? = null,
        private var segments: kotlin.collections.List<Segment>? = null,
        private var allOffers: kotlin.collections.List<FlightDetailsOffer>? = null,
        private var validFormsOfPayment: kotlin.collections.List<AirValidFormsOfPayment>? = null,
        private var crossSell: CrossSell? = null
    ) {
        fun offer(offer: FlightDetailsOffer) = apply { this.offer = offer }

        fun lounges(lounges: kotlin.collections.Map<kotlin.String, kotlin.collections.List<Lounge>>) = apply { this.lounges = lounges }

        fun transactionId(transactionId: kotlin.String) = apply { this.transactionId = transactionId }

        fun warnings(warnings: kotlin.collections.List<FlightDetailsWarning>?) = apply { this.warnings = warnings }

        fun segments(segments: kotlin.collections.List<Segment>?) = apply { this.segments = segments }

        fun allOffers(allOffers: kotlin.collections.List<FlightDetailsOffer>?) = apply { this.allOffers = allOffers }

        fun validFormsOfPayment(validFormsOfPayment: kotlin.collections.List<AirValidFormsOfPayment>?) = apply { this.validFormsOfPayment = validFormsOfPayment }

        fun crossSell(crossSell: CrossSell?) = apply { this.crossSell = crossSell }

        fun build(): FlightDetailsResponse {
            val instance =
                FlightDetailsResponse(
                    offer = offer!!,
                    lounges = lounges!!,
                    transactionId = transactionId!!,
                    warnings = warnings,
                    segments = segments,
                    allOffers = allOffers,
                    validFormsOfPayment = validFormsOfPayment,
                    crossSell = crossSell
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightDetailsResponse) {
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
            offer = offer!!,
            lounges = lounges!!,
            transactionId = transactionId!!,
            warnings = warnings,
            segments = segments,
            allOffers = allOffers,
            validFormsOfPayment = validFormsOfPayment,
            crossSell = crossSell
        )
}
