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

import com.expediagroup.sdk.core.common.getOrThrow
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * List of activity Media.
 * @param type type of the media. So far there is only one option: 1: Image
 * @param propertySize Image size You can find a link to the complete list of Supported Images Sizes in the Related Links section at the bottom of the page.
 * @param url Image URL
 * @param title Image title
 */
@ConsistentCopyVisibility data class ActivitiesMedia private constructor(
    /* type of the media. So far there is only one option: 1: Image */
    @JsonProperty("Type")
    val type: kotlin.String,

    /* Image size You can find a link to the complete list of Supported Images Sizes in the Related Links section at the bottom of the page. */
    @JsonProperty("Size")
    val propertySize: kotlin.String,

    /* Image URL */
    @JsonProperty("Url")
    val url: kotlin.String,

    /* Image title */
    @JsonProperty("Title")
    val title: kotlin.String? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: kotlin.String? = null,
        private var propertySize: kotlin.String? = null,
        private var url: kotlin.String? = null,
        private var title: kotlin.String? = null,
    ) {
        fun type(type: kotlin.String) = apply { this.type = type }

        fun propertySize(propertySize: kotlin.String) = apply { this.propertySize = propertySize }

        fun url(url: kotlin.String) = apply { this.url = url }

        fun title(title: kotlin.String?) = apply { this.title = title }

        fun build(): ActivitiesMedia {
            val type = this.type.getOrThrow {
                IllegalArgumentException("type must not be null")
            }

            val propertySize = this.propertySize.getOrThrow {
                IllegalArgumentException("propertySize must not be null")
            }

            val url = this.url.getOrThrow {
                IllegalArgumentException("url must not be null")
            }

            val instance = ActivitiesMedia(
                type = type,
                propertySize = propertySize,
                url = url,
                title = title,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        type = type,
        propertySize = propertySize,
        url = url,
        title = title,
    )
}
