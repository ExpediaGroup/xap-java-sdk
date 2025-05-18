package com.expediagroup.sdk.xap.generator.mustache.processor.model

import org.openapitools.codegen.CodegenModel
import java.io.Serializable

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

    override fun invoke(codegenModel: CodegenModel): CodegenModel {
        val targetedModel = targetedModelsList.find { it.name == codegenModel.name }

        targetedModel?.let {
            codegenModel.allVars
                .filter { modelVar ->  targetedModel.params.contains(modelVar.name) }
                .forEach { it.dataType = "java.time.LocalDateTime"}
        }

        return codegenModel
    }
}
