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
import com.expediagroup.sdk.xap.model.BaggageFee
import com.expediagroup.sdk.xap.model.BaggageFeeFlightSegment
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for information on Baggage fee information of each Segment.
 * @param flightSegment
 * @param baggageFees Container for baggage fee information of each bag type. The baggage fee can vary for each bag type. The amount can be zero, fixed amount or will be in a range
 */
@ConsistentCopyVisibility data class FlightBaggageFee private constructor(
    @JsonProperty("FlightSegment")
    val flightSegment: BaggageFeeFlightSegment,

    /* Container for baggage fee information of each bag type. The baggage fee can vary for each bag type. The amount can be zero, fixed amount or will be in a range */
    @JsonProperty("BaggageFees")
    val baggageFees: kotlin.collections
        .List<
            BaggageFee,
            >,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var flightSegment: BaggageFeeFlightSegment? = null,
        private var baggageFees: kotlin.collections.List<BaggageFee>? = null,
    ) {
        fun flightSegment(flightSegment: BaggageFeeFlightSegment) = apply { this.flightSegment = flightSegment }

        fun baggageFees(baggageFees: kotlin.collections.List<BaggageFee>) = apply { this.baggageFees = baggageFees }

        fun build(): FlightBaggageFee {
            val flightSegment = this.flightSegment.getOrThrow {
                IllegalArgumentException("flightSegment must not be null")
            }

            val baggageFees = this.baggageFees.getOrThrow {
                IllegalArgumentException("baggageFees must not be null")
            }

            val instance = FlightBaggageFee(
                flightSegment = flightSegment,
                baggageFees = baggageFees,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        flightSegment = flightSegment,
        baggageFees = baggageFees,
    )
}
