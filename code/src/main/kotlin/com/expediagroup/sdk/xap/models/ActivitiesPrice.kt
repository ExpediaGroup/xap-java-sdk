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

import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.ActivitiesMoney
import com.expediagroup.sdk.xap.models.ReferencePrice
import com.expediagroup.sdk.xap.models.Savings
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

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
    @field:NotNull
    @field:Valid
    val totalRate: ActivitiesMoney,
    // Type of passenger. Values supported are: ADULT | SENIOR | CHILD
    @JsonProperty("Category")
    @field:Valid
    val category: kotlin.String? = null,
    @JsonProperty("TotalFees")
    @field:Valid
    val totalFees: ActivitiesMoney? = null,
    @JsonProperty("TotalTaxesAndFees")
    @field:Valid
    val totalTaxesAndFees: ActivitiesMoney? = null,
    @JsonProperty("ReferencePrice")
    @field:Valid
    val referencePrice: ReferencePrice? = null,
    @JsonProperty("Savings")
    @field:Valid
    val savings: Savings? = null
) {
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
        private var savings: Savings? = null
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
                    savings = savings
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: ActivitiesPrice) {
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
            totalRate = totalRate!!,
            category = category,
            totalFees = totalFees,
            totalTaxesAndFees = totalTaxesAndFees,
            referencePrice = referencePrice,
            savings = savings
        )
}
