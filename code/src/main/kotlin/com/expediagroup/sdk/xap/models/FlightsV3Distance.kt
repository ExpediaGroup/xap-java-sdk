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
 * Container for distance of flight.
 * @param `value` The number of miles/kilometers of the distance (specified by the Unit).
 * @param unit The unit (KM or MI) for the distance.
 * @param direction The direction of the location from the search 'center'.Possible values are: N,S,W,E,NW,NE,SW,SE
 */
data class FlightsV3Distance(
    // The number of miles/kilometers of the distance (specified by the Unit).
    @JsonProperty("Value")
    @field:NotNull
    @field:Valid
    val `value`: kotlin.String,
    // The unit (KM or MI) for the distance.
    @JsonProperty("Unit")
    @field:NotNull
    @field:Valid
    val unit: kotlin.String,
    // The direction of the location from the search 'center'.Possible values are: N,S,W,E,NW,NE,SW,SE
    @JsonProperty("Direction")
    @field:Valid
    val direction: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var `value`: kotlin.String? = null,
        private var unit: kotlin.String? = null,
        private var direction: kotlin.String? = null
    ) {
        fun `value`(`value`: kotlin.String) = apply { this.`value` = `value` }

        fun unit(unit: kotlin.String) = apply { this.unit = unit }

        fun direction(direction: kotlin.String?) = apply { this.direction = direction }

        fun build(): FlightsV3Distance {
            val instance =
                FlightsV3Distance(
                    `value` = `value`!!,
                    unit = unit!!,
                    direction = direction
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightsV3Distance) {
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
            `value` = `value`!!,
            unit = unit!!,
            direction = direction
        )
}
