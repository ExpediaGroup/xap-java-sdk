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
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 *
 * @param accept
 * @param method
 * @param href
 */
data class FlightLinksResponseLinksInnerApiDetails(
    @JsonProperty("Accept")
    @field:NotNull
    @field:Valid
    val accept: kotlin.String,
    @JsonProperty("Method")
    @field:NotNull
    @field:Valid
    val method: kotlin.String,
    @JsonProperty("Href")
    @field:NotNull
    @field:Valid
    val href: kotlin.String
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var accept: kotlin.String? = null,
        private var method: kotlin.String? = null,
        private var href: kotlin.String? = null
    ) {
        fun accept(accept: kotlin.String) = apply { this.accept = accept }

        fun method(method: kotlin.String) = apply { this.method = method }

        fun href(href: kotlin.String) = apply { this.href = href }

        fun build(): FlightLinksResponseLinksInnerApiDetails {
            val instance =
                FlightLinksResponseLinksInnerApiDetails(
                    accept = accept!!,
                    method = method!!,
                    href = href!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightLinksResponseLinksInnerApiDetails) {
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
            accept = accept!!,
            method = method!!,
            href = href!!
        )
}
