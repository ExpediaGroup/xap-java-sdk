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

import com.expediagroup.sdk.xap.models.CarsMoney
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Description and costs of any optional special equipment that may be rented with the car.
 * @param code Special equipment code
 * @param name Special equipment name
 * @param ratePeriod Unit indicating the price of special equipment. Support value:Trip,Daily
 * @param price
 */
data class Equipment(
    // Special equipment code
    @JsonProperty("Code")
    val code: kotlin.String,
    // Special equipment name
    @JsonProperty("Name")
    val name: kotlin.String,
    // Unit indicating the price of special equipment. Support value:Trip,Daily
    @JsonProperty("RatePeriod")
    val ratePeriod: kotlin.String? = null,
    @JsonProperty("Price")
    val price: CarsMoney? = null,
) {
    init {
        require(code != null) { "code must not be null" }

        require(name != null) { "name must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: kotlin.String? = null,
        private var name: kotlin.String? = null,
        private var ratePeriod: kotlin.String? = null,
        private var price: CarsMoney? = null,
    ) {
        fun code(code: kotlin.String) = apply { this.code = code }

        fun name(name: kotlin.String) = apply { this.name = name }

        fun ratePeriod(ratePeriod: kotlin.String?) = apply { this.ratePeriod = ratePeriod }

        fun price(price: CarsMoney?) = apply { this.price = price }

        fun build(): Equipment {
            val instance =
                Equipment(
                    code = code!!,
                    name = name!!,
                    ratePeriod = ratePeriod,
                    price = price,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            code = code!!,
            name = name!!,
            ratePeriod = ratePeriod,
            price = price,
        )
}
