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
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param message
 */
@ConsistentCopyVisibility data class PostFlightLinks401Response private constructor(
    @JsonProperty("message")
    val message: kotlin.String,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var message: kotlin.String? = null,
    ) {
        fun message(message: kotlin.String) = apply { this.message = message }

        fun build(): PostFlightLinks401Response {
            val message = this.message.getOrThrow {
                IllegalArgumentException("message must not be null")
            }

            val instance = PostFlightLinks401Response(
                message = message,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        message = message,
    )
}
