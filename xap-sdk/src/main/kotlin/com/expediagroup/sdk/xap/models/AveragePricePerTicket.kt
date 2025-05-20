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

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for average pricing information per ticket. If any passenger type (such as Infant in Lap or Seat) has a $0 total price there will be no ticket, so those passengers would not be included in the count.
 * @param `value` Average price per ticket, excluding infant
 * @param currency Currency in ISO 4217 format
 * @param count Indicates how many tickets were used to determine the average
 */
data class AveragePricePerTicket(
    // Average price per ticket, excluding infant
    @JsonProperty("Value")
    val `value`: kotlin.String,
    // Currency in ISO 4217 format
    @JsonProperty("Currency")
    val currency: kotlin.String,
    // Indicates how many tickets were used to determine the average
    @JsonProperty("Count")
    val count: kotlin.Int,
) {
    init {
        require(`value` != null) { "`value` must not be null" }

        require(currency != null) { "currency must not be null" }

        require(count != null) { "count must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var `value`: kotlin.String? = null,
        private var currency: kotlin.String? = null,
        private var count: kotlin.Int? = null,
    ) {
        fun `value`(`value`: kotlin.String) = apply { this.`value` = `value` }

        fun currency(currency: kotlin.String) = apply { this.currency = currency }

        fun count(count: kotlin.Int) = apply { this.count = count }

        fun build(): AveragePricePerTicket {
            val instance =
                AveragePricePerTicket(
                    `value` = `value`!!,
                    currency = currency!!,
                    count = count!!,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            `value` = `value`!!,
            currency = currency!!,
            count = count!!,
        )
}
