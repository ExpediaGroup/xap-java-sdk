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
 * @property offerToken a valid offerToken from the Flight Search API
 * @property partnerTransactionID Partner-generated identifier.
 * @property accept Accept header for the request.
 * @property acceptEncoding Accept encoding for the request.
 * @property price Included to determine whether there is a price change between the original search and the details response.
 * @property locale Defines request language and country for response.
 * @property agent True means that a telesales agent is involved and the response should include a telesales fee.
 */
@JsonDeserialize(builder = GetFlightDetailsOperationParams.Builder::class)
data class GetFlightDetailsOperationParams(
    @field:NotNull
    @field:Valid
    val offerToken: kotlin.String,
    @field:NotNull
    @field:Valid
    val partnerTransactionID: kotlin.String,
    @field:NotNull
    @field:Valid
    val accept: kotlin.String,
    @field:Valid
    val acceptEncoding: kotlin.String? =
        null,
    @field:Valid
    val price: kotlin.String? =
        null,
    @field:Valid
    val locale: kotlin.String? =
        null,
    @field:Valid
    val agent: kotlin.Boolean? =
        null
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("offerToken") private var offerToken: kotlin.String? = null,
        @JsonProperty("Partner-Transaction-ID") private var partnerTransactionID: kotlin.String? = null,
        @JsonProperty("Accept") private var accept: kotlin.String? = null,
        @JsonProperty("Accept-Encoding") private var acceptEncoding: kotlin.String? = null,
        @JsonProperty("price") private var price: kotlin.String? = null,
        @JsonProperty("locale") private var locale: kotlin.String? = null,
        @JsonProperty("agent") private var agent: kotlin.Boolean? = null
    ) {
        /**
         * @param offerToken a valid offerToken from the Flight Search API
         */
        fun offerToken(offerToken: kotlin.String) = apply { this.offerToken = offerToken }

        /**
         * @param partnerTransactionID Partner-generated identifier.
         */
        fun partnerTransactionID(partnerTransactionID: kotlin.String) = apply { this.partnerTransactionID = partnerTransactionID }

        /**
         * @param accept Accept header for the request.
         */
        fun accept(accept: kotlin.String) = apply { this.accept = accept }

        /**
         * @param acceptEncoding Accept encoding for the request.
         */
        fun acceptEncoding(acceptEncoding: kotlin.String) = apply { this.acceptEncoding = acceptEncoding }

        /**
         * @param price Included to determine whether there is a price change between the original search and the details response.
         */
        fun price(price: kotlin.String) = apply { this.price = price }

        /**
         * @param locale Defines request language and country for response.
         */
        fun locale(locale: kotlin.String) = apply { this.locale = locale }

        /**
         * @param agent True means that a telesales agent is involved and the response should include a telesales fee.
         */
        fun agent(agent: kotlin.Boolean) = apply { this.agent = agent }

        fun build(): GetFlightDetailsOperationParams {
            val params =
                GetFlightDetailsOperationParams(
                    offerToken = offerToken!!,
                    partnerTransactionID = partnerTransactionID!!,
                    accept = accept!!,
                    acceptEncoding = acceptEncoding,
                    price = price,
                    locale = locale,
                    agent = agent
                )

            validate(params)

            return params
        }

        private fun validate(params: GetFlightDetailsOperationParams) {
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
            partnerTransactionID = partnerTransactionID,
            accept = accept,
            acceptEncoding = acceptEncoding,
            price = price,
            locale = locale,
            agent = agent
        )

    override fun getHeaders(): Headers =
        Headers.build {
            partnerTransactionID?.let {
                append("Partner-Transaction-ID", it)
            }
            accept?.let {
                append("Accept", it)
            }
            acceptEncoding?.let {
                append("Accept-Encoding", it)
            }
            append("Accept", "application/vnd.exp-flight.v3+json")
        }

    override fun getQueryParams(): Parameters =
        Parameters.build {
            price?.let {
                append("price", it)
            }
            locale?.let {
                append("locale", it)
            }
            agent?.let {
                append("agent", it.toString())
            }
        }

    override fun getPathParams(): Map<String, String> =
        buildMap {
            offerToken?.also {
                put("offerToken", offerToken)
            }
        }
}
