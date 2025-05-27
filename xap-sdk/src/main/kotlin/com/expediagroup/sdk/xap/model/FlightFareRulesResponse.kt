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
import com.expediagroup.sdk.xap.model.FlightFareRuleItem
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param flightFareRule Container for segment and corresponding fare rules.
 * @param transactionId Unique identifier for the transaction.
 */
@ConsistentCopyVisibility data class FlightFareRulesResponse private constructor(
    /* Container for segment and corresponding fare rules. */
    @JsonProperty("FlightFareRule")
    val flightFareRule: kotlin.collections
        .List<
            FlightFareRuleItem,
            >,

    /* Unique identifier for the transaction. */
    @JsonProperty("TransactionId")
    val transactionId: kotlin.String,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var flightFareRule: kotlin.collections.List<FlightFareRuleItem>? = null,
        private var transactionId: kotlin.String? = null,
    ) {
        fun flightFareRule(flightFareRule: kotlin.collections.List<FlightFareRuleItem>) = apply { this.flightFareRule = flightFareRule }

        fun transactionId(transactionId: kotlin.String) = apply { this.transactionId = transactionId }

        fun build(): FlightFareRulesResponse {
            val flightFareRule = this.flightFareRule.getOrThrow {
                IllegalArgumentException("flightFareRule must not be null")
            }

            val transactionId = this.transactionId.getOrThrow {
                IllegalArgumentException("transactionId must not be null")
            }

            val instance = FlightFareRulesResponse(
                flightFareRule = flightFareRule,
                transactionId = transactionId,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        flightFareRule = flightFareRule,
        transactionId = transactionId,
    )
}
