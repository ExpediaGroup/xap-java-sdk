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
import com.expediagroup.sdk.xap.models.HotelLinksApiRateCalendar
import com.expediagroup.sdk.xap.models.HotelLinksWebSearchResult
import com.expediagroup.sdk.xap.models.Link
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Container for list of **HATEOAS** links to Expedia website to complete booking.  This links section will only return a deeplink to the Website Search Results page by default.  If you have selected AD deeplinks they will only <u>appear</u> within the `RoomTypes` section of the  response, as the Lodging Details API returns details at the room offer level, and not at the property level.
 * @param webSearchResult
 * @param apiRateCalendar
 */
data class HotelLinks(
    @JsonProperty("WebSearchResult")
    @field:Valid
    val webSearchResult: HotelLinksWebSearchResult? = null,
    @JsonProperty("ApiRateCalendar")
    @field:Valid
    val apiRateCalendar: HotelLinksApiRateCalendar? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var webSearchResult: HotelLinksWebSearchResult? = null,
        private var apiRateCalendar: HotelLinksApiRateCalendar? = null
    ) {
        fun webSearchResult(webSearchResult: HotelLinksWebSearchResult?) = apply { this.webSearchResult = webSearchResult }

        fun apiRateCalendar(apiRateCalendar: HotelLinksApiRateCalendar?) = apply { this.apiRateCalendar = apiRateCalendar }

        fun build(): HotelLinks {
            val instance =
                HotelLinks(
                    webSearchResult = webSearchResult,
                    apiRateCalendar = apiRateCalendar
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: HotelLinks) {
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
            webSearchResult = webSearchResult,
            apiRateCalendar = apiRateCalendar
        )
}
