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
* List of all the details of rating.
    * @param category The category of rating detail.
    * @param percentage The percentage of rating detail category.
*/
data class RatingDetails(
            /* The category of rating detail. */
@JsonProperty("Category")
val category:
    kotlin.String
,

            /* The percentage of rating detail category. */
@JsonProperty("Percentage")
val percentage:
    kotlin.String

) {
    


    init {
                require(category != null) { "category must not be null" }







        require(percentage != null) { "percentage must not be null" }








    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var category: kotlin.String? = null,
                private var percentage: kotlin.String? = null
        ) {
                fun category(category: kotlin.String) = apply { this.category = category }
                fun percentage(percentage: kotlin.String) = apply { this.percentage = percentage }

    fun build(): RatingDetails {
    val instance = RatingDetails(
            category = category!!,
            percentage = percentage!!
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            category = category!!,
            percentage = percentage!!
    )
}
