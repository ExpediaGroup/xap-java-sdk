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
package com.expediagroup.sdk.xap.client

import com.expediagroup.sdk.core.client.BaseXapClient
import com.expediagroup.sdk.core.configuration.XapClientConfiguration
import com.expediagroup.sdk.core.constant.ConfigurationName
import com.expediagroup.sdk.core.constant.provider.ExceptionMessageProvider.getMissingRequiredConfigurationMessage
import com.expediagroup.sdk.core.model.EmptyResponse
import com.expediagroup.sdk.core.model.Nothing
import com.expediagroup.sdk.core.model.Operation
import com.expediagroup.sdk.core.model.Response
import com.expediagroup.sdk.core.model.exception.client.ExpediaGroupConfigurationException
import com.expediagroup.sdk.core.model.exception.handle
import com.expediagroup.sdk.xap.models.*
import com.expediagroup.sdk.xap.models.exception.ErrorObjectMapper
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiAPIGatewayErrorException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiAPIMErrorException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiActivitiesErrorsException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiCarsErrorsException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiErrorResponseException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiErrorsException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiFlightsV1ErrorsException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiFlightsV2ErrorsException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiFlightsV3APIMErrorException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiFlightsV3ErrorsException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiGetFlightFlexsearch400ResponseException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiLodgingErrorsException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiPostFlightLinks401ResponseException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiPostFlightLinks403ResponseException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiPostFlightLinks404ResponseException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiPostFlightLinks405ResponseException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiPostFlightLinks429ResponseException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiPostFlightLinks503ResponseException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiPostFlightLinks504ResponseException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiPresignedUrlResponseException
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiSdpAPIMErrorException
import com.expediagroup.sdk.xap.operations.GetActivityDetailsOperation
import com.expediagroup.sdk.xap.operations.GetActivityListingsOperation
import com.expediagroup.sdk.xap.operations.GetCarDetailsOperation
import com.expediagroup.sdk.xap.operations.GetCarsListingsOperation
import com.expediagroup.sdk.xap.operations.GetFeedDownloadUrlOperation
import com.expediagroup.sdk.xap.operations.GetFlightBagaggefeeOperation
import com.expediagroup.sdk.xap.operations.GetFlightDetailsOperation
import com.expediagroup.sdk.xap.operations.GetFlightFarerulesOperation
import com.expediagroup.sdk.xap.operations.GetFlightFlexsearchOperation
import com.expediagroup.sdk.xap.operations.GetFlightListingsOperation
import com.expediagroup.sdk.xap.operations.GetFlightSeatmapOperation
import com.expediagroup.sdk.xap.operations.GetFlightsFarecalendarOperation
import com.expediagroup.sdk.xap.operations.GetLodgingAvailabilityCalendarsOperation
import com.expediagroup.sdk.xap.operations.GetLodgingDetailsOperation
import com.expediagroup.sdk.xap.operations.GetLodgingListingsOperation
import com.expediagroup.sdk.xap.operations.GetLodgingQuotesOperation
import com.expediagroup.sdk.xap.operations.GetLodgingRateCalendarOperation
import com.expediagroup.sdk.xap.operations.PostFlightLinksOperation
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.exclude
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.future.future
import java.util.concurrent.CompletableFuture

/**
* The XAP Lodging Search APIs can be used by partners both booking via an Expedia website, or by partners that
will be booking via the XAP APIs. Each API also provides pre-configured links to the Expedia website,
the XAP Booking API, or both.

*/

class XapClient private constructor(clientConfiguration: XapClientConfiguration) : BaseXapClient("xap", clientConfiguration) {
    class Builder : BaseXapClient.Builder<Builder>() {
        override fun build() =
            XapClient(
                XapClientConfiguration(key, secret, endpoint, requestTimeout, connectionTimeout, socketTimeout, maskedLoggingHeaders, maskedLoggingBodyFields)
            )
    }

