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
 * @property offerToken a valid offerToken from the Flight Search API
 * @property partnerTransactionID Partner-generated identifier.
 * @property price Included to determine whether there is a price change between the original search and the details response.
 * @property locale Defines request language and country for response.
 * @property agent True means that a telesales agent is involved and the response should include a telesales fee.
 */
@JsonDeserialize(builder = GetFlightDetailsOperationParams.Builder::class)
@ConsistentCopyVisibility
data class GetFlightDetailsOperationParams private constructor(
    val offerToken: kotlin.String,
    val partnerTransactionID: kotlin.String,
    val price: kotlin.String? =
        null,
    val locale: kotlin.String? =
        null,
    val agent: kotlin.Boolean? =
        null,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("offerToken") private var offerToken: kotlin.String? = null,
        @JsonProperty("Partner-Transaction-ID") private var partnerTransactionID: kotlin.String? = null,
        @JsonProperty("price") private var price: kotlin.String? = null,
        @JsonProperty("locale") private var locale: kotlin.String? = null,
        @JsonProperty("agent") private var agent: kotlin.Boolean? = null,

    ) {
        /**
         * @param offerToken a valid offerToken from the Flight Search API
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
         * @param price Included to determine whether there is a price change between the original search and the details response.
         */
        fun price(
            price: kotlin.String,
        ) = apply { this.price = price }

        /**
         * @param locale Defines request language and country for response.
         */
        fun locale(
            locale: kotlin.String,
        ) = apply { this.locale = locale }

        /**
         * @param agent True means that a telesales agent is involved and the response should include a telesales fee.
         */
        fun agent(
            agent: kotlin.Boolean,
        ) = apply { this.agent = agent }

        fun build(): GetFlightDetailsOperationParams {
            val offerToken = this.offerToken.getOrThrow {
                IllegalArgumentException("offerToken must not be null")
            }

            val partnerTransactionID = this.partnerTransactionID.getOrThrow {
                IllegalArgumentException("partnerTransactionID must not be null")
            }

            val params = GetFlightDetailsOperationParams(
                offerToken = offerToken,
                partnerTransactionID = partnerTransactionID,
                price = price,
                locale = locale,
                agent = agent,
            )
            return params
        }
    }

    fun toBuilder() = Builder(
        offerToken = offerToken,
        partnerTransactionID = partnerTransactionID,
        price = price,
        locale = locale,
        agent = agent,
    )

    fun getHeaders(): Headers = Headers.builder().apply {
        add("Partner-Transaction-ID", partnerTransactionID)
        add("Accept", "application/vnd.exp-flight.v3+json")
    }.build()

    fun getQueryParams(): List<UrlQueryParam> = buildList {
        price?.let {
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
        agent?.let {
            val key = "agent"
            val value = buildList {
                add(it.toString())
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
