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

import com.expediagroup.sdk.xap.models.ErrorResponseErrorsInner
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param transactionId
 * @param errors
 */
data class ErrorResponse(
    @JsonProperty("TransactionId")
    val transactionId: kotlin.String,
    @JsonProperty("Errors")
    val errors: kotlin.collections
        .List<
            ErrorResponseErrorsInner,
        >,
) {
    init {
        require(transactionId != null) { "transactionId must not be null" }

        require(errors != null) { "errors must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var transactionId: kotlin.String? = null,
        private var errors: kotlin.collections.List<ErrorResponseErrorsInner>? = null,
    ) {
        fun transactionId(transactionId: kotlin.String) = apply { this.transactionId = transactionId }

        fun errors(errors: kotlin.collections.List<ErrorResponseErrorsInner>) = apply { this.errors = errors }

        fun build(): ErrorResponse {
            val instance =
                ErrorResponse(
                    transactionId = transactionId!!,
                    errors = errors!!,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            transactionId = transactionId!!,
            errors = errors!!,
        )
}
