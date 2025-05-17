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
package com.expediagroup.sdk.xap.models

import com.expediagroup.sdk.xap.models.FareOptions
import com.expediagroup.sdk.xap.models.FlightDetailsRefundPenalty
import com.expediagroup.sdk.xap.models.FlightsV3Link
import com.expediagroup.sdk.xap.models.OfferPrice
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Contains list of all the available alternate fare upsell/downsell offers.
 * @param offerToken Unique key to identify each offer.
 * @param splitTicket True if more that one ticket will be issued for this offer per passenger. False if only one ticket will be issued per passenger.
 * @param opaqueFlight Indicate whether the air product is a opaque flight product or not. If true, then there will be no Segments node for this air product.
 * @param merchantName All Merchant name appending them together with a ?*?
 * @param links Container for deeplink URL information.
 * @param offerPrice
 * @param refundable True if flight is refundable, False if it's not refundable.
 * @param international True, if flight is international. False, if flight departs and arrives within the same country
 * @param ticketType Type of ticket being issued
 * @param fareOptions Contains list of fare options
 * @param undisclosedGenderSupported Whether to support undisclosed gender? True = support False = not support
 * @param unspecifiedGenderSupported Whether to support unspecified gender? True = support False = not support
 * @param referenceId Key that allows the user to create a package using this flight.
 * @param metaApiBook To indicate meta partners whether the air product is available for TAAS instant book.
 * @param free24HourCancellation True if Booking can be cancelled  within 24 hours of booking.
 * @param segmentIds Container for list of segment ids in a particular offer. For Opaque flight, no segment will be present.
 * @param vfopKey Key generated for Valid form of payment
 * @param refundPenalty Contains refund penalty information
 */
