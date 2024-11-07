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
/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.expediagroup.sdk.xap.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * Contains link information, including link address, request method. Only provided if FileInfo is in OtherFileOptions.
 * @param href a link address.
 * @param method Request method, it will support `GET`, `POST`, `DELETE` and `PUT` etc...
 */
data class SdpLink(
    // a link address.
    @JsonProperty("href")
    @field:Valid
    val href: kotlin.String? = null,
    // Request method, it will support `GET`, `POST`, `DELETE` and `PUT` etc...
    @JsonProperty("method")
    @field:Valid
    val method: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var href: kotlin.String? = null,
        private var method: kotlin.String? = null
    ) {
        fun href(href: kotlin.String?) = apply { this.href = href }

        fun method(method: kotlin.String?) = apply { this.method = method }

        fun build(): SdpLink {
            return SdpLink(
                href = href,
                method = method
            )
        }
    }

    fun toBuilder() =
        Builder(
            href = href,
            method = method
        )
}
