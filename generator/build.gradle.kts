import com.expediagroup.sdk.openapigenerator.mustache.SupportingTemplate
import com.expediagroup.sdk.xap.generator.mustache.lambda.AllowedMediaTypesLambda
import com.expediagroup.sdk.xap.generator.mustache.processor.model.LocalDateTimeModelProcessor
import com.expediagroup.sdk.xap.generator.mustache.processor.operation.LocalDateTimeOperationParamsProcessor
import com.expediagroup.sdk.xap.generator.mustache.processor.operation.RoomsOperationParamsProcessor
import com.expediagroup.sdk.xap.generator.mustache.processor.operation.SegmentsOperationParamsProcessor

plugins {
    id("com.expediagroup.sdk.openapigenerator") version "0.0.15-alpha"
}

group = project.property("GROUP_ID") as String

val specsDir = "$rootDir/specs"
val basePackageStr = "com.expediagroup.sdk.xap"

egSdkGenerator {
    namespace = "xap"
    basePackage = basePackageStr
    specFilePath = File("$specsDir/transformed-spec.yaml")

    objectMapper = "com.expediagroup.sdk.xap.core.mapper.XapJacksonObjectMapper.INSTANCE"

    outputDir = File("$rootDir/xap-sdk/src/main/kotlin")
    customTemplatesDir = File("$projectDir/src/main/resources/templates")

    operationProcessors =
        listOf(
            RoomsOperationParamsProcessor("$basePackageStr.model"),
            SegmentsOperationParamsProcessor("$basePackageStr.model"),
            LocalDateTimeOperationParamsProcessor(),
        )

    modelProcessors =
        listOf(
            LocalDateTimeModelProcessor(),
        )

    lambdas =
        listOf(
            Pair("allowedMediaTypes", AllowedMediaTypesLambda()),
        )

    supportingTemplates =
        listOf(
            SupportingTemplate(
                templateFile = "room.mustache",
                destinationPath = "com/expediagroup/sdk/xap/model",
                fileName = "Room.kt",
            ),
            SupportingTemplate(
                templateFile = "get_flight_listings_operation_segment_param.mustache",
                destinationPath = "com/expediagroup/sdk/xap/model",
                fileName = "GetFlightListingsOperationSegmentParam.kt",
            ),
        )
}

/**
 * Generates the operations and models from the provided spec file and formats the generated code using ktlint.
 */
tasks.register<Exec>("generateAndFormat") {
    dependsOn(":generator:generateEgSdk")
    commandLine("sh", "-c", "../gradlew :xap-sdk:ktlintFormat")
}

/**
 * Merges all OpenAPI spec files into a single specs.yaml file.
 * This is done using the openapi-merge-cli tool.
 * The merged specs.yaml is used as input for the spec transformer (if needed).
 */
tasks.register<Exec>("mergeSpecs") {
    commandLine("npx", "openapi-merge-cli")
    workingDir = File(specsDir)
}

/**
 * Transforms the merged OpenAPI spec file using the Expedia Group spec transformer.
 * This transformation:
 * - Adds required headers
 * - Converts operation IDs to tags
 * - Sets default string type to single quotes
 * - Processes the merged specs.yaml and outputs transformed-specs.yaml
 */
tasks.register<Exec>("transformSpecs") {
    dependsOn("mergeSpecs")
    workingDir = File(specsDir)
    commandLine(
        "npx",
        "--yes",
        "-p",
        "@expediagroup/spec-transformer",
        "cli",
        "--headers",
        "accept,accept-encoding,key",
        "--operationIdsToTags",
        "--defaultStringType",
        "QUOTE_SINGLE",
        "--input",
        "specs.yaml",
        "--output",
        "transformed-spec.yaml",
    )
}
