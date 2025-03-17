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

import com.expediagroup.sdk.xap.models.CancellationPenaltyRulePenaltyPrice

import com.fasterxml.jackson.annotation.JsonProperty

/**
* 
    * @param penaltyNightCount Specifies the per-stay cancellation fee charged in terms of the cost of the number of nights listed, in addition to any other penalties. The rate charged is based on the earliest night(s) of the stay. 
    * @param penaltyPercentOfStay Specifies the per-stay cancellation fee charged as a percentage of the total rate, in addition to any other penalties listed. 
    * @param penaltyPrice 
    * @param penaltyStartDateTime The beginning of the window of time when the `CancellationPenaltyRule` is in effect.  The date and time are expressed in ISO 8601 International Date format, and local to the hotel. 
    * @param penaltyEndDateTime The end of the window of time when the `CancellationPenaltyRule` is in effect.  The date and time are expressed in ISO 8601 International Date format, and local to the hotel. 
*/
data class CancellationPenaltyRule(
            /* Specifies the per-stay cancellation fee charged in terms of the cost of the number of nights listed, in addition to any other penalties. The rate charged is based on the earliest night(s) of the stay.  */
@JsonProperty("PenaltyNightCount")
val penaltyNightCount: kotlin.Int? = null,

            /* Specifies the per-stay cancellation fee charged as a percentage of the total rate, in addition to any other penalties listed.  */
@JsonProperty("PenaltyPercentOfStay")
val penaltyPercentOfStay: kotlin.String? = null,

        @JsonProperty("PenaltyPrice")
val penaltyPrice: CancellationPenaltyRulePenaltyPrice? = null,

            /* The beginning of the window of time when the `CancellationPenaltyRule` is in effect.  The date and time are expressed in ISO 8601 International Date format, and local to the hotel.  */
@JsonProperty("PenaltyStartDateTime")
val penaltyStartDateTime: java.time.OffsetDateTime? = null,

            /* The end of the window of time when the `CancellationPenaltyRule` is in effect.  The date and time are expressed in ISO 8601 International Date format, and local to the hotel.  */
@JsonProperty("PenaltyEndDateTime")
val penaltyEndDateTime: java.time.OffsetDateTime? = null
) {
    


    init {
        



































    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var penaltyNightCount: kotlin.Int? = null,
                private var penaltyPercentOfStay: kotlin.String? = null,
                private var penaltyPrice: CancellationPenaltyRulePenaltyPrice? = null,
                private var penaltyStartDateTime: java.time.OffsetDateTime? = null,
                private var penaltyEndDateTime: java.time.OffsetDateTime? = null
        ) {
                fun penaltyNightCount(penaltyNightCount: kotlin.Int?) = apply { this.penaltyNightCount = penaltyNightCount }
                fun penaltyPercentOfStay(penaltyPercentOfStay: kotlin.String?) = apply { this.penaltyPercentOfStay = penaltyPercentOfStay }
                fun penaltyPrice(penaltyPrice: CancellationPenaltyRulePenaltyPrice?) = apply { this.penaltyPrice = penaltyPrice }
                fun penaltyStartDateTime(penaltyStartDateTime: java.time.OffsetDateTime?) = apply { this.penaltyStartDateTime = penaltyStartDateTime }
                fun penaltyEndDateTime(penaltyEndDateTime: java.time.OffsetDateTime?) = apply { this.penaltyEndDateTime = penaltyEndDateTime }

    fun build(): CancellationPenaltyRule {
    val instance = CancellationPenaltyRule(
            penaltyNightCount = penaltyNightCount,
            penaltyPercentOfStay = penaltyPercentOfStay,
            penaltyPrice = penaltyPrice,
            penaltyStartDateTime = penaltyStartDateTime,
            penaltyEndDateTime = penaltyEndDateTime
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            penaltyNightCount = penaltyNightCount,
            penaltyPercentOfStay = penaltyPercentOfStay,
            penaltyPrice = penaltyPrice,
            penaltyStartDateTime = penaltyStartDateTime,
            penaltyEndDateTime = penaltyEndDateTime
    )
}
