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
 * @property offerToken An offerToken from a Flight API responses.
 * @property source Accepts any single-word value that describes the source from which the API is being called. Example: 'Details', 'Book', 'Itin', etc.
 * @property partnerTransactionID Partner-generated identifier.
 * @property locale locale information (Even though locale may be requested, the majority of the information in the response comes directly from the GDS systems and does NOT have any localization to languages other than English)
 */
@JsonDeserialize(builder = GetFlightFarerulesOperationParams.Builder::class)
@ConsistentCopyVisibility
data class GetFlightFarerulesOperationParams private constructor(
    val offerToken: kotlin.String,
    val source: kotlin.String? =
        null,
    val partnerTransactionID: kotlin.String,
    val locale: kotlin.String? =
        null,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("offerToken") private var offerToken: kotlin.String? = null,
        @JsonProperty("Source") private var source: kotlin.String? = null,
        @JsonProperty("Partner-Transaction-ID") private var partnerTransactionID: kotlin.String? = null,
        @JsonProperty("locale") private var locale: kotlin.String? = null,

    ) {
        /**
         * @param offerToken An offerToken from a Flight API responses.
         */
        fun offerToken(
            offerToken: kotlin.String,
        ) = apply { this.offerToken = offerToken }

        /**
         * @param source Accepts any single-word value that describes the source from which the API is being called. Example: 'Details', 'Book', 'Itin', etc.
         */
        fun source(
            source: kotlin.String,
        ) = apply { this.source = source }

        /**
         * @param partnerTransactionID Partner-generated identifier.
         */
        fun partnerTransactionID(
            partnerTransactionID: kotlin.String,
        ) = apply { this.partnerTransactionID = partnerTransactionID }

        /**
         * @param locale locale information (Even though locale may be requested, the majority of the information in the response comes directly from the GDS systems and does NOT have any localization to languages other than English)
         */
        fun locale(
            locale: kotlin.String,
        ) = apply { this.locale = locale }

        fun build(): GetFlightFarerulesOperationParams {
            val offerToken = this.offerToken.getOrThrow {
                IllegalArgumentException("offerToken must not be null")
            }

            val partnerTransactionID = this.partnerTransactionID.getOrThrow {
                IllegalArgumentException("partnerTransactionID must not be null")
            }

            val params = GetFlightFarerulesOperationParams(
                offerToken = offerToken,
                source = source,
                partnerTransactionID = partnerTransactionID,
                locale = locale,
            )
            return params
        }
    }

    fun toBuilder() = Builder(
        offerToken = offerToken,
        source = source,
        partnerTransactionID = partnerTransactionID,
        locale = locale,
    )

    fun getHeaders(): Headers = Headers.builder().apply {
        source?.let {
            add("Source", it)
        }
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
    }

    fun getPathParams(): Map<String, String> = buildMap {
        offerToken.also {
            put("offerToken", offerToken)
        }
    }
}
