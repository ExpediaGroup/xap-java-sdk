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

/**
 * Container for cancellation penalty details.
 * @param penaltyPercentOfStay Specifies the per-stay cancellation fee charged as a percentage of the total rate, in addition to any other penalties listed.
 * @param penaltyStartDateTime The beginning of the window of time when the `CancellationPenaltyRule` is in effect.  The date and time are expressed in ISO 8601 International Date format, and local to the property.
 * @param penaltyEndDateTime The end of the window of time when the `CancellationPenaltyRule` is in effect.  The date and time are expressed in ISO 8601 International Date format, and local to the property.
 */
data class LodgingCancellationPenaltyRule(
    // Specifies the per-stay cancellation fee charged as a percentage of the total rate, in addition to any other penalties listed.
    @JsonProperty("PenaltyPercentOfStay")
    @field:Valid
    val penaltyPercentOfStay: kotlin.String? = null,
    // The beginning of the window of time when the `CancellationPenaltyRule` is in effect.  The date and time are expressed in ISO 8601 International Date format, and local to the property.
    @JsonProperty("PenaltyStartDateTime")
    val penaltyStartDateTime: java.time.OffsetDateTime? = null,
    // The end of the window of time when the `CancellationPenaltyRule` is in effect.  The date and time are expressed in ISO 8601 International Date format, and local to the property.
    @JsonProperty("PenaltyEndDateTime")
    val penaltyEndDateTime: java.time.OffsetDateTime? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var penaltyPercentOfStay: kotlin.String? = null,
        private var penaltyStartDateTime: java.time.OffsetDateTime? = null,
        private var penaltyEndDateTime: java.time.OffsetDateTime? = null
    ) {
        fun penaltyPercentOfStay(penaltyPercentOfStay: kotlin.String?) = apply { this.penaltyPercentOfStay = penaltyPercentOfStay }

        fun penaltyStartDateTime(penaltyStartDateTime: java.time.OffsetDateTime?) = apply { this.penaltyStartDateTime = penaltyStartDateTime }

        fun penaltyEndDateTime(penaltyEndDateTime: java.time.OffsetDateTime?) = apply { this.penaltyEndDateTime = penaltyEndDateTime }

        fun build(): LodgingCancellationPenaltyRule {
            val instance =
                LodgingCancellationPenaltyRule(
                    penaltyPercentOfStay = penaltyPercentOfStay,
                    penaltyStartDateTime = penaltyStartDateTime,
                    penaltyEndDateTime = penaltyEndDateTime
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: LodgingCancellationPenaltyRule) {
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
            penaltyPercentOfStay = penaltyPercentOfStay,
            penaltyStartDateTime = penaltyStartDateTime,
            penaltyEndDateTime = penaltyEndDateTime
        )
}
