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
 * @property partnerTransactionId The `Partner-Transaction-ID` is a required API request header element that is <u>not</u> consumed by Expedia. It will be required in all XAP v3 API request headers and will be mirrored back to the partner in the corresponding API response header.  The `Partner-Transaction-ID` may be any alphanumeric string of the partner's choosing.
 * @property propertyIds Comma-separated list of Expedia Property IDs.  The API request supports a maximum of 50 Property IDs in a single request.
 */
@JsonDeserialize(builder = GetLodgingAvailabilityCalendarsOperationParams.Builder::class)
@ConsistentCopyVisibility
data class GetLodgingAvailabilityCalendarsOperationParams private constructor(
    val partnerTransactionId: kotlin.String,
    val propertyIds: kotlin.collections.Set<
        kotlin.String,
        >? =
        null,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("Partner-Transaction-Id") private var partnerTransactionId: kotlin.String? = null,
        @JsonProperty("propertyIds") private var propertyIds: kotlin.collections.Set<
            kotlin.String,
            >? = null,

    ) {
        /**
         * @param partnerTransactionId The `Partner-Transaction-ID` is a required API request header element that is <u>not</u> consumed by Expedia. It will be required in all XAP v3 API request headers and will be mirrored back to the partner in the corresponding API response header.  The `Partner-Transaction-ID` may be any alphanumeric string of the partner's choosing.
         */
        fun partnerTransactionId(
            partnerTransactionId: kotlin.String,
        ) = apply { this.partnerTransactionId = partnerTransactionId }

        /**
         * @param propertyIds Comma-separated list of Expedia Property IDs.  The API request supports a maximum of 50 Property IDs in a single request.
         */
        fun propertyIds(
            propertyIds: kotlin.collections.Set<
                kotlin.String,
                >,
        ) = apply { this.propertyIds = propertyIds }

        fun build(): GetLodgingAvailabilityCalendarsOperationParams {
            val partnerTransactionId = this.partnerTransactionId.getOrThrow {
                IllegalArgumentException("partnerTransactionId must not be null")
            }

            val params = GetLodgingAvailabilityCalendarsOperationParams(
                partnerTransactionId = partnerTransactionId,
                propertyIds = propertyIds,
            )
            return params
        }
    }

    fun toBuilder() = Builder(
        partnerTransactionId = partnerTransactionId,
        propertyIds = propertyIds,
    )

    fun getHeaders(): Headers = Headers.builder().apply {
        add("Partner-Transaction-Id", partnerTransactionId)
        add("Accept", "application/vnd.exp-lodging.v3+json")
    }.build()

    fun getQueryParams(): List<UrlQueryParam> = buildList {
        propertyIds?.let {
            val key = "propertyIds"
            val value = buildList {
                addAll(it.map { v -> v.toString() })
            }

            add(
                UrlQueryParam(
                    key = key,
                    value = value,
                    stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", explode),
                ),
            )
        }
    }
}
