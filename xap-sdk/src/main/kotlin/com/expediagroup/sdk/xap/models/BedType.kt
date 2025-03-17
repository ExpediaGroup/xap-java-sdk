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
* Statement of bed types available for this offer. A room may have several bed type options available.  **NOTE**: due to the large number of bed type options available, we no longer publish a list of available bed types. More information is available in [Lodging Bed Types](https://developers.expediagroup.com/xap/products/xap/lodging/references/bed-types). 
    * @param id The bed type ID
    * @param description The bed type description.
*/
data class BedType(
            /* The bed type ID */
@JsonProperty("Id")
val id: kotlin.String? = null,

            /* The bed type description. */
@JsonProperty("Description")
val description: kotlin.String? = null
) {
    


    init {
        














    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var id: kotlin.String? = null,
                private var description: kotlin.String? = null
        ) {
                fun id(id: kotlin.String?) = apply { this.id = id }
                fun description(description: kotlin.String?) = apply { this.description = description }

    fun build(): BedType {
    val instance = BedType(
            id = id,
            description = description
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            id = id,
            description = description
    )
}
