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
 * Adjusts selected operation parameters so that they’re generated as
 * `java.time.LocalDateTime` instead of the default type.
 *
 * For each `CodegenOperation` emitted by OpenAPI-Generator:
 *  1. If the operation’s `baseName` matches an entry in [targetedOperationsList],
 *  2. Every query parameter whose `baseName` appears in that entry’s `params`
 *     list has its `dataType` set to `"java.time.LocalDateTime"`.
 *
 * Usage: register the processor in the generator's config (`com.expediagroup.sdk.openapigenerator`):
 * - `additionalProperties.put("operationProcessors", listOf(LocalDateTimeOperationParamsProcessor()))`
 *
 * **Note: Must be used with the default EG SDK mustache templates**
 */
class LocalDateTimeOperationParamsProcessor : Serializable, (CodegenOperation) -> CodegenOperation {

    /**
     * Maps an operation name to the list of its query parameter names
     * that should be generated as `LocalDateTime`.
     */
    internal data class TargetedOperation(
        val name: String,
        val params: List<String>
    ) : Serializable

    private val targetedOperationsList = listOf(
        TargetedOperation("GetCarsListings", listOf("pickupTime", "dropOffTime"))
    )

    /** Applies the rewrite, then returns the (potentially) modified operation. */
    override fun invoke(operation: CodegenOperation): CodegenOperation = operation.apply {
        targetedOperationsList.firstOrNull { it.name == baseName }?.params?.let { names ->
            queryParams.filter {
                it.baseName in names
            }.forEach {
                it.dataType = "java.time.LocalDateTime"
            }
        }
    }
}
