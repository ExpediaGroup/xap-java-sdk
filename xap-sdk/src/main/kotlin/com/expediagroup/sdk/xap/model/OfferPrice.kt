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
import com.expediagroup.sdk.xap.model.AveragePricePerTicket
import com.expediagroup.sdk.xap.model.Fee
import com.expediagroup.sdk.xap.model.FlightsV3Money
import com.expediagroup.sdk.xap.model.PricePerPassengerCategory
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * The total price of the package if purchased as a standalone hotel and a standalone flight. (base price + taxes and fees)
 * @param totalPrice
 * @param basePrice
 * @param totalTaxesAndFees
 * @param averagePricePerTicket
 * @param pricePerPassengerCategory Container for pricing information for each passenger category. (note that passengers are grouped into standard categories based on the age ranges standardized by the airlines)
 * @param totalRefund
 * @param totalFees
 * @param fees Container for list of fees charged
 * @param totalTaxes
 * @param discount
 */
@ConsistentCopyVisibility data class OfferPrice private constructor(
    @JsonProperty("TotalPrice")
    val totalPrice: FlightsV3Money,

    @JsonProperty("BasePrice")
    val basePrice: FlightsV3Money,

    @JsonProperty("TotalTaxesAndFees")
    val totalTaxesAndFees: FlightsV3Money,

    @JsonProperty("AveragePricePerTicket")
    val averagePricePerTicket: AveragePricePerTicket,

    /* Container for pricing information for each passenger category. (note that passengers are grouped into standard categories based on the age ranges standardized by the airlines) */
    @JsonProperty("PricePerPassengerCategory")
    val pricePerPassengerCategory: kotlin.collections
        .List<
            PricePerPassengerCategory,
            >,

    @JsonProperty("TotalRefund")
    val totalRefund: FlightsV3Money? = null,

    @JsonProperty("TotalFees")
    val totalFees: FlightsV3Money? = null,

    /* Container for list of fees charged */
    @JsonProperty("Fees")
    val fees: kotlin.collections.List<Fee>? = null,

    @JsonProperty("TotalTaxes")
    val totalTaxes: FlightsV3Money? = null,

    @JsonProperty("Discount")
    val discount: FlightsV3Money? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var totalPrice: FlightsV3Money? = null,
        private var basePrice: FlightsV3Money? = null,
        private var totalTaxesAndFees: FlightsV3Money? = null,
        private var averagePricePerTicket: AveragePricePerTicket? = null,
        private var pricePerPassengerCategory: kotlin.collections.List<PricePerPassengerCategory>? = null,
        private var totalRefund: FlightsV3Money? = null,
        private var totalFees: FlightsV3Money? = null,
        private var fees: kotlin.collections.List<Fee>? = null,
        private var totalTaxes: FlightsV3Money? = null,
        private var discount: FlightsV3Money? = null,
    ) {
        fun totalPrice(totalPrice: FlightsV3Money) = apply { this.totalPrice = totalPrice }

        fun basePrice(basePrice: FlightsV3Money) = apply { this.basePrice = basePrice }

        fun totalTaxesAndFees(totalTaxesAndFees: FlightsV3Money) = apply { this.totalTaxesAndFees = totalTaxesAndFees }

        fun averagePricePerTicket(averagePricePerTicket: AveragePricePerTicket) = apply { this.averagePricePerTicket = averagePricePerTicket }

        fun pricePerPassengerCategory(pricePerPassengerCategory: kotlin.collections.List<PricePerPassengerCategory>) = apply { this.pricePerPassengerCategory = pricePerPassengerCategory }

        fun totalRefund(totalRefund: FlightsV3Money?) = apply { this.totalRefund = totalRefund }

        fun totalFees(totalFees: FlightsV3Money?) = apply { this.totalFees = totalFees }

        fun fees(fees: kotlin.collections.List<Fee>?) = apply { this.fees = fees }

        fun totalTaxes(totalTaxes: FlightsV3Money?) = apply { this.totalTaxes = totalTaxes }

        fun discount(discount: FlightsV3Money?) = apply { this.discount = discount }

        fun build(): OfferPrice {
            val totalPrice = this.totalPrice.getOrThrow {
                IllegalArgumentException("totalPrice must not be null")
            }

            val basePrice = this.basePrice.getOrThrow {
                IllegalArgumentException("basePrice must not be null")
            }

            val totalTaxesAndFees = this.totalTaxesAndFees.getOrThrow {
                IllegalArgumentException("totalTaxesAndFees must not be null")
            }

            val averagePricePerTicket = this.averagePricePerTicket.getOrThrow {
                IllegalArgumentException("averagePricePerTicket must not be null")
            }

            val pricePerPassengerCategory = this.pricePerPassengerCategory.getOrThrow {
                IllegalArgumentException("pricePerPassengerCategory must not be null")
            }

            val instance = OfferPrice(
                totalPrice = totalPrice,
                basePrice = basePrice,
                totalTaxesAndFees = totalTaxesAndFees,
                averagePricePerTicket = averagePricePerTicket,
                pricePerPassengerCategory = pricePerPassengerCategory,
                totalRefund = totalRefund,
                totalFees = totalFees,
                fees = fees,
                totalTaxes = totalTaxes,
                discount = discount,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        totalPrice = totalPrice,
        basePrice = basePrice,
        totalTaxesAndFees = totalTaxesAndFees,
        averagePricePerTicket = averagePricePerTicket,
        pricePerPassengerCategory = pricePerPassengerCategory,
        totalRefund = totalRefund,
        totalFees = totalFees,
        fees = fees,
        totalTaxes = totalTaxes,
        discount = discount,
    )
}
