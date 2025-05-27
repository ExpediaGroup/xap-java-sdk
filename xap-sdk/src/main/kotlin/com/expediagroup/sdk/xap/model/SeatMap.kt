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
import com.expediagroup.sdk.xap.model.Row
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for seatmap information.
 * @param cabinClass Cabin code for different class of service offered by the airline
 * @param rows Container for list of seat row information.
 */
@ConsistentCopyVisibility data class SeatMap private constructor(
    /* Cabin code for different class of service offered by the airline */
    @JsonProperty("CabinClass")
    val cabinClass: SeatMap.CabinClass,

    /* Container for list of seat row information. */
    @JsonProperty("Rows")
    val rows: kotlin.collections
        .List<
            Row,
            >,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var cabinClass: SeatMap.CabinClass? = null,
        private var rows: kotlin.collections.List<Row>? = null,
    ) {
        fun cabinClass(cabinClass: SeatMap.CabinClass) = apply { this.cabinClass = cabinClass }

        fun rows(rows: kotlin.collections.List<Row>) = apply { this.rows = rows }

        fun build(): SeatMap {
            val cabinClass = this.cabinClass.getOrThrow {
                IllegalArgumentException("cabinClass must not be null")
            }

            val rows = this.rows.getOrThrow {
                IllegalArgumentException("rows must not be null")
            }

            val instance = SeatMap(
                cabinClass = cabinClass,
                rows = rows,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        cabinClass = cabinClass,
        rows = rows,
    )

    /**
     * Cabin code for different class of service offered by the airline
     * Values: ECONOMY,FIRST,BUSINESS,PREMIUM_ECONOMY
     */
    enum class CabinClass(val value: kotlin.String) {
        @JsonProperty("ECONOMY")
        ECONOMY("ECONOMY"),

        @JsonProperty("FIRST")
        FIRST("FIRST"),

        @JsonProperty("BUSINESS")
        BUSINESS("BUSINESS"),

        @JsonProperty("PREMIUM_ECONOMY")
        PREMIUM_ECONOMY("PREMIUM_ECONOMY"),
    }
}
