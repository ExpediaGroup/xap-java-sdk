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

import com.expediagroup.sdk.xap.models.DepositDetail
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for deposit policy details
 * @param description
 * @param details Container for deposit policy details
 */
data class DepositPolicy(
    @JsonProperty("Description")
    val description: kotlin.collections.List<kotlin.String>? = null,
    // Container for deposit policy details
    @JsonProperty("Details")
    val details: kotlin.collections.List<DepositDetail>? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var description: kotlin.collections.List<kotlin.String>? = null,
        private var details: kotlin.collections.List<DepositDetail>? = null,
    ) {
        fun description(description: kotlin.collections.List<kotlin.String>?) = apply { this.description = description }

        fun details(details: kotlin.collections.List<DepositDetail>?) = apply { this.details = details }

        fun build(): DepositPolicy {
            val instance =
                DepositPolicy(
                    description = description,
                    details = details,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            description = description,
            details = details,
        )
}
