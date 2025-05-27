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

class SegmentsOperationParamsProcessor(
    private val modelPackage: String
) : Serializable, (CodegenOperation) -> CodegenOperation {

    private val segmentsRegex = Regex("""^segment\d+\w*$""", RegexOption.IGNORE_CASE)
    private val targetedOperations = listOf("GetFlightListings")

    override fun invoke(operation: CodegenOperation): CodegenOperation = operation.apply {
        if (baseName in targetedOperations) {
            queryParams.filter {
                segmentsRegex.matches(it.paramName)
            }.takeIf {
                it.isNotEmpty()
            }?.also { rooms ->
                rooms.forEach { p ->
                    p.vendorExtensions["x-replacedByBuilderHelper"] = true
                }
                vendorExtensions["x-needsBuilderHelper"] = true
                vendorExtensions["x-builderHelpersCode"] = segmentsHelperTextImpl().plus("\n")
            }
        }
    }

    private fun segmentsHelperTextImpl() = (1..6).joinToString("\n\n") { i ->
        """
        fun segment$i(
            segment: ${modelPackage}.GetFlightListingsOperationSegmentParam
        ) = apply {
            this.segment${i}Origin = segment.origin
            this.segment${i}Destination = segment.destination
            this.segment${i}DepartureDate = segment.departureDate
            this.segment${i}DepartureStartTime = segment.departureStartTime
            this.segment${i}DepartureEndTime = segment.departureEndTime
        }
        """.trimIndent()
    }
}
