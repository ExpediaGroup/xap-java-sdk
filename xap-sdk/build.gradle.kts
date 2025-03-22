import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.1.10"
    id("java")
    id("signing")
    id("maven-publish")
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    api("com.expediagroup:expediagroup-sdk-rest:0.0.1-beta-SNAPSHOT")
    implementation("com.expediagroup:expediagroup-sdk-transport-okhttp:0.0.1-beta-SNAPSHOT")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    runtimeOnly("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.2")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
    compilerOptions.jvmTarget.set(JvmTarget.JVM_1_8)
}

java {
    withSourcesJar()
    targetCompatibility = JavaVersion.VERSION_1_8
    sourceCompatibility = JavaVersion.VERSION_1_8
}

ktlint {
    debug = true
    version = "1.5.0"
    verbose = true

    additionalEditorconfig =
        mapOf(
            "max_line_length" to "200",
            "indent_style" to "space",
            "indent_size" to "4",
            "insert_final_newline" to "true",
            "end_of_line" to "lf",
            "ktlint_standard_enum-entry-name-case" to "disabled",
        )
}

apply("$rootDir/gradle-tasks/publish.gradle.kts")
apply("$rootDir/gradle-tasks/signing.gradle.kts")
apply("$rootDir/gradle-tasks/snapshot.gradle.kts")
