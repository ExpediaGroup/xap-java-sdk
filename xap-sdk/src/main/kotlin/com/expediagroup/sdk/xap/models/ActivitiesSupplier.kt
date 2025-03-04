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
import com.expediagroup.sdk.xap.models.ActivitiesPhone
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
* The details information of company providing the activity.
 * @param name The name of the company providing the activity.
 * @param phone
*/
data class ActivitiesSupplier(
    // The name of the company providing the activity.
    @JsonProperty("Name")
    @field:NotNull
    @field:Valid
    val name: kotlin.String,
    @JsonProperty("Phone")
    @field:Valid
    val phone: ActivitiesPhone? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var name: kotlin.String? = null,
        private var phone: ActivitiesPhone? = null
    ) {
        fun name(name: kotlin.String) = apply { this.name = name }

        fun phone(phone: ActivitiesPhone?) = apply { this.phone = phone }

        fun build(): ActivitiesSupplier {
            val instance =
                ActivitiesSupplier(
                    name = name!!,
                    phone = phone
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: ActivitiesSupplier) {
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
            name = name!!,
            phone = phone
        )
}
