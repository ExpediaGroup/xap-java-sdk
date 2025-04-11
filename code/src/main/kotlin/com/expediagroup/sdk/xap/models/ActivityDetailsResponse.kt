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
import com.expediagroup.sdk.xap.models.ActivitiesWarning
import com.expediagroup.sdk.xap.models.Activity
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 *
 * @param transactionId A unique identifier for this transaction.
 * @param location The location user searched, translated into the full, unambiguous format.
 * @param warnings Container for all warnings generated during the transaction.
 * @param startDate The startDate of activities in YYY-MM-DD format.
 * @param endDate The endDate of activities in YYY-MM-DD format.
 * @param activityDetails
 */
data class ActivityDetailsResponse(
    // A unique identifier for this transaction.
    @JsonProperty("TransactionId")
    @field:NotNull
    @field:Valid
    val transactionId: kotlin.String,
    // The location user searched, translated into the full, unambiguous format.
    @JsonProperty("Location")
    @field:NotNull
    @field:Valid
    val location: kotlin.String,
    // Container for all warnings generated during the transaction.
    @JsonProperty("Warnings")
    @field:Valid
    val warnings: kotlin.collections.List<ActivitiesWarning>? = null,
    // The startDate of activities in YYY-MM-DD format.
    @JsonProperty("StartDate")
    val startDate: java.time.LocalDate? = null,
    // The endDate of activities in YYY-MM-DD format.
    @JsonProperty("EndDate")
    val endDate: java.time.LocalDate? = null,
    @JsonProperty("ActivityDetails")
    @field:Valid
    val activityDetails: Activity? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var transactionId: kotlin.String? = null,
        private var location: kotlin.String? = null,
        private var warnings: kotlin.collections.List<ActivitiesWarning>? = null,
        private var startDate: java.time.LocalDate? = null,
        private var endDate: java.time.LocalDate? = null,
        private var activityDetails: Activity? = null
    ) {
        fun transactionId(transactionId: kotlin.String) = apply { this.transactionId = transactionId }

        fun location(location: kotlin.String) = apply { this.location = location }

        fun warnings(warnings: kotlin.collections.List<ActivitiesWarning>?) = apply { this.warnings = warnings }

        fun startDate(startDate: java.time.LocalDate?) = apply { this.startDate = startDate }

        fun endDate(endDate: java.time.LocalDate?) = apply { this.endDate = endDate }

        fun activityDetails(activityDetails: Activity?) = apply { this.activityDetails = activityDetails }

        fun build(): ActivityDetailsResponse {
            val instance =
                ActivityDetailsResponse(
                    transactionId = transactionId!!,
                    location = location!!,
                    warnings = warnings,
                    startDate = startDate,
                    endDate = endDate,
                    activityDetails = activityDetails
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: ActivityDetailsResponse) {
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
            location = location!!,
            warnings = warnings,
            startDate = startDate,
            endDate = endDate,
            activityDetails = activityDetails
        )
}
