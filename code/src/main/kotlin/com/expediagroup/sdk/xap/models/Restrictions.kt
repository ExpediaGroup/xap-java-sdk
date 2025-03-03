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
 * Container of the Restrictions associated to this ticket.
 * @param type Type of the Restriction.
 * @param max Maximum value allowed for the restriction type.
 * @param min Minimum value allowed for the restriction type.
 * @param description The text to describe the restriction.
 */
data class Restrictions(
    // Type of the Restriction.
    @JsonProperty("Type")
    @field:NotNull
    @field:Valid
    val type: kotlin.String,
    // Maximum value allowed for the restriction type.
    @JsonProperty("Max")
    @field:NotNull
    @field:Valid
    val max: kotlin.String,
    // Minimum value allowed for the restriction type.
    @JsonProperty("Min")
    @field:NotNull
    @field:Valid
    val min: kotlin.String,
    // The text to describe the restriction.
    @JsonProperty("Description")
    @field:Valid
    val description: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: kotlin.String? = null,
        private var max: kotlin.String? = null,
        private var min: kotlin.String? = null,
        private var description: kotlin.String? = null
    ) {
        fun type(type: kotlin.String) = apply { this.type = type }

        fun max(max: kotlin.String) = apply { this.max = max }

        fun min(min: kotlin.String) = apply { this.min = min }

        fun description(description: kotlin.String?) = apply { this.description = description }

        fun build(): Restrictions {
            val instance =
                Restrictions(
                    type = type!!,
                    max = max!!,
                    min = min!!,
                    description = description
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Restrictions) {
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
            type = type!!,
            max = max!!,
            min = min!!,
            description = description
        )
}
