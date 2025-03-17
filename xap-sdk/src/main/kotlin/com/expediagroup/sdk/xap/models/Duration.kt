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
* The maximum amount of time for a rental that still qualifies for this rate.This may or may not be the same as the current rental duration.
    * @param unit The unit for minimum amount of time for a rental.
    * @param count The minimum number of units that qualify for minimum amount of time for a rental.
*/
data class Duration(
            /* The unit for minimum amount of time for a rental. */
@JsonProperty("Unit")
val unit:
    kotlin.String
,

            /* The minimum number of units that qualify for minimum amount of time for a rental. */
@JsonProperty("Count")
val count:
    kotlin.Long

) {
    


    init {
                require(unit != null) { "unit must not be null" }







        require(count != null) { "count must not be null" }








    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var unit: kotlin.String? = null,
                private var count: kotlin.Long? = null
        ) {
                fun unit(unit: kotlin.String) = apply { this.unit = unit }
                fun count(count: kotlin.Long) = apply { this.count = count }

    fun build(): Duration {
    val instance = Duration(
            unit = unit!!,
            count = count!!
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            unit = unit!!,
            count = count!!
    )
}
