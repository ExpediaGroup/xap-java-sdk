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
import com.expediagroup.sdk.xap.models.RateCalendar
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
* Container for all hotel rate calendar data.
 * @param ecomHotelId The unique, Expedia-specific hotel property identifier used to designate a single hotel.
 * @param hcomHotelId The unique, Hotels.com-specific hotel property identifier used to designate a single hotel.  This will be returned if searching via `hcomHotelId` in request or the request is coming from Hcom partner.
 * @param rateCalendar Container for all rate calendar data.
*/
data class HotelRateCalendar(
    // The unique, Expedia-specific hotel property identifier used to designate a single hotel.
    @JsonProperty("EcomHotelId")
    @field:Valid
    val ecomHotelId: kotlin.String? = null,
    // The unique, Hotels.com-specific hotel property identifier used to designate a single hotel.  This will be returned if searching via `hcomHotelId` in request or the request is coming from Hcom partner.
    @JsonProperty("HcomHotelId")
    @field:Valid
    val hcomHotelId: kotlin.String? = null,
    // Container for all rate calendar data.
    @JsonProperty("RateCalendar")
    @field:Valid
    val rateCalendar: kotlin.collections.List<RateCalendar>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var ecomHotelId: kotlin.String? = null,
        private var hcomHotelId: kotlin.String? = null,
        private var rateCalendar: kotlin.collections.List<RateCalendar>? = null
    ) {
        fun ecomHotelId(ecomHotelId: kotlin.String?) = apply { this.ecomHotelId = ecomHotelId }

        fun hcomHotelId(hcomHotelId: kotlin.String?) = apply { this.hcomHotelId = hcomHotelId }

        fun rateCalendar(rateCalendar: kotlin.collections.List<RateCalendar>?) = apply { this.rateCalendar = rateCalendar }

        fun build(): HotelRateCalendar {
            val instance =
                HotelRateCalendar(
                    ecomHotelId = ecomHotelId,
                    hcomHotelId = hcomHotelId,
                    rateCalendar = rateCalendar
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: HotelRateCalendar) {
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
            ecomHotelId = ecomHotelId,
            hcomHotelId = hcomHotelId,
            rateCalendar = rateCalendar
        )
}
