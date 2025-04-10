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
import com.expediagroup.sdk.xap.models.BaggageFee
import com.expediagroup.sdk.xap.models.BaggageFeeFlightSegment
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container for information on Baggage fee information of each Segment.
 * @param flightSegment
 * @param baggageFees Container for baggage fee information of each bag type. The baggage fee can vary for each bag type. The amount can be zero, fixed amount or will be in a range
 */
data class FlightBaggageFee(
    @JsonProperty("FlightSegment")
    @field:NotNull
    @field:Valid
    val flightSegment: BaggageFeeFlightSegment,
    // Container for baggage fee information of each bag type. The baggage fee can vary for each bag type. The amount can be zero, fixed amount or will be in a range
    @JsonProperty("BaggageFees")
    @field:NotNull
    @field:Valid
    val baggageFees: kotlin.collections
        .List<
            BaggageFee
        >
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var flightSegment: BaggageFeeFlightSegment? = null,
        private var baggageFees: kotlin.collections.List<BaggageFee>? = null
    ) {
        fun flightSegment(flightSegment: BaggageFeeFlightSegment) = apply { this.flightSegment = flightSegment }

        fun baggageFees(baggageFees: kotlin.collections.List<BaggageFee>) = apply { this.baggageFees = baggageFees }

        fun build(): FlightBaggageFee {
            val instance =
                FlightBaggageFee(
                    flightSegment = flightSegment!!,
                    baggageFees = baggageFees!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightBaggageFee) {
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
            flightSegment = flightSegment!!,
            baggageFees = baggageFees!!
        )
}
