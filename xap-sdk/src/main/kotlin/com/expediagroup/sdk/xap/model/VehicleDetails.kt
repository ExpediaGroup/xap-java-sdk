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
import com.expediagroup.sdk.xap.model.Capacity
import com.expediagroup.sdk.xap.model.CarCategory
import com.expediagroup.sdk.xap.model.CarType
import com.expediagroup.sdk.xap.model.FuelAC
import com.expediagroup.sdk.xap.model.TransmissionDrive
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Specific information for a car.
 * @param carClass Car category and type.
 * @param carCategory
 * @param carType
 * @param transmissionDrive
 * @param fuelAC
 * @param make Car manufacturer and model.
 * @param minDoors Minimal car door count.
 * @param maxDoors Maximal car door count.
 * @param fuelLevel Car fuel information.
 * @param capacity
 */
@ConsistentCopyVisibility data class VehicleDetails private constructor(
    /* Car category and type. */
    @JsonProperty("CarClass")
    val carClass: kotlin.String,

    @JsonProperty("CarCategory")
    val carCategory: CarCategory,

    @JsonProperty("CarType")
    val carType: CarType,

    @JsonProperty("TransmissionDrive")
    val transmissionDrive: TransmissionDrive,

    @JsonProperty("FuelAC")
    val fuelAC: FuelAC,

    /* Car manufacturer and model. */
    @JsonProperty("Make")
    val make: kotlin.String? = null,

    /* Minimal car door count. */
    @JsonProperty("MinDoors")
    val minDoors: kotlin.Int? = null,

    /* Maximal car door count. */
    @JsonProperty("MaxDoors")
    val maxDoors: kotlin.Int? = null,

    /* Car fuel information. */
    @JsonProperty("FuelLevel")
    val fuelLevel: kotlin.String? = null,

    @JsonProperty("Capacity")
    val capacity: Capacity? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var carClass: kotlin.String? = null,
        private var carCategory: CarCategory? = null,
        private var carType: CarType? = null,
        private var transmissionDrive: TransmissionDrive? = null,
        private var fuelAC: FuelAC? = null,
        private var make: kotlin.String? = null,
        private var minDoors: kotlin.Int? = null,
        private var maxDoors: kotlin.Int? = null,
        private var fuelLevel: kotlin.String? = null,
        private var capacity: Capacity? = null,
    ) {
        fun carClass(carClass: kotlin.String) = apply { this.carClass = carClass }

        fun carCategory(carCategory: CarCategory) = apply { this.carCategory = carCategory }

        fun carType(carType: CarType) = apply { this.carType = carType }

        fun transmissionDrive(transmissionDrive: TransmissionDrive) = apply { this.transmissionDrive = transmissionDrive }

        fun fuelAC(fuelAC: FuelAC) = apply { this.fuelAC = fuelAC }

        fun make(make: kotlin.String?) = apply { this.make = make }

        fun minDoors(minDoors: kotlin.Int?) = apply { this.minDoors = minDoors }

        fun maxDoors(maxDoors: kotlin.Int?) = apply { this.maxDoors = maxDoors }

        fun fuelLevel(fuelLevel: kotlin.String?) = apply { this.fuelLevel = fuelLevel }

        fun capacity(capacity: Capacity?) = apply { this.capacity = capacity }

        fun build(): VehicleDetails {
            val carClass = this.carClass.getOrThrow {
                IllegalArgumentException("carClass must not be null")
            }

            val carCategory = this.carCategory.getOrThrow {
                IllegalArgumentException("carCategory must not be null")
            }

            val carType = this.carType.getOrThrow {
                IllegalArgumentException("carType must not be null")
            }

            val transmissionDrive = this.transmissionDrive.getOrThrow {
                IllegalArgumentException("transmissionDrive must not be null")
            }

            val fuelAC = this.fuelAC.getOrThrow {
                IllegalArgumentException("fuelAC must not be null")
            }

            val instance = VehicleDetails(
                carClass = carClass,
                carCategory = carCategory,
                carType = carType,
                transmissionDrive = transmissionDrive,
                fuelAC = fuelAC,
                make = make,
                minDoors = minDoors,
                maxDoors = maxDoors,
                fuelLevel = fuelLevel,
                capacity = capacity,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        carClass = carClass,
        carCategory = carCategory,
        carType = carType,
        transmissionDrive = transmissionDrive,
        fuelAC = fuelAC,
        make = make,
        minDoors = minDoors,
        maxDoors = maxDoors,
        fuelLevel = fuelLevel,
        capacity = capacity,
    )
}
