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
package com.expediagroup.sdk.xap.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param categoryCode Tax or fee category code.
 * @param amount Tax or fee value.
 */
@ConsistentCopyVisibility data class RoomRatesTaxesAndFeesDetailsInner private constructor(
    /* Tax or fee category code. */
    @JsonProperty("CategoryCode")
    val categoryCode: kotlin.Any? = null,

    /* Tax or fee value. */
    @JsonProperty("Amount")
    val amount: kotlin.Any? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var categoryCode: kotlin.Any? = null,
        private var amount: kotlin.Any? = null,
    ) {
        fun categoryCode(categoryCode: kotlin.Any?) = apply { this.categoryCode = categoryCode }

        fun amount(amount: kotlin.Any?) = apply { this.amount = amount }

        fun build(): RoomRatesTaxesAndFeesDetailsInner {
            val instance = RoomRatesTaxesAndFeesDetailsInner(
                categoryCode = categoryCode,
                amount = amount,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        categoryCode = categoryCode,
        amount = amount,
    )
}
