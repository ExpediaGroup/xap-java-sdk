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
import com.expediagroup.sdk.xap.models.HotelRateCalendar
import com.expediagroup.sdk.xap.models.Warning
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 *
 * @param warnings There were some errors or events during the transaction, but the API has still returned a response.  Container for all warnings.
 * @param transactionId Unique identifier for the transaction.
 * @param rateCalendars Container for all hotel rate calendar data.
 */
data class RateCalendarResponse(
    // There were some errors or events during the transaction, but the API has still returned a response.  Container for all warnings.
    @JsonProperty("Warnings")
    @field:Valid
    val warnings: kotlin.collections.List<Warning>? = null,
    // Unique identifier for the transaction.
    @JsonProperty("TransactionId")
    @field:Valid
    val transactionId: kotlin.String? = null,
    // Container for all hotel rate calendar data.
    @JsonProperty("RateCalendars")
    @field:Valid
    val rateCalendars: kotlin.collections.List<HotelRateCalendar>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var warnings: kotlin.collections.List<Warning>? = null,
        private var transactionId: kotlin.String? = null,
        private var rateCalendars: kotlin.collections.List<HotelRateCalendar>? = null
    ) {
        fun warnings(warnings: kotlin.collections.List<Warning>?) = apply { this.warnings = warnings }

        fun transactionId(transactionId: kotlin.String?) = apply { this.transactionId = transactionId }

        fun rateCalendars(rateCalendars: kotlin.collections.List<HotelRateCalendar>?) = apply { this.rateCalendars = rateCalendars }

        fun build(): RateCalendarResponse {
            val instance =
                RateCalendarResponse(
                    warnings = warnings,
                    transactionId = transactionId,
                    rateCalendars = rateCalendars
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: RateCalendarResponse) {
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
            warnings = warnings,
            transactionId = transactionId,
            rateCalendars = rateCalendars
        )
}
