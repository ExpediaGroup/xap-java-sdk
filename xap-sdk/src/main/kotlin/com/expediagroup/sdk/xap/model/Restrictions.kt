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
 * Container of the Restrictions associated to this ticket.
 * @param type Type of the Restriction.
 * @param max Maximum value allowed for the restriction type.
 * @param min Minimum value allowed for the restriction type.
 * @param description The text to describe the restriction.
 */
@ConsistentCopyVisibility data class Restrictions private constructor(
    /* Type of the Restriction. */
    @JsonProperty("Type")
    val type: kotlin.String,

    /* Maximum value allowed for the restriction type. */
    @JsonProperty("Max")
    val max: kotlin.String,

    /* Minimum value allowed for the restriction type. */
    @JsonProperty("Min")
    val min: kotlin.String,

    /* The text to describe the restriction. */
    @JsonProperty("Description")
    val description: kotlin.String? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: kotlin.String? = null,
        private var max: kotlin.String? = null,
        private var min: kotlin.String? = null,
        private var description: kotlin.String? = null,
    ) {
        fun type(type: kotlin.String) = apply { this.type = type }

        fun max(max: kotlin.String) = apply { this.max = max }

        fun min(min: kotlin.String) = apply { this.min = min }

        fun description(description: kotlin.String?) = apply { this.description = description }

        fun build(): Restrictions {
            val type = this.type.getOrThrow {
                IllegalArgumentException("type must not be null")
            }

            val max = this.max.getOrThrow {
                IllegalArgumentException("max must not be null")
            }

            val min = this.min.getOrThrow {
                IllegalArgumentException("min must not be null")
            }

            val instance = Restrictions(
                type = type,
                max = max,
                min = min,
                description = description,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        type = type,
        max = max,
        min = min,
        description = description,
    )
}
