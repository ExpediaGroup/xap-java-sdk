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

import com.expediagroup.sdk.xap.model.DateRange
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param propertyId The unique property identifier that designates a single property.
 * @param dateRange
 * @param availability A string of codes that shows property availability, one for every day in the specified date range.  Valid values include Y (available) and N (unavailable).  ***Note**: The first code stands for availability on the `StartDate` in the `DateRange` and the last one stands  for the `EndDate`.*
 * @param changeOver A string of codes that shows changeover action, one for every day in the specified date range.  Valid values include - X (no action possible) - C (check-in, checkout) - O (checkout only) - I (check-in only) ***Note**: The first code stands for possible action on the `StartDate` in the `DateRange` and the last one  stands for the `EndDate`. All actions are possible if not returned.*
 * @param minPriorNotify A comma-separated list of numbers that shows how many days before a reservation the booking must occur, one for every day in the specified date range. Valid values include 0-999, and 0 indicates no prior notification required for a given day. The unit is always day.  ***Note**: The first number stands for the minimum advance booking days on the `StartDate` in the `DateRange` and the last one stands for the `EndDate`. No limitation if not returned.*
 * @param minStay A comma-separated list of numbers that show the minimum number of days a traveler can stay, one for every day in the specified date range.  Valid values include 0-999, and 0 indicates no minimum for a given day. The unit is always day.  ***Note**: The first number stands for the minimum stay on the `StartDate` in the `DateRange` and the last one stands for the `EndDate`. No limitation if not returned.*
 * @param maxStay A comma-separated list of numbers that show the maximum number of days a traveler can stay, one for every day in the specified date range.  Valid values include 0-999, and 0 indicates no maximum for a given day. The unit is always day.  ***Note**: The first number stands for the maximum stay on the `StartDate` in the `DateRange` and the  last one stands for the `EndDate`. No limitation if not returned.
 */
@ConsistentCopyVisibility data class AvailabilityCalendar private constructor(
    /* The unique property identifier that designates a single property. */
    @JsonProperty("PropertyId")
    val propertyId: kotlin.String? = null,

    @JsonProperty("DateRange")
    val dateRange: DateRange? = null,

    /* A string of codes that shows property availability, one for every day in the specified date range.  Valid values include Y (available) and N (unavailable).  ***Note**: The first code stands for availability on the `StartDate` in the `DateRange` and the last one stands  for the `EndDate`.*  */
    @JsonProperty("Availability")
    val availability: kotlin.String? = null,

    /* A string of codes that shows changeover action, one for every day in the specified date range.  Valid values include - X (no action possible) - C (check-in, checkout) - O (checkout only) - I (check-in only) ***Note**: The first code stands for possible action on the `StartDate` in the `DateRange` and the last one  stands for the `EndDate`. All actions are possible if not returned.*  */
    @JsonProperty("ChangeOver")
    val changeOver: kotlin.String? = null,

    /* A comma-separated list of numbers that shows how many days before a reservation the booking must occur, one for every day in the specified date range. Valid values include 0-999, and 0 indicates no prior notification required for a given day. The unit is always day.  ***Note**: The first number stands for the minimum advance booking days on the `StartDate` in the `DateRange` and the last one stands for the `EndDate`. No limitation if not returned.*  */
    @JsonProperty("MinPriorNotify")
    val minPriorNotify: kotlin.String? = null,

    /* A comma-separated list of numbers that show the minimum number of days a traveler can stay, one for every day in the specified date range.  Valid values include 0-999, and 0 indicates no minimum for a given day. The unit is always day.  ***Note**: The first number stands for the minimum stay on the `StartDate` in the `DateRange` and the last one stands for the `EndDate`. No limitation if not returned.*  */
    @JsonProperty("MinStay")
    val minStay: kotlin.String? = null,

    /* A comma-separated list of numbers that show the maximum number of days a traveler can stay, one for every day in the specified date range.  Valid values include 0-999, and 0 indicates no maximum for a given day. The unit is always day.  ***Note**: The first number stands for the maximum stay on the `StartDate` in the `DateRange` and the  last one stands for the `EndDate`. No limitation if not returned.  */
    @JsonProperty("MaxStay")
    val maxStay: kotlin.String? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var propertyId: kotlin.String? = null,
        private var dateRange: DateRange? = null,
        private var availability: kotlin.String? = null,
        private var changeOver: kotlin.String? = null,
        private var minPriorNotify: kotlin.String? = null,
        private var minStay: kotlin.String? = null,
        private var maxStay: kotlin.String? = null,
    ) {
        fun propertyId(propertyId: kotlin.String?) = apply { this.propertyId = propertyId }

        fun dateRange(dateRange: DateRange?) = apply { this.dateRange = dateRange }

        fun availability(availability: kotlin.String?) = apply { this.availability = availability }

        fun changeOver(changeOver: kotlin.String?) = apply { this.changeOver = changeOver }

        fun minPriorNotify(minPriorNotify: kotlin.String?) = apply { this.minPriorNotify = minPriorNotify }

        fun minStay(minStay: kotlin.String?) = apply { this.minStay = minStay }

        fun maxStay(maxStay: kotlin.String?) = apply { this.maxStay = maxStay }

        fun build(): AvailabilityCalendar {
            val instance = AvailabilityCalendar(
                propertyId = propertyId,
                dateRange = dateRange,
                availability = availability,
                changeOver = changeOver,
                minPriorNotify = minPriorNotify,
                minStay = minStay,
                maxStay = maxStay,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        propertyId = propertyId,
        dateRange = dateRange,
        availability = availability,
        changeOver = changeOver,
        minPriorNotify = minPriorNotify,
        minStay = minStay,
        maxStay = maxStay,
    )
}
