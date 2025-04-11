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
import com.expediagroup.sdk.xap.models.GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInner
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container for error list.
 * @param code Error code describing the issue
 * @param locationOptions Container for list of matching locations corresponding to the value entered in the location keyword search.
 */
data class GetFlightFlexsearch400ResponseErrorsInnerError(
    // Error code describing the issue
    @JsonProperty("Code")
    @field:NotNull
    @field:Valid
    val code: kotlin.String,
    // Container for list of matching locations corresponding to the value entered in the location keyword search.
    @JsonProperty("LocationOptions")
    @field:Valid
    val locationOptions: kotlin.collections.List<GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInner>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: kotlin.String? = null,
        private var locationOptions: kotlin.collections.List<GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInner>? = null
    ) {
        fun code(code: kotlin.String) = apply { this.code = code }

        fun locationOptions(locationOptions: kotlin.collections.List<GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInner>?) = apply { this.locationOptions = locationOptions }

        fun build(): GetFlightFlexsearch400ResponseErrorsInnerError {
            val instance =
                GetFlightFlexsearch400ResponseErrorsInnerError(
                    code = code!!,
                    locationOptions = locationOptions
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: GetFlightFlexsearch400ResponseErrorsInnerError) {
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
            locationOptions = locationOptions
        )
}
