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

import com.expediagroup.sdk.xap.models.CarsMoney
import com.expediagroup.sdk.xap.models.Deductible
import com.fasterxml.jackson.annotation.JsonProperty

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
    val isRequired: kotlin.Boolean,
    // Category of the fee / Coverages
    @JsonProperty("FinanceCategory")
    val financeCategory: kotlin.String,
    // Sub category of the fee / Coverages .
    @JsonProperty("FinanceSubCategory")
    val financeSubCategory: kotlin.String,
    @JsonProperty("Amount")
    val amount: CarsMoney,
    // Description of the fee.
    @JsonProperty("Description")
    val description: kotlin.String? = null,
    @JsonProperty("Deductible")
    val deductible: Deductible? = null,
) {
    init {
        require(isRequired != null) { "isRequired must not be null" }

        require(financeCategory != null) { "financeCategory must not be null" }

        require(financeSubCategory != null) { "financeSubCategory must not be null" }

        require(amount != null) { "amount must not be null" }
    }

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
        private var deductible: Deductible? = null,
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
                    deductible = deductible,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            isRequired = isRequired!!,
            financeCategory = financeCategory!!,
            financeSubCategory = financeSubCategory!!,
            amount = amount!!,
            description = description,
            deductible = deductible,
        )
}
