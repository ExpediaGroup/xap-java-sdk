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
 * Container filter condition for the filtered file. [Filters](https://confluence.expedia.biz/display/EWS/Filtered+Feed+File+Generation+Schedule+and+Access#FilteredFeedFileGenerationScheduleandAccess-Filters)
 * @param pointOfSupply List of filter condition for PointOfSupplies: `US`, `AT`,`BR`,`CA`,`FR`,`DE`,`GR`,`IT`, `JP`,`KR`,`MX`,`PT`,`ES`,`TR`, `AE`,`GB`.
 * @param brand List of filter condition for Brands: `VRBO`.
 * @param structureType List of filter condition for StructureTypes: `VR`, `CONVENTIONAL`.
 */
data class FilterConditions(
    // List of filter condition for PointOfSupplies: `US`, `AT`,`BR`,`CA`,`FR`,`DE`,`GR`,`IT`, `JP`,`KR`,`MX`,`PT`,`ES`,`TR`, `AE`,`GB`.
    @JsonProperty("pointOfSupply")
    val pointOfSupply: kotlin.String? = null,
    // List of filter condition for Brands: `VRBO`.
    @JsonProperty("brand")
    val brand: kotlin.String? = null,
    // List of filter condition for StructureTypes: `VR`, `CONVENTIONAL`.
    @JsonProperty("structureType")
    val structureType: kotlin.String? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var pointOfSupply: kotlin.String? = null,
        private var brand: kotlin.String? = null,
        private var structureType: kotlin.String? = null,
    ) {
        fun pointOfSupply(pointOfSupply: kotlin.String?) = apply { this.pointOfSupply = pointOfSupply }

        fun brand(brand: kotlin.String?) = apply { this.brand = brand }

        fun structureType(structureType: kotlin.String?) = apply { this.structureType = structureType }

        fun build(): FilterConditions {
            val instance =
                FilterConditions(
                    pointOfSupply = pointOfSupply,
                    brand = brand,
                    structureType = structureType,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            pointOfSupply = pointOfSupply,
            brand = brand,
            structureType = structureType,
        )
}
