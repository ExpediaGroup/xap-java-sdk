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
package com.expediagroup.sdk.xap.models

import com.expediagroup.sdk.xap.models.FlexSearchResponseOffersInner
import com.expediagroup.sdk.xap.models.FlexSearchResponseWarningsInner
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param offers Container for list of air offers. An offer gives trip details including flight and pricing information.
 * @param transactionId Unique identifier for the transaction.
 * @param warnings Container for Warning Codes.
 */
data class FlexSearchResponse(
    // Container for list of air offers. An offer gives trip details including flight and pricing information.
    @JsonProperty("Offers")
    val offers: kotlin.collections
        .List<
            FlexSearchResponseOffersInner,
        >,
    // Unique identifier for the transaction.
    @JsonProperty("TransactionId")
    val transactionId: kotlin.String,
    // Container for Warning Codes.
    @JsonProperty("Warnings")
    val warnings: kotlin.collections.List<FlexSearchResponseWarningsInner>? = null,
) {
    init {
        require(offers != null) { "offers must not be null" }

        require(transactionId != null) { "transactionId must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var offers: kotlin.collections.List<FlexSearchResponseOffersInner>? = null,
        private var transactionId: kotlin.String? = null,
        private var warnings: kotlin.collections.List<FlexSearchResponseWarningsInner>? = null,
    ) {
        fun offers(offers: kotlin.collections.List<FlexSearchResponseOffersInner>) = apply { this.offers = offers }

        fun transactionId(transactionId: kotlin.String) = apply { this.transactionId = transactionId }

        fun warnings(warnings: kotlin.collections.List<FlexSearchResponseWarningsInner>?) = apply { this.warnings = warnings }

        fun build(): FlexSearchResponse {
            val instance =
                FlexSearchResponse(
                    offers = offers!!,
                    transactionId = transactionId!!,
                    warnings = warnings,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            offers = offers!!,
            transactionId = transactionId!!,
            warnings = warnings,
        )
}
