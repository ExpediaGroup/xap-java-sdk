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
import com.expediagroup.sdk.xap.models.Rule
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Contains the list of fare rule details
 * @param fareBasisCode Fare Basis Code to which the rules are applied.
 * @param rules Container for fare rules.
 */
data class FareRule(
    // Fare Basis Code to which the rules are applied.
    @JsonProperty("FareBasisCode")
    @field:NotNull
    @field:Valid
    val fareBasisCode: kotlin.String,
    // Container for fare rules.
    @JsonProperty("Rules")
    @field:NotNull
    @field:Valid
    val rules: kotlin.collections
        .List<
            Rule
        >
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var fareBasisCode: kotlin.String? = null,
        private var rules: kotlin.collections.List<Rule>? = null
    ) {
        fun fareBasisCode(fareBasisCode: kotlin.String) = apply { this.fareBasisCode = fareBasisCode }

        fun rules(rules: kotlin.collections.List<Rule>) = apply { this.rules = rules }

        fun build(): FareRule {
            val instance =
                FareRule(
                    fareBasisCode = fareBasisCode!!,
                    rules = rules!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FareRule) {
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
            fareBasisCode = fareBasisCode!!,
            rules = rules!!
        )
}
