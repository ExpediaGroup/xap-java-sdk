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

import com.expediagroup.sdk.xap.model.Money
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * There were some errors or events during the transaction, but the API has still returned a response.  Container for all warnings.
 * @param code The code of a warning.
 * @param description A detail information of what happened.
 * @param originalPrice
 * @param newPrice
 * @param changedAmount
 * @param changedPercentage The changed percentage. In the sample 2.97 means the changed percentage is 2.97%.
 */
@ConsistentCopyVisibility data class Warning private constructor(
    /* The code of a warning. */
    @JsonProperty("Code")
    val code: kotlin.String? = null,

    /* A detail information of what happened. */
    @JsonProperty("Description")
    val description: kotlin.String? = null,

    @JsonProperty("OriginalPrice")
    val originalPrice: Money? = null,

    @JsonProperty("NewPrice")
    val newPrice: Money? = null,

    @JsonProperty("ChangedAmount")
    val changedAmount: Money? = null,

    /* The changed percentage. In the sample 2.97 means the changed percentage is 2.97%. */
    @JsonProperty("ChangedPercentage")
    val changedPercentage: kotlin.String? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var code: kotlin.String? = null,
        private var description: kotlin.String? = null,
        private var originalPrice: Money? = null,
        private var newPrice: Money? = null,
        private var changedAmount: Money? = null,
        private var changedPercentage: kotlin.String? = null,
    ) {
        fun code(code: kotlin.String?) = apply { this.code = code }

        fun description(description: kotlin.String?) = apply { this.description = description }

        fun originalPrice(originalPrice: Money?) = apply { this.originalPrice = originalPrice }

        fun newPrice(newPrice: Money?) = apply { this.newPrice = newPrice }

        fun changedAmount(changedAmount: Money?) = apply { this.changedAmount = changedAmount }

        fun changedPercentage(changedPercentage: kotlin.String?) = apply { this.changedPercentage = changedPercentage }

        fun build(): Warning {
            val instance = Warning(
                code = code,
                description = description,
                originalPrice = originalPrice,
                newPrice = newPrice,
                changedAmount = changedAmount,
                changedPercentage = changedPercentage,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        code = code,
        description = description,
        originalPrice = originalPrice,
        newPrice = newPrice,
        changedAmount = changedAmount,
        changedPercentage = changedPercentage,
    )
}
