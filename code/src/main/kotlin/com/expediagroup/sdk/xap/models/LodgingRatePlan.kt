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
import com.expediagroup.sdk.xap.models.LodgingCancellationPolicy
import com.expediagroup.sdk.xap.models.LodgingPromotion
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Container for rate plan information.
 * @param cancellationPolicy
 * @param promotions All promotion information of the ratePlan.
 */
data class LodgingRatePlan(
    @JsonProperty("CancellationPolicy")
    @field:Valid
    val cancellationPolicy: LodgingCancellationPolicy? = null,
    // All promotion information of the ratePlan.
    @JsonProperty("Promotions")
    @field:Valid
    val promotions: kotlin.collections.List<LodgingPromotion>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var cancellationPolicy: LodgingCancellationPolicy? = null,
        private var promotions: kotlin.collections.List<LodgingPromotion>? = null
    ) {
        fun cancellationPolicy(cancellationPolicy: LodgingCancellationPolicy?) = apply { this.cancellationPolicy = cancellationPolicy }

        fun promotions(promotions: kotlin.collections.List<LodgingPromotion>?) = apply { this.promotions = promotions }

        fun build(): LodgingRatePlan {
            val instance =
                LodgingRatePlan(
                    cancellationPolicy = cancellationPolicy,
                    promotions = promotions
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: LodgingRatePlan) {
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
            cancellationPolicy = cancellationPolicy,
            promotions = promotions
        )
}
