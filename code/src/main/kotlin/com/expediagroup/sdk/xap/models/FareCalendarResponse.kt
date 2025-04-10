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
import com.expediagroup.sdk.xap.models.FareCalendarResponseOffersInner
import com.expediagroup.sdk.xap.models.FlightsV3Warning
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 *
 * @param offers Container for list of air offers. An offer gives total trip details including flight and pricing information.
 * @param transactionId Unique identifier for the transaction.
 * @param warnings Container for Warning Codes.
 */
data class FareCalendarResponse(
    // Container for list of air offers. An offer gives total trip details including flight and pricing information.
    @JsonProperty("Offers")
    @field:NotNull
    @field:Valid
    val offers: kotlin.collections
        .List<
            FareCalendarResponseOffersInner
        >,
    // Unique identifier for the transaction.
    @JsonProperty("TransactionId")
    @field:NotNull
    @field:Valid
    val transactionId: kotlin.String,
    // Container for Warning Codes.
    @JsonProperty("Warnings")
    @field:Valid
    val warnings: kotlin.collections.List<FlightsV3Warning>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var offers: kotlin.collections.List<FareCalendarResponseOffersInner>? = null,
        private var transactionId: kotlin.String? = null,
        private var warnings: kotlin.collections.List<FlightsV3Warning>? = null
    ) {
        fun offers(offers: kotlin.collections.List<FareCalendarResponseOffersInner>) = apply { this.offers = offers }

        fun transactionId(transactionId: kotlin.String) = apply { this.transactionId = transactionId }

        fun warnings(warnings: kotlin.collections.List<FlightsV3Warning>?) = apply { this.warnings = warnings }

        fun build(): FareCalendarResponse {
            val instance =
                FareCalendarResponse(
                    offers = offers!!,
                    transactionId = transactionId!!,
                    warnings = warnings
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FareCalendarResponse) {
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
            offers = offers!!,
            transactionId = transactionId!!,
            warnings = warnings
        )
}
