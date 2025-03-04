import com.samskivert.mustache.Mustache
import com.samskivert.mustache.Template
import java.io.Serializable
import java.io.Writer
import org.openapitools.codegen.CodegenConstants
import org.openapitools.codegen.CodegenResponse

plugins {
    kotlin("jvm") version "2.1.10"
    id("com.expediagroup.sdk.openapigenerator") version "1.0.0-SNAPSHOT"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
}

group = "com.expediagroup.sdk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

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
    inputSpec = System.getProperty("inputSpec") ?: "specs.yaml"

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
    configFile = "$projectDir/src/main/generator-config.yaml"
    outputDir = "$rootDir/xap-sdk/src/main/kotlin"

    additionalProperties.put(CodegenConstants.ENUM_PROPERTY_NAMING, "UPPERCASE")
    additionalProperties.put("allowedMediaTypes", Lambdas.AllowedMediaTypesLambda())

    configOptions.put("sourceFolder", "")

    globalProperties.put("supportingFiles", "Room.kt")
    globalProperties.put("KOTLIN_POST_PROCESS_FILE", "npm run --prefix post-processor process")
}

class Lambdas {
    class AllowedMediaTypesLambda : Mustache.Lambda, Serializable {
        override fun execute(fragment: Template.Fragment, writer: Writer) {
            val response: CodegenResponse = fragment.context() as CodegenResponse
            if (response.is2xx) {
                val mediaTypes: List<String> = response.content.keys.filter {
                    !it.contains("xml", ignoreCase = true)
                }

                val context = mapOf("mediaTypes" to mediaTypes)
                fragment.execute(context, writer)
            }
        }
    }
}
