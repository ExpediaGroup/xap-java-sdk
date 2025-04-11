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
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * List of lounges
 * @param name Name of the lounge corresponding to a lounge key
 * @param isConferenceRoomAvailable Indicates whether the lounge has a conference room.
 * @param phoneNumber Phone number of the lounge (if available)
 * @param description Free text field with any extra information about the lounge
 */
data class Lounge(
    // Name of the lounge corresponding to a lounge key
    @JsonProperty("Name")
    @field:NotNull
    @field:Valid
    val name: kotlin.String,
    // Indicates whether the lounge has a conference room.
    @JsonProperty("IsConferenceRoomAvailable")
    @field:Valid
    val isConferenceRoomAvailable: kotlin.Boolean? = null,
    // Phone number of the lounge (if available)
    @JsonProperty("PhoneNumber")
    @field:Valid
    val phoneNumber: kotlin.String? = null,
    // Free text field with any extra information about the lounge
    @JsonProperty("Description")
    @field:Valid
    val description: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var name: kotlin.String? = null,
        private var isConferenceRoomAvailable: kotlin.Boolean? = null,
        private var phoneNumber: kotlin.String? = null,
        private var description: kotlin.String? = null
    ) {
        fun name(name: kotlin.String) = apply { this.name = name }

        fun isConferenceRoomAvailable(isConferenceRoomAvailable: kotlin.Boolean?) = apply { this.isConferenceRoomAvailable = isConferenceRoomAvailable }

        fun phoneNumber(phoneNumber: kotlin.String?) = apply { this.phoneNumber = phoneNumber }

        fun description(description: kotlin.String?) = apply { this.description = description }

        fun build(): Lounge {
            val instance =
                Lounge(
                    name = name!!,
                    isConferenceRoomAvailable = isConferenceRoomAvailable,
                    phoneNumber = phoneNumber,
                    description = description
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Lounge) {
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
            isConferenceRoomAvailable = isConferenceRoomAvailable,
            phoneNumber = phoneNumber,
            description = description
        )
}
