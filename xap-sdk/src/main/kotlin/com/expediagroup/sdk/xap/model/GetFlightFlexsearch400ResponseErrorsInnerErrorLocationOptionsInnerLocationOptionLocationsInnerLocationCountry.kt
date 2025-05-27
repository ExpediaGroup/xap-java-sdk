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
package com.expediagroup.sdk.xap.model

import com.expediagroup.sdk.xap.model.GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInnerLocationCountryCountry
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param country
 */
@ConsistentCopyVisibility data class GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInnerLocationCountry private constructor(
    @JsonProperty("Country")
    val country: GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInnerLocationCountryCountry? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var country: GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInnerLocationCountryCountry? = null,
    ) {
        fun country(country: GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInnerLocationCountryCountry?) = apply { this.country = country }

        fun build(): GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInnerLocationCountry {
            val instance = GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInnerLocationCountry(
                country = country,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        country = country,
    )
}
