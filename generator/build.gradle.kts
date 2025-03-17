import com.samskivert.mustache.Mustache
import com.samskivert.mustache.Template
import org.jlleitschuh.gradle.ktlint.tasks.KtLintFormatTask
import org.openapitools.codegen.CodegenConstants
import org.openapitools.codegen.CodegenResponse
import java.io.Serializable
import java.io.Writer

plugins {
    kotlin("jvm") version "2.1.10"
    id("com.expediagroup.sdk.openapigenerator") version "0.0.3-beta-SNAPSHOT"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
}

group = "com.expediagroup.sdk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }
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
    inputSpec = System.getProperty("inputSpec") ?: "$projectDir/src/main/resources/specs.yaml"

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
    additionalProperties.put("allowedMediaTypes", Lambdas.AllowedMediaTypesLambda())

    configOptions.put("sourceFolder", "")

    globalProperties.put("supportingFiles", "Room.kt")
}


class Lambdas {
    class AllowedMediaTypesLambda : Mustache.Lambda, Serializable {
        override fun execute(
            fragment: Template.Fragment,
            writer: Writer,
        ) {
            val response: CodegenResponse = fragment.context() as CodegenResponse
            if (response.is2xx) {
                val mediaTypes: List<String> =
                    response.content.keys.filter {
                        !it.contains("xml", ignoreCase = true)
                    }

                val context = mapOf("mediaTypes" to mediaTypes)
                fragment.execute(context, writer)
            }
        }
    }
}
