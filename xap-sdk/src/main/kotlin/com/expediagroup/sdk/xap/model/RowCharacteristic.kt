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
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for row characteristics.
 * @param code Code of Row
 * @param name Corresponding name for the row code Same response will have upper deck and lower deck information if any
 */
@ConsistentCopyVisibility data class RowCharacteristic private constructor(
    /* Code of Row */
    @JsonProperty("Code")
    val code: RowCharacteristic.Code,

    /* Corresponding name for the row code Same response will have upper deck and lower deck information if any */
    @JsonProperty("Name")
    val name: RowCharacteristic.Name,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: RowCharacteristic.Code? = null,
        private var name: RowCharacteristic.Name? = null,
    ) {
        fun code(code: RowCharacteristic.Code) = apply { this.code = code }

        fun name(name: RowCharacteristic.Name) = apply { this.name = name }

        fun build(): RowCharacteristic {
            val code = this.code.getOrThrow {
                IllegalArgumentException("code must not be null")
            }

            val name = this.name.getOrThrow {
                IllegalArgumentException("name must not be null")
            }

            val instance = RowCharacteristic(
                code = code,
                name = name,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        code = code,
        name = name,
    )

    /**
     * Code of Row
     * Values: E,X,S,N,U,W
     */
    enum class Code(val value: kotlin.String) {
        @JsonProperty("E")
        E("E"),

        @JsonProperty("X")
        X("X"),

        @JsonProperty("S")
        S("S"),

        @JsonProperty("N")
        N("N"),

        @JsonProperty("U")
        U("U"),

        @JsonProperty("W")
        W("W"),
    }

    /**
     * Corresponding name for the row code Same response will have upper deck and lower deck information if any
     * Values: EMPTY,EXIT,SMOKING,NONSMOKING,UPPERDECK,OVERWING
     */
    enum class Name(val value: kotlin.String) {
        @JsonProperty("EMPTY")
        EMPTY("EMPTY"),

        @JsonProperty("EXIT")
        EXIT("EXIT"),

        @JsonProperty("SMOKING")
        SMOKING("SMOKING"),

        @JsonProperty("NONSMOKING")
        NONSMOKING("NONSMOKING"),

        @JsonProperty("UPPERDECK")
        UPPERDECK("UPPERDECK"),

        @JsonProperty("OVERWING")
        OVERWING("OVERWING"),
    }
}
