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
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container for seat characteristics information.
 * @param code Code of seat
 * @param name Corresponding name for the seat code
 */
data class SeatCharacteristic(
    // Code of seat
    @JsonProperty("Code")
    @field:NotNull
    val code: SeatCharacteristic.Code,
    // Corresponding name for the seat code
    @JsonProperty("Name")
    @field:NotNull
    val name: SeatCharacteristic.Name
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: SeatCharacteristic.Code? = null,
        private var name: SeatCharacteristic.Name? = null
    ) {
        fun code(code: SeatCharacteristic.Code) = apply { this.code = code }

        fun name(name: SeatCharacteristic.Name) = apply { this.name = name }

        fun build(): SeatCharacteristic {
            val instance =
                SeatCharacteristic(
                    code = code!!,
                    name = name!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: SeatCharacteristic) {
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
            code = code!!,
            name = name!!
        )

    /**
     * Code of seat
     * Values: E,H,W,A,X,M,S,P
     */
    enum class Code(val value: kotlin.String) {
        @JsonProperty("E")
        E("E"),

        @JsonProperty("H")
        H("H"),

        @JsonProperty("W")
        W("W"),

        @JsonProperty("A")
        A("A"),

        @JsonProperty("X")
        X("X"),

        @JsonProperty("M")
        M("M"),

        @JsonProperty("S")
        S("S"),

        @JsonProperty("P")
        P("P")
    }

    /**
     * Corresponding name for the seat code
     * Values: EMPTY,ACCESSIBLE,WINDOW,AISLE,EXIT,MAINCABINEXTRA,PREFERRED,PREMIUM
     */
    enum class Name(val value: kotlin.String) {
        @JsonProperty("EMPTY")
        EMPTY("EMPTY"),

        @JsonProperty("ACCESSIBLE")
        ACCESSIBLE("ACCESSIBLE"),

        @JsonProperty("WINDOW")
        WINDOW("WINDOW"),

        @JsonProperty("AISLE")
        AISLE("AISLE"),

        @JsonProperty("EXIT")
        EXIT("EXIT"),

        @JsonProperty("MAINCABINEXTRA")
        MAINCABINEXTRA("MAINCABINEXTRA"),

        @JsonProperty("PREFERRED")
        PREFERRED("PREFERRED"),

        @JsonProperty("PREMIUM")
        PREMIUM("PREMIUM")
    }
}
