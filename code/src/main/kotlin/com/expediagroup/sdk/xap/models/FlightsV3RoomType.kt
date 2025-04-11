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

import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.FlightsV3LodgingAmenity
import com.expediagroup.sdk.xap.models.FlightsV3Media
import com.expediagroup.sdk.xap.models.FlightsV3Promotion
import com.expediagroup.sdk.xap.models.FlightsV3RatePlan
import com.expediagroup.sdk.xap.models.FlightsV3RoomOccupancyPolicy
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

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
data class FlightsV3RoomType(
    // Container for rate plan information.
    @JsonProperty("RatePlans")
    @field:NotNull
    @field:Valid
    val ratePlans: kotlin.collections
        .List<
            FlightsV3RatePlan
        >,
    // Text description of the room type.
    @JsonProperty("Description")
    @field:Valid
    val description: kotlin.String? = null,
    @JsonProperty("Promotions")
    @field:Valid
    val promotions: kotlin.collections.List<FlightsV3Promotion>? = null,
    // The smoking options available for the room type. Options could be: SmokingOrNonSmoking Smoking NonSmoking
    @JsonProperty("SmokingOption")
    val smokingOption: FlightsV3RoomType.SmokingOption? = null,
    @JsonProperty("RoomOccupancyPolicy")
    @field:Valid
    val roomOccupancyPolicy: FlightsV3RoomOccupancyPolicy? = null,
    // Container for all room amenities.
    @JsonProperty("Amenities")
    @field:Valid
    val amenities: kotlin.collections.List<FlightsV3LodgingAmenity>? = null,
    // Container for hotel images
    @JsonProperty("Media")
    @field:Valid
    val media: kotlin.collections.List<FlightsV3Media>? = null
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
        private var media: kotlin.collections.List<FlightsV3Media>? = null
    ) {
        fun ratePlans(ratePlans: kotlin.collections.List<FlightsV3RatePlan>) = apply { this.ratePlans = ratePlans }

        fun description(description: kotlin.String?) = apply { this.description = description }

        fun promotions(promotions: kotlin.collections.List<FlightsV3Promotion>?) = apply { this.promotions = promotions }

        fun smokingOption(smokingOption: FlightsV3RoomType.SmokingOption?) = apply { this.smokingOption = smokingOption }

        fun roomOccupancyPolicy(roomOccupancyPolicy: FlightsV3RoomOccupancyPolicy?) = apply { this.roomOccupancyPolicy = roomOccupancyPolicy }

        fun amenities(amenities: kotlin.collections.List<FlightsV3LodgingAmenity>?) = apply { this.amenities = amenities }

        fun media(media: kotlin.collections.List<FlightsV3Media>?) = apply { this.media = media }

        fun build(): FlightsV3RoomType {
            val instance =
                FlightsV3RoomType(
                    ratePlans = ratePlans!!,
                    description = description,
                    promotions = promotions,
                    smokingOption = smokingOption,
                    roomOccupancyPolicy = roomOccupancyPolicy,
                    amenities = amenities,
                    media = media
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightsV3RoomType) {
            val validator =
                Validation
                    .byDefaultProvider()
                    .configure()
                    .messageInterpolator(ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .validator

            val violations = validator.validate(instance)

            if (violations.isNotEmpty()) {
                throw PropertyConstraintViolationException(
                    constraintViolations = violations.map { "${it.propertyPath}: ${it.message}" }
                )
            }
        }
    }

    fun toBuilder() =
        Builder(
            ratePlans = ratePlans!!,
            description = description,
            promotions = promotions,
            smokingOption = smokingOption,
            roomOccupancyPolicy = roomOccupancyPolicy,
            amenities = amenities,
            media = media
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
        NON_SMOKING("NonSmoking")
    }
}
