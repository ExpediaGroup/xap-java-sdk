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
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Validation

/**
* Capacity for car's properties.
 * @param adultCount The typical number of adults that can fit comfortably in the car.
 * @param childCount The typical number of children that can fit comfortably in the car.
 * @param smallLuggageCount The typical number of small pieces of luggage that fit in the cargo space.
 * @param largeLuggageCount The typical number of large pieces of luggage that fit in the cargo space.
*/
data class Capacity(
    // The typical number of adults that can fit comfortably in the car.
    @JsonProperty("AdultCount")
    val adultCount: kotlin.Long,
    // The typical number of children that can fit comfortably in the car.
    @JsonProperty("ChildCount")
    val childCount: kotlin.Long? = null,
    // The typical number of small pieces of luggage that fit in the cargo space.
    @JsonProperty("SmallLuggageCount")
    val smallLuggageCount: kotlin.Long? = null,
    // The typical number of large pieces of luggage that fit in the cargo space.
    @JsonProperty("LargeLuggageCount")
    val largeLuggageCount: kotlin.Long? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var adultCount: kotlin.Long? = null,
        private var childCount: kotlin.Long? = null,
        private var smallLuggageCount: kotlin.Long? = null,
        private var largeLuggageCount: kotlin.Long? = null
    ) {
        fun adultCount(adultCount: kotlin.Long) = apply { this.adultCount = adultCount }

        fun childCount(childCount: kotlin.Long?) = apply { this.childCount = childCount }

        fun smallLuggageCount(smallLuggageCount: kotlin.Long?) = apply { this.smallLuggageCount = smallLuggageCount }

        fun largeLuggageCount(largeLuggageCount: kotlin.Long?) = apply { this.largeLuggageCount = largeLuggageCount }

        fun build(): Capacity {
            val instance =
                Capacity(
                    adultCount = adultCount!!,
                    childCount = childCount,
                    smallLuggageCount = smallLuggageCount,
                    largeLuggageCount = largeLuggageCount
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Capacity) {
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
            adultCount = adultCount!!,
            childCount = childCount,
            smallLuggageCount = smallLuggageCount,
            largeLuggageCount = largeLuggageCount
        )
}
