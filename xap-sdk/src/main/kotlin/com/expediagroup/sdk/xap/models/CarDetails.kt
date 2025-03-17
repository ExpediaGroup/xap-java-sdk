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

import com.expediagroup.sdk.xap.models.AdditionalFee
import com.expediagroup.sdk.xap.models.CarPolicy
import com.expediagroup.sdk.xap.models.CarsCancellationPolicy
import com.expediagroup.sdk.xap.models.CarsMoney
import com.expediagroup.sdk.xap.models.Equipment
import com.expediagroup.sdk.xap.models.ExtraFees
import com.expediagroup.sdk.xap.models.Image
import com.expediagroup.sdk.xap.models.PenaltyType
import com.expediagroup.sdk.xap.models.Price
import com.expediagroup.sdk.xap.models.RateDetails
import com.expediagroup.sdk.xap.models.Rating
import com.expediagroup.sdk.xap.models.RentalLimits
import com.expediagroup.sdk.xap.models.Supplier
import com.expediagroup.sdk.xap.models.TaxesAndFees
import com.expediagroup.sdk.xap.models.VehicleDetails
import com.expediagroup.sdk.xap.models.VendorLocationDetails

import com.fasterxml.jackson.annotation.JsonProperty

/**
* Details of requested car.
    * @param vehicleDetails 
    * @param supplier 
    * @param pickupDetails 
    * @param dropOffDetails 
    * @param price 
    * @param cancellationPolicy 
    * @param onlineCheckIn Indicate whether the supplier supports online checkin
    * @param skipTheCounter Indicate whether the supplier supports skip the counter
    * @param rateDetails 
    * @param referencePrice 
    * @param additionalFees List of additional fees including both mandatory and optional fees.such as young driver fee/drop off fee /CollisionDamageWaiver
    * @param taxesAndFeesDetails List of TaxesAndFees Details
    * @param extraFeesDetails List of ExtraFeesDetails
    * @param specialEquipments Description and costs of any optional special equipment that may be rented with the car.
    * @param rentalLimits 
    * @param noShowPenalty 
    * @param carPolicies A list of policies that apply to this car rental.
    * @param images List of image resources of the car product.
    * @param rating 
*/
data class CarDetails(
        @JsonProperty("VehicleDetails")
val vehicleDetails:
    VehicleDetails
,

        @JsonProperty("Supplier")
val supplier:
    Supplier
,

        @JsonProperty("PickupDetails")
val pickupDetails:
    VendorLocationDetails
,

        @JsonProperty("DropOffDetails")
val dropOffDetails:
    VendorLocationDetails
,

        @JsonProperty("Price")
val price:
    Price
,

        @JsonProperty("CancellationPolicy")
val cancellationPolicy:
    CarsCancellationPolicy
,

            /* Indicate whether the supplier supports online checkin */
@JsonProperty("OnlineCheckIn")
val onlineCheckIn: kotlin.Boolean? = null,

            /* Indicate whether the supplier supports skip the counter */
@JsonProperty("SkipTheCounter")
val skipTheCounter: kotlin.Boolean? = null,

        @JsonProperty("RateDetails")
val rateDetails: RateDetails? = null,

        @JsonProperty("ReferencePrice")
val referencePrice: CarsMoney? = null,

            /* List of additional fees including both mandatory and optional fees.such as young driver fee/drop off fee /CollisionDamageWaiver */
@JsonProperty("AdditionalFees")
val additionalFees: kotlin.collections.List<AdditionalFee>? = null,

            /* List of TaxesAndFees Details */
@JsonProperty("TaxesAndFeesDetails")
val taxesAndFeesDetails: kotlin.collections.List<TaxesAndFees>? = null,

            /* List of ExtraFeesDetails */
@JsonProperty("ExtraFeesDetails")
val extraFeesDetails: kotlin.collections.List<ExtraFees>? = null,

            /* Description and costs of any optional special equipment that may be rented with the car. */
@JsonProperty("SpecialEquipments")
val specialEquipments: kotlin.collections.List<Equipment>? = null,

        @JsonProperty("RentalLimits")
val rentalLimits: RentalLimits? = null,

        @JsonProperty("NoShowPenalty")
val noShowPenalty: PenaltyType? = null,

            /* A list of policies that apply to this car rental. */
@JsonProperty("CarPolicies")
val carPolicies: kotlin.collections.List<CarPolicy>? = null,

            /* List of image resources of the car product. */
@JsonProperty("Images")
val images: kotlin.collections.List<Image>? = null,

        @JsonProperty("Rating")
val rating: Rating? = null
) {
    


    init {
                require(vehicleDetails != null) { "vehicleDetails must not be null" }







        require(supplier != null) { "supplier must not be null" }







        require(pickupDetails != null) { "pickupDetails must not be null" }







        require(dropOffDetails != null) { "dropOffDetails must not be null" }







        require(price != null) { "price must not be null" }







        require(cancellationPolicy != null) { "cancellationPolicy must not be null" }



































































































    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var vehicleDetails: VehicleDetails? = null,
                private var supplier: Supplier? = null,
                private var pickupDetails: VendorLocationDetails? = null,
                private var dropOffDetails: VendorLocationDetails? = null,
                private var price: Price? = null,
                private var cancellationPolicy: CarsCancellationPolicy? = null,
                private var onlineCheckIn: kotlin.Boolean? = null,
                private var skipTheCounter: kotlin.Boolean? = null,
                private var rateDetails: RateDetails? = null,
                private var referencePrice: CarsMoney? = null,
                private var additionalFees: kotlin.collections.List<AdditionalFee>? = null,
                private var taxesAndFeesDetails: kotlin.collections.List<TaxesAndFees>? = null,
                private var extraFeesDetails: kotlin.collections.List<ExtraFees>? = null,
                private var specialEquipments: kotlin.collections.List<Equipment>? = null,
                private var rentalLimits: RentalLimits? = null,
                private var noShowPenalty: PenaltyType? = null,
                private var carPolicies: kotlin.collections.List<CarPolicy>? = null,
                private var images: kotlin.collections.List<Image>? = null,
                private var rating: Rating? = null
        ) {
                fun vehicleDetails(vehicleDetails: VehicleDetails) = apply { this.vehicleDetails = vehicleDetails }
                fun supplier(supplier: Supplier) = apply { this.supplier = supplier }
                fun pickupDetails(pickupDetails: VendorLocationDetails) = apply { this.pickupDetails = pickupDetails }
                fun dropOffDetails(dropOffDetails: VendorLocationDetails) = apply { this.dropOffDetails = dropOffDetails }
                fun price(price: Price) = apply { this.price = price }
                fun cancellationPolicy(cancellationPolicy: CarsCancellationPolicy) = apply { this.cancellationPolicy = cancellationPolicy }
                fun onlineCheckIn(onlineCheckIn: kotlin.Boolean?) = apply { this.onlineCheckIn = onlineCheckIn }
                fun skipTheCounter(skipTheCounter: kotlin.Boolean?) = apply { this.skipTheCounter = skipTheCounter }
                fun rateDetails(rateDetails: RateDetails?) = apply { this.rateDetails = rateDetails }
                fun referencePrice(referencePrice: CarsMoney?) = apply { this.referencePrice = referencePrice }
                fun additionalFees(additionalFees: kotlin.collections.List<AdditionalFee>?) = apply { this.additionalFees = additionalFees }
                fun taxesAndFeesDetails(taxesAndFeesDetails: kotlin.collections.List<TaxesAndFees>?) = apply { this.taxesAndFeesDetails = taxesAndFeesDetails }
                fun extraFeesDetails(extraFeesDetails: kotlin.collections.List<ExtraFees>?) = apply { this.extraFeesDetails = extraFeesDetails }
                fun specialEquipments(specialEquipments: kotlin.collections.List<Equipment>?) = apply { this.specialEquipments = specialEquipments }
                fun rentalLimits(rentalLimits: RentalLimits?) = apply { this.rentalLimits = rentalLimits }
                fun noShowPenalty(noShowPenalty: PenaltyType?) = apply { this.noShowPenalty = noShowPenalty }
                fun carPolicies(carPolicies: kotlin.collections.List<CarPolicy>?) = apply { this.carPolicies = carPolicies }
                fun images(images: kotlin.collections.List<Image>?) = apply { this.images = images }
                fun rating(rating: Rating?) = apply { this.rating = rating }

    fun build(): CarDetails {
    val instance = CarDetails(
            vehicleDetails = vehicleDetails!!,
            supplier = supplier!!,
            pickupDetails = pickupDetails!!,
            dropOffDetails = dropOffDetails!!,
            price = price!!,
            cancellationPolicy = cancellationPolicy!!,
            onlineCheckIn = onlineCheckIn,
            skipTheCounter = skipTheCounter,
            rateDetails = rateDetails,
            referencePrice = referencePrice,
            additionalFees = additionalFees,
            taxesAndFeesDetails = taxesAndFeesDetails,
            extraFeesDetails = extraFeesDetails,
            specialEquipments = specialEquipments,
            rentalLimits = rentalLimits,
            noShowPenalty = noShowPenalty,
            carPolicies = carPolicies,
            images = images,
            rating = rating
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            vehicleDetails = vehicleDetails!!,
            supplier = supplier!!,
            pickupDetails = pickupDetails!!,
            dropOffDetails = dropOffDetails!!,
            price = price!!,
            cancellationPolicy = cancellationPolicy!!,
            onlineCheckIn = onlineCheckIn,
            skipTheCounter = skipTheCounter,
            rateDetails = rateDetails,
            referencePrice = referencePrice,
            additionalFees = additionalFees,
            taxesAndFeesDetails = taxesAndFeesDetails,
            extraFeesDetails = extraFeesDetails,
            specialEquipments = specialEquipments,
            rentalLimits = rentalLimits,
            noShowPenalty = noShowPenalty,
            carPolicies = carPolicies,
            images = images,
            rating = rating
    )
}
