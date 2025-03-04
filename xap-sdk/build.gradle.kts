import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm")
    id("java")
    id("maven-publish")
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
}

group = "com.expediagroup.sdk"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    api("com.expediagroup:sdk-rest:1.0.1-SNAPSHOT")
    api("com.expediagroup:sdk-core:1.0.0-SNAPSHOT")
    implementation("com.expediagroup:sdk-okhttp-transport:1.0.0-SNAPSHOT")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    implementation("org.hibernate:hibernate-validator:6.0.2.Final")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
    implementation("javax.validation:validation-api:2.0.1.Final")
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
            "ktlint_standard_enum-entry-name-case" to "disabled"
        )
}


publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "xap-sdk"
            version = "1.0.0-SNAPSHOT"
            groupId = project.property("groupId").toString()
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
    }
}
