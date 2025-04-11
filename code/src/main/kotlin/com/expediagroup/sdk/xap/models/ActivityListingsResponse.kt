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
import com.expediagroup.sdk.xap.models.CategoryGroup
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 *
 * @param transactionId A unique identifier for this transaction.
 * @param count The number of activities returned in the response.
 * @param location The location that the user searched, expressed in the exact format that the inventory system uses to designate the location.
 * @param warnings Container for all warnings generated during the transaction.
 * @param startDate The startDate of the returned group of activities in YYYY-MM-DD format.
 * @param endDate The endDate of returned group of activities in YYYY-MM-DD format.
 * @param activities List of activities matching the search criteria.
 * @param categories Container for a breakdown of how many of each type of Activity have been returned in the API response.
 */
data class ActivityListingsResponse(
    // A unique identifier for this transaction.
    @JsonProperty("TransactionId")
    @field:NotNull
    @field:Valid
    val transactionId: kotlin.String,
    // The number of activities returned in the response.
    @JsonProperty("Count")
    val count: kotlin.Long,
    // The location that the user searched, expressed in the exact format that the inventory system uses to designate the location.
    @JsonProperty("Location")
    @field:NotNull
    @field:Valid
    val location: kotlin.String,
    // Container for all warnings generated during the transaction.
    @JsonProperty("Warnings")
    @field:Valid
    val warnings: kotlin.collections.List<ActivitiesWarning>? = null,
    // The startDate of the returned group of activities in YYYY-MM-DD format.
    @JsonProperty("StartDate")
    val startDate: java.time.LocalDate? = null,
    // The endDate of returned group of activities in YYYY-MM-DD format.
    @JsonProperty("EndDate")
    val endDate: java.time.LocalDate? = null,
    // List of activities matching the search criteria.
    @JsonProperty("Activities")
    @field:Valid
    val activities: kotlin.collections.List<Activity>? = null,
    // Container for a breakdown of how many of each type of Activity have been returned in the API response.
    @JsonProperty("Categories")
    @field:Valid
    val categories: kotlin.collections.List<CategoryGroup>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var transactionId: kotlin.String? = null,
        private var count: kotlin.Long? = null,
        private var location: kotlin.String? = null,
        private var warnings: kotlin.collections.List<ActivitiesWarning>? = null,
        private var startDate: java.time.LocalDate? = null,
        private var endDate: java.time.LocalDate? = null,
        private var activities: kotlin.collections.List<Activity>? = null,
        private var categories: kotlin.collections.List<CategoryGroup>? = null
    ) {
        fun transactionId(transactionId: kotlin.String) = apply { this.transactionId = transactionId }

        fun count(count: kotlin.Long) = apply { this.count = count }

        fun location(location: kotlin.String) = apply { this.location = location }

        fun warnings(warnings: kotlin.collections.List<ActivitiesWarning>?) = apply { this.warnings = warnings }

        fun startDate(startDate: java.time.LocalDate?) = apply { this.startDate = startDate }

        fun endDate(endDate: java.time.LocalDate?) = apply { this.endDate = endDate }

        fun activities(activities: kotlin.collections.List<Activity>?) = apply { this.activities = activities }

        fun categories(categories: kotlin.collections.List<CategoryGroup>?) = apply { this.categories = categories }

        fun build(): ActivityListingsResponse {
            val instance =
                ActivityListingsResponse(
                    transactionId = transactionId!!,
                    count = count!!,
                    location = location!!,
                    warnings = warnings,
                    startDate = startDate,
                    endDate = endDate,
                    activities = activities,
                    categories = categories
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: ActivityListingsResponse) {
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
            count = count!!,
            location = location!!,
            warnings = warnings,
            startDate = startDate,
            endDate = endDate,
            activities = activities,
            categories = categories
        )
}
