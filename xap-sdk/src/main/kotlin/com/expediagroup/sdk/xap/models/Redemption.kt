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
import com.expediagroup.sdk.xap.models.ActivitiesLocation
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
* Container of redemption information.
 * @param type The type of redemption process associated to the activity.
 * @param redemptionLocations List of redemption locations where the activity will take place, please refer to Location Section below.
*/
data class Redemption(
    // The type of redemption process associated to the activity.
    @JsonProperty("Type")
    @field:Valid
    val type: kotlin.String? = null,
    // List of redemption locations where the activity will take place, please refer to Location Section below.
    @JsonProperty("RedemptionLocations")
    @field:Valid
    val redemptionLocations: kotlin.collections.List<ActivitiesLocation>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: kotlin.String? = null,
        private var redemptionLocations: kotlin.collections.List<ActivitiesLocation>? = null
    ) {
        fun type(type: kotlin.String?) = apply { this.type = type }

        fun redemptionLocations(redemptionLocations: kotlin.collections.List<ActivitiesLocation>?) = apply { this.redemptionLocations = redemptionLocations }

        fun build(): Redemption {
            val instance =
                Redemption(
                    type = type,
                    redemptionLocations = redemptionLocations
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Redemption) {
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
            type = type,
            redemptionLocations = redemptionLocations
        )
}
