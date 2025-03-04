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

import com.expediagroup.sdk.rest.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.Money
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
* The corresponded standalone price to the package rate plan (to show the `strikethrough`).  Only returned when this is a package rate plan.
 * @param baseRate The standalone price of the rate plan for all occupants, excluding taxes and fees.
 * @param strikeOutBaseRate The base rate strikeout in the standalone shopping path.
 * @param taxesAndFees The total standalone rate of taxes and fees of the rate plan for all occupants.
 * @param strikeOutTaxesAndFees The taxes and fees strikeout in the standalone shopping path.
 * @param totalPrice The total standalone price of the rate plan, which is equal to the sum of `BaseRate` and `TaxesAndFees`. Hotel mandatory fees are not included as these are paid at the hotel at checkout.
 * @param totalStrikeOutPrice The total strikeout in the standalone shopping path, which is equal to the sum of `StrikeOutBaseRate` and `StrikeOutTaxesAndFees`.
 * @param avgNightlyRate The average standalone nightly base rate per night per room of the rate plan, which is equal to the `BaseRate` divided by `StayDates` and by `room number`.
 * @param avgNightlyStrikeOutRate The average strikeout of the base rate in the standalone shopping path, which is per night per room and is equal to `StrikeOutBaseRate` divided by `StayDates` and by `room number`.
 * @param hotelMandatoryFees The total standalone mandatory fees.
 * @param strikeOutHotelMandatoryFees The strikeout of the mandatory fees in the standalone shopping path.
*/
data class RoomTypeStandalonePrice(
    // The standalone price of the rate plan for all occupants, excluding taxes and fees.
    @JsonProperty("BaseRate")
    @field:Valid
    val baseRate: Money? = null,
    // The base rate strikeout in the standalone shopping path.
    @JsonProperty("StrikeOutBaseRate")
    @field:Valid
    val strikeOutBaseRate: Money? = null,
    // The total standalone rate of taxes and fees of the rate plan for all occupants.
    @JsonProperty("TaxesAndFees")
    @field:Valid
    val taxesAndFees: Money? = null,
    // The taxes and fees strikeout in the standalone shopping path.
    @JsonProperty("StrikeOutTaxesAndFees")
    @field:Valid
    val strikeOutTaxesAndFees: Money? = null,
    // The total standalone price of the rate plan, which is equal to the sum of `BaseRate` and `TaxesAndFees`. Hotel mandatory fees are not included as these are paid at the hotel at checkout.
    @JsonProperty("TotalPrice")
    @field:Valid
    val totalPrice: Money? = null,
    // The total strikeout in the standalone shopping path, which is equal to the sum of `StrikeOutBaseRate` and `StrikeOutTaxesAndFees`.
    @JsonProperty("TotalStrikeOutPrice")
    @field:Valid
    val totalStrikeOutPrice: Money? = null,
    // The average standalone nightly base rate per night per room of the rate plan, which is equal to the `BaseRate` divided by `StayDates` and by `room number`.
    @JsonProperty("AvgNightlyRate")
    @field:Valid
    val avgNightlyRate: Money? = null,
    // The average strikeout of the base rate in the standalone shopping path, which is per night per room and is equal to `StrikeOutBaseRate` divided by `StayDates` and by `room number`.
    @JsonProperty("AvgNightlyStrikeOutRate")
    @field:Valid
    val avgNightlyStrikeOutRate: Money? = null,
    // The total standalone mandatory fees.
    @JsonProperty("HotelMandatoryFees")
    @field:Valid
    val hotelMandatoryFees: Money? = null,
    // The strikeout of the mandatory fees in the standalone shopping path.
    @JsonProperty("StrikeOutHotelMandatoryFees")
    @field:Valid
    val strikeOutHotelMandatoryFees: Money? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var baseRate: Money? = null,
        private var strikeOutBaseRate: Money? = null,
        private var taxesAndFees: Money? = null,
        private var strikeOutTaxesAndFees: Money? = null,
        private var totalPrice: Money? = null,
        private var totalStrikeOutPrice: Money? = null,
        private var avgNightlyRate: Money? = null,
        private var avgNightlyStrikeOutRate: Money? = null,
        private var hotelMandatoryFees: Money? = null,
        private var strikeOutHotelMandatoryFees: Money? = null
    ) {
        fun baseRate(baseRate: Money?) = apply { this.baseRate = baseRate }

        fun strikeOutBaseRate(strikeOutBaseRate: Money?) = apply { this.strikeOutBaseRate = strikeOutBaseRate }

        fun taxesAndFees(taxesAndFees: Money?) = apply { this.taxesAndFees = taxesAndFees }

        fun strikeOutTaxesAndFees(strikeOutTaxesAndFees: Money?) = apply { this.strikeOutTaxesAndFees = strikeOutTaxesAndFees }

        fun totalPrice(totalPrice: Money?) = apply { this.totalPrice = totalPrice }

        fun totalStrikeOutPrice(totalStrikeOutPrice: Money?) = apply { this.totalStrikeOutPrice = totalStrikeOutPrice }

        fun avgNightlyRate(avgNightlyRate: Money?) = apply { this.avgNightlyRate = avgNightlyRate }

        fun avgNightlyStrikeOutRate(avgNightlyStrikeOutRate: Money?) = apply { this.avgNightlyStrikeOutRate = avgNightlyStrikeOutRate }

        fun hotelMandatoryFees(hotelMandatoryFees: Money?) = apply { this.hotelMandatoryFees = hotelMandatoryFees }

        fun strikeOutHotelMandatoryFees(strikeOutHotelMandatoryFees: Money?) = apply { this.strikeOutHotelMandatoryFees = strikeOutHotelMandatoryFees }

        fun build(): RoomTypeStandalonePrice {
            val instance =
                RoomTypeStandalonePrice(
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

        private fun validate(instance: RoomTypeStandalonePrice) {
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
