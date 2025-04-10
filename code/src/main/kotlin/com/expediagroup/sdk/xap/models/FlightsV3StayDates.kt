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
import javax.validation.Validation

/**
 * Container for Dates of Stay information
 * @param checkInDate The initial day of the hotel stay in an ISO 8601 Date format [YYYY-MM-DD].
 * @param checkOutDate The final day of the hotel stay in an ISO 8601 Date format [YYYY-MM-DD].
 */
data class FlightsV3StayDates(
    // The initial day of the hotel stay in an ISO 8601 Date format [YYYY-MM-DD].
    @JsonProperty("CheckInDate")
    val checkInDate: java.time.LocalDate,
    // The final day of the hotel stay in an ISO 8601 Date format [YYYY-MM-DD].
    @JsonProperty("CheckOutDate")
    val checkOutDate: java.time.LocalDate
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var checkInDate: java.time.LocalDate? = null,
        private var checkOutDate: java.time.LocalDate? = null
    ) {
        fun checkInDate(checkInDate: java.time.LocalDate) = apply { this.checkInDate = checkInDate }

        fun checkOutDate(checkOutDate: java.time.LocalDate) = apply { this.checkOutDate = checkOutDate }

        fun build(): FlightsV3StayDates {
            val instance =
                FlightsV3StayDates(
                    checkInDate = checkInDate!!,
                    checkOutDate = checkOutDate!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightsV3StayDates) {
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
            checkInDate = checkInDate!!,
            checkOutDate = checkOutDate!!
        )
}
