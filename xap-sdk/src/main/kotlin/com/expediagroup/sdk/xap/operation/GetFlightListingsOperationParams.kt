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
package com.expediagroup.sdk.xap.operations

import com.expediagroup.sdk.core.http.Headers
import com.expediagroup.sdk.rest.model.UrlQueryParam
import com.expediagroup.sdk.rest.util.UrlQueryParamStringifier.explode
import com.expediagroup.sdk.rest.util.swaggerCollectionFormatStringifier
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

/**
 * @property partnerTransactionID Partner-generated identifier.
 * @property adult Number of Adult Travelers. Either one adult or one senior per itinerary is mandatory
 * @property senior Number of Senior (age > 65) Travelers
 * @property childrenAges Comma-separated list of the ages of all child travelers (ages 2 - 17).
 * @property infantInLap Number of Infant travelers without a reserved seat. Age should be less than 2
 * @property infantInSeat Number of Infant travelers with reserved seat. Age should be less than 2
 * @property segment1Origin 3-letter IATA Airport code/Location name from where the passenger is departing.
 * @property segment1Destination 3-letter IATA Airport code/Location name from where the passenger is arriving.
 * @property segment1DepartureDate Date, in ISO format [YYYY-MM-DD], on which customer wants to depart.
 * @property segment1DepartureStartTime Lower limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
 * @property segment1DepartureEndTime Upper limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
 * @property segment2Origin 3-letter IATA Airport code/Location name from where the passenger is departing.
 * @property segment2Destination 3-letter IATA Airport code/Location name from where the passenger is arriving.
 * @property segment2DepartureDate Date, in ISO format [YYYY-MM-DD], on which customer wants to depart.
 * @property segment2DepartureStartTime Lower limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
 * @property segment2DepartureEndTime Upper limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
 * @property segment3Origin 3-letter IATA Airport code/Location name from where the passenger is departing.
 * @property segment3Destination 3-letter IATA Airport code/Location name from where the passenger is arriving.
 * @property segment3DepartureDate Date, in ISO format [YYYY-MM-DD], on which customer wants to depart.
 * @property segment3DepartureStartTime Lower limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
 * @property segment3DepartureEndTime Upper limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
 * @property segment4Origin 3-letter IATA Airport code/Location name from where the passenger is departing.
 * @property segment4Destination 3-letter IATA Airport code/Location name from where the passenger is arriving.
 * @property segment4DepartureDate Date, in ISO format [YYYY-MM-DD], on which customer wants to depart.
 * @property segment4DepartureStartTime Lower limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
 * @property segment4DepartureEndTime Upper limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
 * @property segment5Origin 3-letter IATA Airport code/Location name from where the passenger is departing.
 * @property segment5Destination 3-letter IATA Airport code/Location name from where the passenger is arriving.
 * @property segment5DepartureDate Date, in ISO format [YYYY-MM-DD], on which customer wants to depart.
 * @property segment5DepartureStartTime Lower limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
 * @property segment5DepartureEndTime Upper limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
 * @property segment6Origin 3-letter IATA Airport code/Location name from where the passenger is departing.
 * @property segment6Destination 3-letter IATA Airport code/Location name from where the passenger is arriving.
 * @property segment6DepartureDate Date, in ISO format [YYYY-MM-DD], on which customer wants to depart.
 * @property segment6DepartureStartTime Lower limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
 * @property segment6DepartureEndTime Upper limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
 * @property locale Indicates the language and country with which the user would like to see any translated information.
 * @property cabinClass The desired cabin classes that the user would like to see offers for. Options can be: economy | first | business | premiumeconomy
 * @property numberOfStops Filter for the number of stops the user would like to see offers for. A value of 0 returns only non-stop flights in the search response, and a value of 1 returns offers
 * @property sortType Sort the search results according to one selected category. Only sort by price is supported at this time. Note: default = Price
 * @property limit The maximum number of Flight offers returned in the response. Must be an integer value greater than 0.Note: default = 1600
 * @property selectedCarriers Adding comma-separated list of IATA or Expedia airline codes will limit the search results to include flight offers only with the selected carriers.
 * @property accountCodes AlphaNumeric characters. Different codes separated by comma
 * @property agent Designates whether a telesales agent was involved in the transaction. true = telesales agent involved false = no telesales agent involved
 * @property links Comma-separated list that indicates which HATEOAS links should be included in the response. WD (Website Details Page - included by default) AD (Details API link) ABF (Baggage Fee API) ASM (Seat Map API) WPS (Web Package Search)
 * @property refundable Refundable solutions will be returned if and only if we pass refundable as true in the request.(refundable=true). (Not Yet Supported in Production)
 * @property filterNearByAirport Filters nearby airports ensuring only results from the requests airport code are returned. This request param is valid only for AirportCode-based searches. Note: default = false
 * @property filterBasicEconomy Filters out all the Basic Economy fare solutions in the flight search response. Note: default = false
 * @property anchorBy Designates that the user is doing a Multi-step Search. Possible values are: segment1 | segment2 | segment3 | segment4 | segment5
 * @property selectedOffer Captures the previously selected flight segments during a Multi-step Search.
 * @property filterNonFlightOffers If set to true, this parameter filters out all non-Flight Offers (offers with any of the legs comprising transit via Train, Bus or Boat) from flight search response. If the parameter is not present or is set to false, then Flight Offers may contain travel legs via means other than flight (Train, Bus or Boat). Default value is false
 * @property enableSplitTicket if set to false, this parameter will filter out all the split ticket solutions from the Flight Offers. If set to true (by default it will be true), API response will include split ticket solutions if split ticket is enabled at key configuration level as well.
 */
