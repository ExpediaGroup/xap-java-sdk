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
import com.expediagroup.sdk.xap.models.CarsDistance
import com.expediagroup.sdk.xap.models.ExtraCostPerDistance
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * A list of charges to be levied based on the mileage driven.
 * @param freeDistance
 * @param freeDistanceRatePeriod Rate period for free distance.
 * @param extraCostPerDistance
 */
data class Mileage(
    @JsonProperty("FreeDistance")
    @field:NotNull
    @field:Valid
    val freeDistance: CarsDistance,
    // Rate period for free distance.
    @JsonProperty("FreeDistanceRatePeriod")
    @field:NotNull
    @field:Valid
    val freeDistanceRatePeriod: kotlin.String,
    @JsonProperty("ExtraCostPerDistance")
    @field:Valid
    val extraCostPerDistance: ExtraCostPerDistance? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var freeDistance: CarsDistance? = null,
        private var freeDistanceRatePeriod: kotlin.String? = null,
        private var extraCostPerDistance: ExtraCostPerDistance? = null
    ) {
        fun freeDistance(freeDistance: CarsDistance) = apply { this.freeDistance = freeDistance }

        fun freeDistanceRatePeriod(freeDistanceRatePeriod: kotlin.String) = apply { this.freeDistanceRatePeriod = freeDistanceRatePeriod }

        fun extraCostPerDistance(extraCostPerDistance: ExtraCostPerDistance?) = apply { this.extraCostPerDistance = extraCostPerDistance }

        fun build(): Mileage {
            val instance =
                Mileage(
                    freeDistance = freeDistance!!,
                    freeDistanceRatePeriod = freeDistanceRatePeriod!!,
                    extraCostPerDistance = extraCostPerDistance
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Mileage) {
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
            freeDistance = freeDistance!!,
            freeDistanceRatePeriod = freeDistanceRatePeriod!!,
            extraCostPerDistance = extraCostPerDistance
        )
}
