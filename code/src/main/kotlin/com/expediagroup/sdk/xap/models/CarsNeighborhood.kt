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
/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.expediagroup.sdk.xap.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * Geography entities which are typically contained within a city. This includes the categories neighborhood and point of interest. Low level regions are not a formally defined concept in the geography model.
 * @param id Neighborhood id.
 * @param name Neighborhood name.
 */
data class CarsNeighborhood(
    // Neighborhood id.
    @JsonProperty("Id")
    @field:Valid
    val id: kotlin.String,
    // Neighborhood name.
    @JsonProperty("Name")
    @field:Valid
    val name: kotlin.String
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.String? = null,
        private var name: kotlin.String? = null
    ) {
        fun id(id: kotlin.String) = apply { this.id = id }

        fun name(name: kotlin.String) = apply { this.name = name }

        fun build(): CarsNeighborhood {
            // Check required params
            validateNullity()
            return CarsNeighborhood(
                id = id!!,
                name = name!!
            )
        }

        private fun validateNullity() {
            if (id == null) {
                throw NullPointerException("Required parameter id is missing")
            }
            if (name == null) {
                throw NullPointerException("Required parameter name is missing")
            }
        }
    }

    fun toBuilder() =
        Builder(
            id = id!!,
            name = name!!
        )
}
