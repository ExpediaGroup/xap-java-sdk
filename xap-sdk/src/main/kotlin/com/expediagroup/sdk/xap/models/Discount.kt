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
 * List of discount information.
 * @param type Discount type. Supported values : CorpDiscount | Coupon.
 * @param code Discount code.
 */
data class Discount(
    // Discount type. Supported values : CorpDiscount | Coupon.
    @JsonProperty("Type")
    val type: kotlin.String,
    // Discount code.
    @JsonProperty("Code")
    val code: kotlin.String,
) {
    init {
        require(type != null) { "type must not be null" }

        require(code != null) { "code must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: kotlin.String? = null,
        private var code: kotlin.String? = null,
    ) {
        fun type(type: kotlin.String) = apply { this.type = type }

        fun code(code: kotlin.String) = apply { this.code = code }

        fun build(): Discount {
            val instance =
                Discount(
                    type = type!!,
                    code = code!!,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            type = type!!,
            code = code!!,
        )
}
