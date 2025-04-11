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
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container for fees that are charged for using certain payment methods. Since the method of payment is not known until time of booking, these fees are returned separately and not included in the total price
 * @param paymentMethod Method of payment
 * @param name Name of Payment Method.
 * @param fee The amount of the payment fee.
 * @param currency Currency of the fee in ISO 4217 format
 */
data class AirValidFormsOfPayment(
    // Method of payment
    @JsonProperty("PaymentMethod")
    @field:NotNull
    @field:Valid
    val paymentMethod: kotlin.String,
    // Name of Payment Method.
    @JsonProperty("Name")
    @field:NotNull
    @field:Valid
    val name: kotlin.String,
    // The amount of the payment fee.
    @JsonProperty("Fee")
    @field:NotNull
    @field:Valid
    val fee: kotlin.String,
    // Currency of the fee in ISO 4217 format
    @JsonProperty("Currency")
    @field:NotNull
    @field:Valid
    val currency: kotlin.String
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var paymentMethod: kotlin.String? = null,
        private var name: kotlin.String? = null,
        private var fee: kotlin.String? = null,
        private var currency: kotlin.String? = null
    ) {
        fun paymentMethod(paymentMethod: kotlin.String) = apply { this.paymentMethod = paymentMethod }

        fun name(name: kotlin.String) = apply { this.name = name }

        fun fee(fee: kotlin.String) = apply { this.fee = fee }

        fun currency(currency: kotlin.String) = apply { this.currency = currency }

        fun build(): AirValidFormsOfPayment {
            val instance =
                AirValidFormsOfPayment(
                    paymentMethod = paymentMethod!!,
                    name = name!!,
                    fee = fee!!,
                    currency = currency!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: AirValidFormsOfPayment) {
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
            paymentMethod = paymentMethod!!,
            name = name!!,
            fee = fee!!,
            currency = currency!!
        )
}
