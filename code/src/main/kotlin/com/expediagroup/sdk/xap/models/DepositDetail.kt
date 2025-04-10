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
import com.expediagroup.sdk.xap.models.When
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Container for deposit policy details
 * @param type Should be one of the following values: PERCENT The deposit amount is calculated as a percentage of the total booking cost. NIGHT The deposit amount is calculated in terms of nights plus tax. AMOUNT The deposit amount in USD. REMAINDER The deposit amount is equal to the booking cost minus any deposits that have been made before this point.
 * @param `value` Value to indicate how many/much of the type listed above is going to be charged as a deposit.
 * @param `when`
 */
data class DepositDetail(
    // Should be one of the following values: PERCENT The deposit amount is calculated as a percentage of the total booking cost. NIGHT The deposit amount is calculated in terms of nights plus tax. AMOUNT The deposit amount in USD. REMAINDER The deposit amount is equal to the booking cost minus any deposits that have been made before this point.
    @JsonProperty("Type")
    val type: DepositDetail.Type? = null,
    // Value to indicate how many/much of the type listed above is going to be charged as a deposit.
    @JsonProperty("Value")
    @field:Valid
    val `value`: kotlin.String? = null,
    @JsonProperty("When")
    @field:Valid
    val `when`: When? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: DepositDetail.Type? = null,
        private var `value`: kotlin.String? = null,
        private var `when`: When? = null
    ) {
        fun type(type: DepositDetail.Type?) = apply { this.type = type }

        fun `value`(`value`: kotlin.String?) = apply { this.`value` = `value` }

        fun `when`(`when`: When?) = apply { this.`when` = `when` }

        fun build(): DepositDetail {
            val instance =
                DepositDetail(
                    type = type,
                    `value` = `value`,
                    `when` = `when`
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: DepositDetail) {
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
            `value` = `value`,
            `when` = `when`
        )

    /**
     * Should be one of the following values: PERCENT The deposit amount is calculated as a percentage of the total booking cost. NIGHT The deposit amount is calculated in terms of nights plus tax. AMOUNT The deposit amount in USD. REMAINDER The deposit amount is equal to the booking cost minus any deposits that have been made before this point.
     * Values: PERCENT,NIGHT,AMOUNT,REMAINDER
     */
    enum class Type(val value: kotlin.String) {
        @JsonProperty("PERCENT")
        PERCENT("PERCENT"),

        @JsonProperty("NIGHT")
        NIGHT("NIGHT"),

        @JsonProperty("AMOUNT")
        AMOUNT("AMOUNT"),

        @JsonProperty("REMAINDER")
        REMAINDER("REMAINDER")
    }
}
