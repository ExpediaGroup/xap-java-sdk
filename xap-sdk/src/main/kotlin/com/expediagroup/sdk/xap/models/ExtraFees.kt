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

import com.expediagroup.sdk.xap.models.CarsMoney
import com.fasterxml.jackson.annotation.JsonProperty

/**
* List of ExtraFeesDetails
 * @param unit Rate period beyond the base rate. Supported values: ExtraHourly, ExtraDaily
 * @param unitCount Numbers of period
 * @param amount
*/
data class ExtraFees(
    // Rate period beyond the base rate. Supported values: ExtraHourly, ExtraDaily
    @JsonProperty("Unit")
    val unit: kotlin.String,
    // Numbers of period
    @JsonProperty("UnitCount")
    val unitCount: kotlin.Long,
    @JsonProperty("Amount")
    val amount: CarsMoney,
) {
    init {
        require(unit != null) { "unit must not be null" }

        require(unitCount != null) { "unitCount must not be null" }

        require(amount != null) { "amount must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var unit: kotlin.String? = null,
        private var unitCount: kotlin.Long? = null,
        private var amount: CarsMoney? = null,
    ) {
        fun unit(unit: kotlin.String) = apply { this.unit = unit }

        fun unitCount(unitCount: kotlin.Long) = apply { this.unitCount = unitCount }

        fun amount(amount: CarsMoney) = apply { this.amount = amount }

        fun build(): ExtraFees {
            val instance =
                ExtraFees(
                    unit = unit!!,
                    unitCount = unitCount!!,
                    amount = amount!!,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            unit = unit!!,
            unitCount = unitCount!!,
            amount = amount!!,
        )
}
