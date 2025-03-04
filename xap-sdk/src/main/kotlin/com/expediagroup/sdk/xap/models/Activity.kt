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

import com.expediagroup.sdk.rest.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.ActivitiesCancellationPolicy
import com.expediagroup.sdk.xap.models.ActivitiesLink
import com.expediagroup.sdk.xap.models.ActivitiesLocation
import com.expediagroup.sdk.xap.models.ActivitiesMedia
import com.expediagroup.sdk.xap.models.ActivitiesPrice
import com.expediagroup.sdk.xap.models.ActivitiesSupplier
import com.expediagroup.sdk.xap.models.Offer
import com.expediagroup.sdk.xap.models.Redemption
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
* Detailed information on the Activity.
 * @param id The numerical identifier for this particular activity
 * @param title The display title for this activity.
 * @param description The description of the Activity.
 * @param media List of activity Media.
 * @param categories A list of the Activity categories to which this particular activity belongs. Possible values are: Adventures Air, Balloon & Helicopter Tours Attractions Cruises & Water Tours Day Trips & Excursions Food & Drink Hop-on Hop-off Multi-Day & Extended Tours Nightlife Private Tours Private Transfers Shared Transfers Show & Sport Tickets Sightseeing Passes Theme Parks Tours & Sightseeing Walking & Bike Tours Water Activities Wedding Ceremonies Winter Activities
 * @param duration The anticipated time duration for the activity. Using java jdk Duration parsing.
 * @param freeCancellation A boolean value describing whether or not this activity reservation can be cancelled without incurring a penalty.
 * @param price
 * @param supplier
 * @param redemption
 * @param activityLocations Container of location information where activity happens.
 * @param reviewScore The overall Expedia score for the activity.
 * @param reviewCount The number of Expedia reviews that went into the calculation of the ReviewScore.
 * @param links HATEOAS links included in this response.
 * @param cancellationPolicy
 * @param highlights Web formatted statement of the Highlight(s) for the activity.
 * @param termsAndConditions Terms and Conditions for the Activity.
 * @param inclusions Web formatted statement of what is included in the activity
 * @param exclusions Web formatted statement of what is NOT included in the activity
 * @param knowBeforeYouBook Web formatted statement of things that a purchaser should be aware of BEFORE they book this activity.
 * @param knowBeforeYouGo Web formatted statement of things that a purchaser should be aware of BEFORE they go to this activity.
 * @param offers Offers for the activity.
