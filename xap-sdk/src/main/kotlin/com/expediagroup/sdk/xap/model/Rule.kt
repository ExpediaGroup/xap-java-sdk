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
 * Container for fare rules.
 * @param category Category name of the rule.
 * @param description Rule description.
 */
@ConsistentCopyVisibility data class Rule private constructor(
    /* Category name of the rule. */
    @JsonProperty("Category")
    val category: kotlin.String,

    /* Rule description. */
    @JsonProperty("Description")
    val description: kotlin.String,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var category: kotlin.String? = null,
        private var description: kotlin.String? = null,
    ) {
        fun category(category: kotlin.String) = apply { this.category = category }

        fun description(description: kotlin.String) = apply { this.description = description }

        fun build(): Rule {
            val category = this.category.getOrThrow {
                IllegalArgumentException("category must not be null")
            }

            val description = this.description.getOrThrow {
                IllegalArgumentException("description must not be null")
            }

            val instance = Rule(
                category = category,
                description = description,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        category = category,
        description = description,
    )
}