    class BuilderWithHttpClient() : BaseXapClient.BuilderWithHttpClient<BuilderWithHttpClient>() {
        override fun build(): XapClient {
            if (okHttpClient == null) {
                throw ExpediaGroupConfigurationException(getMissingRequiredConfigurationMessage(ConfigurationName.OKHTTP_CLIENT))
            }

            return XapClient(
                XapClientConfiguration(key, secret, endpoint, null, null, null, maskedLoggingHeaders, maskedLoggingBodyFields, okHttpClient)
            )
        }
    }

    companion object {
        @JvmStatic fun builder() = Builder()

        @JvmStatic fun builderWithHttpClient() = BuilderWithHttpClient()
    }

    override suspend fun throwServiceException(
        response: HttpResponse,
        operationId: String
    ): Unit = throw ErrorObjectMapper.process(response, operationId)

    private suspend inline fun <reified RequestType> executeHttpRequest(operation: Operation<RequestType>): HttpResponse =
        httpClient.request {
            method = HttpMethod.parse(operation.method)
            url(operation.url)

            operation.params?.getHeaders()?.let {
                headers.appendAll(it)
            }

            operation.params?.getQueryParams()?.let {
                url.parameters.appendAll(it)
            }

            val extraHeaders =
                buildMap {
                    put("key", configurationProvider.key ?: "")
                }

            appendHeaders(extraHeaders)
            contentType(ContentType.Application.Json)
            setBody(operation.requestBody)
            exclude(ContentType.Application.Json)
        }

