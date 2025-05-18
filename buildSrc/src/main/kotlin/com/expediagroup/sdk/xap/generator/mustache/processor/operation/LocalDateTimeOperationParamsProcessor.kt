package com.expediagroup.sdk.xap.generator.mustache.processor.operation

import org.openapitools.codegen.CodegenOperation
import java.io.Serializable

class LocalDateTimeOperationParamsProcessor : Serializable, (CodegenOperation) -> CodegenOperation {

    internal data class TargetedOperation(
        val name: String,
        val params: List<String>
    ) : Serializable

    private val targetedOperationsList = listOf(
        TargetedOperation("GetCarsListings", listOf("pickupTime", "dropOffTime"))
    )

    override fun invoke(operation: CodegenOperation): CodegenOperation {
        val targetedOperation = targetedOperationsList.find { it.name == operation.baseName }

        targetedOperation?.let {
            operation.queryParams
                .filter { operationVar ->  targetedOperation.params.contains(operationVar.baseName) }
                .forEach { it.dataType = "java.time.LocalDateTime"}
        }

        return operation
    }
}
