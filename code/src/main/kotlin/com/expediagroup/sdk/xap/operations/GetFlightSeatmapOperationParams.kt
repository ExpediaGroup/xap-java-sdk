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
 * @property offerToken An Air offerToken from a Flight Search or Flight Details.
 * @property partnerTransactionID Partner-generated identifier.
 * @property accept Accept header for the request.
 * @property locale The language in which the response content should be displayed.
 * @property loyaltyProgramNumber A Traveler's Loyalty Program number for a specified carrier.
 * @property travelerFirstName Traveler's first name of whose loyalty information is passed (mandatory if you are entering a loyalty number).
 * @property travelerLastName Traveler's last name of whose loyalty information is passed (mandatory if you are entering a loyalty number).
 */
@JsonDeserialize(builder = GetFlightSeatmapOperationParams.Builder::class)
data class GetFlightSeatmapOperationParams(
    @field:NotNull
    @field:Valid
    val offerToken: kotlin.String,
    @field:NotNull
    @field:Valid
    val partnerTransactionID: kotlin.String,
    @field:NotNull
    @field:Valid
    val accept: kotlin.String,
    @field:Valid
    val locale: kotlin.String? =
        null,
    @field:Valid
    val loyaltyProgramNumber: kotlin.String? =
        null,
    @field:Valid
    val travelerFirstName: kotlin.String? =
        null,
    @field:Valid
    val travelerLastName: kotlin.String? =
        null
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("offerToken") private var offerToken: kotlin.String? = null,
        @JsonProperty("Partner-Transaction-ID") private var partnerTransactionID: kotlin.String? = null,
        @JsonProperty("Accept") private var accept: kotlin.String? = null,
        @JsonProperty("locale") private var locale: kotlin.String? = null,
        @JsonProperty("loyaltyProgramNumber") private var loyaltyProgramNumber: kotlin.String? = null,
        @JsonProperty("travelerFirstName") private var travelerFirstName: kotlin.String? = null,
        @JsonProperty("travelerLastName") private var travelerLastName: kotlin.String? = null
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
         * @param accept Accept header for the request.
         */
        fun accept(accept: kotlin.String) = apply { this.accept = accept }

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
                    accept = accept!!,
                    locale = locale,
                    loyaltyProgramNumber = loyaltyProgramNumber,
                    travelerFirstName = travelerFirstName,
                    travelerLastName = travelerLastName
                )

            validate(params)

            return params
        }

        private fun validate(params: GetFlightSeatmapOperationParams) {
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
            offerToken = offerToken,
            partnerTransactionID = partnerTransactionID,
            accept = accept,
            locale = locale,
            loyaltyProgramNumber = loyaltyProgramNumber,
            travelerFirstName = travelerFirstName,
            travelerLastName = travelerLastName
        )

    override fun getHeaders(): Headers =
        Headers.build {
            partnerTransactionID?.let {
                append("Partner-Transaction-ID", it)
            }
            accept?.let {
                append("Accept", it)
            }
            append("Accept", "application/vnd.exp-flight.v2+json")
        }

    override fun getQueryParams(): Parameters =
        Parameters.build {
            locale?.let {
                append("locale", it)
            }
            loyaltyProgramNumber?.let {
                append("loyaltyProgramNumber", it)
            }
            travelerFirstName?.let {
                append("travelerFirstName", it)
            }
            travelerLastName?.let {
                append("travelerLastName", it)
            }
        }

    override fun getPathParams(): Map<String, String> =
        buildMap {
            offerToken?.also {
                put("offerToken", offerToken)
            }
        }
}
