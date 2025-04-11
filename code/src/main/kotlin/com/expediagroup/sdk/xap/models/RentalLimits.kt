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
import com.expediagroup.sdk.xap.models.Duration
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Limitations that are part of this rental agreement.
 * @param minDuration
 * @param maxDuration
 */
data class RentalLimits(
    @JsonProperty("MinDuration")
    @field:NotNull
    @field:Valid
    val minDuration: Duration,
    @JsonProperty("MaxDuration")
    @field:NotNull
    @field:Valid
    val maxDuration: Duration
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var minDuration: Duration? = null,
        private var maxDuration: Duration? = null
    ) {
        fun minDuration(minDuration: Duration) = apply { this.minDuration = minDuration }

        fun maxDuration(maxDuration: Duration) = apply { this.maxDuration = maxDuration }

        fun build(): RentalLimits {
            val instance =
                RentalLimits(
                    minDuration = minDuration!!,
                    maxDuration = maxDuration!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: RentalLimits) {
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
            minDuration = minDuration!!,
            maxDuration = maxDuration!!
        )
}
