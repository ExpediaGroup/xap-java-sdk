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
 * The phone of the company providing the activity.
 * @param countryCode Country code of traveler's phone number; only digits allowed.
 * @param number Traveler's phone number; only digits allowed.
 * @param areaCode Area code of traveler's phone number; only digits allowed.
 * @param extensionNumber
 */
data class ActivitiesPhone(
    // Country code of traveler's phone number; only digits allowed.
    @JsonProperty("CountryCode")
    val countryCode: kotlin.String,
    // Traveler's phone number; only digits allowed.
    @JsonProperty("Number")
    val number: kotlin.String,
    // Area code of traveler's phone number; only digits allowed.
    @JsonProperty("AreaCode")
    val areaCode: kotlin.String? = null,
    @JsonProperty("ExtensionNumber")
    val extensionNumber: kotlin.String? = null,
) {
    init {
        require(countryCode != null) { "countryCode must not be null" }

        require(number != null) { "number must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var countryCode: kotlin.String? = null,
        private var number: kotlin.String? = null,
        private var areaCode: kotlin.String? = null,
        private var extensionNumber: kotlin.String? = null,
    ) {
        fun countryCode(countryCode: kotlin.String) = apply { this.countryCode = countryCode }

        fun number(number: kotlin.String) = apply { this.number = number }

        fun areaCode(areaCode: kotlin.String?) = apply { this.areaCode = areaCode }

        fun extensionNumber(extensionNumber: kotlin.String?) = apply { this.extensionNumber = extensionNumber }

        fun build(): ActivitiesPhone {
            val instance =
                ActivitiesPhone(
                    countryCode = countryCode!!,
                    number = number!!,
                    areaCode = areaCode,
                    extensionNumber = extensionNumber,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            countryCode = countryCode!!,
            number = number!!,
            areaCode = areaCode,
            extensionNumber = extensionNumber,
        )
}
