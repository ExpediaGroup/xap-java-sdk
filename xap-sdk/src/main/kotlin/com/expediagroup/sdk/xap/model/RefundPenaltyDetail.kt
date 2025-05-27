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
import com.expediagroup.sdk.xap.model.FlightsV3Money
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for en route change/cancellations and pre-trip change/cancellations
 * @param allow Status of penalty information
 * @param penalty
 */
@ConsistentCopyVisibility data class RefundPenaltyDetail private constructor(
    /* Status of penalty information */
    @JsonProperty("Allow")
    val allow: RefundPenaltyDetail.Allow,

    @JsonProperty("Penalty")
    val penalty: FlightsV3Money? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var allow: RefundPenaltyDetail.Allow? = null,
        private var penalty: FlightsV3Money? = null,
    ) {
        fun allow(allow: RefundPenaltyDetail.Allow) = apply { this.allow = allow }

        fun penalty(penalty: FlightsV3Money?) = apply { this.penalty = penalty }

        fun build(): RefundPenaltyDetail {
            val allow = this.allow.getOrThrow {
                IllegalArgumentException("allow must not be null")
            }

            val instance = RefundPenaltyDetail(
                allow = allow,
                penalty = penalty,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        allow = allow,
        penalty = penalty,
    )

    /**
     * Status of penalty information
     * Values: YES,NO,UNKNOWN
     */
    enum class Allow(val value: kotlin.String) {
        @JsonProperty("YES")
        YES("YES"),

        @JsonProperty("NO")
        NO("NO"),

        @JsonProperty("UNKNOWN")
        UNKNOWN("UNKNOWN"),
    }
}
