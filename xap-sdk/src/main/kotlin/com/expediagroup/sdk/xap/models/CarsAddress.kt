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

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Address information
 * @param address1 Street Number, Street Name, or PO Box
 * @param city The city
 * @param country 3-letter code for the country
 * @param address2 Apartment, Floor, Suite, Bldg # or more specific information about Address1.
 * @param suite Suite/apartment number
 * @param province The state or province
 * @param postalCode Zip/postal code
 */
data class CarsAddress(
    // Street Number, Street Name, or PO Box
    @JsonProperty("Address1")
    val address1: kotlin.String,
    // The city
    @JsonProperty("City")
    val city: kotlin.String,
    // 3-letter code for the country
    @JsonProperty("Country")
    val country: kotlin.String,
    // Apartment, Floor, Suite, Bldg # or more specific information about Address1.
    @JsonProperty("Address2")
    val address2: kotlin.String? = null,
    // Suite/apartment number
    @JsonProperty("Suite")
    val suite: kotlin.String? = null,
    // The state or province
    @JsonProperty("Province")
    val province: kotlin.String? = null,
    // Zip/postal code
    @JsonProperty("PostalCode")
    val postalCode: kotlin.String? = null,
) {
    init {
        require(address1 != null) { "address1 must not be null" }

        require(city != null) { "city must not be null" }

        require(country != null) { "country must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var address1: kotlin.String? = null,
        private var city: kotlin.String? = null,
        private var country: kotlin.String? = null,
        private var address2: kotlin.String? = null,
        private var suite: kotlin.String? = null,
        private var province: kotlin.String? = null,
        private var postalCode: kotlin.String? = null,
    ) {
        fun address1(address1: kotlin.String) = apply { this.address1 = address1 }

        fun city(city: kotlin.String) = apply { this.city = city }

        fun country(country: kotlin.String) = apply { this.country = country }

        fun address2(address2: kotlin.String?) = apply { this.address2 = address2 }

        fun suite(suite: kotlin.String?) = apply { this.suite = suite }

        fun province(province: kotlin.String?) = apply { this.province = province }

        fun postalCode(postalCode: kotlin.String?) = apply { this.postalCode = postalCode }

        fun build(): CarsAddress {
            val instance =
                CarsAddress(
                    address1 = address1!!,
                    city = city!!,
                    country = country!!,
                    address2 = address2,
                    suite = suite,
                    province = province,
                    postalCode = postalCode,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            address1 = address1!!,
            city = city!!,
            country = country!!,
            address2 = address2,
            suite = suite,
            province = province,
            postalCode = postalCode,
        )
}
