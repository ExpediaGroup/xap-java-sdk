/**
 * Copyright (C) 2025 Expedia, Inc.
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

import com.expediagroup.sdk.xap.models.ActivitiesMoney
import com.expediagroup.sdk.xap.models.ReferencePrice
import com.expediagroup.sdk.xap.models.Savings
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * The price of the offer
 * @param totalRate
 * @param category Type of passenger. Values supported are: ADULT | SENIOR | CHILD
 * @param totalFees
 * @param totalTaxesAndFees
 * @param referencePrice
 * @param savings
 */
data class ActivitiesPrice(
    @JsonProperty("TotalRate")
    val totalRate: ActivitiesMoney,
    // Type of passenger. Values supported are: ADULT | SENIOR | CHILD
    @JsonProperty("Category")
    val category: kotlin.String? = null,
    @JsonProperty("TotalFees")
    val totalFees: ActivitiesMoney? = null,
    @JsonProperty("TotalTaxesAndFees")
    val totalTaxesAndFees: ActivitiesMoney? = null,
    @JsonProperty("ReferencePrice")
    val referencePrice: ReferencePrice? = null,
    @JsonProperty("Savings")
    val savings: Savings? = null,
) {
    init {
        require(totalRate != null) { "totalRate must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var totalRate: ActivitiesMoney? = null,
        private var category: kotlin.String? = null,
        private var totalFees: ActivitiesMoney? = null,
        private var totalTaxesAndFees: ActivitiesMoney? = null,
        private var referencePrice: ReferencePrice? = null,
        private var savings: Savings? = null,
    ) {
        fun totalRate(totalRate: ActivitiesMoney) = apply { this.totalRate = totalRate }

        fun category(category: kotlin.String?) = apply { this.category = category }

        fun totalFees(totalFees: ActivitiesMoney?) = apply { this.totalFees = totalFees }

        fun totalTaxesAndFees(totalTaxesAndFees: ActivitiesMoney?) = apply { this.totalTaxesAndFees = totalTaxesAndFees }

        fun referencePrice(referencePrice: ReferencePrice?) = apply { this.referencePrice = referencePrice }

        fun savings(savings: Savings?) = apply { this.savings = savings }

        fun build(): ActivitiesPrice {
            val instance =
                ActivitiesPrice(
                    totalRate = totalRate!!,
                    category = category,
                    totalFees = totalFees,
                    totalTaxesAndFees = totalTaxesAndFees,
                    referencePrice = referencePrice,
                    savings = savings,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            totalRate = totalRate!!,
            category = category,
            totalFees = totalFees,
            totalTaxesAndFees = totalTaxesAndFees,
            referencePrice = referencePrice,
            savings = savings,
        )
}
