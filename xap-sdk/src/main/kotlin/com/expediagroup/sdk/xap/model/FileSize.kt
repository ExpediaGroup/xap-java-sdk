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
 * The information about the file size.
 * @param unit The unit about the file size.
 * @param `value` The value about the file size.
 */
@ConsistentCopyVisibility data class FileSize private constructor(
    /* The unit about the file size. */
    @JsonProperty("unit")
    val unit: FileSize.Unit? = null,

    /* The value about the file size. */
    @JsonProperty("value")
    val `value`: kotlin.Long? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var unit: FileSize.Unit? = null,
        private var `value`: kotlin.Long? = null,
    ) {
        fun unit(unit: FileSize.Unit?) = apply { this.unit = unit }

        fun `value`(`value`: kotlin.Long?) = apply { this.`value` = `value` }

        fun build(): FileSize {
            val instance = FileSize(
                unit = unit,
                `value` = `value`,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        unit = unit,
        `value` = `value`,
    )

    /**
     * The unit about the file size.
     * Values: KB,MB,GB
     */
    enum class Unit(val value: kotlin.String) {
        @JsonProperty("KB")
        KB("KB"),

        @JsonProperty("MB")
        MB("MB"),

        @JsonProperty("GB")
        GB("GB"),
    }
}
