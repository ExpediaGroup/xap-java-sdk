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
 * @param waiverPolicyDescription The localized waiver policy description, which could be put in front of cancellation policy description.
 */
data class WaiverPolicy(
    // The localized waiver policy description, which could be put in front of cancellation policy description.
    @JsonProperty("WaiverPolicyDescription")
    val waiverPolicyDescription: kotlin.String? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var waiverPolicyDescription: kotlin.String? = null,
    ) {
        fun waiverPolicyDescription(waiverPolicyDescription: kotlin.String?) = apply { this.waiverPolicyDescription = waiverPolicyDescription }

        fun build(): WaiverPolicy {
            val instance =
                WaiverPolicy(
                    waiverPolicyDescription = waiverPolicyDescription,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            waiverPolicyDescription = waiverPolicyDescription,
        )
}
