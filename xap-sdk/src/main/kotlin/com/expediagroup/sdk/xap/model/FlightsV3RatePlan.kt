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
package com.expediagroup.sdk.xap.model

import com.expediagroup.sdk.core.common.getOrThrow
import com.expediagroup.sdk.xap.model.FlightsV3LodgingAmenity
import com.expediagroup.sdk.xap.model.FlightsV3StayDates
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for rate plan information.
 * @param roomTypeId The room type identifier.
 * @param ratePlanId The rate plan identifier.
 * @param inventorySourceId The identification number of the source that provides the rate plan.
 * @param rateRuleId The identifier of rate rule.
 * @param inventorySourceCode The source name that provided the rate plan.
 * @param stayDates
 * @param remainingCount The number of rooms remaining through Expedia for this room type. NOTE: This value does NOT represent the total number of rooms remaining at the hotel property, only the number of rooms allocated to Expedia for sale by the property that currently remain in Expedia's inventory. When a hotel is listed as 'sold out' by Expedia there may still be rooms available for sale by the hotel through other channels. CMA Compliance Note (UK): websites in the UK that display remainingCount should make it clear to consumers that this count refers to the number of rooms remaining within Expedia inventory - NOT the number remaining at the property.
 * @param freeInternet Indicates whether the price of the room includes free Internet. (either wireless or wired)
 * @param freeWiFi Indicates whether the price of the room includes free wireless Internet access.
 * @param freeInternetDetails The localized details for the free internet amenity (only shown when FreeInternet = true).
 * @param freeParking Indicates whether the price of the room includes free parking.
 * @param freeBreakfast Indicates whether the price of the room includes free breakfast.
 * @param freeBreakfastDetails The localized details for the free breakfast amenity (only shown when FreeBreakfast = true).
 * @param amenities The amenities of the rateplan.
 */
