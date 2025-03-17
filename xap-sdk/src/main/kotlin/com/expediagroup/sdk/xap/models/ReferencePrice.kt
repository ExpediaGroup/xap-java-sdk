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

import com.expediagroup.sdk.xap.models.ActivitiesMoney
import com.fasterxml.jackson.annotation.JsonProperty

/**
* Container for the reference price used for strike out display.
 * @param totalRate
 * @param totalFees
 * @param totalTaxesAndFees
*/
data class ReferencePrice(
    @JsonProperty("TotalRate")
    val totalRate: ActivitiesMoney,
    @JsonProperty("TotalFees")
    val totalFees: ActivitiesMoney? = null,
    @JsonProperty("TotalTaxesAndFees")
    val totalTaxesAndFees: ActivitiesMoney? = null,
) {
    init {
        require(totalRate != null) { "totalRate must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var totalRate: ActivitiesMoney? = null,
        private var totalFees: ActivitiesMoney? = null,
        private var totalTaxesAndFees: ActivitiesMoney? = null,
    ) {
        fun totalRate(totalRate: ActivitiesMoney) = apply { this.totalRate = totalRate }

        fun totalFees(totalFees: ActivitiesMoney?) = apply { this.totalFees = totalFees }

        fun totalTaxesAndFees(totalTaxesAndFees: ActivitiesMoney?) = apply { this.totalTaxesAndFees = totalTaxesAndFees }

        fun build(): ReferencePrice {
            val instance =
                ReferencePrice(
                    totalRate = totalRate!!,
                    totalFees = totalFees,
                    totalTaxesAndFees = totalTaxesAndFees,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            totalRate = totalRate!!,
            totalFees = totalFees,
            totalTaxesAndFees = totalTaxesAndFees,
        )
}
