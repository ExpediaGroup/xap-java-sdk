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

import com.expediagroup.sdk.xap.model.FlexSearchResponseOffersInnerOfferSegmentsInnerSegment
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param segment
 */
@ConsistentCopyVisibility data class FlexSearchResponseOffersInnerOfferSegmentsInner private constructor(
    @JsonProperty("Segment")
    val segment: FlexSearchResponseOffersInnerOfferSegmentsInnerSegment? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var segment: FlexSearchResponseOffersInnerOfferSegmentsInnerSegment? = null,
    ) {
        fun segment(segment: FlexSearchResponseOffersInnerOfferSegmentsInnerSegment?) = apply { this.segment = segment }

        fun build(): FlexSearchResponseOffersInnerOfferSegmentsInner {
            val instance = FlexSearchResponseOffersInnerOfferSegmentsInner(
                segment = segment,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        segment = segment,
    )
}
