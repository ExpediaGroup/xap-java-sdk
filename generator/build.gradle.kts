import com.expediagroup.sdk.xap.generator.mustache.AllowedMediaTypesLambda
import org.openapitools.codegen.CodegenConstants

plugins {
    id("com.expediagroup.sdk.openapigenerator") version "0.0.9-alpha"
}

group = project.property("GROUP_ID") as String

dependencies {
    api("org.openapitools:openapi-generator:7.11.0")
}

openApiGenerate {
    inputSpec = System.getProperty("inputSpec") ?: "$projectDir/src/main/resources/specs.yaml"

    packageName = "com.expediagroup.sdk.xap"
    invokerPackage = "com.expediagroup.sdk.xap"
    apiPackage = "com.expediagroup.sdk.xap.operations"
    modelPackage = "com.expediagroup.sdk.xap.models"

    dryRun = false
    cleanupOutput = false
    generateApiDocumentation = false
    generateApiTests = false
    generateModelTests = false
    enablePostProcessFile = true

    templateDir = "$projectDir/src/main/resources/templates"
    configFile = "$projectDir/src/main/resources/generator-config.yaml"
    outputDir = "$rootDir/xap-sdk/src/main/kotlin"

    additionalProperties.put(CodegenConstants.ENUM_PROPERTY_NAMING, "UPPERCASE")
    additionalProperties.put("allowedMediaTypes", AllowedMediaTypesLambda())

    configOptions.put("sourceFolder", "")

    globalProperties.put("supportingFiles", "Room.kt")
}

tasks.named("openApiGenerate").configure {
    doLast {
        project.providers.exec {
            commandLine(
                "../gradlew ktlintFormat".split(" "),
            )
            workingDir = File("$rootDir/xap-sdk").absoluteFile
        }
    }
}
