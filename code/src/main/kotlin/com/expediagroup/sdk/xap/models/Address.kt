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

/**
 * The address information of the location.
 * @param address1 Street Number, Street Name, or PO Box
 * @param address2 Apartment, Floor, Suite, Bldg or more specific information about Address1.
 * @param city The city
 * @param province The state or province
 * @param country 3-letter code for the country
 * @param postalCode Zip/postal code
 */
data class Address(
    // Street Number, Street Name, or PO Box
    @JsonProperty("Address1")
    @field:Valid
    val address1: kotlin.String? = null,
    // Apartment, Floor, Suite, Bldg or more specific information about Address1.
    @JsonProperty("Address2")
    @field:Valid
    val address2: kotlin.String? = null,
    // The city
    @JsonProperty("City")
    @field:Valid
    val city: kotlin.String? = null,
    // The state or province
    @JsonProperty("Province")
    @field:Valid
    val province: kotlin.String? = null,
    // 3-letter code for the country
    @JsonProperty("Country")
    @field:Valid
    val country: kotlin.String? = null,
    // Zip/postal code
    @JsonProperty("PostalCode")
    @field:Valid
    val postalCode: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var address1: kotlin.String? = null,
        private var address2: kotlin.String? = null,
        private var city: kotlin.String? = null,
        private var province: kotlin.String? = null,
        private var country: kotlin.String? = null,
        private var postalCode: kotlin.String? = null
    ) {
        fun address1(address1: kotlin.String?) = apply { this.address1 = address1 }

        fun address2(address2: kotlin.String?) = apply { this.address2 = address2 }

        fun city(city: kotlin.String?) = apply { this.city = city }

        fun province(province: kotlin.String?) = apply { this.province = province }

        fun country(country: kotlin.String?) = apply { this.country = country }

        fun postalCode(postalCode: kotlin.String?) = apply { this.postalCode = postalCode }

        fun build(): Address {
            val instance =
                Address(
                    address1 = address1,
                    address2 = address2,
                    city = city,
                    province = province,
                    country = country,
                    postalCode = postalCode
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Address) {
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
            address1 = address1,
            address2 = address2,
            city = city,
            province = province,
            country = country,
            postalCode = postalCode
        )
}
