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
import javax.validation.Validation

/**
 * Container for power amenities available in this flight leg
 * @param available Indication of the availability of Power amenity.
 */
data class Power(
    // Indication of the availability of Power amenity.
    @JsonProperty("Available")
    val available: Power.Available? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var available: Power.Available? = null
    ) {
        fun available(available: Power.Available?) = apply { this.available = available }

        fun build(): Power {
            val instance =
                Power(
                    available = available
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Power) {
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
            available = available
        )

    /**
     * Indication of the availability of Power amenity.
     * Values: YES,NO,UNKNOWN
     */
    enum class Available(val value: kotlin.String) {
        @JsonProperty("YES")
        YES("YES"),

        @JsonProperty("NO")
        NO("NO"),

        @JsonProperty("UNKNOWN")
        UNKNOWN("UNKNOWN")
    }
}
