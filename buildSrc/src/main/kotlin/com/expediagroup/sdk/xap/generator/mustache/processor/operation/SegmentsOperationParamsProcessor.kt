package com.expediagroup.sdk.xap.generator.mustache.processor.operation

import org.openapitools.codegen.CodegenOperation
import java.io.Serializable

class SegmentsOperationParamsProcessor : Serializable, (CodegenOperation) -> CodegenOperation {

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

    private fun segmentsHelperTextImpl() = """
        fun segment1(segment: com.expediagroup.sdk.xap.models.GetFlightListingsOperationSegmentParam) = apply {
          this.segment1Origin = segment.origin
          this.segment1Destination = segment.destination
          this.segment1DepartureDate = segment.departureDate
          this.segment1DepartureStartTime = segment.departureStartTime
          this.segment1DepartureEndTime = segment.departureEndTime
        }

        fun segment2(segment: com.expediagroup.sdk.xap.models.GetFlightListingsOperationSegmentParam) = apply {
          this.segment2Origin = segment.origin
          this.segment2Destination = segment.destination
          this.segment2DepartureDate = segment.departureDate
          this.segment2DepartureStartTime = segment.departureStartTime
          this.segment2DepartureEndTime = segment.departureEndTime
        }

        fun segment3(segment: com.expediagroup.sdk.xap.models.GetFlightListingsOperationSegmentParam) = apply {
          this.segment3Origin = segment.origin
          this.segment3Destination = segment.destination
          this.segment3DepartureDate = segment.departureDate
          this.segment3DepartureStartTime = segment.departureStartTime
          this.segment3DepartureEndTime = segment.departureEndTime
        }

        fun segment4(segment: com.expediagroup.sdk.xap.models.GetFlightListingsOperationSegmentParam) = apply {
          this.segment4Origin = segment.origin
          this.segment4Destination = segment.destination
          this.segment4DepartureDate = segment.departureDate
          this.segment4DepartureStartTime = segment.departureStartTime
          this.segment4DepartureEndTime = segment.departureEndTime
        }

        fun segment5(segment: com.expediagroup.sdk.xap.models.GetFlightListingsOperationSegmentParam) = apply {
          this.segment5Origin = segment.origin
          this.segment5Destination = segment.destination
          this.segment5DepartureDate = segment.departureDate
          this.segment5DepartureStartTime = segment.departureStartTime
          this.segment5DepartureEndTime = segment.departureEndTime
        }

        fun segment6(segment: com.expediagroup.sdk.xap.models.GetFlightListingsOperationSegmentParam) = apply {
          this.segment6Origin = segment.origin
          this.segment6Destination = segment.destination
          this.segment6DepartureDate = segment.departureDate
          this.segment6DepartureStartTime = segment.departureStartTime
          this.segment6DepartureEndTime = segment.departureEndTime
        }
    """.trimIndent()
}
