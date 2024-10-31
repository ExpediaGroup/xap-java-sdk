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

import com.expediagroup.sdk.xap.models.PaymentSchedulePrice
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * Contains information on the payment schedule.
 * @param due Date/Time stamp when this installment/deposit should be paid by.
 * @param price
 */
data class PaymentSchedule(
    // Date/Time stamp when this installment/deposit should be paid by.
    @JsonProperty("Due")
    val due: java.time.LocalDate? = null,
    @JsonProperty("Price")
    @field:Valid
    val price: PaymentSchedulePrice? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var due: java.time.LocalDate? = null,
        private var price: PaymentSchedulePrice? = null
    ) {
        fun due(due: java.time.LocalDate?) = apply { this.due = due }

        fun price(price: PaymentSchedulePrice?) = apply { this.price = price }

        fun build(): PaymentSchedule {
            return PaymentSchedule(
                due = due,
                price = price
            )
        }
    }

    fun toBuilder() =
        Builder(
            due = due,
            price = price
        )
}
