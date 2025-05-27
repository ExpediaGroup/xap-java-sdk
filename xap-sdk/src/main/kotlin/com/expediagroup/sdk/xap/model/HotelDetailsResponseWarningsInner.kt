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
 * @param code The code of the warning.  Available values are: - PRICE_DECREASED: The price decreased after shopping. - PRICE_INCREASED: The price increased after shopping. - CURRENCY_CHANGE: You will be charged in a different currency.
 * @param description A detail information of what happened.
 * @param originalPrice The original price from the Lodging Search API response.
 * @param newPrice The new price.
 * @param changedAmount The difference between `OriginalPrice` and `NewPrice`.
 * @param changedPercentage The changed percentage. In the sample 2.97 means the changed percentage is 2.97%.
 */
@ConsistentCopyVisibility data class HotelDetailsResponseWarningsInner private constructor(
    /* The code of the warning.  Available values are: - PRICE_DECREASED: The price decreased after shopping. - PRICE_INCREASED: The price increased after shopping. - CURRENCY_CHANGE: You will be charged in a different currency.  */
    @JsonProperty("Code")
    val code: kotlin.Any? = null,

    /* A detail information of what happened. */
    @JsonProperty("Description")
    val description: kotlin.String? = null,

    /* The original price from the Lodging Search API response. */
    @JsonProperty("OriginalPrice")
    val originalPrice: kotlin.Any? = null,

    /* The new price. */
    @JsonProperty("NewPrice")
    val newPrice: kotlin.Any? = null,

    /* The difference between `OriginalPrice` and `NewPrice`. */
    @JsonProperty("ChangedAmount")
    val changedAmount: kotlin.Any? = null,

    /* The changed percentage. In the sample 2.97 means the changed percentage is 2.97%. */
    @JsonProperty("ChangedPercentage")
    val changedPercentage: kotlin.String? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: kotlin.Any? = null,
        private var description: kotlin.String? = null,
        private var originalPrice: kotlin.Any? = null,
        private var newPrice: kotlin.Any? = null,
        private var changedAmount: kotlin.Any? = null,
        private var changedPercentage: kotlin.String? = null,
    ) {
        fun code(code: kotlin.Any?) = apply { this.code = code }

        fun description(description: kotlin.String?) = apply { this.description = description }

        fun originalPrice(originalPrice: kotlin.Any?) = apply { this.originalPrice = originalPrice }

        fun newPrice(newPrice: kotlin.Any?) = apply { this.newPrice = newPrice }

        fun changedAmount(changedAmount: kotlin.Any?) = apply { this.changedAmount = changedAmount }

        fun changedPercentage(changedPercentage: kotlin.String?) = apply { this.changedPercentage = changedPercentage }

        fun build(): HotelDetailsResponseWarningsInner {
            val instance = HotelDetailsResponseWarningsInner(
                code = code,
                description = description,
                originalPrice = originalPrice,
                newPrice = newPrice,
                changedAmount = changedAmount,
                changedPercentage = changedPercentage,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        code = code,
        description = description,
        originalPrice = originalPrice,
        newPrice = newPrice,
        changedAmount = changedAmount,
        changedPercentage = changedPercentage,
    )
}
