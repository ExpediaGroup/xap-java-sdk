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
import com.expediagroup.sdk.xap.models.ActivitiesMoney
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container of savings information.
 * @param percentage The percentage of the price that has been discounted off the regular price for the current activity.
 * @param amount
 */
data class Savings(
    // The percentage of the price that has been discounted off the regular price for the current activity.
    @JsonProperty("Percentage")
    val percentage: kotlin.Int,
    @JsonProperty("Amount")
    @field:NotNull
    @field:Valid
    val amount: ActivitiesMoney
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var percentage: kotlin.Int? = null,
        private var amount: ActivitiesMoney? = null
    ) {
        fun percentage(percentage: kotlin.Int) = apply { this.percentage = percentage }

        fun amount(amount: ActivitiesMoney) = apply { this.amount = amount }

        fun build(): Savings {
            val instance =
                Savings(
                    percentage = percentage!!,
                    amount = amount!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Savings) {
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
            percentage = percentage!!,
            amount = amount!!
        )
}
