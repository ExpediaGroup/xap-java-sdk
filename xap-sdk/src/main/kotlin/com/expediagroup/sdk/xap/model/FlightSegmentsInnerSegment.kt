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

import com.expediagroup.sdk.xap.model.FlightSegmentsInnerSegmentLegsInner
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param fareType
 * @param provider
 * @param legs
 * @param lowCost
 */
data class FlightSegmentsInnerSegment(
    @JsonProperty("FareType")
    val fareType: FlightSegmentsInnerSegment.FareType,
    @JsonProperty("Provider")
    val provider: kotlin.String,
    @JsonProperty("Legs")
    val legs: kotlin.collections
        .List<
            FlightSegmentsInnerSegmentLegsInner,
        >,
    @JsonProperty("LowCost")
    val lowCost: kotlin.Boolean? = null,
) {
    init {
        require(fareType != null) { "fareType must not be null" }

        require(provider != null) { "provider must not be null" }

        require(legs != null) { "legs must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var fareType: FlightSegmentsInnerSegment.FareType? = null,
        private var provider: kotlin.String? = null,
        private var legs: kotlin.collections.List<FlightSegmentsInnerSegmentLegsInner>? = null,
        private var lowCost: kotlin.Boolean? = null,
    ) {
        fun fareType(fareType: FlightSegmentsInnerSegment.FareType) = apply { this.fareType = fareType }

        fun provider(provider: kotlin.String) = apply { this.provider = provider }

        fun legs(legs: kotlin.collections.List<FlightSegmentsInnerSegmentLegsInner>) = apply { this.legs = legs }

        fun lowCost(lowCost: kotlin.Boolean?) = apply { this.lowCost = lowCost }

        fun build(): FlightSegmentsInnerSegment {
            val instance =
                FlightSegmentsInnerSegment(
                    fareType = fareType!!,
                    provider = provider!!,
                    legs = legs!!,
                    lowCost = lowCost,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            fareType = fareType!!,
            provider = provider!!,
            legs = legs!!,
            lowCost = lowCost,
        )

    /**
     *
     * Values: PUBLISHED,NET,WEB
     */
    enum class FareType(
        val value: kotlin.String,
    ) {
        @JsonProperty("PUBLISHED")
        PUBLISHED("PUBLISHED"),

        @JsonProperty("NET")
        NET("NET"),

        @JsonProperty("WEB")
        WEB("WEB"),
    }
}
