/**
 * Copyright (C) 2025 Expedia, Inc.
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
package com.expediagroup.sdk.xap.model

import com.expediagroup.sdk.core.common.getOrThrow
import com.expediagroup.sdk.xap.model.ActivitiesCancellationPolicy
import com.expediagroup.sdk.xap.model.Ticket
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * The list of available Time Slots for the activity.
 * @param dateTime The date and time when the activity happens.
 * @param allDayActivity Indicates whether the activity is an all-day activity.
 * @param cancellationPolicy
 * @param tickets Container for ticket information.
 */
@ConsistentCopyVisibility data class AvailableTimeSlot private constructor(
    /* The date and time when the activity happens. */
    @JsonProperty("DateTime")
    val dateTime: java.time.LocalDateTime,

    /* Indicates whether the activity is an all-day activity. */
    @JsonProperty("AllDayActivity")
    val allDayActivity: kotlin.Boolean,

    @JsonProperty("CancellationPolicy")
    val cancellationPolicy: ActivitiesCancellationPolicy,

    /* Container for ticket information. */
    @JsonProperty("Tickets")
    val tickets: kotlin.collections
        .List<
            Ticket,
            >,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var dateTime: java.time.LocalDateTime? = null,
        private var allDayActivity: kotlin.Boolean? = null,
        private var cancellationPolicy: ActivitiesCancellationPolicy? = null,
        private var tickets: kotlin.collections.List<Ticket>? = null,
    ) {
        fun dateTime(dateTime: java.time.LocalDateTime) = apply { this.dateTime = dateTime }

        fun allDayActivity(allDayActivity: kotlin.Boolean) = apply { this.allDayActivity = allDayActivity }

        fun cancellationPolicy(cancellationPolicy: ActivitiesCancellationPolicy) = apply { this.cancellationPolicy = cancellationPolicy }

        fun tickets(tickets: kotlin.collections.List<Ticket>) = apply { this.tickets = tickets }

        fun build(): AvailableTimeSlot {
            val dateTime = this.dateTime.getOrThrow {
                IllegalArgumentException("dateTime must not be null")
            }

            val allDayActivity = this.allDayActivity.getOrThrow {
                IllegalArgumentException("allDayActivity must not be null")
            }

            val cancellationPolicy = this.cancellationPolicy.getOrThrow {
                IllegalArgumentException("cancellationPolicy must not be null")
            }

            val tickets = this.tickets.getOrThrow {
                IllegalArgumentException("tickets must not be null")
            }

            val instance = AvailableTimeSlot(
                dateTime = dateTime,
                allDayActivity = allDayActivity,
                cancellationPolicy = cancellationPolicy,
                tickets = tickets,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        dateTime = dateTime,
        allDayActivity = allDayActivity,
        cancellationPolicy = cancellationPolicy,
        tickets = tickets,
    )
}
