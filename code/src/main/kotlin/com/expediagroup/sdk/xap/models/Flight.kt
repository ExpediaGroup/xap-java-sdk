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
import com.expediagroup.sdk.xap.models.FlightSegmentsInner
import com.expediagroup.sdk.xap.models.FlightTotalPrice
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 *
 * @param flightId
 * @param segments
 * @param totalPrice
 */
data class Flight(
    @JsonProperty("FlightId")
    @field:NotNull
    @field:Valid
    val flightId: kotlin.String,
    @JsonProperty("Segments")
    @field:NotNull
    @field:Valid
    val segments: kotlin.collections
        .List<
            FlightSegmentsInner
        >,
    @JsonProperty("TotalPrice")
    @field:Valid
    val totalPrice: FlightTotalPrice? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var flightId: kotlin.String? = null,
        private var segments: kotlin.collections.List<FlightSegmentsInner>? = null,
        private var totalPrice: FlightTotalPrice? = null
    ) {
        fun flightId(flightId: kotlin.String) = apply { this.flightId = flightId }

        fun segments(segments: kotlin.collections.List<FlightSegmentsInner>) = apply { this.segments = segments }

        fun totalPrice(totalPrice: FlightTotalPrice?) = apply { this.totalPrice = totalPrice }

        fun build(): Flight {
            val instance =
                Flight(
                    flightId = flightId!!,
                    segments = segments!!,
                    totalPrice = totalPrice
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Flight) {
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
            flightId = flightId!!,
            segments = segments!!,
            totalPrice = totalPrice
        )
}
