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
import com.expediagroup.sdk.xap.models.ExtraCostPerDistance
import com.fasterxml.jackson.annotation.JsonProperty

/**
* A list of charges to be levied based on the mileage driven.
 * @param freeDistance
 * @param freeDistanceRatePeriod Rate period for free distance.
 * @param extraCostPerDistance
*/
data class Mileage(
    @JsonProperty("FreeDistance")
    val freeDistance: CarsDistance,
    // Rate period for free distance.
    @JsonProperty("FreeDistanceRatePeriod")
    val freeDistanceRatePeriod: kotlin.String,
    @JsonProperty("ExtraCostPerDistance")
    val extraCostPerDistance: ExtraCostPerDistance? = null,
) {
    init {
        require(freeDistance != null) { "freeDistance must not be null" }

        require(freeDistanceRatePeriod != null) { "freeDistanceRatePeriod must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var freeDistance: CarsDistance? = null,
        private var freeDistanceRatePeriod: kotlin.String? = null,
        private var extraCostPerDistance: ExtraCostPerDistance? = null,
    ) {
        fun freeDistance(freeDistance: CarsDistance) = apply { this.freeDistance = freeDistance }

        fun freeDistanceRatePeriod(freeDistanceRatePeriod: kotlin.String) = apply { this.freeDistanceRatePeriod = freeDistanceRatePeriod }

        fun extraCostPerDistance(extraCostPerDistance: ExtraCostPerDistance?) = apply { this.extraCostPerDistance = extraCostPerDistance }

        fun build(): Mileage {
            val instance =
                Mileage(
                    freeDistance = freeDistance!!,
                    freeDistanceRatePeriod = freeDistanceRatePeriod!!,
                    extraCostPerDistance = extraCostPerDistance,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            freeDistance = freeDistance!!,
            freeDistanceRatePeriod = freeDistanceRatePeriod!!,
            extraCostPerDistance = extraCostPerDistance,
        )
}
