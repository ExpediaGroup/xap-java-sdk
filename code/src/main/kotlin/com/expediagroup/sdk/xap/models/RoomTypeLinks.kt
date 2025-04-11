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
import com.expediagroup.sdk.xap.models.HotelLinksWebSearchResult
import com.expediagroup.sdk.xap.models.Link
import com.expediagroup.sdk.xap.models.RoomTypeLinksApiDetails
import com.expediagroup.sdk.xap.models.RoomTypeLinksWebDetails
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Container for list of HATEOAS links to either Expedia website or additional Expedia APIs to complete booking of the selected offer.  Which links are returned in this section are defined by the `links` parameter in the Search API query.  Available links are: - WebSearchResult (link to web search result page) - WebDetails (link to web infosite) - ApiDetails (link for details API)
 * @param webSearchResult
 * @param webDetails
 * @param apiDetails
 */
data class RoomTypeLinks(
    @JsonProperty("WebSearchResult")
    @field:Valid
    val webSearchResult: HotelLinksWebSearchResult? = null,
    @JsonProperty("WebDetails")
    @field:Valid
    val webDetails: RoomTypeLinksWebDetails? = null,
    @JsonProperty("ApiDetails")
    @field:Valid
    val apiDetails: RoomTypeLinksApiDetails? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var webSearchResult: HotelLinksWebSearchResult? = null,
        private var webDetails: RoomTypeLinksWebDetails? = null,
        private var apiDetails: RoomTypeLinksApiDetails? = null
    ) {
        fun webSearchResult(webSearchResult: HotelLinksWebSearchResult?) = apply { this.webSearchResult = webSearchResult }

        fun webDetails(webDetails: RoomTypeLinksWebDetails?) = apply { this.webDetails = webDetails }

        fun apiDetails(apiDetails: RoomTypeLinksApiDetails?) = apply { this.apiDetails = apiDetails }

        fun build(): RoomTypeLinks {
            val instance =
                RoomTypeLinks(
                    webSearchResult = webSearchResult,
                    webDetails = webDetails,
                    apiDetails = apiDetails
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: RoomTypeLinks) {
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
            webDetails = webDetails,
            apiDetails = apiDetails
        )
}
