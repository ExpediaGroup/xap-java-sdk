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

import com.fasterxml.jackson.annotation.JsonProperty

/**
* The extra distance information.
 * @param `value` The number of miles/kilometers of the distance (specified by the Unit).
 * @param unit The unit (KM or MI) for the distance.
 * @param direction The direction of the location from the search 'center'.Possible values are: N,S,W,E,NW,NE,SW,SE
*/
data class CarsDistance(
    // The number of miles/kilometers of the distance (specified by the Unit).
    @JsonProperty("Value")
    val `value`: kotlin.String,
    // The unit (KM or MI) for the distance.
    @JsonProperty("Unit")
    val unit: kotlin.String? = null,
    // The direction of the location from the search 'center'.Possible values are: N,S,W,E,NW,NE,SW,SE
    @JsonProperty("Direction")
    val direction: kotlin.String? = null,
) {
    init {
        require(`value` != null) { "`value` must not be null" }
    }

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

        fun unit(unit: kotlin.String?) = apply { this.unit = unit }

        fun direction(direction: kotlin.String?) = apply { this.direction = direction }

        fun build(): CarsDistance {
            val instance =
                CarsDistance(
                    `value` = `value`!!,
                    unit = unit,
                    direction = direction,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            `value` = `value`!!,
            unit = unit,
            direction = direction,
        )
}
