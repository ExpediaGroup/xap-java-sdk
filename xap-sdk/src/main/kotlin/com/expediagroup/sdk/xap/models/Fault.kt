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
* API returned information.
    * @param code Fault code.
    * @param description Fault description.
*/
data class Fault(
            /* Fault code. */
@JsonProperty("code")
val code: kotlin.String? = null,

            /* Fault description. */
@JsonProperty("description")
val description: kotlin.String? = null
) {
    


    init {
        














    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var code: kotlin.String? = null,
                private var description: kotlin.String? = null
        ) {
                fun code(code: kotlin.String?) = apply { this.code = code }
                fun description(description: kotlin.String?) = apply { this.description = description }

    fun build(): Fault {
    val instance = Fault(
            code = code,
            description = description
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            code = code,
            description = description
    )
}
