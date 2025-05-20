/**
 * Copyright (C) 2025 Expedia, Inc.
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
package com.expediagroup.sdk.xap.model

import com.fasterxml.jackson.annotation.JsonProperty

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
    val title: kotlin.String? = null,
    // Image size. You can find a link to the complete list of Supported Images Sizes in [Lodging Image Captions, IDs, and Sizes](https://developers.expediagroup.com/xap/products/xap/lodging/references/image-captions-ids-and-sizes).
    @JsonProperty("Size")
    val propertySize: kotlin.String? = null,
    // Image URL
    @JsonProperty("Url")
    val url: kotlin.String? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: Media.Type? = null,
        private var title: kotlin.String? = null,
        private var propertySize: kotlin.String? = null,
        private var url: kotlin.String? = null,
    ) {
        fun type(type: Media.Type?) = apply { this.type = type }

        fun title(title: kotlin.String?) = apply { this.title = title }

        fun propertySize(propertySize: kotlin.String?) = apply { this.propertySize = propertySize }

        fun url(url: kotlin.String?) = apply { this.url = url }

        fun build(): Media {
            val instance =
                Media(
                    type = type,
                    title = title,
                    propertySize = propertySize,
                    url = url,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            type = type,
            title = title,
            propertySize = propertySize,
            url = url,
        )

    /**
     * Supported type of media.  1=Image.  Only 1 is supported now.
     * Values: _1
     */
    enum class Type(
        val value: kotlin.String,
    ) {
        @JsonProperty("1")
        _1("1"),
    }
}
