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
import com.expediagroup.sdk.xap.models.RatePlanPriceBaseRate
import com.expediagroup.sdk.xap.models.RatePlanPriceHotelMandatoryFees
import com.expediagroup.sdk.xap.models.RatePlanPriceTaxesAndFees
import com.expediagroup.sdk.xap.models.RoomTypePriceAvgNightlyRate
import com.expediagroup.sdk.xap.models.RoomTypePriceAvgNightlyRateWithFees
import com.expediagroup.sdk.xap.models.RoomTypePriceAvgNightlyStrikeOutRate
import com.expediagroup.sdk.xap.models.RoomTypePriceAvgNightlyStrikeoutRateWithFees
import com.expediagroup.sdk.xap.models.RoomTypePriceRefundableDamageDeposit
import com.expediagroup.sdk.xap.models.RoomTypePriceTotalPrice
import com.expediagroup.sdk.xap.models.RoomTypePriceTotalPriceWithHotelFees
import com.expediagroup.sdk.xap.models.RoomTypePriceTotalStrikeOutPrice
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Container of all price components of the room.
 * @param baseRate
 * @param taxesAndFees
 * @param totalPrice
 * @param totalStrikeOutPrice
 * @param avgNightlyRate
 * @param avgNightlyStrikeOutRate
 * @param avgNightlyRateWithFees
 * @param avgNightlyStrikeoutRateWithFees
 * @param hotelMandatoryFees
 * @param totalPriceWithHotelFees
 * @param refundableDamageDeposit
 */
