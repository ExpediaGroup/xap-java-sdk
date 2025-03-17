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

import com.expediagroup.sdk.xap.models.CarsDateRange
import com.expediagroup.sdk.xap.models.TimeRange

import com.fasterxml.jackson.annotation.JsonProperty

/**
* A List of date time periods to indicate the vendor business hours for the pickup time.
    * @param dateRange 
    * @param timeRanges A list of time range to indicate the operation hours of the date range.
*/
data class DateTimePeriod(
        @JsonProperty("DateRange")
val dateRange:
    CarsDateRange
,

            /* A list of time range to indicate the operation hours of the date range. */
@JsonProperty("TimeRanges")
val timeRanges: kotlin.collections.List<TimeRange>? = null
) {
    


    init {
                require(dateRange != null) { "dateRange must not be null" }















    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var dateRange: CarsDateRange? = null,
                private var timeRanges: kotlin.collections.List<TimeRange>? = null
        ) {
                fun dateRange(dateRange: CarsDateRange) = apply { this.dateRange = dateRange }
                fun timeRanges(timeRanges: kotlin.collections.List<TimeRange>?) = apply { this.timeRanges = timeRanges }

    fun build(): DateTimePeriod {
    val instance = DateTimePeriod(
            dateRange = dateRange!!,
            timeRanges = timeRanges
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            dateRange = dateRange!!,
            timeRanges = timeRanges
    )
}
