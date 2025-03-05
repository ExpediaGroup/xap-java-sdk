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
/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.expediagroup.sdk.xap.models

import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.Entertainment
import com.expediagroup.sdk.xap.models.Power
import com.expediagroup.sdk.xap.models.Wifi
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * List of specific amenities available for each flight leg.
 * @param entertainment
 * @param wifi
 * @param power
 */
data class Amenities(
    @JsonProperty("Entertainment")
    @field:Valid
    val entertainment: Entertainment? = null,
    @JsonProperty("Wifi")
    @field:Valid
    val wifi: Wifi? = null,
    @JsonProperty("Power")
    @field:Valid
    val power: Power? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var entertainment: Entertainment? = null,
        private var wifi: Wifi? = null,
        private var power: Power? = null
    ) {
        fun entertainment(entertainment: Entertainment?) = apply { this.entertainment = entertainment }

        fun wifi(wifi: Wifi?) = apply { this.wifi = wifi }

        fun power(power: Power?) = apply { this.power = power }

        fun build(): Amenities {
            val instance =
                Amenities(
                    entertainment = entertainment,
                    wifi = wifi,
                    power = power
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Amenities) {
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
            entertainment = entertainment,
            wifi = wifi,
            power = power
        )
}
