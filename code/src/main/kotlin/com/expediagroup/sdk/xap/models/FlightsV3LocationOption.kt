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
import com.expediagroup.sdk.xap.models.FlightsV3Location
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * List for possible locations from which the customer must choose the best one to be re-submitted in the request.
 * @param requestedLocation Location used in partner request.
 * @param locations Container for list of possible locations that could be used to disambiguate the query.
 */
data class FlightsV3LocationOption(
    // Location used in partner request.
    @JsonProperty("RequestedLocation")
    @field:NotNull
    @field:Valid
    val requestedLocation: kotlin.String,
    // Container for list of possible locations that could be used to disambiguate the query.
    @JsonProperty("Locations")
    @field:NotNull
    @field:Valid
    val locations: kotlin.collections
        .List<
            FlightsV3Location
        >
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var requestedLocation: kotlin.String? = null,
        private var locations: kotlin.collections.List<FlightsV3Location>? = null
    ) {
        fun requestedLocation(requestedLocation: kotlin.String) = apply { this.requestedLocation = requestedLocation }

        fun locations(locations: kotlin.collections.List<FlightsV3Location>) = apply { this.locations = locations }

        fun build(): FlightsV3LocationOption {
            val instance =
                FlightsV3LocationOption(
                    requestedLocation = requestedLocation!!,
                    locations = locations!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightsV3LocationOption) {
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
            requestedLocation = requestedLocation!!,
            locations = locations!!
        )
}
