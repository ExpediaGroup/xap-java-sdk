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

import com.expediagroup.sdk.xap.models.LodgingOccupant
import com.expediagroup.sdk.xap.models.LodgingStayDates
import com.expediagroup.sdk.xap.models.LodgingWarning
import com.expediagroup.sdk.xap.models.Property

import com.fasterxml.jackson.annotation.JsonProperty

/**
* 
    * @param warnings There were some errors or events during the transaction, but the API has still returned a response.  Container for all warnings. 
    * @param count The number of properties actually returned in the response.
    * @param totalPropertyCount The number of properties requested.
    * @param transactionId Unique identifier for the API transaction.
    * @param stayDates 
    * @param lengthOfStay The number of stay nights.
    * @param occupants Container for the list of room occupants.
    * @param properties Container for all properties.
*/
data class LodgingQuotesResponse(
            /* There were some errors or events during the transaction, but the API has still returned a response.  Container for all warnings.  */
@JsonProperty("Warnings")
val warnings: kotlin.collections.List<LodgingWarning>? = null,

            /* The number of properties actually returned in the response. */
@JsonProperty("Count")
val count: kotlin.Int? = null,

            /* The number of properties requested. */
@JsonProperty("TotalPropertyCount")
val totalPropertyCount: kotlin.Int? = null,

            /* Unique identifier for the API transaction. */
@JsonProperty("TransactionId")
val transactionId: kotlin.String? = null,

        @JsonProperty("StayDates")
val stayDates: LodgingStayDates? = null,

            /* The number of stay nights. */
@JsonProperty("LengthOfStay")
val lengthOfStay: kotlin.Int? = null,

            /* Container for the list of room occupants. */
@JsonProperty("Occupants")
val occupants: kotlin.collections.List<LodgingOccupant>? = null,

            /* Container for all properties. */
@JsonProperty("Properties")
val properties: kotlin.collections.List<Property>? = null
) {
    


    init {
        
























































    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var warnings: kotlin.collections.List<LodgingWarning>? = null,
                private var count: kotlin.Int? = null,
                private var totalPropertyCount: kotlin.Int? = null,
                private var transactionId: kotlin.String? = null,
                private var stayDates: LodgingStayDates? = null,
                private var lengthOfStay: kotlin.Int? = null,
                private var occupants: kotlin.collections.List<LodgingOccupant>? = null,
                private var properties: kotlin.collections.List<Property>? = null
        ) {
                fun warnings(warnings: kotlin.collections.List<LodgingWarning>?) = apply { this.warnings = warnings }
                fun count(count: kotlin.Int?) = apply { this.count = count }
                fun totalPropertyCount(totalPropertyCount: kotlin.Int?) = apply { this.totalPropertyCount = totalPropertyCount }
                fun transactionId(transactionId: kotlin.String?) = apply { this.transactionId = transactionId }
                fun stayDates(stayDates: LodgingStayDates?) = apply { this.stayDates = stayDates }
                fun lengthOfStay(lengthOfStay: kotlin.Int?) = apply { this.lengthOfStay = lengthOfStay }
                fun occupants(occupants: kotlin.collections.List<LodgingOccupant>?) = apply { this.occupants = occupants }
                fun properties(properties: kotlin.collections.List<Property>?) = apply { this.properties = properties }

    fun build(): LodgingQuotesResponse {
    val instance = LodgingQuotesResponse(
            warnings = warnings,
            count = count,
            totalPropertyCount = totalPropertyCount,
            transactionId = transactionId,
            stayDates = stayDates,
            lengthOfStay = lengthOfStay,
            occupants = occupants,
            properties = properties
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            warnings = warnings,
            count = count,
            totalPropertyCount = totalPropertyCount,
            transactionId = transactionId,
            stayDates = stayDates,
            lengthOfStay = lengthOfStay,
            occupants = occupants,
            properties = properties
    )
}
