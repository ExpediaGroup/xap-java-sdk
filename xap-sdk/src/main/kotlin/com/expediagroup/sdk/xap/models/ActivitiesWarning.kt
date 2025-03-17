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

import com.expediagroup.sdk.xap.models.ActivitiesLink
import com.expediagroup.sdk.xap.models.ActivitiesMoney

import com.fasterxml.jackson.annotation.JsonProperty

/**
* Container for all warnings generated during the transaction.
    * @param code Standardized warning code.
    * @param description Standardized warning description message.
    * @param originalPrice 
    * @param newPrice 
    * @param changedAmount 
    * @param changedPercentage The changed percentage. In the sample 2.97 means the changed percentage is 2.97%.
    * @param links 
*/
data class ActivitiesWarning(
            /* Standardized warning code. */
@JsonProperty("Code")
val code:
    kotlin.String
,

            /* Standardized warning description message. */
@JsonProperty("Description")
val description:
    kotlin.String
,

        @JsonProperty("OriginalPrice")
val originalPrice: ActivitiesMoney? = null,

        @JsonProperty("NewPrice")
val newPrice: ActivitiesMoney? = null,

        @JsonProperty("ChangedAmount")
val changedAmount: ActivitiesMoney? = null,

            /* The changed percentage. In the sample 2.97 means the changed percentage is 2.97%. */
@JsonProperty("ChangedPercentage")
val changedPercentage: kotlin.String? = null,

        @JsonProperty("Links")
val links: kotlin.collections.Map<kotlin.String, ActivitiesLink>? = null
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
                private var originalPrice: ActivitiesMoney? = null,
                private var newPrice: ActivitiesMoney? = null,
                private var changedAmount: ActivitiesMoney? = null,
                private var changedPercentage: kotlin.String? = null,
                private var links: kotlin.collections.Map<kotlin.String, ActivitiesLink>? = null
        ) {
                fun code(code: kotlin.String) = apply { this.code = code }
                fun description(description: kotlin.String) = apply { this.description = description }
                fun originalPrice(originalPrice: ActivitiesMoney?) = apply { this.originalPrice = originalPrice }
                fun newPrice(newPrice: ActivitiesMoney?) = apply { this.newPrice = newPrice }
                fun changedAmount(changedAmount: ActivitiesMoney?) = apply { this.changedAmount = changedAmount }
                fun changedPercentage(changedPercentage: kotlin.String?) = apply { this.changedPercentage = changedPercentage }
                fun links(links: kotlin.collections.Map<kotlin.String, ActivitiesLink>?) = apply { this.links = links }

    fun build(): ActivitiesWarning {
    val instance = ActivitiesWarning(
            code = code!!,
            description = description!!,
            originalPrice = originalPrice,
            newPrice = newPrice,
            changedAmount = changedAmount,
            changedPercentage = changedPercentage,
            links = links
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            code = code!!,
            description = description!!,
            originalPrice = originalPrice,
            newPrice = newPrice,
            changedAmount = changedAmount,
            changedPercentage = changedPercentage,
            links = links
    )
}
