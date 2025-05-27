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
package com.expediagroup.sdk.xap.model

import com.expediagroup.sdk.core.common.getOrThrow
import com.expediagroup.sdk.xap.model.FlightLinksResponseLinksInner
import com.expediagroup.sdk.xap.model.FlightLinksResponseWarningsInner
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param transactionId
 * @param warnings
 * @param links
 */
@ConsistentCopyVisibility data class FlightLinksResponse private constructor(
    @JsonProperty("TransactionId")
    val transactionId: kotlin.String,

    @JsonProperty("Warnings")
    val warnings: kotlin.collections.List<FlightLinksResponseWarningsInner>? = null,

    @JsonProperty("Links")
    val links: kotlin.collections.List<FlightLinksResponseLinksInner>? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var transactionId: kotlin.String? = null,
        private var warnings: kotlin.collections.List<FlightLinksResponseWarningsInner>? = null,
        private var links: kotlin.collections.List<FlightLinksResponseLinksInner>? = null,
    ) {
        fun transactionId(transactionId: kotlin.String) = apply { this.transactionId = transactionId }

        fun warnings(warnings: kotlin.collections.List<FlightLinksResponseWarningsInner>?) = apply { this.warnings = warnings }

        fun links(links: kotlin.collections.List<FlightLinksResponseLinksInner>?) = apply { this.links = links }

        fun build(): FlightLinksResponse {
            val transactionId = this.transactionId.getOrThrow {
                IllegalArgumentException("transactionId must not be null")
            }

            val instance = FlightLinksResponse(
                transactionId = transactionId,
                warnings = warnings,
                links = links,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        transactionId = transactionId,
        warnings = warnings,
        links = links,
    )
}
