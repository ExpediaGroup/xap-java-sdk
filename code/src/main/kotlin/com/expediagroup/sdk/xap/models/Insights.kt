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
package com.expediagroup.sdk.xap.models

import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.FlightsV3Money
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container for insights response.
 * @param cheapestOfferId Container for insights response.
 * @param averageCheapestPrice
 * @param medianCheapestPrice
 * @param searchComparisonCount Number of searches compared to build the insights information for the given search.
 * @param percentageOfFaresHigher Percentage value that defines where the cheapest fare of the current search result stand comparing the prices for similar searches for nearby dates.
 * @param priceTrend Shows how the price trends
 * @param pastPerDayMedianCheapestPrices List of median of cheapest prices for the specifc search criteria for last 21 days. List will be in the order starting from 21 days ago till yesterday.
 * @param message Shows a custom message in the respective language corresponding to the locale requested.
 */
data class Insights(
    // Container for insights response.
    @JsonProperty("CheapestOfferId")
    @field:NotNull
    @field:Valid
    val cheapestOfferId: kotlin.String,
    @JsonProperty("AverageCheapestPrice")
    @field:NotNull
    @field:Valid
    val averageCheapestPrice: FlightsV3Money,
    @JsonProperty("MedianCheapestPrice")
    @field:NotNull
    @field:Valid
    val medianCheapestPrice: FlightsV3Money,
    // Number of searches compared to build the insights information for the given search.
    @JsonProperty("SearchComparisonCount")
    val searchComparisonCount: kotlin.Int,
    // Percentage value that defines where the cheapest fare of the current search result stand comparing the prices for similar searches for nearby dates.
    @JsonProperty("PercentageOfFaresHigher")
    val percentageOfFaresHigher: kotlin.Int,
    // Shows how the price trends
    @JsonProperty("PriceTrend")
    val priceTrend: Insights.PriceTrend? = null,
    // List of median of cheapest prices for the specifc search criteria for last 21 days. List will be in the order starting from 21 days ago till yesterday.
    @JsonProperty("PastPerDayMedianCheapestPrices")
    @field:Valid
    val pastPerDayMedianCheapestPrices: kotlin.collections.List<kotlin.String>? = null,
    // Shows a custom message in the respective language corresponding to the locale requested.
    @JsonProperty("Message")
    @field:Valid
    val message: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var cheapestOfferId: kotlin.String? = null,
        private var averageCheapestPrice: FlightsV3Money? = null,
        private var medianCheapestPrice: FlightsV3Money? = null,
        private var searchComparisonCount: kotlin.Int? = null,
        private var percentageOfFaresHigher: kotlin.Int? = null,
        private var priceTrend: Insights.PriceTrend? = null,
        private var pastPerDayMedianCheapestPrices: kotlin.collections.List<kotlin.String>? = null,
        private var message: kotlin.String? = null
    ) {
        fun cheapestOfferId(cheapestOfferId: kotlin.String) = apply { this.cheapestOfferId = cheapestOfferId }

        fun averageCheapestPrice(averageCheapestPrice: FlightsV3Money) = apply { this.averageCheapestPrice = averageCheapestPrice }

        fun medianCheapestPrice(medianCheapestPrice: FlightsV3Money) = apply { this.medianCheapestPrice = medianCheapestPrice }

        fun searchComparisonCount(searchComparisonCount: kotlin.Int) = apply { this.searchComparisonCount = searchComparisonCount }

        fun percentageOfFaresHigher(percentageOfFaresHigher: kotlin.Int) = apply { this.percentageOfFaresHigher = percentageOfFaresHigher }

        fun priceTrend(priceTrend: Insights.PriceTrend?) = apply { this.priceTrend = priceTrend }

        fun pastPerDayMedianCheapestPrices(pastPerDayMedianCheapestPrices: kotlin.collections.List<kotlin.String>?) =
            apply {
                this.pastPerDayMedianCheapestPrices =
                    pastPerDayMedianCheapestPrices
            }

        fun message(message: kotlin.String?) = apply { this.message = message }

        fun build(): Insights {
            val instance =
                Insights(
                    cheapestOfferId = cheapestOfferId!!,
                    averageCheapestPrice = averageCheapestPrice!!,
                    medianCheapestPrice = medianCheapestPrice!!,
                    searchComparisonCount = searchComparisonCount!!,
                    percentageOfFaresHigher = percentageOfFaresHigher!!,
                    priceTrend = priceTrend,
                    pastPerDayMedianCheapestPrices = pastPerDayMedianCheapestPrices,
                    message = message
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Insights) {
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
            cheapestOfferId = cheapestOfferId!!,
            averageCheapestPrice = averageCheapestPrice!!,
            medianCheapestPrice = medianCheapestPrice!!,
            searchComparisonCount = searchComparisonCount!!,
            percentageOfFaresHigher = percentageOfFaresHigher!!,
            priceTrend = priceTrend,
            pastPerDayMedianCheapestPrices = pastPerDayMedianCheapestPrices,
            message = message
        )

    /**
     * Shows how the price trends
     * Values: TRENDING_UP,TRENDING_DOWN,NEUTRAL
     */
    enum class PriceTrend(val value: kotlin.String) {
        @JsonProperty("TRENDING_UP")
        TRENDING_UP("TRENDING_UP"),

        @JsonProperty("TRENDING_DOWN")
        TRENDING_DOWN("TRENDING_DOWN"),

        @JsonProperty("NEUTRAL")
        NEUTRAL("NEUTRAL")
    }
}
