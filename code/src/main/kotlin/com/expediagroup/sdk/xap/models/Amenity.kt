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
import com.expediagroup.sdk.xap.models.AmenityInfo
import com.expediagroup.sdk.xap.models.Bag
import com.expediagroup.sdk.xap.models.SeatChoice
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Container for various amenities included in a particular segment.
 * @param seatChoice
 * @param carryOnBag
 * @param checkedBag List of Checked Bag Amenity.
 * @param change
 * @param free24HrCancellation True if booking can be cancelled with in 24 hours after booking.
 * @param refund
 * @param personalItem
 * @param upgrade
 * @param boardingGroup List of available BoardingGroup Amenity.
 */
data class Amenity(
    @JsonProperty("SeatChoice")
    @field:Valid
    val seatChoice: SeatChoice? = null,
    @JsonProperty("CarryOnBag")
    @field:Valid
    val carryOnBag: Bag? = null,
    // List of Checked Bag Amenity.
    @JsonProperty("CheckedBag")
    @field:Valid
    val checkedBag: kotlin.collections.List<Bag>? = null,
    @JsonProperty("Change")
    @field:Valid
    val change: AmenityInfo? = null,
    // True if booking can be cancelled with in 24 hours after booking.
    @JsonProperty("Free24HrCancellation")
    @field:Valid
    val free24HrCancellation: kotlin.Boolean? = null,
    @JsonProperty("Refund")
    @field:Valid
    val refund: AmenityInfo? = null,
    @JsonProperty("PersonalItem")
    @field:Valid
    val personalItem: AmenityInfo? = null,
    @JsonProperty("Upgrade")
    @field:Valid
    val upgrade: AmenityInfo? = null,
    // List of available BoardingGroup Amenity.
    @JsonProperty("BoardingGroup")
    val boardingGroup: kotlin.collections.List<Amenity.BoardingGroup>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var seatChoice: SeatChoice? = null,
        private var carryOnBag: Bag? = null,
        private var checkedBag: kotlin.collections.List<Bag>? = null,
        private var change: AmenityInfo? = null,
        private var free24HrCancellation: kotlin.Boolean? = null,
        private var refund: AmenityInfo? = null,
        private var personalItem: AmenityInfo? = null,
        private var upgrade: AmenityInfo? = null,
        private var boardingGroup: kotlin.collections.List<Amenity.BoardingGroup>? = null
    ) {
        fun seatChoice(seatChoice: SeatChoice?) = apply { this.seatChoice = seatChoice }

        fun carryOnBag(carryOnBag: Bag?) = apply { this.carryOnBag = carryOnBag }

        fun checkedBag(checkedBag: kotlin.collections.List<Bag>?) = apply { this.checkedBag = checkedBag }

        fun change(change: AmenityInfo?) = apply { this.change = change }

        fun free24HrCancellation(free24HrCancellation: kotlin.Boolean?) = apply { this.free24HrCancellation = free24HrCancellation }

        fun refund(refund: AmenityInfo?) = apply { this.refund = refund }

        fun personalItem(personalItem: AmenityInfo?) = apply { this.personalItem = personalItem }

        fun upgrade(upgrade: AmenityInfo?) = apply { this.upgrade = upgrade }

        fun boardingGroup(boardingGroup: kotlin.collections.List<Amenity.BoardingGroup>?) = apply { this.boardingGroup = boardingGroup }

        fun build(): Amenity {
            val instance =
                Amenity(
                    seatChoice = seatChoice,
                    carryOnBag = carryOnBag,
                    checkedBag = checkedBag,
                    change = change,
                    free24HrCancellation = free24HrCancellation,
                    refund = refund,
                    personalItem = personalItem,
                    upgrade = upgrade,
                    boardingGroup = boardingGroup
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Amenity) {
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
            seatChoice = seatChoice,
            carryOnBag = carryOnBag,
            checkedBag = checkedBag,
            change = change,
            free24HrCancellation = free24HrCancellation,
            refund = refund,
            personalItem = personalItem,
            upgrade = upgrade,
            boardingGroup = boardingGroup
        )

    /**
     * List of available BoardingGroup Amenity.
     * Values: LAST,PRIORITY,BASED_ON_SEAT_LOCATION,PAY_TO_UPGRADE
     */
    enum class BoardingGroup(val value: kotlin.String) {
        @JsonProperty("LAST")
        LAST("LAST"),

        @JsonProperty("PRIORITY")
        PRIORITY("PRIORITY"),

        @JsonProperty("BASED_ON_SEAT_LOCATION")
        BASED_ON_SEAT_LOCATION("BASED_ON_SEAT_LOCATION"),

        @JsonProperty("PAY_TO_UPGRADE")
        PAY_TO_UPGRADE("PAY_TO_UPGRADE")
    }
}
