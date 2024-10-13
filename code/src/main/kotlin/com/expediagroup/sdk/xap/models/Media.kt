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
 * Container for hotel images
 * @param type Supported type of media.  1=Image.  Only 1 is supported now.
 * @param title Image title
 * @param propertySize Image size. You can find a link to the complete list of Supported Images Sizes in [Lodging Image Captions, IDs, and Sizes](https://developers.expediagroup.com/xap/products/xap/lodging/references/image-captions-ids-and-sizes).
 * @param url Image URL
 */
data class Media(
    // Supported type of media.  1=Image.  Only 1 is supported now.
    @JsonProperty("Type")
    val type: Media.Type? = null,
    // Image title
    @JsonProperty("Title")
    @field:Valid
    val title: kotlin.String? = null,
    // Image size. You can find a link to the complete list of Supported Images Sizes in [Lodging Image Captions, IDs, and Sizes](https://developers.expediagroup.com/xap/products/xap/lodging/references/image-captions-ids-and-sizes).
    @JsonProperty("Size")
    @field:Valid
    val propertySize: kotlin.String? = null,
    // Image URL
    @JsonProperty("Url")
    @field:Valid
    val url: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: Media.Type? = null,
        private var title: kotlin.String? = null,
        private var propertySize: kotlin.String? = null,
        private var url: kotlin.String? = null
    ) {
        fun type(type: Media.Type?) = apply { this.type = type }

        fun title(title: kotlin.String?) = apply { this.title = title }

        fun propertySize(propertySize: kotlin.String?) = apply { this.propertySize = propertySize }

        fun url(url: kotlin.String?) = apply { this.url = url }

        fun build(): Media {
            return Media(
                type = type,
                title = title,
                propertySize = propertySize,
                url = url
            )
        }
    }

    fun toBuilder() =
        Builder(
            type = type,
            title = title,
            propertySize = propertySize,
            url = url
        )

    /**
     * Supported type of media.  1=Image.  Only 1 is supported now.
     * Values: _1
     */
    enum class Type(val value: kotlin.String) {
        @JsonProperty("1")
        _1("1")
    }
}
