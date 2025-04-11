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
import com.expediagroup.sdk.xap.models.Money
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 *
 * @param categoryCode
 * @param amount
 */
data class TaxesAndFeesDetail(
    @JsonProperty("CategoryCode")
    @field:Valid
    val categoryCode: kotlin.String? = null,
    @JsonProperty("Amount")
    @field:Valid
    val amount: Money? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var categoryCode: kotlin.String? = null,
        private var amount: Money? = null
    ) {
        fun categoryCode(categoryCode: kotlin.String?) = apply { this.categoryCode = categoryCode }

        fun amount(amount: Money?) = apply { this.amount = amount }

        fun build(): TaxesAndFeesDetail {
            val instance =
                TaxesAndFeesDetail(
                    categoryCode = categoryCode,
                    amount = amount
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: TaxesAndFeesDetail) {
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
            categoryCode = categoryCode,
            amount = amount
        )
}
