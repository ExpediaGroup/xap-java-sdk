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
package com.expediagroup.sdk.xap.models

import com.expediagroup.sdk.xap.models.Car
import com.expediagroup.sdk.xap.models.CarsWarning
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param transactionId A unique identifier for this transaction.
 * @param carCount The number of cars offers returned in the response.
 * @param warnings Container for warning codes
 * @param cars List of cars matching the search criteria.
 */
data class CarListingsResponse(
    // A unique identifier for this transaction.
    @JsonProperty("TransactionId")
    val transactionId: kotlin.String,
    // The number of cars offers returned in the response.
    @JsonProperty("CarCount")
    val carCount: kotlin.Long,
    // Container for warning codes
    @JsonProperty("Warnings")
    val warnings: kotlin.collections.List<CarsWarning>? = null,
    // List of cars matching the search criteria.
    @JsonProperty("Cars")
    val cars: kotlin.collections.List<Car>? = null,
) {
    init {
        require(transactionId != null) { "transactionId must not be null" }

        require(carCount != null) { "carCount must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var transactionId: kotlin.String? = null,
        private var carCount: kotlin.Long? = null,
        private var warnings: kotlin.collections.List<CarsWarning>? = null,
        private var cars: kotlin.collections.List<Car>? = null,
    ) {
        fun transactionId(transactionId: kotlin.String) = apply { this.transactionId = transactionId }

        fun carCount(carCount: kotlin.Long) = apply { this.carCount = carCount }

        fun warnings(warnings: kotlin.collections.List<CarsWarning>?) = apply { this.warnings = warnings }

        fun cars(cars: kotlin.collections.List<Car>?) = apply { this.cars = cars }

        fun build(): CarListingsResponse {
            val instance =
                CarListingsResponse(
                    transactionId = transactionId!!,
                    carCount = carCount!!,
                    warnings = warnings,
                    cars = cars,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            transactionId = transactionId!!,
            carCount = carCount!!,
            warnings = warnings,
            cars = cars,
        )
}
