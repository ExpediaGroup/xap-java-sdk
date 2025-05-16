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
 * Container for room preferences.
 * @param type The type of preference. Options are: SmokingPreference Bed
 * @param `value` The value of the room preference.  For SmokingPreference, options are  SmokingOrNonSmoking Smoking NonSmoking For supported Bed Types, please refer to the Related Links section at the bottom of the page.
 */
data class RoomPreference(
    // The type of preference. Options are: SmokingPreference Bed
    @JsonProperty("Type")
    val type: RoomPreference.Type? = null,
    // The value of the room preference.  For SmokingPreference, options are  SmokingOrNonSmoking Smoking NonSmoking For supported Bed Types, please refer to the Related Links section at the bottom of the page.
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
        private var type: RoomPreference.Type? = null,
        private var `value`: kotlin.String? = null,
    ) {
        fun type(type: RoomPreference.Type?) = apply { this.type = type }

        fun `value`(`value`: kotlin.String?) = apply { this.`value` = `value` }

        fun build(): RoomPreference {
            val instance =
                RoomPreference(
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
     * The type of preference. Options are: SmokingPreference Bed
     * Values: SMOKING_PREFERENCE,BED
     */
    enum class Type(
        val value: kotlin.String,
    ) {
        @JsonProperty("SmokingPreference")
        SMOKING_PREFERENCE("SmokingPreference"),

        @JsonProperty("Bed")
        BED("Bed"),
    }
}
