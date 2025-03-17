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

import com.expediagroup.sdk.xap.models.Money

import com.fasterxml.jackson.annotation.JsonProperty

/**
* 
    * @param `value` The total price of the corresponding date.
    * @param currency The ISO 4217 Currency Code that the Value is expressed in.  See [Global Currency Codes](https://developers.expediagroup.com/xap/products/xap/lodging/references/global-currency-codes) for a full list of supported currencies. 
    * @param localCurrencyPrice 
*/
data class RateCalendarPrice(
            /* The total price of the corresponding date. */
@JsonProperty("Value")
val `value`: kotlin.Any? = null,

            /* The ISO 4217 Currency Code that the Value is expressed in.  See [Global Currency Codes](https://developers.expediagroup.com/xap/products/xap/lodging/references/global-currency-codes) for a full list of supported currencies.  */
@JsonProperty("Currency")
val currency: kotlin.Any? = null,

        @JsonProperty("LocalCurrencyPrice")
val localCurrencyPrice: Money? = null
) {
    


    init {
        





















    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var `value`: kotlin.Any? = null,
                private var currency: kotlin.Any? = null,
                private var localCurrencyPrice: Money? = null
        ) {
                fun `value`(`value`: kotlin.Any?) = apply { this.`value` = `value` }
                fun currency(currency: kotlin.Any?) = apply { this.currency = currency }
                fun localCurrencyPrice(localCurrencyPrice: Money?) = apply { this.localCurrencyPrice = localCurrencyPrice }

    fun build(): RateCalendarPrice {
    val instance = RateCalendarPrice(
            `value` = `value`,
            currency = currency,
            localCurrencyPrice = localCurrencyPrice
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            `value` = `value`,
            currency = currency,
            localCurrencyPrice = localCurrencyPrice
    )
}
