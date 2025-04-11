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
package com.expediagroup.sdk.xap.models.exception

import com.expediagroup.sdk.core.model.exception.service.ExpediaGroupApiException
import com.expediagroup.sdk.core.model.exception.service.ExpediaGroupServiceDefaultErrorException
import com.expediagroup.sdk.core.model.getTransactionId
import com.expediagroup.sdk.xap.models.*
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import kotlinx.coroutines.runBlocking

internal open class HttpStatusCodeRange(
    private val statusCode: String,
    val getException: (HttpResponse) -> ExpediaGroupApiException
) : Comparable<HttpStatusCodeRange> {
    open fun matches(statusCode: String): Boolean = if (isRangeDefinition()) this.statusCode.first() == statusCode.first() else this.statusCode == statusCode

    open fun isRangeDefinition(): Boolean = statusCode.matches(Regex("^[1-5]XX$"))

    override fun compareTo(other: HttpStatusCodeRange): Int = (if (this.isRangeDefinition()) 1 else 0).compareTo(if (other.isRangeDefinition()) 1 else 0)
}

internal object DefaultHttpStatusCodeRange : HttpStatusCodeRange(
    "DefaultHttpStatusCodeRange",
    { ExpediaGroupServiceDefaultErrorException(it.status.value, runBlocking { it.bodyAsText() }, it.request.headers.getTransactionId()) }
) {
    override fun matches(statusCode: String): Boolean = true

    override fun isRangeDefinition(): Boolean = true
}

