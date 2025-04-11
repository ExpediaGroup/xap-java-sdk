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
 * Container for no show penalty element
 * @param type What the penalty amount is based on. should be one of the following values:AMOUNT : it means the user is charged a fixed amount specified in the value node. Say 50$ for example.PERCENT : it means the user is charged a percentage of the base rate/total rate.PERDAY : it means the user is charged Per Day Price. For eg.., if the value is 2, it means the penalty amount will the Per day price of 2 days.
 * @param `value` Value to indicate how many/much of the type listed above is going to be charged as a penalty.
 * @param currency The currency of the amount, only valid when Type=AMOUNT
 */
data class PenaltyType(
    // What the penalty amount is based on. should be one of the following values:AMOUNT : it means the user is charged a fixed amount specified in the value node. Say 50$ for example.PERCENT : it means the user is charged a percentage of the base rate/total rate.PERDAY : it means the user is charged Per Day Price. For eg.., if the value is 2, it means the penalty amount will the Per day price of 2 days.
    @JsonProperty("Type")
    @field:NotNull
    @field:Valid
    val type: kotlin.String,
    // Value to indicate how many/much of the type listed above is going to be charged as a penalty.
    @JsonProperty("Value")
    @field:NotNull
    @field:Valid
    val `value`: kotlin.String,
    // The currency of the amount, only valid when Type=AMOUNT
    @JsonProperty("Currency")
    @field:NotNull
    @field:Valid
    val currency: kotlin.String
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: kotlin.String? = null,
        private var `value`: kotlin.String? = null,
        private var currency: kotlin.String? = null
    ) {
        fun type(type: kotlin.String) = apply { this.type = type }

        fun `value`(`value`: kotlin.String) = apply { this.`value` = `value` }

        fun currency(currency: kotlin.String) = apply { this.currency = currency }

        fun build(): PenaltyType {
            val instance =
                PenaltyType(
                    type = type!!,
                    `value` = `value`!!,
                    currency = currency!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: PenaltyType) {
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
            type = type!!,
            `value` = `value`!!,
            currency = currency!!
        )
}
