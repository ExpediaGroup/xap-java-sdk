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
 * @property offerToken An offerToken from a Flight API responses.
 * @property source Accepts any single-word value that describes the source from which the API is being called. Example: 'Details', 'Book', 'Itin', etc.
 * @property partnerTransactionID Partner-generated identifier.
 * @property locale locale information (Even though locale may be requested, the majority of the information in the response comes directly from the GDS systems and does NOT have any localization to languages other than English)
 */
@JsonDeserialize(builder = GetFlightFarerulesOperationParams.Builder::class)
data class GetFlightFarerulesOperationParams(
    @field:NotNull
    @field:Valid
    val offerToken: kotlin.String,
    @field:Valid
    val source: kotlin.String? =
        null,
    @field:NotNull
    @field:Valid
    val partnerTransactionID: kotlin.String,
    @field:Valid
    val locale: kotlin.String? =
        null
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("offerToken") private var offerToken: kotlin.String? = null,
        @JsonProperty("Source") private var source: kotlin.String? = null,
        @JsonProperty("Partner-Transaction-ID") private var partnerTransactionID: kotlin.String? = null,
        @JsonProperty("locale") private var locale: kotlin.String? = null
    ) {
        /**
         * @param offerToken An offerToken from a Flight API responses.
         */
        fun offerToken(offerToken: kotlin.String) = apply { this.offerToken = offerToken }

        /**
         * @param source Accepts any single-word value that describes the source from which the API is being called. Example: 'Details', 'Book', 'Itin', etc.
         */
        fun source(source: kotlin.String) = apply { this.source = source }

        /**
         * @param partnerTransactionID Partner-generated identifier.
         */
        fun partnerTransactionID(partnerTransactionID: kotlin.String) = apply { this.partnerTransactionID = partnerTransactionID }

        /**
         * @param locale locale information (Even though locale may be requested, the majority of the information in the response comes directly from the GDS systems and does NOT have any localization to languages other than English)
         */
        fun locale(locale: kotlin.String) = apply { this.locale = locale }

        fun build(): GetFlightFarerulesOperationParams {
            val params =
                GetFlightFarerulesOperationParams(
                    offerToken = offerToken!!,
                    source = source,
                    partnerTransactionID = partnerTransactionID!!,
                    locale = locale
                )

            validate(params)

            return params
        }

        private fun validate(params: GetFlightFarerulesOperationParams) {
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
            offerToken = offerToken,
            source = source,
            partnerTransactionID = partnerTransactionID,
            locale = locale
        )

    override fun getHeaders(): Headers =
        Headers.build {
            source?.let {
                append("Source", it)
            }
            partnerTransactionID?.let {
                append("Partner-Transaction-ID", it)
            }
            append("Accept", "application/vnd.exp-flight.v1+json")
        }

    override fun getQueryParams(): Parameters =
        Parameters.build {
            locale?.let {
                append("locale", it)
            }
        }

    override fun getPathParams(): Map<String, String> =
        buildMap {
            offerToken?.also {
                put("offerToken", offerToken)
            }
        }
}
