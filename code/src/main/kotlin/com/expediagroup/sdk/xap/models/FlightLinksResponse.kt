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
import com.expediagroup.sdk.xap.models.FlightLinksResponseLinksInner
import com.expediagroup.sdk.xap.models.FlightLinksResponseWarningsInner
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 *
 * @param transactionId
 * @param warnings
 * @param links
 */
data class FlightLinksResponse(
    @JsonProperty("TransactionId")
    @field:NotNull
    @field:Valid
    val transactionId: kotlin.String,
    @JsonProperty("Warnings")
    @field:Valid
    val warnings: kotlin.collections.List<FlightLinksResponseWarningsInner>? = null,
    @JsonProperty("Links")
    @field:Valid
    val links: kotlin.collections.List<FlightLinksResponseLinksInner>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var transactionId: kotlin.String? = null,
        private var warnings: kotlin.collections.List<FlightLinksResponseWarningsInner>? = null,
        private var links: kotlin.collections.List<FlightLinksResponseLinksInner>? = null
    ) {
        fun transactionId(transactionId: kotlin.String) = apply { this.transactionId = transactionId }

        fun warnings(warnings: kotlin.collections.List<FlightLinksResponseWarningsInner>?) = apply { this.warnings = warnings }

        fun links(links: kotlin.collections.List<FlightLinksResponseLinksInner>?) = apply { this.links = links }

        fun build(): FlightLinksResponse {
            val instance =
                FlightLinksResponse(
                    transactionId = transactionId!!,
                    warnings = warnings,
                    links = links
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightLinksResponse) {
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
            transactionId = transactionId!!,
            warnings = warnings,
            links = links
        )
}
