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
import com.expediagroup.sdk.xap.models.RatePlanStandalonePriceAvgNightlyRate
import com.expediagroup.sdk.xap.models.RatePlanStandalonePriceAvgNightlyStrikeOutRate
import com.expediagroup.sdk.xap.models.RatePlanStandalonePriceBaseRate
import com.expediagroup.sdk.xap.models.RatePlanStandalonePriceHotelMandatoryFees
import com.expediagroup.sdk.xap.models.RatePlanStandalonePriceStrikeOutBaseRate
import com.expediagroup.sdk.xap.models.RatePlanStandalonePriceStrikeOutHotelMandatoryFees
import com.expediagroup.sdk.xap.models.RatePlanStandalonePriceStrikeOutTaxesAndFees
import com.expediagroup.sdk.xap.models.RatePlanStandalonePriceTaxesAndFees
import com.expediagroup.sdk.xap.models.RatePlanStandalonePriceTotalPrice
import com.expediagroup.sdk.xap.models.RatePlanStandalonePriceTotalStrikeOutPrice
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * The corresponded standalone price to the package rate plan (to show the `strikethrough`).  Only returned when the returned `rateplan` is being used as part of a package.
 * @param baseRate
 * @param strikeOutBaseRate
 * @param taxesAndFees
 * @param strikeOutTaxesAndFees
 * @param totalPrice
 * @param totalStrikeOutPrice
 * @param avgNightlyRate
 * @param avgNightlyStrikeOutRate
 * @param hotelMandatoryFees
 * @param strikeOutHotelMandatoryFees
 */
data class RatePlanStandalonePrice(
    @JsonProperty("BaseRate")
    @field:Valid
    val baseRate: RatePlanStandalonePriceBaseRate? = null,
    @JsonProperty("StrikeOutBaseRate")
    @field:Valid
    val strikeOutBaseRate: RatePlanStandalonePriceStrikeOutBaseRate? = null,
    @JsonProperty("TaxesAndFees")
    @field:Valid
    val taxesAndFees: RatePlanStandalonePriceTaxesAndFees? = null,
    @JsonProperty("StrikeOutTaxesAndFees")
    @field:Valid
    val strikeOutTaxesAndFees: RatePlanStandalonePriceStrikeOutTaxesAndFees? = null,
    @JsonProperty("TotalPrice")
    @field:Valid
    val totalPrice: RatePlanStandalonePriceTotalPrice? = null,
    @JsonProperty("TotalStrikeOutPrice")
    @field:Valid
    val totalStrikeOutPrice: RatePlanStandalonePriceTotalStrikeOutPrice? = null,
    @JsonProperty("AvgNightlyRate")
    @field:Valid
    val avgNightlyRate: RatePlanStandalonePriceAvgNightlyRate? = null,
    @JsonProperty("AvgNightlyStrikeOutRate")
    @field:Valid
    val avgNightlyStrikeOutRate: RatePlanStandalonePriceAvgNightlyStrikeOutRate? = null,
    @JsonProperty("HotelMandatoryFees")
    @field:Valid
    val hotelMandatoryFees: RatePlanStandalonePriceHotelMandatoryFees? = null,
    @JsonProperty("StrikeOutHotelMandatoryFees")
    @field:Valid
    val strikeOutHotelMandatoryFees: RatePlanStandalonePriceStrikeOutHotelMandatoryFees? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var baseRate: RatePlanStandalonePriceBaseRate? = null,
        private var strikeOutBaseRate: RatePlanStandalonePriceStrikeOutBaseRate? = null,
        private var taxesAndFees: RatePlanStandalonePriceTaxesAndFees? = null,
        private var strikeOutTaxesAndFees: RatePlanStandalonePriceStrikeOutTaxesAndFees? = null,
        private var totalPrice: RatePlanStandalonePriceTotalPrice? = null,
        private var totalStrikeOutPrice: RatePlanStandalonePriceTotalStrikeOutPrice? = null,
        private var avgNightlyRate: RatePlanStandalonePriceAvgNightlyRate? = null,
        private var avgNightlyStrikeOutRate: RatePlanStandalonePriceAvgNightlyStrikeOutRate? = null,
        private var hotelMandatoryFees: RatePlanStandalonePriceHotelMandatoryFees? = null,
        private var strikeOutHotelMandatoryFees: RatePlanStandalonePriceStrikeOutHotelMandatoryFees? = null
    ) {
        fun baseRate(baseRate: RatePlanStandalonePriceBaseRate?) = apply { this.baseRate = baseRate }

        fun strikeOutBaseRate(strikeOutBaseRate: RatePlanStandalonePriceStrikeOutBaseRate?) = apply { this.strikeOutBaseRate = strikeOutBaseRate }

        fun taxesAndFees(taxesAndFees: RatePlanStandalonePriceTaxesAndFees?) = apply { this.taxesAndFees = taxesAndFees }

        fun strikeOutTaxesAndFees(strikeOutTaxesAndFees: RatePlanStandalonePriceStrikeOutTaxesAndFees?) = apply { this.strikeOutTaxesAndFees = strikeOutTaxesAndFees }

        fun totalPrice(totalPrice: RatePlanStandalonePriceTotalPrice?) = apply { this.totalPrice = totalPrice }

        fun totalStrikeOutPrice(totalStrikeOutPrice: RatePlanStandalonePriceTotalStrikeOutPrice?) = apply { this.totalStrikeOutPrice = totalStrikeOutPrice }

        fun avgNightlyRate(avgNightlyRate: RatePlanStandalonePriceAvgNightlyRate?) = apply { this.avgNightlyRate = avgNightlyRate }

        fun avgNightlyStrikeOutRate(avgNightlyStrikeOutRate: RatePlanStandalonePriceAvgNightlyStrikeOutRate?) = apply { this.avgNightlyStrikeOutRate = avgNightlyStrikeOutRate }

        fun hotelMandatoryFees(hotelMandatoryFees: RatePlanStandalonePriceHotelMandatoryFees?) = apply { this.hotelMandatoryFees = hotelMandatoryFees }

        fun strikeOutHotelMandatoryFees(strikeOutHotelMandatoryFees: RatePlanStandalonePriceStrikeOutHotelMandatoryFees?) =
            apply {
                this.strikeOutHotelMandatoryFees =
                    strikeOutHotelMandatoryFees
            }

        fun build(): RatePlanStandalonePrice {
            val instance =
                RatePlanStandalonePrice(
                    baseRate = baseRate,
                    strikeOutBaseRate = strikeOutBaseRate,
                    taxesAndFees = taxesAndFees,
                    strikeOutTaxesAndFees = strikeOutTaxesAndFees,
                    totalPrice = totalPrice,
                    totalStrikeOutPrice = totalStrikeOutPrice,
                    avgNightlyRate = avgNightlyRate,
                    avgNightlyStrikeOutRate = avgNightlyStrikeOutRate,
                    hotelMandatoryFees = hotelMandatoryFees,
                    strikeOutHotelMandatoryFees = strikeOutHotelMandatoryFees
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: RatePlanStandalonePrice) {
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
            strikeOutBaseRate = strikeOutBaseRate,
            taxesAndFees = taxesAndFees,
            strikeOutTaxesAndFees = strikeOutTaxesAndFees,
            totalPrice = totalPrice,
            totalStrikeOutPrice = totalStrikeOutPrice,
            avgNightlyRate = avgNightlyRate,
            avgNightlyStrikeOutRate = avgNightlyStrikeOutRate,
            hotelMandatoryFees = hotelMandatoryFees,
            strikeOutHotelMandatoryFees = strikeOutHotelMandatoryFees
        )
}
