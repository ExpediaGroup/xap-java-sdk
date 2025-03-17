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
* Container for a breakdown of how many of each type of Activity have been returned in the API response.
    * @param count The count of the number of categories the returned set of activities map to.
    * @param groupName The group which the category belongs Possible value is: Recommendations Tours Activities Transportation Promotions Duration
    * @param groupDisplayName The localized value for category name.
    * @param categoryName The name of one of the categories.
    * @param categoryDisplayName 
*/
data class CategoryGroup(
            /* The count of the number of categories the returned set of activities map to. */
@JsonProperty("Count")
val count:
    kotlin.Int
,

            /* The group which the category belongs Possible value is: Recommendations Tours Activities Transportation Promotions Duration */
@JsonProperty("GroupName")
val groupName:
    kotlin.String
,

            /* The localized value for category name. */
@JsonProperty("GroupDisplayName")
val groupDisplayName:
    kotlin.String
,

            /* The name of one of the categories. */
@JsonProperty("CategoryName")
val categoryName:
    kotlin.String
,

        @JsonProperty("CategoryDisplayName")
val categoryDisplayName: kotlin.String? = null
) {
    


    init {
                require(count != null) { "count must not be null" }







        require(groupName != null) { "groupName must not be null" }







        require(groupDisplayName != null) { "groupDisplayName must not be null" }







        require(categoryName != null) { "categoryName must not be null" }















    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var count: kotlin.Int? = null,
                private var groupName: kotlin.String? = null,
                private var groupDisplayName: kotlin.String? = null,
                private var categoryName: kotlin.String? = null,
                private var categoryDisplayName: kotlin.String? = null
        ) {
                fun count(count: kotlin.Int) = apply { this.count = count }
                fun groupName(groupName: kotlin.String) = apply { this.groupName = groupName }
                fun groupDisplayName(groupDisplayName: kotlin.String) = apply { this.groupDisplayName = groupDisplayName }
                fun categoryName(categoryName: kotlin.String) = apply { this.categoryName = categoryName }
                fun categoryDisplayName(categoryDisplayName: kotlin.String?) = apply { this.categoryDisplayName = categoryDisplayName }

    fun build(): CategoryGroup {
    val instance = CategoryGroup(
            count = count!!,
            groupName = groupName!!,
            groupDisplayName = groupDisplayName!!,
            categoryName = categoryName!!,
            categoryDisplayName = categoryDisplayName
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            count = count!!,
            groupName = groupName!!,
            groupDisplayName = groupDisplayName!!,
            categoryName = categoryName!!,
            categoryDisplayName = categoryDisplayName
    )
}
