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
import com.expediagroup.sdk.xap.models.Fee
import com.expediagroup.sdk.xap.models.FlightsV3Money
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container for pricing information for each passenger category. (note that passengers are grouped into standard categories based on the age ranges standardized by the airlines)
 * @param category Passenger category
 * @param count Total number of travelers of the same passenger category
 * @param basePrice
 * @param totalPrice
 * @param totalTaxes
 * @param fees List of fees per passenger category
 */
data class PricePerPassengerCategory(
    // Passenger category
    @JsonProperty("Category")
    @field:NotNull
    val category: PricePerPassengerCategory.Category,
    // Total number of travelers of the same passenger category
    @JsonProperty("Count")
    val count: kotlin.Int,
    @JsonProperty("BasePrice")
    @field:NotNull
    @field:Valid
    val basePrice: FlightsV3Money,
    @JsonProperty("TotalPrice")
    @field:Valid
    val totalPrice: FlightsV3Money? = null,
    @JsonProperty("TotalTaxes")
    @field:Valid
    val totalTaxes: FlightsV3Money? = null,
    // List of fees per passenger category
    @JsonProperty("Fees")
    @field:Valid
    val fees: kotlin.collections.List<Fee>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var category: PricePerPassengerCategory.Category? = null,
        private var count: kotlin.Int? = null,
        private var basePrice: FlightsV3Money? = null,
        private var totalPrice: FlightsV3Money? = null,
        private var totalTaxes: FlightsV3Money? = null,
        private var fees: kotlin.collections.List<Fee>? = null
    ) {
        fun category(category: PricePerPassengerCategory.Category) = apply { this.category = category }

        fun count(count: kotlin.Int) = apply { this.count = count }

        fun basePrice(basePrice: FlightsV3Money) = apply { this.basePrice = basePrice }

        fun totalPrice(totalPrice: FlightsV3Money?) = apply { this.totalPrice = totalPrice }

        fun totalTaxes(totalTaxes: FlightsV3Money?) = apply { this.totalTaxes = totalTaxes }

        fun fees(fees: kotlin.collections.List<Fee>?) = apply { this.fees = fees }

        fun build(): PricePerPassengerCategory {
            val instance =
                PricePerPassengerCategory(
                    category = category!!,
                    count = count!!,
                    basePrice = basePrice!!,
                    totalPrice = totalPrice,
                    totalTaxes = totalTaxes,
                    fees = fees
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: PricePerPassengerCategory) {
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
            category = category!!,
            count = count!!,
            basePrice = basePrice!!,
            totalPrice = totalPrice,
            totalTaxes = totalTaxes,
            fees = fees
        )

    /**
     * Passenger category
     * Values: ADULT,SENIOR,ADULT_CHILD,CHILD,INFANT_IN_SEAT,INFANT_IN_LAP
     */
    enum class Category(val value: kotlin.String) {
        @JsonProperty("ADULT")
        ADULT("ADULT"),

        @JsonProperty("SENIOR")
        SENIOR("SENIOR"),

        @JsonProperty("ADULT_CHILD")
        ADULT_CHILD("ADULT_CHILD"),

        @JsonProperty("CHILD")
        CHILD("CHILD"),

        @JsonProperty("INFANT_IN_SEAT")
        INFANT_IN_SEAT("INFANT_IN_SEAT"),

        @JsonProperty("INFANT_IN_LAP")
        INFANT_IN_LAP("INFANT_IN_LAP")
    }
}
