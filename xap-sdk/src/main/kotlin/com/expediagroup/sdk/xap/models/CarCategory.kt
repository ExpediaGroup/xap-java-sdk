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

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Car category. Please find list of Car Type Codes in https://expediaintegration.zendesk.com/hc/en-us/articles/115008631767
 * @param code Car category code.
 * @param `value` Car category value.
 */
data class CarCategory(
    // Car category code.
    @JsonProperty("Code")
    val code: kotlin.String,
    // Car category value.
    @JsonProperty("Value")
    val `value`: kotlin.String,
) {
    init {
        require(code != null) { "code must not be null" }

        require(`value` != null) { "`value` must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: kotlin.String? = null,
        private var `value`: kotlin.String? = null,
    ) {
        fun code(code: kotlin.String) = apply { this.code = code }

        fun `value`(`value`: kotlin.String) = apply { this.`value` = `value` }

        fun build(): CarCategory {
            val instance =
                CarCategory(
                    code = code!!,
                    `value` = `value`!!,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            code = code!!,
            `value` = `value`!!,
        )
}
