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
 * Geography entities which are typically contained within a city. This includes the categories neighborhood and point of interest. Low level regions are not a formally defined concept in the geography model.
 * @param id Neighborhood id.
 * @param name Neighborhood name.
 */
@ConsistentCopyVisibility data class ActivitiesNeighborhood private constructor(
    /* Neighborhood id. */
    @JsonProperty("Id")
    val id: kotlin.String,

    /* Neighborhood name. */
    @JsonProperty("Name")
    val name: kotlin.String,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.String? = null,
        private var name: kotlin.String? = null,
    ) {
        fun id(id: kotlin.String) = apply { this.id = id }

        fun name(name: kotlin.String) = apply { this.name = name }

        fun build(): ActivitiesNeighborhood {
            val id = this.id.getOrThrow {
                IllegalArgumentException("id must not be null")
            }

            val name = this.name.getOrThrow {
                IllegalArgumentException("name must not be null")
            }

            val instance = ActivitiesNeighborhood(
                id = id,
                name = name,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        id = id,
        name = name,
    )
}
