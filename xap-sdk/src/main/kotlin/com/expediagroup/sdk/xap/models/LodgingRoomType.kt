/*
 * Copyright (C) 2022 Expedia, Inc.
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

import com.expediagroup.sdk.xap.models.LodgingRatePlan
import com.expediagroup.sdk.xap.models.LodgingRoomTypeLinks
import com.expediagroup.sdk.xap.models.LodgingRoomTypePrice
import com.fasterxml.jackson.annotation.JsonProperty

/**
*
 * @param ratePlans Container for rate plan information.
 * @param price
 * @param links
*/
data class LodgingRoomType(
    // Container for rate plan information.
    @JsonProperty("RatePlans")
    val ratePlans: kotlin.collections.List<LodgingRatePlan>? = null,
    @JsonProperty("Price")
    val price: LodgingRoomTypePrice? = null,
    @JsonProperty("Links")
    val links: LodgingRoomTypeLinks? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var ratePlans: kotlin.collections.List<LodgingRatePlan>? = null,
        private var price: LodgingRoomTypePrice? = null,
        private var links: LodgingRoomTypeLinks? = null,
    ) {
        fun ratePlans(ratePlans: kotlin.collections.List<LodgingRatePlan>?) = apply { this.ratePlans = ratePlans }

        fun price(price: LodgingRoomTypePrice?) = apply { this.price = price }

        fun links(links: LodgingRoomTypeLinks?) = apply { this.links = links }

        fun build(): LodgingRoomType {
            val instance =
                LodgingRoomType(
                    ratePlans = ratePlans,
                    price = price,
                    links = links,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            ratePlans = ratePlans,
            price = price,
            links = links,
        )
}
