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
 *
 * @param countryCode The designated country calling code.
 * @param areaCode The phone's area code.
 * @param number The phone's local number.
 * @param extensionNumber Optional extension number, if required to reach the hotel.
 */
data class Phone(
    // The designated country calling code.
    @JsonProperty("CountryCode")
    @field:Valid
    val countryCode: kotlin.String? = null,
    // The phone's area code.
    @JsonProperty("AreaCode")
    @field:Valid
    val areaCode: kotlin.String? = null,
    // The phone's local number.
    @JsonProperty("Number")
    @field:Valid
    val number: kotlin.String? = null,
    // Optional extension number, if required to reach the hotel.
    @JsonProperty("ExtensionNumber")
    @field:Valid
    val extensionNumber: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var countryCode: kotlin.String? = null,
        private var areaCode: kotlin.String? = null,
        private var number: kotlin.String? = null,
        private var extensionNumber: kotlin.String? = null
    ) {
        fun countryCode(countryCode: kotlin.String?) = apply { this.countryCode = countryCode }

        fun areaCode(areaCode: kotlin.String?) = apply { this.areaCode = areaCode }

        fun number(number: kotlin.String?) = apply { this.number = number }

        fun extensionNumber(extensionNumber: kotlin.String?) = apply { this.extensionNumber = extensionNumber }

        fun build(): Phone {
            val instance =
                Phone(
                    countryCode = countryCode,
                    areaCode = areaCode,
                    number = number,
                    extensionNumber = extensionNumber
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Phone) {
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
            countryCode = countryCode,
            areaCode = areaCode,
            number = number,
            extensionNumber = extensionNumber
        )
}