internal object ErrorObjectMapper {
    private val defaultHttpStatusCodeRanges = listOf(DefaultHttpStatusCodeRange)
    private val httpStatusCodeRanges: Map<String, List<HttpStatusCodeRange>> =
        mapOf(
            Pair(
                "getActivityDetails",
                listOf(
                    HttpStatusCodeRange("400") {
                        ExpediaGroupApiActivitiesErrorsException(it.status.value, fetchErrorObject(it) as ActivitiesErrors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("401") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("500") {
                        ExpediaGroupApiActivitiesErrorsException(it.status.value, fetchErrorObject(it) as ActivitiesErrors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("503") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getActivityListings",
                listOf(
                    HttpStatusCodeRange("400") {
                        ExpediaGroupApiActivitiesErrorsException(it.status.value, fetchErrorObject(it) as ActivitiesErrors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("401") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("500") {
                        ExpediaGroupApiActivitiesErrorsException(it.status.value, fetchErrorObject(it) as ActivitiesErrors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("503") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getCarDetails",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiCarsErrorsException(it.status.value, fetchErrorObject(it) as CarsErrors, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("500") { ExpediaGroupApiCarsErrorsException(it.status.value, fetchErrorObject(it) as CarsErrors, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getCarsListings",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiCarsErrorsException(it.status.value, fetchErrorObject(it) as CarsErrors, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("401") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("503") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getFeedDownloadUrl",
                listOf(
                    HttpStatusCodeRange("400") {
                        ExpediaGroupApiPresignedUrlResponseException(it.status.value, fetchErrorObject(it) as PresignedUrlResponse, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("401") { ExpediaGroupApiSdpAPIMErrorException(it.status.value, fetchErrorObject(it) as SdpAPIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiSdpAPIMErrorException(it.status.value, fetchErrorObject(it) as SdpAPIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiSdpAPIMErrorException(it.status.value, fetchErrorObject(it) as SdpAPIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiSdpAPIMErrorException(it.status.value, fetchErrorObject(it) as SdpAPIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("500") {
                        ExpediaGroupApiPresignedUrlResponseException(it.status.value, fetchErrorObject(it) as PresignedUrlResponse, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("503") { ExpediaGroupApiSdpAPIMErrorException(it.status.value, fetchErrorObject(it) as SdpAPIMError, it.headers.getTransactionId()) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getFlightBagaggefee",
                listOf(
                    HttpStatusCodeRange("400") {
                        ExpediaGroupApiFlightsV1ErrorsException(it.status.value, fetchErrorObject(it) as FlightsV1Errors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("401") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("500") {
                        ExpediaGroupApiFlightsV1ErrorsException(it.status.value, fetchErrorObject(it) as FlightsV1Errors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("503") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getFlightDetails",
                listOf(
                    HttpStatusCodeRange("400") {
                        ExpediaGroupApiFlightsV3ErrorsException(it.status.value, fetchErrorObject(it) as FlightsV3Errors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("401") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("403") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("404") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("409") {
                        ExpediaGroupApiFlightsV3ErrorsException(it.status.value, fetchErrorObject(it) as FlightsV3Errors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("429") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("500") {
                        ExpediaGroupApiFlightsV3ErrorsException(it.status.value, fetchErrorObject(it) as FlightsV3Errors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("503") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getFlightFarerules",
                listOf(
                    HttpStatusCodeRange("400") {
                        ExpediaGroupApiFlightsV1ErrorsException(it.status.value, fetchErrorObject(it) as FlightsV1Errors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("401") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("500") {
                        ExpediaGroupApiFlightsV1ErrorsException(it.status.value, fetchErrorObject(it) as FlightsV1Errors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("503") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getFlightFlexsearch",
                listOf(
                    HttpStatusCodeRange("400") {
                        ExpediaGroupApiGetFlightFlexsearch400ResponseException(
                            it.status.value,
                            fetchErrorObject(it) as GetFlightFlexsearch400Response,
                            it.headers.getTransactionId()
                        )
                    },
                    HttpStatusCodeRange("401") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("403") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("404") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("429") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("500") {
                        ExpediaGroupApiGetFlightFlexsearch400ResponseException(
                            it.status.value,
                            fetchErrorObject(it) as GetFlightFlexsearch400Response,
                            it.headers.getTransactionId()
                        )
                    },
                    HttpStatusCodeRange("503") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getFlightListings",
                listOf(
                    HttpStatusCodeRange("400") {
                        ExpediaGroupApiFlightsV3ErrorsException(it.status.value, fetchErrorObject(it) as FlightsV3Errors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("401") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("403") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("404") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("409") {
                        ExpediaGroupApiFlightsV3ErrorsException(it.status.value, fetchErrorObject(it) as FlightsV3Errors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("429") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("500") {
                        ExpediaGroupApiFlightsV3ErrorsException(it.status.value, fetchErrorObject(it) as FlightsV3Errors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("503") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getFlightSeatmap",
                listOf(
                    HttpStatusCodeRange("400") {
                        ExpediaGroupApiFlightsV2ErrorsException(it.status.value, fetchErrorObject(it) as FlightsV2Errors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("401") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("403") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("404") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("429") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("500") {
                        ExpediaGroupApiFlightsV2ErrorsException(it.status.value, fetchErrorObject(it) as FlightsV2Errors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("503") { ExpediaGroupApiAPIMErrorException(it.status.value, fetchErrorObject(it) as APIMError, it.headers.getTransactionId()) },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getFlightsFarecalendar",
                listOf(
                    HttpStatusCodeRange("400") {
                        ExpediaGroupApiFlightsV3ErrorsException(it.status.value, fetchErrorObject(it) as FlightsV3Errors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("401") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("403") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("404") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("429") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("500") {
                        ExpediaGroupApiFlightsV3ErrorsException(it.status.value, fetchErrorObject(it) as FlightsV3Errors, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("503") {
                        ExpediaGroupApiFlightsV3APIMErrorException(it.status.value, fetchErrorObject(it) as FlightsV3APIMError, it.headers.getTransactionId())
                    },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getLodgingAvailabilityCalendars",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiLodgingErrorsException(it.status.value, fetchErrorObject(it) as LodgingErrors, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("401") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("403") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("429") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("500") { ExpediaGroupApiLodgingErrorsException(it.status.value, fetchErrorObject(it) as LodgingErrors, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("503") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("504") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getLodgingDetails",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorsException(it.status.value, fetchErrorObject(it) as Errors, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("401") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("403") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("429") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorsException(it.status.value, fetchErrorObject(it) as Errors, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("503") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("504") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getLodgingListings",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorsException(it.status.value, fetchErrorObject(it) as Errors, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("401") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("403") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("429") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorsException(it.status.value, fetchErrorObject(it) as Errors, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("503") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("504") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getLodgingQuotes",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiLodgingErrorsException(it.status.value, fetchErrorObject(it) as LodgingErrors, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("401") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("403") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("429") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("500") { ExpediaGroupApiLodgingErrorsException(it.status.value, fetchErrorObject(it) as LodgingErrors, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("503") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("504") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "getLodgingRateCalendar",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorsException(it.status.value, fetchErrorObject(it) as Errors, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("401") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("403") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("409") { ExpediaGroupApiErrorsException(it.status.value, fetchErrorObject(it) as Errors, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("429") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorsException(it.status.value, fetchErrorObject(it) as Errors, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("503") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("504") {
                        ExpediaGroupApiAPIGatewayErrorException(it.status.value, fetchErrorObject(it) as APIGatewayError, it.headers.getTransactionId())
                    },
                    DefaultHttpStatusCodeRange
                )
            ),
            Pair(
                "postFlightLinks",
                listOf(
                    HttpStatusCodeRange("400") { ExpediaGroupApiErrorResponseException(it.status.value, fetchErrorObject(it) as ErrorResponse, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("401") {
                        ExpediaGroupApiPostFlightLinks401ResponseException(it.status.value, fetchErrorObject(it) as PostFlightLinks401Response, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("403") {
                        ExpediaGroupApiPostFlightLinks403ResponseException(it.status.value, fetchErrorObject(it) as PostFlightLinks403Response, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("404") {
                        ExpediaGroupApiPostFlightLinks404ResponseException(it.status.value, fetchErrorObject(it) as PostFlightLinks404Response, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("405") {
                        ExpediaGroupApiPostFlightLinks405ResponseException(it.status.value, fetchErrorObject(it) as PostFlightLinks405Response, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("429") {
                        ExpediaGroupApiPostFlightLinks429ResponseException(it.status.value, fetchErrorObject(it) as PostFlightLinks429Response, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("500") { ExpediaGroupApiErrorResponseException(it.status.value, fetchErrorObject(it) as ErrorResponse, it.headers.getTransactionId()) },
                    HttpStatusCodeRange("503") {
                        ExpediaGroupApiPostFlightLinks503ResponseException(it.status.value, fetchErrorObject(it) as PostFlightLinks503Response, it.headers.getTransactionId())
                    },
                    HttpStatusCodeRange("504") {
                        ExpediaGroupApiPostFlightLinks504ResponseException(it.status.value, fetchErrorObject(it) as PostFlightLinks504Response, it.headers.getTransactionId())
                    },
                    DefaultHttpStatusCodeRange
                )
            )
        )

    fun process(
        httpResponse: HttpResponse,
        operationId: String
    ): ExpediaGroupApiException =
        httpStatusCodeRanges.getOrDefault(operationId, defaultHttpStatusCodeRanges).filter { it.matches(httpResponse.status.value.toString()) }.min().getException(httpResponse)

    private inline fun <reified T> fetchErrorObject(httpResponse: HttpResponse): T =
        runBlocking {
            runCatching {
                httpResponse.body<T>()
            }.getOrElse { throw ExpediaGroupServiceDefaultErrorException(httpResponse.status.value, httpResponse.bodyAsText(), httpResponse.request.headers.getTransactionId()) }
        }
}

class ExpediaGroupApiActivitiesErrorsException(code: Int, override val errorObject: ActivitiesErrors, transactionId: String?) : ExpediaGroupApiException(code, errorObject, transactionId)

class ExpediaGroupApiAPIMErrorException(code: Int, override val errorObject: APIMError, transactionId: String?) : ExpediaGroupApiException(code, errorObject, transactionId)

class ExpediaGroupApiCarsErrorsException(code: Int, override val errorObject: CarsErrors, transactionId: String?) : ExpediaGroupApiException(code, errorObject, transactionId)

class ExpediaGroupApiPresignedUrlResponseException(code: Int, override val errorObject: PresignedUrlResponse, transactionId: String?) : ExpediaGroupApiException(
    code,
    errorObject,
    transactionId
)

class ExpediaGroupApiSdpAPIMErrorException(code: Int, override val errorObject: SdpAPIMError, transactionId: String?) : ExpediaGroupApiException(code, errorObject, transactionId)

class ExpediaGroupApiFlightsV1ErrorsException(code: Int, override val errorObject: FlightsV1Errors, transactionId: String?) : ExpediaGroupApiException(code, errorObject, transactionId)

class ExpediaGroupApiFlightsV3ErrorsException(code: Int, override val errorObject: FlightsV3Errors, transactionId: String?) : ExpediaGroupApiException(code, errorObject, transactionId)

class ExpediaGroupApiFlightsV3APIMErrorException(code: Int, override val errorObject: FlightsV3APIMError, transactionId: String?) : ExpediaGroupApiException(code, errorObject, transactionId)

class ExpediaGroupApiGetFlightFlexsearch400ResponseException(code: Int, override val errorObject: GetFlightFlexsearch400Response, transactionId: String?) : ExpediaGroupApiException(
    code,
    errorObject,
    transactionId
)

class ExpediaGroupApiFlightsV2ErrorsException(code: Int, override val errorObject: FlightsV2Errors, transactionId: String?) : ExpediaGroupApiException(code, errorObject, transactionId)

class ExpediaGroupApiLodgingErrorsException(code: Int, override val errorObject: LodgingErrors, transactionId: String?) : ExpediaGroupApiException(code, errorObject, transactionId)

class ExpediaGroupApiAPIGatewayErrorException(code: Int, override val errorObject: APIGatewayError, transactionId: String?) : ExpediaGroupApiException(code, errorObject, transactionId)

class ExpediaGroupApiErrorsException(code: Int, override val errorObject: Errors, transactionId: String?) : ExpediaGroupApiException(code, errorObject, transactionId)

class ExpediaGroupApiErrorResponseException(code: Int, override val errorObject: ErrorResponse, transactionId: String?) : ExpediaGroupApiException(code, errorObject, transactionId)

class ExpediaGroupApiPostFlightLinks401ResponseException(code: Int, override val errorObject: PostFlightLinks401Response, transactionId: String?) : ExpediaGroupApiException(
    code,
    errorObject,
    transactionId
)

class ExpediaGroupApiPostFlightLinks403ResponseException(code: Int, override val errorObject: PostFlightLinks403Response, transactionId: String?) : ExpediaGroupApiException(
    code,
    errorObject,
    transactionId
)

class ExpediaGroupApiPostFlightLinks404ResponseException(code: Int, override val errorObject: PostFlightLinks404Response, transactionId: String?) : ExpediaGroupApiException(
    code,
    errorObject,
    transactionId
)

class ExpediaGroupApiPostFlightLinks405ResponseException(code: Int, override val errorObject: PostFlightLinks405Response, transactionId: String?) : ExpediaGroupApiException(
    code,
    errorObject,
    transactionId
)

class ExpediaGroupApiPostFlightLinks429ResponseException(code: Int, override val errorObject: PostFlightLinks429Response, transactionId: String?) : ExpediaGroupApiException(
    code,
    errorObject,
    transactionId
)

class ExpediaGroupApiPostFlightLinks503ResponseException(code: Int, override val errorObject: PostFlightLinks503Response, transactionId: String?) : ExpediaGroupApiException(
    code,
    errorObject,
    transactionId
)

class ExpediaGroupApiPostFlightLinks504ResponseException(code: Int, override val errorObject: PostFlightLinks504Response, transactionId: String?) : ExpediaGroupApiException(
    code,
    errorObject,
    transactionId
)
