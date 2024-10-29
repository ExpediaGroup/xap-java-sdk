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
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.ktor.http.Headers
import io.ktor.http.Parameters

/**
 * @property offerToken car offer token
 * @property partnerTransactionId [Not consumed by Expedia] Partner-generated identifier.
 * @property price The total price for the product.
 * @property currency Price currency code
 * @property source source mobile  - The value mobile represents that the client is mobile.
 */
@JsonDeserialize(builder = GetCarDetailsOperationParams.Builder::class)
data class GetCarDetailsOperationParams(
    val offerToken: kotlin.String,
    val partnerTransactionId: kotlin.String,
    val price: kotlin.String,
    val currency: kotlin.String,
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
            validateNullity()

            return GetCarDetailsOperationParams(
                offerToken = offerToken!!,
                partnerTransactionId = partnerTransactionId!!,
                price = price!!,
                currency = currency!!,
                source = source
            )
        }

        private fun validateNullity() {
            if (offerToken == null) {
                throw NullPointerException("Required parameter offerToken is missing")
            }
            if (partnerTransactionId == null) {
                throw NullPointerException("Required parameter partnerTransactionId is missing")
            }
            if (price == null) {
                throw NullPointerException("Required parameter price is missing")
            }
            if (currency == null) {
                throw NullPointerException("Required parameter currency is missing")
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

    override fun getHeaders(): Headers {
        return Headers.build {
            partnerTransactionId?.let {
                append("Partner-Transaction-Id", it)
            }
            append("Accept", "application/vnd.exp-car.v3+json,application/vnd.exp-car.v3+xml")
        }
    }

    override fun getQueryParams(): Parameters {
        return Parameters.build {
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
    }

    override fun getPathParams(): Map<String, String> {
        return buildMap {
            offerToken?.also {
                put("offerToken", offerToken)
            }
        }
    }
}