    private inline fun <reified RequestType> executeWithEmptyResponse(operation: Operation<RequestType>): EmptyResponse {
        try {
            return executeAsyncWithEmptyResponse(operation).get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    private inline fun <reified RequestType> executeAsyncWithEmptyResponse(operation: Operation<RequestType>): CompletableFuture<EmptyResponse> =
        GlobalScope.future(Dispatchers.IO) {
            try {
                val response = executeHttpRequest(operation)
                throwIfError(response, operation.operationId)
                EmptyResponse(response.status.value, response.headers.entries())
            } catch (exception: Exception) {
                exception.handle()
            }
        }

    private inline fun <reified RequestType, reified ResponseType> execute(operation: Operation<RequestType>): Response<ResponseType> {
        try {
            return executeAsync<RequestType, ResponseType>(operation).get()
        } catch (exception: Exception) {
            exception.handle()
        }
    }

    private inline fun <reified RequestType, reified ResponseType> executeAsync(operation: Operation<RequestType>): CompletableFuture<Response<ResponseType>> =
        GlobalScope.future(Dispatchers.IO) {
            try {
                val response = executeHttpRequest(operation)
                throwIfError(response, operation.operationId)
                Response(response.status.value, response.body<ResponseType>(), response.headers.entries())
            } catch (exception: Exception) {
                exception.handle()
            }
        }

    /**
     *
     * The Activity Details API provides detailed information about one selected activity.
     * @param operation [GetActivityDetailsOperation]
     * @throws ExpediaGroupApiActivitiesErrorsException
     * @throws ExpediaGroupApiAPIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [Response] object with a body of type ActivityDetailsResponse
     */
    fun execute(operation: GetActivityDetailsOperation): Response<ActivityDetailsResponse> = execute<Nothing, ActivityDetailsResponse>(operation)

    /**
     *
     * The Activity Details API provides detailed information about one selected activity.
     * @param operation [GetActivityDetailsOperation]
     * @throws ExpediaGroupApiActivitiesErrorsException
     * @throws ExpediaGroupApiAPIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [CompletableFuture<Response>] object with a body of type ActivityDetailsResponse
     */
    fun executeAsync(operation: GetActivityDetailsOperation): CompletableFuture<Response<ActivityDetailsResponse>> = executeAsync<Nothing, ActivityDetailsResponse>(operation)

    /**
     *
     * The Activities Search API allows partners to search for Expedia Activity inventory.
     * @param operation [GetActivityListingsOperation]
     * @throws ExpediaGroupApiActivitiesErrorsException
     * @throws ExpediaGroupApiAPIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [Response] object with a body of type ActivityListingsResponse
     */
    fun execute(operation: GetActivityListingsOperation): Response<ActivityListingsResponse> = execute<Nothing, ActivityListingsResponse>(operation)

    /**
     *
     * The Activities Search API allows partners to search for Expedia Activity inventory.
     * @param operation [GetActivityListingsOperation]
     * @throws ExpediaGroupApiActivitiesErrorsException
     * @throws ExpediaGroupApiAPIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [CompletableFuture<Response>] object with a body of type ActivityListingsResponse
     */
    fun executeAsync(operation: GetActivityListingsOperation): CompletableFuture<Response<ActivityListingsResponse>> = executeAsync<Nothing, ActivityListingsResponse>(operation)

    /**
     * Get Extended information with a single car offer
     * Extended information about the rates, charges, fees, and other terms associated with a single car offer.
     * @param operation [GetCarDetailsOperation]
     * @throws ExpediaGroupApiCarsErrorsException
     * @throws ExpediaGroupApiAPIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [Response] object with a body of type CarDetailsResponse
     */
    fun execute(operation: GetCarDetailsOperation): Response<CarDetailsResponse> = execute<Nothing, CarDetailsResponse>(operation)

    /**
     * Get Extended information with a single car offer
     * Extended information about the rates, charges, fees, and other terms associated with a single car offer.
     * @param operation [GetCarDetailsOperation]
     * @throws ExpediaGroupApiCarsErrorsException
     * @throws ExpediaGroupApiAPIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [CompletableFuture<Response>] object with a body of type CarDetailsResponse
     */
    fun executeAsync(operation: GetCarDetailsOperation): CompletableFuture<Response<CarDetailsResponse>> = executeAsync<Nothing, CarDetailsResponse>(operation)

    /**
     * Search Expedia car inventory
     * Search Expedia car inventory by date, pickup, and dropoff location to return a listing of available cars for hire.
     * @param operation [GetCarsListingsOperation]
     * @throws ExpediaGroupApiCarsErrorsException
     * @throws ExpediaGroupApiAPIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [Response] object with a body of type CarListingsResponse
     */
    fun execute(operation: GetCarsListingsOperation): Response<CarListingsResponse> = execute<Nothing, CarListingsResponse>(operation)

    /**
     * Search Expedia car inventory
     * Search Expedia car inventory by date, pickup, and dropoff location to return a listing of available cars for hire.
     * @param operation [GetCarsListingsOperation]
     * @throws ExpediaGroupApiCarsErrorsException
     * @throws ExpediaGroupApiAPIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [CompletableFuture<Response>] object with a body of type CarListingsResponse
     */
    fun executeAsync(operation: GetCarsListingsOperation): CompletableFuture<Response<CarListingsResponse>> = executeAsync<Nothing, CarListingsResponse>(operation)

    /**
     *
     * Get the Download URL and other details of the static files.
     * @param operation [GetFeedDownloadUrlOperation]
     * @throws ExpediaGroupApiPresignedUrlResponseException
     * @throws ExpediaGroupApiSdpAPIMErrorException
     * @return a [Response] object with a body of type PresignedUrlResponse
     */
    fun execute(operation: GetFeedDownloadUrlOperation): Response<PresignedUrlResponse> = execute<Nothing, PresignedUrlResponse>(operation)

    /**
     *
     * Get the Download URL and other details of the static files.
     * @param operation [GetFeedDownloadUrlOperation]
     * @throws ExpediaGroupApiPresignedUrlResponseException
     * @throws ExpediaGroupApiSdpAPIMErrorException
     * @return a [CompletableFuture<Response>] object with a body of type PresignedUrlResponse
     */
    fun executeAsync(operation: GetFeedDownloadUrlOperation): CompletableFuture<Response<PresignedUrlResponse>> = executeAsync<Nothing, PresignedUrlResponse>(operation)

    /**
     *
     * request for baggage fee information
     * @param operation [GetFlightBagaggefeeOperation]
     * @throws ExpediaGroupApiFlightsV1ErrorsException
     * @throws ExpediaGroupApiAPIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [Response] object with a body of type FlightBaggageFeesResponse
     */
    fun execute(operation: GetFlightBagaggefeeOperation): Response<FlightBaggageFeesResponse> = execute<Nothing, FlightBaggageFeesResponse>(operation)

    /**
     *
     * request for baggage fee information
     * @param operation [GetFlightBagaggefeeOperation]
     * @throws ExpediaGroupApiFlightsV1ErrorsException
     * @throws ExpediaGroupApiAPIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [CompletableFuture<Response>] object with a body of type FlightBaggageFeesResponse
     */
    fun executeAsync(operation: GetFlightBagaggefeeOperation): CompletableFuture<Response<FlightBaggageFeesResponse>> = executeAsync<Nothing, FlightBaggageFeesResponse>(operation)

    /**
     *
     * The Flight Details API will return validated pricing on the itinerary passed in on the request. It's a GET request with the transaction secured over HTTPS. This message supports response in JSON and XML format.
     * @param operation [GetFlightDetailsOperation]
     * @throws ExpediaGroupApiFlightsV3ErrorsException
     * @throws ExpediaGroupApiFlightsV3APIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [Response] object with a body of type FlightDetailsResponse
     */
    fun execute(operation: GetFlightDetailsOperation): Response<FlightDetailsResponse> = execute<Nothing, FlightDetailsResponse>(operation)

    /**
     *
     * The Flight Details API will return validated pricing on the itinerary passed in on the request. It's a GET request with the transaction secured over HTTPS. This message supports response in JSON and XML format.
     * @param operation [GetFlightDetailsOperation]
     * @throws ExpediaGroupApiFlightsV3ErrorsException
     * @throws ExpediaGroupApiFlightsV3APIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [CompletableFuture<Response>] object with a body of type FlightDetailsResponse
     */
    fun executeAsync(operation: GetFlightDetailsOperation): CompletableFuture<Response<FlightDetailsResponse>> = executeAsync<Nothing, FlightDetailsResponse>(operation)

    /**
     *
     * request for farerule information
     * @param operation [GetFlightFarerulesOperation]
     * @throws ExpediaGroupApiFlightsV1ErrorsException
     * @throws ExpediaGroupApiAPIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [Response] object with a body of type FlightFareRulesResponse
     */
    fun execute(operation: GetFlightFarerulesOperation): Response<FlightFareRulesResponse> = execute<Nothing, FlightFareRulesResponse>(operation)

    /**
     *
     * request for farerule information
     * @param operation [GetFlightFarerulesOperation]
     * @throws ExpediaGroupApiFlightsV1ErrorsException
     * @throws ExpediaGroupApiAPIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [CompletableFuture<Response>] object with a body of type FlightFareRulesResponse
     */
    fun executeAsync(operation: GetFlightFarerulesOperation): CompletableFuture<Response<FlightFareRulesResponse>> = executeAsync<Nothing, FlightFareRulesResponse>(operation)

    /**
     *
     * request for flexsearch information
     * @param operation [GetFlightFlexsearchOperation]
     * @throws ExpediaGroupApiGetFlightFlexsearch400ResponseException
     * @throws ExpediaGroupApiFlightsV3APIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [Response] object with a body of type FlexSearchResponse
     */
    fun execute(operation: GetFlightFlexsearchOperation): Response<FlexSearchResponse> = execute<Nothing, FlexSearchResponse>(operation)

    /**
     *
     * request for flexsearch information
     * @param operation [GetFlightFlexsearchOperation]
     * @throws ExpediaGroupApiGetFlightFlexsearch400ResponseException
     * @throws ExpediaGroupApiFlightsV3APIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [CompletableFuture<Response>] object with a body of type FlexSearchResponse
     */
    fun executeAsync(operation: GetFlightFlexsearchOperation): CompletableFuture<Response<FlexSearchResponse>> = executeAsync<Nothing, FlexSearchResponse>(operation)

    /**
     *
     * search flight products
     * @param operation [GetFlightListingsOperation]
     * @throws ExpediaGroupApiFlightsV3ErrorsException
     * @throws ExpediaGroupApiFlightsV3APIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [Response] object with a body of type FlightSearchResponse
     */
    fun execute(operation: GetFlightListingsOperation): Response<FlightSearchResponse> = execute<Nothing, FlightSearchResponse>(operation)

    /**
     *
     * search flight products
     * @param operation [GetFlightListingsOperation]
     * @throws ExpediaGroupApiFlightsV3ErrorsException
     * @throws ExpediaGroupApiFlightsV3APIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [CompletableFuture<Response>] object with a body of type FlightSearchResponse
     */
    fun executeAsync(operation: GetFlightListingsOperation): CompletableFuture<Response<FlightSearchResponse>> = executeAsync<Nothing, FlightSearchResponse>(operation)

    /**
     *
     * request for seatmap information
     * @param operation [GetFlightSeatmapOperation]
     * @throws ExpediaGroupApiFlightsV2ErrorsException
     * @throws ExpediaGroupApiAPIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [Response] object with a body of type SeatMapResponse
     */
    fun execute(operation: GetFlightSeatmapOperation): Response<SeatMapResponse> = execute<Nothing, SeatMapResponse>(operation)

    /**
     *
     * request for seatmap information
     * @param operation [GetFlightSeatmapOperation]
     * @throws ExpediaGroupApiFlightsV2ErrorsException
     * @throws ExpediaGroupApiAPIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [CompletableFuture<Response>] object with a body of type SeatMapResponse
     */
    fun executeAsync(operation: GetFlightSeatmapOperation): CompletableFuture<Response<SeatMapResponse>> = executeAsync<Nothing, SeatMapResponse>(operation)

    /**
     *
     * API that will return the lowest fares for multiple days so that the same can be displayed as a booking widget calendar.
     * @param operation [GetFlightsFarecalendarOperation]
     * @throws ExpediaGroupApiFlightsV3ErrorsException
     * @throws ExpediaGroupApiFlightsV3APIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [Response] object with a body of type FareCalendarResponse
     */
    fun execute(operation: GetFlightsFarecalendarOperation): Response<FareCalendarResponse> = execute<Nothing, FareCalendarResponse>(operation)

    /**
     *
     * API that will return the lowest fares for multiple days so that the same can be displayed as a booking widget calendar.
     * @param operation [GetFlightsFarecalendarOperation]
     * @throws ExpediaGroupApiFlightsV3ErrorsException
     * @throws ExpediaGroupApiFlightsV3APIMErrorException
     * @throws ExpediaGroupApiException
     * @return a [CompletableFuture<Response>] object with a body of type FareCalendarResponse
     */
    fun executeAsync(operation: GetFlightsFarecalendarOperation): CompletableFuture<Response<FareCalendarResponse>> = executeAsync<Nothing, FareCalendarResponse>(operation)

    /**
     * Get availability calendars of properties
     * Returns the availability of each day for a range of dates for given Expedia lodging properties.
     * @param operation [GetLodgingAvailabilityCalendarsOperation]
     * @throws ExpediaGroupApiLodgingErrorsException
     * @throws ExpediaGroupApiAPIGatewayErrorException
     * @return a [Response] object with a body of type AvailabilityCalendarResponse
     */
    fun execute(operation: GetLodgingAvailabilityCalendarsOperation): Response<AvailabilityCalendarResponse> = execute<Nothing, AvailabilityCalendarResponse>(operation)

    /**
     * Get availability calendars of properties
     * Returns the availability of each day for a range of dates for given Expedia lodging properties.
     * @param operation [GetLodgingAvailabilityCalendarsOperation]
     * @throws ExpediaGroupApiLodgingErrorsException
     * @throws ExpediaGroupApiAPIGatewayErrorException
     * @return a [CompletableFuture<Response>] object with a body of type AvailabilityCalendarResponse
     */
    fun executeAsync(operation: GetLodgingAvailabilityCalendarsOperation): CompletableFuture<Response<AvailabilityCalendarResponse>> = executeAsync<Nothing, AvailabilityCalendarResponse>(operation)

    /**
     * Get Extended information with a single property offer
     * Extended information about the rate, charges, fees, and financial terms associated with booking a single lodging rate plan offer.
     * @param operation [GetLodgingDetailsOperation]
     * @throws ExpediaGroupApiErrorsException
     * @throws ExpediaGroupApiAPIGatewayErrorException
     * @return a [Response] object with a body of type HotelDetailsResponse
     */
    fun execute(operation: GetLodgingDetailsOperation): Response<HotelDetailsResponse> = execute<Nothing, HotelDetailsResponse>(operation)

    /**
     * Get Extended information with a single property offer
     * Extended information about the rate, charges, fees, and financial terms associated with booking a single lodging rate plan offer.
     * @param operation [GetLodgingDetailsOperation]
     * @throws ExpediaGroupApiErrorsException
     * @throws ExpediaGroupApiAPIGatewayErrorException
     * @return a [CompletableFuture<Response>] object with a body of type HotelDetailsResponse
     */
    fun executeAsync(operation: GetLodgingDetailsOperation): CompletableFuture<Response<HotelDetailsResponse>> = executeAsync<Nothing, HotelDetailsResponse>(operation)

    /**
     * Search lodging inventory
     * Search Expedia lodging inventory by Location Keyword, Region ID, Lat/Long, or Hotel ID(s) and return up to 1,000 offers in response.  Provides deeplink to Expedia site to book, or rate plan info to enable API booking.
     * @param operation [GetLodgingListingsOperation]
     * @throws ExpediaGroupApiErrorsException
     * @throws ExpediaGroupApiAPIGatewayErrorException
     * @return a [Response] object with a body of type HotelListingsResponse
     */
    fun execute(operation: GetLodgingListingsOperation): Response<HotelListingsResponse> = execute<Nothing, HotelListingsResponse>(operation)

    /**
     * Search lodging inventory
     * Search Expedia lodging inventory by Location Keyword, Region ID, Lat/Long, or Hotel ID(s) and return up to 1,000 offers in response.  Provides deeplink to Expedia site to book, or rate plan info to enable API booking.
     * @param operation [GetLodgingListingsOperation]
     * @throws ExpediaGroupApiErrorsException
     * @throws ExpediaGroupApiAPIGatewayErrorException
     * @return a [CompletableFuture<Response>] object with a body of type HotelListingsResponse
     */
    fun executeAsync(operation: GetLodgingListingsOperation): CompletableFuture<Response<HotelListingsResponse>> = executeAsync<Nothing, HotelListingsResponse>(operation)

    /**
     * Get properties price and availability information
     * The Lodging Quotes API will return the price and availability information for given Expedia lodging property ID(s).
     * @param operation [GetLodgingQuotesOperation]
     * @throws ExpediaGroupApiLodgingErrorsException
     * @throws ExpediaGroupApiAPIGatewayErrorException
     * @return a [Response] object with a body of type LodgingQuotesResponse
     */
    fun execute(operation: GetLodgingQuotesOperation): Response<LodgingQuotesResponse> = execute<Nothing, LodgingQuotesResponse>(operation)

    /**
     * Get properties price and availability information
     * The Lodging Quotes API will return the price and availability information for given Expedia lodging property ID(s).
     * @param operation [GetLodgingQuotesOperation]
     * @throws ExpediaGroupApiLodgingErrorsException
     * @throws ExpediaGroupApiAPIGatewayErrorException
     * @return a [CompletableFuture<Response>] object with a body of type LodgingQuotesResponse
     */
    fun executeAsync(operation: GetLodgingQuotesOperation): CompletableFuture<Response<LodgingQuotesResponse>> = executeAsync<Nothing, LodgingQuotesResponse>(operation)

    /**
     * Get rate calendar of a property
     * The Rate Calendar API will return the lowest rate plan for a range of days for one selected Expedia lodging property.
     * @param operation [GetLodgingRateCalendarOperation]
     * @throws ExpediaGroupApiErrorsException
     * @throws ExpediaGroupApiAPIGatewayErrorException
     * @return a [Response] object with a body of type RateCalendarResponse
     */
    fun execute(operation: GetLodgingRateCalendarOperation): Response<RateCalendarResponse> = execute<Nothing, RateCalendarResponse>(operation)

    /**
     * Get rate calendar of a property
     * The Rate Calendar API will return the lowest rate plan for a range of days for one selected Expedia lodging property.
     * @param operation [GetLodgingRateCalendarOperation]
     * @throws ExpediaGroupApiErrorsException
     * @throws ExpediaGroupApiAPIGatewayErrorException
     * @return a [CompletableFuture<Response>] object with a body of type RateCalendarResponse
     */
    fun executeAsync(operation: GetLodgingRateCalendarOperation): CompletableFuture<Response<RateCalendarResponse>> = executeAsync<Nothing, RateCalendarResponse>(operation)

    /**
     * Request for flight links
     * The API inputs define a particular flight itinerary. The API response will include deeplinks to Expedia Flight Infosite and/or an API query for details for the selected flight.
     * @param operation [PostFlightLinksOperation]
     * @throws ExpediaGroupApiErrorResponseException
     * @throws ExpediaGroupApiPostFlightLinks401ResponseException
     * @throws ExpediaGroupApiPostFlightLinks403ResponseException
     * @throws ExpediaGroupApiPostFlightLinks404ResponseException
     * @throws ExpediaGroupApiPostFlightLinks405ResponseException
     * @throws ExpediaGroupApiPostFlightLinks429ResponseException
     * @throws ExpediaGroupApiPostFlightLinks503ResponseException
     * @throws ExpediaGroupApiPostFlightLinks504ResponseException
     * @return a [Response] object with a body of type FlightLinksResponse
     */
    fun execute(operation: PostFlightLinksOperation): Response<FlightLinksResponse> = execute<FlightLinksRequest, FlightLinksResponse>(operation)

    /**
     * Request for flight links
     * The API inputs define a particular flight itinerary. The API response will include deeplinks to Expedia Flight Infosite and/or an API query for details for the selected flight.
     * @param operation [PostFlightLinksOperation]
     * @throws ExpediaGroupApiErrorResponseException
     * @throws ExpediaGroupApiPostFlightLinks401ResponseException
     * @throws ExpediaGroupApiPostFlightLinks403ResponseException
     * @throws ExpediaGroupApiPostFlightLinks404ResponseException
     * @throws ExpediaGroupApiPostFlightLinks405ResponseException
     * @throws ExpediaGroupApiPostFlightLinks429ResponseException
     * @throws ExpediaGroupApiPostFlightLinks503ResponseException
     * @throws ExpediaGroupApiPostFlightLinks504ResponseException
     * @return a [CompletableFuture<Response>] object with a body of type FlightLinksResponse
     */
    fun executeAsync(operation: PostFlightLinksOperation): CompletableFuture<Response<FlightLinksResponse>> = executeAsync<FlightLinksRequest, FlightLinksResponse>(operation)
}
