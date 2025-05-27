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
import com.expediagroup.sdk.xap.model.FlightsV3LodgingAmenity
import com.expediagroup.sdk.xap.model.FlightsV3Media
import com.expediagroup.sdk.xap.model.FlightsV3Promotion
import com.expediagroup.sdk.xap.model.FlightsV3RatePlan
import com.expediagroup.sdk.xap.model.FlightsV3RoomOccupancyPolicy
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param ratePlans Container for rate plan information.
 * @param description Text description of the room type.
 * @param promotions
 * @param smokingOption The smoking options available for the room type. Options could be: SmokingOrNonSmoking Smoking NonSmoking
 * @param roomOccupancyPolicy
 * @param amenities Container for all room amenities.
 * @param media Container for hotel images
 */
@ConsistentCopyVisibility data class FlightsV3RoomType private constructor(
    /* Container for rate plan information. */
    @JsonProperty("RatePlans")
    val ratePlans: kotlin.collections
        .List<
            FlightsV3RatePlan,
            >,

    /* Text description of the room type. */
    @JsonProperty("Description")
    val description: kotlin.String? = null,

    @JsonProperty("Promotions")
    val promotions: kotlin.collections.List<FlightsV3Promotion>? = null,

    /* The smoking options available for the room type. Options could be: SmokingOrNonSmoking Smoking NonSmoking */
    @JsonProperty("SmokingOption")
    val smokingOption: FlightsV3RoomType.SmokingOption? = null,

    @JsonProperty("RoomOccupancyPolicy")
    val roomOccupancyPolicy: FlightsV3RoomOccupancyPolicy? = null,

    /* Container for all room amenities. */
    @JsonProperty("Amenities")
    val amenities: kotlin.collections.List<FlightsV3LodgingAmenity>? = null,

    /* Container for hotel images */
    @JsonProperty("Media")
    val media: kotlin.collections.List<FlightsV3Media>? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var ratePlans: kotlin.collections.List<FlightsV3RatePlan>? = null,
        private var description: kotlin.String? = null,
        private var promotions: kotlin.collections.List<FlightsV3Promotion>? = null,
        private var smokingOption: FlightsV3RoomType.SmokingOption? = null,
        private var roomOccupancyPolicy: FlightsV3RoomOccupancyPolicy? = null,
        private var amenities: kotlin.collections.List<FlightsV3LodgingAmenity>? = null,
        private var media: kotlin.collections.List<FlightsV3Media>? = null,
    ) {
        fun ratePlans(ratePlans: kotlin.collections.List<FlightsV3RatePlan>) = apply { this.ratePlans = ratePlans }

        fun description(description: kotlin.String?) = apply { this.description = description }

        fun promotions(promotions: kotlin.collections.List<FlightsV3Promotion>?) = apply { this.promotions = promotions }

        fun smokingOption(smokingOption: FlightsV3RoomType.SmokingOption?) = apply { this.smokingOption = smokingOption }

        fun roomOccupancyPolicy(roomOccupancyPolicy: FlightsV3RoomOccupancyPolicy?) = apply { this.roomOccupancyPolicy = roomOccupancyPolicy }

        fun amenities(amenities: kotlin.collections.List<FlightsV3LodgingAmenity>?) = apply { this.amenities = amenities }

        fun media(media: kotlin.collections.List<FlightsV3Media>?) = apply { this.media = media }

        fun build(): FlightsV3RoomType {
            val ratePlans = this.ratePlans.getOrThrow {
                IllegalArgumentException("ratePlans must not be null")
            }

            val instance = FlightsV3RoomType(
                ratePlans = ratePlans,
                description = description,
                promotions = promotions,
                smokingOption = smokingOption,
                roomOccupancyPolicy = roomOccupancyPolicy,
                amenities = amenities,
                media = media,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        ratePlans = ratePlans,
        description = description,
        promotions = promotions,
        smokingOption = smokingOption,
        roomOccupancyPolicy = roomOccupancyPolicy,
        amenities = amenities,
        media = media,
    )

    /**
     * The smoking options available for the room type. Options could be: SmokingOrNonSmoking Smoking NonSmoking
     * Values: SMOKING_OR_NON_SMOKING,SMOKING,NON_SMOKING
     */
    enum class SmokingOption(val value: kotlin.String) {
        @JsonProperty("SmokingOrNonSmoking")
        SMOKING_OR_NON_SMOKING("SmokingOrNonSmoking"),

        @JsonProperty("Smoking")
        SMOKING("Smoking"),

        @JsonProperty("NonSmoking")
        NON_SMOKING("NonSmoking"),
    }
}
