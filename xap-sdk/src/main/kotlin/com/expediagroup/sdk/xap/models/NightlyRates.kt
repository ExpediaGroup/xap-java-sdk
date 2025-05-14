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

import com.expediagroup.sdk.xap.models.Money
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param stayDate
 * @param baseRate
 */
data class NightlyRates(
    @JsonProperty("StayDate")
    val stayDate: java.time.LocalDate? = null,
    @JsonProperty("BaseRate")
    val baseRate: Money? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var stayDate: java.time.LocalDate? = null,
        private var baseRate: Money? = null,
    ) {
        fun stayDate(stayDate: java.time.LocalDate?) = apply { this.stayDate = stayDate }

        fun baseRate(baseRate: Money?) = apply { this.baseRate = baseRate }

        fun build(): NightlyRates {
            val instance =
                NightlyRates(
                    stayDate = stayDate,
                    baseRate = baseRate,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            stayDate = stayDate,
            baseRate = baseRate,
        )
}
