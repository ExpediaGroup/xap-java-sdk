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
package com.expediagroup.sdk.xap.operations

import com.expediagroup.sdk.core.model.OperationParams
import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.infrastructure.*
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.ktor.http.Headers
import io.ktor.http.Parameters
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * @property partnerTransactionID Partner-generated identifier.
 * @property accept Accept header for the request.
 * @property acceptEncoding Accept encoding for the request.
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
    @field:NotNull
    @field:Valid
    val partnerTransactionID: kotlin.String,
    @field:NotNull
    @field:Valid
    val accept: kotlin.String,
    @field:Valid
    val acceptEncoding: kotlin.String? =
        null,
    @field:Valid
    val adult: kotlin.Int? =
        null,
    @field:Valid
    val senior: kotlin.Int? =
        null,
    @field:Valid
    val childrenAges: kotlin.String? =
        null,
    @field:Valid
    val infantInLap: kotlin.Int? =
        null,
    @field:Valid
    val infantInSeat: kotlin.Int? =
        null,
    @field:NotNull
    @field:Valid
    val segment1Origin: kotlin.String,
    @field:NotNull
    @field:Valid
    val segment1Destination: kotlin.String,
    @field:NotNull
    @field:Valid
    val segment1DepartureDate: java.time.LocalDate,
    @field:Valid
    val segment1DepartureStartTime: kotlin.String? =
        null,
    @field:Valid
    val segment1DepartureEndTime: kotlin.String? =
        null,
    @field:Valid
    val segment2Origin: kotlin.String? =
        null,
    @field:Valid
    val segment2Destination: kotlin.String? =
        null,
    @field:Valid
    val segment2DepartureDate: kotlin.String? =
        null,
    @field:Valid
    val segment2DepartureStartTime: kotlin.String? =
        null,
    @field:Valid
    val segment2DepartureEndTime: kotlin.String? =
        null,
    @field:Valid
    val segment3Origin: kotlin.String? =
        null,
    @field:Valid
    val segment3Destination: kotlin.String? =
        null,
    @field:Valid
    val segment3DepartureDate: kotlin.String? =
        null,
    @field:Valid
    val segment3DepartureStartTime: kotlin.String? =
        null,
    @field:Valid
    val segment3DepartureEndTime: kotlin.String? =
        null,
    @field:Valid
    val segment4Origin: kotlin.String? =
        null,
    @field:Valid
    val segment4Destination: kotlin.String? =
        null,
    @field:Valid
    val segment4DepartureDate: kotlin.String? =
        null,
    @field:Valid
    val segment4DepartureStartTime: kotlin.String? =
        null,
    @field:Valid
    val segment4DepartureEndTime: kotlin.String? =
        null,
    @field:Valid
    val segment5Origin: kotlin.String? =
        null,
    @field:Valid
    val segment5Destination: kotlin.String? =
        null,
    @field:Valid
    val segment5DepartureDate: kotlin.String? =
        null,
    @field:Valid
    val segment5DepartureStartTime: kotlin.String? =
        null,
    @field:Valid
    val segment5DepartureEndTime: kotlin.String? =
        null,
    @field:Valid
    val segment6Origin: kotlin.String? =
        null,
    @field:Valid
    val segment6Destination: kotlin.String? =
        null,
    @field:Valid
    val segment6DepartureDate: kotlin.String? =
        null,
    @field:Valid
    val segment6DepartureStartTime: kotlin.String? =
        null,
    @field:Valid
    val segment6DepartureEndTime: kotlin.String? =
        null,
    @field:Valid
    val locale: kotlin.String? =
        null,
    @field:Valid
    val cabinClass: kotlin.String? =
        null,
    @field:Valid
    val numberOfStops: kotlin.Int? =
        null,
    @field:Valid
    val sortType: kotlin.String? =
        null,
    @field:Valid
    val limit: kotlin.Int? =
        null,
    @field:Valid
    val selectedCarriers: kotlin.String? =
        null,
    @field:Valid
    val accountCodes: kotlin.String? =
        null,
    @field:Valid
    val agent: kotlin.Boolean? =
        null,
    @field:Valid
    val links: kotlin.String? =
        null,
    @field:Valid
    val refundable: kotlin.Boolean? =
        null,
    @field:Valid
    val filterNearByAirport: kotlin.Boolean? =
        null,
    @field:Valid
    val filterBasicEconomy: kotlin.Boolean? =
        null,
    @field:Valid
    val anchorBy: kotlin.String? =
        null,
    @field:Valid
    val selectedOffer: kotlin.String? =
        null,
    @field:Valid
    val filterNonFlightOffers: kotlin.Boolean? =
        null,
    @field:Valid
    val enableSplitTicket: kotlin.Boolean? =
        null
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("Partner-Transaction-ID") private var partnerTransactionID: kotlin.String? = null,
        @JsonProperty("Accept") private var accept: kotlin.String? = null,
        @JsonProperty("Accept-Encoding") private var acceptEncoding: kotlin.String? = null,
        @JsonProperty("adult") private var adult: kotlin.Int? = null,
        @JsonProperty("senior") private var senior: kotlin.Int? = null,
        @JsonProperty("childrenAges") private var childrenAges: kotlin.String? = null,
        @JsonProperty("infantInLap") private var infantInLap: kotlin.Int? = null,
        @JsonProperty("infantInSeat") private var infantInSeat: kotlin.Int? = null,
        @JsonProperty("segment1.origin") private var segment1Origin: kotlin.String? = null,
        @JsonProperty("segment1.destination") private var segment1Destination: kotlin.String? = null,
        @JsonProperty("segment1.departureDate") private var segment1DepartureDate: java.time.LocalDate? = null,
        @JsonProperty("segment1.departureStartTime") private var segment1DepartureStartTime: kotlin.String? = null,
        @JsonProperty("segment1.departureEndTime") private var segment1DepartureEndTime: kotlin.String? = null,
        @JsonProperty("segment2.origin") private var segment2Origin: kotlin.String? = null,
        @JsonProperty("segment2.destination") private var segment2Destination: kotlin.String? = null,
        @JsonProperty("segment2.departureDate") private var segment2DepartureDate: kotlin.String? = null,
        @JsonProperty("segment2.departureStartTime") private var segment2DepartureStartTime: kotlin.String? = null,
        @JsonProperty("segment2.departureEndTime") private var segment2DepartureEndTime: kotlin.String? = null,
        @JsonProperty("segment3.origin") private var segment3Origin: kotlin.String? = null,
        @JsonProperty("segment3.destination") private var segment3Destination: kotlin.String? = null,
        @JsonProperty("segment3.departureDate") private var segment3DepartureDate: kotlin.String? = null,
        @JsonProperty("segment3.departureStartTime") private var segment3DepartureStartTime: kotlin.String? = null,
        @JsonProperty("segment3.departureEndTime") private var segment3DepartureEndTime: kotlin.String? = null,
        @JsonProperty("segment4.origin") private var segment4Origin: kotlin.String? = null,
        @JsonProperty("segment4.destination") private var segment4Destination: kotlin.String? = null,
        @JsonProperty("segment4.departureDate") private var segment4DepartureDate: kotlin.String? = null,
        @JsonProperty("segment4.departureStartTime") private var segment4DepartureStartTime: kotlin.String? = null,
        @JsonProperty("segment4.departureEndTime") private var segment4DepartureEndTime: kotlin.String? = null,
        @JsonProperty("segment5.origin") private var segment5Origin: kotlin.String? = null,
        @JsonProperty("segment5.destination") private var segment5Destination: kotlin.String? = null,
        @JsonProperty("segment5.departureDate") private var segment5DepartureDate: kotlin.String? = null,
        @JsonProperty("segment5.departureStartTime") private var segment5DepartureStartTime: kotlin.String? = null,
        @JsonProperty("segment5.departureEndTime") private var segment5DepartureEndTime: kotlin.String? = null,
        @JsonProperty("segment6.origin") private var segment6Origin: kotlin.String? = null,
        @JsonProperty("segment6.destination") private var segment6Destination: kotlin.String? = null,
        @JsonProperty("segment6.departureDate") private var segment6DepartureDate: kotlin.String? = null,
        @JsonProperty("segment6.departureStartTime") private var segment6DepartureStartTime: kotlin.String? = null,
        @JsonProperty("segment6.departureEndTime") private var segment6DepartureEndTime: kotlin.String? = null,
        @JsonProperty("locale") private var locale: kotlin.String? = null,
        @JsonProperty("cabinClass") private var cabinClass: kotlin.String? = null,
        @JsonProperty("numberOfStops") private var numberOfStops: kotlin.Int? = null,
        @JsonProperty("sortType") private var sortType: kotlin.String? = null,
        @JsonProperty("limit") private var limit: kotlin.Int? = null,
        @JsonProperty("selectedCarriers") private var selectedCarriers: kotlin.String? = null,
        @JsonProperty("accountCodes") private var accountCodes: kotlin.String? = null,
        @JsonProperty("agent") private var agent: kotlin.Boolean? = null,
        @JsonProperty("links") private var links: kotlin.String? = null,
        @JsonProperty("refundable") private var refundable: kotlin.Boolean? = null,
        @JsonProperty("filterNearByAirport") private var filterNearByAirport: kotlin.Boolean? = null,
        @JsonProperty("filterBasicEconomy") private var filterBasicEconomy: kotlin.Boolean? = null,
        @JsonProperty("anchorBy") private var anchorBy: kotlin.String? = null,
        @JsonProperty("selectedOffer") private var selectedOffer: kotlin.String? = null,
        @JsonProperty("filterNonFlightOffers") private var filterNonFlightOffers: kotlin.Boolean? = null,
        @JsonProperty("enableSplitTicket") private var enableSplitTicket: kotlin.Boolean? = null
    ) {
        /**
         * @param partnerTransactionID Partner-generated identifier.
         */
        fun partnerTransactionID(partnerTransactionID: kotlin.String) = apply { this.partnerTransactionID = partnerTransactionID }

        /**
         * @param accept Accept header for the request.
         */
        fun accept(accept: kotlin.String) = apply { this.accept = accept }

        /**
         * @param acceptEncoding Accept encoding for the request.
         */
        fun acceptEncoding(acceptEncoding: kotlin.String) = apply { this.acceptEncoding = acceptEncoding }

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
        fun childrenAges(childrenAges: kotlin.String) = apply { this.childrenAges = childrenAges }

        /**
         * @param infantInLap Number of Infant travelers without a reserved seat. Age should be less than 2
         */
        fun infantInLap(infantInLap: kotlin.Int) = apply { this.infantInLap = infantInLap }

        /**
         * @param infantInSeat Number of Infant travelers with reserved seat. Age should be less than 2
         */
        fun infantInSeat(infantInSeat: kotlin.Int) = apply { this.infantInSeat = infantInSeat }

        /**
         * @param segment1Origin 3-letter IATA Airport code/Location name from where the passenger is departing.
         */
        fun segment1Origin(segment1Origin: kotlin.String) = apply { this.segment1Origin = segment1Origin }

        /**
         * @param segment1Destination 3-letter IATA Airport code/Location name from where the passenger is arriving.
         */
        fun segment1Destination(segment1Destination: kotlin.String) = apply { this.segment1Destination = segment1Destination }

        /**
         * @param segment1DepartureDate Date, in ISO format [YYYY-MM-DD], on which customer wants to depart.
         */
        fun segment1DepartureDate(segment1DepartureDate: java.time.LocalDate) = apply { this.segment1DepartureDate = segment1DepartureDate }

        /**
         * @param segment1DepartureStartTime Lower limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
         */
        fun segment1DepartureStartTime(segment1DepartureStartTime: kotlin.String) = apply { this.segment1DepartureStartTime = segment1DepartureStartTime }

        /**
         * @param segment1DepartureEndTime Upper limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
         */
        fun segment1DepartureEndTime(segment1DepartureEndTime: kotlin.String) = apply { this.segment1DepartureEndTime = segment1DepartureEndTime }

        /**
         * @param segment2Origin 3-letter IATA Airport code/Location name from where the passenger is departing.
         */
        fun segment2Origin(segment2Origin: kotlin.String) = apply { this.segment2Origin = segment2Origin }

        /**
         * @param segment2Destination 3-letter IATA Airport code/Location name from where the passenger is arriving.
         */
        fun segment2Destination(segment2Destination: kotlin.String) = apply { this.segment2Destination = segment2Destination }

        /**
         * @param segment2DepartureDate Date, in ISO format [YYYY-MM-DD], on which customer wants to depart.
         */
        fun segment2DepartureDate(segment2DepartureDate: kotlin.String) = apply { this.segment2DepartureDate = segment2DepartureDate }

        /**
         * @param segment2DepartureStartTime Lower limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
         */
        fun segment2DepartureStartTime(segment2DepartureStartTime: kotlin.String) = apply { this.segment2DepartureStartTime = segment2DepartureStartTime }

        /**
         * @param segment2DepartureEndTime Upper limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
         */
        fun segment2DepartureEndTime(segment2DepartureEndTime: kotlin.String) = apply { this.segment2DepartureEndTime = segment2DepartureEndTime }

        /**
         * @param segment3Origin 3-letter IATA Airport code/Location name from where the passenger is departing.
         */
        fun segment3Origin(segment3Origin: kotlin.String) = apply { this.segment3Origin = segment3Origin }

        /**
         * @param segment3Destination 3-letter IATA Airport code/Location name from where the passenger is arriving.
         */
        fun segment3Destination(segment3Destination: kotlin.String) = apply { this.segment3Destination = segment3Destination }

        /**
         * @param segment3DepartureDate Date, in ISO format [YYYY-MM-DD], on which customer wants to depart.
         */
        fun segment3DepartureDate(segment3DepartureDate: kotlin.String) = apply { this.segment3DepartureDate = segment3DepartureDate }

        /**
         * @param segment3DepartureStartTime Lower limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
         */
        fun segment3DepartureStartTime(segment3DepartureStartTime: kotlin.String) = apply { this.segment3DepartureStartTime = segment3DepartureStartTime }

        /**
         * @param segment3DepartureEndTime Upper limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
         */
        fun segment3DepartureEndTime(segment3DepartureEndTime: kotlin.String) = apply { this.segment3DepartureEndTime = segment3DepartureEndTime }

        /**
         * @param segment4Origin 3-letter IATA Airport code/Location name from where the passenger is departing.
         */
        fun segment4Origin(segment4Origin: kotlin.String) = apply { this.segment4Origin = segment4Origin }

        /**
         * @param segment4Destination 3-letter IATA Airport code/Location name from where the passenger is arriving.
         */
        fun segment4Destination(segment4Destination: kotlin.String) = apply { this.segment4Destination = segment4Destination }

        /**
         * @param segment4DepartureDate Date, in ISO format [YYYY-MM-DD], on which customer wants to depart.
         */
        fun segment4DepartureDate(segment4DepartureDate: kotlin.String) = apply { this.segment4DepartureDate = segment4DepartureDate }

        /**
         * @param segment4DepartureStartTime Lower limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
         */
        fun segment4DepartureStartTime(segment4DepartureStartTime: kotlin.String) = apply { this.segment4DepartureStartTime = segment4DepartureStartTime }

        /**
         * @param segment4DepartureEndTime Upper limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
         */
        fun segment4DepartureEndTime(segment4DepartureEndTime: kotlin.String) = apply { this.segment4DepartureEndTime = segment4DepartureEndTime }

        /**
         * @param segment5Origin 3-letter IATA Airport code/Location name from where the passenger is departing.
         */
        fun segment5Origin(segment5Origin: kotlin.String) = apply { this.segment5Origin = segment5Origin }

        /**
         * @param segment5Destination 3-letter IATA Airport code/Location name from where the passenger is arriving.
         */
        fun segment5Destination(segment5Destination: kotlin.String) = apply { this.segment5Destination = segment5Destination }

        /**
         * @param segment5DepartureDate Date, in ISO format [YYYY-MM-DD], on which customer wants to depart.
         */
        fun segment5DepartureDate(segment5DepartureDate: kotlin.String) = apply { this.segment5DepartureDate = segment5DepartureDate }

        /**
         * @param segment5DepartureStartTime Lower limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
         */
        fun segment5DepartureStartTime(segment5DepartureStartTime: kotlin.String) = apply { this.segment5DepartureStartTime = segment5DepartureStartTime }

        /**
         * @param segment5DepartureEndTime Upper limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
         */
        fun segment5DepartureEndTime(segment5DepartureEndTime: kotlin.String) = apply { this.segment5DepartureEndTime = segment5DepartureEndTime }

        /**
         * @param segment6Origin 3-letter IATA Airport code/Location name from where the passenger is departing.
         */
        fun segment6Origin(segment6Origin: kotlin.String) = apply { this.segment6Origin = segment6Origin }

        /**
         * @param segment6Destination 3-letter IATA Airport code/Location name from where the passenger is arriving.
         */
        fun segment6Destination(segment6Destination: kotlin.String) = apply { this.segment6Destination = segment6Destination }

        /**
         * @param segment6DepartureDate Date, in ISO format [YYYY-MM-DD], on which customer wants to depart.
         */
        fun segment6DepartureDate(segment6DepartureDate: kotlin.String) = apply { this.segment6DepartureDate = segment6DepartureDate }

        /**
         * @param segment6DepartureStartTime Lower limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
         */
        fun segment6DepartureStartTime(segment6DepartureStartTime: kotlin.String) = apply { this.segment6DepartureStartTime = segment6DepartureStartTime }

        /**
         * @param segment6DepartureEndTime Upper limit of desired departure time window, expressed in the local time of the departure location, in standard ISO format.
         */
        fun segment6DepartureEndTime(segment6DepartureEndTime: kotlin.String) = apply { this.segment6DepartureEndTime = segment6DepartureEndTime }

        /**
         * @param locale Indicates the language and country with which the user would like to see any translated information.
         */
        fun locale(locale: kotlin.String) = apply { this.locale = locale }

        /**
         * @param cabinClass The desired cabin classes that the user would like to see offers for. Options can be: economy | first | business | premiumeconomy
         */
        fun cabinClass(cabinClass: kotlin.String) = apply { this.cabinClass = cabinClass }

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
        fun selectedCarriers(selectedCarriers: kotlin.String) = apply { this.selectedCarriers = selectedCarriers }

        /**
         * @param accountCodes AlphaNumeric characters. Different codes separated by comma
         */
        fun accountCodes(accountCodes: kotlin.String) = apply { this.accountCodes = accountCodes }

        /**
         * @param agent Designates whether a telesales agent was involved in the transaction. true = telesales agent involved false = no telesales agent involved
         */
        fun agent(agent: kotlin.Boolean) = apply { this.agent = agent }

        /**
         * @param links Comma-separated list that indicates which HATEOAS links should be included in the response. WD (Website Details Page - included by default) AD (Details API link) ABF (Baggage Fee API) ASM (Seat Map API) WPS (Web Package Search)
         */
        fun links(links: kotlin.String) = apply { this.links = links }

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
        fun anchorBy(anchorBy: kotlin.String) = apply { this.anchorBy = anchorBy }

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

        fun build(): GetFlightListingsOperationParams {
            val params =
                GetFlightListingsOperationParams(
                    partnerTransactionID = partnerTransactionID!!,
                    accept = accept!!,
                    acceptEncoding = acceptEncoding,
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
                    enableSplitTicket = enableSplitTicket
                )

            validate(params)

            return params
        }

        private fun validate(params: GetFlightListingsOperationParams) {
            val validator =
                Validation
                    .byDefaultProvider()
                    .configure()
                    .messageInterpolator(ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .validator

            val violations = validator.validate(params)

            if (violations.isNotEmpty()) {
                throw PropertyConstraintViolationException(
                    constraintViolations = violations.map { "${it.propertyPath}: ${it.message}" }
                )
            }
        }
    }

    fun toBuilder() =
        Builder(
            partnerTransactionID = partnerTransactionID,
            accept = accept,
            acceptEncoding = acceptEncoding,
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
            enableSplitTicket = enableSplitTicket
        )

    override fun getHeaders(): Headers =
        Headers.build {
            partnerTransactionID?.let {
                append("Partner-Transaction-ID", it)
            }
            accept?.let {
                append("Accept", it)
            }
            acceptEncoding?.let {
                append("Accept-Encoding", it)
            }
            append("Accept", "application/vnd.exp-flight.v3+json")
        }

    override fun getQueryParams(): Parameters =
        Parameters.build {
            adult?.let {
                append("adult", it.toString())
            }
            senior?.let {
                append("senior", it.toString())
            }
            childrenAges?.let {
                append("childrenAges", it)
            }
            infantInLap?.let {
                append("infantInLap", it.toString())
            }
            infantInSeat?.let {
                append("infantInSeat", it.toString())
            }
            segment1Origin?.let {
                append("segment1.origin", it)
            }
            segment1Destination?.let {
                append("segment1.destination", it)
            }
            segment1DepartureDate?.let {
                append("segment1.departureDate", it.toString())
            }
            segment1DepartureStartTime?.let {
                append("segment1.departureStartTime", it)
            }
            segment1DepartureEndTime?.let {
                append("segment1.departureEndTime", it)
            }
            segment2Origin?.let {
                append("segment2.origin", it)
            }
            segment2Destination?.let {
                append("segment2.destination", it)
            }
            segment2DepartureDate?.let {
                append("segment2.departureDate", it)
            }
            segment2DepartureStartTime?.let {
                append("segment2.departureStartTime", it)
            }
            segment2DepartureEndTime?.let {
                append("segment2.departureEndTime", it)
            }
            segment3Origin?.let {
                append("segment3.origin", it)
            }
            segment3Destination?.let {
                append("segment3.destination", it)
            }
            segment3DepartureDate?.let {
                append("segment3.departureDate", it)
            }
            segment3DepartureStartTime?.let {
                append("segment3.departureStartTime", it)
            }
            segment3DepartureEndTime?.let {
                append("segment3.departureEndTime", it)
            }
            segment4Origin?.let {
                append("segment4.origin", it)
            }
            segment4Destination?.let {
                append("segment4.destination", it)
            }
            segment4DepartureDate?.let {
                append("segment4.departureDate", it)
            }
            segment4DepartureStartTime?.let {
                append("segment4.departureStartTime", it)
            }
            segment4DepartureEndTime?.let {
                append("segment4.departureEndTime", it)
            }
            segment5Origin?.let {
                append("segment5.origin", it)
            }
            segment5Destination?.let {
                append("segment5.destination", it)
            }
            segment5DepartureDate?.let {
                append("segment5.departureDate", it)
            }
            segment5DepartureStartTime?.let {
                append("segment5.departureStartTime", it)
            }
            segment5DepartureEndTime?.let {
                append("segment5.departureEndTime", it)
            }
            segment6Origin?.let {
                append("segment6.origin", it)
            }
            segment6Destination?.let {
                append("segment6.destination", it)
            }
            segment6DepartureDate?.let {
                append("segment6.departureDate", it)
            }
            segment6DepartureStartTime?.let {
                append("segment6.departureStartTime", it)
            }
            segment6DepartureEndTime?.let {
                append("segment6.departureEndTime", it)
            }
            locale?.let {
                append("locale", it)
            }
            cabinClass?.let {
                append("cabinClass", it)
            }
            numberOfStops?.let {
                append("numberOfStops", it.toString())
            }
            sortType?.let {
                append("sortType", it)
            }
            limit?.let {
                append("limit", it.toString())
            }
            selectedCarriers?.let {
                append("selectedCarriers", it)
            }
            accountCodes?.let {
                append("accountCodes", it)
            }
            agent?.let {
                append("agent", it.toString())
            }
            links?.let {
                append("links", it)
            }
            refundable?.let {
                append("refundable", it.toString())
            }
            filterNearByAirport?.let {
                append("filterNearByAirport", it.toString())
            }
            filterBasicEconomy?.let {
                append("filterBasicEconomy", it.toString())
            }
            anchorBy?.let {
                append("anchorBy", it)
            }
            selectedOffer?.let {
                append("selectedOffer", it)
            }
            filterNonFlightOffers?.let {
                append("filterNonFlightOffers", it.toString())
            }
            enableSplitTicket?.let {
                append("enableSplitTicket", it.toString())
            }
        }

    override fun getPathParams(): Map<String, String> =
        buildMap {
        }
}
