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
*
 * @param id Amenity ID.
 * @param name Amenity Name.
*/
data class RatePlanAmenitiesInner(
    // Amenity ID.
    @JsonProperty("Id")
    val id: kotlin.Any? = null,
    // Amenity Name.
    @JsonProperty("Name")
    val name: kotlin.Any? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.Any? = null,
        private var name: kotlin.Any? = null,
    ) {
        fun id(id: kotlin.Any?) = apply { this.id = id }

        fun name(name: kotlin.Any?) = apply { this.name = name }

        fun build(): RatePlanAmenitiesInner {
            val instance =
                RatePlanAmenitiesInner(
                    id = id,
                    name = name,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            id = id,
            name = name,
        )
}
