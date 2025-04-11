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
import com.expediagroup.sdk.xap.models.FlightFareRuleItem
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 *
 * @param flightFareRule Container for segment and corresponding fare rules.
 * @param transactionId Unique identifier for the transaction.
 */
data class FlightFareRulesResponse(
    // Container for segment and corresponding fare rules.
    @JsonProperty("FlightFareRule")
    @field:NotNull
    @field:Valid
    val flightFareRule: kotlin.collections
        .List<
            FlightFareRuleItem
        >,
    // Unique identifier for the transaction.
    @JsonProperty("TransactionId")
    @field:NotNull
    @field:Valid
    val transactionId: kotlin.String
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var flightFareRule: kotlin.collections.List<FlightFareRuleItem>? = null,
        private var transactionId: kotlin.String? = null
    ) {
        fun flightFareRule(flightFareRule: kotlin.collections.List<FlightFareRuleItem>) = apply { this.flightFareRule = flightFareRule }

        fun transactionId(transactionId: kotlin.String) = apply { this.transactionId = transactionId }

        fun build(): FlightFareRulesResponse {
            val instance =
                FlightFareRulesResponse(
                    flightFareRule = flightFareRule!!,
                    transactionId = transactionId!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightFareRulesResponse) {
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
            flightFareRule = flightFareRule!!,
            transactionId = transactionId!!
        )
}
