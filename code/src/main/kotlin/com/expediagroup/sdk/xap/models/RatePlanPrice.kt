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
import com.expediagroup.sdk.xap.models.RatePlanPriceAvgNightlyRate
import com.expediagroup.sdk.xap.models.RatePlanPriceAvgNightlyStrikeOutRate
import com.expediagroup.sdk.xap.models.RatePlanPriceBaseRate
import com.expediagroup.sdk.xap.models.RatePlanPriceHotelMandatoryFees
import com.expediagroup.sdk.xap.models.RatePlanPriceNightlyRatesInner
import com.expediagroup.sdk.xap.models.RatePlanPriceRefundableDamageDeposit
import com.expediagroup.sdk.xap.models.RatePlanPriceRoomRatesInner
import com.expediagroup.sdk.xap.models.RatePlanPriceTaxesAndFees
import com.expediagroup.sdk.xap.models.RatePlanPriceTaxesAndFeesDetailsInner
import com.expediagroup.sdk.xap.models.RatePlanPriceTotalPrice
import com.expediagroup.sdk.xap.models.RatePlanPriceTotalStrikeOutPrice
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Container for all price components of the rate plan.
 * @param baseRate
 * @param taxesAndFees
 * @param totalPrice
 * @param totalStrikeOutPrice
 * @param avgNightlyRate
 * @param avgNightlyStrikeOutRate
 * @param hotelMandatoryFees
 * @param refundableDamageDeposit
 * @param nightlyRates Nightly base rate of the rate plan.
 * @param taxesAndFeesDetails Container for taxes and fees detail information. Only visible by configuration. Please contact your Expedia Account Manager if you need this node.
 * @param roomRates Container for the rate information of all rooms. This is only returned in Lodging Details API.
 */
data class RatePlanPrice(
    @JsonProperty("BaseRate")
    @field:Valid
    val baseRate: RatePlanPriceBaseRate? = null,
    @JsonProperty("TaxesAndFees")
    @field:Valid
    val taxesAndFees: RatePlanPriceTaxesAndFees? = null,
    @JsonProperty("TotalPrice")
    @field:Valid
    val totalPrice: RatePlanPriceTotalPrice? = null,
    @JsonProperty("TotalStrikeOutPrice")
    @field:Valid
    val totalStrikeOutPrice: RatePlanPriceTotalStrikeOutPrice? = null,
    @JsonProperty("AvgNightlyRate")
    @field:Valid
    val avgNightlyRate: RatePlanPriceAvgNightlyRate? = null,
    @JsonProperty("AvgNightlyStrikeOutRate")
    @field:Valid
    val avgNightlyStrikeOutRate: RatePlanPriceAvgNightlyStrikeOutRate? = null,
    @JsonProperty("HotelMandatoryFees")
    @field:Valid
    val hotelMandatoryFees: RatePlanPriceHotelMandatoryFees? = null,
    @JsonProperty("RefundableDamageDeposit")
    @field:Valid
    val refundableDamageDeposit: RatePlanPriceRefundableDamageDeposit? = null,
    // Nightly base rate of the rate plan.
    @JsonProperty("NightlyRates")
    @field:Valid
    val nightlyRates: kotlin.collections.List<RatePlanPriceNightlyRatesInner>? = null,
    // Container for taxes and fees detail information. Only visible by configuration. Please contact your Expedia Account Manager if you need this node.
    @JsonProperty("TaxesAndFeesDetails")
    @field:Valid
    val taxesAndFeesDetails: kotlin.collections.List<RatePlanPriceTaxesAndFeesDetailsInner>? = null,
    // Container for the rate information of all rooms. This is only returned in Lodging Details API.
    @JsonProperty("RoomRates")
    @field:Valid
    val roomRates: kotlin.collections.List<RatePlanPriceRoomRatesInner>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var baseRate: RatePlanPriceBaseRate? = null,
        private var taxesAndFees: RatePlanPriceTaxesAndFees? = null,
        private var totalPrice: RatePlanPriceTotalPrice? = null,
        private var totalStrikeOutPrice: RatePlanPriceTotalStrikeOutPrice? = null,
        private var avgNightlyRate: RatePlanPriceAvgNightlyRate? = null,
        private var avgNightlyStrikeOutRate: RatePlanPriceAvgNightlyStrikeOutRate? = null,
        private var hotelMandatoryFees: RatePlanPriceHotelMandatoryFees? = null,
        private var refundableDamageDeposit: RatePlanPriceRefundableDamageDeposit? = null,
        private var nightlyRates: kotlin.collections.List<RatePlanPriceNightlyRatesInner>? = null,
        private var taxesAndFeesDetails: kotlin.collections.List<RatePlanPriceTaxesAndFeesDetailsInner>? = null,
        private var roomRates: kotlin.collections.List<RatePlanPriceRoomRatesInner>? = null
    ) {
        fun baseRate(baseRate: RatePlanPriceBaseRate?) = apply { this.baseRate = baseRate }

        fun taxesAndFees(taxesAndFees: RatePlanPriceTaxesAndFees?) = apply { this.taxesAndFees = taxesAndFees }

        fun totalPrice(totalPrice: RatePlanPriceTotalPrice?) = apply { this.totalPrice = totalPrice }

        fun totalStrikeOutPrice(totalStrikeOutPrice: RatePlanPriceTotalStrikeOutPrice?) = apply { this.totalStrikeOutPrice = totalStrikeOutPrice }

        fun avgNightlyRate(avgNightlyRate: RatePlanPriceAvgNightlyRate?) = apply { this.avgNightlyRate = avgNightlyRate }

        fun avgNightlyStrikeOutRate(avgNightlyStrikeOutRate: RatePlanPriceAvgNightlyStrikeOutRate?) = apply { this.avgNightlyStrikeOutRate = avgNightlyStrikeOutRate }

        fun hotelMandatoryFees(hotelMandatoryFees: RatePlanPriceHotelMandatoryFees?) = apply { this.hotelMandatoryFees = hotelMandatoryFees }

        fun refundableDamageDeposit(refundableDamageDeposit: RatePlanPriceRefundableDamageDeposit?) = apply { this.refundableDamageDeposit = refundableDamageDeposit }

        fun nightlyRates(nightlyRates: kotlin.collections.List<RatePlanPriceNightlyRatesInner>?) = apply { this.nightlyRates = nightlyRates }

        fun taxesAndFeesDetails(taxesAndFeesDetails: kotlin.collections.List<RatePlanPriceTaxesAndFeesDetailsInner>?) = apply { this.taxesAndFeesDetails = taxesAndFeesDetails }

        fun roomRates(roomRates: kotlin.collections.List<RatePlanPriceRoomRatesInner>?) = apply { this.roomRates = roomRates }

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
                    roomRates = roomRates
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: RatePlanPrice) {
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
            hotelMandatoryFees = hotelMandatoryFees,
            refundableDamageDeposit = refundableDamageDeposit,
            nightlyRates = nightlyRates,
            taxesAndFeesDetails = taxesAndFeesDetails,
            roomRates = roomRates
        )
}
