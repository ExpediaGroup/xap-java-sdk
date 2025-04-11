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
import com.expediagroup.sdk.xap.models.MandatoryFeesDetail
import com.expediagroup.sdk.xap.models.RoomRatesBaseRate
import com.expediagroup.sdk.xap.models.RoomRatesNightlyRatesInner
import com.expediagroup.sdk.xap.models.RoomRatesTaxesAndFees
import com.expediagroup.sdk.xap.models.RoomRatesTaxesAndFeesDetailsInner
import com.expediagroup.sdk.xap.models.RoomRatesTotalPrice
import com.expediagroup.sdk.xap.models.RoomRatesTotalStrikeOutPrice
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 *
 * @param roomIndex Index of which of the requested rooms this entry refers to.
 * @param baseRate
 * @param taxesAndFees
 * @param totalPrice
 * @param totalStrikeOutPrice
 * @param taxesAndFeesDetails The breakdown for taxes and fees for this room for the entire stay.  Only visible by configuration. Please contact your Expedia Account Manager if you need this node.
 * @param mandatoryFeesDetails The breakdown for the taxes and fees that must be paid at the property.
 * @param nightlyRates Container for the nightly rate of current room.
 */
data class RatePlanPriceRoomRatesInner(
    // Index of which of the requested rooms this entry refers to.
    @JsonProperty("RoomIndex")
    val roomIndex: kotlin.Int? = null,
    @JsonProperty("BaseRate")
    @field:Valid
    val baseRate: RoomRatesBaseRate? = null,
    @JsonProperty("TaxesAndFees")
    @field:Valid
    val taxesAndFees: RoomRatesTaxesAndFees? = null,
    @JsonProperty("TotalPrice")
    @field:Valid
    val totalPrice: RoomRatesTotalPrice? = null,
    @JsonProperty("TotalStrikeOutPrice")
    @field:Valid
    val totalStrikeOutPrice: RoomRatesTotalStrikeOutPrice? = null,
    // The breakdown for taxes and fees for this room for the entire stay.  Only visible by configuration. Please contact your Expedia Account Manager if you need this node.
    @JsonProperty("TaxesAndFeesDetails")
    @field:Valid
    val taxesAndFeesDetails: kotlin.collections.List<RoomRatesTaxesAndFeesDetailsInner>? = null,
    // The breakdown for the taxes and fees that must be paid at the property.
    @JsonProperty("MandatoryFeesDetails")
    @field:Valid
    val mandatoryFeesDetails: kotlin.collections.List<MandatoryFeesDetail>? = null,
    // Container for the nightly rate of current room.
    @JsonProperty("NightlyRates")
    @field:Valid
    val nightlyRates: kotlin.collections.List<RoomRatesNightlyRatesInner>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var roomIndex: kotlin.Int? = null,
        private var baseRate: RoomRatesBaseRate? = null,
        private var taxesAndFees: RoomRatesTaxesAndFees? = null,
        private var totalPrice: RoomRatesTotalPrice? = null,
        private var totalStrikeOutPrice: RoomRatesTotalStrikeOutPrice? = null,
        private var taxesAndFeesDetails: kotlin.collections.List<RoomRatesTaxesAndFeesDetailsInner>? = null,
        private var mandatoryFeesDetails: kotlin.collections.List<MandatoryFeesDetail>? = null,
        private var nightlyRates: kotlin.collections.List<RoomRatesNightlyRatesInner>? = null
    ) {
        fun roomIndex(roomIndex: kotlin.Int?) = apply { this.roomIndex = roomIndex }

        fun baseRate(baseRate: RoomRatesBaseRate?) = apply { this.baseRate = baseRate }

        fun taxesAndFees(taxesAndFees: RoomRatesTaxesAndFees?) = apply { this.taxesAndFees = taxesAndFees }

        fun totalPrice(totalPrice: RoomRatesTotalPrice?) = apply { this.totalPrice = totalPrice }

        fun totalStrikeOutPrice(totalStrikeOutPrice: RoomRatesTotalStrikeOutPrice?) = apply { this.totalStrikeOutPrice = totalStrikeOutPrice }

        fun taxesAndFeesDetails(taxesAndFeesDetails: kotlin.collections.List<RoomRatesTaxesAndFeesDetailsInner>?) = apply { this.taxesAndFeesDetails = taxesAndFeesDetails }

        fun mandatoryFeesDetails(mandatoryFeesDetails: kotlin.collections.List<MandatoryFeesDetail>?) = apply { this.mandatoryFeesDetails = mandatoryFeesDetails }

        fun nightlyRates(nightlyRates: kotlin.collections.List<RoomRatesNightlyRatesInner>?) = apply { this.nightlyRates = nightlyRates }

        fun build(): RatePlanPriceRoomRatesInner {
            val instance =
                RatePlanPriceRoomRatesInner(
                    roomIndex = roomIndex,
                    baseRate = baseRate,
                    taxesAndFees = taxesAndFees,
                    totalPrice = totalPrice,
                    totalStrikeOutPrice = totalStrikeOutPrice,
                    taxesAndFeesDetails = taxesAndFeesDetails,
                    mandatoryFeesDetails = mandatoryFeesDetails,
                    nightlyRates = nightlyRates
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: RatePlanPriceRoomRatesInner) {
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
            roomIndex = roomIndex,
            baseRate = baseRate,
            taxesAndFees = taxesAndFees,
            totalPrice = totalPrice,
            totalStrikeOutPrice = totalStrikeOutPrice,
            taxesAndFeesDetails = taxesAndFeesDetails,
            mandatoryFeesDetails = mandatoryFeesDetails,
            nightlyRates = nightlyRates
        )
}
