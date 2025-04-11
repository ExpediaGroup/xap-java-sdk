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
import com.expediagroup.sdk.xap.models.FlightsV3Money
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container for offer price information.
 * @param totalPrice
 */
data class FareCalendarResponseOffersInnerOfferPrice(
    @JsonProperty("TotalPrice")
    @field:NotNull
    @field:Valid
    val totalPrice: FlightsV3Money
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var totalPrice: FlightsV3Money? = null
    ) {
        fun totalPrice(totalPrice: FlightsV3Money) = apply { this.totalPrice = totalPrice }

        fun build(): FareCalendarResponseOffersInnerOfferPrice {
            val instance =
                FareCalendarResponseOffersInnerOfferPrice(
                    totalPrice = totalPrice!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FareCalendarResponseOffersInnerOfferPrice) {
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
            totalPrice = totalPrice!!
        )
}
