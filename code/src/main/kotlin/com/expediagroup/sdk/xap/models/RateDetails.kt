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

import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.Discount
import com.expediagroup.sdk.xap.models.Mileage
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

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
    @field:NotNull
    @field:Valid
    val ratePeriod: kotlin.String,
    // Indicates whether this reservation should be paid at the time of booking (true) or at time of rental return (false).
    @JsonProperty("PrePay")
    @field:NotNull
    @field:Valid
    val prePay: kotlin.Boolean,
    // Rate plan identifier.
    @JsonProperty("RateCode")
    @field:Valid
    val rateCode: kotlin.String? = null,
    // Indicates whether credit card is required for booking.
    @JsonProperty("CreditCardRequired")
    @field:Valid
    val creditCardRequired: kotlin.Boolean? = null,
    // List of discount information.
    @JsonProperty("Discounts")
    @field:Valid
    val discounts: kotlin.collections.List<Discount>? = null,
    // A list of charges to be levied based on the mileage driven.
    @JsonProperty("Mileages")
    @field:Valid
    val mileages: kotlin.collections.List<Mileage>? = null,
    // Indicates whether car offer is mobile rate.
    @JsonProperty("MobileRate")
    @field:Valid
    val mobileRate: kotlin.Boolean? = null
) {
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
        private var mobileRate: kotlin.Boolean? = null
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
                    mobileRate = mobileRate
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: RateDetails) {
            val validator =
                Validation
                    .byDefaultProvider()
                    .configure()
                    .messageInterpolator(ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .validator

            val violations = validator.validate(instance)

            if (violations.isNotEmpty()) {
                throw PropertyConstraintViolationException(
                    constraintViolations = violations.map { "${it.propertyPath}: ${it.message}" }
                )
            }
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
            mobileRate = mobileRate
        )
}
