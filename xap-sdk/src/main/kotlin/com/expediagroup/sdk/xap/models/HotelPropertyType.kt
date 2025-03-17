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
* Container for information on hotel property type.  You can find a complete list of Lodging Property Types in [Lodging Property Types](https://developers.expediagroup.com/xap/products/xap/lodging/references/property-types). 
    * @param id The id of hotel property type.
    * @param name The name of hotel property type.
*/
data class HotelPropertyType(
            /* The id of hotel property type. */
@JsonProperty("Id")
val id: kotlin.Int? = null,

            /* The name of hotel property type. */
@JsonProperty("Name")
val name: kotlin.String? = null
) {
    


    init {
        














    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var id: kotlin.Int? = null,
                private var name: kotlin.String? = null
        ) {
                fun id(id: kotlin.Int?) = apply { this.id = id }
                fun name(name: kotlin.String?) = apply { this.name = name }

    fun build(): HotelPropertyType {
    val instance = HotelPropertyType(
            id = id,
            name = name
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            id = id,
            name = name
    )
}
