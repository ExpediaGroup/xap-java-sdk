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

import com.expediagroup.sdk.xap.models.AvailabilityCalendar
import com.expediagroup.sdk.xap.models.LodgingWarning

import com.fasterxml.jackson.annotation.JsonProperty

/**
* 
    * @param warnings There were some errors or events during the transaction, but the API has still returned a response.  Container for all warnings. 
    * @param transactionId Unique identifier for the transaction.
    * @param availabilityCalendars A list of the calendar entities.
*/
data class AvailabilityCalendarResponse(
            /* There were some errors or events during the transaction, but the API has still returned a response.  Container for all warnings.  */
@JsonProperty("Warnings")
val warnings: kotlin.collections.List<LodgingWarning>? = null,

            /* Unique identifier for the transaction. */
@JsonProperty("TransactionId")
val transactionId: kotlin.String? = null,

            /* A list of the calendar entities. */
@JsonProperty("AvailabilityCalendars")
val availabilityCalendars: kotlin.collections.List<AvailabilityCalendar>? = null
) {
    


    init {
        





















    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var warnings: kotlin.collections.List<LodgingWarning>? = null,
                private var transactionId: kotlin.String? = null,
                private var availabilityCalendars: kotlin.collections.List<AvailabilityCalendar>? = null
        ) {
                fun warnings(warnings: kotlin.collections.List<LodgingWarning>?) = apply { this.warnings = warnings }
                fun transactionId(transactionId: kotlin.String?) = apply { this.transactionId = transactionId }
                fun availabilityCalendars(availabilityCalendars: kotlin.collections.List<AvailabilityCalendar>?) = apply { this.availabilityCalendars = availabilityCalendars }

    fun build(): AvailabilityCalendarResponse {
    val instance = AvailabilityCalendarResponse(
            warnings = warnings,
            transactionId = transactionId,
            availabilityCalendars = availabilityCalendars
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            warnings = warnings,
            transactionId = transactionId,
            availabilityCalendars = availabilityCalendars
    )
}
