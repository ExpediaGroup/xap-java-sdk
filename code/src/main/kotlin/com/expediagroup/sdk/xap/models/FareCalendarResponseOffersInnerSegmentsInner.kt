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
/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.expediagroup.sdk.xap.models

import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.FareCalendarResponseOffersInnerSegmentsInnerLegsInner
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container of information about each flight offer Segments (the trip from one stopping place to another) are made up of Legs This will be given back in response if includeSegmentDetails=true
 * @param legs Container information on each flight leg.
 */
data class FareCalendarResponseOffersInnerSegmentsInner(
    // Container information on each flight leg.
    @JsonProperty("Legs")
    @field:NotNull
    @field:Valid
    val legs: kotlin.collections
        .List<
            FareCalendarResponseOffersInnerSegmentsInnerLegsInner
        >
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var legs: kotlin.collections.List<FareCalendarResponseOffersInnerSegmentsInnerLegsInner>? = null
    ) {
        fun legs(legs: kotlin.collections.List<FareCalendarResponseOffersInnerSegmentsInnerLegsInner>) = apply { this.legs = legs }

        fun build(): FareCalendarResponseOffersInnerSegmentsInner {
            val instance =
                FareCalendarResponseOffersInnerSegmentsInner(
                    legs = legs!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FareCalendarResponseOffersInnerSegmentsInner) {
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
            legs = legs!!
        )
}
