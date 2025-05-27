/**
 * Copyright (C) 2025 Expedia, Inc.
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
package com.expediagroup.sdk.xap.operation

import com.expediagroup.sdk.core.common.getOrThrow
import com.expediagroup.sdk.core.http.Headers
import com.expediagroup.sdk.rest.model.UrlQueryParam
import com.expediagroup.sdk.rest.util.UrlQueryParamStringifier.explode
import com.expediagroup.sdk.rest.util.swaggerCollectionFormatStringifier
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

/**
 * @property offerToken car offer token
 * @property partnerTransactionId [Not consumed by Expedia] Partner-generated identifier.
 * @property price The total price for the product.
 * @property currency Price currency code
 * @property source source mobile  - The value mobile represents that the client is mobile.
 */
@JsonDeserialize(builder = GetCarDetailsOperationParams.Builder::class)
@ConsistentCopyVisibility
data class GetCarDetailsOperationParams private constructor(
    val offerToken: kotlin.String,
    val partnerTransactionId: kotlin.String,
    val price: kotlin.String,
    val currency: kotlin.String,
    val source: kotlin.String? =
        null,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("offerToken") private var offerToken: kotlin.String? = null,
        @JsonProperty("Partner-Transaction-Id") private var partnerTransactionId: kotlin.String? = null,
        @JsonProperty("price") private var price: kotlin.String? = null,
        @JsonProperty("currency") private var currency: kotlin.String? = null,
        @JsonProperty("source") private var source: kotlin.String? = null,

    ) {
        /**
         * @param offerToken car offer token
         */
        fun offerToken(
            offerToken: kotlin.String,
        ) = apply { this.offerToken = offerToken }

        /**
         * @param partnerTransactionId [Not consumed by Expedia] Partner-generated identifier.
         */
        fun partnerTransactionId(
            partnerTransactionId: kotlin.String,
        ) = apply { this.partnerTransactionId = partnerTransactionId }

        /**
         * @param price The total price for the product.
         */
        fun price(
            price: kotlin.String,
        ) = apply { this.price = price }

        /**
         * @param currency Price currency code
         */
        fun currency(
            currency: kotlin.String,
        ) = apply { this.currency = currency }

        /**
         * @param source source mobile  - The value mobile represents that the client is mobile.
         */
        fun source(
            source: kotlin.String,
        ) = apply { this.source = source }

        fun build(): GetCarDetailsOperationParams {
            val offerToken = this.offerToken.getOrThrow {
                IllegalArgumentException("offerToken must not be null")
            }

            val partnerTransactionId = this.partnerTransactionId.getOrThrow {
                IllegalArgumentException("partnerTransactionId must not be null")
            }

            val price = this.price.getOrThrow {
                IllegalArgumentException("price must not be null")
            }

            val currency = this.currency.getOrThrow {
                IllegalArgumentException("currency must not be null")
            }

            val params = GetCarDetailsOperationParams(
                offerToken = offerToken,
                partnerTransactionId = partnerTransactionId,
                price = price,
                currency = currency,
                source = source,
            )
            return params
        }
    }

    fun toBuilder() = Builder(
        offerToken = offerToken,
        partnerTransactionId = partnerTransactionId,
        price = price,
        currency = currency,
        source = source,
    )

    fun getHeaders(): Headers = Headers.builder().apply {
        add("Partner-Transaction-Id", partnerTransactionId)
        add("Accept", "application/vnd.exp-car.v3+json")
    }.build()

    fun getQueryParams(): List<UrlQueryParam> = buildList {
        price.let {
            val key = "price"
            val value = buildList {
                add(it)
            }

            add(
                UrlQueryParam(
                    key = key,
                    value = value,
                    stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                ),
            )
        }
        currency.let {
            val key = "currency"
            val value = buildList {
                add(it)
            }

            add(
                UrlQueryParam(
                    key = key,
                    value = value,
                    stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                ),
            )
        }
        source?.let {
            val key = "source"
            val value = buildList {
                add(it)
            }

            add(
                UrlQueryParam(
                    key = key,
                    value = value,
                    stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                ),
            )
        }
    }

    fun getPathParams(): Map<String, String> = buildMap {
        offerToken.also {
            put("offerToken", offerToken)
        }
    }
}
