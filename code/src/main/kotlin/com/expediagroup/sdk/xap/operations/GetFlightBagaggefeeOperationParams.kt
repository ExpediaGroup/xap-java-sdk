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
 * @property offerToken The offerToken from the Flight Listings API
 * @property partnerTransactionID Partner-generated identifier.
 * @property accept Accept header for the request.
 * @property acceptEncoding Accept encoding for the request.
 * @property locale Locale information to provide airline name. If this information is not provided, the locale corresponding for that partners default will be selected.
 * @property currency Specifies currency code for baggage fees to be returned in. Format should be ISO 4217 currency code (3 letter). If this information is not provided, the currency corresponding for that partners default will be selected.
 */
@JsonDeserialize(builder = GetFlightBagaggefeeOperationParams.Builder::class)
data class GetFlightBagaggefeeOperationParams(
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
    val locale: kotlin.String? =
        null,
    @field:Valid
    val currency: kotlin.String? =
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
        @JsonProperty("locale") private var locale: kotlin.String? = null,
        @JsonProperty("currency") private var currency: kotlin.String? = null
    ) {
        /**
         * @param offerToken The offerToken from the Flight Listings API
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
         * @param locale Locale information to provide airline name. If this information is not provided, the locale corresponding for that partners default will be selected.
         */
        fun locale(locale: kotlin.String) = apply { this.locale = locale }

        /**
         * @param currency Specifies currency code for baggage fees to be returned in. Format should be ISO 4217 currency code (3 letter). If this information is not provided, the currency corresponding for that partners default will be selected.
         */
        fun currency(currency: kotlin.String) = apply { this.currency = currency }

        fun build(): GetFlightBagaggefeeOperationParams {
            val params =
                GetFlightBagaggefeeOperationParams(
                    offerToken = offerToken!!,
                    partnerTransactionID = partnerTransactionID!!,
                    accept = accept!!,
                    acceptEncoding = acceptEncoding,
                    locale = locale,
                    currency = currency
                )

            validate(params)

            return params
        }

        private fun validate(params: GetFlightBagaggefeeOperationParams) {
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
            locale = locale,
            currency = currency
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
            append("Accept", "application/vnd.exp-flight.v1+json")
        }

    override fun getQueryParams(): Parameters =
        Parameters.build {
            locale?.let {
                append("locale", it)
            }
            currency?.let {
                append("currency", it)
            }
        }

    override fun getPathParams(): Map<String, String> =
        buildMap {
            offerToken?.also {
                put("offerToken", offerToken)
            }
        }
}
