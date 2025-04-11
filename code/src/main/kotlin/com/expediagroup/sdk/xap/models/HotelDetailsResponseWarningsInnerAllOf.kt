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
 *
 * @param code The code of the warning.  Available values are: - PRICE_DECREASED: The price decreased after shopping. - PRICE_INCREASED: The price increased after shopping. - CURRENCY_CHANGE: You will be charged in a different currency.
 * @param originalPrice The original price from the Lodging Search API response.
 * @param newPrice The new price.
 * @param changedAmount The difference between `OriginalPrice` and `NewPrice`.
 */
data class HotelDetailsResponseWarningsInnerAllOf(
    // The code of the warning.  Available values are: - PRICE_DECREASED: The price decreased after shopping. - PRICE_INCREASED: The price increased after shopping. - CURRENCY_CHANGE: You will be charged in a different currency.
    @JsonProperty("Code")
    @field:Valid
    val code: kotlin.Any? = null,
    // The original price from the Lodging Search API response.
    @JsonProperty("OriginalPrice")
    @field:Valid
    val originalPrice: kotlin.Any? = null,
    // The new price.
    @JsonProperty("NewPrice")
    @field:Valid
    val newPrice: kotlin.Any? = null,
    // The difference between `OriginalPrice` and `NewPrice`.
    @JsonProperty("ChangedAmount")
    @field:Valid
    val changedAmount: kotlin.Any? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: kotlin.Any? = null,
        private var originalPrice: kotlin.Any? = null,
        private var newPrice: kotlin.Any? = null,
        private var changedAmount: kotlin.Any? = null
    ) {
        fun code(code: kotlin.Any?) = apply { this.code = code }

        fun originalPrice(originalPrice: kotlin.Any?) = apply { this.originalPrice = originalPrice }

        fun newPrice(newPrice: kotlin.Any?) = apply { this.newPrice = newPrice }

        fun changedAmount(changedAmount: kotlin.Any?) = apply { this.changedAmount = changedAmount }

        fun build(): HotelDetailsResponseWarningsInnerAllOf {
            val instance =
                HotelDetailsResponseWarningsInnerAllOf(
                    code = code,
                    originalPrice = originalPrice,
                    newPrice = newPrice,
                    changedAmount = changedAmount
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: HotelDetailsResponseWarningsInnerAllOf) {
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
            code = code,
            originalPrice = originalPrice,
            newPrice = newPrice,
            changedAmount = changedAmount
        )
}
