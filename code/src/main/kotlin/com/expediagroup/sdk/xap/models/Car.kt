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
import com.expediagroup.sdk.xap.models.AdditionalFee
import com.expediagroup.sdk.xap.models.CarsCancellationPolicy
import com.expediagroup.sdk.xap.models.CarsLink
import com.expediagroup.sdk.xap.models.CarsMoney
import com.expediagroup.sdk.xap.models.Image
import com.expediagroup.sdk.xap.models.PenaltyType
import com.expediagroup.sdk.xap.models.Price
import com.expediagroup.sdk.xap.models.RateDetails
import com.expediagroup.sdk.xap.models.RatingWithoutDetails
import com.expediagroup.sdk.xap.models.Supplier
import com.expediagroup.sdk.xap.models.VehicleDetails
import com.expediagroup.sdk.xap.models.VendorLocationDetails
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * List of cars matching the search criteria.
 * @param id Uniquely identifies a Car Offer.Note: since pay-online and pay-at-the-counter Car Offers have the same associated Rate Code, the Offer ID is the only unique identifier to differentiate between the two offers when referencing or booking.
 * @param vehicleDetails
 * @param supplier
 * @param pickupDetails
 * @param dropOffDetails
 * @param price
 * @param cancellationPolicy
 * @param dataTimeStamp DataTimeStamp
 * @param onlineCheckIn Indicate whether the supplier supports online checkin
 * @param skipTheCounter Indicate whether the supplier supports skip the counter
 * @param links A map of links to other Car APIs or Expedia websites.
 * @param rateDetails
 * @param referencePrice
 * @param additionalFees List of additional fees including both mandatory and optional fees.such as young driver fee/drop off fee /CollisionDamageWaiver
 * @param noShowPenalty
 * @param images List of image resources of the car product.
 * @param rating
 */
