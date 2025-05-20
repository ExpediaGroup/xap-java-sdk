package com.expediagroup.sdk.xap.generator.mustache.processor.operation

import org.openapitools.codegen.CodegenOperation
import java.io.Serializable

class SegmentsOperationParamsProcessor(
    private val modelPackage: String
) : Serializable, (CodegenOperation) -> CodegenOperation {

    private val segmentsRegex = Regex("""^segment\d+\w*$""", RegexOption.IGNORE_CASE)
    private val targetedOperations = listOf("GetFlightListings")

    override fun invoke(operation: CodegenOperation): CodegenOperation {

        if (targetedOperations.contains(operation.baseName)) {
            var needsSegmentsHelperMethod = false

            operation.queryParams.forEach {
                if (segmentsRegex.matches(it.paramName)) {
                    it.vendorExtensions["x-replacedByBuilderHelper"] = true
                    needsSegmentsHelperMethod = true
                }
            }

            if (needsSegmentsHelperMethod) {
                operation.vendorExtensions["x-needsBuilderHelper"] = true
                operation.vendorExtensions["x-builderHelpersCode"] = "${segmentsHelperTextImpl()} \n"
            }
        }

        return operation
    }

    private fun segmentsHelperTextImpl() = (1..6).joinToString("\n\n") { i ->
        """
        fun segment$i(
            segment: ${modelPackage}.GetFlightListingsOperationSegmentParam
        ) = apply {
            this.segment${i}Origin           = segment.origin
            this.segment${i}Destination      = segment.destination
            this.segment${i}DepartureDate    = segment.departureDate
            this.segment${i}DepartureStartTime = segment.departureStartTime
            this.segment${i}DepartureEndTime   = segment.departureEndTime
        }
        """.trimIndent()
    }
}
