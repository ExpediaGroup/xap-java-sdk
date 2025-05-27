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
 * Car type. Please find list of Car Type Codes in https://expediaintegration.zendesk.com/hc/en-us/articles/115002516708
 * @param code Car type code.
 * @param `value` Car type value.
 */
@ConsistentCopyVisibility data class CarType private constructor(
    /* Car type code. */
    @JsonProperty("Code")
    val code: kotlin.String,

    /* Car type value. */
    @JsonProperty("Value")
    val `value`: kotlin.String,

) {

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

        fun build(): CarType {
            val code = this.code.getOrThrow {
                IllegalArgumentException("code must not be null")
            }

            val `value` = this.`value`.getOrThrow {
                IllegalArgumentException("`value` must not be null")
            }

            val instance = CarType(
                code = code,
                `value` = `value`,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        code = code,
        `value` = `value`,
    )
}