data class Car(
    // Uniquely identifies a Car Offer.Note: since pay-online and pay-at-the-counter Car Offers have the same associated Rate Code, the Offer ID is the only unique identifier to differentiate between the two offers when referencing or booking.
    @JsonProperty("Id")
    @field:NotNull
    @field:Valid
    val id: kotlin.String,
    @JsonProperty("VehicleDetails")
    @field:NotNull
    @field:Valid
    val vehicleDetails: VehicleDetails,
    @JsonProperty("Supplier")
    @field:NotNull
    @field:Valid
    val supplier: Supplier,
    @JsonProperty("PickupDetails")
    @field:NotNull
    @field:Valid
    val pickupDetails: VendorLocationDetails,
    @JsonProperty("DropOffDetails")
    @field:NotNull
    @field:Valid
    val dropOffDetails: VendorLocationDetails,
    @JsonProperty("Price")
    @field:NotNull
    @field:Valid
    val price: Price,
    @JsonProperty("CancellationPolicy")
    @field:NotNull
    @field:Valid
    val cancellationPolicy: CarsCancellationPolicy,
    // DataTimeStamp
    @JsonProperty("DataTimeStamp")
    val dataTimeStamp: java.time.OffsetDateTime? = null,
    // Indicate whether the supplier supports online checkin
    @JsonProperty("OnlineCheckIn")
    @field:Valid
    val onlineCheckIn: kotlin.Boolean? = null,
    // Indicate whether the supplier supports skip the counter
    @JsonProperty("SkipTheCounter")
    @field:Valid
    val skipTheCounter: kotlin.Boolean? = null,
    // A map of links to other Car APIs or Expedia websites.
    @JsonProperty("Links")
    @field:Valid
    val links: kotlin.collections.Map<kotlin.String, CarsLink>? = null,
    @JsonProperty("RateDetails")
    @field:Valid
    val rateDetails: RateDetails? = null,
    @JsonProperty("ReferencePrice")
    @field:Valid
    val referencePrice: CarsMoney? = null,
    // List of additional fees including both mandatory and optional fees.such as young driver fee/drop off fee /CollisionDamageWaiver
    @JsonProperty("AdditionalFees")
    @field:Valid
    val additionalFees: kotlin.collections.List<AdditionalFee>? = null,
    @JsonProperty("NoShowPenalty")
    @field:Valid
    val noShowPenalty: PenaltyType? = null,
    // List of image resources of the car product.
    @JsonProperty("Images")
    @field:Valid
    val images: kotlin.collections.List<Image>? = null,
    @JsonProperty("Rating")
    @field:Valid
    val rating: RatingWithoutDetails? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.String? = null,
        private var vehicleDetails: VehicleDetails? = null,
        private var supplier: Supplier? = null,
        private var pickupDetails: VendorLocationDetails? = null,
        private var dropOffDetails: VendorLocationDetails? = null,
        private var price: Price? = null,
        private var cancellationPolicy: CarsCancellationPolicy? = null,
        private var dataTimeStamp: java.time.OffsetDateTime? = null,
        private var onlineCheckIn: kotlin.Boolean? = null,
        private var skipTheCounter: kotlin.Boolean? = null,
        private var links: kotlin.collections.Map<kotlin.String, CarsLink>? = null,
        private var rateDetails: RateDetails? = null,
        private var referencePrice: CarsMoney? = null,
        private var additionalFees: kotlin.collections.List<AdditionalFee>? = null,
        private var noShowPenalty: PenaltyType? = null,
        private var images: kotlin.collections.List<Image>? = null,
        private var rating: RatingWithoutDetails? = null
    ) {
        fun id(id: kotlin.String) = apply { this.id = id }

        fun vehicleDetails(vehicleDetails: VehicleDetails) = apply { this.vehicleDetails = vehicleDetails }

        fun supplier(supplier: Supplier) = apply { this.supplier = supplier }

        fun pickupDetails(pickupDetails: VendorLocationDetails) = apply { this.pickupDetails = pickupDetails }

        fun dropOffDetails(dropOffDetails: VendorLocationDetails) = apply { this.dropOffDetails = dropOffDetails }

        fun price(price: Price) = apply { this.price = price }

        fun cancellationPolicy(cancellationPolicy: CarsCancellationPolicy) = apply { this.cancellationPolicy = cancellationPolicy }

        fun dataTimeStamp(dataTimeStamp: java.time.OffsetDateTime?) = apply { this.dataTimeStamp = dataTimeStamp }

        fun onlineCheckIn(onlineCheckIn: kotlin.Boolean?) = apply { this.onlineCheckIn = onlineCheckIn }

        fun skipTheCounter(skipTheCounter: kotlin.Boolean?) = apply { this.skipTheCounter = skipTheCounter }

        fun links(links: kotlin.collections.Map<kotlin.String, CarsLink>?) = apply { this.links = links }

        fun rateDetails(rateDetails: RateDetails?) = apply { this.rateDetails = rateDetails }

        fun referencePrice(referencePrice: CarsMoney?) = apply { this.referencePrice = referencePrice }

        fun additionalFees(additionalFees: kotlin.collections.List<AdditionalFee>?) = apply { this.additionalFees = additionalFees }

        fun noShowPenalty(noShowPenalty: PenaltyType?) = apply { this.noShowPenalty = noShowPenalty }

        fun images(images: kotlin.collections.List<Image>?) = apply { this.images = images }

        fun rating(rating: RatingWithoutDetails?) = apply { this.rating = rating }

        fun build(): Car {
            val instance =
                Car(
                    id = id!!,
                    vehicleDetails = vehicleDetails!!,
                    supplier = supplier!!,
                    pickupDetails = pickupDetails!!,
                    dropOffDetails = dropOffDetails!!,
                    price = price!!,
                    cancellationPolicy = cancellationPolicy!!,
                    dataTimeStamp = dataTimeStamp,
                    onlineCheckIn = onlineCheckIn,
                    skipTheCounter = skipTheCounter,
                    links = links,
                    rateDetails = rateDetails,
                    referencePrice = referencePrice,
                    additionalFees = additionalFees,
                    noShowPenalty = noShowPenalty,
                    images = images,
                    rating = rating
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Car) {
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
            id = id!!,
            vehicleDetails = vehicleDetails!!,
            supplier = supplier!!,
            pickupDetails = pickupDetails!!,
            dropOffDetails = dropOffDetails!!,
            price = price!!,
            cancellationPolicy = cancellationPolicy!!,
            dataTimeStamp = dataTimeStamp,
            onlineCheckIn = onlineCheckIn,
            skipTheCounter = skipTheCounter,
            links = links,
            rateDetails = rateDetails,
            referencePrice = referencePrice,
            additionalFees = additionalFees,
            noShowPenalty = noShowPenalty,
            images = images,
            rating = rating
        )
}
