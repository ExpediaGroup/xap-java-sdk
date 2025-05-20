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

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for error list.
 * @param code Error code describing the issue
 * @param description A simple description of what the error is.
 */
data class FlightsV1Error(
    // Error code describing the issue
    @JsonProperty("Code")
    val code: kotlin.String,
    // A simple description of what the error is.
    @JsonProperty("Description")
    val description: kotlin.String,
) {
    init {
        require(code != null) { "code must not be null" }

        require(description != null) { "description must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: kotlin.String? = null,
        private var description: kotlin.String? = null,
    ) {
        fun code(code: kotlin.String) = apply { this.code = code }

        fun description(description: kotlin.String) = apply { this.description = description }

        fun build(): FlightsV1Error {
            val instance =
                FlightsV1Error(
                    code = code!!,
                    description = description!!,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            code = code!!,
            description = description!!,
        )
}
