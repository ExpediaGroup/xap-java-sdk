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

import com.expediagroup.sdk.xap.model.RefundPenaltyDetail
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Contains refund penalty information
 * @param segmentIds Contains a list of segment Ids
 * @param preTripChange
 */
data class FlightsV3OfferRefundPenaltyInnerRefundPenalty(
    // Contains a list of segment Ids
    @JsonProperty("SegmentIds")
    val segmentIds: kotlin.collections
        .List<
            kotlin.String,
        >,
    @JsonProperty("PreTripChange")
    val preTripChange: RefundPenaltyDetail? = null,
) {
    init {
        require(segmentIds != null) { "segmentIds must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var segmentIds: kotlin.collections.List<kotlin.String>? = null,
        private var preTripChange: RefundPenaltyDetail? = null,
    ) {
        fun segmentIds(segmentIds: kotlin.collections.List<kotlin.String>) = apply { this.segmentIds = segmentIds }

        fun preTripChange(preTripChange: RefundPenaltyDetail?) = apply { this.preTripChange = preTripChange }

        fun build(): FlightsV3OfferRefundPenaltyInnerRefundPenalty {
            val instance =
                FlightsV3OfferRefundPenaltyInnerRefundPenalty(
                    segmentIds = segmentIds!!,
                    preTripChange = preTripChange,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            segmentIds = segmentIds!!,
            preTripChange = preTripChange,
        )
}
