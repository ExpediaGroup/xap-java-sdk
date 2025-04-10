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
 * @property segment1Origin 3-letter IATA Airport code or location name from where the passenger is departing.
 * @property segment1Destination 3-letter IATA Airport code or location name to where the passenger is arriving.
 * @property segment1DepartureDate Date, in ISO format [YYYY-MM-DD], on which customer wants to depart.
 * @property segment2Origin 3-letter IATA Airport code or location name from where the passenger is departing.
 * @property segment2Destination 3-letter IATA Airport code or location name to where the passenger is arriving.
 * @property segment2DepartureDate Date, in ISO format [YYYY-MM-DD], on which customer wants to depart.
 * @property includeSegmentDetails If included, the response will included all segment and leg details. By default, the value of this parameter will be false.
 */
@JsonDeserialize(builder = GetFlightFlexsearchOperationParams.Builder::class)
data class GetFlightFlexsearchOperationParams(
    @field:NotNull
    @field:Valid
    val partnerTransactionID: kotlin.String,
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
    val segment2Origin: kotlin.String? =
        null,
    @field:Valid
    val segment2Destination: kotlin.String? =
        null,
    @field:Valid
    val segment2DepartureDate: kotlin.String? =
        null,
    @field:Valid
    val includeSegmentDetails: kotlin.Boolean? =
        null
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("Partner-Transaction-ID") private var partnerTransactionID: kotlin.String? = null,
        @JsonProperty("segment1.origin") private var segment1Origin: kotlin.String? = null,
        @JsonProperty("segment1.destination") private var segment1Destination: kotlin.String? = null,
        @JsonProperty("segment1.departureDate") private var segment1DepartureDate: java.time.LocalDate? = null,
        @JsonProperty("segment2.origin") private var segment2Origin: kotlin.String? = null,
        @JsonProperty("segment2.destination") private var segment2Destination: kotlin.String? = null,
        @JsonProperty("segment2.departureDate") private var segment2DepartureDate: kotlin.String? = null,
        @JsonProperty("includeSegmentDetails") private var includeSegmentDetails: kotlin.Boolean? = null
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
         * @param segment1Destination 3-letter IATA Airport code or location name to where the passenger is arriving.
         */
        fun segment1Destination(segment1Destination: kotlin.String) = apply { this.segment1Destination = segment1Destination }

        /**
         * @param segment1DepartureDate Date, in ISO format [YYYY-MM-DD], on which customer wants to depart.
         */
        fun segment1DepartureDate(segment1DepartureDate: java.time.LocalDate) = apply { this.segment1DepartureDate = segment1DepartureDate }

        /**
         * @param segment2Origin 3-letter IATA Airport code or location name from where the passenger is departing.
         */
        fun segment2Origin(segment2Origin: kotlin.String) = apply { this.segment2Origin = segment2Origin }

        /**
         * @param segment2Destination 3-letter IATA Airport code or location name to where the passenger is arriving.
         */
        fun segment2Destination(segment2Destination: kotlin.String) = apply { this.segment2Destination = segment2Destination }

        /**
         * @param segment2DepartureDate Date, in ISO format [YYYY-MM-DD], on which customer wants to depart.
         */
        fun segment2DepartureDate(segment2DepartureDate: kotlin.String) = apply { this.segment2DepartureDate = segment2DepartureDate }

        /**
         * @param includeSegmentDetails If included, the response will included all segment and leg details. By default, the value of this parameter will be false.
         */
        fun includeSegmentDetails(includeSegmentDetails: kotlin.Boolean) = apply { this.includeSegmentDetails = includeSegmentDetails }

        fun build(): GetFlightFlexsearchOperationParams {
            val params =
                GetFlightFlexsearchOperationParams(
                    partnerTransactionID = partnerTransactionID!!,
                    segment1Origin = segment1Origin!!,
                    segment1Destination = segment1Destination!!,
                    segment1DepartureDate = segment1DepartureDate!!,
                    segment2Origin = segment2Origin,
                    segment2Destination = segment2Destination,
                    segment2DepartureDate = segment2DepartureDate,
                    includeSegmentDetails = includeSegmentDetails
                )

            validate(params)

            return params
        }

        private fun validate(params: GetFlightFlexsearchOperationParams) {
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
            segment1Origin = segment1Origin,
            segment1Destination = segment1Destination,
            segment1DepartureDate = segment1DepartureDate,
            segment2Origin = segment2Origin,
            segment2Destination = segment2Destination,
            segment2DepartureDate = segment2DepartureDate,
            includeSegmentDetails = includeSegmentDetails
        )

    override fun getHeaders(): Headers =
        Headers.build {
            partnerTransactionID?.let {
                append("Partner-Transaction-ID", it)
            }
            append("Accept", "application/vnd.exp-flight.v3+json")
        }

    override fun getQueryParams(): Parameters =
        Parameters.build {
            segment1Origin?.let {
                append("segment1.origin", it)
            }
            segment1Destination?.let {
                append("segment1.destination", it)
            }
            segment1DepartureDate?.let {
                append("segment1.departureDate", it.toString())
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
            includeSegmentDetails?.let {
                append("includeSegmentDetails", it.toString())
            }
        }

    override fun getPathParams(): Map<String, String> =
        buildMap {
        }
}
