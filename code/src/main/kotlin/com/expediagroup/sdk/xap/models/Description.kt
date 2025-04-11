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
 * Container for the descriptions of the property.
 * @param locationTeaser A description of the property's location.
 * @param hotelTeaser A description of the features and amenities of the property itself.
 * @param roomTeaser The common description for all of the rooms in the property.
 */
data class Description(
    // A description of the property's location.
    @JsonProperty("LocationTeaser")
    @field:Valid
    val locationTeaser: kotlin.String? = null,
    // A description of the features and amenities of the property itself.
    @JsonProperty("HotelTeaser")
    @field:Valid
    val hotelTeaser: kotlin.String? = null,
    // The common description for all of the rooms in the property.
    @JsonProperty("RoomTeaser")
    @field:Valid
    val roomTeaser: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var locationTeaser: kotlin.String? = null,
        private var hotelTeaser: kotlin.String? = null,
        private var roomTeaser: kotlin.String? = null
    ) {
        fun locationTeaser(locationTeaser: kotlin.String?) = apply { this.locationTeaser = locationTeaser }

        fun hotelTeaser(hotelTeaser: kotlin.String?) = apply { this.hotelTeaser = hotelTeaser }

        fun roomTeaser(roomTeaser: kotlin.String?) = apply { this.roomTeaser = roomTeaser }

        fun build(): Description {
            val instance =
                Description(
                    locationTeaser = locationTeaser,
                    hotelTeaser = hotelTeaser,
                    roomTeaser = roomTeaser
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Description) {
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
            locationTeaser = locationTeaser,
            hotelTeaser = hotelTeaser,
            roomTeaser = roomTeaser
        )
}
