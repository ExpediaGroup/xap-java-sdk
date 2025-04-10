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
import com.expediagroup.sdk.xap.models.CarsMoney
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Indicate whether it is deductible
 * @param excessAmount
 * @param liabilityAmount
 * @param deductibleAmount
 */
data class Deductible(
    @JsonProperty("ExcessAmount")
    @field:Valid
    val excessAmount: CarsMoney? = null,
    @JsonProperty("LiabilityAmount")
    @field:Valid
    val liabilityAmount: CarsMoney? = null,
    @JsonProperty("DeductibleAmount")
    @field:Valid
    val deductibleAmount: CarsMoney? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var excessAmount: CarsMoney? = null,
        private var liabilityAmount: CarsMoney? = null,
        private var deductibleAmount: CarsMoney? = null
    ) {
        fun excessAmount(excessAmount: CarsMoney?) = apply { this.excessAmount = excessAmount }

        fun liabilityAmount(liabilityAmount: CarsMoney?) = apply { this.liabilityAmount = liabilityAmount }

        fun deductibleAmount(deductibleAmount: CarsMoney?) = apply { this.deductibleAmount = deductibleAmount }

        fun build(): Deductible {
            val instance =
                Deductible(
                    excessAmount = excessAmount,
                    liabilityAmount = liabilityAmount,
                    deductibleAmount = deductibleAmount
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Deductible) {
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
            excessAmount = excessAmount,
            liabilityAmount = liabilityAmount,
            deductibleAmount = deductibleAmount
        )
}
