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
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 *
 * @param adult
 * @param senior
 * @param childrenAges
 * @param infantInSeat
 * @param infantInLap
 */
data class PassengerDetails(
    @JsonProperty("Adult")
    @field:NotNull
    @field:Valid
    val adult: kotlin.String,
    @JsonProperty("Senior")
    @field:NotNull
    @field:Valid
    val senior: kotlin.String,
    @JsonProperty("ChildrenAges")
    @field:Valid
    val childrenAges: kotlin.String? = null,
    @JsonProperty("InfantInSeat")
    @field:Valid
    val infantInSeat: kotlin.String? = null,
    @JsonProperty("InfantInLap")
    @field:Valid
    val infantInLap: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var adult: kotlin.String? = null,
        private var senior: kotlin.String? = null,
        private var childrenAges: kotlin.String? = null,
        private var infantInSeat: kotlin.String? = null,
        private var infantInLap: kotlin.String? = null
    ) {
        fun adult(adult: kotlin.String) = apply { this.adult = adult }

        fun senior(senior: kotlin.String) = apply { this.senior = senior }

        fun childrenAges(childrenAges: kotlin.String?) = apply { this.childrenAges = childrenAges }

        fun infantInSeat(infantInSeat: kotlin.String?) = apply { this.infantInSeat = infantInSeat }

        fun infantInLap(infantInLap: kotlin.String?) = apply { this.infantInLap = infantInLap }

        fun build(): PassengerDetails {
            val instance =
                PassengerDetails(
                    adult = adult!!,
                    senior = senior!!,
                    childrenAges = childrenAges,
                    infantInSeat = infantInSeat,
                    infantInLap = infantInLap
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: PassengerDetails) {
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
            adult = adult!!,
            senior = senior!!,
            childrenAges = childrenAges,
            infantInSeat = infantInSeat,
            infantInLap = infantInLap
        )
}
