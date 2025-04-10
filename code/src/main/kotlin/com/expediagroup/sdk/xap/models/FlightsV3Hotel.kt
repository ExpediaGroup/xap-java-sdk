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
import com.expediagroup.sdk.xap.models.FlightsV3Description
import com.expediagroup.sdk.xap.models.FlightsV3Distance
import com.expediagroup.sdk.xap.models.FlightsV3HotelLocation
import com.expediagroup.sdk.xap.models.FlightsV3HotelPolicies
import com.expediagroup.sdk.xap.models.FlightsV3Link
import com.expediagroup.sdk.xap.models.FlightsV3LodgingAmenity
import com.expediagroup.sdk.xap.models.FlightsV3Media
import com.expediagroup.sdk.xap.models.FlightsV3RoomType
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container for information on each offered hotel.
 * @param id The unique, Expedia-specific hotel property identifier used to designate a single hotel.
 * @param name The common name of the hotel
 * @param localCurrencyCode The Local Currency Code for Hotel (which will be used for any fees that must be paid at the hotel)
 * @param location
 * @param distance
 * @param description
 * @param status  Indicates whether there are available offers at the property during the dates requested, as well as information as to why. Note that pricing will only be present in the API response for a status of 'AVAILABLE'. If there are no rooms available at the property for the dates requested, then 'NOT_AVAILABLE' will be returned. If there are available rooms, but none that meet the specific parameters of the search request, then one of the other messages will be returned
 * @param thumbnailUrl URL of the thumbnail image of the hotel.Note that other images sizes are available - You can find a link to the complete list of Supported Hotel Image Sizes in the Related Links section at the bottom of the page
 * @param starRating Star rating value of the hotel property.
 * @param isOfficialRating Official rating value indicates if the hotel is certified or official. (Required in Australia Point of Sale)
 * @param guestRating Average overall guest rating of the hotel. The value is between 1.0 and 5.0 in 0.1 increments. Higher is better.
 * @param guestReviewCount The total count of guest reviews used to create the average GuestRating above.
 * @param links Container for list of HATEOAS links to Expedia/partner website to complete booking.
 * @param policies
 * @param hotelAmenities Container for all hotel amenities.
 * @param roomAmenities Container for all room amenities.
 * @param roomTypes Container for all of available room types.
 * @param media Container for hotel images
 * @param accessibility The accessibility options available for the room.
 */
