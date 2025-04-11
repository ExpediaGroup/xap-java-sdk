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
import com.expediagroup.sdk.xap.models.CarsMoney
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Pricing information for the rental.
 * @param totalPrice
 * @param ratePeriodUnitPrice
 * @param basePrice
 * @param taxesAndFees
 * @param totalPriceDueAtBooking
 */
data class Price(
    @JsonProperty("TotalPrice")
    @field:NotNull
    @field:Valid
    val totalPrice: CarsMoney,
    @JsonProperty("RatePeriodUnitPrice")
    @field:Valid
    val ratePeriodUnitPrice: CarsMoney? = null,
    @JsonProperty("BasePrice")
    @field:Valid
    val basePrice: CarsMoney? = null,
    @JsonProperty("TaxesAndFees")
    @field:Valid
    val taxesAndFees: CarsMoney? = null,
    @JsonProperty("TotalPriceDueAtBooking")
    @field:Valid
    val totalPriceDueAtBooking: CarsMoney? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var totalPrice: CarsMoney? = null,
        private var ratePeriodUnitPrice: CarsMoney? = null,
        private var basePrice: CarsMoney? = null,
        private var taxesAndFees: CarsMoney? = null,
        private var totalPriceDueAtBooking: CarsMoney? = null
    ) {
        fun totalPrice(totalPrice: CarsMoney) = apply { this.totalPrice = totalPrice }

        fun ratePeriodUnitPrice(ratePeriodUnitPrice: CarsMoney?) = apply { this.ratePeriodUnitPrice = ratePeriodUnitPrice }

        fun basePrice(basePrice: CarsMoney?) = apply { this.basePrice = basePrice }

        fun taxesAndFees(taxesAndFees: CarsMoney?) = apply { this.taxesAndFees = taxesAndFees }

        fun totalPriceDueAtBooking(totalPriceDueAtBooking: CarsMoney?) = apply { this.totalPriceDueAtBooking = totalPriceDueAtBooking }

        fun build(): Price {
            val instance =
                Price(
                    totalPrice = totalPrice!!,
                    ratePeriodUnitPrice = ratePeriodUnitPrice,
                    basePrice = basePrice,
                    taxesAndFees = taxesAndFees,
                    totalPriceDueAtBooking = totalPriceDueAtBooking
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Price) {
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
            ratePeriodUnitPrice = ratePeriodUnitPrice,
            basePrice = basePrice,
            taxesAndFees = taxesAndFees,
            totalPriceDueAtBooking = totalPriceDueAtBooking
        )
}
