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

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for seat characteristics information.
 * @param code Code of seat
 * @param name Corresponding name for the seat code
 */
data class SeatCharacteristic(
    // Code of seat
    @JsonProperty("Code")
    val code: SeatCharacteristic.Code,
    // Corresponding name for the seat code
    @JsonProperty("Name")
    val name: SeatCharacteristic.Name,
) {
    init {
        require(code != null) { "code must not be null" }

        require(name != null) { "name must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: SeatCharacteristic.Code? = null,
        private var name: SeatCharacteristic.Name? = null,
    ) {
        fun code(code: SeatCharacteristic.Code) = apply { this.code = code }

        fun name(name: SeatCharacteristic.Name) = apply { this.name = name }

        fun build(): SeatCharacteristic {
            val instance =
                SeatCharacteristic(
                    code = code!!,
                    name = name!!,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            code = code!!,
            name = name!!,
        )

    /**
     * Code of seat
     * Values: E,H,W,A,X,M,S,P
     */
    enum class Code(
        val value: kotlin.String,
    ) {
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
        P("P"),
    }

    /**
     * Corresponding name for the seat code
     * Values: EMPTY,ACCESSIBLE,WINDOW,AISLE,EXIT,MAINCABINEXTRA,PREFERRED,PREMIUM
     */
    enum class Name(
        val value: kotlin.String,
    ) {
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
        PREMIUM("PREMIUM"),
    }
}
