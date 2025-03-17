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

import com.expediagroup.sdk.xap.models.PaymentSchedulePrice

import com.fasterxml.jackson.annotation.JsonProperty

/**
* Contains information on the payment schedule.
    * @param due Date/Time stamp when this installment/deposit should be paid by.
    * @param price 
*/
data class PaymentSchedule(
            /* Date/Time stamp when this installment/deposit should be paid by. */
@JsonProperty("Due")
val due: java.time.LocalDate? = null,

        @JsonProperty("Price")
val price: PaymentSchedulePrice? = null
) {
    


    init {
        














    }

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
    val instance = PaymentSchedule(
            due = due,
            price = price
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            due = due,
            price = price
    )
}
