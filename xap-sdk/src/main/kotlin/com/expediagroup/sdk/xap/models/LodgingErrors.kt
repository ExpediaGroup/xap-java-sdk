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

import com.expediagroup.sdk.xap.models.LodgingError

import com.fasterxml.jackson.annotation.JsonProperty

/**
* 
    * @param errors Container for error list.
    * @param transactionId A unique identifier for the transaction.
*/
data class LodgingErrors(
            /* Container for error list. */
@JsonProperty("Errors")
val errors: kotlin.collections.List<LodgingError>? = null,

            /* A unique identifier for the transaction. */
@JsonProperty("TransactionId")
val transactionId: kotlin.String? = null
) {
    


    init {
        














    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var errors: kotlin.collections.List<LodgingError>? = null,
                private var transactionId: kotlin.String? = null
        ) {
                fun errors(errors: kotlin.collections.List<LodgingError>?) = apply { this.errors = errors }
                fun transactionId(transactionId: kotlin.String?) = apply { this.transactionId = transactionId }

    fun build(): LodgingErrors {
    val instance = LodgingErrors(
            errors = errors,
            transactionId = transactionId
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            errors = errors,
            transactionId = transactionId
    )
}
