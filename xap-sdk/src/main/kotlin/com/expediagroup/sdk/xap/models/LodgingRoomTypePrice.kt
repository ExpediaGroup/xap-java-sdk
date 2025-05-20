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
 * Container for Price information.
 * @param baseRate The price of the rate plan for all occupants, excluding taxes and fees.
 * @param taxesAndFees The total amount of taxes and fees of the rate plan for all occupants.
 * @param totalPrice The total price of the rate plan, which is equal to the sum of `BaseRate` + `TaxesAndFees`. Property mandatory fees are <u>not</u> included in this value as these are paid at the property at checkout.
 * @param avgNightlyRate The average nightly base rate per night per room of the rate plan, which is equal to the `BaseRate` divided by `StayDates` and by `room number`.
 * @param avgNightlyRateWithFees The average nightly rate per night per room of the room type, including all fees except those imposed by the government.
 * @param propertyMandatoryFees The total mandatory fees which will be charged at the property for the rate plan.
 * @param totalPriceWithPropertyFees The total price of the rate plan include property mandatory fees, which is equal to the sum of `BaseRate` + `TaxesAndFees` + `PropertyMandatoryFees`. **NOTE**: Since UK regulations require that `PropertyMandatoryFees` be included in this price, the quoted price will <u>only</u> be accurate for the day of quote. This is due to the fact that currency exchange fluctuations will change the exact amount of any `PropertyMandatoryFees` that are to be collected at the hotel during the guest's stay if the cost is converted into any other currency. **CMA Compliance Note (UK)**: Websites doing business in the UK should be displaying this value to be compliant with CMA requirements.
 * @param refundableDamageDeposit The refundable damage deposit.
 */
data class LodgingRoomTypePrice(
    // The price of the rate plan for all occupants, excluding taxes and fees.
    @JsonProperty("BaseRate")
    val baseRate: LodgingMoney? = null,
    // The total amount of taxes and fees of the rate plan for all occupants.
    @JsonProperty("TaxesAndFees")
    val taxesAndFees: LodgingMoney? = null,
    // The total price of the rate plan, which is equal to the sum of `BaseRate` + `TaxesAndFees`. Property mandatory fees are <u>not</u> included in this value as these are paid at the property at checkout.
    @JsonProperty("TotalPrice")
    val totalPrice: LodgingMoney? = null,
    // The average nightly base rate per night per room of the rate plan, which is equal to the `BaseRate` divided by `StayDates` and by `room number`.
    @JsonProperty("AvgNightlyRate")
    val avgNightlyRate: LodgingMoney? = null,
    // The average nightly rate per night per room of the room type, including all fees except those imposed by the government.
    @JsonProperty("AvgNightlyRateWithFees")
    val avgNightlyRateWithFees: LodgingMoney? = null,
    // The total mandatory fees which will be charged at the property for the rate plan.
    @JsonProperty("PropertyMandatoryFees")
    val propertyMandatoryFees: LodgingMoney? = null,
    // The total price of the rate plan include property mandatory fees, which is equal to the sum of `BaseRate` + `TaxesAndFees` + `PropertyMandatoryFees`. **NOTE**: Since UK regulations require that `PropertyMandatoryFees` be included in this price, the quoted price will <u>only</u> be accurate for the day of quote. This is due to the fact that currency exchange fluctuations will change the exact amount of any `PropertyMandatoryFees` that are to be collected at the hotel during the guest's stay if the cost is converted into any other currency. **CMA Compliance Note (UK)**: Websites doing business in the UK should be displaying this value to be compliant with CMA requirements.
    @JsonProperty("TotalPriceWithPropertyFees")
    val totalPriceWithPropertyFees: LodgingMoney? = null,
    // The refundable damage deposit.
    @JsonProperty("RefundableDamageDeposit")
    val refundableDamageDeposit: LodgingMoney? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var baseRate: LodgingMoney? = null,
        private var taxesAndFees: LodgingMoney? = null,
        private var totalPrice: LodgingMoney? = null,
        private var avgNightlyRate: LodgingMoney? = null,
        private var avgNightlyRateWithFees: LodgingMoney? = null,
        private var propertyMandatoryFees: LodgingMoney? = null,
        private var totalPriceWithPropertyFees: LodgingMoney? = null,
        private var refundableDamageDeposit: LodgingMoney? = null,
    ) {
        fun baseRate(baseRate: LodgingMoney?) = apply { this.baseRate = baseRate }

        fun taxesAndFees(taxesAndFees: LodgingMoney?) = apply { this.taxesAndFees = taxesAndFees }

        fun totalPrice(totalPrice: LodgingMoney?) = apply { this.totalPrice = totalPrice }

        fun avgNightlyRate(avgNightlyRate: LodgingMoney?) = apply { this.avgNightlyRate = avgNightlyRate }

        fun avgNightlyRateWithFees(avgNightlyRateWithFees: LodgingMoney?) = apply { this.avgNightlyRateWithFees = avgNightlyRateWithFees }

        fun propertyMandatoryFees(propertyMandatoryFees: LodgingMoney?) = apply { this.propertyMandatoryFees = propertyMandatoryFees }

        fun totalPriceWithPropertyFees(totalPriceWithPropertyFees: LodgingMoney?) = apply { this.totalPriceWithPropertyFees = totalPriceWithPropertyFees }

        fun refundableDamageDeposit(refundableDamageDeposit: LodgingMoney?) = apply { this.refundableDamageDeposit = refundableDamageDeposit }

        fun build(): LodgingRoomTypePrice {
            val instance =
                LodgingRoomTypePrice(
                    baseRate = baseRate,
                    taxesAndFees = taxesAndFees,
                    totalPrice = totalPrice,
                    avgNightlyRate = avgNightlyRate,
                    avgNightlyRateWithFees = avgNightlyRateWithFees,
                    propertyMandatoryFees = propertyMandatoryFees,
                    totalPriceWithPropertyFees = totalPriceWithPropertyFees,
                    refundableDamageDeposit = refundableDamageDeposit,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            baseRate = baseRate,
            taxesAndFees = taxesAndFees,
            totalPrice = totalPrice,
            avgNightlyRate = avgNightlyRate,
            avgNightlyRateWithFees = avgNightlyRateWithFees,
            propertyMandatoryFees = propertyMandatoryFees,
            totalPriceWithPropertyFees = totalPriceWithPropertyFees,
            refundableDamageDeposit = refundableDamageDeposit,
        )
}
