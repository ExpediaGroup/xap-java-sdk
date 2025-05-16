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

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Indicate when to pay.
 * @param type Indicates the time of the deposit collection. Options are: UPON_BOOKING The customer must pay the deposit when booking the property. DAYS_PRIOR The customer must pay the deposit a number of days before arriving at the property. UPON_ARRIVAL The customer must pay the deposit upon arriving at the property.
 * @param `value` This value will only be shown when Deposit Type is DAYS_PRIOR to indicate the number of days prior to check in when the deposit will be collected.
 */
data class When(
    // Indicates the time of the deposit collection. Options are: UPON_BOOKING The customer must pay the deposit when booking the property. DAYS_PRIOR The customer must pay the deposit a number of days before arriving at the property. UPON_ARRIVAL The customer must pay the deposit upon arriving at the property.
    @JsonProperty("Type")
    val type: When.Type? = null,
    // This value will only be shown when Deposit Type is DAYS_PRIOR to indicate the number of days prior to check in when the deposit will be collected.
    @JsonProperty("Value")
    val `value`: kotlin.String? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: When.Type? = null,
        private var `value`: kotlin.String? = null,
    ) {
        fun type(type: When.Type?) = apply { this.type = type }

        fun `value`(`value`: kotlin.String?) = apply { this.`value` = `value` }

        fun build(): When {
            val instance =
                When(
                    type = type,
                    `value` = `value`,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            type = type,
            `value` = `value`,
        )

    /**
     * Indicates the time of the deposit collection. Options are: UPON_BOOKING The customer must pay the deposit when booking the property. DAYS_PRIOR The customer must pay the deposit a number of days before arriving at the property. UPON_ARRIVAL The customer must pay the deposit upon arriving at the property.
     * Values: UPON_BOOKING,DAYS_PRIOR,UPON_ARRIVAL
     */
    enum class Type(
        val value: kotlin.String,
    ) {
        @JsonProperty("UPON_BOOKING")
        UPON_BOOKING("UPON_BOOKING"),

        @JsonProperty("DAYS_PRIOR")
        DAYS_PRIOR("DAYS_PRIOR"),

        @JsonProperty("UPON_ARRIVAL")
        UPON_ARRIVAL("UPON_ARRIVAL"),
    }
}
