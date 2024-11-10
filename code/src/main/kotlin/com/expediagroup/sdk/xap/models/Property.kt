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

import com.expediagroup.sdk.xap.models.LodgingRoomType
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * Container for information on each offered hotel.
 * @param id The unique property identifier used to designate a single property.
 * @param status Represents whether the offer is currently available.
 * @param roomTypes Container for all of available room types.
 */
data class Property(
    // The unique property identifier used to designate a single property.
    @JsonProperty("Id")
    @field:Valid
    val id: kotlin.String? = null,
    // Represents whether the offer is currently available.
    @JsonProperty("Status")
    val status: Property.Status? = null,
    // Container for all of available room types.
    @JsonProperty("RoomTypes")
    @field:Valid
    val roomTypes: kotlin.collections.List<LodgingRoomType>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.String? = null,
        private var status: Property.Status? = null,
        private var roomTypes: kotlin.collections.List<LodgingRoomType>? = null
    ) {
        fun id(id: kotlin.String?) = apply { this.id = id }

        fun status(status: Property.Status?) = apply { this.status = status }

        fun roomTypes(roomTypes: kotlin.collections.List<LodgingRoomType>?) = apply { this.roomTypes = roomTypes }

        fun build(): Property {
            return Property(
                id = id,
                status = status,
                roomTypes = roomTypes
            )
        }
    }

    fun toBuilder() =
        Builder(
            id = id,
            status = status,
            roomTypes = roomTypes
        )

    /**
     * Represents whether the offer is currently available.
     * Values: AVAILABLE,NOT_AVAILABLE,ERROR,NUMBER_OF_ADULTS_NOT_ACCEPTED,NUMBER_OF_CHILDREN_NOT_ACCEPTED,NUMBER_OF_INFANTS_NOT_ACCEPTED,NUMBER_OF_PERSONS_NOT_ACCEPTED,CHECK_IN_AGE_NOT_ACCEPTED
     */
    enum class Status(val value: kotlin.String) {
        @JsonProperty("AVAILABLE")
        AVAILABLE("AVAILABLE"),

        @JsonProperty("NOT_AVAILABLE")
        NOT_AVAILABLE("NOT_AVAILABLE"),

        @JsonProperty("ERROR")
        ERROR("ERROR"),

        @JsonProperty("NUMBER_OF_ADULTS_NOT_ACCEPTED")
        NUMBER_OF_ADULTS_NOT_ACCEPTED("NUMBER_OF_ADULTS_NOT_ACCEPTED"),

        @JsonProperty("NUMBER_OF_CHILDREN_NOT_ACCEPTED")
        NUMBER_OF_CHILDREN_NOT_ACCEPTED("NUMBER_OF_CHILDREN_NOT_ACCEPTED"),

        @JsonProperty("NUMBER_OF_INFANTS_NOT_ACCEPTED")
        NUMBER_OF_INFANTS_NOT_ACCEPTED("NUMBER_OF_INFANTS_NOT_ACCEPTED"),

        @JsonProperty("NUMBER_OF_PERSONS_NOT_ACCEPTED")
        NUMBER_OF_PERSONS_NOT_ACCEPTED("NUMBER_OF_PERSONS_NOT_ACCEPTED"),

        @JsonProperty("CHECK_IN_AGE_NOT_ACCEPTED")
        CHECK_IN_AGE_NOT_ACCEPTED("CHECK_IN_AGE_NOT_ACCEPTED")
    }
}