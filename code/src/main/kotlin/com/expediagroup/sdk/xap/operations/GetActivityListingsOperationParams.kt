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
package com.expediagroup.sdk.xap.operations

import com.expediagroup.sdk.core.model.OperationParams
import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.infrastructure.*
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.ktor.http.Headers
import io.ktor.http.Parameters
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * @property partnerTransactionID Partner-generated identifier.
 * @property location Can be a city name, street address, three-letter IATA Airport Code or a landmark name. (If the value submitted does not clearly identify a single location the API may returne a disambiguation response that lists possible options)
 * @property geoLocation The latitude and longitude values identifying the center point of a search radius (circle). North latitude will be represented by a positive value. South latitude by a negative value. East longitude will be represented by a positive value. West longitude by a negative value. The latitude and longitude values are joined together with a comma (,) character.
 * @property startDate Start date for the activity window in YYY-MM-DD format. If an endDate value is supplied there must also be a startDate.
 * @property endDate End date for the activity window in YYY-MM-DD format.
 * @property locale locale is composed of language identifier and region identifier, connected by \"_\" that specifies the language in which the response will be returned. example: \"fr_FR\" refers to French as spoken in France, while \"fr_CA\" refers to French as spoken in Canada. For a full list of supported locales please refer to the link at the bottom of the page.
 * @property links Comma-separated list to specify the types of HATEAOS links returned in the API Response. WD (deep link URL to web infosite) AD (details API query)
 */
@JsonDeserialize(builder = GetActivityListingsOperationParams.Builder::class)
data class GetActivityListingsOperationParams(
    @field:NotNull
    @field:Valid
    val partnerTransactionID: kotlin.String,
    @field:Valid
    val location: kotlin.String? =
        null,
    @field:Valid
    val geoLocation: kotlin.String? =
        null,
    @field:Valid
    val startDate: kotlin.String? =
        "currentDate",
    @field:Valid
    val endDate: kotlin.String? =
        "startDate+14",
    @field:Valid
    val locale: kotlin.String? =
        null,
    @field:Valid
    val links: kotlin.String? =
        null
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("Partner-Transaction-ID") private var partnerTransactionID: kotlin.String? = null,
        @JsonProperty("location") private var location: kotlin.String? = null,
        @JsonProperty("geoLocation") private var geoLocation: kotlin.String? = null,
        @JsonProperty("startDate") private var startDate: kotlin.String? = null,
        @JsonProperty("endDate") private var endDate: kotlin.String? = null,
        @JsonProperty("locale") private var locale: kotlin.String? = null,
        @JsonProperty("links") private var links: kotlin.String? = null
    ) {
        /**
         * @param partnerTransactionID Partner-generated identifier.
         */
        fun partnerTransactionID(partnerTransactionID: kotlin.String) = apply { this.partnerTransactionID = partnerTransactionID }

        /**
         * @param location Can be a city name, street address, three-letter IATA Airport Code or a landmark name. (If the value submitted does not clearly identify a single location the API may returne a disambiguation response that lists possible options)
         */
        fun location(location: kotlin.String) = apply { this.location = location }

        /**
         * @param geoLocation The latitude and longitude values identifying the center point of a search radius (circle). North latitude will be represented by a positive value. South latitude by a negative value. East longitude will be represented by a positive value. West longitude by a negative value. The latitude and longitude values are joined together with a comma (,) character.
         */
        fun geoLocation(geoLocation: kotlin.String) = apply { this.geoLocation = geoLocation }

        /**
         * @param startDate Start date for the activity window in YYY-MM-DD format. If an endDate value is supplied there must also be a startDate.
         */
        fun startDate(startDate: kotlin.String) = apply { this.startDate = startDate }

        /**
         * @param endDate End date for the activity window in YYY-MM-DD format.
         */
        fun endDate(endDate: kotlin.String) = apply { this.endDate = endDate }

        /**
         * @param locale locale is composed of language identifier and region identifier, connected by \"_\" that specifies the language in which the response will be returned. example: \"fr_FR\" refers to French as spoken in France, while \"fr_CA\" refers to French as spoken in Canada. For a full list of supported locales please refer to the link at the bottom of the page.
         */
        fun locale(locale: kotlin.String) = apply { this.locale = locale }

        /**
         * @param links Comma-separated list to specify the types of HATEAOS links returned in the API Response. WD (deep link URL to web infosite) AD (details API query)
         */
        fun links(links: kotlin.String) = apply { this.links = links }

        fun build(): GetActivityListingsOperationParams {
            val params =
                GetActivityListingsOperationParams(
                    partnerTransactionID = partnerTransactionID!!,
                    location = location,
                    geoLocation = geoLocation,
                    startDate = startDate,
                    endDate = endDate,
                    locale = locale,
                    links = links
                )

            validate(params)

            return params
        }

        private fun validate(params: GetActivityListingsOperationParams) {
            val validator =
                Validation
                    .byDefaultProvider()
                    .configure()
                    .messageInterpolator(ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .validator

            val violations = validator.validate(params)

            if (violations.isNotEmpty()) {
                throw PropertyConstraintViolationException(
                    constraintViolations = violations.map { "${it.propertyPath}: ${it.message}" }
                )
            }
        }
    }

    fun toBuilder() =
        Builder(
            partnerTransactionID = partnerTransactionID,
            location = location,
            geoLocation = geoLocation,
            startDate = startDate,
            endDate = endDate,
            locale = locale,
            links = links
        )

    override fun getHeaders(): Headers =
        Headers.build {
            partnerTransactionID?.let {
                append("Partner-Transaction-ID", it)
            }
            append("Accept", "application/vnd.exp-activity.v3+json")
        }

    override fun getQueryParams(): Parameters =
        Parameters.build {
            location?.let {
                append("location", it)
            }
            geoLocation?.let {
                append("geoLocation", it)
            }
            startDate?.let {
                append("startDate", it)
            }
            endDate?.let {
                append("endDate", it)
            }
            locale?.let {
                append("locale", it)
            }
            links?.let {
                append("links", it)
            }
        }

    override fun getPathParams(): Map<String, String> =
        buildMap {
        }
}
