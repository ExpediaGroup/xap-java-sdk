/**
 * Copyright (C) 2025 Expedia, Inc.
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

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * List of all the forms of payment that will be accepted for the booking of this rental transaction.
 * @param paymentMethod Method of payment
 * @param paymentSubMethod Sub method of payment
 * @param brandName The brand name of the payment sub-method to be displayed to the customer. In many cases it will be the same as the payment sub-method, but \"Visa/Carte Bleue\" and \"Visa/Delta are some of the exceptions.
 */
data class CarsValidFormsOfPayment(
    // Method of payment
    @JsonProperty("PaymentMethod")
    val paymentMethod: kotlin.String,
    // Sub method of payment
    @JsonProperty("PaymentSubMethod")
    val paymentSubMethod: kotlin.String,
    // The brand name of the payment sub-method to be displayed to the customer. In many cases it will be the same as the payment sub-method, but \"Visa/Carte Bleue\" and \"Visa/Delta are some of the exceptions.
    @JsonProperty("BrandName")
    val brandName: kotlin.String,
) {
    init {
        require(paymentMethod != null) { "paymentMethod must not be null" }

        require(paymentSubMethod != null) { "paymentSubMethod must not be null" }

        require(brandName != null) { "brandName must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var paymentMethod: kotlin.String? = null,
        private var paymentSubMethod: kotlin.String? = null,
        private var brandName: kotlin.String? = null,
    ) {
        fun paymentMethod(paymentMethod: kotlin.String) = apply { this.paymentMethod = paymentMethod }

        fun paymentSubMethod(paymentSubMethod: kotlin.String) = apply { this.paymentSubMethod = paymentSubMethod }

        fun brandName(brandName: kotlin.String) = apply { this.brandName = brandName }

        fun build(): CarsValidFormsOfPayment {
            val instance =
                CarsValidFormsOfPayment(
                    paymentMethod = paymentMethod!!,
                    paymentSubMethod = paymentSubMethod!!,
                    brandName = brandName!!,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            paymentMethod = paymentMethod!!,
            paymentSubMethod = paymentSubMethod!!,
            brandName = brandName!!,
        )
}
