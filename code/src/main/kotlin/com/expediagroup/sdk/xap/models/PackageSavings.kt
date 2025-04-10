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
 * The amount of money that the traveler is saving by booking a package over booking the same offers as standalone products.
 * @param `value` The value of the element being defined.
 * @param currency The ISO 4217 Currency Code that the value is expressed in.
 * @param localCurrencyPrice
 * @param type Savings type
 * @param description Description of savings
 */
data class PackageSavings(
    // The value of the element being defined.
    @JsonProperty("Value")
    @field:NotNull
    @field:Valid
    val `value`: kotlin.String,
    // The ISO 4217 Currency Code that the value is expressed in.
    @JsonProperty("Currency")
    @field:NotNull
    @field:Valid
    val currency: kotlin.String,
    @JsonProperty("LocalCurrencyPrice")
    @field:Valid
    val localCurrencyPrice: FlightsV3Money? = null,
    // Savings type
    @JsonProperty("Type")
    @field:Valid
    val type: kotlin.String? = null,
    // Description of savings
    @JsonProperty("Description")
    @field:Valid
    val description: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var `value`: kotlin.String? = null,
        private var currency: kotlin.String? = null,
        private var localCurrencyPrice: FlightsV3Money? = null,
        private var type: kotlin.String? = null,
        private var description: kotlin.String? = null
    ) {
        fun `value`(`value`: kotlin.String) = apply { this.`value` = `value` }

        fun currency(currency: kotlin.String) = apply { this.currency = currency }

        fun localCurrencyPrice(localCurrencyPrice: FlightsV3Money?) = apply { this.localCurrencyPrice = localCurrencyPrice }

        fun type(type: kotlin.String?) = apply { this.type = type }

        fun description(description: kotlin.String?) = apply { this.description = description }

        fun build(): PackageSavings {
            val instance =
                PackageSavings(
                    `value` = `value`!!,
                    currency = currency!!,
                    localCurrencyPrice = localCurrencyPrice,
                    type = type,
                    description = description
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: PackageSavings) {
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
            currency = currency!!,
            localCurrencyPrice = localCurrencyPrice,
            type = type,
            description = description
        )
}
