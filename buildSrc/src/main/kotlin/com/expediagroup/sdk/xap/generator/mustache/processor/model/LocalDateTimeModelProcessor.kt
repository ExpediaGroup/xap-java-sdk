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
package com.expediagroup.sdk.xap.generator.mustache.processor.model

import org.openapitools.codegen.CodegenModel
import java.io.Serializable

/**
 * Rewrites specific fields in selected models so that the generated code
 * uses `java.time.LocalDateTime` instead of the default type.
 *
 * The processor checks every `CodegenModel` emitted by OpenAPI-Generator:
 *  * If the model’s simple name matches one of the entries in [targetedModelsList],
 *  * every property whose name appears in that entry’s `params` list
 *    has its `dataType` forcibly set to `"java.time.LocalDateTime"`.
 *
 * Usage: register the processor in the generator's config (`com.expediagroup.sdk.openapigenerator`):
 * - `additionalProperties.put("modelProcessors", listOf(LocalDateTimeModelProcessor()))`
 *
 * **Note: Must be used with the default EG SDK mustache templates**
 */
class LocalDateTimeModelProcessor : Serializable, (CodegenModel) -> CodegenModel {

    /**
     * Declarative mapping of **model name → list of field names** that must be
     * generated as `LocalDateTime`.
     */
    internal data class TargetedModel(
        val name: String,
        val params: List<String>
    ) : Serializable

    private val targetedModelsList = listOf(
        TargetedModel("ActivitiesCancellationPolicy", listOf("freeCancellationEndDateTime")),
        TargetedModel("AvailableTimeSlot", listOf("dateTime")),
        TargetedModel("CarsCancellationPolicy", listOf("freeCancellationEndDateTime")),
        TargetedModel("NonCancellableDateTimeRange", listOf("startDateTime", "endDateTime")),
        TargetedModel("PenaltyRule", listOf("startDateTime", "endDateTime")),
        TargetedModel("VendorLocationDetails", listOf("dateTime", "endDateTime")),
    )

    /** Applies the rewrite, then returns the (potentially) modified model. */
    override fun invoke(codegenModel: CodegenModel): CodegenModel = codegenModel.apply {
        targetedModelsList.firstOrNull { it.name == name }?.params?.let { names ->
            allVars.filter {
                it.name in names
            }.forEach {
                it.dataType = "java.time.LocalDateTime"
            }
        }
    }
}
