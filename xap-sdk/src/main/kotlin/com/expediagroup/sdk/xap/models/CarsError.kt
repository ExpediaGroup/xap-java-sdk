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

import com.expediagroup.sdk.rest.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.CarsLocationOption
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
* Container for error list.
 * @param code Error code describing the issue
 * @param description A simple description of what the error is.
 * @param detailCode Detailed error code describing the issue.
 * @param locationKeyword The requested location that caused the error.
 * @param locationOptions List for possible locations from which the customer must choose the best one to be re-submitted in the request.
*/
data class CarsError(
    // Error code describing the issue
    @JsonProperty("Code")
    @field:NotNull
    @field:Valid
    val code: kotlin.String,
    // A simple description of what the error is.
    @JsonProperty("Description")
    @field:NotNull
    @field:Valid
    val description: kotlin.String,
    // Detailed error code describing the issue.
    @JsonProperty("DetailCode")
    @field:Valid
    val detailCode: kotlin.String? = null,
    // The requested location that caused the error.
    @JsonProperty("LocationKeyword")
    @field:Valid
    val locationKeyword: kotlin.String? = null,
    // List for possible locations from which the customer must choose the best one to be re-submitted in the request.
    @JsonProperty("LocationOptions")
    @field:Valid
    val locationOptions: kotlin.collections.List<CarsLocationOption>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: kotlin.String? = null,
        private var description: kotlin.String? = null,
        private var detailCode: kotlin.String? = null,
        private var locationKeyword: kotlin.String? = null,
        private var locationOptions: kotlin.collections.List<CarsLocationOption>? = null
    ) {
        fun code(code: kotlin.String) = apply { this.code = code }

        fun description(description: kotlin.String) = apply { this.description = description }

        fun detailCode(detailCode: kotlin.String?) = apply { this.detailCode = detailCode }

        fun locationKeyword(locationKeyword: kotlin.String?) = apply { this.locationKeyword = locationKeyword }

        fun locationOptions(locationOptions: kotlin.collections.List<CarsLocationOption>?) = apply { this.locationOptions = locationOptions }

        fun build(): CarsError {
            val instance =
                CarsError(
                    code = code!!,
                    description = description!!,
                    detailCode = detailCode,
                    locationKeyword = locationKeyword,
                    locationOptions = locationOptions
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: CarsError) {
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
            description = description!!,
            detailCode = detailCode,
            locationKeyword = locationKeyword,
            locationOptions = locationOptions
        )
}
