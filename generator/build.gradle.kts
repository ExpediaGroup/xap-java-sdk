import com.expediagroup.sdk.xap.generator.mustache.AllowedMediaTypesLambda
import org.openapitools.codegen.CodegenConstants

plugins {
    `kotlin-dsl`
    kotlin("jvm") version "2.1.10"
    id("com.expediagroup.sdk.openapigenerator") version "0.0.3-beta-SNAPSHOT"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
}

group = "com.expediagroup.sdk"
version = "1.0-SNAPSHOT"

dependencies {
    api("org.openapitools:openapi-generator:7.11.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
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