@JsonDeserialize(builder = GetFlightListingsOperationParams.Builder::class)
data class GetFlightListingsOperationParams(
    val partnerTransactionID: kotlin.String,
    val adult: kotlin.Int? =
        null,
    val senior: kotlin.Int? =
        null,
    val childrenAges: kotlin.collections.List<
        kotlin.Int,
    >? =
        null,
    val infantInLap: kotlin.Int? =
        null,
    val infantInSeat: kotlin.Int? =
        null,
    val segment1Origin: kotlin.String,
    val segment1Destination: kotlin.String,
    val segment1DepartureDate: java.time.LocalDate,
    val segment1DepartureStartTime: kotlin.String? =
        null,
    val segment1DepartureEndTime: kotlin.String? =
        null,
    val segment2Origin: kotlin.String? =
        null,
    val segment2Destination: kotlin.String? =
        null,
    val segment2DepartureDate: java.time.LocalDate? =
        null,
    val segment2DepartureStartTime: kotlin.String? =
        null,
    val segment2DepartureEndTime: kotlin.String? =
        null,
    val segment3Origin: kotlin.String? =
        null,
    val segment3Destination: kotlin.String? =
        null,
    val segment3DepartureDate: java.time.LocalDate? =
        null,
    val segment3DepartureStartTime: kotlin.String? =
        null,
    val segment3DepartureEndTime: kotlin.String? =
        null,
    val segment4Origin: kotlin.String? =
        null,
    val segment4Destination: kotlin.String? =
        null,
    val segment4DepartureDate: java.time.LocalDate? =
        null,
    val segment4DepartureStartTime: kotlin.String? =
        null,
    val segment4DepartureEndTime: kotlin.String? =
        null,
    val segment5Origin: kotlin.String? =
        null,
    val segment5Destination: kotlin.String? =
        null,
    val segment5DepartureDate: java.time.LocalDate? =
        null,
    val segment5DepartureStartTime: kotlin.String? =
        null,
    val segment5DepartureEndTime: kotlin.String? =
        null,
    val segment6Origin: kotlin.String? =
        null,
    val segment6Destination: kotlin.String? =
        null,
    val segment6DepartureDate: java.time.LocalDate? =
        null,
    val segment6DepartureStartTime: kotlin.String? =
        null,
    val segment6DepartureEndTime: kotlin.String? =
        null,
    val locale: kotlin.String? =
        null,
    val cabinClass: GetFlightListingsOperationParams.CabinClass? =
        null,
    val numberOfStops: kotlin.Int? =
        null,
    val sortType: kotlin.String? =
        null,
    val limit: kotlin.Int? =
        null,
    val selectedCarriers: kotlin.collections.List<
        kotlin.String,
    >? =
        null,
    val accountCodes: kotlin.collections.List<
        kotlin.String,
    >? =
        null,
    val agent: kotlin.Boolean? =
        null,
    val links: kotlin.collections.List<
        GetFlightListingsOperationParams.Links,
    >? =
        null,
    val refundable: kotlin.Boolean? =
        null,
    val filterNearByAirport: kotlin.Boolean? =
        null,
    val filterBasicEconomy: kotlin.Boolean? =
        null,
    val anchorBy: GetFlightListingsOperationParams.AnchorBy? =
        null,
    val selectedOffer: kotlin.String? =
        null,
    val filterNonFlightOffers: kotlin.Boolean? =
        null,
    val enableSplitTicket: kotlin.Boolean? =
        null,
) {
    init {
        require(partnerTransactionID != null) { "partnerTransactionID must not be null" }

        require(segment1Origin != null) { "segment1Origin must not be null" }

        require(segment1Destination != null) { "segment1Destination must not be null" }

        require(segment1DepartureDate != null) { "segment1DepartureDate must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    enum class CabinClass(
        val value: kotlin.String,
    ) {
        ECONOMY("economy"),
        FIRST("first"),
        BUSINESS("business"),
        PREMIUMECONOMY("premiumeconomy"),
    }

    enum class Links(
        val value: kotlin.String,
    ) {
        WD("WD"),
        AD("AD"),
        ABF("ABF"),
        ASM("ASM"),
        WPS("WPS"),
    }

    enum class AnchorBy(
        val value: kotlin.String,
    ) {
        SEGMENT1("segment1"),
        SEGMENT2("segment2"),
        SEGMENT3("segment3"),
        SEGMENT4("segment4"),
        SEGMENT5("segment5"),
    }

    class Builder(
        @JsonProperty("Partner-Transaction-ID") private var partnerTransactionID: kotlin.String? = null,
        @JsonProperty("adult") private var adult: kotlin.Int? = null,
        @JsonProperty("senior") private var senior: kotlin.Int? = null,
        @JsonProperty("childrenAges") private var childrenAges: kotlin.collections.List<
            kotlin.Int,
        >? = null,
        @JsonProperty("infantInLap") private var infantInLap: kotlin.Int? = null,
        @JsonProperty("infantInSeat") private var infantInSeat: kotlin.Int? = null,
        @JsonProperty("segment1.origin") private var segment1Origin: kotlin.String? = null,
        @JsonProperty("segment1.destination") private var segment1Destination: kotlin.String? = null,
        @JsonProperty("segment1.departureDate") private var segment1DepartureDate: java.time.LocalDate? = null,
        @JsonProperty("segment1.departureStartTime") private var segment1DepartureStartTime: kotlin.String? = null,
        @JsonProperty("segment1.departureEndTime") private var segment1DepartureEndTime: kotlin.String? = null,
        @JsonProperty("segment2.origin") private var segment2Origin: kotlin.String? = null,
        @JsonProperty("segment2.destination") private var segment2Destination: kotlin.String? = null,
        @JsonProperty("segment2.departureDate") private var segment2DepartureDate: java.time.LocalDate? = null,
        @JsonProperty("segment2.departureStartTime") private var segment2DepartureStartTime: kotlin.String? = null,
        @JsonProperty("segment2.departureEndTime") private var segment2DepartureEndTime: kotlin.String? = null,
        @JsonProperty("segment3.origin") private var segment3Origin: kotlin.String? = null,
        @JsonProperty("segment3.destination") private var segment3Destination: kotlin.String? = null,
        @JsonProperty("segment3.departureDate") private var segment3DepartureDate: java.time.LocalDate? = null,
        @JsonProperty("segment3.departureStartTime") private var segment3DepartureStartTime: kotlin.String? = null,
        @JsonProperty("segment3.departureEndTime") private var segment3DepartureEndTime: kotlin.String? = null,
        @JsonProperty("segment4.origin") private var segment4Origin: kotlin.String? = null,
        @JsonProperty("segment4.destination") private var segment4Destination: kotlin.String? = null,
        @JsonProperty("segment4.departureDate") private var segment4DepartureDate: java.time.LocalDate? = null,
        @JsonProperty("segment4.departureStartTime") private var segment4DepartureStartTime: kotlin.String? = null,
        @JsonProperty("segment4.departureEndTime") private var segment4DepartureEndTime: kotlin.String? = null,
        @JsonProperty("segment5.origin") private var segment5Origin: kotlin.String? = null,
        @JsonProperty("segment5.destination") private var segment5Destination: kotlin.String? = null,
        @JsonProperty("segment5.departureDate") private var segment5DepartureDate: java.time.LocalDate? = null,
        @JsonProperty("segment5.departureStartTime") private var segment5DepartureStartTime: kotlin.String? = null,
        @JsonProperty("segment5.departureEndTime") private var segment5DepartureEndTime: kotlin.String? = null,
        @JsonProperty("segment6.origin") private var segment6Origin: kotlin.String? = null,
        @JsonProperty("segment6.destination") private var segment6Destination: kotlin.String? = null,
        @JsonProperty("segment6.departureDate") private var segment6DepartureDate: java.time.LocalDate? = null,
        @JsonProperty("segment6.departureStartTime") private var segment6DepartureStartTime: kotlin.String? = null,
        @JsonProperty("segment6.departureEndTime") private var segment6DepartureEndTime: kotlin.String? = null,
        @JsonProperty("locale") private var locale: kotlin.String? = null,
        @JsonProperty("cabinClass") private var cabinClass: GetFlightListingsOperationParams.CabinClass? = null,
        @JsonProperty("numberOfStops") private var numberOfStops: kotlin.Int? = null,
        @JsonProperty("sortType") private var sortType: kotlin.String? = null,
        @JsonProperty("limit") private var limit: kotlin.Int? = null,
        @JsonProperty("selectedCarriers") private var selectedCarriers: kotlin.collections.List<
            kotlin.String,
        >? = null,
        @JsonProperty("accountCodes") private var accountCodes: kotlin.collections.List<
            kotlin.String,
        >? = null,
        @JsonProperty("agent") private var agent: kotlin.Boolean? = null,
        @JsonProperty("links") private var links: kotlin.collections.List<
            GetFlightListingsOperationParams.Links,
        >? = null,
        @JsonProperty("refundable") private var refundable: kotlin.Boolean? = null,
        @JsonProperty("filterNearByAirport") private var filterNearByAirport: kotlin.Boolean? = null,
        @JsonProperty("filterBasicEconomy") private var filterBasicEconomy: kotlin.Boolean? = null,
        @JsonProperty("anchorBy") private var anchorBy: GetFlightListingsOperationParams.AnchorBy? = null,
        @JsonProperty("selectedOffer") private var selectedOffer: kotlin.String? = null,
        @JsonProperty("filterNonFlightOffers") private var filterNonFlightOffers: kotlin.Boolean? = null,
        @JsonProperty("enableSplitTicket") private var enableSplitTicket: kotlin.Boolean? = null,
    ) {
        /**
         * @param partnerTransactionID Partner-generated identifier.
         */
        fun partnerTransactionID(partnerTransactionID: kotlin.String) = apply { this.partnerTransactionID = partnerTransactionID }

        /**
         * @param adult Number of Adult Travelers. Either one adult or one senior per itinerary is mandatory
         */
        fun adult(adult: kotlin.Int) = apply { this.adult = adult }

        /**
         * @param senior Number of Senior (age > 65) Travelers
         */
        fun senior(senior: kotlin.Int) = apply { this.senior = senior }

        /**
         * @param childrenAges Comma-separated list of the ages of all child travelers (ages 2 - 17).
         */
        fun childrenAges(
            childrenAges: kotlin.collections.List<
                kotlin.Int,
            >,
        ) = apply { this.childrenAges = childrenAges }

        /**
         * @param infantInLap Number of Infant travelers without a reserved seat. Age should be less than 2
         */
        fun infantInLap(infantInLap: kotlin.Int) = apply { this.infantInLap = infantInLap }

        /**
         * @param infantInSeat Number of Infant travelers with reserved seat. Age should be less than 2
         */
        fun infantInSeat(infantInSeat: kotlin.Int) = apply { this.infantInSeat = infantInSeat }

        /**
         * @param locale Indicates the language and country with which the user would like to see any translated information.
         */
        fun locale(locale: kotlin.String) = apply { this.locale = locale }

        /**
         * @param cabinClass The desired cabin classes that the user would like to see offers for. Options can be: economy | first | business | premiumeconomy
         */
        fun cabinClass(cabinClass: GetFlightListingsOperationParams.CabinClass) = apply { this.cabinClass = cabinClass }

        /**
         * @param numberOfStops Filter for the number of stops the user would like to see offers for. A value of 0 returns only non-stop flights in the search response, and a value of 1 returns offers
         */
        fun numberOfStops(numberOfStops: kotlin.Int) = apply { this.numberOfStops = numberOfStops }

        /**
         * @param sortType Sort the search results according to one selected category. Only sort by price is supported at this time. Note: default = Price
         */
        fun sortType(sortType: kotlin.String) = apply { this.sortType = sortType }

        /**
         * @param limit The maximum number of Flight offers returned in the response. Must be an integer value greater than 0.Note: default = 1600
         */
        fun limit(limit: kotlin.Int) = apply { this.limit = limit }

        /**
         * @param selectedCarriers Adding comma-separated list of IATA or Expedia airline codes will limit the search results to include flight offers only with the selected carriers.
         */
        fun selectedCarriers(
            selectedCarriers: kotlin.collections.List<
                kotlin.String,
            >,
        ) = apply { this.selectedCarriers = selectedCarriers }

        /**
         * @param accountCodes AlphaNumeric characters. Different codes separated by comma
         */
        fun accountCodes(
            accountCodes: kotlin.collections.List<
                kotlin.String,
            >,
        ) = apply { this.accountCodes = accountCodes }

        /**
         * @param agent Designates whether a telesales agent was involved in the transaction. true = telesales agent involved false = no telesales agent involved
         */
        fun agent(agent: kotlin.Boolean) = apply { this.agent = agent }

        /**
         * @param links Comma-separated list that indicates which HATEOAS links should be included in the response. WD (Website Details Page - included by default) AD (Details API link) ABF (Baggage Fee API) ASM (Seat Map API) WPS (Web Package Search)
         */
        fun links(
            links: kotlin.collections.List<
                GetFlightListingsOperationParams.Links,
            >,
        ) = apply { this.links = links }

        /**
         * @param refundable Refundable solutions will be returned if and only if we pass refundable as true in the request.(refundable=true). (Not Yet Supported in Production)
         */
        fun refundable(refundable: kotlin.Boolean) = apply { this.refundable = refundable }

        /**
         * @param filterNearByAirport Filters nearby airports ensuring only results from the requests airport code are returned. This request param is valid only for AirportCode-based searches. Note: default = false
         */
        fun filterNearByAirport(filterNearByAirport: kotlin.Boolean) = apply { this.filterNearByAirport = filterNearByAirport }

        /**
         * @param filterBasicEconomy Filters out all the Basic Economy fare solutions in the flight search response. Note: default = false
         */
        fun filterBasicEconomy(filterBasicEconomy: kotlin.Boolean) = apply { this.filterBasicEconomy = filterBasicEconomy }

        /**
         * @param anchorBy Designates that the user is doing a Multi-step Search. Possible values are: segment1 | segment2 | segment3 | segment4 | segment5
         */
        fun anchorBy(anchorBy: GetFlightListingsOperationParams.AnchorBy) = apply { this.anchorBy = anchorBy }

        /**
         * @param selectedOffer Captures the previously selected flight segments during a Multi-step Search.
         */
        fun selectedOffer(selectedOffer: kotlin.String) = apply { this.selectedOffer = selectedOffer }

        /**
         * @param filterNonFlightOffers If set to true, this parameter filters out all non-Flight Offers (offers with any of the legs comprising transit via Train, Bus or Boat) from flight search response. If the parameter is not present or is set to false, then Flight Offers may contain travel legs via means other than flight (Train, Bus or Boat). Default value is false
         */
        fun filterNonFlightOffers(filterNonFlightOffers: kotlin.Boolean) = apply { this.filterNonFlightOffers = filterNonFlightOffers }

        /**
         * @param enableSplitTicket if set to false, this parameter will filter out all the split ticket solutions from the Flight Offers. If set to true (by default it will be true), API response will include split ticket solutions if split ticket is enabled at key configuration level as well.
         */
        fun enableSplitTicket(enableSplitTicket: kotlin.Boolean) = apply { this.enableSplitTicket = enableSplitTicket }

        fun segment1(segment: com.expediagroup.sdk.xap.models.GetFlightListingsOperationSegmentParam) =
            apply {
                this.segment1Origin = segment.origin
                this.segment1Destination = segment.destination
                this.segment1DepartureDate = segment.departureDate
                this.segment1DepartureStartTime = segment.departureStartTime
                this.segment1DepartureEndTime = segment.departureEndTime
            }

        fun segment2(segment: com.expediagroup.sdk.xap.models.GetFlightListingsOperationSegmentParam) =
            apply {
                this.segment2Origin = segment.origin
                this.segment2Destination = segment.destination
                this.segment2DepartureDate = segment.departureDate
                this.segment2DepartureStartTime = segment.departureStartTime
                this.segment2DepartureEndTime = segment.departureEndTime
            }

        fun segment3(segment: com.expediagroup.sdk.xap.models.GetFlightListingsOperationSegmentParam) =
            apply {
                this.segment3Origin = segment.origin
                this.segment3Destination = segment.destination
                this.segment3DepartureDate = segment.departureDate
                this.segment3DepartureStartTime = segment.departureStartTime
                this.segment3DepartureEndTime = segment.departureEndTime
            }

        fun segment4(segment: com.expediagroup.sdk.xap.models.GetFlightListingsOperationSegmentParam) =
            apply {
                this.segment4Origin = segment.origin
                this.segment4Destination = segment.destination
                this.segment4DepartureDate = segment.departureDate
                this.segment4DepartureStartTime = segment.departureStartTime
                this.segment4DepartureEndTime = segment.departureEndTime
            }

        fun segment5(segment: com.expediagroup.sdk.xap.models.GetFlightListingsOperationSegmentParam) =
            apply {
                this.segment5Origin = segment.origin
                this.segment5Destination = segment.destination
                this.segment5DepartureDate = segment.departureDate
                this.segment5DepartureStartTime = segment.departureStartTime
                this.segment5DepartureEndTime = segment.departureEndTime
            }

        fun segment6(segment: com.expediagroup.sdk.xap.models.GetFlightListingsOperationSegmentParam) =
            apply {
                this.segment6Origin = segment.origin
                this.segment6Destination = segment.destination
                this.segment6DepartureDate = segment.departureDate
                this.segment6DepartureStartTime = segment.departureStartTime
                this.segment6DepartureEndTime = segment.departureEndTime
            }

        fun build(): GetFlightListingsOperationParams {
            val params =
                GetFlightListingsOperationParams(
                    partnerTransactionID = partnerTransactionID!!,
                    adult = adult,
                    senior = senior,
                    childrenAges = childrenAges,
                    infantInLap = infantInLap,
                    infantInSeat = infantInSeat,
                    segment1Origin = segment1Origin!!,
                    segment1Destination = segment1Destination!!,
                    segment1DepartureDate = segment1DepartureDate!!,
                    segment1DepartureStartTime = segment1DepartureStartTime,
                    segment1DepartureEndTime = segment1DepartureEndTime,
                    segment2Origin = segment2Origin,
                    segment2Destination = segment2Destination,
                    segment2DepartureDate = segment2DepartureDate,
                    segment2DepartureStartTime = segment2DepartureStartTime,
                    segment2DepartureEndTime = segment2DepartureEndTime,
                    segment3Origin = segment3Origin,
                    segment3Destination = segment3Destination,
                    segment3DepartureDate = segment3DepartureDate,
                    segment3DepartureStartTime = segment3DepartureStartTime,
                    segment3DepartureEndTime = segment3DepartureEndTime,
                    segment4Origin = segment4Origin,
                    segment4Destination = segment4Destination,
                    segment4DepartureDate = segment4DepartureDate,
                    segment4DepartureStartTime = segment4DepartureStartTime,
                    segment4DepartureEndTime = segment4DepartureEndTime,
                    segment5Origin = segment5Origin,
                    segment5Destination = segment5Destination,
                    segment5DepartureDate = segment5DepartureDate,
                    segment5DepartureStartTime = segment5DepartureStartTime,
                    segment5DepartureEndTime = segment5DepartureEndTime,
                    segment6Origin = segment6Origin,
                    segment6Destination = segment6Destination,
                    segment6DepartureDate = segment6DepartureDate,
                    segment6DepartureStartTime = segment6DepartureStartTime,
                    segment6DepartureEndTime = segment6DepartureEndTime,
                    locale = locale,
                    cabinClass = cabinClass,
                    numberOfStops = numberOfStops,
                    sortType = sortType,
                    limit = limit,
                    selectedCarriers = selectedCarriers,
                    accountCodes = accountCodes,
                    agent = agent,
                    links = links,
                    refundable = refundable,
                    filterNearByAirport = filterNearByAirport,
                    filterBasicEconomy = filterBasicEconomy,
                    anchorBy = anchorBy,
                    selectedOffer = selectedOffer,
                    filterNonFlightOffers = filterNonFlightOffers,
                    enableSplitTicket = enableSplitTicket,
                )

            return params
        }
    }

    fun toBuilder() =
        Builder(
            partnerTransactionID = partnerTransactionID,
            adult = adult,
            senior = senior,
            childrenAges = childrenAges,
            infantInLap = infantInLap,
            infantInSeat = infantInSeat,
            segment1Origin = segment1Origin,
            segment1Destination = segment1Destination,
            segment1DepartureDate = segment1DepartureDate,
            segment1DepartureStartTime = segment1DepartureStartTime,
            segment1DepartureEndTime = segment1DepartureEndTime,
            segment2Origin = segment2Origin,
            segment2Destination = segment2Destination,
            segment2DepartureDate = segment2DepartureDate,
            segment2DepartureStartTime = segment2DepartureStartTime,
            segment2DepartureEndTime = segment2DepartureEndTime,
            segment3Origin = segment3Origin,
            segment3Destination = segment3Destination,
            segment3DepartureDate = segment3DepartureDate,
            segment3DepartureStartTime = segment3DepartureStartTime,
            segment3DepartureEndTime = segment3DepartureEndTime,
            segment4Origin = segment4Origin,
            segment4Destination = segment4Destination,
            segment4DepartureDate = segment4DepartureDate,
            segment4DepartureStartTime = segment4DepartureStartTime,
            segment4DepartureEndTime = segment4DepartureEndTime,
            segment5Origin = segment5Origin,
            segment5Destination = segment5Destination,
            segment5DepartureDate = segment5DepartureDate,
            segment5DepartureStartTime = segment5DepartureStartTime,
            segment5DepartureEndTime = segment5DepartureEndTime,
            segment6Origin = segment6Origin,
            segment6Destination = segment6Destination,
            segment6DepartureDate = segment6DepartureDate,
            segment6DepartureStartTime = segment6DepartureStartTime,
            segment6DepartureEndTime = segment6DepartureEndTime,
            locale = locale,
            cabinClass = cabinClass,
            numberOfStops = numberOfStops,
            sortType = sortType,
            limit = limit,
            selectedCarriers = selectedCarriers,
            accountCodes = accountCodes,
            agent = agent,
            links = links,
            refundable = refundable,
            filterNearByAirport = filterNearByAirport,
            filterBasicEconomy = filterBasicEconomy,
            anchorBy = anchorBy,
            selectedOffer = selectedOffer,
            filterNonFlightOffers = filterNonFlightOffers,
            enableSplitTicket = enableSplitTicket,
        )

    fun getHeaders(): Headers =
        Headers
            .builder()
            .apply {
                partnerTransactionID?.let {
                    add("Partner-Transaction-ID", it)
                }
                add("Accept", "application/vnd.exp-flight.v3+json")
            }.build()

    fun getQueryParams(): List<UrlQueryParam> =
        buildList {
            adult?.let {
                val key = "adult"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            senior?.let {
                val key = "senior"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            childrenAges?.let {
                val key = "childrenAges"
                val value =
                    buildList {
                        addAll(it.map { v -> v.toString() })
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", explode),
                    ),
                )
            }
            infantInLap?.let {
                val key = "infantInLap"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            infantInSeat?.let {
                val key = "infantInSeat"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment1Origin?.let {
                val key = "segment1.origin"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment1Destination?.let {
                val key = "segment1.destination"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment1DepartureDate?.let {
                val key = "segment1.departureDate"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment1DepartureStartTime?.let {
                val key = "segment1.departureStartTime"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment1DepartureEndTime?.let {
                val key = "segment1.departureEndTime"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment2Origin?.let {
                val key = "segment2.origin"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment2Destination?.let {
                val key = "segment2.destination"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment2DepartureDate?.let {
                val key = "segment2.departureDate"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment2DepartureStartTime?.let {
                val key = "segment2.departureStartTime"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment2DepartureEndTime?.let {
                val key = "segment2.departureEndTime"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment3Origin?.let {
                val key = "segment3.origin"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment3Destination?.let {
                val key = "segment3.destination"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment3DepartureDate?.let {
                val key = "segment3.departureDate"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment3DepartureStartTime?.let {
                val key = "segment3.departureStartTime"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment3DepartureEndTime?.let {
                val key = "segment3.departureEndTime"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment4Origin?.let {
                val key = "segment4.origin"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment4Destination?.let {
                val key = "segment4.destination"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment4DepartureDate?.let {
                val key = "segment4.departureDate"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment4DepartureStartTime?.let {
                val key = "segment4.departureStartTime"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment4DepartureEndTime?.let {
                val key = "segment4.departureEndTime"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment5Origin?.let {
                val key = "segment5.origin"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment5Destination?.let {
                val key = "segment5.destination"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment5DepartureDate?.let {
                val key = "segment5.departureDate"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment5DepartureStartTime?.let {
                val key = "segment5.departureStartTime"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment5DepartureEndTime?.let {
                val key = "segment5.departureEndTime"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment6Origin?.let {
                val key = "segment6.origin"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment6Destination?.let {
                val key = "segment6.destination"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment6DepartureDate?.let {
                val key = "segment6.departureDate"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment6DepartureStartTime?.let {
                val key = "segment6.departureStartTime"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            segment6DepartureEndTime?.let {
                val key = "segment6.departureEndTime"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            locale?.let {
                val key = "locale"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            cabinClass?.let {
                val key = "cabinClass"
                val value =
                    buildList {
                        add(it.value)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            numberOfStops?.let {
                val key = "numberOfStops"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            sortType?.let {
                val key = "sortType"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            limit?.let {
                val key = "limit"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            selectedCarriers?.let {
                val key = "selectedCarriers"
                val value =
                    buildList {
                        addAll(it.map { v -> v.toString() })
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", explode),
                    ),
                )
            }
            accountCodes?.let {
                val key = "accountCodes"
                val value =
                    buildList {
                        addAll(it.map { v -> v.toString() })
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", explode),
                    ),
                )
            }
            agent?.let {
                val key = "agent"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            links?.let {
                val key = "links"
                val value =
                    buildList {
                        addAll(it.map { v -> v.value.toString() })
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", explode),
                    ),
                )
            }
            refundable?.let {
                val key = "refundable"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            filterNearByAirport?.let {
                val key = "filterNearByAirport"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            filterBasicEconomy?.let {
                val key = "filterBasicEconomy"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            anchorBy?.let {
                val key = "anchorBy"
                val value =
                    buildList {
                        add(it.value)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            selectedOffer?.let {
                val key = "selectedOffer"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            filterNonFlightOffers?.let {
                val key = "filterNonFlightOffers"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            enableSplitTicket?.let {
                val key = "enableSplitTicket"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
        }
}
