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

/**
 * Container for Hotel policy information.
 * @param checkInStartTime Beginning of the standard check-in window on the check in date, stated in the local time of the property.
 * @param checkInEndTime End of the standard check-in window on the check in date, stated in the local time of the property.
 * @param checkOutTime Customers must check out before this time on the check out date, expressed in the local time of the property.
 * @param petPolicies The policy of the property toward having pets stay with guests.
 * @param childrenAndExtraBedsPolicies The policy of the property for having children stay at the property, as well as for including extra beds in the room.
 */
data class FlightsV3HotelPolicies(
    // Beginning of the standard check-in window on the check in date, stated in the local time of the property.
    @JsonProperty("CheckInStartTime")
    @field:Valid
    val checkInStartTime: kotlin.String? = null,
    // End of the standard check-in window on the check in date, stated in the local time of the property.
    @JsonProperty("CheckInEndTime")
    @field:Valid
    val checkInEndTime: kotlin.String? = null,
    // Customers must check out before this time on the check out date, expressed in the local time of the property.
    @JsonProperty("CheckOutTime")
    @field:Valid
    val checkOutTime: kotlin.String? = null,
    // The policy of the property toward having pets stay with guests.
    @JsonProperty("PetPolicies")
    @field:Valid
    val petPolicies: kotlin.collections.List<kotlin.String>? = null,
    // The policy of the property for having children stay at the property, as well as for including extra beds in the room.
    @JsonProperty("ChildrenAndExtraBedsPolicies")
    @field:Valid
    val childrenAndExtraBedsPolicies: kotlin.collections.List<kotlin.String>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var checkInStartTime: kotlin.String? = null,
        private var checkInEndTime: kotlin.String? = null,
        private var checkOutTime: kotlin.String? = null,
        private var petPolicies: kotlin.collections.List<kotlin.String>? = null,
        private var childrenAndExtraBedsPolicies: kotlin.collections.List<kotlin.String>? = null
    ) {
        fun checkInStartTime(checkInStartTime: kotlin.String?) = apply { this.checkInStartTime = checkInStartTime }

        fun checkInEndTime(checkInEndTime: kotlin.String?) = apply { this.checkInEndTime = checkInEndTime }

        fun checkOutTime(checkOutTime: kotlin.String?) = apply { this.checkOutTime = checkOutTime }

        fun petPolicies(petPolicies: kotlin.collections.List<kotlin.String>?) = apply { this.petPolicies = petPolicies }

        fun childrenAndExtraBedsPolicies(childrenAndExtraBedsPolicies: kotlin.collections.List<kotlin.String>?) = apply { this.childrenAndExtraBedsPolicies = childrenAndExtraBedsPolicies }

        fun build(): FlightsV3HotelPolicies {
            val instance =
                FlightsV3HotelPolicies(
                    checkInStartTime = checkInStartTime,
                    checkInEndTime = checkInEndTime,
                    checkOutTime = checkOutTime,
                    petPolicies = petPolicies,
                    childrenAndExtraBedsPolicies = childrenAndExtraBedsPolicies
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightsV3HotelPolicies) {
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
            checkInStartTime = checkInStartTime,
            checkInEndTime = checkInEndTime,
            checkOutTime = checkOutTime,
            petPolicies = petPolicies,
            childrenAndExtraBedsPolicies = childrenAndExtraBedsPolicies
        )
}
