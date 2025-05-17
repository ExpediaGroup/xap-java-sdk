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
 * Container for Hotel Reference information
 * @param hotelId Unique id to represent a hotel
 * @param roomKey Unique id to represent Room
 */
data class HotelReference(
    // Unique id to represent a hotel
    @JsonProperty("HotelId")
    val hotelId: kotlin.String,
    // Unique id to represent Room
    @JsonProperty("RoomKey")
    val roomKey: kotlin.String,
) {
    init {
        require(hotelId != null) { "hotelId must not be null" }

        require(roomKey != null) { "roomKey must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var hotelId: kotlin.String? = null,
        private var roomKey: kotlin.String? = null,
    ) {
        fun hotelId(hotelId: kotlin.String) = apply { this.hotelId = hotelId }

        fun roomKey(roomKey: kotlin.String) = apply { this.roomKey = roomKey }

        fun build(): HotelReference {
            val instance =
                HotelReference(
                    hotelId = hotelId!!,
                    roomKey = roomKey!!,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            hotelId = hotelId!!,
            roomKey = roomKey!!,
        )
}
