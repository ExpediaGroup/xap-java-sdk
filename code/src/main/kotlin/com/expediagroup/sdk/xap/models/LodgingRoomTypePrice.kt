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
import com.expediagroup.sdk.xap.models.LodgingRoomTypePriceAvgNightlyRate
import com.expediagroup.sdk.xap.models.LodgingRoomTypePriceAvgNightlyRateWithFees
import com.expediagroup.sdk.xap.models.LodgingRoomTypePriceBaseRate
import com.expediagroup.sdk.xap.models.LodgingRoomTypePricePropertyMandatoryFees
import com.expediagroup.sdk.xap.models.LodgingRoomTypePriceRefundableDamageDeposit
import com.expediagroup.sdk.xap.models.LodgingRoomTypePriceTaxesAndFees
import com.expediagroup.sdk.xap.models.LodgingRoomTypePriceTotalPrice
import com.expediagroup.sdk.xap.models.LodgingRoomTypePriceTotalPriceWithPropertyFees
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Container for Price information.
 * @param baseRate
 * @param taxesAndFees
 * @param totalPrice
 * @param avgNightlyRate
 * @param avgNightlyRateWithFees
 * @param propertyMandatoryFees
 * @param totalPriceWithPropertyFees
 * @param refundableDamageDeposit
 */
data class LodgingRoomTypePrice(
    @JsonProperty("BaseRate")
    @field:Valid
    val baseRate: LodgingRoomTypePriceBaseRate? = null,
    @JsonProperty("TaxesAndFees")
    @field:Valid
    val taxesAndFees: LodgingRoomTypePriceTaxesAndFees? = null,
    @JsonProperty("TotalPrice")
    @field:Valid
    val totalPrice: LodgingRoomTypePriceTotalPrice? = null,
    @JsonProperty("AvgNightlyRate")
    @field:Valid
    val avgNightlyRate: LodgingRoomTypePriceAvgNightlyRate? = null,
    @JsonProperty("AvgNightlyRateWithFees")
    @field:Valid
    val avgNightlyRateWithFees: LodgingRoomTypePriceAvgNightlyRateWithFees? = null,
    @JsonProperty("PropertyMandatoryFees")
    @field:Valid
    val propertyMandatoryFees: LodgingRoomTypePricePropertyMandatoryFees? = null,
    @JsonProperty("TotalPriceWithPropertyFees")
    @field:Valid
    val totalPriceWithPropertyFees: LodgingRoomTypePriceTotalPriceWithPropertyFees? = null,
    @JsonProperty("RefundableDamageDeposit")
    @field:Valid
    val refundableDamageDeposit: LodgingRoomTypePriceRefundableDamageDeposit? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var baseRate: LodgingRoomTypePriceBaseRate? = null,
        private var taxesAndFees: LodgingRoomTypePriceTaxesAndFees? = null,
        private var totalPrice: LodgingRoomTypePriceTotalPrice? = null,
        private var avgNightlyRate: LodgingRoomTypePriceAvgNightlyRate? = null,
        private var avgNightlyRateWithFees: LodgingRoomTypePriceAvgNightlyRateWithFees? = null,
        private var propertyMandatoryFees: LodgingRoomTypePricePropertyMandatoryFees? = null,
        private var totalPriceWithPropertyFees: LodgingRoomTypePriceTotalPriceWithPropertyFees? = null,
        private var refundableDamageDeposit: LodgingRoomTypePriceRefundableDamageDeposit? = null
    ) {
        fun baseRate(baseRate: LodgingRoomTypePriceBaseRate?) = apply { this.baseRate = baseRate }

        fun taxesAndFees(taxesAndFees: LodgingRoomTypePriceTaxesAndFees?) = apply { this.taxesAndFees = taxesAndFees }

        fun totalPrice(totalPrice: LodgingRoomTypePriceTotalPrice?) = apply { this.totalPrice = totalPrice }

        fun avgNightlyRate(avgNightlyRate: LodgingRoomTypePriceAvgNightlyRate?) = apply { this.avgNightlyRate = avgNightlyRate }

        fun avgNightlyRateWithFees(avgNightlyRateWithFees: LodgingRoomTypePriceAvgNightlyRateWithFees?) = apply { this.avgNightlyRateWithFees = avgNightlyRateWithFees }

        fun propertyMandatoryFees(propertyMandatoryFees: LodgingRoomTypePricePropertyMandatoryFees?) = apply { this.propertyMandatoryFees = propertyMandatoryFees }

        fun totalPriceWithPropertyFees(totalPriceWithPropertyFees: LodgingRoomTypePriceTotalPriceWithPropertyFees?) = apply { this.totalPriceWithPropertyFees = totalPriceWithPropertyFees }

        fun refundableDamageDeposit(refundableDamageDeposit: LodgingRoomTypePriceRefundableDamageDeposit?) = apply { this.refundableDamageDeposit = refundableDamageDeposit }

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
                    refundableDamageDeposit = refundableDamageDeposit
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: LodgingRoomTypePrice) {
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
            baseRate = baseRate,
            taxesAndFees = taxesAndFees,
            totalPrice = totalPrice,
            avgNightlyRate = avgNightlyRate,
            avgNightlyRateWithFees = avgNightlyRateWithFees,
            propertyMandatoryFees = propertyMandatoryFees,
            totalPriceWithPropertyFees = totalPriceWithPropertyFees,
            refundableDamageDeposit = refundableDamageDeposit
        )
}
