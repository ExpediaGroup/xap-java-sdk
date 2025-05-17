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
package com.expediagroup.sdk.xap.models

import com.expediagroup.sdk.xap.models.Fee
import com.expediagroup.sdk.xap.models.FlightsV3Money
import com.expediagroup.sdk.xap.models.PackageSavings
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for package price information
 * @param packageBasePrice
 * @param packageTotalPrice
 * @param packageTaxesAndFees
 * @param standAloneTotalPrice
 * @param fees Special fees attached to the package (for example, Air Service Fees). This amount is not included in PackageTaxesAndFees.
 * @param savings
 * @param averageCostPerPerson
 * @param totalHotelMandatoryFees
 */
data class PackagePrice(
    @JsonProperty("PackageBasePrice")
    val packageBasePrice: FlightsV3Money,
    @JsonProperty("PackageTotalPrice")
    val packageTotalPrice: FlightsV3Money,
    @JsonProperty("PackageTaxesAndFees")
    val packageTaxesAndFees: FlightsV3Money,
    @JsonProperty("StandAloneTotalPrice")
    val standAloneTotalPrice: FlightsV3Money,
    // Special fees attached to the package (for example, Air Service Fees). This amount is not included in PackageTaxesAndFees.
    @JsonProperty("Fees")
    val fees: kotlin.collections
        .List<
            Fee,
        >,
    @JsonProperty("Savings")
    val savings: PackageSavings,
    @JsonProperty("AverageCostPerPerson")
    val averageCostPerPerson: FlightsV3Money,
    @JsonProperty("TotalHotelMandatoryFees")
    val totalHotelMandatoryFees: Fee? = null,
) {
    init {
        require(packageBasePrice != null) { "packageBasePrice must not be null" }

        require(packageTotalPrice != null) { "packageTotalPrice must not be null" }

        require(packageTaxesAndFees != null) { "packageTaxesAndFees must not be null" }

        require(standAloneTotalPrice != null) { "standAloneTotalPrice must not be null" }

        require(fees != null) { "fees must not be null" }

        require(savings != null) { "savings must not be null" }

        require(averageCostPerPerson != null) { "averageCostPerPerson must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var packageBasePrice: FlightsV3Money? = null,
        private var packageTotalPrice: FlightsV3Money? = null,
        private var packageTaxesAndFees: FlightsV3Money? = null,
        private var standAloneTotalPrice: FlightsV3Money? = null,
        private var fees: kotlin.collections.List<Fee>? = null,
        private var savings: PackageSavings? = null,
        private var averageCostPerPerson: FlightsV3Money? = null,
        private var totalHotelMandatoryFees: Fee? = null,
    ) {
        fun packageBasePrice(packageBasePrice: FlightsV3Money) = apply { this.packageBasePrice = packageBasePrice }

        fun packageTotalPrice(packageTotalPrice: FlightsV3Money) = apply { this.packageTotalPrice = packageTotalPrice }

        fun packageTaxesAndFees(packageTaxesAndFees: FlightsV3Money) = apply { this.packageTaxesAndFees = packageTaxesAndFees }

        fun standAloneTotalPrice(standAloneTotalPrice: FlightsV3Money) = apply { this.standAloneTotalPrice = standAloneTotalPrice }

        fun fees(fees: kotlin.collections.List<Fee>) = apply { this.fees = fees }

        fun savings(savings: PackageSavings) = apply { this.savings = savings }

        fun averageCostPerPerson(averageCostPerPerson: FlightsV3Money) = apply { this.averageCostPerPerson = averageCostPerPerson }

        fun totalHotelMandatoryFees(totalHotelMandatoryFees: Fee?) = apply { this.totalHotelMandatoryFees = totalHotelMandatoryFees }

        fun build(): PackagePrice {
            val instance =
                PackagePrice(
                    packageBasePrice = packageBasePrice!!,
                    packageTotalPrice = packageTotalPrice!!,
                    packageTaxesAndFees = packageTaxesAndFees!!,
                    standAloneTotalPrice = standAloneTotalPrice!!,
                    fees = fees!!,
                    savings = savings!!,
                    averageCostPerPerson = averageCostPerPerson!!,
                    totalHotelMandatoryFees = totalHotelMandatoryFees,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            packageBasePrice = packageBasePrice!!,
            packageTotalPrice = packageTotalPrice!!,
            packageTaxesAndFees = packageTaxesAndFees!!,
            standAloneTotalPrice = standAloneTotalPrice!!,
            fees = fees!!,
            savings = savings!!,
            averageCostPerPerson = averageCostPerPerson!!,
            totalHotelMandatoryFees = totalHotelMandatoryFees,
        )
}
