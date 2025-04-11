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
import com.expediagroup.sdk.xap.models.PenaltyType
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container for penalty rules
 * @param penalty
 * @param startDateTime The time when this penalty window starts
 * @param endDateTime The time when this penalty window ends
 */
data class PenaltyRule(
    @JsonProperty("Penalty")
    @field:NotNull
    @field:Valid
    val penalty: PenaltyType,
    // The time when this penalty window starts
    @JsonProperty("StartDateTime")
    val startDateTime: java.time.LocalDateTime,
    // The time when this penalty window ends
    @JsonProperty("EndDateTime")
    val endDateTime: java.time.LocalDateTime
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var penalty: PenaltyType? = null,
        private var startDateTime: java.time.LocalDateTime? = null,
        private var endDateTime: java.time.LocalDateTime? = null
    ) {
        fun penalty(penalty: PenaltyType) = apply { this.penalty = penalty }

        fun startDateTime(startDateTime: java.time.LocalDateTime) = apply { this.startDateTime = startDateTime }

        fun endDateTime(endDateTime: java.time.LocalDateTime) = apply { this.endDateTime = endDateTime }

        fun build(): PenaltyRule {
            val instance =
                PenaltyRule(
                    penalty = penalty!!,
                    startDateTime = startDateTime!!,
                    endDateTime = endDateTime!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: PenaltyRule) {
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
            penalty = penalty!!,
            startDateTime = startDateTime!!,
            endDateTime = endDateTime!!
        )
}
