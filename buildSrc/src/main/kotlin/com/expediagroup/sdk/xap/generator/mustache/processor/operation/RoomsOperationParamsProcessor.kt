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
package com.expediagroup.sdk.xap.generator.mustache.processor.operation

import org.openapitools.codegen.CodegenOperation
import java.io.Serializable

/**
 * Marks individual `roomN…` query parameters for replacement and injects
 * a consolidated `rooms(...)` builder helper into targeted operations.
 *
 * For each `CodegenOperation` whose `baseName` is in [targetedOperations]:
 *  1. Finds query parameters matching [roomRegex].
 *  2. Flags each matched parameter with `x-replacedByBuilderHelper`.
 *  3. If any were found, sets:
 *     - `x-needsBuilderHelper` to true, and
 *     - `x-builderHelpersCode` to the Kotlin implementation returned by [roomsHelperTextImpl].
 *
 * Usage: register the processor in the generator's config (`com.expediagroup.sdk.openapigenerator`):
 * - `additionalProperties.put("operationProcessors", listOf(RoomsOperationParamsProcessor()))`
 *
 * **Note: Must be used with the default EG SDK mustache templates**
 */
class RoomsOperationParamsProcessor(
    private val modelPackage: String
) : Serializable, (CodegenOperation) -> CodegenOperation {

    /** Regex to identify room parameters like `room1Adult`, `room2ChildAges`, etc. */
    private val roomRegex = Regex("""^room\d+\w*$""", RegexOption.IGNORE_CASE)

    /** List of operation names where the rooms helper should be applied. */
    private val targetedOperations = listOf("GetLodgingQuotes", "GetLodgingListings")

    /**
     * Scans `queryParams` for matches against [roomRegex].
     * Flags each match, and if any are found, injects the builder helper code.
     *
     * @param operation the operation to inspect and modify
     * @return the same [CodegenOperation], with vendor extensions set as needed
     */

    override fun invoke(operation: CodegenOperation): CodegenOperation = operation.apply {
        if (baseName in targetedOperations) {
            queryParams.filter {
                roomRegex.matches(it.paramName)
            }.takeIf {
                it.isNotEmpty()
            }?.also { rooms ->
                rooms.forEach { p ->
                    p.vendorExtensions["x-replacedByBuilderHelper"] = true
                }
                vendorExtensions["x-needsBuilderHelper"] = true
                vendorExtensions["x-builderHelpersCode"] = roomsHelperTextImpl().plus("\n")
            }
        }
    }

    /**
     * Returns the Kotlin code for the `rooms(...)` builder extension.
     * This snippet replaces individual `roomN…` methods with a loop over a `List<Room>`.
     */
    private fun roomsHelperTextImpl() = """
        fun rooms(rooms: List<${modelPackage}.Room>) =
            apply {
                require(rooms.size <= 8) {
                    "A maximum of 8 rooms can be specified"
                }

                rooms.elementAtOrNull(0)?.let {
                    this.room1Adults = it.adults
                    this.room1ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(1)?.let {
                    this.room2Adults = it.adults
                    this.room2ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(2)?.let {
                    this.room3Adults = it.adults
                    this.room3ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(3)?.let {
                    this.room4Adults = it.adults
                    this.room4ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(4)?.let {
                    this.room5Adults = it.adults
                    this.room5ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(5)?.let {
                    this.room6Adults = it.adults
                    this.room6ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(6)?.let {
                    this.room7Adults = it.adults
                    this.room7ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(7)?.let {
                    this.room8Adults = it.adults
                    this.room8ChildAges = it.childAges?.map { item -> item.toString() }
                }
            }
    """.trimIndent()
}
