plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    api("org.openapitools:openapi-generator:7.11.0")
    implementation("com.samskivert:jmustache:1.16")
}
