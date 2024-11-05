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

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 *
 * @param categoryCode Category ID of this specific tax or fee.
 * @param amount The value of this specific tax or fee.
 */
data class RatePlanPriceTaxesAndFeesDetailsInner(
    // Category ID of this specific tax or fee.
    @JsonProperty("CategoryCode")
    @field:Valid
    val categoryCode: kotlin.Any? = null,
    // The value of this specific tax or fee.
    @JsonProperty("Amount")
    @field:Valid
    val amount: kotlin.Any? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var categoryCode: kotlin.Any? = null,
        private var amount: kotlin.Any? = null
    ) {
        fun categoryCode(categoryCode: kotlin.Any?) = apply { this.categoryCode = categoryCode }

        fun amount(amount: kotlin.Any?) = apply { this.amount = amount }

        fun build(): RatePlanPriceTaxesAndFeesDetailsInner {
            return RatePlanPriceTaxesAndFeesDetailsInner(
                categoryCode = categoryCode,
                amount = amount
            )
        }
    }

    fun toBuilder() =
        Builder(
            categoryCode = categoryCode,
            amount = amount
        )
}
