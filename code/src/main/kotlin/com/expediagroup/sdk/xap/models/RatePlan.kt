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
import com.expediagroup.sdk.xap.models.CancellationPolicy
import com.expediagroup.sdk.xap.models.PaymentSchedule
import com.expediagroup.sdk.xap.models.Promotion
import com.expediagroup.sdk.xap.models.RatePlanAmenitiesInner
import com.expediagroup.sdk.xap.models.RatePlanPrice
import com.expediagroup.sdk.xap.models.RatePlanStandalonePrice
import com.expediagroup.sdk.xap.models.StayDates
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Container for information on each rate plan.
 * @param roomTypeId The room type identifier.
 * @param ratePlanId The rate plan identifier.
 * @param rateRuleId The identifier of rate rule.
 * @param inventorySourceId The identification number of the source that provides the rate plan.
 * @param inventorySourceCode The source name that provided the rate plan.
 * @param stayDates
 * @param remainingCount The number of rooms remaining through Expedia for this room type.  **NOTE**: This value does <u>NOT</u> represent the total number of rooms remaining at the hotel property, only the number of rooms allocated to Expedia for sale by the property that currently remain in Expedia's inventory.  When a hotel is listed as 'sold out' by Expedia there may still be rooms available for sale by the hotel through other channels.  Also note that the `RemainingCount` response element has a maximum returned value of 100, even if there are more than 100 rooms available within bookable Expedia inventory.  **CMA Compliance Note (UK)**: websites in the UK that display `remainingCount` should make it clear to consumers that this count refers to the number of rooms remaining within Expedia inventory - NOT the number remaining at the property.
 * @param price
 * @param memberOnlyDeal Indicates whether the rate is for member only.  Only visible by configuration. Please contact your Expedia Account Manager if you need this node.
 * @param promotions All promotion information of the `ratePlan`.
 * @param standalonePrice
 * @param taxesAndFeesInclusive Indicates whether taxes and fees are included in base rate.
 * @param guaranteeText Text description for any deposit information provide by the property (if applicable).
 * @param paymentMethod Room payment information. The available options are: - Online: Pay at the time of booking - Hotel: Could either be paid at the hotel during the time of stay or paid at the time indicated by `PaymentTime` and `PaymentSchedule`
 * @param paymentTime The time when the booking amount will be charged on the traveler’s credit card. Valid values are as follows: - UponBooking - PayLater - SupplierDiscretion
 * @param fullDepositUponBooking It will be true if PaymentSchedule has one installment and the value of \"Due\" is booking date, otherwise, it will be false.
 * @param paymentSchedule Contains information on the payment schedule.
 * @param reserveWithDeposit Indicates whether this rate plan need deposit to reserve.
 * @param freeInternet Indicates whether the price of the room includes free Internet. (either wireless or wired)
 * @param freeWiFi Indicates whether the price of the room includes free wireless Internet access.
 * @param freeInternetDetails The localized details for the free internet amenity (only shown when FreeInternet = true).  You can find a link to the complete list of Free Internet Details in [Lodging Free Breakfast and Internet Details](https://developers.expediagroup.com/xap/products/xap/lodging/references/free-breakfast-and-internet-details).
 * @param freeParking Indicates whether the price of the room includes free parking.
 * @param freeBreakfast Indicates whether the price of the room includes free breakfast.
 * @param freeBreakfastDetails The localized details for the free breakfast amenity (only shown when FreeBreakfast = true).  You can find a link to the complete list of Free Breakfast Details in [Lodging Free Breakfast and Internet Details](https://developers.expediagroup.com/xap/products/xap/lodging/references/free-breakfast-and-internet-details).
 * @param hcomRewardsEarn Indicate whether the room qualifies for Hcom Rewards Earn. (Hotels.com partners only)
 * @param hcomRewardsBurn Indicate whether the room qualifies for Hcom Rewards Burn. (Hotels.com partners only)
 * @param cancellationPolicy
 * @param amenities The amenities of the `rateplan`.
 */
data class RatePlan(
    // The room type identifier.
    @JsonProperty("RoomTypeId")
    @field:Valid
    val roomTypeId: kotlin.String? = null,
    // The rate plan identifier.
    @JsonProperty("RatePlanId")
    @field:Valid
    val ratePlanId: kotlin.String? = null,
    // The identifier of rate rule.
    @JsonProperty("RateRuleId")
    @field:Valid
    val rateRuleId: kotlin.String? = null,
    // The identification number of the source that provides the rate plan.
    @JsonProperty("InventorySourceId")
    @field:Valid
    val inventorySourceId: kotlin.String? = null,
    // The source name that provided the rate plan.
    @JsonProperty("InventorySourceCode")
    @field:Valid
    val inventorySourceCode: kotlin.String? = null,
    @JsonProperty("StayDates")
    @field:Valid
    val stayDates: StayDates? = null,
    // The number of rooms remaining through Expedia for this room type.  **NOTE**: This value does <u>NOT</u> represent the total number of rooms remaining at the hotel property, only the number of rooms allocated to Expedia for sale by the property that currently remain in Expedia's inventory.  When a hotel is listed as 'sold out' by Expedia there may still be rooms available for sale by the hotel through other channels.  Also note that the `RemainingCount` response element has a maximum returned value of 100, even if there are more than 100 rooms available within bookable Expedia inventory.  **CMA Compliance Note (UK)**: websites in the UK that display `remainingCount` should make it clear to consumers that this count refers to the number of rooms remaining within Expedia inventory - NOT the number remaining at the property.
    @JsonProperty("RemainingCount")
    val remainingCount: kotlin.Int? = null,
    @JsonProperty("Price")
    @field:Valid
    val price: RatePlanPrice? = null,
    // Indicates whether the rate is for member only.  Only visible by configuration. Please contact your Expedia Account Manager if you need this node.
    @JsonProperty("MemberOnlyDeal")
    @field:Valid
    val memberOnlyDeal: kotlin.Boolean? = null,
    // All promotion information of the `ratePlan`.
    @JsonProperty("Promotions")
    @field:Valid
    val promotions: kotlin.collections.List<Promotion>? = null,
    @JsonProperty("StandalonePrice")
    @field:Valid
    val standalonePrice: RatePlanStandalonePrice? = null,
    // Indicates whether taxes and fees are included in base rate.
    @JsonProperty("TaxesAndFeesInclusive")
    @field:Valid
    val taxesAndFeesInclusive: kotlin.Boolean? = null,
    // Text description for any deposit information provide by the property (if applicable).
    @JsonProperty("GuaranteeText")
    @field:Valid
    val guaranteeText: kotlin.String? = null,
    // Room payment information. The available options are: - Online: Pay at the time of booking - Hotel: Could either be paid at the hotel during the time of stay or paid at the time indicated by `PaymentTime` and `PaymentSchedule`
    @JsonProperty("PaymentMethod")
    val paymentMethod: RatePlan.PaymentMethod? = null,
    // The time when the booking amount will be charged on the traveler’s credit card. Valid values are as follows: - UponBooking - PayLater - SupplierDiscretion
    @JsonProperty("PaymentTime")
    val paymentTime: RatePlan.PaymentTime? = null,
    // It will be true if PaymentSchedule has one installment and the value of \"Due\" is booking date, otherwise, it will be false.
    @JsonProperty("FullDepositUponBooking")
    @field:Valid
    val fullDepositUponBooking: kotlin.Boolean? = null,
    // Contains information on the payment schedule.
    @JsonProperty("PaymentSchedule")
    @field:Valid
    val paymentSchedule: kotlin.collections.List<PaymentSchedule>? = null,
    // Indicates whether this rate plan need deposit to reserve.
    @JsonProperty("ReserveWithDeposit")
    @field:Valid
    val reserveWithDeposit: kotlin.Boolean? = null,
    // Indicates whether the price of the room includes free Internet. (either wireless or wired)
    @JsonProperty("FreeInternet")
    @field:Valid
    val freeInternet: kotlin.Boolean? = null,
    // Indicates whether the price of the room includes free wireless Internet access.
    @JsonProperty("FreeWiFi")
    @field:Valid
    val freeWiFi: kotlin.Boolean? = null,
    // The localized details for the free internet amenity (only shown when FreeInternet = true).  You can find a link to the complete list of Free Internet Details in [Lodging Free Breakfast and Internet Details](https://developers.expediagroup.com/xap/products/xap/lodging/references/free-breakfast-and-internet-details).
    @JsonProperty("FreeInternetDetails")
    @field:Valid
    val freeInternetDetails: kotlin.collections.List<kotlin.String>? = null,
    // Indicates whether the price of the room includes free parking.
    @JsonProperty("FreeParking")
    @field:Valid
    val freeParking: kotlin.Boolean? = null,
    // Indicates whether the price of the room includes free breakfast.
    @JsonProperty("FreeBreakfast")
    @field:Valid
    val freeBreakfast: kotlin.Boolean? = null,
    // The localized details for the free breakfast amenity (only shown when FreeBreakfast = true).  You can find a link to the complete list of Free Breakfast Details in [Lodging Free Breakfast and Internet Details](https://developers.expediagroup.com/xap/products/xap/lodging/references/free-breakfast-and-internet-details).
    @JsonProperty("FreeBreakfastDetails")
    @field:Valid
    val freeBreakfastDetails: kotlin.collections.List<kotlin.String>? = null,
    // Indicate whether the room qualifies for Hcom Rewards Earn. (Hotels.com partners only)
    @JsonProperty("HcomRewardsEarn")
    @field:Valid
    val hcomRewardsEarn: kotlin.Boolean? = null,
    // Indicate whether the room qualifies for Hcom Rewards Burn. (Hotels.com partners only)
    @JsonProperty("HcomRewardsBurn")
    @field:Valid
    val hcomRewardsBurn: kotlin.Boolean? = null,
    @JsonProperty("CancellationPolicy")
    @field:Valid
    val cancellationPolicy: CancellationPolicy? = null,
    // The amenities of the `rateplan`.
    @JsonProperty("Amenities")
    @field:Valid
    val amenities: kotlin.collections.List<RatePlanAmenitiesInner>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var roomTypeId: kotlin.String? = null,
        private var ratePlanId: kotlin.String? = null,
        private var rateRuleId: kotlin.String? = null,
        private var inventorySourceId: kotlin.String? = null,
        private var inventorySourceCode: kotlin.String? = null,
        private var stayDates: StayDates? = null,
        private var remainingCount: kotlin.Int? = null,
        private var price: RatePlanPrice? = null,
        private var memberOnlyDeal: kotlin.Boolean? = null,
        private var promotions: kotlin.collections.List<Promotion>? = null,
        private var standalonePrice: RatePlanStandalonePrice? = null,
        private var taxesAndFeesInclusive: kotlin.Boolean? = null,
        private var guaranteeText: kotlin.String? = null,
        private var paymentMethod: RatePlan.PaymentMethod? = null,
        private var paymentTime: RatePlan.PaymentTime? = null,
        private var fullDepositUponBooking: kotlin.Boolean? = null,
        private var paymentSchedule: kotlin.collections.List<PaymentSchedule>? = null,
        private var reserveWithDeposit: kotlin.Boolean? = null,
        private var freeInternet: kotlin.Boolean? = null,
        private var freeWiFi: kotlin.Boolean? = null,
        private var freeInternetDetails: kotlin.collections.List<kotlin.String>? = null,
        private var freeParking: kotlin.Boolean? = null,
        private var freeBreakfast: kotlin.Boolean? = null,
        private var freeBreakfastDetails: kotlin.collections.List<kotlin.String>? = null,
        private var hcomRewardsEarn: kotlin.Boolean? = null,
        private var hcomRewardsBurn: kotlin.Boolean? = null,
        private var cancellationPolicy: CancellationPolicy? = null,
        private var amenities: kotlin.collections.List<RatePlanAmenitiesInner>? = null
    ) {
        fun roomTypeId(roomTypeId: kotlin.String?) = apply { this.roomTypeId = roomTypeId }

        fun ratePlanId(ratePlanId: kotlin.String?) = apply { this.ratePlanId = ratePlanId }

        fun rateRuleId(rateRuleId: kotlin.String?) = apply { this.rateRuleId = rateRuleId }

        fun inventorySourceId(inventorySourceId: kotlin.String?) = apply { this.inventorySourceId = inventorySourceId }

        fun inventorySourceCode(inventorySourceCode: kotlin.String?) = apply { this.inventorySourceCode = inventorySourceCode }

        fun stayDates(stayDates: StayDates?) = apply { this.stayDates = stayDates }

        fun remainingCount(remainingCount: kotlin.Int?) = apply { this.remainingCount = remainingCount }

        fun price(price: RatePlanPrice?) = apply { this.price = price }

        fun memberOnlyDeal(memberOnlyDeal: kotlin.Boolean?) = apply { this.memberOnlyDeal = memberOnlyDeal }

        fun promotions(promotions: kotlin.collections.List<Promotion>?) = apply { this.promotions = promotions }

        fun standalonePrice(standalonePrice: RatePlanStandalonePrice?) = apply { this.standalonePrice = standalonePrice }

        fun taxesAndFeesInclusive(taxesAndFeesInclusive: kotlin.Boolean?) = apply { this.taxesAndFeesInclusive = taxesAndFeesInclusive }

        fun guaranteeText(guaranteeText: kotlin.String?) = apply { this.guaranteeText = guaranteeText }

        fun paymentMethod(paymentMethod: RatePlan.PaymentMethod?) = apply { this.paymentMethod = paymentMethod }

        fun paymentTime(paymentTime: RatePlan.PaymentTime?) = apply { this.paymentTime = paymentTime }

        fun fullDepositUponBooking(fullDepositUponBooking: kotlin.Boolean?) = apply { this.fullDepositUponBooking = fullDepositUponBooking }

        fun paymentSchedule(paymentSchedule: kotlin.collections.List<PaymentSchedule>?) = apply { this.paymentSchedule = paymentSchedule }

        fun reserveWithDeposit(reserveWithDeposit: kotlin.Boolean?) = apply { this.reserveWithDeposit = reserveWithDeposit }

        fun freeInternet(freeInternet: kotlin.Boolean?) = apply { this.freeInternet = freeInternet }

        fun freeWiFi(freeWiFi: kotlin.Boolean?) = apply { this.freeWiFi = freeWiFi }

        fun freeInternetDetails(freeInternetDetails: kotlin.collections.List<kotlin.String>?) = apply { this.freeInternetDetails = freeInternetDetails }

        fun freeParking(freeParking: kotlin.Boolean?) = apply { this.freeParking = freeParking }

        fun freeBreakfast(freeBreakfast: kotlin.Boolean?) = apply { this.freeBreakfast = freeBreakfast }

        fun freeBreakfastDetails(freeBreakfastDetails: kotlin.collections.List<kotlin.String>?) = apply { this.freeBreakfastDetails = freeBreakfastDetails }

        fun hcomRewardsEarn(hcomRewardsEarn: kotlin.Boolean?) = apply { this.hcomRewardsEarn = hcomRewardsEarn }

        fun hcomRewardsBurn(hcomRewardsBurn: kotlin.Boolean?) = apply { this.hcomRewardsBurn = hcomRewardsBurn }

        fun cancellationPolicy(cancellationPolicy: CancellationPolicy?) = apply { this.cancellationPolicy = cancellationPolicy }

        fun amenities(amenities: kotlin.collections.List<RatePlanAmenitiesInner>?) = apply { this.amenities = amenities }

        fun build(): RatePlan {
            val instance =
                RatePlan(
                    roomTypeId = roomTypeId,
                    ratePlanId = ratePlanId,
                    rateRuleId = rateRuleId,
                    inventorySourceId = inventorySourceId,
                    inventorySourceCode = inventorySourceCode,
                    stayDates = stayDates,
                    remainingCount = remainingCount,
                    price = price,
                    memberOnlyDeal = memberOnlyDeal,
                    promotions = promotions,
                    standalonePrice = standalonePrice,
                    taxesAndFeesInclusive = taxesAndFeesInclusive,
                    guaranteeText = guaranteeText,
                    paymentMethod = paymentMethod,
                    paymentTime = paymentTime,
                    fullDepositUponBooking = fullDepositUponBooking,
                    paymentSchedule = paymentSchedule,
                    reserveWithDeposit = reserveWithDeposit,
                    freeInternet = freeInternet,
                    freeWiFi = freeWiFi,
                    freeInternetDetails = freeInternetDetails,
                    freeParking = freeParking,
                    freeBreakfast = freeBreakfast,
                    freeBreakfastDetails = freeBreakfastDetails,
                    hcomRewardsEarn = hcomRewardsEarn,
                    hcomRewardsBurn = hcomRewardsBurn,
                    cancellationPolicy = cancellationPolicy,
                    amenities = amenities
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: RatePlan) {
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
            roomTypeId = roomTypeId,
            ratePlanId = ratePlanId,
            rateRuleId = rateRuleId,
            inventorySourceId = inventorySourceId,
            inventorySourceCode = inventorySourceCode,
            stayDates = stayDates,
            remainingCount = remainingCount,
            price = price,
            memberOnlyDeal = memberOnlyDeal,
            promotions = promotions,
            standalonePrice = standalonePrice,
            taxesAndFeesInclusive = taxesAndFeesInclusive,
            guaranteeText = guaranteeText,
            paymentMethod = paymentMethod,
            paymentTime = paymentTime,
            fullDepositUponBooking = fullDepositUponBooking,
            paymentSchedule = paymentSchedule,
            reserveWithDeposit = reserveWithDeposit,
            freeInternet = freeInternet,
            freeWiFi = freeWiFi,
            freeInternetDetails = freeInternetDetails,
            freeParking = freeParking,
            freeBreakfast = freeBreakfast,
            freeBreakfastDetails = freeBreakfastDetails,
            hcomRewardsEarn = hcomRewardsEarn,
            hcomRewardsBurn = hcomRewardsBurn,
            cancellationPolicy = cancellationPolicy,
            amenities = amenities
        )

    /**
     * Room payment information. The available options are: - Online: Pay at the time of booking - Hotel: Could either be paid at the hotel during the time of stay or paid at the time indicated by `PaymentTime` and `PaymentSchedule`
     * Values: ONLINE,HOTEL
     */
    enum class PaymentMethod(val value: kotlin.String) {
        @JsonProperty("Online")
        ONLINE("Online"),

        @JsonProperty("Hotel")
        HOTEL("Hotel")
    }

    /**
     * The time when the booking amount will be charged on the traveler’s credit card. Valid values are as follows: - UponBooking - PayLater - SupplierDiscretion
     * Values: UPON_BOOKING,PAY_LATER,SUPPLIER_DISCRETION
     */
    enum class PaymentTime(val value: kotlin.String) {
        @JsonProperty("UponBooking")
        UPON_BOOKING("UponBooking"),

        @JsonProperty("PayLater")
        PAY_LATER("PayLater"),

        @JsonProperty("SupplierDiscretion")
        SUPPLIER_DISCRETION("SupplierDiscretion")
    }
}
