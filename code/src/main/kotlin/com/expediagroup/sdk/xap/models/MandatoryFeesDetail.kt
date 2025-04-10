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
import com.expediagroup.sdk.xap.models.MandatoryFeesDetailAmount
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * The breakdown for the taxes and fees that must be paid at the property.
 * @param type Type of mandatory fee.
 * @param amount
 */
data class MandatoryFeesDetail(
    // Type of mandatory fee.
    @JsonProperty("Type")
    val type: MandatoryFeesDetail.Type? = null,
    @JsonProperty("Amount")
    @field:Valid
    val amount: MandatoryFeesDetailAmount? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: MandatoryFeesDetail.Type? = null,
        private var amount: MandatoryFeesDetailAmount? = null
    ) {
        fun type(type: MandatoryFeesDetail.Type?) = apply { this.type = type }

        fun amount(amount: MandatoryFeesDetailAmount?) = apply { this.amount = amount }

        fun build(): MandatoryFeesDetail {
            val instance =
                MandatoryFeesDetail(
                    type = type,
                    amount = amount
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: MandatoryFeesDetail) {
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
            type = type,
            amount = amount
        )

    /**
     * Type of mandatory fee.
     * Values: CITY_LOCAL_TAX_AMT,CITY_LOCAL_TAX_PCNT,CLEANING_FEE_AMT,CLEANING_FEE_PCNT,CLUB_CARD_ADULT,CLUB_CARD_CHILD,DESTINATION_FEE_AMT,DESTINATION_FEE_PCNT,GALA_DINNER_ADULT,GALA_DINNER_CHILD,GALA_DINNER_CHINESE_NY_ADULT,GALA_DINNER_CHINESE_NY_CHILD,GALA_DINNER_NY_DAY_ADULT,GALA_DINNER_NY_DAY_CHILD,GALA_DINNER_NY_EVE_ADULT,GALA_DINNER_NY_EVE_CHILD,GALA_DINNER_VALENTINES_DAY_ADULT,GALA_DINNER_VALENTINES_DAY_CHILD,GALA_DINNER_XMAS_DAY_ADULT,GALA_DINNER_XMAS_DAY_CHILD,GALA_DINNER_XMAS_EVE_ADULT,GALA_DINNER_XMAS_EVE_CHILD,RESORT_FEE_AMT,RESORT_FEE_PCNT,SANITATION_FEE,SEASONAL_HEATING_FEE,TOURISM_FEE_AMT,TOURISM_FEE_PCNT,TOWEL_SHEETS_FEE_AMT,TRANSFER_FEE_AMT_ADULT,TRANSFER_FEE_AMT_CHILD,UTILITY_SURCHARGE
     */
    enum class Type(val value: kotlin.String) {
        @JsonProperty("City_LocalTax_Amt")
        CITY_LOCAL_TAX_AMT("City_LocalTax_Amt"),

        @JsonProperty("City_LocalTax_Pcnt")
        CITY_LOCAL_TAX_PCNT("City_LocalTax_Pcnt"),

        @JsonProperty("CleaningFee_Amt")
        CLEANING_FEE_AMT("CleaningFee_Amt"),

        @JsonProperty("CleaningFee_Pcnt")
        CLEANING_FEE_PCNT("CleaningFee_Pcnt"),

        @JsonProperty("ClubCardAdult")
        CLUB_CARD_ADULT("ClubCardAdult"),

        @JsonProperty("ClubCardChild")
        CLUB_CARD_CHILD("ClubCardChild"),

        @JsonProperty("DestinationFee_Amt")
        DESTINATION_FEE_AMT("DestinationFee_Amt"),

        @JsonProperty("DestinationFee_Pcnt")
        DESTINATION_FEE_PCNT("DestinationFee_Pcnt"),

        @JsonProperty("GalaDinnerAdult")
        GALA_DINNER_ADULT("GalaDinnerAdult"),

        @JsonProperty("GalaDinnerChild")
        GALA_DINNER_CHILD("GalaDinnerChild"),

        @JsonProperty("GalaDinnerChineseNYAdult")
        GALA_DINNER_CHINESE_NY_ADULT("GalaDinnerChineseNYAdult"),

        @JsonProperty("GalaDinnerChineseNYChild")
        GALA_DINNER_CHINESE_NY_CHILD("GalaDinnerChineseNYChild"),

        @JsonProperty("GalaDinnerNYDayAdult")
        GALA_DINNER_NY_DAY_ADULT("GalaDinnerNYDayAdult"),

        @JsonProperty("GalaDinnerNYDayChild")
        GALA_DINNER_NY_DAY_CHILD("GalaDinnerNYDayChild"),

        @JsonProperty("GalaDinnerNYEveAdult")
        GALA_DINNER_NY_EVE_ADULT("GalaDinnerNYEveAdult"),

        @JsonProperty("GalaDinnerNYEveChild")
        GALA_DINNER_NY_EVE_CHILD("GalaDinnerNYEveChild"),

        @JsonProperty("GalaDinnerValentinesDayAdult")
        GALA_DINNER_VALENTINES_DAY_ADULT("GalaDinnerValentinesDayAdult"),

        @JsonProperty("GalaDinnerValentinesDayChild")
        GALA_DINNER_VALENTINES_DAY_CHILD("GalaDinnerValentinesDayChild"),

        @JsonProperty("GalaDinnerXMASDayAdult")
        GALA_DINNER_XMAS_DAY_ADULT("GalaDinnerXMASDayAdult"),

        @JsonProperty("GalaDinnerXMASDayChild")
        GALA_DINNER_XMAS_DAY_CHILD("GalaDinnerXMASDayChild"),

        @JsonProperty("GalaDinnerXMASEveAdult")
        GALA_DINNER_XMAS_EVE_ADULT("GalaDinnerXMASEveAdult"),

        @JsonProperty("GalaDinnerXMASEveChild")
        GALA_DINNER_XMAS_EVE_CHILD("GalaDinnerXMASEveChild"),

        @JsonProperty("ResortFee_Amt")
        RESORT_FEE_AMT("ResortFee_Amt"),

        @JsonProperty("ResortFee_Pcnt")
        RESORT_FEE_PCNT("ResortFee_Pcnt"),

        @JsonProperty("SanitationFee")
        SANITATION_FEE("SanitationFee"),

        @JsonProperty("SeasonalHeatingFee")
        SEASONAL_HEATING_FEE("SeasonalHeatingFee"),

        @JsonProperty("TourismFee_Amt")
        TOURISM_FEE_AMT("TourismFee_Amt"),

        @JsonProperty("TourismFee_Pcnt")
        TOURISM_FEE_PCNT("TourismFee_Pcnt"),

        @JsonProperty("TowelSheetsFee_Amt")
        TOWEL_SHEETS_FEE_AMT("TowelSheetsFee_Amt"),

        @JsonProperty("TransferFee_Amt_Adult")
        TRANSFER_FEE_AMT_ADULT("TransferFee_Amt_Adult"),

        @JsonProperty("TransferFee_Amt_Child")
        TRANSFER_FEE_AMT_CHILD("TransferFee_Amt_Child"),

        @JsonProperty("UtilitySurcharge")
        UTILITY_SURCHARGE("UtilitySurcharge")
    }
}
