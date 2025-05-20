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

import com.expediagroup.sdk.xap.model.Amenity
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Contains list of fare options
 * @param fareName Contains the name of the Fare tagged against the specific fare options.
 * @param segmentIds Contains list of segment Ids to which these FareOptions are provided.
 * @param included Contains list of options that are already included.
 * @param feeApplied Contains list of options that are fee applied.
 * @param notAvailable Contains list of options that are unavailable.
 * @param amenities
 */
data class FareOptions(
    // Contains the name of the Fare tagged against the specific fare options.
    @JsonProperty("FareName")
    val fareName: kotlin.String,
    // Contains list of segment Ids to which these FareOptions are provided.
    @JsonProperty("SegmentIds")
    val segmentIds: kotlin.collections
        .List<
            kotlin.String,
        >,
    // Contains list of options that are already included.
    @JsonProperty("Included")
    val included: kotlin.collections
        .List<
            kotlin.String,
        >,
    // Contains list of options that are fee applied.
    @JsonProperty("FeeApplied")
    val feeApplied: kotlin.collections
        .List<
            kotlin.String,
        >,
    // Contains list of options that are unavailable.
    @JsonProperty("NotAvailable")
    val notAvailable: kotlin.collections.List<kotlin.String>? = null,
    @JsonProperty("Amenities")
    val amenities: Amenity? = null,
) {
    init {
        require(fareName != null) { "fareName must not be null" }

        require(segmentIds != null) { "segmentIds must not be null" }

        require(included != null) { "included must not be null" }

        require(feeApplied != null) { "feeApplied must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var fareName: kotlin.String? = null,
        private var segmentIds: kotlin.collections.List<kotlin.String>? = null,
        private var included: kotlin.collections.List<kotlin.String>? = null,
        private var feeApplied: kotlin.collections.List<kotlin.String>? = null,
        private var notAvailable: kotlin.collections.List<kotlin.String>? = null,
        private var amenities: Amenity? = null,
    ) {
        fun fareName(fareName: kotlin.String) = apply { this.fareName = fareName }

        fun segmentIds(segmentIds: kotlin.collections.List<kotlin.String>) = apply { this.segmentIds = segmentIds }

        fun included(included: kotlin.collections.List<kotlin.String>) = apply { this.included = included }

        fun feeApplied(feeApplied: kotlin.collections.List<kotlin.String>) = apply { this.feeApplied = feeApplied }

        fun notAvailable(notAvailable: kotlin.collections.List<kotlin.String>?) = apply { this.notAvailable = notAvailable }

        fun amenities(amenities: Amenity?) = apply { this.amenities = amenities }

        fun build(): FareOptions {
            val instance =
                FareOptions(
                    fareName = fareName!!,
                    segmentIds = segmentIds!!,
                    included = included!!,
                    feeApplied = feeApplied!!,
                    notAvailable = notAvailable,
                    amenities = amenities,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            fareName = fareName!!,
            segmentIds = segmentIds!!,
            included = included!!,
            feeApplied = feeApplied!!,
            notAvailable = notAvailable,
            amenities = amenities,
        )
}
