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
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
* List of all the forms of payment that will be accepted for the booking of this rental transaction.
 * @param paymentMethod Method of payment
 * @param paymentSubMethod Sub method of payment
 * @param brandName The brand name of the payment sub-method to be displayed to the customer. In many cases it will be the same as the payment sub-method, but \"Visa/Carte Bleue\" and \"Visa/Delta are some of the exceptions.
*/
data class CarsValidFormsOfPayment(
    // Method of payment
    @JsonProperty("PaymentMethod")
    @field:NotNull
    @field:Valid
    val paymentMethod: kotlin.String,
    // Sub method of payment
    @JsonProperty("PaymentSubMethod")
    @field:NotNull
    @field:Valid
    val paymentSubMethod: kotlin.String,
    // The brand name of the payment sub-method to be displayed to the customer. In many cases it will be the same as the payment sub-method, but \"Visa/Carte Bleue\" and \"Visa/Delta are some of the exceptions.
    @JsonProperty("BrandName")
    @field:NotNull
    @field:Valid
    val brandName: kotlin.String
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var paymentMethod: kotlin.String? = null,
        private var paymentSubMethod: kotlin.String? = null,
        private var brandName: kotlin.String? = null
    ) {
        fun paymentMethod(paymentMethod: kotlin.String) = apply { this.paymentMethod = paymentMethod }

        fun paymentSubMethod(paymentSubMethod: kotlin.String) = apply { this.paymentSubMethod = paymentSubMethod }

        fun brandName(brandName: kotlin.String) = apply { this.brandName = brandName }

        fun build(): CarsValidFormsOfPayment {
            val instance =
                CarsValidFormsOfPayment(
                    paymentMethod = paymentMethod!!,
                    paymentSubMethod = paymentSubMethod!!,
                    brandName = brandName!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: CarsValidFormsOfPayment) {
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
            paymentSubMethod = paymentSubMethod!!,
            brandName = brandName!!
        )
}
