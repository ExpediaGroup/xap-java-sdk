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

import com.expediagroup.sdk.xap.models.FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLeg
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param leg
 */
data class FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInner(
    @JsonProperty("Leg")
    val leg: FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLeg? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var leg: FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLeg? = null,
    ) {
        fun leg(leg: FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInnerLeg?) = apply { this.leg = leg }

        fun build(): FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInner {
            val instance =
                FlexSearchResponseOffersInnerOfferSegmentsInnerSegmentLegsInner(
                    leg = leg,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            leg = leg,
        )
}
