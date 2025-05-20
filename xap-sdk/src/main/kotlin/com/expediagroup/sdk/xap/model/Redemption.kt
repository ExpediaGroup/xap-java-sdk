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

import com.expediagroup.sdk.xap.model.ActivitiesLocation
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container of redemption information.
 * @param type The type of redemption process associated to the activity.
 * @param redemptionLocations List of redemption locations where the activity will take place, please refer to Location Section below.
 */
data class Redemption(
    // The type of redemption process associated to the activity.
    @JsonProperty("Type")
    val type: kotlin.String? = null,
    // List of redemption locations where the activity will take place, please refer to Location Section below.
    @JsonProperty("RedemptionLocations")
    val redemptionLocations: kotlin.collections.List<ActivitiesLocation>? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: kotlin.String? = null,
        private var redemptionLocations: kotlin.collections.List<ActivitiesLocation>? = null,
    ) {
        fun type(type: kotlin.String?) = apply { this.type = type }

        fun redemptionLocations(redemptionLocations: kotlin.collections.List<ActivitiesLocation>?) = apply { this.redemptionLocations = redemptionLocations }

        fun build(): Redemption {
            val instance =
                Redemption(
                    type = type,
                    redemptionLocations = redemptionLocations,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            type = type,
            redemptionLocations = redemptionLocations,
        )
}
