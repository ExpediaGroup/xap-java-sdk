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

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * The rating of the car being offered.
 * @param ratingPercentage The percentage of rating.
 * @param ratingCount The total count of rating.
 */
data class RatingWithoutDetails(
    // The percentage of rating.
    @JsonProperty("RatingPercentage")
    val ratingPercentage: kotlin.String,
    // The total count of rating.
    @JsonProperty("RatingCount")
    val ratingCount: kotlin.String,
) {
    init {
        require(ratingPercentage != null) { "ratingPercentage must not be null" }

        require(ratingCount != null) { "ratingCount must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var ratingPercentage: kotlin.String? = null,
        private var ratingCount: kotlin.String? = null,
    ) {
        fun ratingPercentage(ratingPercentage: kotlin.String) = apply { this.ratingPercentage = ratingPercentage }

        fun ratingCount(ratingCount: kotlin.String) = apply { this.ratingCount = ratingCount }

        fun build(): RatingWithoutDetails {
            val instance =
                RatingWithoutDetails(
                    ratingPercentage = ratingPercentage!!,
                    ratingCount = ratingCount!!,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            ratingPercentage = ratingPercentage!!,
            ratingCount = ratingCount!!,
        )
}
