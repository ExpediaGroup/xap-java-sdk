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
import com.expediagroup.sdk.xap.models.CarsMoney
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Extra cost for each increment of distance used.
 * @param distance
 * @param cost
 */
data class ExtraCostPerDistance(
    @JsonProperty("Distance")
    @field:NotNull
    @field:Valid
    val distance: CarsDistance,
    @JsonProperty("Cost")
    @field:NotNull
    @field:Valid
    val cost: CarsMoney
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var distance: CarsDistance? = null,
        private var cost: CarsMoney? = null
    ) {
        fun distance(distance: CarsDistance) = apply { this.distance = distance }

        fun cost(cost: CarsMoney) = apply { this.cost = cost }

        fun build(): ExtraCostPerDistance {
            val instance =
                ExtraCostPerDistance(
                    distance = distance!!,
                    cost = cost!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: ExtraCostPerDistance) {
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
            distance = distance!!,
            cost = cost!!
        )
}
