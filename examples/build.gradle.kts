plugins {
    id("java")
}

group = "com.expediagroup.sdk"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
}

dependencies {
    api(project(":xap-sdk"))

    api("org.apache.logging.log4j:log4j-api:2.24.3")
    api("org.apache.logging.log4j:log4j-slf4j2-impl:2.24.3")
    api("com.fasterxml.jackson.core:jackson-databind:2.18.3")
    implementation("org.apache.commons:commons-lang3:3.17.0")
}
