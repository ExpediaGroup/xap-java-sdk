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

import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.Fee
import com.expediagroup.sdk.xap.models.FlightsV3Money
import com.expediagroup.sdk.xap.models.PackageSavings
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

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
    @field:NotNull
    @field:Valid
    val packageBasePrice: FlightsV3Money,
    @JsonProperty("PackageTotalPrice")
    @field:NotNull
    @field:Valid
    val packageTotalPrice: FlightsV3Money,
    @JsonProperty("PackageTaxesAndFees")
    @field:NotNull
    @field:Valid
    val packageTaxesAndFees: FlightsV3Money,
    @JsonProperty("StandAloneTotalPrice")
    @field:NotNull
    @field:Valid
    val standAloneTotalPrice: FlightsV3Money,
    // Special fees attached to the package (for example, Air Service Fees). This amount is not included in PackageTaxesAndFees.
    @JsonProperty("Fees")
    @field:NotNull
    @field:Valid
    val fees: kotlin.collections
        .List<
            Fee
        >,
    @JsonProperty("Savings")
    @field:NotNull
    @field:Valid
    val savings: PackageSavings,
    @JsonProperty("AverageCostPerPerson")
    @field:NotNull
    @field:Valid
    val averageCostPerPerson: FlightsV3Money,
    @JsonProperty("TotalHotelMandatoryFees")
    @field:Valid
    val totalHotelMandatoryFees: Fee? = null
) {
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
        private var totalHotelMandatoryFees: Fee? = null
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
                    totalHotelMandatoryFees = totalHotelMandatoryFees
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: PackagePrice) {
            val validator =
                Validation
                    .byDefaultProvider()
                    .configure()
                    .messageInterpolator(ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .validator

            val violations = validator.validate(instance)

            if (violations.isNotEmpty()) {
                throw PropertyConstraintViolationException(
                    constraintViolations = violations.map { "${it.propertyPath}: ${it.message}" }
                )
            }
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
            totalHotelMandatoryFees = totalHotelMandatoryFees
        )
}
