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
 * A list of time range to indicate the operation hours of the date range.
 * @param startTime Start time at pickup location of the date range.
 * @param endTime End time at pickup location of the date range.
 */
data class TimeRange(
    // Start time at pickup location of the date range.
    @JsonProperty("StartTime")
    @field:NotNull
    @field:Valid
    val startTime: kotlin.String,
    // End time at pickup location of the date range.
    @JsonProperty("EndTime")
    @field:NotNull
    @field:Valid
    val endTime: kotlin.String
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var startTime: kotlin.String? = null,
        private var endTime: kotlin.String? = null
    ) {
        fun startTime(startTime: kotlin.String) = apply { this.startTime = startTime }

        fun endTime(endTime: kotlin.String) = apply { this.endTime = endTime }

        fun build(): TimeRange {
            val instance =
                TimeRange(
                    startTime = startTime!!,
                    endTime = endTime!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: TimeRange) {
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
            startTime = startTime!!,
            endTime = endTime!!
        )
}
