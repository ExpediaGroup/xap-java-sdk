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

import com.expediagroup.sdk.rest.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.ActivitiesMoney
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
* Container for the reference price used for strike out display.
 * @param totalRate
 * @param totalFees
 * @param totalTaxesAndFees
*/
data class ReferencePrice(
    @JsonProperty("TotalRate")
    @field:NotNull
    @field:Valid
    val totalRate: ActivitiesMoney,
    @JsonProperty("TotalFees")
    @field:Valid
    val totalFees: ActivitiesMoney? = null,
    @JsonProperty("TotalTaxesAndFees")
    @field:Valid
    val totalTaxesAndFees: ActivitiesMoney? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var totalRate: ActivitiesMoney? = null,
        private var totalFees: ActivitiesMoney? = null,
        private var totalTaxesAndFees: ActivitiesMoney? = null
    ) {
        fun totalRate(totalRate: ActivitiesMoney) = apply { this.totalRate = totalRate }

        fun totalFees(totalFees: ActivitiesMoney?) = apply { this.totalFees = totalFees }

        fun totalTaxesAndFees(totalTaxesAndFees: ActivitiesMoney?) = apply { this.totalTaxesAndFees = totalTaxesAndFees }

        fun build(): ReferencePrice {
            val instance =
                ReferencePrice(
                    totalRate = totalRate!!,
                    totalFees = totalFees,
                    totalTaxesAndFees = totalTaxesAndFees
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: ReferencePrice) {
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
            totalRate = totalRate!!,
            totalFees = totalFees,
            totalTaxesAndFees = totalTaxesAndFees
        )
}
