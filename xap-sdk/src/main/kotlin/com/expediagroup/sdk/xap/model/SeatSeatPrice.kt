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

import com.expediagroup.sdk.core.common.getOrThrow
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for seat price to provide fee information associated with a paid seat. It can be null if it is not a paid seat.
 * @param `value`
 * @param currency
 */
@ConsistentCopyVisibility data class SeatSeatPrice private constructor(
    @JsonProperty("Value")
    val `value`: kotlin.String,

    @JsonProperty("Currency")
    val currency: kotlin.String,

) {

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

        fun build(): SeatSeatPrice {
            val `value` = this.`value`.getOrThrow {
                IllegalArgumentException("`value` must not be null")
            }

            val currency = this.currency.getOrThrow {
                IllegalArgumentException("currency must not be null")
            }

            val instance = SeatSeatPrice(
                `value` = `value`,
                currency = currency,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        `value` = `value`,
        currency = currency,
    )
}
