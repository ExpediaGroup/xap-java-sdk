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
import com.expediagroup.sdk.xap.models.LodgingCancellationPenaltyRule
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Container for Cancellation Policy information.
 * @param refundable Indicate whether the rate is refundable or not.
 * @param freeCancellation Indicate whether the room can be cancelled free of charge.
 * @param freeCancellationEndDateTime The date and time until which the room can be cancelled free of charge.
 * @param cancellationPenaltyRules Container for Cancellation Penalty Rules information.
 */
data class LodgingCancellationPolicy(
    // Indicate whether the rate is refundable or not.
    @JsonProperty("Refundable")
    @field:Valid
    val refundable: kotlin.Boolean? = null,
    // Indicate whether the room can be cancelled free of charge.
    @JsonProperty("FreeCancellation")
    @field:Valid
    val freeCancellation: kotlin.Boolean? = null,
    // The date and time until which the room can be cancelled free of charge.
    @JsonProperty("FreeCancellationEndDateTime")
    val freeCancellationEndDateTime: java.time.OffsetDateTime? = null,
    // Container for Cancellation Penalty Rules information.
    @JsonProperty("CancellationPenaltyRules")
    @field:Valid
    val cancellationPenaltyRules: kotlin.collections.List<LodgingCancellationPenaltyRule>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var refundable: kotlin.Boolean? = null,
        private var freeCancellation: kotlin.Boolean? = null,
        private var freeCancellationEndDateTime: java.time.OffsetDateTime? = null,
        private var cancellationPenaltyRules: kotlin.collections.List<LodgingCancellationPenaltyRule>? = null
    ) {
        fun refundable(refundable: kotlin.Boolean?) = apply { this.refundable = refundable }

        fun freeCancellation(freeCancellation: kotlin.Boolean?) = apply { this.freeCancellation = freeCancellation }

        fun freeCancellationEndDateTime(freeCancellationEndDateTime: java.time.OffsetDateTime?) = apply { this.freeCancellationEndDateTime = freeCancellationEndDateTime }

        fun cancellationPenaltyRules(cancellationPenaltyRules: kotlin.collections.List<LodgingCancellationPenaltyRule>?) = apply { this.cancellationPenaltyRules = cancellationPenaltyRules }

        fun build(): LodgingCancellationPolicy {
            val instance =
                LodgingCancellationPolicy(
                    refundable = refundable,
                    freeCancellation = freeCancellation,
                    freeCancellationEndDateTime = freeCancellationEndDateTime,
                    cancellationPenaltyRules = cancellationPenaltyRules
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: LodgingCancellationPolicy) {
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
            refundable = refundable,
            freeCancellation = freeCancellation,
            freeCancellationEndDateTime = freeCancellationEndDateTime,
            cancellationPenaltyRules = cancellationPenaltyRules
        )
}
