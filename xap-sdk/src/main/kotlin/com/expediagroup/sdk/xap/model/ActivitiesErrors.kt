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
import com.expediagroup.sdk.xap.model.ActivitiesError
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param errors Container for error list.
 * @param transactionId A unique identifier for the transaction.
 */
@ConsistentCopyVisibility data class ActivitiesErrors private constructor(
    /* Container for error list. */
    @JsonProperty("Errors")
    val errors: kotlin.collections
        .List<
            ActivitiesError,
            >,

    /* A unique identifier for the transaction. */
    @JsonProperty("TransactionId")
    val transactionId: kotlin.String,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var errors: kotlin.collections.List<ActivitiesError>? = null,
        private var transactionId: kotlin.String? = null,
    ) {
        fun errors(errors: kotlin.collections.List<ActivitiesError>) = apply { this.errors = errors }

        fun transactionId(transactionId: kotlin.String) = apply { this.transactionId = transactionId }

        fun build(): ActivitiesErrors {
            val errors = this.errors.getOrThrow {
                IllegalArgumentException("errors must not be null")
            }

            val transactionId = this.transactionId.getOrThrow {
                IllegalArgumentException("transactionId must not be null")
            }

            val instance = ActivitiesErrors(
                errors = errors,
                transactionId = transactionId,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        errors = errors,
        transactionId = transactionId,
    )
}
