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

import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.Capacity
import com.expediagroup.sdk.xap.models.CarCategory
import com.expediagroup.sdk.xap.models.CarType
import com.expediagroup.sdk.xap.models.FuelAC
import com.expediagroup.sdk.xap.models.TransmissionDrive
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

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
data class VehicleDetails(
    // Car category and type.
    @JsonProperty("CarClass")
    @field:NotNull
    @field:Valid
    val carClass: kotlin.String,
    @JsonProperty("CarCategory")
    @field:NotNull
    @field:Valid
    val carCategory: CarCategory,
    @JsonProperty("CarType")
    @field:NotNull
    @field:Valid
    val carType: CarType,
    @JsonProperty("TransmissionDrive")
    @field:NotNull
    @field:Valid
    val transmissionDrive: TransmissionDrive,
    @JsonProperty("FuelAC")
    @field:NotNull
    @field:Valid
    val fuelAC: FuelAC,
    // Car manufacturer and model.
    @JsonProperty("Make")
    @field:Valid
    val make: kotlin.String? = null,
    // Minimal car door count.
    @JsonProperty("MinDoors")
    val minDoors: kotlin.Int? = null,
    // Maximal car door count.
    @JsonProperty("MaxDoors")
    val maxDoors: kotlin.Int? = null,
    // Car fuel information.
    @JsonProperty("FuelLevel")
    @field:Valid
    val fuelLevel: kotlin.String? = null,
    @JsonProperty("Capacity")
    @field:Valid
    val capacity: Capacity? = null
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
        private var capacity: Capacity? = null
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
            val instance =
                VehicleDetails(
                    carClass = carClass!!,
                    carCategory = carCategory!!,
                    carType = carType!!,
                    transmissionDrive = transmissionDrive!!,
                    fuelAC = fuelAC!!,
                    make = make,
                    minDoors = minDoors,
                    maxDoors = maxDoors,
                    fuelLevel = fuelLevel,
                    capacity = capacity
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: VehicleDetails) {
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
            carClass = carClass!!,
            carCategory = carCategory!!,
            carType = carType!!,
            transmissionDrive = transmissionDrive!!,
            fuelAC = fuelAC!!,
            make = make,
            minDoors = minDoors,
            maxDoors = maxDoors,
            fuelLevel = fuelLevel,
            capacity = capacity
        )
}
