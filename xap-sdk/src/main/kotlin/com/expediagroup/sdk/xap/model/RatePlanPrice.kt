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

import com.expediagroup.sdk.xap.model.Money
import com.expediagroup.sdk.xap.model.RatePlanPriceNightlyRatesInner
import com.expediagroup.sdk.xap.model.RatePlanPriceTaxesAndFeesDetailsInner
import com.expediagroup.sdk.xap.model.RoomRates
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for all price components of the rate plan.
 * @param baseRate The price of the rate plan for all occupants, excluding taxes and fees.
 * @param taxesAndFees The total rate of taxes and fees of the rate plan for all occupants.
 * @param totalPrice The total price of the rate plan, which is equal to the sum of `BaseRate` + `TaxesAndFees`. Hotel mandatory fees are not included in this value as these are paid at the hotel at checkout. Promotion amount have been deducted from the `TotalPrice` value.
 * @param totalStrikeOutPrice The total amount to strikeout price. This value is the sum of the pre-discount `BaseRate` + the pre-discount `TaxesAndFees`.
 * @param avgNightlyRate The average nightly base rate per night per room of the rate plan, which is equal to the `BaseRate` divided by `StayDates` and by `room number`.
 * @param avgNightlyStrikeOutRate The average nightly strike out price per night per room of the rate plan, which is equal to the strike out of `BaseRate` divided by `StayDates` and by `room number`.
 * @param hotelMandatoryFees The total mandatory fees which will be charged at the hotel for the rate plan.
 * @param refundableDamageDeposit The refundable damage deposit.
 * @param nightlyRates Nightly base rate of the rate plan.
 * @param taxesAndFeesDetails Container for taxes and fees detail information. Only visible by configuration. Please contact your Expedia Account Manager if you need this node.
 * @param roomRates Container for the rate information of all rooms. This is only returned in Lodging Details API.
 */
