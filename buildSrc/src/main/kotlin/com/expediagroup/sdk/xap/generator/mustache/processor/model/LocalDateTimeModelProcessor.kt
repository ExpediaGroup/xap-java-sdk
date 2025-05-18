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
 * Usage: register the processor in the generator's config:
 * - `additionalProperties.put("modelProcessors", listOf(LocalDateTimeModelProcessor()))`
 */
class LocalDateTimeModelProcessor : Serializable, (CodegenModel) -> CodegenModel {

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

    override fun invoke(codegenModel: CodegenModel): CodegenModel = targetedModelsList
        .find {
            it.name == codegenModel.name
        }?.let {
            codegenModel.allVars
                .filter { modelVar ->
                    it.params.contains(modelVar.name)
                }.forEach { modelVar ->
                    modelVar.dataType = "java.time.LocalDateTime"
                }
        }.let {
            codegenModel
        }
}
