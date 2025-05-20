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

import com.expediagroup.sdk.xap.model.Discount
import com.expediagroup.sdk.xap.model.Mileage
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * The rate detail information for a car offer.
 * @param ratePeriod Rate period. Supported values: Daily,Weekly,Monthly,Trip,Weekend
 * @param prePay Indicates whether this reservation should be paid at the time of booking (true) or at time of rental return (false).
 * @param rateCode Rate plan identifier.
 * @param creditCardRequired Indicates whether credit card is required for booking.
 * @param discounts List of discount information.
 * @param mileages A list of charges to be levied based on the mileage driven.
 * @param mobileRate Indicates whether car offer is mobile rate.
 */
data class RateDetails(
    // Rate period. Supported values: Daily,Weekly,Monthly,Trip,Weekend
    @JsonProperty("RatePeriod")
    val ratePeriod: kotlin.String,
    // Indicates whether this reservation should be paid at the time of booking (true) or at time of rental return (false).
    @JsonProperty("PrePay")
    val prePay: kotlin.Boolean,
    // Rate plan identifier.
    @JsonProperty("RateCode")
    val rateCode: kotlin.String? = null,
    // Indicates whether credit card is required for booking.
    @JsonProperty("CreditCardRequired")
    val creditCardRequired: kotlin.Boolean? = null,
    // List of discount information.
    @JsonProperty("Discounts")
    val discounts: kotlin.collections.List<Discount>? = null,
    // A list of charges to be levied based on the mileage driven.
    @JsonProperty("Mileages")
    val mileages: kotlin.collections.List<Mileage>? = null,
    // Indicates whether car offer is mobile rate.
    @JsonProperty("MobileRate")
    val mobileRate: kotlin.Boolean? = null,
) {
    init {
        require(ratePeriod != null) { "ratePeriod must not be null" }

        require(prePay != null) { "prePay must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var ratePeriod: kotlin.String? = null,
        private var prePay: kotlin.Boolean? = null,
        private var rateCode: kotlin.String? = null,
        private var creditCardRequired: kotlin.Boolean? = null,
        private var discounts: kotlin.collections.List<Discount>? = null,
        private var mileages: kotlin.collections.List<Mileage>? = null,
        private var mobileRate: kotlin.Boolean? = null,
    ) {
        fun ratePeriod(ratePeriod: kotlin.String) = apply { this.ratePeriod = ratePeriod }

        fun prePay(prePay: kotlin.Boolean) = apply { this.prePay = prePay }

        fun rateCode(rateCode: kotlin.String?) = apply { this.rateCode = rateCode }

        fun creditCardRequired(creditCardRequired: kotlin.Boolean?) = apply { this.creditCardRequired = creditCardRequired }

        fun discounts(discounts: kotlin.collections.List<Discount>?) = apply { this.discounts = discounts }

        fun mileages(mileages: kotlin.collections.List<Mileage>?) = apply { this.mileages = mileages }

        fun mobileRate(mobileRate: kotlin.Boolean?) = apply { this.mobileRate = mobileRate }

        fun build(): RateDetails {
            val instance =
                RateDetails(
                    ratePeriod = ratePeriod!!,
                    prePay = prePay!!,
                    rateCode = rateCode,
                    creditCardRequired = creditCardRequired,
                    discounts = discounts,
                    mileages = mileages,
                    mobileRate = mobileRate,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            ratePeriod = ratePeriod!!,
            prePay = prePay!!,
            rateCode = rateCode,
            creditCardRequired = creditCardRequired,
            discounts = discounts,
            mileages = mileages,
            mobileRate = mobileRate,
        )
}
