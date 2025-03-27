plugins {
    id("signing")
    id("maven-publish")
}

group = property("GROUP_ID") as String
version = property("VERSION") as String

dependencies {
    api("com.expediagroup:expediagroup-sdk-rest:0.0.6-alpha")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
    runtimeOnly("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.2")
    implementation("org.slf4j:slf4j-api:2.0.17")
}

apply("$rootDir/gradle-tasks/publish.gradle.kts")
apply("$rootDir/gradle-tasks/signing.gradle.kts")
apply("$rootDir/gradle-tasks/sdk-properties.gradle.kts")