data class FlightsV3Hotel(
    // The unique, Expedia-specific hotel property identifier used to designate a single hotel.
    @JsonProperty("Id")
    @field:NotNull
    @field:Valid
    val id: kotlin.String,
    // The common name of the hotel
    @JsonProperty("Name")
    @field:NotNull
    @field:Valid
    val name: kotlin.String,
    // The Local Currency Code for Hotel (which will be used for any fees that must be paid at the hotel)
    @JsonProperty("LocalCurrencyCode")
    @field:NotNull
    @field:Valid
    val localCurrencyCode: kotlin.String,
    @JsonProperty("Location")
    @field:NotNull
    @field:Valid
    val location: FlightsV3HotelLocation,
    @JsonProperty("Distance")
    @field:NotNull
    @field:Valid
    val distance: FlightsV3Distance,
    @JsonProperty("Description")
    @field:NotNull
    @field:Valid
    val description: FlightsV3Description,
    // Indicates whether there are available offers at the property during the dates requested, as well as information as to why. Note that pricing will only be present in the API response for a status of 'AVAILABLE'. If there are no rooms available at the property for the dates requested, then 'NOT_AVAILABLE' will be returned. If there are available rooms, but none that meet the specific parameters of the search request, then one of the other messages will be returned
    @JsonProperty("Status")
    @field:NotNull
    val status: FlightsV3Hotel.Status,
    // URL of the thumbnail image of the hotel.Note that other images sizes are available - You can find a link to the complete list of Supported Hotel Image Sizes in the Related Links section at the bottom of the page
    @JsonProperty("ThumbnailUrl")
    @field:NotNull
    @field:Valid
    val thumbnailUrl: kotlin.String,
    // Star rating value of the hotel property.
    @JsonProperty("StarRating")
    @field:NotNull
    val starRating: FlightsV3Hotel.StarRating,
    // Official rating value indicates if the hotel is certified or official. (Required in Australia Point of Sale)
    @JsonProperty("IsOfficialRating")
    @field:NotNull
    @field:Valid
    val isOfficialRating: kotlin.Boolean,
    // Average overall guest rating of the hotel. The value is between 1.0 and 5.0 in 0.1 increments. Higher is better.
    @JsonProperty("GuestRating")
    @field:NotNull
    @field:Valid
    val guestRating: kotlin.String,
    // The total count of guest reviews used to create the average GuestRating above.
    @JsonProperty("GuestReviewCount")
    val guestReviewCount: kotlin.Int,
    // Container for list of HATEOAS links to Expedia/partner website to complete booking.
    @JsonProperty("Links")
    @field:NotNull
    @field:Valid
    val links: kotlin.collections.Map<kotlin.String, FlightsV3Link>,
    @JsonProperty("Policies")
    @field:NotNull
    @field:Valid
    val policies: FlightsV3HotelPolicies,
    // Container for all hotel amenities.
    @JsonProperty("HotelAmenities")
    @field:NotNull
    @field:Valid
    val hotelAmenities: kotlin.collections
        .List<
            FlightsV3LodgingAmenity
        >,
    // Container for all room amenities.
    @JsonProperty("RoomAmenities")
    @field:NotNull
    @field:Valid
    val roomAmenities: kotlin.collections
        .List<
            FlightsV3LodgingAmenity
        >,
    // Container for all of available room types.
    @JsonProperty("RoomTypes")
    @field:NotNull
    @field:Valid
    val roomTypes: kotlin.collections
        .List<
            FlightsV3RoomType
        >,
    // Container for hotel images
    @JsonProperty("Media")
    @field:Valid
    val media: kotlin.collections.List<FlightsV3Media>? = null,
    // The accessibility options available for the room.
    @JsonProperty("Accessibility")
    @field:Valid
    val accessibility: kotlin.collections.List<kotlin.String>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.String? = null,
        private var name: kotlin.String? = null,
        private var localCurrencyCode: kotlin.String? = null,
        private var location: FlightsV3HotelLocation? = null,
        private var distance: FlightsV3Distance? = null,
        private var description: FlightsV3Description? = null,
        private var status: FlightsV3Hotel.Status? = null,
        private var thumbnailUrl: kotlin.String? = null,
        private var starRating: FlightsV3Hotel.StarRating? = null,
        private var isOfficialRating: kotlin.Boolean? = null,
        private var guestRating: kotlin.String? = null,
        private var guestReviewCount: kotlin.Int? = null,
        private var links: kotlin.collections.Map<kotlin.String, FlightsV3Link>? = null,
        private var policies: FlightsV3HotelPolicies? = null,
        private var hotelAmenities: kotlin.collections.List<FlightsV3LodgingAmenity>? = null,
        private var roomAmenities: kotlin.collections.List<FlightsV3LodgingAmenity>? = null,
        private var roomTypes: kotlin.collections.List<FlightsV3RoomType>? = null,
        private var media: kotlin.collections.List<FlightsV3Media>? = null,
        private var accessibility: kotlin.collections.List<kotlin.String>? = null
    ) {
        fun id(id: kotlin.String) = apply { this.id = id }

        fun name(name: kotlin.String) = apply { this.name = name }

        fun localCurrencyCode(localCurrencyCode: kotlin.String) = apply { this.localCurrencyCode = localCurrencyCode }

        fun location(location: FlightsV3HotelLocation) = apply { this.location = location }

        fun distance(distance: FlightsV3Distance) = apply { this.distance = distance }

        fun description(description: FlightsV3Description) = apply { this.description = description }

        fun status(status: FlightsV3Hotel.Status) = apply { this.status = status }

        fun thumbnailUrl(thumbnailUrl: kotlin.String) = apply { this.thumbnailUrl = thumbnailUrl }

        fun starRating(starRating: FlightsV3Hotel.StarRating) = apply { this.starRating = starRating }

        fun isOfficialRating(isOfficialRating: kotlin.Boolean) = apply { this.isOfficialRating = isOfficialRating }

        fun guestRating(guestRating: kotlin.String) = apply { this.guestRating = guestRating }

        fun guestReviewCount(guestReviewCount: kotlin.Int) = apply { this.guestReviewCount = guestReviewCount }

        fun links(links: kotlin.collections.Map<kotlin.String, FlightsV3Link>) = apply { this.links = links }

        fun policies(policies: FlightsV3HotelPolicies) = apply { this.policies = policies }

        fun hotelAmenities(hotelAmenities: kotlin.collections.List<FlightsV3LodgingAmenity>) = apply { this.hotelAmenities = hotelAmenities }

        fun roomAmenities(roomAmenities: kotlin.collections.List<FlightsV3LodgingAmenity>) = apply { this.roomAmenities = roomAmenities }

        fun roomTypes(roomTypes: kotlin.collections.List<FlightsV3RoomType>) = apply { this.roomTypes = roomTypes }

        fun media(media: kotlin.collections.List<FlightsV3Media>?) = apply { this.media = media }

        fun accessibility(accessibility: kotlin.collections.List<kotlin.String>?) = apply { this.accessibility = accessibility }

        fun build(): FlightsV3Hotel {
            val instance =
                FlightsV3Hotel(
                    id = id!!,
                    name = name!!,
                    localCurrencyCode = localCurrencyCode!!,
                    location = location!!,
                    distance = distance!!,
                    description = description!!,
                    status = status!!,
                    thumbnailUrl = thumbnailUrl!!,
                    starRating = starRating!!,
                    isOfficialRating = isOfficialRating!!,
                    guestRating = guestRating!!,
                    guestReviewCount = guestReviewCount!!,
                    links = links!!,
                    policies = policies!!,
                    hotelAmenities = hotelAmenities!!,
                    roomAmenities = roomAmenities!!,
                    roomTypes = roomTypes!!,
                    media = media,
                    accessibility = accessibility
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightsV3Hotel) {
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
            id = id!!,
            name = name!!,
            localCurrencyCode = localCurrencyCode!!,
            location = location!!,
            distance = distance!!,
            description = description!!,
            status = status!!,
            thumbnailUrl = thumbnailUrl!!,
            starRating = starRating!!,
            isOfficialRating = isOfficialRating!!,
            guestRating = guestRating!!,
            guestReviewCount = guestReviewCount!!,
            links = links!!,
            policies = policies!!,
            hotelAmenities = hotelAmenities!!,
            roomAmenities = roomAmenities!!,
            roomTypes = roomTypes!!,
            media = media,
            accessibility = accessibility
        )

    /**
     *  Indicates whether there are available offers at the property during the dates requested, as well as information as to why. Note that pricing will only be present in the API response for a status of 'AVAILABLE'. If there are no rooms available at the property for the dates requested, then 'NOT_AVAILABLE' will be returned. If there are available rooms, but none that meet the specific parameters of the search request, then one of the other messages will be returned
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
