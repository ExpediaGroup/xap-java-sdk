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
import com.expediagroup.sdk.xap.models.ActivitiesCancellationPolicy
import com.expediagroup.sdk.xap.models.Ticket
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * The list of available Time Slots for the activity.
 * @param dateTime The date and time when the activity happens.
 * @param allDayActivity Indicates whether the activity is an all-day activity.
 * @param cancellationPolicy
 * @param tickets Container for ticket information.
 */
data class AvailableTimeSlot(
    // The date and time when the activity happens.
    @JsonProperty("DateTime")
    val dateTime: java.time.LocalDateTime,
    // Indicates whether the activity is an all-day activity.
    @JsonProperty("AllDayActivity")
    @field:NotNull
    @field:Valid
    val allDayActivity: kotlin.Boolean,
    @JsonProperty("CancellationPolicy")
    @field:NotNull
    @field:Valid
    val cancellationPolicy: ActivitiesCancellationPolicy,
    // Container for ticket information.
    @JsonProperty("Tickets")
    @field:NotNull
    @field:Valid
    val tickets: kotlin.collections
        .List<
            Ticket
        >
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var dateTime: java.time.LocalDateTime? = null,
        private var allDayActivity: kotlin.Boolean? = null,
        private var cancellationPolicy: ActivitiesCancellationPolicy? = null,
        private var tickets: kotlin.collections.List<Ticket>? = null
    ) {
        fun dateTime(dateTime: java.time.LocalDateTime) = apply { this.dateTime = dateTime }

        fun allDayActivity(allDayActivity: kotlin.Boolean) = apply { this.allDayActivity = allDayActivity }

        fun cancellationPolicy(cancellationPolicy: ActivitiesCancellationPolicy) = apply { this.cancellationPolicy = cancellationPolicy }

        fun tickets(tickets: kotlin.collections.List<Ticket>) = apply { this.tickets = tickets }

        fun build(): AvailableTimeSlot {
            val instance =
                AvailableTimeSlot(
                    dateTime = dateTime!!,
                    allDayActivity = allDayActivity!!,
                    cancellationPolicy = cancellationPolicy!!,
                    tickets = tickets!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: AvailableTimeSlot) {
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
            dateTime = dateTime!!,
            allDayActivity = allDayActivity!!,
            cancellationPolicy = cancellationPolicy!!,
            tickets = tickets!!
        )
}
