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
import com.expediagroup.sdk.xap.models.Error
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 *
 * @param errors Container for error list.
 * @param transactionId A unique identifier for the transaction.
 */
data class Errors(
    // Container for error list.
    @JsonProperty("Errors")
    @field:Valid
    val errors: kotlin.collections.List<Error>? = null,
    // A unique identifier for the transaction.
    @JsonProperty("TransactionId")
    @field:Valid
    val transactionId: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var errors: kotlin.collections.List<Error>? = null,
        private var transactionId: kotlin.String? = null
    ) {
        fun errors(errors: kotlin.collections.List<Error>?) = apply { this.errors = errors }

        fun transactionId(transactionId: kotlin.String?) = apply { this.transactionId = transactionId }

        fun build(): Errors {
            val instance =
                Errors(
                    errors = errors,
                    transactionId = transactionId
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Errors) {
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
            errors = errors,
            transactionId = transactionId
        )
}
