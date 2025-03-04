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

import com.expediagroup.sdk.rest.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.CarsMoney
import com.expediagroup.sdk.xap.models.Deductible
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
* List of additional fees including both mandatory and optional fees.such as young driver fee/drop off fee /CollisionDamageWaiver
 * @param isRequired Indicates whether this additional fee is mandatory.
 * @param financeCategory Category of the fee / Coverages
 * @param financeSubCategory Sub category of the fee / Coverages .
 * @param amount
 * @param description Description of the fee.
 * @param deductible
*/
data class AdditionalFee(
    // Indicates whether this additional fee is mandatory.
    @JsonProperty("IsRequired")
    @field:NotNull
    @field:Valid
    val isRequired: kotlin.Boolean,
    // Category of the fee / Coverages
    @JsonProperty("FinanceCategory")
    @field:NotNull
    @field:Valid
    val financeCategory: kotlin.String,
    // Sub category of the fee / Coverages .
    @JsonProperty("FinanceSubCategory")
    @field:NotNull
    @field:Valid
    val financeSubCategory: kotlin.String,
    @JsonProperty("Amount")
    @field:NotNull
    @field:Valid
    val amount: CarsMoney,
    // Description of the fee.
    @JsonProperty("Description")
    @field:Valid
    val description: kotlin.String? = null,
    @JsonProperty("Deductible")
    @field:Valid
    val deductible: Deductible? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var isRequired: kotlin.Boolean? = null,
        private var financeCategory: kotlin.String? = null,
        private var financeSubCategory: kotlin.String? = null,
        private var amount: CarsMoney? = null,
        private var description: kotlin.String? = null,
        private var deductible: Deductible? = null
    ) {
        fun isRequired(isRequired: kotlin.Boolean) = apply { this.isRequired = isRequired }

        fun financeCategory(financeCategory: kotlin.String) = apply { this.financeCategory = financeCategory }

        fun financeSubCategory(financeSubCategory: kotlin.String) = apply { this.financeSubCategory = financeSubCategory }

        fun amount(amount: CarsMoney) = apply { this.amount = amount }

        fun description(description: kotlin.String?) = apply { this.description = description }

        fun deductible(deductible: Deductible?) = apply { this.deductible = deductible }

        fun build(): AdditionalFee {
            val instance =
                AdditionalFee(
                    isRequired = isRequired!!,
                    financeCategory = financeCategory!!,
                    financeSubCategory = financeSubCategory!!,
                    amount = amount!!,
                    description = description,
                    deductible = deductible
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: AdditionalFee) {
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
            isRequired = isRequired!!,
            financeCategory = financeCategory!!,
            financeSubCategory = financeSubCategory!!,
            amount = amount!!,
            description = description,
            deductible = deductible
        )
}