@ConsistentCopyVisibility data class FlightsV3RatePlan private constructor(
    /* The room type identifier. */
    @JsonProperty("RoomTypeId")
    val roomTypeId: kotlin.String,

    /* The rate plan identifier. */
    @JsonProperty("RatePlanId")
    val ratePlanId: kotlin.String,

    /* The identification number of the source that provides the rate plan. */
    @JsonProperty("InventorySourceId")
    val inventorySourceId: kotlin.String,

    /* The identifier of rate rule. */
    @JsonProperty("RateRuleId")
    val rateRuleId: kotlin.String? = null,

    /* The source name that provided the rate plan. */
    @JsonProperty("InventorySourceCode")
    val inventorySourceCode: kotlin.String? = null,

    @JsonProperty("StayDates")
    val stayDates: FlightsV3StayDates? = null,

    /* The number of rooms remaining through Expedia for this room type. NOTE: This value does NOT represent the total number of rooms remaining at the hotel property, only the number of rooms allocated to Expedia for sale by the property that currently remain in Expedia's inventory. When a hotel is listed as 'sold out' by Expedia there may still be rooms available for sale by the hotel through other channels. CMA Compliance Note (UK): websites in the UK that display remainingCount should make it clear to consumers that this count refers to the number of rooms remaining within Expedia inventory - NOT the number remaining at the property. */
    @JsonProperty("RemainingCount")
    val remainingCount: kotlin.Int? = null,

    /* Indicates whether the price of the room includes free Internet. (either wireless or wired) */
    @JsonProperty("FreeInternet")
    val freeInternet: kotlin.Boolean? = null,

    /* Indicates whether the price of the room includes free wireless Internet access. */
    @JsonProperty("FreeWiFi")
    val freeWiFi: kotlin.Boolean? = null,

    /* The localized details for the free internet amenity (only shown when FreeInternet = true). */
    @JsonProperty("FreeInternetDetails")
    val freeInternetDetails: kotlin.collections.List<kotlin.String>? = null,

    /* Indicates whether the price of the room includes free parking. */
    @JsonProperty("FreeParking")
    val freeParking: kotlin.Boolean? = null,

    /* Indicates whether the price of the room includes free breakfast. */
    @JsonProperty("FreeBreakfast")
    val freeBreakfast: kotlin.Boolean? = null,

    /* The localized details for the free breakfast amenity (only shown when FreeBreakfast = true). */
    @JsonProperty("FreeBreakfastDetails")
    val freeBreakfastDetails: kotlin.collections.List<kotlin.String>? = null,

    /* The amenities of the rateplan. */
    @JsonProperty("Amenities")
    val amenities: kotlin.collections.List<FlightsV3LodgingAmenity>? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var roomTypeId: kotlin.String? = null,
        private var ratePlanId: kotlin.String? = null,
        private var inventorySourceId: kotlin.String? = null,
        private var rateRuleId: kotlin.String? = null,
        private var inventorySourceCode: kotlin.String? = null,
        private var stayDates: FlightsV3StayDates? = null,
        private var remainingCount: kotlin.Int? = null,
        private var freeInternet: kotlin.Boolean? = null,
        private var freeWiFi: kotlin.Boolean? = null,
        private var freeInternetDetails: kotlin.collections.List<kotlin.String>? = null,
        private var freeParking: kotlin.Boolean? = null,
        private var freeBreakfast: kotlin.Boolean? = null,
        private var freeBreakfastDetails: kotlin.collections.List<kotlin.String>? = null,
        private var amenities: kotlin.collections.List<FlightsV3LodgingAmenity>? = null,
    ) {
        fun roomTypeId(roomTypeId: kotlin.String) = apply { this.roomTypeId = roomTypeId }

        fun ratePlanId(ratePlanId: kotlin.String) = apply { this.ratePlanId = ratePlanId }

        fun inventorySourceId(inventorySourceId: kotlin.String) = apply { this.inventorySourceId = inventorySourceId }

        fun rateRuleId(rateRuleId: kotlin.String?) = apply { this.rateRuleId = rateRuleId }

        fun inventorySourceCode(inventorySourceCode: kotlin.String?) = apply { this.inventorySourceCode = inventorySourceCode }

        fun stayDates(stayDates: FlightsV3StayDates?) = apply { this.stayDates = stayDates }

        fun remainingCount(remainingCount: kotlin.Int?) = apply { this.remainingCount = remainingCount }

        fun freeInternet(freeInternet: kotlin.Boolean?) = apply { this.freeInternet = freeInternet }

        fun freeWiFi(freeWiFi: kotlin.Boolean?) = apply { this.freeWiFi = freeWiFi }

        fun freeInternetDetails(freeInternetDetails: kotlin.collections.List<kotlin.String>?) = apply { this.freeInternetDetails = freeInternetDetails }

        fun freeParking(freeParking: kotlin.Boolean?) = apply { this.freeParking = freeParking }

        fun freeBreakfast(freeBreakfast: kotlin.Boolean?) = apply { this.freeBreakfast = freeBreakfast }

        fun freeBreakfastDetails(freeBreakfastDetails: kotlin.collections.List<kotlin.String>?) = apply { this.freeBreakfastDetails = freeBreakfastDetails }

        fun amenities(amenities: kotlin.collections.List<FlightsV3LodgingAmenity>?) = apply { this.amenities = amenities }

        fun build(): FlightsV3RatePlan {
            val roomTypeId = this.roomTypeId.getOrThrow {
                IllegalArgumentException("roomTypeId must not be null")
            }

            val ratePlanId = this.ratePlanId.getOrThrow {
                IllegalArgumentException("ratePlanId must not be null")
            }

            val inventorySourceId = this.inventorySourceId.getOrThrow {
                IllegalArgumentException("inventorySourceId must not be null")
            }

            val instance = FlightsV3RatePlan(
                roomTypeId = roomTypeId,
                ratePlanId = ratePlanId,
                inventorySourceId = inventorySourceId,
                rateRuleId = rateRuleId,
                inventorySourceCode = inventorySourceCode,
                stayDates = stayDates,
                remainingCount = remainingCount,
                freeInternet = freeInternet,
                freeWiFi = freeWiFi,
                freeInternetDetails = freeInternetDetails,
                freeParking = freeParking,
                freeBreakfast = freeBreakfast,
                freeBreakfastDetails = freeBreakfastDetails,
                amenities = amenities,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        roomTypeId = roomTypeId,
        ratePlanId = ratePlanId,
        inventorySourceId = inventorySourceId,
        rateRuleId = rateRuleId,
        inventorySourceCode = inventorySourceCode,
        stayDates = stayDates,
        remainingCount = remainingCount,
        freeInternet = freeInternet,
        freeWiFi = freeWiFi,
        freeInternetDetails = freeInternetDetails,
        freeParking = freeParking,
        freeBreakfast = freeBreakfast,
        freeBreakfastDetails = freeBreakfastDetails,
        amenities = amenities,
    )
}
