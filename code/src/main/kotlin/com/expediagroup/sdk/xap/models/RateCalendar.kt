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
/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.expediagroup.sdk.xap.models

import com.expediagroup.sdk.xap.models.RateCalendarPrice
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * The lowest rate information of requested days
 * @param stayDate Stay date for which the price is returned.
 * @param price
 * @param priceLevel Indicates how that day's price compares to the other lowest price for that hotel over the searched date range. Prices will be bucketed into LOW/MEDIUM/HIGH. Here are the details for each `PriceLevel`: - HIGH: 65th percentile+ - MEDIUM: 30th Percentile+ - LOW: Anything lower than 30th percentile
 * @param status Represents whether the offer is currently available.
 */
data class RateCalendar(
    // Stay date for which the price is returned.
    @JsonProperty("StayDate")
    val stayDate: java.time.LocalDate? = null,
    @JsonProperty("Price")
    @field:Valid
    val price: RateCalendarPrice? = null,
    // Indicates how that day's price compares to the other lowest price for that hotel over the searched date range. Prices will be bucketed into LOW/MEDIUM/HIGH. Here are the details for each `PriceLevel`: - HIGH: 65th percentile+ - MEDIUM: 30th Percentile+ - LOW: Anything lower than 30th percentile
    @JsonProperty("PriceLevel")
    val priceLevel: RateCalendar.PriceLevel? = null,
    // Represents whether the offer is currently available.
    @JsonProperty("Status")
    val status: RateCalendar.Status? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var stayDate: java.time.LocalDate? = null,
        private var price: RateCalendarPrice? = null,
        private var priceLevel: RateCalendar.PriceLevel? = null,
        private var status: RateCalendar.Status? = null
    ) {
        fun stayDate(stayDate: java.time.LocalDate?) = apply { this.stayDate = stayDate }

        fun price(price: RateCalendarPrice?) = apply { this.price = price }

        fun priceLevel(priceLevel: RateCalendar.PriceLevel?) = apply { this.priceLevel = priceLevel }

        fun status(status: RateCalendar.Status?) = apply { this.status = status }

        fun build(): RateCalendar {
            return RateCalendar(
                stayDate = stayDate,
                price = price,
                priceLevel = priceLevel,
                status = status
            )
        }
    }

    fun toBuilder() =
        Builder(
            stayDate = stayDate,
            price = price,
            priceLevel = priceLevel,
            status = status
        )

    /**
     * Indicates how that day's price compares to the other lowest price for that hotel over the searched date range. Prices will be bucketed into LOW/MEDIUM/HIGH. Here are the details for each `PriceLevel`: - HIGH: 65th percentile+ - MEDIUM: 30th Percentile+ - LOW: Anything lower than 30th percentile
     * Values: HIGH,MEDIUM,LOW
     */
    enum class PriceLevel(val value: kotlin.String) {
        @JsonProperty("HIGH")
        HIGH("HIGH"),

        @JsonProperty("MEDIUM")
        MEDIUM("MEDIUM"),

        @JsonProperty("LOW")
        LOW("LOW")
    }

    /**
     * Represents whether the offer is currently available.
     * Values: AVAILABLE,NOT_AVAILABLE
     */
    enum class Status(val value: kotlin.String) {
        @JsonProperty("AVAILABLE")
        AVAILABLE("AVAILABLE"),

        @JsonProperty("NOT_AVAILABLE")
        NOT_AVAILABLE("NOT_AVAILABLE")
    }
}
