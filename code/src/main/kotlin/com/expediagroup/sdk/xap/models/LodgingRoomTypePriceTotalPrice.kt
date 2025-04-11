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

/**
 * The total price of the rate plan, which is equal to the sum of `BaseRate` + `TaxesAndFees`. Property mandatory fees are <u>not</u> included in this value as these are paid at the property at checkout.
 * @param `value` The value of the element being defined.
 * @param currency The ISO 4217 Currency Code that the value is expressed in.
 */
data class LodgingRoomTypePriceTotalPrice(
    // The value of the element being defined.
    @JsonProperty("Value")
    @field:Valid
    val `value`: kotlin.String? = null,
    // The ISO 4217 Currency Code that the value is expressed in.
    @JsonProperty("Currency")
    @field:Valid
    val currency: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var `value`: kotlin.String? = null,
        private var currency: kotlin.String? = null
    ) {
        fun `value`(`value`: kotlin.String?) = apply { this.`value` = `value` }

        fun currency(currency: kotlin.String?) = apply { this.currency = currency }

        fun build(): LodgingRoomTypePriceTotalPrice {
            val instance =
                LodgingRoomTypePriceTotalPrice(
                    `value` = `value`,
                    currency = currency
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: LodgingRoomTypePriceTotalPrice) {
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
            `value` = `value`,
            currency = currency
        )
}
