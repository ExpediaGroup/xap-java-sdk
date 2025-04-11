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
import com.expediagroup.sdk.xap.models.FlightsV3Money
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 *
 * @param code Standardized warning code.
 * @param description Standardized warning description message.
 * @param originalPrice
 * @param newPrice
 * @param changedAmount
 */
data class FlightDetailsWarning(
    // Standardized warning code.
    @JsonProperty("Code")
    @field:NotNull
    @field:Valid
    val code: kotlin.String,
    // Standardized warning description message.
    @JsonProperty("Description")
    @field:NotNull
    @field:Valid
    val description: kotlin.String,
    @JsonProperty("OriginalPrice")
    @field:Valid
    val originalPrice: FlightsV3Money? = null,
    @JsonProperty("NewPrice")
    @field:Valid
    val newPrice: FlightsV3Money? = null,
    @JsonProperty("ChangedAmount")
    @field:Valid
    val changedAmount: FlightsV3Money? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: kotlin.String? = null,
        private var description: kotlin.String? = null,
        private var originalPrice: FlightsV3Money? = null,
        private var newPrice: FlightsV3Money? = null,
        private var changedAmount: FlightsV3Money? = null
    ) {
        fun code(code: kotlin.String) = apply { this.code = code }

        fun description(description: kotlin.String) = apply { this.description = description }

        fun originalPrice(originalPrice: FlightsV3Money?) = apply { this.originalPrice = originalPrice }

        fun newPrice(newPrice: FlightsV3Money?) = apply { this.newPrice = newPrice }

        fun changedAmount(changedAmount: FlightsV3Money?) = apply { this.changedAmount = changedAmount }

        fun build(): FlightDetailsWarning {
            val instance =
                FlightDetailsWarning(
                    code = code!!,
                    description = description!!,
                    originalPrice = originalPrice,
                    newPrice = newPrice,
                    changedAmount = changedAmount
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightDetailsWarning) {
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
            code = code!!,
            description = description!!,
            originalPrice = originalPrice,
            newPrice = newPrice,
            changedAmount = changedAmount
        )
}