data class RoomTypePrice(
    @JsonProperty("BaseRate")
    @field:Valid
    val baseRate: RatePlanPriceBaseRate? = null,
    @JsonProperty("TaxesAndFees")
    @field:Valid
    val taxesAndFees: RatePlanPriceTaxesAndFees? = null,
    @JsonProperty("TotalPrice")
    @field:Valid
    val totalPrice: RoomTypePriceTotalPrice? = null,
    @JsonProperty("TotalStrikeOutPrice")
    @field:Valid
    val totalStrikeOutPrice: RoomTypePriceTotalStrikeOutPrice? = null,
    @JsonProperty("AvgNightlyRate")
    @field:Valid
    val avgNightlyRate: RoomTypePriceAvgNightlyRate? = null,
    @JsonProperty("AvgNightlyStrikeOutRate")
    @field:Valid
    val avgNightlyStrikeOutRate: RoomTypePriceAvgNightlyStrikeOutRate? = null,
    @JsonProperty("AvgNightlyRateWithFees")
    @field:Valid
    val avgNightlyRateWithFees: RoomTypePriceAvgNightlyRateWithFees? = null,
    @JsonProperty("AvgNightlyStrikeoutRateWithFees")
    @field:Valid
    val avgNightlyStrikeoutRateWithFees: RoomTypePriceAvgNightlyStrikeoutRateWithFees? = null,
    @JsonProperty("HotelMandatoryFees")
    @field:Valid
    val hotelMandatoryFees: RatePlanPriceHotelMandatoryFees? = null,
    @JsonProperty("TotalPriceWithHotelFees")
    @field:Valid
    val totalPriceWithHotelFees: RoomTypePriceTotalPriceWithHotelFees? = null,
    @JsonProperty("RefundableDamageDeposit")
    @field:Valid
    val refundableDamageDeposit: RoomTypePriceRefundableDamageDeposit? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var baseRate: RatePlanPriceBaseRate? = null,
        private var taxesAndFees: RatePlanPriceTaxesAndFees? = null,
        private var totalPrice: RoomTypePriceTotalPrice? = null,
        private var totalStrikeOutPrice: RoomTypePriceTotalStrikeOutPrice? = null,
        private var avgNightlyRate: RoomTypePriceAvgNightlyRate? = null,
        private var avgNightlyStrikeOutRate: RoomTypePriceAvgNightlyStrikeOutRate? = null,
        private var avgNightlyRateWithFees: RoomTypePriceAvgNightlyRateWithFees? = null,
        private var avgNightlyStrikeoutRateWithFees: RoomTypePriceAvgNightlyStrikeoutRateWithFees? = null,
        private var hotelMandatoryFees: RatePlanPriceHotelMandatoryFees? = null,
        private var totalPriceWithHotelFees: RoomTypePriceTotalPriceWithHotelFees? = null,
        private var refundableDamageDeposit: RoomTypePriceRefundableDamageDeposit? = null
    ) {
        fun baseRate(baseRate: RatePlanPriceBaseRate?) = apply { this.baseRate = baseRate }

        fun taxesAndFees(taxesAndFees: RatePlanPriceTaxesAndFees?) = apply { this.taxesAndFees = taxesAndFees }

        fun totalPrice(totalPrice: RoomTypePriceTotalPrice?) = apply { this.totalPrice = totalPrice }

        fun totalStrikeOutPrice(totalStrikeOutPrice: RoomTypePriceTotalStrikeOutPrice?) = apply { this.totalStrikeOutPrice = totalStrikeOutPrice }

        fun avgNightlyRate(avgNightlyRate: RoomTypePriceAvgNightlyRate?) = apply { this.avgNightlyRate = avgNightlyRate }

        fun avgNightlyStrikeOutRate(avgNightlyStrikeOutRate: RoomTypePriceAvgNightlyStrikeOutRate?) = apply { this.avgNightlyStrikeOutRate = avgNightlyStrikeOutRate }

        fun avgNightlyRateWithFees(avgNightlyRateWithFees: RoomTypePriceAvgNightlyRateWithFees?) = apply { this.avgNightlyRateWithFees = avgNightlyRateWithFees }

        fun avgNightlyStrikeoutRateWithFees(avgNightlyStrikeoutRateWithFees: RoomTypePriceAvgNightlyStrikeoutRateWithFees?) =
            apply {
                this.avgNightlyStrikeoutRateWithFees =
                    avgNightlyStrikeoutRateWithFees
            }

        fun hotelMandatoryFees(hotelMandatoryFees: RatePlanPriceHotelMandatoryFees?) = apply { this.hotelMandatoryFees = hotelMandatoryFees }

        fun totalPriceWithHotelFees(totalPriceWithHotelFees: RoomTypePriceTotalPriceWithHotelFees?) = apply { this.totalPriceWithHotelFees = totalPriceWithHotelFees }

        fun refundableDamageDeposit(refundableDamageDeposit: RoomTypePriceRefundableDamageDeposit?) = apply { this.refundableDamageDeposit = refundableDamageDeposit }

        fun build(): RoomTypePrice {
            val instance =
                RoomTypePrice(
                    baseRate = baseRate,
                    taxesAndFees = taxesAndFees,
                    totalPrice = totalPrice,
                    totalStrikeOutPrice = totalStrikeOutPrice,
                    avgNightlyRate = avgNightlyRate,
                    avgNightlyStrikeOutRate = avgNightlyStrikeOutRate,
                    avgNightlyRateWithFees = avgNightlyRateWithFees,
                    avgNightlyStrikeoutRateWithFees = avgNightlyStrikeoutRateWithFees,
                    hotelMandatoryFees = hotelMandatoryFees,
                    totalPriceWithHotelFees = totalPriceWithHotelFees,
                    refundableDamageDeposit = refundableDamageDeposit
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: RoomTypePrice) {
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
            totalStrikeOutPrice = totalStrikeOutPrice,
            avgNightlyRate = avgNightlyRate,
            avgNightlyStrikeOutRate = avgNightlyStrikeOutRate,
            avgNightlyRateWithFees = avgNightlyRateWithFees,
            avgNightlyStrikeoutRateWithFees = avgNightlyStrikeoutRateWithFees,
            hotelMandatoryFees = hotelMandatoryFees,
            totalPriceWithHotelFees = totalPriceWithHotelFees,
            refundableDamageDeposit = refundableDamageDeposit
        )
}
