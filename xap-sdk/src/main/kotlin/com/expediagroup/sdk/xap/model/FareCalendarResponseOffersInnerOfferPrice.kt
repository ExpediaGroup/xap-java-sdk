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
import com.expediagroup.sdk.xap.model.FlightsV3Money
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for offer price information.
 * @param totalPrice
 */
@ConsistentCopyVisibility data class FareCalendarResponseOffersInnerOfferPrice private constructor(
    @JsonProperty("TotalPrice")
    val totalPrice: FlightsV3Money,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var totalPrice: FlightsV3Money? = null,
    ) {
        fun totalPrice(totalPrice: FlightsV3Money) = apply { this.totalPrice = totalPrice }

        fun build(): FareCalendarResponseOffersInnerOfferPrice {
            val totalPrice = this.totalPrice.getOrThrow {
                IllegalArgumentException("totalPrice must not be null")
            }

            val instance = FareCalendarResponseOffersInnerOfferPrice(
                totalPrice = totalPrice,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        totalPrice = totalPrice,
    )
}
