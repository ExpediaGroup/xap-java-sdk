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
import com.expediagroup.sdk.xap.models.FlightsV3AgeClassRestriction
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Room occupancy policy.
 * @param maxGuestCount The maximum number of guests allowed to stay in a room.
 * @param ageClassRestrictions Container for room occupancy rules based on the age of the guests.
 */
data class FlightsV3RoomOccupancyPolicy(
    // The maximum number of guests allowed to stay in a room.
    @JsonProperty("MaxGuestCount")
    val maxGuestCount: kotlin.Int,
    // Container for room occupancy rules based on the age of the guests.
    @JsonProperty("AgeClassRestrictions")
    @field:Valid
    val ageClassRestrictions: kotlin.collections.List<FlightsV3AgeClassRestriction>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var maxGuestCount: kotlin.Int? = null,
        private var ageClassRestrictions: kotlin.collections.List<FlightsV3AgeClassRestriction>? = null
    ) {
        fun maxGuestCount(maxGuestCount: kotlin.Int) = apply { this.maxGuestCount = maxGuestCount }

        fun ageClassRestrictions(ageClassRestrictions: kotlin.collections.List<FlightsV3AgeClassRestriction>?) = apply { this.ageClassRestrictions = ageClassRestrictions }

        fun build(): FlightsV3RoomOccupancyPolicy {
            val instance =
                FlightsV3RoomOccupancyPolicy(
                    maxGuestCount = maxGuestCount!!,
                    ageClassRestrictions = ageClassRestrictions
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightsV3RoomOccupancyPolicy) {
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
            maxGuestCount = maxGuestCount!!,
            ageClassRestrictions = ageClassRestrictions
        )
}
