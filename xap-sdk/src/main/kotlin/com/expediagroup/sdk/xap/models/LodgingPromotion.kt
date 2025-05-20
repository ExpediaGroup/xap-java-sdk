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

import com.expediagroup.sdk.xap.models.LodgingMoney
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param description The description of the promotion.
 * @param amount
 */
data class LodgingPromotion(
    // The description of the promotion.
    @JsonProperty("Description")
    val description: kotlin.String? = null,
    @JsonProperty("Amount")
    val amount: LodgingMoney? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var description: kotlin.String? = null,
        private var amount: LodgingMoney? = null,
    ) {
        fun description(description: kotlin.String?) = apply { this.description = description }

        fun amount(amount: LodgingMoney?) = apply { this.amount = amount }

        fun build(): LodgingPromotion {
            val instance =
                LodgingPromotion(
                    description = description,
                    amount = amount,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            description = description,
            amount = amount,
        )
}
