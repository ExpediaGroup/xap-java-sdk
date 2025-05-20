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

import com.expediagroup.sdk.xap.model.AirValidFormsOfPayment
import com.expediagroup.sdk.xap.model.CrossSell
import com.expediagroup.sdk.xap.model.FlightDetailsOffer
import com.expediagroup.sdk.xap.model.FlightDetailsWarning
import com.expediagroup.sdk.xap.model.Lounge
import com.expediagroup.sdk.xap.model.Segment
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param offer
 * @param transactionId Unique identifier for the transaction.
 * @param warnings Container for Warning Codes.
 * @param segments Container for flight segments.
 * @param allOffers Contains list of all the available alternate fare upsell/downsell offers.
 * @param validFormsOfPayment Container for fees that are charged for using certain payment methods.
 * @param lounges
 * @param crossSell
 */
data class FlightDetailsResponse(
    @JsonProperty("Offer")
    val offer: FlightDetailsOffer,
    // Unique identifier for the transaction.
    @JsonProperty("TransactionId")
    val transactionId: kotlin.String,
    // Container for Warning Codes.
    @JsonProperty("Warnings")
    val warnings: kotlin.collections.List<FlightDetailsWarning>? = null,
    // Container for flight segments.
    @JsonProperty("Segments")
    val segments: kotlin.collections.List<Segment>? = null,
    // Contains list of all the available alternate fare upsell/downsell offers.
    @JsonProperty("AllOffers")
    val allOffers: kotlin.collections.List<FlightDetailsOffer>? = null,
    // Container for fees that are charged for using certain payment methods.
    @JsonProperty("ValidFormsOfPayment")
    val validFormsOfPayment: kotlin.collections.List<AirValidFormsOfPayment>? = null,
    @JsonProperty("Lounges")
    val lounges: kotlin.collections.Map<kotlin.String, kotlin.collections.List<Lounge>>? = null,
    @JsonProperty("CrossSell")
    val crossSell: CrossSell? = null,
) {
    init {
        require(offer != null) { "offer must not be null" }

        require(transactionId != null) { "transactionId must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var offer: FlightDetailsOffer? = null,
        private var transactionId: kotlin.String? = null,
        private var warnings: kotlin.collections.List<FlightDetailsWarning>? = null,
        private var segments: kotlin.collections.List<Segment>? = null,
        private var allOffers: kotlin.collections.List<FlightDetailsOffer>? = null,
        private var validFormsOfPayment: kotlin.collections.List<AirValidFormsOfPayment>? = null,
        private var lounges: kotlin.collections.Map<kotlin.String, kotlin.collections.List<Lounge>>? = null,
        private var crossSell: CrossSell? = null,
    ) {
        fun offer(offer: FlightDetailsOffer) = apply { this.offer = offer }

        fun transactionId(transactionId: kotlin.String) = apply { this.transactionId = transactionId }

        fun warnings(warnings: kotlin.collections.List<FlightDetailsWarning>?) = apply { this.warnings = warnings }

        fun segments(segments: kotlin.collections.List<Segment>?) = apply { this.segments = segments }

        fun allOffers(allOffers: kotlin.collections.List<FlightDetailsOffer>?) = apply { this.allOffers = allOffers }

        fun validFormsOfPayment(validFormsOfPayment: kotlin.collections.List<AirValidFormsOfPayment>?) = apply { this.validFormsOfPayment = validFormsOfPayment }

        fun lounges(lounges: kotlin.collections.Map<kotlin.String, kotlin.collections.List<Lounge>>?) = apply { this.lounges = lounges }

        fun crossSell(crossSell: CrossSell?) = apply { this.crossSell = crossSell }

        fun build(): FlightDetailsResponse {
            val instance =
                FlightDetailsResponse(
                    offer = offer!!,
                    transactionId = transactionId!!,
                    warnings = warnings,
                    segments = segments,
                    allOffers = allOffers,
                    validFormsOfPayment = validFormsOfPayment,
                    lounges = lounges,
                    crossSell = crossSell,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            offer = offer!!,
            transactionId = transactionId!!,
            warnings = warnings,
            segments = segments,
            allOffers = allOffers,
            validFormsOfPayment = validFormsOfPayment,
            lounges = lounges,
            crossSell = crossSell,
        )
}
