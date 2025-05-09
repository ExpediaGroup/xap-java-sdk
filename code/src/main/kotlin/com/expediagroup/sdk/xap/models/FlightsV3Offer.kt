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
import com.expediagroup.sdk.xap.models.FlightsV3Link
import com.expediagroup.sdk.xap.models.FlightsV3OfferRefundPenaltyInner
import com.expediagroup.sdk.xap.models.OfferPrice
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container for list of air offers. An offer gives total trip details including flight and pricing information.
 * @param splitTicket True if more that one ticket will be issued for this offer per passenger. False if only one ticket will be issued per passenger.
 * @param links Container for deeplink URL information.
 * @param segmentIds Container for list of segment ids in a particular offer. For Opaque flight, no segment will be present.
 * @param offerPrice
 * @param refundable True if flight is refundable, False if it's not refundable.
 * @param international True, if flight is international. False, if flight departs and arrives within the same country
 * @param offerToken Unique key to identify each offer.
 * @param referenceId Unique key to identify matching private fares.
 * @param metaApiBook To indicate meta partners whether the air product is available for TAAS instant book.
 * @param opaqueFlight Indicate whether the air product is a opaque flight product or not. If true, then there will be no Segments node for this air product.
 * @param free24HourCancellation True if Booking can be cancelled  within 24 hours of booking.
 * @param vfopKey Key generated for Valid form of payment
 * @param refundPenalty Contains refund penalty information
 * @param ticketType Type of ticket being issued
 */
data class FlightsV3Offer(
    // True if more that one ticket will be issued for this offer per passenger. False if only one ticket will be issued per passenger.
    @JsonProperty("SplitTicket")
    @field:NotNull
    @field:Valid
    val splitTicket: kotlin.Boolean,
    // Container for deeplink URL information.
    @JsonProperty("Links")
    @field:NotNull
    @field:Valid
    val links: kotlin.collections.Map<kotlin.String, FlightsV3Link>,
    // Container for list of segment ids in a particular offer. For Opaque flight, no segment will be present.
    @JsonProperty("SegmentIds")
    @field:NotNull
    @field:Valid
    val segmentIds: kotlin.collections
        .List<
            kotlin.String
        >,
    @JsonProperty("OfferPrice")
    @field:NotNull
    @field:Valid
    val offerPrice: OfferPrice,
    // True if flight is refundable, False if it's not refundable.
    @JsonProperty("Refundable")
    @field:NotNull
    @field:Valid
    val refundable: kotlin.Boolean,
    // True, if flight is international. False, if flight departs and arrives within the same country
    @JsonProperty("International")
    @field:NotNull
    @field:Valid
    val international: kotlin.Boolean,
    // Unique key to identify each offer.
    @JsonProperty("offerToken")
    @field:Valid
    val offerToken: kotlin.String? = null,
    // Unique key to identify matching private fares.
    @JsonProperty("ReferenceId")
    @field:Valid
    val referenceId: kotlin.String? = null,
    // To indicate meta partners whether the air product is available for TAAS instant book.
    @JsonProperty("MetaApiBook")
    @field:Valid
    val metaApiBook: kotlin.Boolean? = null,
    // Indicate whether the air product is a opaque flight product or not. If true, then there will be no Segments node for this air product.
    @JsonProperty("OpaqueFlight")
    @field:Valid
    val opaqueFlight: kotlin.Boolean? = null,
    // True if Booking can be cancelled  within 24 hours of booking.
    @JsonProperty("Free24HourCancellation")
    @field:Valid
    val free24HourCancellation: kotlin.Boolean? = null,
    // Key generated for Valid form of payment
    @JsonProperty("VfopKey")
    @field:Valid
    val vfopKey: kotlin.String? = null,
    // Contains refund penalty information
    @JsonProperty("RefundPenalty")
    @field:Valid
    val refundPenalty: kotlin.collections.List<FlightsV3OfferRefundPenaltyInner>? = null,
    // Type of ticket being issued
    @JsonProperty("TicketType")
    @field:Valid
    val ticketType: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var splitTicket: kotlin.Boolean? = null,
        private var links: kotlin.collections.Map<kotlin.String, FlightsV3Link>? = null,
        private var segmentIds: kotlin.collections.List<kotlin.String>? = null,
        private var offerPrice: OfferPrice? = null,
        private var refundable: kotlin.Boolean? = null,
        private var international: kotlin.Boolean? = null,
        private var offerToken: kotlin.String? = null,
        private var referenceId: kotlin.String? = null,
        private var metaApiBook: kotlin.Boolean? = null,
        private var opaqueFlight: kotlin.Boolean? = null,
        private var free24HourCancellation: kotlin.Boolean? = null,
        private var vfopKey: kotlin.String? = null,
        private var refundPenalty: kotlin.collections.List<FlightsV3OfferRefundPenaltyInner>? = null,
        private var ticketType: kotlin.String? = null
    ) {
        fun splitTicket(splitTicket: kotlin.Boolean) = apply { this.splitTicket = splitTicket }

        fun links(links: kotlin.collections.Map<kotlin.String, FlightsV3Link>) = apply { this.links = links }

        fun segmentIds(segmentIds: kotlin.collections.List<kotlin.String>) = apply { this.segmentIds = segmentIds }

        fun offerPrice(offerPrice: OfferPrice) = apply { this.offerPrice = offerPrice }

        fun refundable(refundable: kotlin.Boolean) = apply { this.refundable = refundable }

        fun international(international: kotlin.Boolean) = apply { this.international = international }

        fun offerToken(offerToken: kotlin.String?) = apply { this.offerToken = offerToken }

        fun referenceId(referenceId: kotlin.String?) = apply { this.referenceId = referenceId }

        fun metaApiBook(metaApiBook: kotlin.Boolean?) = apply { this.metaApiBook = metaApiBook }

        fun opaqueFlight(opaqueFlight: kotlin.Boolean?) = apply { this.opaqueFlight = opaqueFlight }

        fun free24HourCancellation(free24HourCancellation: kotlin.Boolean?) = apply { this.free24HourCancellation = free24HourCancellation }

        fun vfopKey(vfopKey: kotlin.String?) = apply { this.vfopKey = vfopKey }

        fun refundPenalty(refundPenalty: kotlin.collections.List<FlightsV3OfferRefundPenaltyInner>?) = apply { this.refundPenalty = refundPenalty }

        fun ticketType(ticketType: kotlin.String?) = apply { this.ticketType = ticketType }

        fun build(): FlightsV3Offer {
            val instance =
                FlightsV3Offer(
                    splitTicket = splitTicket!!,
                    links = links!!,
                    segmentIds = segmentIds!!,
                    offerPrice = offerPrice!!,
                    refundable = refundable!!,
                    international = international!!,
                    offerToken = offerToken,
                    referenceId = referenceId,
                    metaApiBook = metaApiBook,
                    opaqueFlight = opaqueFlight,
                    free24HourCancellation = free24HourCancellation,
                    vfopKey = vfopKey,
                    refundPenalty = refundPenalty,
                    ticketType = ticketType
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightsV3Offer) {
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
            splitTicket = splitTicket!!,
            links = links!!,
            segmentIds = segmentIds!!,
            offerPrice = offerPrice!!,
            refundable = refundable!!,
            international = international!!,
            offerToken = offerToken,
            referenceId = referenceId,
            metaApiBook = metaApiBook,
            opaqueFlight = opaqueFlight,
            free24HourCancellation = free24HourCancellation,
            vfopKey = vfopKey,
            refundPenalty = refundPenalty,
            ticketType = ticketType
        )
}
