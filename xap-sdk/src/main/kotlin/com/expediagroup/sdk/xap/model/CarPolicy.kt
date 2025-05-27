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
 * A list of policies that apply to this car rental.
 * @param categoryCode The category that this policy applies to (e.g. cancellation, drivers license requirements, driver age requirements)
 * @param policyText The raw text of the policy.This is generally localized into the requested language, but may be English if no other translations are available.
 */
@ConsistentCopyVisibility data class CarPolicy private constructor(
    /* The category that this policy applies to (e.g. cancellation, drivers license requirements, driver age requirements)  */
    @JsonProperty("CategoryCode")
    val categoryCode: kotlin.String,

    /* The raw text of the policy.This is generally localized into the requested language, but may be English if no other translations are available. */
    @JsonProperty("PolicyText")
    val policyText: kotlin.String? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var categoryCode: kotlin.String? = null,
        private var policyText: kotlin.String? = null,
    ) {
        fun categoryCode(categoryCode: kotlin.String) = apply { this.categoryCode = categoryCode }

        fun policyText(policyText: kotlin.String?) = apply { this.policyText = policyText }

        fun build(): CarPolicy {
            val categoryCode = this.categoryCode.getOrThrow {
                IllegalArgumentException("categoryCode must not be null")
            }

            val instance = CarPolicy(
                categoryCode = categoryCode,
                policyText = policyText,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        categoryCode = categoryCode,
        policyText = policyText,
    )
}
