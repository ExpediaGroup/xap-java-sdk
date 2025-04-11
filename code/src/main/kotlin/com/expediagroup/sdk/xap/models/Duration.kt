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
import javax.validation.constraints.NotNull

/**
 * The maximum amount of time for a rental that still qualifies for this rate.This may or may not be the same as the current rental duration.
 * @param unit The unit for minimum amount of time for a rental.
 * @param count The minimum number of units that qualify for minimum amount of time for a rental.
 */
data class Duration(
    // The unit for minimum amount of time for a rental.
    @JsonProperty("Unit")
    @field:NotNull
    @field:Valid
    val unit: kotlin.String,
    // The minimum number of units that qualify for minimum amount of time for a rental.
    @JsonProperty("Count")
    val count: kotlin.Long
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var unit: kotlin.String? = null,
        private var count: kotlin.Long? = null
    ) {
        fun unit(unit: kotlin.String) = apply { this.unit = unit }

        fun count(count: kotlin.Long) = apply { this.count = count }

        fun build(): Duration {
            val instance =
                Duration(
                    unit = unit!!,
                    count = count!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Duration) {
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
            unit = unit!!,
            count = count!!
        )
}
