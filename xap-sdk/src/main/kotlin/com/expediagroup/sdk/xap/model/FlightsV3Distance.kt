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
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for distance of flight.
 * @param `value` The number of miles/kilometers of the distance (specified by the Unit).
 * @param unit The unit (KM or MI) for the distance.
 * @param direction The direction of the location from the search 'center'.Possible values are: N,S,W,E,NW,NE,SW,SE
 */
@ConsistentCopyVisibility data class FlightsV3Distance private constructor(
    /* The number of miles/kilometers of the distance (specified by the Unit). */
    @JsonProperty("Value")
    val `value`: kotlin.String,

    /* The unit (KM or MI) for the distance. */
    @JsonProperty("Unit")
    val unit: kotlin.String,

    /* The direction of the location from the search 'center'.Possible values are: N,S,W,E,NW,NE,SW,SE */
    @JsonProperty("Direction")
    val direction: kotlin.String? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var `value`: kotlin.String? = null,
        private var unit: kotlin.String? = null,
        private var direction: kotlin.String? = null,
    ) {
        fun `value`(`value`: kotlin.String) = apply { this.`value` = `value` }

        fun unit(unit: kotlin.String) = apply { this.unit = unit }

        fun direction(direction: kotlin.String?) = apply { this.direction = direction }

        fun build(): FlightsV3Distance {
            val `value` = this.`value`.getOrThrow {
                IllegalArgumentException("`value` must not be null")
            }

            val unit = this.unit.getOrThrow {
                IllegalArgumentException("unit must not be null")
            }

            val instance = FlightsV3Distance(
                `value` = `value`,
                unit = unit,
                direction = direction,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        `value` = `value`,
        unit = unit,
        direction = direction,
    )
}
