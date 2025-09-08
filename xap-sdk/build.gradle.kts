plugins {
    id("signing")
    id("maven-publish")
    id("org.jetbrains.dokka") version "2.0.0"
    id("com.github.hierynomus.license") version "0.16.1"
}

license {
    header = rootProject.file("LICENSE-HEADER.txt")
    strictCheck = true
    includes(
        listOf(
            "**/*.kt",
            "**/*.java",
        ),
    )
}

group = property("GROUP_ID") as String
version = property("VERSION") as String

dependencies {
    api("com.expediagroup:expediagroup-sdk-rest:1.0.0")

    implementation(platform("com.fasterxml.jackson:jackson-bom:2.20.0"))
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    implementation("org.slf4j:slf4j-api:2.0.17")

    dokkaHtmlPlugin("org.jetbrains.dokka:versioning-plugin:2.0.0")
}

dokka {
    moduleName = "XAP SDK"

    pluginsConfiguration {
        html {
            val dokkaAssets = File(project.findProperty("dokka-assets.location").toString())

            customAssets.from(File(dokkaAssets, "logo-icon.svg"))
            customStyleSheets.from(File(dokkaAssets, "custom-styles.css"))
        }

        versioning {
            version = project.property("VERSION") as String

            olderVersionsDir.set(File(project.findProperty("dokka-old-versions.location").toString()))

            renderVersionsNavigationOnAllPages = true
        }
    }
}

apply("$rootDir/gradle-tasks/publish.gradle.kts")
apply("$rootDir/gradle-tasks/signing.gradle.kts")
apply("$rootDir/gradle-tasks/sdk-properties.gradle.kts")
