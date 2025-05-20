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
 * @property offerToken An Air offerToken from a Flight Search or Flight Details.
 * @property partnerTransactionID Partner-generated identifier.
 * @property locale The language in which the response content should be displayed.
 * @property loyaltyProgramNumber A Traveler's Loyalty Program number for a specified carrier.
 * @property travelerFirstName Traveler's first name of whose loyalty information is passed (mandatory if you are entering a loyalty number).
 * @property travelerLastName Traveler's last name of whose loyalty information is passed (mandatory if you are entering a loyalty number).
 */
@JsonDeserialize(builder = GetFlightSeatmapOperationParams.Builder::class)
data class GetFlightSeatmapOperationParams(
    val offerToken: kotlin.String,
    val partnerTransactionID: kotlin.String,
    val locale: kotlin.String? =
        null,
    val loyaltyProgramNumber: kotlin.String? =
        null,
    val travelerFirstName: kotlin.String? =
        null,
    val travelerLastName: kotlin.String? =
        null,
) {
    init {
        require(offerToken != null) { "offerToken must not be null" }

        require(partnerTransactionID != null) { "partnerTransactionID must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("offerToken") private var offerToken: kotlin.String? = null,
        @JsonProperty("Partner-Transaction-ID") private var partnerTransactionID: kotlin.String? = null,
        @JsonProperty("locale") private var locale: kotlin.String? = null,
        @JsonProperty("loyaltyProgramNumber") private var loyaltyProgramNumber: kotlin.String? = null,
        @JsonProperty("travelerFirstName") private var travelerFirstName: kotlin.String? = null,
        @JsonProperty("travelerLastName") private var travelerLastName: kotlin.String? = null,
    ) {
        /**
         * @param offerToken An Air offerToken from a Flight Search or Flight Details.
         */
        fun offerToken(offerToken: kotlin.String) = apply { this.offerToken = offerToken }

        /**
         * @param partnerTransactionID Partner-generated identifier.
         */
        fun partnerTransactionID(partnerTransactionID: kotlin.String) = apply { this.partnerTransactionID = partnerTransactionID }

        /**
         * @param locale The language in which the response content should be displayed.
         */
        fun locale(locale: kotlin.String) = apply { this.locale = locale }

        /**
         * @param loyaltyProgramNumber A Traveler's Loyalty Program number for a specified carrier.
         */
        fun loyaltyProgramNumber(loyaltyProgramNumber: kotlin.String) = apply { this.loyaltyProgramNumber = loyaltyProgramNumber }

        /**
         * @param travelerFirstName Traveler's first name of whose loyalty information is passed (mandatory if you are entering a loyalty number).
         */
        fun travelerFirstName(travelerFirstName: kotlin.String) = apply { this.travelerFirstName = travelerFirstName }

        /**
         * @param travelerLastName Traveler's last name of whose loyalty information is passed (mandatory if you are entering a loyalty number).
         */
        fun travelerLastName(travelerLastName: kotlin.String) = apply { this.travelerLastName = travelerLastName }

        fun build(): GetFlightSeatmapOperationParams {
            val params =
                GetFlightSeatmapOperationParams(
                    offerToken = offerToken!!,
                    partnerTransactionID = partnerTransactionID!!,
                    locale = locale,
                    loyaltyProgramNumber = loyaltyProgramNumber,
                    travelerFirstName = travelerFirstName,
                    travelerLastName = travelerLastName,
                )

            return params
        }
    }

    fun toBuilder() =
        Builder(
            offerToken = offerToken,
            partnerTransactionID = partnerTransactionID,
            locale = locale,
            loyaltyProgramNumber = loyaltyProgramNumber,
            travelerFirstName = travelerFirstName,
            travelerLastName = travelerLastName,
        )

    fun getHeaders(): Headers =
        Headers
            .builder()
            .apply {
                partnerTransactionID?.let {
                    add("Partner-Transaction-ID", it)
                }
                add("Accept", "application/vnd.exp-flight.v2+json")
            }.build()

    fun getQueryParams(): List<UrlQueryParam> =
        buildList {
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
            loyaltyProgramNumber?.let {
                val key = "loyaltyProgramNumber"
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
            travelerFirstName?.let {
                val key = "travelerFirstName"
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
            travelerLastName?.let {
                val key = "travelerLastName"
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
        }

    fun getPathParams(): Map<String, String> =
        buildMap {
            offerToken?.also {
                put("offerToken", offerToken)
            }
        }
}
