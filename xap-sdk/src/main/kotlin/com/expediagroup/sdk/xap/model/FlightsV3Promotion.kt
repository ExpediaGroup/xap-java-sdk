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
 *
 * @param type Promotion type, possible values: PO | FN
 * @param description The description of the promotion.
 * @param `value`
 */
@ConsistentCopyVisibility data class FlightsV3Promotion private constructor(
    /* Promotion type, possible values: PO | FN */
    @JsonProperty("Type")
    val type: kotlin.String? = null,

    /* The description of the promotion. */
    @JsonProperty("Description")
    val description: kotlin.String? = null,

    @JsonProperty("Value")
    val `value`: kotlin.Double? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: kotlin.String? = null,
        private var description: kotlin.String? = null,
        private var `value`: kotlin.Double? = null,
    ) {
        fun type(type: kotlin.String?) = apply { this.type = type }

        fun description(description: kotlin.String?) = apply { this.description = description }

        fun `value`(`value`: kotlin.Double?) = apply { this.`value` = `value` }

        fun build(): FlightsV3Promotion {
            val instance = FlightsV3Promotion(
                type = type,
                description = description,
                `value` = `value`,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        type = type,
        description = description,
        `value` = `value`,
    )
}
