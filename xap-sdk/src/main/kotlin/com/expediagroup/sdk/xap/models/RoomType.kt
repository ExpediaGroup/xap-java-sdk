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

import com.expediagroup.sdk.xap.models.BedType
import com.expediagroup.sdk.xap.models.Promotion
import com.expediagroup.sdk.xap.models.RatePlan
import com.expediagroup.sdk.xap.models.RoomOccupancyPolicy
import com.expediagroup.sdk.xap.models.RoomTypeAmenitiesInner
import com.expediagroup.sdk.xap.models.RoomTypeLinks
import com.expediagroup.sdk.xap.models.RoomTypeMediaInner
import com.expediagroup.sdk.xap.models.RoomTypePrice
import com.expediagroup.sdk.xap.models.RoomTypeStandalonePrice
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param description Text description of the room type.
 * @param roomKey An encrypted string which includes the information that could be used to address the current room type.  `RoomKey` has been renamed as `OfferId`.
 * @param offerId An encrypted string which includes the information that could be used to address the current room type.
 * @param merchantName Name of Merchant that did the initial Authentication.
 * @param ratePlanType Indicate the room type is sold as package or standalone.
 * @param ratePlans Container for rate plan information.
 * @param price
 * @param standalonePrice
 * @param promotionsDeprecated All promotion information of the room.  **Note**: The node has been moved to `RatePlan` node, and will be deprecated soon.
 * @param links
 * @param smokingOption The smoking options available for the room type.
 * @param bedTypeOptions Statement of bed types available for this offer. A room may have several bed type options available.  **NOTE**: due to the large number of bed type options available, we no longer publish a list of available bed types. More information is available in [Lodging Bed Types](https://developers.expediagroup.com/xap/products/xap/lodging/references/bed-types).
 * @param roomOccupancyPolicy
 * @param amenities Container for all room amenities.
 * @param descriptiveAmenities Container for all room amenities in group.  The key is amenity category, the values are the amenity information. The category for grouped amenities in room level will be: - ACCESSIBILITY - BATHROOM - BEDROOM - CLUB_EXEC - FAMILY_FRIENDLY - ENTERTAINMENT - FOOD_AND_DRINK - INTERNET - MORE - OUTDOOR_SPACE - SAFETY
 * @param media Container for Media elements.
 */
data class RoomType(
    // Text description of the room type.
    @JsonProperty("Description")
    val description: kotlin.String? = null,
    // An encrypted string which includes the information that could be used to address the current room type.  `RoomKey` has been renamed as `OfferId`.
    @Deprecated(message = "This property is deprecated.")
    @JsonProperty("RoomKey")
    val roomKey: kotlin.String? = null,
    // An encrypted string which includes the information that could be used to address the current room type.
    @JsonProperty("OfferId")
    val offerId: kotlin.String? = null,
    // Name of Merchant that did the initial Authentication.
    @JsonProperty("MerchantName")
    val merchantName: kotlin.String? = null,
    // Indicate the room type is sold as package or standalone.
    @JsonProperty("RatePlanType")
    val ratePlanType: RoomType.RatePlanType? = null,
    // Container for rate plan information.
    @JsonProperty("RatePlans")
    val ratePlans: kotlin.collections.List<RatePlan>? = null,
    @JsonProperty("Price")
    val price: RoomTypePrice? = null,
    @JsonProperty("StandalonePrice")
    val standalonePrice: RoomTypeStandalonePrice? = null,
    // All promotion information of the room.  **Note**: The node has been moved to `RatePlan` node, and will be deprecated soon.
    @Deprecated(message = "This property is deprecated.")
    @JsonProperty("Promotions [deprecated]")
    val promotionsDeprecated: kotlin.collections.List<Promotion>? = null,
    @JsonProperty("Links")
    val links: RoomTypeLinks? = null,
    // The smoking options available for the room type.
    @JsonProperty("SmokingOption")
    val smokingOption: RoomType.SmokingOption? = null,
    // Statement of bed types available for this offer. A room may have several bed type options available.  **NOTE**: due to the large number of bed type options available, we no longer publish a list of available bed types. More information is available in [Lodging Bed Types](https://developers.expediagroup.com/xap/products/xap/lodging/references/bed-types).
    @JsonProperty("BedTypeOptions")
    val bedTypeOptions: kotlin.collections.List<BedType>? = null,
    @JsonProperty("RoomOccupancyPolicy")
    val roomOccupancyPolicy: RoomOccupancyPolicy? = null,
    // Container for all room amenities.
    @JsonProperty("Amenities")
    val amenities: kotlin.collections.List<RoomTypeAmenitiesInner>? = null,
    // Container for all room amenities in group.  The key is amenity category, the values are the amenity information. The category for grouped amenities in room level will be: - ACCESSIBILITY - BATHROOM - BEDROOM - CLUB_EXEC - FAMILY_FRIENDLY - ENTERTAINMENT - FOOD_AND_DRINK - INTERNET - MORE - OUTDOOR_SPACE - SAFETY
    @JsonProperty("DescriptiveAmenities")
    val descriptiveAmenities: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>? = null,
    // Container for Media elements.
    @JsonProperty("Media")
    val media: kotlin.collections.List<RoomTypeMediaInner>? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var description: kotlin.String? = null,
        private var roomKey: kotlin.String? = null,
        private var offerId: kotlin.String? = null,
        private var merchantName: kotlin.String? = null,
        private var ratePlanType: RoomType.RatePlanType? = null,
        private var ratePlans: kotlin.collections.List<RatePlan>? = null,
        private var price: RoomTypePrice? = null,
        private var standalonePrice: RoomTypeStandalonePrice? = null,
        private var promotionsDeprecated: kotlin.collections.List<Promotion>? = null,
        private var links: RoomTypeLinks? = null,
        private var smokingOption: RoomType.SmokingOption? = null,
        private var bedTypeOptions: kotlin.collections.List<BedType>? = null,
        private var roomOccupancyPolicy: RoomOccupancyPolicy? = null,
        private var amenities: kotlin.collections.List<RoomTypeAmenitiesInner>? = null,
        private var descriptiveAmenities: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>? = null,
        private var media: kotlin.collections.List<RoomTypeMediaInner>? = null,
    ) {
        fun description(description: kotlin.String?) = apply { this.description = description }

        fun roomKey(roomKey: kotlin.String?) = apply { this.roomKey = roomKey }

        fun offerId(offerId: kotlin.String?) = apply { this.offerId = offerId }

        fun merchantName(merchantName: kotlin.String?) = apply { this.merchantName = merchantName }

        fun ratePlanType(ratePlanType: RoomType.RatePlanType?) = apply { this.ratePlanType = ratePlanType }

        fun ratePlans(ratePlans: kotlin.collections.List<RatePlan>?) = apply { this.ratePlans = ratePlans }

        fun price(price: RoomTypePrice?) = apply { this.price = price }

        fun standalonePrice(standalonePrice: RoomTypeStandalonePrice?) = apply { this.standalonePrice = standalonePrice }

        fun promotionsDeprecated(promotionsDeprecated: kotlin.collections.List<Promotion>?) = apply { this.promotionsDeprecated = promotionsDeprecated }

        fun links(links: RoomTypeLinks?) = apply { this.links = links }

        fun smokingOption(smokingOption: RoomType.SmokingOption?) = apply { this.smokingOption = smokingOption }

        fun bedTypeOptions(bedTypeOptions: kotlin.collections.List<BedType>?) = apply { this.bedTypeOptions = bedTypeOptions }

        fun roomOccupancyPolicy(roomOccupancyPolicy: RoomOccupancyPolicy?) = apply { this.roomOccupancyPolicy = roomOccupancyPolicy }

        fun amenities(amenities: kotlin.collections.List<RoomTypeAmenitiesInner>?) = apply { this.amenities = amenities }

        fun descriptiveAmenities(descriptiveAmenities: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>?) =
            apply {
                this.descriptiveAmenities =
                    descriptiveAmenities
            }

        fun media(media: kotlin.collections.List<RoomTypeMediaInner>?) = apply { this.media = media }

        fun build(): RoomType {
            val instance =
                RoomType(
                    description = description,
                    roomKey = roomKey,
                    offerId = offerId,
                    merchantName = merchantName,
                    ratePlanType = ratePlanType,
                    ratePlans = ratePlans,
                    price = price,
                    standalonePrice = standalonePrice,
                    promotionsDeprecated = promotionsDeprecated,
                    links = links,
                    smokingOption = smokingOption,
                    bedTypeOptions = bedTypeOptions,
                    roomOccupancyPolicy = roomOccupancyPolicy,
                    amenities = amenities,
                    descriptiveAmenities = descriptiveAmenities,
                    media = media,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            description = description,
            roomKey = roomKey,
            offerId = offerId,
            merchantName = merchantName,
            ratePlanType = ratePlanType,
            ratePlans = ratePlans,
            price = price,
            standalonePrice = standalonePrice,
            promotionsDeprecated = promotionsDeprecated,
            links = links,
            smokingOption = smokingOption,
            bedTypeOptions = bedTypeOptions,
            roomOccupancyPolicy = roomOccupancyPolicy,
            amenities = amenities,
            descriptiveAmenities = descriptiveAmenities,
            media = media,
        )

    /**
     * Indicate the room type is sold as package or standalone.
     * Values: STANDALONE,PACKAGE,WHOLESALE
     */
    enum class RatePlanType(
        val value: kotlin.String,
    ) {
        @JsonProperty("standalone")
        STANDALONE("standalone"),

        @JsonProperty("package")
        PACKAGE("package"),

        @JsonProperty("wholesale")
        WHOLESALE("wholesale"),
    }

    /**
     * The smoking options available for the room type.
     * Values: SMOKING_OR_NON_SMOKING,SMOKING,NON_SMOKING
     */
    enum class SmokingOption(
        val value: kotlin.String,
    ) {
        @JsonProperty("SmokingOrNonSmoking")
        SMOKING_OR_NON_SMOKING("SmokingOrNonSmoking"),

        @JsonProperty("Smoking")
        SMOKING("Smoking"),

        @JsonProperty("NonSmoking")
        NON_SMOKING("NonSmoking"),
    }
}