data class FlightDetailsOffer(
    // Unique key to identify each offer.
    @JsonProperty("offerToken")
    val offerToken: kotlin.String,
    // True if more that one ticket will be issued for this offer per passenger. False if only one ticket will be issued per passenger.
    @JsonProperty("SplitTicket")
    val splitTicket: kotlin.Boolean,
    // Indicate whether the air product is a opaque flight product or not. If true, then there will be no Segments node for this air product.
    @JsonProperty("OpaqueFlight")
    val opaqueFlight: kotlin.Boolean,
    // All Merchant name appending them together with a ?*?
    @JsonProperty("MerchantName")
    val merchantName: kotlin.String,
    // Container for deeplink URL information.
    @JsonProperty("Links")
    val links: kotlin.collections.Map<kotlin.String, FlightsV3Link>,
    @JsonProperty("OfferPrice")
    val offerPrice: OfferPrice,
    // True if flight is refundable, False if it's not refundable.
    @JsonProperty("Refundable")
    val refundable: kotlin.Boolean,
    // True, if flight is international. False, if flight departs and arrives within the same country
    @JsonProperty("International")
    val international: kotlin.Boolean,
    // Type of ticket being issued
    @JsonProperty("TicketType")
    val ticketType: kotlin.String,
    // Contains list of fare options
    @JsonProperty("FareOptions")
    val fareOptions: kotlin.collections
        .List<
            FareOptions,
        >,
    // Whether to support undisclosed gender? True = support False = not support
    @JsonProperty("UndisclosedGenderSupported")
    val undisclosedGenderSupported: kotlin.Boolean,
    // Whether to support unspecified gender? True = support False = not support
    @JsonProperty("UnspecifiedGenderSupported")
    val unspecifiedGenderSupported: kotlin.Boolean,
    // Key that allows the user to create a package using this flight.
    @JsonProperty("ReferenceId")
    val referenceId: kotlin.String? = null,
    // To indicate meta partners whether the air product is available for TAAS instant book.
    @JsonProperty("MetaApiBook")
    val metaApiBook: kotlin.Boolean? = null,
    // True if Booking can be cancelled  within 24 hours of booking.
    @JsonProperty("Free24HourCancellation")
    val free24HourCancellation: kotlin.Boolean? = null,
    // Container for list of segment ids in a particular offer. For Opaque flight, no segment will be present.
    @JsonProperty("SegmentIds")
    val segmentIds: kotlin.collections.List<kotlin.String>? = null,
    // Key generated for Valid form of payment
    @JsonProperty("VfopKey")
    val vfopKey: kotlin.String? = null,
    // Contains refund penalty information
    @JsonProperty("RefundPenalty")
    val refundPenalty: kotlin.collections.List<FlightDetailsRefundPenalty>? = null,
) {
    init {
        require(offerToken != null) { "offerToken must not be null" }

        require(splitTicket != null) { "splitTicket must not be null" }

        require(opaqueFlight != null) { "opaqueFlight must not be null" }

        require(merchantName != null) { "merchantName must not be null" }

        require(links != null) { "links must not be null" }

        require(offerPrice != null) { "offerPrice must not be null" }

        require(refundable != null) { "refundable must not be null" }

        require(international != null) { "international must not be null" }

        require(ticketType != null) { "ticketType must not be null" }

        require(fareOptions != null) { "fareOptions must not be null" }

        require(undisclosedGenderSupported != null) { "undisclosedGenderSupported must not be null" }

        require(unspecifiedGenderSupported != null) { "unspecifiedGenderSupported must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var offerToken: kotlin.String? = null,
        private var splitTicket: kotlin.Boolean? = null,
        private var opaqueFlight: kotlin.Boolean? = null,
        private var merchantName: kotlin.String? = null,
        private var links: kotlin.collections.Map<kotlin.String, FlightsV3Link>? = null,
        private var offerPrice: OfferPrice? = null,
        private var refundable: kotlin.Boolean? = null,
        private var international: kotlin.Boolean? = null,
        private var ticketType: kotlin.String? = null,
        private var fareOptions: kotlin.collections.List<FareOptions>? = null,
        private var undisclosedGenderSupported: kotlin.Boolean? = null,
        private var unspecifiedGenderSupported: kotlin.Boolean? = null,
        private var referenceId: kotlin.String? = null,
        private var metaApiBook: kotlin.Boolean? = null,
        private var free24HourCancellation: kotlin.Boolean? = null,
        private var segmentIds: kotlin.collections.List<kotlin.String>? = null,
        private var vfopKey: kotlin.String? = null,
        private var refundPenalty: kotlin.collections.List<FlightDetailsRefundPenalty>? = null,
    ) {
        fun offerToken(offerToken: kotlin.String) = apply { this.offerToken = offerToken }

        fun splitTicket(splitTicket: kotlin.Boolean) = apply { this.splitTicket = splitTicket }

        fun opaqueFlight(opaqueFlight: kotlin.Boolean) = apply { this.opaqueFlight = opaqueFlight }

        fun merchantName(merchantName: kotlin.String) = apply { this.merchantName = merchantName }

        fun links(links: kotlin.collections.Map<kotlin.String, FlightsV3Link>) = apply { this.links = links }

        fun offerPrice(offerPrice: OfferPrice) = apply { this.offerPrice = offerPrice }

        fun refundable(refundable: kotlin.Boolean) = apply { this.refundable = refundable }

        fun international(international: kotlin.Boolean) = apply { this.international = international }

        fun ticketType(ticketType: kotlin.String) = apply { this.ticketType = ticketType }

        fun fareOptions(fareOptions: kotlin.collections.List<FareOptions>) = apply { this.fareOptions = fareOptions }

        fun undisclosedGenderSupported(undisclosedGenderSupported: kotlin.Boolean) = apply { this.undisclosedGenderSupported = undisclosedGenderSupported }

        fun unspecifiedGenderSupported(unspecifiedGenderSupported: kotlin.Boolean) = apply { this.unspecifiedGenderSupported = unspecifiedGenderSupported }

        fun referenceId(referenceId: kotlin.String?) = apply { this.referenceId = referenceId }

        fun metaApiBook(metaApiBook: kotlin.Boolean?) = apply { this.metaApiBook = metaApiBook }

        fun free24HourCancellation(free24HourCancellation: kotlin.Boolean?) = apply { this.free24HourCancellation = free24HourCancellation }

        fun segmentIds(segmentIds: kotlin.collections.List<kotlin.String>?) = apply { this.segmentIds = segmentIds }

        fun vfopKey(vfopKey: kotlin.String?) = apply { this.vfopKey = vfopKey }

        fun refundPenalty(refundPenalty: kotlin.collections.List<FlightDetailsRefundPenalty>?) = apply { this.refundPenalty = refundPenalty }

        fun build(): FlightDetailsOffer {
            val instance =
                FlightDetailsOffer(
                    offerToken = offerToken!!,
                    splitTicket = splitTicket!!,
                    opaqueFlight = opaqueFlight!!,
                    merchantName = merchantName!!,
                    links = links!!,
                    offerPrice = offerPrice!!,
                    refundable = refundable!!,
                    international = international!!,
                    ticketType = ticketType!!,
                    fareOptions = fareOptions!!,
                    undisclosedGenderSupported = undisclosedGenderSupported!!,
                    unspecifiedGenderSupported = unspecifiedGenderSupported!!,
                    referenceId = referenceId,
                    metaApiBook = metaApiBook,
                    free24HourCancellation = free24HourCancellation,
                    segmentIds = segmentIds,
                    vfopKey = vfopKey,
                    refundPenalty = refundPenalty,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            offerToken = offerToken!!,
            splitTicket = splitTicket!!,
            opaqueFlight = opaqueFlight!!,
            merchantName = merchantName!!,
            links = links!!,
            offerPrice = offerPrice!!,
            refundable = refundable!!,
            international = international!!,
            ticketType = ticketType!!,
            fareOptions = fareOptions!!,
            undisclosedGenderSupported = undisclosedGenderSupported!!,
            unspecifiedGenderSupported = unspecifiedGenderSupported!!,
            referenceId = referenceId,
            metaApiBook = metaApiBook,
            free24HourCancellation = free24HourCancellation,
            segmentIds = segmentIds,
            vfopKey = vfopKey,
            refundPenalty = refundPenalty,
        )
}
