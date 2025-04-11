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
import com.expediagroup.sdk.xap.models.RowCharacteristic
import com.expediagroup.sdk.xap.models.Seat
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Container for list of seat row information.
 * @param rowNumber Specific row number.
 * @param rowCharacteristics Container for row characteristics.
 * @param seats Container for seat information in that particular row. It can be null if it is empty row.
 */
data class Row(
    // Specific row number.
    @JsonProperty("RowNumber")
    val rowNumber: kotlin.Int,
    // Container for row characteristics.
    @JsonProperty("RowCharacteristics")
    @field:Valid
    val rowCharacteristics: kotlin.collections.List<RowCharacteristic>? = null,
    // Container for seat information in that particular row. It can be null if it is empty row.
    @JsonProperty("Seats")
    @field:Valid
    val seats: kotlin.collections.List<Seat>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var rowNumber: kotlin.Int? = null,
        private var rowCharacteristics: kotlin.collections.List<RowCharacteristic>? = null,
        private var seats: kotlin.collections.List<Seat>? = null
    ) {
        fun rowNumber(rowNumber: kotlin.Int) = apply { this.rowNumber = rowNumber }

        fun rowCharacteristics(rowCharacteristics: kotlin.collections.List<RowCharacteristic>?) = apply { this.rowCharacteristics = rowCharacteristics }

        fun seats(seats: kotlin.collections.List<Seat>?) = apply { this.seats = seats }

        fun build(): Row {
            val instance =
                Row(
                    rowNumber = rowNumber!!,
                    rowCharacteristics = rowCharacteristics,
                    seats = seats
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Row) {
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
            rowNumber = rowNumber!!,
            rowCharacteristics = rowCharacteristics,
            seats = seats
        )
}
