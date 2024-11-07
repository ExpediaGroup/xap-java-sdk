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
/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.expediagroup.sdk.xap.models

import com.expediagroup.sdk.xap.models.CarsMoney
import com.expediagroup.sdk.xap.models.Deductible
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

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
    @field:Valid
    val isRequired: kotlin.Boolean,
    // Category of the fee / Coverages
    @JsonProperty("FinanceCategory")
    @field:Valid
    val financeCategory: kotlin.String,
    // Sub category of the fee / Coverages .
    @JsonProperty("FinanceSubCategory")
    @field:Valid
    val financeSubCategory: kotlin.String,
    @JsonProperty("Amount")
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
            // Check required params
            validateNullity()
            return AdditionalFee(
                isRequired = isRequired!!,
                financeCategory = financeCategory!!,
                financeSubCategory = financeSubCategory!!,
                amount = amount!!,
                description = description,
                deductible = deductible
            )
        }

        private fun validateNullity() {
            if (isRequired == null) {
                throw NullPointerException("Required parameter isRequired is missing")
            }
            if (financeCategory == null) {
                throw NullPointerException("Required parameter financeCategory is missing")
            }
            if (financeSubCategory == null) {
                throw NullPointerException("Required parameter financeSubCategory is missing")
            }
            if (amount == null) {
                throw NullPointerException("Required parameter amount is missing")
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
