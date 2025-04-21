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
* The supplier of the car being offered.
 * @param id Supplier ID.
 * @param name Supplier Name.
 * @param code Supplier Code.
 * @param logoImageUrl Supplier Logo Image Url.
*/
data class Supplier(
    // Supplier ID.
    @JsonProperty("Id")
    val id: kotlin.String,
    // Supplier Name.
    @JsonProperty("Name")
    val name: kotlin.String,
    // Supplier Code.
    @JsonProperty("Code")
    val code: kotlin.String,
    // Supplier Logo Image Url.
    @JsonProperty("LogoImageUrl")
    val logoImageUrl: kotlin.String? = null,
) {
    init {
        require(id != null) { "id must not be null" }

        require(name != null) { "name must not be null" }

        require(code != null) { "code must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.String? = null,
        private var name: kotlin.String? = null,
        private var code: kotlin.String? = null,
        private var logoImageUrl: kotlin.String? = null,
    ) {
        fun id(id: kotlin.String) = apply { this.id = id }

        fun name(name: kotlin.String) = apply { this.name = name }

        fun code(code: kotlin.String) = apply { this.code = code }

        fun logoImageUrl(logoImageUrl: kotlin.String?) = apply { this.logoImageUrl = logoImageUrl }

        fun build(): Supplier {
            val instance =
                Supplier(
                    id = id!!,
                    name = name!!,
                    code = code!!,
                    logoImageUrl = logoImageUrl,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            id = id!!,
            name = name!!,
            code = code!!,
            logoImageUrl = logoImageUrl,
        )
}
