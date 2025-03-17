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

import com.expediagroup.sdk.xap.models.CarsDistance
import com.expediagroup.sdk.xap.models.CarsMoney
import com.fasterxml.jackson.annotation.JsonProperty

/**
* Extra cost for each increment of distance used.
 * @param distance
 * @param cost
*/
data class ExtraCostPerDistance(
    @JsonProperty("Distance")
    val distance: CarsDistance,
    @JsonProperty("Cost")
    val cost: CarsMoney,
) {
    init {
        require(distance != null) { "distance must not be null" }

        require(cost != null) { "cost must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var distance: CarsDistance? = null,
        private var cost: CarsMoney? = null,
    ) {
        fun distance(distance: CarsDistance) = apply { this.distance = distance }

        fun cost(cost: CarsMoney) = apply { this.cost = cost }

        fun build(): ExtraCostPerDistance {
            val instance =
                ExtraCostPerDistance(
                    distance = distance!!,
                    cost = cost!!,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            distance = distance!!,
            cost = cost!!,
        )
}
