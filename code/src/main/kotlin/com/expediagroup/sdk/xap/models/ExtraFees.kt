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
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * List of ExtraFeesDetails
 * @param unit Rate period beyond the base rate. Supported values: ExtraHourly, ExtraDaily
 * @param unitCount Numbers of period
 * @param amount
 */
data class ExtraFees(
    // Rate period beyond the base rate. Supported values: ExtraHourly, ExtraDaily
    @JsonProperty("Unit")
    @field:Valid
    val unit: kotlin.String,
    // Numbers of period
    @JsonProperty("UnitCount")
    val unitCount: kotlin.Long,
    @JsonProperty("Amount")
    @field:Valid
    val amount: CarsMoney
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var unit: kotlin.String? = null,
        private var unitCount: kotlin.Long? = null,
        private var amount: CarsMoney? = null
    ) {
        fun unit(unit: kotlin.String) = apply { this.unit = unit }

        fun unitCount(unitCount: kotlin.Long) = apply { this.unitCount = unitCount }

        fun amount(amount: CarsMoney) = apply { this.amount = amount }

        fun build(): ExtraFees {
            // Check required params
            validateNullity()
            return ExtraFees(
                unit = unit!!,
                unitCount = unitCount!!,
                amount = amount!!
            )
        }

        private fun validateNullity() {
            if (unit == null) {
                throw NullPointerException("Required parameter unit is missing")
            }
            if (unitCount == null) {
                throw NullPointerException("Required parameter unitCount is missing")
            }
            if (amount == null) {
                throw NullPointerException("Required parameter amount is missing")
            }
        }
    }

    fun toBuilder() =
        Builder(
            unit = unit!!,
            unitCount = unitCount!!,
            amount = amount!!
        )
}