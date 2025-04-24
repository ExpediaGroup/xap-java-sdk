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
package com.expediagroup.sdk.xap.infrastructure

typealias MultiValueMap = MutableMap<String, List<String>>

fun collectionDelimiter(collectionFormat: String) =
    when (collectionFormat) {
        "csv" -> ","
        "tsv" -> "\t"
        "pipe" -> "|"
        "space" -> " "
        else -> ""
    }

val defaultMultiValueConverter: (item: Any?) -> String = { item -> "$item" }

fun <T : Any?> toMultiValue(
    items: Array<T>,
    collectionFormat: String,
    map: (item: T) -> String = defaultMultiValueConverter,
) = toMultiValue(items.asIterable(), collectionFormat, map)

fun <T : Any?> toMultiValue(
    items: Iterable<T>,
    collectionFormat: String,
    map: (item: T) -> String = defaultMultiValueConverter,
): List<String> =
    when (collectionFormat) {
        "multi" -> items.map(map)
        else -> listOf(items.joinToString(separator = collectionDelimiter(collectionFormat), transform = map))
    }
