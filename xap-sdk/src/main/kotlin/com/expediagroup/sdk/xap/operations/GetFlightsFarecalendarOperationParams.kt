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
 * @property segment1Origin 3-letter IATA Airport code or location name from where the passenger is departing.
 * @property segment1Destination 3-letter IATA Airport code or location name from where the passenger is arriving.
 * @property segment2Origin 3-letter IATA Airport code or location name from where the passenger is departing in the second segment.
 * @property segment2Destination 3-letter IATA Airport code or location name from where the passenger is arriving in the second segment.
 * @property departureDate Designates the date which decides the fare calendar response. For One Way trips, the response shows offers with price from departureDate till departureDate + 60 days For RoundTrip, If the trip=outbound, then the response shows offers with price from departureDate of outbound segment till departureDate + 60 days. If the trip=inbound, then the response shows offers with price from departureDate of inbound segment till departureDate + 14 days.
 * @property trip Required for RoundTrip for identifying whether we are requesting for inbound or outbound.  Possible values :  inbound  outbound
 * @property includeSegmentDetails If the user wants to know the segment details of the offer then they can send this param as true and will get the segment and leg level details in the response. By default this will be set as false.
 */
@JsonDeserialize(builder = GetFlightsFarecalendarOperationParams.Builder::class)
data class GetFlightsFarecalendarOperationParams(
    val partnerTransactionID: kotlin.String,
    val segment1Origin: kotlin.String,
    val segment1Destination: kotlin.String,
    val segment2Origin: kotlin.String? =
        null,
    val segment2Destination: kotlin.String? =
        null,
    val departureDate: java.time.LocalDate,
    val trip: kotlin.String? =
        null,
    val includeSegmentDetails: kotlin.Boolean? =
        null,
) {
    init {
        require(partnerTransactionID != null) { "partnerTransactionID must not be null" }

        require(segment1Origin != null) { "segment1Origin must not be null" }

        require(segment1Destination != null) { "segment1Destination must not be null" }

        require(departureDate != null) { "departureDate must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("Partner-Transaction-ID") private var partnerTransactionID: kotlin.String? = null,
        @JsonProperty("segment1.origin") private var segment1Origin: kotlin.String? = null,
        @JsonProperty("segment1.destination") private var segment1Destination: kotlin.String? = null,
        @JsonProperty("segment2.origin") private var segment2Origin: kotlin.String? = null,
        @JsonProperty("segment2.destination") private var segment2Destination: kotlin.String? = null,
        @JsonProperty("departureDate") private var departureDate: java.time.LocalDate? = null,
        @JsonProperty("trip") private var trip: kotlin.String? = null,
        @JsonProperty("includeSegmentDetails") private var includeSegmentDetails: kotlin.Boolean? = null,
    ) {
        /**
         * @param partnerTransactionID Partner-generated identifier.
         */
        fun partnerTransactionID(partnerTransactionID: kotlin.String) = apply { this.partnerTransactionID = partnerTransactionID }

        /**
         * @param segment1Origin 3-letter IATA Airport code or location name from where the passenger is departing.
         */
        fun segment1Origin(segment1Origin: kotlin.String) = apply { this.segment1Origin = segment1Origin }

        /**
         * @param segment1Destination 3-letter IATA Airport code or location name from where the passenger is arriving.
         */
        fun segment1Destination(segment1Destination: kotlin.String) = apply { this.segment1Destination = segment1Destination }

        /**
         * @param segment2Origin 3-letter IATA Airport code or location name from where the passenger is departing in the second segment.
         */
        fun segment2Origin(segment2Origin: kotlin.String) = apply { this.segment2Origin = segment2Origin }

        /**
         * @param segment2Destination 3-letter IATA Airport code or location name from where the passenger is arriving in the second segment.
         */
        fun segment2Destination(segment2Destination: kotlin.String) = apply { this.segment2Destination = segment2Destination }

        /**
         * @param departureDate Designates the date which decides the fare calendar response. For One Way trips, the response shows offers with price from departureDate till departureDate + 60 days For RoundTrip, If the trip=outbound, then the response shows offers with price from departureDate of outbound segment till departureDate + 60 days. If the trip=inbound, then the response shows offers with price from departureDate of inbound segment till departureDate + 14 days.
         */
        fun departureDate(departureDate: java.time.LocalDate) = apply { this.departureDate = departureDate }

        /**
         * @param trip Required for RoundTrip for identifying whether we are requesting for inbound or outbound.  Possible values :  inbound  outbound
         */
        fun trip(trip: kotlin.String) = apply { this.trip = trip }

        /**
         * @param includeSegmentDetails If the user wants to know the segment details of the offer then they can send this param as true and will get the segment and leg level details in the response. By default this will be set as false.
         */
        fun includeSegmentDetails(includeSegmentDetails: kotlin.Boolean) = apply { this.includeSegmentDetails = includeSegmentDetails }

        fun build(): GetFlightsFarecalendarOperationParams {
            val params =
                GetFlightsFarecalendarOperationParams(
                    partnerTransactionID = partnerTransactionID!!,
                    segment1Origin = segment1Origin!!,
                    segment1Destination = segment1Destination!!,
                    segment2Origin = segment2Origin,
                    segment2Destination = segment2Destination,
                    departureDate = departureDate!!,
                    trip = trip,
                    includeSegmentDetails = includeSegmentDetails,
                )

            return params
        }
    }

    fun toBuilder() =
        Builder(
            partnerTransactionID = partnerTransactionID,
            segment1Origin = segment1Origin,
            segment1Destination = segment1Destination,
            segment2Origin = segment2Origin,
            segment2Destination = segment2Destination,
            departureDate = departureDate,
            trip = trip,
            includeSegmentDetails = includeSegmentDetails,
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
            departureDate?.let {
                val key = "departureDate"
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
            trip?.let {
                val key = "trip"
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
            includeSegmentDetails?.let {
                val key = "includeSegmentDetails"
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
