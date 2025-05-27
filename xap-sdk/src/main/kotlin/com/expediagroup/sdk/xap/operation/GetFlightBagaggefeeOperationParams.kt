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
 * @property offerToken The offerToken from the Flight Listings API
 * @property partnerTransactionID Partner-generated identifier.
 * @property locale Locale information to provide airline name. If this information is not provided, the locale corresponding for that partners default will be selected.
 * @property currency Specifies currency code for baggage fees to be returned in. Format should be ISO 4217 currency code (3 letter). If this information is not provided, the currency corresponding for that partners default will be selected.
 */
@JsonDeserialize(builder = GetFlightBagaggefeeOperationParams.Builder::class)
@ConsistentCopyVisibility
data class GetFlightBagaggefeeOperationParams private constructor(
    val offerToken: kotlin.String,
    val partnerTransactionID: kotlin.String,
    val locale: kotlin.String? =
        null,
    val currency: kotlin.String? =
        null,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("offerToken") private var offerToken: kotlin.String? = null,
        @JsonProperty("Partner-Transaction-ID") private var partnerTransactionID: kotlin.String? = null,
        @JsonProperty("locale") private var locale: kotlin.String? = null,
        @JsonProperty("currency") private var currency: kotlin.String? = null,

    ) {
        /**
         * @param offerToken The offerToken from the Flight Listings API
         */
        fun offerToken(
            offerToken: kotlin.String,
        ) = apply { this.offerToken = offerToken }

        /**
         * @param partnerTransactionID Partner-generated identifier.
         */
        fun partnerTransactionID(
            partnerTransactionID: kotlin.String,
        ) = apply { this.partnerTransactionID = partnerTransactionID }

        /**
         * @param locale Locale information to provide airline name. If this information is not provided, the locale corresponding for that partners default will be selected.
         */
        fun locale(
            locale: kotlin.String,
        ) = apply { this.locale = locale }

        /**
         * @param currency Specifies currency code for baggage fees to be returned in. Format should be ISO 4217 currency code (3 letter). If this information is not provided, the currency corresponding for that partners default will be selected.
         */
        fun currency(
            currency: kotlin.String,
        ) = apply { this.currency = currency }

        fun build(): GetFlightBagaggefeeOperationParams {
            val offerToken = this.offerToken.getOrThrow {
                IllegalArgumentException("offerToken must not be null")
            }

            val partnerTransactionID = this.partnerTransactionID.getOrThrow {
                IllegalArgumentException("partnerTransactionID must not be null")
            }

            val params = GetFlightBagaggefeeOperationParams(
                offerToken = offerToken,
                partnerTransactionID = partnerTransactionID,
                locale = locale,
                currency = currency,
            )
            return params
        }
    }

    fun toBuilder() = Builder(
        offerToken = offerToken,
        partnerTransactionID = partnerTransactionID,
        locale = locale,
        currency = currency,
    )

    fun getHeaders(): Headers = Headers.builder().apply {
        add("Partner-Transaction-ID", partnerTransactionID)
        add("Accept", "application/vnd.exp-flight.v1+json")
    }.build()

    fun getQueryParams(): List<UrlQueryParam> = buildList {
        locale?.let {
            val key = "locale"
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
        currency?.let {
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
    }

    fun getPathParams(): Map<String, String> = buildMap {
        offerToken.also {
            put("offerToken", offerToken)
        }
    }
}
