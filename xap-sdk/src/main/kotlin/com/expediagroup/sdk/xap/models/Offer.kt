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

import com.expediagroup.sdk.xap.models.ActivitiesLink
import com.expediagroup.sdk.xap.models.ActivitiesPrice
import com.expediagroup.sdk.xap.models.AvailableTimeSlot
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Offers for the activity.
 * @param id The numerical identifier for the offer.
 * @param title A descriptive title for this offer.
 * @param duration The anticipated time duration for the activity, e xpressed using Java JDK duration format.
 * @param availableTimeSlots The list of available Time Slots for the activity.
 * @param offerPrice
 * @param description Description of this offer.
 * @param links Container of HATEOAS URL's
 */
data class Offer(
    // The numerical identifier for the offer.
    @JsonProperty("Id")
    val id: kotlin.Int,
    // A descriptive title for this offer.
    @JsonProperty("Title")
    val title: kotlin.String,
    // The anticipated time duration for the activity, e xpressed using Java JDK duration format.
    @JsonProperty("Duration")
    val duration: kotlin.String,
    // The list of available Time Slots for the activity.
    @JsonProperty("AvailableTimeSlots")
    val availableTimeSlots: kotlin.collections
        .List<
            AvailableTimeSlot,
        >,
    @JsonProperty("OfferPrice")
    val offerPrice: ActivitiesPrice,
    // Description of this offer.
    @JsonProperty("Description")
    val description: kotlin.String? = null,
    // Container of HATEOAS URL's
    @JsonProperty("Links")
    val links: kotlin.collections.Map<kotlin.String, ActivitiesLink>? = null,
) {
    init {
        require(id != null) { "id must not be null" }

        require(title != null) { "title must not be null" }

        require(duration != null) { "duration must not be null" }

        require(availableTimeSlots != null) { "availableTimeSlots must not be null" }

        require(offerPrice != null) { "offerPrice must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.Int? = null,
        private var title: kotlin.String? = null,
        private var duration: kotlin.String? = null,
        private var availableTimeSlots: kotlin.collections.List<AvailableTimeSlot>? = null,
        private var offerPrice: ActivitiesPrice? = null,
        private var description: kotlin.String? = null,
        private var links: kotlin.collections.Map<kotlin.String, ActivitiesLink>? = null,
    ) {
        fun id(id: kotlin.Int) = apply { this.id = id }

        fun title(title: kotlin.String) = apply { this.title = title }

        fun duration(duration: kotlin.String) = apply { this.duration = duration }

        fun availableTimeSlots(availableTimeSlots: kotlin.collections.List<AvailableTimeSlot>) = apply { this.availableTimeSlots = availableTimeSlots }

        fun offerPrice(offerPrice: ActivitiesPrice) = apply { this.offerPrice = offerPrice }

        fun description(description: kotlin.String?) = apply { this.description = description }

        fun links(links: kotlin.collections.Map<kotlin.String, ActivitiesLink>?) = apply { this.links = links }

        fun build(): Offer {
            val instance =
                Offer(
                    id = id!!,
                    title = title!!,
                    duration = duration!!,
                    availableTimeSlots = availableTimeSlots!!,
                    offerPrice = offerPrice!!,
                    description = description,
                    links = links,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            id = id!!,
            title = title!!,
            duration = duration!!,
            availableTimeSlots = availableTimeSlots!!,
            offerPrice = offerPrice!!,
            description = description,
            links = links,
        )
}
