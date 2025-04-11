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
 * Car fuel type and whether Air Conditioning is included. Please find list of Car Fuel AC Codes in https://expediaintegration.zendesk.com/hc/en-us/articles/115005378328
 * @param code Car FuelAC code.
 * @param `value` Car FuelAC value.
 */
data class FuelAC(
    // Car FuelAC code.
    @JsonProperty("Code")
    @field:NotNull
    @field:Valid
    val code: kotlin.String,
    // Car FuelAC value.
    @JsonProperty("Value")
    @field:NotNull
    @field:Valid
    val `value`: kotlin.String
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: kotlin.String? = null,
        private var `value`: kotlin.String? = null
    ) {
        fun code(code: kotlin.String) = apply { this.code = code }

        fun `value`(`value`: kotlin.String) = apply { this.`value` = `value` }

        fun build(): FuelAC {
            val instance =
                FuelAC(
                    code = code!!,
                    `value` = `value`!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FuelAC) {
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
            code = code!!,
            `value` = `value`!!
        )
}
