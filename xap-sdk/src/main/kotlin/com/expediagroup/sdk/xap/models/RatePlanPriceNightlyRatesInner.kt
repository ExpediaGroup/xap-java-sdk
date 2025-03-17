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
* 
    * @param stayDate A single night during the requested stay.
    * @param baseRate Container for nightly base rate.
*/
data class RatePlanPriceNightlyRatesInner(
            /* A single night during the requested stay. */
@JsonProperty("StayDate")
val stayDate: kotlin.Any? = null,

            /* Container for nightly base rate. */
@JsonProperty("BaseRate")
val baseRate: kotlin.Any? = null
) {
    


    init {
        














    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var stayDate: kotlin.Any? = null,
                private var baseRate: kotlin.Any? = null
        ) {
                fun stayDate(stayDate: kotlin.Any?) = apply { this.stayDate = stayDate }
                fun baseRate(baseRate: kotlin.Any?) = apply { this.baseRate = baseRate }

    fun build(): RatePlanPriceNightlyRatesInner {
    val instance = RatePlanPriceNightlyRatesInner(
            stayDate = stayDate,
            baseRate = baseRate
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            stayDate = stayDate,
            baseRate = baseRate
    )
}
