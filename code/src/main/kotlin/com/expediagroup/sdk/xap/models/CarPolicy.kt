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
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * A list of policies that apply to this car rental.
 * @param categoryCode The category that this policy applies to (e.g. cancellation, drivers license requirements, driver age requirements)
 * @param policyText The raw text of the policy.This is generally localized into the requested language, but may be English if no other translations are available.
 */
data class CarPolicy(
    // The category that this policy applies to (e.g. cancellation, drivers license requirements, driver age requirements)
    @JsonProperty("CategoryCode")
    @field:NotNull
    @field:Valid
    val categoryCode: kotlin.String,
    // The raw text of the policy.This is generally localized into the requested language, but may be English if no other translations are available.
    @JsonProperty("PolicyText")
    @field:Valid
    val policyText: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var categoryCode: kotlin.String? = null,
        private var policyText: kotlin.String? = null
    ) {
        fun categoryCode(categoryCode: kotlin.String) = apply { this.categoryCode = categoryCode }

        fun policyText(policyText: kotlin.String?) = apply { this.policyText = policyText }

        fun build(): CarPolicy {
            val instance =
                CarPolicy(
                    categoryCode = categoryCode!!,
                    policyText = policyText
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: CarPolicy) {
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
            categoryCode = categoryCode!!,
            policyText = policyText
        )
}
