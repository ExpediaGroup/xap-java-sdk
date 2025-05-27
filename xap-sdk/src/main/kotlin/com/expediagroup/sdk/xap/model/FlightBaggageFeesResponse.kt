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
import com.expediagroup.sdk.xap.model.FlightBaggageFee
import com.expediagroup.sdk.xap.model.FlightsV1Warning
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param flightBaggageFees Container for information on Baggage fee information of each Segment.
 * @param transactionId Unique identifier for the transaction.
 * @param warnings Container for Warning messages.
 */
@ConsistentCopyVisibility data class FlightBaggageFeesResponse private constructor(
    /* Container for information on Baggage fee information of each Segment. */
    @JsonProperty("FlightBaggageFees")
    val flightBaggageFees: kotlin.collections
        .List<
            FlightBaggageFee,
            >,

    /* Unique identifier for the transaction. */
    @JsonProperty("TransactionId")
    val transactionId: kotlin.String,

    /* Container for Warning messages. */
    @JsonProperty("Warnings")
    val warnings: kotlin.collections.List<FlightsV1Warning>? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var flightBaggageFees: kotlin.collections.List<FlightBaggageFee>? = null,
        private var transactionId: kotlin.String? = null,
        private var warnings: kotlin.collections.List<FlightsV1Warning>? = null,
    ) {
        fun flightBaggageFees(flightBaggageFees: kotlin.collections.List<FlightBaggageFee>) = apply { this.flightBaggageFees = flightBaggageFees }

        fun transactionId(transactionId: kotlin.String) = apply { this.transactionId = transactionId }

        fun warnings(warnings: kotlin.collections.List<FlightsV1Warning>?) = apply { this.warnings = warnings }

        fun build(): FlightBaggageFeesResponse {
            val flightBaggageFees = this.flightBaggageFees.getOrThrow {
                IllegalArgumentException("flightBaggageFees must not be null")
            }

            val transactionId = this.transactionId.getOrThrow {
                IllegalArgumentException("transactionId must not be null")
            }

            val instance = FlightBaggageFeesResponse(
                flightBaggageFees = flightBaggageFees,
                transactionId = transactionId,
                warnings = warnings,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        flightBaggageFees = flightBaggageFees,
        transactionId = transactionId,
        warnings = warnings,
    )
}