data class RatePlanPrice(
    // The price of the rate plan for all occupants, excluding taxes and fees.
    @JsonProperty("BaseRate")
    val baseRate: Money? = null,
    // The total rate of taxes and fees of the rate plan for all occupants.
    @JsonProperty("TaxesAndFees")
    val taxesAndFees: Money? = null,
    // The total price of the rate plan, which is equal to the sum of `BaseRate` + `TaxesAndFees`. Hotel mandatory fees are not included in this value as these are paid at the hotel at checkout. Promotion amount have been deducted from the `TotalPrice` value.
    @JsonProperty("TotalPrice")
    val totalPrice: Money? = null,
    // The total amount to strikeout price. This value is the sum of the pre-discount `BaseRate` + the pre-discount `TaxesAndFees`.
    @JsonProperty("TotalStrikeOutPrice")
    val totalStrikeOutPrice: Money? = null,
    // The average nightly base rate per night per room of the rate plan, which is equal to the `BaseRate` divided by `StayDates` and by `room number`.
    @JsonProperty("AvgNightlyRate")
    val avgNightlyRate: Money? = null,
    // The average nightly strike out price per night per room of the rate plan, which is equal to the strike out of `BaseRate` divided by `StayDates` and by `room number`.
    @JsonProperty("AvgNightlyStrikeOutRate")
    val avgNightlyStrikeOutRate: Money? = null,
    // The total mandatory fees which will be charged at the hotel for the rate plan.
    @JsonProperty("HotelMandatoryFees")
    val hotelMandatoryFees: Money? = null,
    // The refundable damage deposit.
    @JsonProperty("RefundableDamageDeposit")
    val refundableDamageDeposit: Money? = null,
    // Nightly base rate of the rate plan.
    @JsonProperty("NightlyRates")
    val nightlyRates: kotlin.collections.List<RatePlanPriceNightlyRatesInner>? = null,
    // Container for taxes and fees detail information. Only visible by configuration. Please contact your Expedia Account Manager if you need this node.
    @JsonProperty("TaxesAndFeesDetails")
    val taxesAndFeesDetails: kotlin.collections.List<RatePlanPriceTaxesAndFeesDetailsInner>? = null,
    // Container for the rate information of all rooms. This is only returned in Lodging Details API.
    @JsonProperty("RoomRates")
    val roomRates: kotlin.collections.List<RoomRates>? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var baseRate: Money? = null,
        private var taxesAndFees: Money? = null,
        private var totalPrice: Money? = null,
        private var totalStrikeOutPrice: Money? = null,
        private var avgNightlyRate: Money? = null,
        private var avgNightlyStrikeOutRate: Money? = null,
        private var hotelMandatoryFees: Money? = null,
        private var refundableDamageDeposit: Money? = null,
        private var nightlyRates: kotlin.collections.List<RatePlanPriceNightlyRatesInner>? = null,
        private var taxesAndFeesDetails: kotlin.collections.List<RatePlanPriceTaxesAndFeesDetailsInner>? = null,
        private var roomRates: kotlin.collections.List<RoomRates>? = null,
    ) {
        fun baseRate(baseRate: Money?) = apply { this.baseRate = baseRate }

        fun taxesAndFees(taxesAndFees: Money?) = apply { this.taxesAndFees = taxesAndFees }

        fun totalPrice(totalPrice: Money?) = apply { this.totalPrice = totalPrice }

        fun totalStrikeOutPrice(totalStrikeOutPrice: Money?) = apply { this.totalStrikeOutPrice = totalStrikeOutPrice }

        fun avgNightlyRate(avgNightlyRate: Money?) = apply { this.avgNightlyRate = avgNightlyRate }

        fun avgNightlyStrikeOutRate(avgNightlyStrikeOutRate: Money?) = apply { this.avgNightlyStrikeOutRate = avgNightlyStrikeOutRate }

        fun hotelMandatoryFees(hotelMandatoryFees: Money?) = apply { this.hotelMandatoryFees = hotelMandatoryFees }

        fun refundableDamageDeposit(refundableDamageDeposit: Money?) = apply { this.refundableDamageDeposit = refundableDamageDeposit }

        fun nightlyRates(nightlyRates: kotlin.collections.List<RatePlanPriceNightlyRatesInner>?) = apply { this.nightlyRates = nightlyRates }

        fun taxesAndFeesDetails(taxesAndFeesDetails: kotlin.collections.List<RatePlanPriceTaxesAndFeesDetailsInner>?) = apply { this.taxesAndFeesDetails = taxesAndFeesDetails }

        fun roomRates(roomRates: kotlin.collections.List<RoomRates>?) = apply { this.roomRates = roomRates }

        fun build(): RatePlanPrice {
            val instance =
                RatePlanPrice(
                    baseRate = baseRate,
                    taxesAndFees = taxesAndFees,
                    totalPrice = totalPrice,
                    totalStrikeOutPrice = totalStrikeOutPrice,
                    avgNightlyRate = avgNightlyRate,
                    avgNightlyStrikeOutRate = avgNightlyStrikeOutRate,
                    hotelMandatoryFees = hotelMandatoryFees,
                    refundableDamageDeposit = refundableDamageDeposit,
                    nightlyRates = nightlyRates,
                    taxesAndFeesDetails = taxesAndFeesDetails,
                    roomRates = roomRates,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            baseRate = baseRate,
            taxesAndFees = taxesAndFees,
            totalPrice = totalPrice,
            totalStrikeOutPrice = totalStrikeOutPrice,
            avgNightlyRate = avgNightlyRate,
            avgNightlyStrikeOutRate = avgNightlyStrikeOutRate,
            hotelMandatoryFees = hotelMandatoryFees,
            refundableDamageDeposit = refundableDamageDeposit,
            nightlyRates = nightlyRates,
            taxesAndFeesDetails = taxesAndFeesDetails,
            roomRates = roomRates,
        )
}
