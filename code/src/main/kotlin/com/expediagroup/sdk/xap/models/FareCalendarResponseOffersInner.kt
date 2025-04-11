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
import com.expediagroup.sdk.xap.models.FareCalendarResponseOffersInnerOfferPrice
import com.expediagroup.sdk.xap.models.FareCalendarResponseOffersInnerSegmentsInner
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container for list of air offers. An offer gives total trip details including flight and pricing information.
 * @param departureDate Flight departure date in ISO 8601 format.
 * @param offerPrice
 * @param segments Container of information about each flight offer Segments (the trip from one stopping place to another) are made up of Legs This will be given back in response if includeSegmentDetails=true
 */
data class FareCalendarResponseOffersInner(
    // Flight departure date in ISO 8601 format.
    @JsonProperty("DepartureDate")
    val departureDate: java.time.OffsetDateTime,
    @JsonProperty("OfferPrice")
    @field:NotNull
    @field:Valid
    val offerPrice: FareCalendarResponseOffersInnerOfferPrice,
    // Container of information about each flight offer Segments (the trip from one stopping place to another) are made up of Legs This will be given back in response if includeSegmentDetails=true
    @JsonProperty("Segments")
    @field:Valid
    val segments: kotlin.collections.List<FareCalendarResponseOffersInnerSegmentsInner>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var departureDate: java.time.OffsetDateTime? = null,
        private var offerPrice: FareCalendarResponseOffersInnerOfferPrice? = null,
        private var segments: kotlin.collections.List<FareCalendarResponseOffersInnerSegmentsInner>? = null
    ) {
        fun departureDate(departureDate: java.time.OffsetDateTime) = apply { this.departureDate = departureDate }

        fun offerPrice(offerPrice: FareCalendarResponseOffersInnerOfferPrice) = apply { this.offerPrice = offerPrice }

        fun segments(segments: kotlin.collections.List<FareCalendarResponseOffersInnerSegmentsInner>?) = apply { this.segments = segments }

        fun build(): FareCalendarResponseOffersInner {
            val instance =
                FareCalendarResponseOffersInner(
                    departureDate = departureDate!!,
                    offerPrice = offerPrice!!,
                    segments = segments
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FareCalendarResponseOffersInner) {
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
            departureDate = departureDate!!,
            offerPrice = offerPrice!!,
            segments = segments
        )
}
