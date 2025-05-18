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
    override fun invoke(operation: CodegenOperation): CodegenOperation = targetedOperationsList
        .find {
            it.name == operation.baseName
        }?.let {
            operation.queryParams
                .filter { operationVar ->
                    it.params.contains(operationVar.baseName)
                }.forEach { operationVar ->
                    operationVar.dataType = "java.time.LocalDateTime"
                }
        }.let {
            operation
        }
}
