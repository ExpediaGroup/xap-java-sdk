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
import com.expediagroup.sdk.xap.models.DepositDetail
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Container for deposit policy details
 * @param description
 * @param details Container for deposit policy details
 */
data class DepositPolicy(
    @JsonProperty("Description")
    @field:Valid
    val description: kotlin.collections.List<kotlin.String>? = null,
    // Container for deposit policy details
    @JsonProperty("Details")
    @field:Valid
    val details: kotlin.collections.List<DepositDetail>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var description: kotlin.collections.List<kotlin.String>? = null,
        private var details: kotlin.collections.List<DepositDetail>? = null
    ) {
        fun description(description: kotlin.collections.List<kotlin.String>?) = apply { this.description = description }

        fun details(details: kotlin.collections.List<DepositDetail>?) = apply { this.details = details }

        fun build(): DepositPolicy {
            val instance =
                DepositPolicy(
                    description = description,
                    details = details
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: DepositPolicy) {
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
            description = description,
            details = details
        )
}
