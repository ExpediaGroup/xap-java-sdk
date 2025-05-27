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

import com.expediagroup.sdk.xap.model.FlexSearchResponseOffersInnerOffer
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param offer
 */
@ConsistentCopyVisibility data class FlexSearchResponseOffersInner private constructor(
    @JsonProperty("Offer")
    val offer: FlexSearchResponseOffersInnerOffer? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var offer: FlexSearchResponseOffersInnerOffer? = null,
    ) {
        fun offer(offer: FlexSearchResponseOffersInnerOffer?) = apply { this.offer = offer }

        fun build(): FlexSearchResponseOffersInner {
            val instance = FlexSearchResponseOffersInner(
                offer = offer,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        offer = offer,
    )
}
