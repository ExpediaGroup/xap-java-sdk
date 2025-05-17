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

import com.expediagroup.sdk.xap.models.SeatCharacteristic
import com.expediagroup.sdk.xap.models.SeatSeatPrice
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for seat information in that particular row. It can be null if it is empty row.
 * @param seatNumber Seat Number It can be null if there is no physical seat in the location
 * @param seatToken Token used by the Booking service to reserve seats
 * @param seatOccupancy Occupancy details whether it is available or not. It can be null if there is no physical seat in that location. AVAILABLE - Seat is currently available for selection. OCCUPIED - Seat already occupied
 * @param column It can be null if there is no physical seat in that location
 * @param seatCharacteristics Container for seat characteristics information.
 * @param paidSeat A boolean object (true/false) to indicate whether the seat is paid or not. It can be null
 * @param seatPrice
 */
data class Seat(
    // Seat Number It can be null if there is no physical seat in the location
    @JsonProperty("SeatNumber")
    val seatNumber: kotlin.String? = null,
    // Token used by the Booking service to reserve seats
    @JsonProperty("SeatToken")
    val seatToken: kotlin.String? = null,
    // Occupancy details whether it is available or not. It can be null if there is no physical seat in that location. AVAILABLE - Seat is currently available for selection. OCCUPIED - Seat already occupied
    @JsonProperty("SeatOccupancy")
    val seatOccupancy: Seat.SeatOccupancy? = null,
    // It can be null if there is no physical seat in that location
    @JsonProperty("Column")
    val column: kotlin.String? = null,
    // Container for seat characteristics information.
    @JsonProperty("SeatCharacteristics")
    val seatCharacteristics: kotlin.collections.List<SeatCharacteristic>? = null,
    // A boolean object (true/false) to indicate whether the seat is paid or not. It can be null
    @JsonProperty("PaidSeat")
    val paidSeat: kotlin.Boolean? = null,
    @JsonProperty("SeatPrice")
    val seatPrice: SeatSeatPrice? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var seatNumber: kotlin.String? = null,
        private var seatToken: kotlin.String? = null,
        private var seatOccupancy: Seat.SeatOccupancy? = null,
        private var column: kotlin.String? = null,
        private var seatCharacteristics: kotlin.collections.List<SeatCharacteristic>? = null,
        private var paidSeat: kotlin.Boolean? = null,
        private var seatPrice: SeatSeatPrice? = null,
    ) {
        fun seatNumber(seatNumber: kotlin.String?) = apply { this.seatNumber = seatNumber }

        fun seatToken(seatToken: kotlin.String?) = apply { this.seatToken = seatToken }

        fun seatOccupancy(seatOccupancy: Seat.SeatOccupancy?) = apply { this.seatOccupancy = seatOccupancy }

        fun column(column: kotlin.String?) = apply { this.column = column }

        fun seatCharacteristics(seatCharacteristics: kotlin.collections.List<SeatCharacteristic>?) = apply { this.seatCharacteristics = seatCharacteristics }

        fun paidSeat(paidSeat: kotlin.Boolean?) = apply { this.paidSeat = paidSeat }

        fun seatPrice(seatPrice: SeatSeatPrice?) = apply { this.seatPrice = seatPrice }

        fun build(): Seat {
            val instance =
                Seat(
                    seatNumber = seatNumber,
                    seatToken = seatToken,
                    seatOccupancy = seatOccupancy,
                    column = column,
                    seatCharacteristics = seatCharacteristics,
                    paidSeat = paidSeat,
                    seatPrice = seatPrice,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            seatNumber = seatNumber,
            seatToken = seatToken,
            seatOccupancy = seatOccupancy,
            column = column,
            seatCharacteristics = seatCharacteristics,
            paidSeat = paidSeat,
            seatPrice = seatPrice,
        )

    /**
     * Occupancy details whether it is available or not. It can be null if there is no physical seat in that location. AVAILABLE - Seat is currently available for selection. OCCUPIED - Seat already occupied
     * Values: AVAILABLE,OCCUPIED
     */
    enum class SeatOccupancy(
        val value: kotlin.String,
    ) {
        @JsonProperty("AVAILABLE")
        AVAILABLE("AVAILABLE"),

        @JsonProperty("OCCUPIED")
        OCCUPIED("OCCUPIED"),
    }
}
