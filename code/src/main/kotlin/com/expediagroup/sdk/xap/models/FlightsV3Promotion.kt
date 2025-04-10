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
 * @param type Promotion type, possible values: PO | FN
 * @param description The description of the promotion.
 * @param `value`
 */
data class FlightsV3Promotion(
    // Promotion type, possible values: PO | FN
    @JsonProperty("Type")
    @field:Valid
    val type: kotlin.String? = null,
    // The description of the promotion.
    @JsonProperty("Description")
    @field:Valid
    val description: kotlin.String? = null,
    @JsonProperty("Value")
    val `value`: kotlin.Double? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: kotlin.String? = null,
        private var description: kotlin.String? = null,
        private var `value`: kotlin.Double? = null
    ) {
        fun type(type: kotlin.String?) = apply { this.type = type }

        fun description(description: kotlin.String?) = apply { this.description = description }

        fun `value`(`value`: kotlin.Double?) = apply { this.`value` = `value` }

        fun build(): FlightsV3Promotion {
            val instance =
                FlightsV3Promotion(
                    type = type,
                    description = description,
                    `value` = `value`
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightsV3Promotion) {
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
            type = type,
            description = description,
            `value` = `value`
        )
}
