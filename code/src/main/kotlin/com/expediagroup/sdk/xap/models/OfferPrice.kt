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
import com.expediagroup.sdk.xap.models.AveragePricePerTicket
import com.expediagroup.sdk.xap.models.Fee
import com.expediagroup.sdk.xap.models.FlightsV3Money
import com.expediagroup.sdk.xap.models.PricePerPassengerCategory
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

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
data class OfferPrice(
    @JsonProperty("TotalPrice")
    @field:NotNull
    @field:Valid
    val totalPrice: FlightsV3Money,
    @JsonProperty("BasePrice")
    @field:NotNull
    @field:Valid
    val basePrice: FlightsV3Money,
    @JsonProperty("TotalTaxesAndFees")
    @field:NotNull
    @field:Valid
    val totalTaxesAndFees: FlightsV3Money,
    @JsonProperty("AveragePricePerTicket")
    @field:NotNull
    @field:Valid
    val averagePricePerTicket: AveragePricePerTicket,
    // Container for pricing information for each passenger category. (note that passengers are grouped into standard categories based on the age ranges standardized by the airlines)
    @JsonProperty("PricePerPassengerCategory")
    @field:NotNull
    @field:Valid
    val pricePerPassengerCategory: kotlin.collections
        .List<
            PricePerPassengerCategory
        >,
    @JsonProperty("TotalRefund")
    @field:Valid
    val totalRefund: FlightsV3Money? = null,
    @JsonProperty("TotalFees")
    @field:Valid
    val totalFees: FlightsV3Money? = null,
    // Container for list of fees charged
    @JsonProperty("Fees")
    @field:Valid
    val fees: kotlin.collections.List<Fee>? = null,
    @JsonProperty("TotalTaxes")
    @field:Valid
    val totalTaxes: FlightsV3Money? = null,
    @JsonProperty("Discount")
    @field:Valid
    val discount: FlightsV3Money? = null
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
        private var discount: FlightsV3Money? = null
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
            val instance =
                OfferPrice(
                    totalPrice = totalPrice!!,
                    basePrice = basePrice!!,
                    totalTaxesAndFees = totalTaxesAndFees!!,
                    averagePricePerTicket = averagePricePerTicket!!,
                    pricePerPassengerCategory = pricePerPassengerCategory!!,
                    totalRefund = totalRefund,
                    totalFees = totalFees,
                    fees = fees,
                    totalTaxes = totalTaxes,
                    discount = discount
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: OfferPrice) {
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
            totalPrice = totalPrice!!,
            basePrice = basePrice!!,
            totalTaxesAndFees = totalTaxesAndFees!!,
            averagePricePerTicket = averagePricePerTicket!!,
            pricePerPassengerCategory = pricePerPassengerCategory!!,
            totalRefund = totalRefund,
            totalFees = totalFees,
            fees = fees,
            totalTaxes = totalTaxes,
            discount = discount
        )
}
