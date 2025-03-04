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
import com.expediagroup.sdk.xap.models.CarsLink
import com.expediagroup.sdk.xap.models.CarsMoney
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
* Container for warning codes
 * @param code Standardized warning code.
 * @param description Standardized warning description message.
 * @param originalPrice
 * @param newPrice
 * @param changedAmount
 * @param changedPercentage The changed percentage. In the sample 2.97 means the changed percentage is 2.97%.
 * @param links
*/
data class CarsWarning(
    // Standardized warning code.
    @JsonProperty("Code")
    @field:NotNull
    @field:Valid
    val code: kotlin.String,
    // Standardized warning description message.
    @JsonProperty("Description")
    @field:NotNull
    @field:Valid
    val description: kotlin.String,
    @JsonProperty("OriginalPrice")
    @field:Valid
    val originalPrice: CarsMoney? = null,
    @JsonProperty("NewPrice")
    @field:Valid
    val newPrice: CarsMoney? = null,
    @JsonProperty("ChangedAmount")
    @field:Valid
    val changedAmount: CarsMoney? = null,
    // The changed percentage. In the sample 2.97 means the changed percentage is 2.97%.
    @JsonProperty("ChangedPercentage")
    @field:Valid
    val changedPercentage: kotlin.String? = null,
    @JsonProperty("Links")
    @field:Valid
    val links: kotlin.collections.Map<kotlin.String, CarsLink>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: kotlin.String? = null,
        private var description: kotlin.String? = null,
        private var originalPrice: CarsMoney? = null,
        private var newPrice: CarsMoney? = null,
        private var changedAmount: CarsMoney? = null,
        private var changedPercentage: kotlin.String? = null,
        private var links: kotlin.collections.Map<kotlin.String, CarsLink>? = null
    ) {
        fun code(code: kotlin.String) = apply { this.code = code }

        fun description(description: kotlin.String) = apply { this.description = description }

        fun originalPrice(originalPrice: CarsMoney?) = apply { this.originalPrice = originalPrice }

        fun newPrice(newPrice: CarsMoney?) = apply { this.newPrice = newPrice }

        fun changedAmount(changedAmount: CarsMoney?) = apply { this.changedAmount = changedAmount }

        fun changedPercentage(changedPercentage: kotlin.String?) = apply { this.changedPercentage = changedPercentage }

        fun links(links: kotlin.collections.Map<kotlin.String, CarsLink>?) = apply { this.links = links }

        fun build(): CarsWarning {
            val instance =
                CarsWarning(
                    code = code!!,
                    description = description!!,
                    originalPrice = originalPrice,
                    newPrice = newPrice,
                    changedAmount = changedAmount,
                    changedPercentage = changedPercentage,
                    links = links
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: CarsWarning) {
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
            code = code!!,
            description = description!!,
            originalPrice = originalPrice,
            newPrice = newPrice,
            changedAmount = changedAmount,
            changedPercentage = changedPercentage,
            links = links
        )
}
