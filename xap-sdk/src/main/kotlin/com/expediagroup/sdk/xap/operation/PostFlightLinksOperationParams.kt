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
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

/**
 * @property partnerTransactionID Partner-generated identifier.
 */
@JsonDeserialize(builder = PostFlightLinksOperationParams.Builder::class)
@ConsistentCopyVisibility
data class PostFlightLinksOperationParams private constructor(
    val partnerTransactionID: kotlin.String,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("Partner-Transaction-ID") private var partnerTransactionID: kotlin.String? = null,

    ) {
        /**
         * @param partnerTransactionID Partner-generated identifier.
         */
        fun partnerTransactionID(
            partnerTransactionID: kotlin.String,
        ) = apply { this.partnerTransactionID = partnerTransactionID }

        fun build(): PostFlightLinksOperationParams {
            val partnerTransactionID = this.partnerTransactionID.getOrThrow {
                IllegalArgumentException("partnerTransactionID must not be null")
            }

            val params = PostFlightLinksOperationParams(
                partnerTransactionID = partnerTransactionID,
            )
            return params
        }
    }

    fun toBuilder() = Builder(
        partnerTransactionID = partnerTransactionID,
    )

    fun getHeaders(): Headers = Headers.builder().apply {
        add("Partner-Transaction-ID", partnerTransactionID)
        add("Accept", "application/vnd.exp-flight.v3+json")
    }.build()
}
