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

/**
 *
 * @param stayDate A single night during the requested stay.
 * @param baseRate Container for nightly base rate.
 */
data class RatePlanPriceNightlyRatesInner(
    // A single night during the requested stay.
    @JsonProperty("StayDate")
    @field:Valid
    val stayDate: kotlin.Any? = null,
    // Container for nightly base rate.
    @JsonProperty("BaseRate")
    @field:Valid
    val baseRate: kotlin.Any? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var stayDate: kotlin.Any? = null,
        private var baseRate: kotlin.Any? = null
    ) {
        fun stayDate(stayDate: kotlin.Any?) = apply { this.stayDate = stayDate }

        fun baseRate(baseRate: kotlin.Any?) = apply { this.baseRate = baseRate }

        fun build(): RatePlanPriceNightlyRatesInner {
            val instance =
                RatePlanPriceNightlyRatesInner(
                    stayDate = stayDate,
                    baseRate = baseRate
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: RatePlanPriceNightlyRatesInner) {
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
            stayDate = stayDate,
            baseRate = baseRate
        )
}
