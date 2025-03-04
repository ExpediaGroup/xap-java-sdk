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
* Container of all price components of the room.
 * @param baseRate The price of the rate plan for all occupants, excluding taxes and fees.
 * @param taxesAndFees The total rate of taxes and fees of the rate plan for all occupants.
 * @param totalPrice The total price of the rate plan, which is equal to the sum of `BaseRate` and `TaxesAndFees`. Hotel mandatory fees are not included as these are paid at the hotel at checkout.
 * @param totalStrikeOutPrice The total strike out price of the rate plan, which is equal to the sum of `BaseRate`'s `totalStrikeOut` and `TaxesAndFees`'s `totalStrikeOut`.
 * @param avgNightlyRate The average nightly base rate per night per room of the room type, which is equal to the `BaseRate` divided by `StayDates` and by `room number`.
 * @param avgNightlyStrikeOutRate The average nightly strike out rate per night per room of the room type, which is equal to the strike out of `BaseRate` divided by `StayDates` and by `room number`.
 * @param avgNightlyRateWithFees The average nightly rate per night per room of the room type, including all fees except those imposed by the government.
 * @param avgNightlyStrikeoutRateWithFees The average nightly strike out rate per night per room of the room type, including all fees except those imposed by the government.
 * @param hotelMandatoryFees The total mandatory fees which will be charged at the hotel for the rate plan.
 * @param totalPriceWithHotelFees The total combined price that includes `TotalPrice` that will be charged by Expedia (`BaseRate` + `TaxesAndFees`) combined with any `HotelMandatoryFees` that will be charged at hotel. **NOTE**: Since UK regulations require that `HotelMandatoryFees` be included in this price, the  quoted price will <u>only</u> be accurate for the day of quote. This is due to the fact that  currency exchange fluctuations will change the exact amount of any `HotelMandatoryFees` that are to be collected at the hotel during the guest's stay if the cost is converted into any other currency. **CMA Compliance Note (UK)**: Websites doing business in the UK should be displaying this value to  be compliant with CMA requirements.
 * @param refundableDamageDeposit The refundable damage deposit for the rate plan.
*/
data class RoomTypePrice(
    // The price of the rate plan for all occupants, excluding taxes and fees.
    @JsonProperty("BaseRate")
    @field:Valid
    val baseRate: Money? = null,
    // The total rate of taxes and fees of the rate plan for all occupants.
    @JsonProperty("TaxesAndFees")
    @field:Valid
    val taxesAndFees: Money? = null,
    // The total price of the rate plan, which is equal to the sum of `BaseRate` and `TaxesAndFees`. Hotel mandatory fees are not included as these are paid at the hotel at checkout.
    @JsonProperty("TotalPrice")
    @field:Valid
    val totalPrice: Money? = null,
    // The total strike out price of the rate plan, which is equal to the sum of `BaseRate`'s `totalStrikeOut` and `TaxesAndFees`'s `totalStrikeOut`.
    @JsonProperty("TotalStrikeOutPrice")
    @field:Valid
    val totalStrikeOutPrice: Money? = null,
    // The average nightly base rate per night per room of the room type, which is equal to the `BaseRate` divided by `StayDates` and by `room number`.
    @JsonProperty("AvgNightlyRate")
    @field:Valid
    val avgNightlyRate: Money? = null,
    // The average nightly strike out rate per night per room of the room type, which is equal to the strike out of `BaseRate` divided by `StayDates` and by `room number`.
    @JsonProperty("AvgNightlyStrikeOutRate")
    @field:Valid
    val avgNightlyStrikeOutRate: Money? = null,
    // The average nightly rate per night per room of the room type, including all fees except those imposed by the government.
    @JsonProperty("AvgNightlyRateWithFees")
    @field:Valid
    val avgNightlyRateWithFees: Money? = null,
    // The average nightly strike out rate per night per room of the room type, including all fees except those imposed by the government.
    @JsonProperty("AvgNightlyStrikeoutRateWithFees")
    @field:Valid
    val avgNightlyStrikeoutRateWithFees: Money? = null,
    // The total mandatory fees which will be charged at the hotel for the rate plan.
    @JsonProperty("HotelMandatoryFees")
    @field:Valid
    val hotelMandatoryFees: Money? = null,
    // The total combined price that includes `TotalPrice` that will be charged by Expedia (`BaseRate` + `TaxesAndFees`) combined with any `HotelMandatoryFees` that will be charged at hotel. **NOTE**: Since UK regulations require that `HotelMandatoryFees` be included in this price, the  quoted price will <u>only</u> be accurate for the day of quote. This is due to the fact that  currency exchange fluctuations will change the exact amount of any `HotelMandatoryFees` that are to be collected at the hotel during the guest's stay if the cost is converted into any other currency. **CMA Compliance Note (UK)**: Websites doing business in the UK should be displaying this value to  be compliant with CMA requirements.
    @JsonProperty("TotalPriceWithHotelFees")
    @field:Valid
    val totalPriceWithHotelFees: Money? = null,
    // The refundable damage deposit for the rate plan.
    @JsonProperty("RefundableDamageDeposit")
    @field:Valid
    val refundableDamageDeposit: Money? = null
) {
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
        private var avgNightlyRateWithFees: Money? = null,
        private var avgNightlyStrikeoutRateWithFees: Money? = null,
        private var hotelMandatoryFees: Money? = null,
        private var totalPriceWithHotelFees: Money? = null,
        private var refundableDamageDeposit: Money? = null
    ) {
        fun baseRate(baseRate: Money?) = apply { this.baseRate = baseRate }

        fun taxesAndFees(taxesAndFees: Money?) = apply { this.taxesAndFees = taxesAndFees }

        fun totalPrice(totalPrice: Money?) = apply { this.totalPrice = totalPrice }

        fun totalStrikeOutPrice(totalStrikeOutPrice: Money?) = apply { this.totalStrikeOutPrice = totalStrikeOutPrice }

        fun avgNightlyRate(avgNightlyRate: Money?) = apply { this.avgNightlyRate = avgNightlyRate }

        fun avgNightlyStrikeOutRate(avgNightlyStrikeOutRate: Money?) = apply { this.avgNightlyStrikeOutRate = avgNightlyStrikeOutRate }

        fun avgNightlyRateWithFees(avgNightlyRateWithFees: Money?) = apply { this.avgNightlyRateWithFees = avgNightlyRateWithFees }

        fun avgNightlyStrikeoutRateWithFees(avgNightlyStrikeoutRateWithFees: Money?) = apply { this.avgNightlyStrikeoutRateWithFees = avgNightlyStrikeoutRateWithFees }

        fun hotelMandatoryFees(hotelMandatoryFees: Money?) = apply { this.hotelMandatoryFees = hotelMandatoryFees }

        fun totalPriceWithHotelFees(totalPriceWithHotelFees: Money?) = apply { this.totalPriceWithHotelFees = totalPriceWithHotelFees }

        fun refundableDamageDeposit(refundableDamageDeposit: Money?) = apply { this.refundableDamageDeposit = refundableDamageDeposit }

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
