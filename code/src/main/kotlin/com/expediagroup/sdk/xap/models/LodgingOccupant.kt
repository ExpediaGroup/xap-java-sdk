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
 * @param adults The number of adults in a room.
 * @param childAges The ages of children in a room.
 */
data class LodgingOccupant(
    // The number of adults in a room.
    @JsonProperty("Adults")
    val adults: kotlin.Int? = null,
    // The ages of children in a room.
    @JsonProperty("ChildAges")
    @field:Valid
    val childAges: kotlin.collections.List<kotlin.Int>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var adults: kotlin.Int? = null,
        private var childAges: kotlin.collections.List<kotlin.Int>? = null
    ) {
        fun adults(adults: kotlin.Int?) = apply { this.adults = adults }

        fun childAges(childAges: kotlin.collections.List<kotlin.Int>?) = apply { this.childAges = childAges }

        fun build(): LodgingOccupant {
            val instance =
                LodgingOccupant(
                    adults = adults,
                    childAges = childAges
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: LodgingOccupant) {
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
            adults = adults,
            childAges = childAges
        )
}
