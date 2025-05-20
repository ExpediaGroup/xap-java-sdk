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
 * @param `value`
 * @param currency
 */
data class FlightTotalPrice(
    @JsonProperty("Value")
    val `value`: kotlin.String,
    @JsonProperty("Currency")
    val currency: kotlin.String,
) {
    init {
        require(`value` != null) { "`value` must not be null" }

        require(currency != null) { "currency must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var `value`: kotlin.String? = null,
        private var currency: kotlin.String? = null,
    ) {
        fun `value`(`value`: kotlin.String) = apply { this.`value` = `value` }

        fun currency(currency: kotlin.String) = apply { this.currency = currency }

        fun build(): FlightTotalPrice {
            val instance =
                FlightTotalPrice(
                    `value` = `value`!!,
                    currency = currency!!,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            `value` = `value`!!,
            currency = currency!!,
        )
}
