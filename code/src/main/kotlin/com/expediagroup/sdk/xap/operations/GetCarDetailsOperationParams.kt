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
 * @property offerToken car offer token
 * @property partnerTransactionId [Not consumed by Expedia] Partner-generated identifier.
 * @property price The total price for the product.
 * @property currency Price currency code
 * @property source source mobile  - The value mobile represents that the client is mobile.
 */
@JsonDeserialize(builder = GetCarDetailsOperationParams.Builder::class)
data class GetCarDetailsOperationParams(
    @field:NotNull
    @field:Valid
    val offerToken: kotlin.String,
    @field:NotNull
    @field:Valid
    val partnerTransactionId: kotlin.String,
    @field:NotNull
    @field:Valid
    val price: kotlin.String,
    @field:NotNull
    @field:Valid
    val currency: kotlin.String,
    @field:Valid
    val source: kotlin.String? =
        null
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("offerToken") private var offerToken: kotlin.String? = null,
        @JsonProperty("Partner-Transaction-Id") private var partnerTransactionId: kotlin.String? = null,
        @JsonProperty("price") private var price: kotlin.String? = null,
        @JsonProperty("currency") private var currency: kotlin.String? = null,
        @JsonProperty("source") private var source: kotlin.String? = null
    ) {
        /**
         * @param offerToken car offer token
         */
        fun offerToken(offerToken: kotlin.String) = apply { this.offerToken = offerToken }

        /**
         * @param partnerTransactionId [Not consumed by Expedia] Partner-generated identifier.
         */
        fun partnerTransactionId(partnerTransactionId: kotlin.String) = apply { this.partnerTransactionId = partnerTransactionId }

        /**
         * @param price The total price for the product.
         */
        fun price(price: kotlin.String) = apply { this.price = price }

        /**
         * @param currency Price currency code
         */
        fun currency(currency: kotlin.String) = apply { this.currency = currency }

        /**
         * @param source source mobile  - The value mobile represents that the client is mobile.
         */
        fun source(source: kotlin.String) = apply { this.source = source }

        fun build(): GetCarDetailsOperationParams {
            val params =
                GetCarDetailsOperationParams(
                    offerToken = offerToken!!,
                    partnerTransactionId = partnerTransactionId!!,
                    price = price!!,
                    currency = currency!!,
                    source = source
                )

            validate(params)

            return params
        }

        private fun validate(params: GetCarDetailsOperationParams) {
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
            partnerTransactionId = partnerTransactionId,
            price = price,
            currency = currency,
            source = source
        )

    override fun getHeaders(): Headers =
        Headers.build {
            partnerTransactionId?.let {
                append("Partner-Transaction-Id", it)
            }
            append("Accept", "application/vnd.exp-car.v3+json,application/vnd.exp-car.v3+xml")
        }

    override fun getQueryParams(): Parameters =
        Parameters.build {
            price?.let {
                append("price", it)
            }
            currency?.let {
                append("currency", it)
            }
            source?.let {
                append("source", it)
            }
        }

    override fun getPathParams(): Map<String, String> =
        buildMap {
            offerToken?.also {
                put("offerToken", offerToken)
            }
        }
}
