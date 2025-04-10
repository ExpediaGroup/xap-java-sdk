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
import com.expediagroup.sdk.xap.models.Row
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container for seatmap information.
 * @param cabinClass Cabin code for different class of service offered by the airline
 * @param rows Container for list of seat row information.
 */
data class SeatMap(
    // Cabin code for different class of service offered by the airline
    @JsonProperty("CabinClass")
    @field:NotNull
    val cabinClass: SeatMap.CabinClass,
    // Container for list of seat row information.
    @JsonProperty("Rows")
    @field:NotNull
    @field:Valid
    val rows: kotlin.collections
        .List<
            Row
        >
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var cabinClass: SeatMap.CabinClass? = null,
        private var rows: kotlin.collections.List<Row>? = null
    ) {
        fun cabinClass(cabinClass: SeatMap.CabinClass) = apply { this.cabinClass = cabinClass }

        fun rows(rows: kotlin.collections.List<Row>) = apply { this.rows = rows }

        fun build(): SeatMap {
            val instance =
                SeatMap(
                    cabinClass = cabinClass!!,
                    rows = rows!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: SeatMap) {
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
            cabinClass = cabinClass!!,
            rows = rows!!
        )

    /**
     * Cabin code for different class of service offered by the airline
     * Values: ECONOMY,FIRST,BUSINESS,PREMIUM_ECONOMY
     */
    enum class CabinClass(val value: kotlin.String) {
        @JsonProperty("ECONOMY")
        ECONOMY("ECONOMY"),

        @JsonProperty("FIRST")
        FIRST("FIRST"),

        @JsonProperty("BUSINESS")
        BUSINESS("BUSINESS"),

        @JsonProperty("PREMIUM_ECONOMY")
        PREMIUM_ECONOMY("PREMIUM_ECONOMY")
    }
}