*/
data class Activity(
    // The numerical identifier for this particular activity
    @JsonProperty("Id")
    val id: kotlin.Int,
    // The display title for this activity.
    @JsonProperty("Title")
    @field:NotNull
    @field:Valid
    val title: kotlin.String,
    // The description of the Activity.
    @JsonProperty("Description")
    @field:NotNull
    @field:Valid
    val description: kotlin.String,
    // List of activity Media.
    @JsonProperty("Media")
    @field:NotNull
    @field:Valid
    val media: kotlin.collections
        .List<
            ActivitiesMedia
        >,
    // A list of the Activity categories to which this particular activity belongs. Possible values are: Adventures Air, Balloon & Helicopter Tours Attractions Cruises & Water Tours Day Trips & Excursions Food & Drink Hop-on Hop-off Multi-Day & Extended Tours Nightlife Private Tours Private Transfers Shared Transfers Show & Sport Tickets Sightseeing Passes Theme Parks Tours & Sightseeing Walking & Bike Tours Water Activities Wedding Ceremonies Winter Activities
    @JsonProperty("Categories")
    @field:NotNull
    @field:Valid
    val categories: kotlin.collections
        .List<
            kotlin.String
        >,
    // The anticipated time duration for the activity. Using java jdk Duration parsing.
    @JsonProperty("Duration")
    @field:NotNull
    @field:Valid
    val duration: kotlin.String,
    // A boolean value describing whether or not this activity reservation can be cancelled without incurring a penalty.
    @JsonProperty("FreeCancellation")
    @field:NotNull
    @field:Valid
    val freeCancellation: kotlin.Boolean,
    @JsonProperty("Price")
    @field:NotNull
    @field:Valid
    val price: ActivitiesPrice,
    @JsonProperty("Supplier")
    @field:NotNull
    @field:Valid
    val supplier: ActivitiesSupplier,
    @JsonProperty("Redemption")
    @field:Valid
    val redemption: Redemption? = null,
    // Container of location information where activity happens.
    @JsonProperty("ActivityLocations")
    @field:Valid
    val activityLocations: kotlin.collections.List<ActivitiesLocation>? = null,
    // The overall Expedia score for the activity.
    @JsonProperty("ReviewScore")
    val reviewScore: kotlin.Int? = null,
    // The number of Expedia reviews that went into the calculation of the ReviewScore.
    @JsonProperty("ReviewCount")
    val reviewCount: kotlin.Int? = null,
    // HATEOAS links included in this response.
    @JsonProperty("Links")
    @field:Valid
    val links: kotlin.collections.Map<kotlin.String, ActivitiesLink>? = null,
    @JsonProperty("CancellationPolicy")
    @field:Valid
    val cancellationPolicy: ActivitiesCancellationPolicy? = null,
    // Web formatted statement of the Highlight(s) for the activity.
    @JsonProperty("Highlights")
    @field:Valid
    val highlights: kotlin.collections.List<kotlin.String>? = null,
    // Terms and Conditions for the Activity.
    @JsonProperty("TermsAndConditions")
    @field:Valid
    val termsAndConditions: kotlin.collections.List<kotlin.String>? = null,
    // Web formatted statement of what is included in the activity
    @JsonProperty("Inclusions")
    @field:Valid
    val inclusions: kotlin.collections.List<kotlin.String>? = null,
    // Web formatted statement of what is NOT included in the activity
    @JsonProperty("Exclusions")
    @field:Valid
    val exclusions: kotlin.collections.List<kotlin.String>? = null,
    // Web formatted statement of things that a purchaser should be aware of BEFORE they book this activity.
    @JsonProperty("KnowBeforeYouBook")
    @field:Valid
    val knowBeforeYouBook: kotlin.collections.List<kotlin.String>? = null,
    // Web formatted statement of things that a purchaser should be aware of BEFORE they go to this activity.
    @JsonProperty("KnowBeforeYouGo")
    @field:Valid
    val knowBeforeYouGo: kotlin.collections.List<kotlin.String>? = null,
    // Offers for the activity.
    @JsonProperty("Offers")
    @field:Valid
    val offers: kotlin.collections.List<Offer>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.Int? = null,
        private var title: kotlin.String? = null,
        private var description: kotlin.String? = null,
        private var media: kotlin.collections.List<ActivitiesMedia>? = null,
        private var categories: kotlin.collections.List<kotlin.String>? = null,
        private var duration: kotlin.String? = null,
        private var freeCancellation: kotlin.Boolean? = null,
        private var price: ActivitiesPrice? = null,
        private var supplier: ActivitiesSupplier? = null,
        private var redemption: Redemption? = null,
        private var activityLocations: kotlin.collections.List<ActivitiesLocation>? = null,
        private var reviewScore: kotlin.Int? = null,
        private var reviewCount: kotlin.Int? = null,
        private var links: kotlin.collections.Map<kotlin.String, ActivitiesLink>? = null,
        private var cancellationPolicy: ActivitiesCancellationPolicy? = null,
        private var highlights: kotlin.collections.List<kotlin.String>? = null,
        private var termsAndConditions: kotlin.collections.List<kotlin.String>? = null,
        private var inclusions: kotlin.collections.List<kotlin.String>? = null,
        private var exclusions: kotlin.collections.List<kotlin.String>? = null,
        private var knowBeforeYouBook: kotlin.collections.List<kotlin.String>? = null,
        private var knowBeforeYouGo: kotlin.collections.List<kotlin.String>? = null,
        private var offers: kotlin.collections.List<Offer>? = null
    ) {
        fun id(id: kotlin.Int) = apply { this.id = id }

        fun title(title: kotlin.String) = apply { this.title = title }

        fun description(description: kotlin.String) = apply { this.description = description }

        fun media(media: kotlin.collections.List<ActivitiesMedia>) = apply { this.media = media }

        fun categories(categories: kotlin.collections.List<kotlin.String>) = apply { this.categories = categories }

        fun duration(duration: kotlin.String) = apply { this.duration = duration }

        fun freeCancellation(freeCancellation: kotlin.Boolean) = apply { this.freeCancellation = freeCancellation }

        fun price(price: ActivitiesPrice) = apply { this.price = price }

        fun supplier(supplier: ActivitiesSupplier) = apply { this.supplier = supplier }

        fun redemption(redemption: Redemption?) = apply { this.redemption = redemption }

        fun activityLocations(activityLocations: kotlin.collections.List<ActivitiesLocation>?) = apply { this.activityLocations = activityLocations }

        fun reviewScore(reviewScore: kotlin.Int?) = apply { this.reviewScore = reviewScore }

        fun reviewCount(reviewCount: kotlin.Int?) = apply { this.reviewCount = reviewCount }

        fun links(links: kotlin.collections.Map<kotlin.String, ActivitiesLink>?) = apply { this.links = links }

        fun cancellationPolicy(cancellationPolicy: ActivitiesCancellationPolicy?) = apply { this.cancellationPolicy = cancellationPolicy }

        fun highlights(highlights: kotlin.collections.List<kotlin.String>?) = apply { this.highlights = highlights }

        fun termsAndConditions(termsAndConditions: kotlin.collections.List<kotlin.String>?) = apply { this.termsAndConditions = termsAndConditions }

        fun inclusions(inclusions: kotlin.collections.List<kotlin.String>?) = apply { this.inclusions = inclusions }

        fun exclusions(exclusions: kotlin.collections.List<kotlin.String>?) = apply { this.exclusions = exclusions }

        fun knowBeforeYouBook(knowBeforeYouBook: kotlin.collections.List<kotlin.String>?) = apply { this.knowBeforeYouBook = knowBeforeYouBook }

        fun knowBeforeYouGo(knowBeforeYouGo: kotlin.collections.List<kotlin.String>?) = apply { this.knowBeforeYouGo = knowBeforeYouGo }

        fun offers(offers: kotlin.collections.List<Offer>?) = apply { this.offers = offers }

        fun build(): Activity {
            val instance =
                Activity(
                    id = id!!,
                    title = title!!,
                    description = description!!,
                    media = media!!,
                    categories = categories!!,
                    duration = duration!!,
                    freeCancellation = freeCancellation!!,
                    price = price!!,
                    supplier = supplier!!,
                    redemption = redemption,
                    activityLocations = activityLocations,
                    reviewScore = reviewScore,
                    reviewCount = reviewCount,
                    links = links,
                    cancellationPolicy = cancellationPolicy,
                    highlights = highlights,
                    termsAndConditions = termsAndConditions,
                    inclusions = inclusions,
                    exclusions = exclusions,
                    knowBeforeYouBook = knowBeforeYouBook,
                    knowBeforeYouGo = knowBeforeYouGo,
                    offers = offers
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Activity) {
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
            title = title!!,
            description = description!!,
            media = media!!,
            categories = categories!!,
            duration = duration!!,
            freeCancellation = freeCancellation!!,
            price = price!!,
            supplier = supplier!!,
            redemption = redemption,
            activityLocations = activityLocations,
            reviewScore = reviewScore,
            reviewCount = reviewCount,
            links = links,
            cancellationPolicy = cancellationPolicy,
            highlights = highlights,
            termsAndConditions = termsAndConditions,
            inclusions = inclusions,
            exclusions = exclusions,
            knowBeforeYouBook = knowBeforeYouBook,
            knowBeforeYouGo = knowBeforeYouGo,
            offers = offers
        )
}
