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
/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.expediagroup.sdk.xap.models

import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.ChainAndBrandInfo
import com.expediagroup.sdk.xap.models.Description
import com.expediagroup.sdk.xap.models.Distance
import com.expediagroup.sdk.xap.models.HotelHotelAmenitiesInner
import com.expediagroup.sdk.xap.models.HotelLinks
import com.expediagroup.sdk.xap.models.HotelLocation
import com.expediagroup.sdk.xap.models.HotelPolicies
import com.expediagroup.sdk.xap.models.HotelPropertyType
import com.expediagroup.sdk.xap.models.HotelRoomAmenitiesInner
import com.expediagroup.sdk.xap.models.Media
import com.expediagroup.sdk.xap.models.Phone
import com.expediagroup.sdk.xap.models.PropertyDetails
import com.expediagroup.sdk.xap.models.RoomType
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Container for information on each offered hotel.
 * @param id The unique, Expedia-specific hotel property identifier used to designate a single hotel.
 * @param hcomId The unique, Hotels.com-specific hotel property identifier used to designate a single hotel.  This will only be returned if searching via hcomHotelIds in request.
 * @param name The common name of the hotel
 * @param propertyType
 * @param propertyDetails
 * @param localCurrencyCode The Local Currency Code for Hotel (which will be used for any fees that must be paid at the hotel)
 * @param location
 * @param phoneInfos Container for property phone numbers.  Note: PhoneInfos section will not return for Vrbo Vacation Rental properties.
 * @param distance
 * @param description
 * @param status Indicates whether there are available offers at the property during the dates requested, as well as information as to why.  Note that pricing will <u>only</u> be present in the API response for a status of `AVAILABLE`.  If there are no rooms available at the property for the dates requested, then `NOT_AVAILABLE` will be returned.  If there are available rooms, but none that meet the specific parameters of the search request, then one of the other messages will be returned.
 * @param renovationsAndClosures The information about renovations and closures
 * @param chainAndBrandInfo
 * @param thumbnailUrl URL of the thumbnail image of the hotel.  Note that other images sizes are available - You can find a link to the complete list of Supported Hotel Image Sizes in [Lodging Image Captions, IDs, and Sizes](https://developers.expediagroup.com/xap/products/xap/lodging/references/image-captions-ids-and-sizes).
 * @param starRating Star rating value of the hotel property.
 * @param guestRating Average overall guest rating of the hotel.  The value is between 1.0 and 5.0 in 0.1 increments.  Higher is better.
 * @param guestReviewCount The total count of guest reviews used to create the average `GuestRating` above.
 * @param petFriendly Indicates whether the property allows certain pets under certain circumstances.  Prior to booking, guests should review the PetPolicies information in the Lodging Details API to find out whether a particular pet will be permitted to stay at the property.
 * @param lgbtqiaFriendly This value is returned if the property owner has specifically designated this property as LGBTQIA-friendly.
 * @param links
 * @param policies
 * @param cleanlinessAndSafety Container for all cleanliness and safety measures.  The key is the measures category, the values are the information. The category will be: - CLEANLINESS - SOCIAL_DISTANCING - SAFETY - DISCLAIMER
 * @param optionalExtras The optional extras info.
 * @param importantNotices The important notices for hotel.
 * @param media Container for hotel images
 * @param hotelAmenities Container for all hotel amenities.
 * @param hotelDescriptiveAmenities Container for all hotel amenities in group.  The key is amenity category, the values are the amenity information. The category for grouped amenities in hotel level for conventional lodging hotel will be: - PARKING - FOOD_AND_DRINK - INTERNET - THINGS_TO_DO - FAMILY_FRIENDLY - CONVENIENCES - GUEST_SERVICES - BUSINESS_SERVICE - OUTDOOR - ACCESSIBILITY - SPA - ACTIVITIES_NEARBY - LANGS_SPOKEN - MORE  The category for grouped amenities in hotel level for Vacation Rental hotel will be: - BEACH - SKI - POOL/SPA - INTERNET - PARKING - FAMILY_FRIENDLY - KITCHEN - DINING - BEDROOM - BATHROOMS - LIVING_SPACES - ENTERTAINMENT - OUTDOORS - LAUNDRY - WORKSPACES - CLIMATE_CONTROL - PETS - SUITABILITY/ACCESSIBILITY - SERVICES_AND_CONVENIENCES - LOCATION_HIGHLIGHTS - THINGS_TO_DO - GENERAL - SAFETY
 * @param roomAmenities Container for all room amenities.
 * @param roomDescriptiveAmenities Container for all common room amenities in group.  The key is amenity category, the values are the amenity information. The category for grouped amenities in common room level will be: - BEDROOM - BATHROOM - FOOD_AND_DRINK - ENTERTAINMENT - OUTDOOR_SPACE - MORE
 * @param accessibility The accessibility options available for the room.  Possible accessibility include: - Accessible path of travel - Accessible bathroom - Roll-in shower - Handicapped parking - In-room accessibility - Accessibility equipment for the deaf - Braille or raised signage
 * @param memberOnlyDealAvailable Indicates whether the property has member only deal rates available.
 * @param roomTypes Container for all of available room types.
 */
data class Hotel(
    // The unique, Expedia-specific hotel property identifier used to designate a single hotel.
    @JsonProperty("Id")
    @field:Valid
    val id: kotlin.String? = null,
    // The unique, Hotels.com-specific hotel property identifier used to designate a single hotel.  This will only be returned if searching via hcomHotelIds in request.
    @JsonProperty("HcomId")
    @field:Valid
    val hcomId: kotlin.String? = null,
    // The common name of the hotel
    @JsonProperty("Name")
    @field:Valid
    val name: kotlin.String? = null,
    @JsonProperty("PropertyType")
    @field:Valid
    val propertyType: HotelPropertyType? = null,
    @JsonProperty("PropertyDetails")
    @field:Valid
    val propertyDetails: PropertyDetails? = null,
    // The Local Currency Code for Hotel (which will be used for any fees that must be paid at the hotel)
    @JsonProperty("LocalCurrencyCode")
    @field:Valid
    val localCurrencyCode: kotlin.String? = null,
    @JsonProperty("Location")
    @field:Valid
    val location: HotelLocation? = null,
    // Container for property phone numbers.  Note: PhoneInfos section will not return for Vrbo Vacation Rental properties.
    @JsonProperty("PhoneInfos")
    @field:Valid
    val phoneInfos: kotlin.collections.List<Phone>? = null,
    @JsonProperty("Distance")
    @field:Valid
    val distance: Distance? = null,
    @JsonProperty("Description")
    @field:Valid
    val description: Description? = null,
    // Indicates whether there are available offers at the property during the dates requested, as well as information as to why.  Note that pricing will <u>only</u> be present in the API response for a status of `AVAILABLE`.  If there are no rooms available at the property for the dates requested, then `NOT_AVAILABLE` will be returned.  If there are available rooms, but none that meet the specific parameters of the search request, then one of the other messages will be returned.
    @JsonProperty("Status")
    val status: Hotel.Status? = null,
    // The information about renovations and closures
    @JsonProperty("RenovationsAndClosures")
    @field:Valid
    val renovationsAndClosures: kotlin.collections.List<kotlin.String>? = null,
    @JsonProperty("ChainAndBrandInfo")
    @field:Valid
    val chainAndBrandInfo: ChainAndBrandInfo? = null,
    // URL of the thumbnail image of the hotel.  Note that other images sizes are available - You can find a link to the complete list of Supported Hotel Image Sizes in [Lodging Image Captions, IDs, and Sizes](https://developers.expediagroup.com/xap/products/xap/lodging/references/image-captions-ids-and-sizes).
    @JsonProperty("ThumbnailUrl")
    @field:Valid
    val thumbnailUrl: kotlin.String? = null,
    // Star rating value of the hotel property.
    @JsonProperty("StarRating")
    val starRating: Hotel.StarRating? = null,
    // Average overall guest rating of the hotel.  The value is between 1.0 and 5.0 in 0.1 increments.  Higher is better.
    @JsonProperty("GuestRating")
    @field:Valid
    val guestRating: kotlin.String? = null,
    // The total count of guest reviews used to create the average `GuestRating` above.
    @JsonProperty("GuestReviewCount")
    val guestReviewCount: kotlin.Int? = null,
    // Indicates whether the property allows certain pets under certain circumstances.  Prior to booking, guests should review the PetPolicies information in the Lodging Details API to find out whether a particular pet will be permitted to stay at the property.
    @JsonProperty("PetFriendly")
    @field:Valid
    val petFriendly: kotlin.Boolean? = null,
    // This value is returned if the property owner has specifically designated this property as LGBTQIA-friendly.
    @JsonProperty("LgbtqiaFriendly")
    @field:Valid
    val lgbtqiaFriendly: kotlin.Boolean? = null,
    @JsonProperty("Links")
    @field:Valid
    val links: HotelLinks? = null,
    @JsonProperty("Policies")
    @field:Valid
    val policies: HotelPolicies? = null,
    // Container for all cleanliness and safety measures.  The key is the measures category, the values are the information. The category will be: - CLEANLINESS - SOCIAL_DISTANCING - SAFETY - DISCLAIMER
    @JsonProperty("CleanlinessAndSafety")
    @field:Valid
    val cleanlinessAndSafety: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>? = null,
    // The optional extras info.
    @JsonProperty("OptionalExtras")
    @field:Valid
    val optionalExtras: kotlin.collections.List<kotlin.String>? = null,
    // The important notices for hotel.
    @JsonProperty("ImportantNotices")
    @field:Valid
    val importantNotices: kotlin.collections.List<kotlin.String>? = null,
    // Container for hotel images
    @JsonProperty("Media")
    @field:Valid
    val media: kotlin.collections.List<Media>? = null,
    // Container for all hotel amenities.
    @JsonProperty("HotelAmenities")
    @field:Valid
    val hotelAmenities: kotlin.collections.List<HotelHotelAmenitiesInner>? = null,
    // Container for all hotel amenities in group.  The key is amenity category, the values are the amenity information. The category for grouped amenities in hotel level for conventional lodging hotel will be: - PARKING - FOOD_AND_DRINK - INTERNET - THINGS_TO_DO - FAMILY_FRIENDLY - CONVENIENCES - GUEST_SERVICES - BUSINESS_SERVICE - OUTDOOR - ACCESSIBILITY - SPA - ACTIVITIES_NEARBY - LANGS_SPOKEN - MORE  The category for grouped amenities in hotel level for Vacation Rental hotel will be: - BEACH - SKI - POOL/SPA - INTERNET - PARKING - FAMILY_FRIENDLY - KITCHEN - DINING - BEDROOM - BATHROOMS - LIVING_SPACES - ENTERTAINMENT - OUTDOORS - LAUNDRY - WORKSPACES - CLIMATE_CONTROL - PETS - SUITABILITY/ACCESSIBILITY - SERVICES_AND_CONVENIENCES - LOCATION_HIGHLIGHTS - THINGS_TO_DO - GENERAL - SAFETY
    @JsonProperty("HotelDescriptiveAmenities")
    @field:Valid
    val hotelDescriptiveAmenities: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>? = null,
    // Container for all room amenities.
    @JsonProperty("RoomAmenities")
    @field:Valid
    val roomAmenities: kotlin.collections.List<HotelRoomAmenitiesInner>? = null,
    // Container for all common room amenities in group.  The key is amenity category, the values are the amenity information. The category for grouped amenities in common room level will be: - BEDROOM - BATHROOM - FOOD_AND_DRINK - ENTERTAINMENT - OUTDOOR_SPACE - MORE
    @JsonProperty("RoomDescriptiveAmenities")
    @field:Valid
    val roomDescriptiveAmenities: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>? = null,
    // The accessibility options available for the room.  Possible accessibility include: - Accessible path of travel - Accessible bathroom - Roll-in shower - Handicapped parking - In-room accessibility - Accessibility equipment for the deaf - Braille or raised signage
    @JsonProperty("Accessibility")
    @field:Valid
    val accessibility: kotlin.collections.List<kotlin.String>? = null,
    // Indicates whether the property has member only deal rates available.
    @JsonProperty("MemberOnlyDealAvailable")
    @field:Valid
    val memberOnlyDealAvailable: kotlin.Boolean? = null,
    // Container for all of available room types.
    @JsonProperty("RoomTypes")
    @field:Valid
    val roomTypes: kotlin.collections.List<RoomType>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.String? = null,
        private var hcomId: kotlin.String? = null,
        private var name: kotlin.String? = null,
        private var propertyType: HotelPropertyType? = null,
        private var propertyDetails: PropertyDetails? = null,
        private var localCurrencyCode: kotlin.String? = null,
        private var location: HotelLocation? = null,
        private var phoneInfos: kotlin.collections.List<Phone>? = null,
        private var distance: Distance? = null,
        private var description: Description? = null,
        private var status: Hotel.Status? = null,
        private var renovationsAndClosures: kotlin.collections.List<kotlin.String>? = null,
        private var chainAndBrandInfo: ChainAndBrandInfo? = null,
        private var thumbnailUrl: kotlin.String? = null,
        private var starRating: Hotel.StarRating? = null,
        private var guestRating: kotlin.String? = null,
        private var guestReviewCount: kotlin.Int? = null,
        private var petFriendly: kotlin.Boolean? = null,
        private var lgbtqiaFriendly: kotlin.Boolean? = null,
        private var links: HotelLinks? = null,
        private var policies: HotelPolicies? = null,
        private var cleanlinessAndSafety: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>? = null,
        private var optionalExtras: kotlin.collections.List<kotlin.String>? = null,
        private var importantNotices: kotlin.collections.List<kotlin.String>? = null,
        private var media: kotlin.collections.List<Media>? = null,
        private var hotelAmenities: kotlin.collections.List<HotelHotelAmenitiesInner>? = null,
        private var hotelDescriptiveAmenities: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>? = null,
        private var roomAmenities: kotlin.collections.List<HotelRoomAmenitiesInner>? = null,
        private var roomDescriptiveAmenities: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>? = null,
        private var accessibility: kotlin.collections.List<kotlin.String>? = null,
        private var memberOnlyDealAvailable: kotlin.Boolean? = null,
        private var roomTypes: kotlin.collections.List<RoomType>? = null
    ) {
        fun id(id: kotlin.String?) = apply { this.id = id }

        fun hcomId(hcomId: kotlin.String?) = apply { this.hcomId = hcomId }

        fun name(name: kotlin.String?) = apply { this.name = name }

        fun propertyType(propertyType: HotelPropertyType?) = apply { this.propertyType = propertyType }

        fun propertyDetails(propertyDetails: PropertyDetails?) = apply { this.propertyDetails = propertyDetails }

        fun localCurrencyCode(localCurrencyCode: kotlin.String?) = apply { this.localCurrencyCode = localCurrencyCode }

        fun location(location: HotelLocation?) = apply { this.location = location }

        fun phoneInfos(phoneInfos: kotlin.collections.List<Phone>?) = apply { this.phoneInfos = phoneInfos }

        fun distance(distance: Distance?) = apply { this.distance = distance }

        fun description(description: Description?) = apply { this.description = description }

        fun status(status: Hotel.Status?) = apply { this.status = status }

        fun renovationsAndClosures(renovationsAndClosures: kotlin.collections.List<kotlin.String>?) = apply { this.renovationsAndClosures = renovationsAndClosures }

        fun chainAndBrandInfo(chainAndBrandInfo: ChainAndBrandInfo?) = apply { this.chainAndBrandInfo = chainAndBrandInfo }

        fun thumbnailUrl(thumbnailUrl: kotlin.String?) = apply { this.thumbnailUrl = thumbnailUrl }

        fun starRating(starRating: Hotel.StarRating?) = apply { this.starRating = starRating }

        fun guestRating(guestRating: kotlin.String?) = apply { this.guestRating = guestRating }

        fun guestReviewCount(guestReviewCount: kotlin.Int?) = apply { this.guestReviewCount = guestReviewCount }

        fun petFriendly(petFriendly: kotlin.Boolean?) = apply { this.petFriendly = petFriendly }

        fun lgbtqiaFriendly(lgbtqiaFriendly: kotlin.Boolean?) = apply { this.lgbtqiaFriendly = lgbtqiaFriendly }

        fun links(links: HotelLinks?) = apply { this.links = links }

        fun policies(policies: HotelPolicies?) = apply { this.policies = policies }

        fun cleanlinessAndSafety(cleanlinessAndSafety: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>?) =
            apply {
                this.cleanlinessAndSafety =
                    cleanlinessAndSafety
            }

        fun optionalExtras(optionalExtras: kotlin.collections.List<kotlin.String>?) = apply { this.optionalExtras = optionalExtras }

        fun importantNotices(importantNotices: kotlin.collections.List<kotlin.String>?) = apply { this.importantNotices = importantNotices }

        fun media(media: kotlin.collections.List<Media>?) = apply { this.media = media }

        fun hotelAmenities(hotelAmenities: kotlin.collections.List<HotelHotelAmenitiesInner>?) = apply { this.hotelAmenities = hotelAmenities }

        fun hotelDescriptiveAmenities(hotelDescriptiveAmenities: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>?) =
            apply {
                this.hotelDescriptiveAmenities =
                    hotelDescriptiveAmenities
            }

        fun roomAmenities(roomAmenities: kotlin.collections.List<HotelRoomAmenitiesInner>?) = apply { this.roomAmenities = roomAmenities }

        fun roomDescriptiveAmenities(roomDescriptiveAmenities: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>?) =
            apply {
                this.roomDescriptiveAmenities =
                    roomDescriptiveAmenities
            }

        fun accessibility(accessibility: kotlin.collections.List<kotlin.String>?) = apply { this.accessibility = accessibility }

        fun memberOnlyDealAvailable(memberOnlyDealAvailable: kotlin.Boolean?) = apply { this.memberOnlyDealAvailable = memberOnlyDealAvailable }

        fun roomTypes(roomTypes: kotlin.collections.List<RoomType>?) = apply { this.roomTypes = roomTypes }

        fun build(): Hotel {
            val instance =
                Hotel(
                    id = id,
                    hcomId = hcomId,
                    name = name,
                    propertyType = propertyType,
                    propertyDetails = propertyDetails,
                    localCurrencyCode = localCurrencyCode,
                    location = location,
                    phoneInfos = phoneInfos,
                    distance = distance,
                    description = description,
                    status = status,
                    renovationsAndClosures = renovationsAndClosures,
                    chainAndBrandInfo = chainAndBrandInfo,
                    thumbnailUrl = thumbnailUrl,
                    starRating = starRating,
                    guestRating = guestRating,
                    guestReviewCount = guestReviewCount,
                    petFriendly = petFriendly,
                    lgbtqiaFriendly = lgbtqiaFriendly,
                    links = links,
                    policies = policies,
                    cleanlinessAndSafety = cleanlinessAndSafety,
                    optionalExtras = optionalExtras,
                    importantNotices = importantNotices,
                    media = media,
                    hotelAmenities = hotelAmenities,
                    hotelDescriptiveAmenities = hotelDescriptiveAmenities,
                    roomAmenities = roomAmenities,
                    roomDescriptiveAmenities = roomDescriptiveAmenities,
                    accessibility = accessibility,
                    memberOnlyDealAvailable = memberOnlyDealAvailable,
                    roomTypes = roomTypes
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Hotel) {
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
            id = id,
            hcomId = hcomId,
            name = name,
            propertyType = propertyType,
            propertyDetails = propertyDetails,
            localCurrencyCode = localCurrencyCode,
            location = location,
            phoneInfos = phoneInfos,
            distance = distance,
            description = description,
            status = status,
            renovationsAndClosures = renovationsAndClosures,
            chainAndBrandInfo = chainAndBrandInfo,
            thumbnailUrl = thumbnailUrl,
            starRating = starRating,
            guestRating = guestRating,
            guestReviewCount = guestReviewCount,
            petFriendly = petFriendly,
            lgbtqiaFriendly = lgbtqiaFriendly,
            links = links,
            policies = policies,
            cleanlinessAndSafety = cleanlinessAndSafety,
            optionalExtras = optionalExtras,
            importantNotices = importantNotices,
            media = media,
            hotelAmenities = hotelAmenities,
            hotelDescriptiveAmenities = hotelDescriptiveAmenities,
            roomAmenities = roomAmenities,
            roomDescriptiveAmenities = roomDescriptiveAmenities,
            accessibility = accessibility,
            memberOnlyDealAvailable = memberOnlyDealAvailable,
            roomTypes = roomTypes
        )

    /**
     * Indicates whether there are available offers at the property during the dates requested, as well as information as to why.  Note that pricing will <u>only</u> be present in the API response for a status of `AVAILABLE`.  If there are no rooms available at the property for the dates requested, then `NOT_AVAILABLE` will be returned.  If there are available rooms, but none that meet the specific parameters of the search request, then one of the other messages will be returned.
     * Values: AVAILABLE,NOT_AVAILABLE,ERROR,NUMBER_OF_ADULTS_NOT_ACCEPTED,NUMBER_OF_CHILDREN_NOT_ACCEPTED,NUMBER_OF_INFANTS_NOT_ACCEPTED,NUMBER_OF_PERSONS_NOT_ACCEPTED,CHECK_IN_AGE_NOT_ACCEPTED
     */
    enum class Status(val value: kotlin.String) {
        @JsonProperty("AVAILABLE")
        AVAILABLE("AVAILABLE"),

        @JsonProperty("NOT_AVAILABLE")
        NOT_AVAILABLE("NOT_AVAILABLE"),

        @JsonProperty("ERROR")
        ERROR("ERROR"),

        @JsonProperty("NUMBER_OF_ADULTS_NOT_ACCEPTED")
        NUMBER_OF_ADULTS_NOT_ACCEPTED("NUMBER_OF_ADULTS_NOT_ACCEPTED"),

        @JsonProperty("NUMBER_OF_CHILDREN_NOT_ACCEPTED")
        NUMBER_OF_CHILDREN_NOT_ACCEPTED("NUMBER_OF_CHILDREN_NOT_ACCEPTED"),

        @JsonProperty("NUMBER_OF_INFANTS_NOT_ACCEPTED")
        NUMBER_OF_INFANTS_NOT_ACCEPTED("NUMBER_OF_INFANTS_NOT_ACCEPTED"),

        @JsonProperty("NUMBER_OF_PERSONS_NOT_ACCEPTED")
        NUMBER_OF_PERSONS_NOT_ACCEPTED("NUMBER_OF_PERSONS_NOT_ACCEPTED"),

        @JsonProperty("CHECK_IN_AGE_NOT_ACCEPTED")
        CHECK_IN_AGE_NOT_ACCEPTED("CHECK_IN_AGE_NOT_ACCEPTED")
    }

    /**
     * Star rating value of the hotel property.
     * Values: _1_PERIOD0,_1_PERIOD5,_2_PERIOD0,_2_PERIOD5,_3_PERIOD0,_3_PERIOD5,_4_PERIOD0,_4_PERIOD5,_5_PERIOD0
     */
    enum class StarRating(val value: kotlin.String) {
        @JsonProperty("1.0")
        _1_PERIOD0("1.0"),

        @JsonProperty("1.5")
        _1_PERIOD5("1.5"),

        @JsonProperty("2.0")
        _2_PERIOD0("2.0"),

        @JsonProperty("2.5")
        _2_PERIOD5("2.5"),

        @JsonProperty("3.0")
        _3_PERIOD0("3.0"),

        @JsonProperty("3.5")
        _3_PERIOD5("3.5"),

        @JsonProperty("4.0")
        _4_PERIOD0("4.0"),

        @JsonProperty("4.5")
        _4_PERIOD5("4.5"),

        @JsonProperty("5.0")
        _5_PERIOD0("5.0")
    }
}
