import com.expediagroup.sdk.xap.generator.mustache.lambda.AllowedMediaTypesLambda
import com.expediagroup.sdk.xap.generator.mustache.processor.model.LocalDateTimeModelProcessor
import com.expediagroup.sdk.xap.generator.mustache.processor.operation.LocalDateTimeOperationParamsProcessor
import com.expediagroup.sdk.xap.generator.mustache.processor.operation.RoomsOperationParamsProcessor
import com.expediagroup.sdk.xap.generator.mustache.processor.operation.SegmentsOperationParamsProcessor
import org.openapitools.codegen.CodegenConstants

plugins {
    id("com.expediagroup.sdk.openapigenerator") version "0.0.12-alpha"
}

group = project.property("GROUP_ID") as String

dependencies {
    api("org.openapitools:openapi-generator:7.11.0")
}

val specsDir = "$rootDir/specs"
val basePackageStr = "${project.property("GROUP_ID")}.sdk.${project.property("namespace")}"
val apiPackageStr = "$basePackageStr.operation"
val modelPackageStr = "$basePackageStr.model"

/**
 * Configuration for OpenAPI code generation.
 * This configures how the OpenAPI Generator will create the SDK code from the API specs.
 */
openApiGenerate {
    // Path to the transformed OpenAPI specification file
    inputSpec = "$specsDir/transformed-specs.yaml"

    // Java package configuration
    packageName = basePackageStr
    apiPackage = apiPackageStr
    modelPackage = modelPackageStr

    // Generation behavior settings
    dryRun = false
    cleanupOutput = false
    generateApiDocumentation = false
    generateApiTests = false
    generateModelTests = false
    enablePostProcessFile = true

    // Paths to resources needed for generation
    templateDir = "$projectDir/src/main/resources/templates"
    configFile = "$projectDir/src/main/resources/generator-config.yaml"
    outputDir = "$rootDir/xap-sdk/src/main/kotlin"

    // Additional configuration properties
    additionalProperties.put(CodegenConstants.ENUM_PROPERTY_NAMING, "UPPERCASE")
    additionalProperties.put("allowedMediaTypes", AllowedMediaTypesLambda())
    additionalProperties.put(
        "operationProcessors",
        listOf(
            RoomsOperationParamsProcessor(modelPackageStr),
            SegmentsOperationParamsProcessor(modelPackageStr),
            LocalDateTimeOperationParamsProcessor(),
        ),
    )

    additionalProperties.put("modelProcessors", listOf(LocalDateTimeModelProcessor()))
    additionalProperties.put("modelPackage", modelPackage)

    configOptions.put("sourceFolder", "")

    // Additional files to generate beyond the core API/model classes
    globalProperties.put("supportingFiles", "Room.kt,GetFlightListingsOperationSegmentParam.kt")
}

/**
 * Runs the entire code generation process in a single command:
 * 1. Merges OpenAPI spec files into a single specs.yaml file
 * 2. Transforms the OpenAPI spec files using EG spec transformer
 * 3. Generates the SDK code based on the transformed spec
 * 4. Formats license headers in the generated code
 * 5. Applies ktlint formatting to ensure code style consistency
 *
 * This task is the main entry point for the SDK generation workflow.
 */
tasks.register<Exec>("generateAll") {
    dependsOn(":generator:transformSpecs")
    commandLine(
        "sh",
        "-c",
        """
        ../gradlew :generator:openApiGenerate &&
        ../gradlew :xap-sdk:licenseFormatMain &&
        ../gradlew :xap-sdk:ktlintFormat
        """.trimIndent(),
    )
}

/**
 * Merges all OpenAPI spec files into a single specs.yaml file.
 * This is done using the openapi-merge-cli tool.
 * The merged specs.yaml is used as input for the spec transformer.
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
        "transformed-specs.yaml",
    )
}
