plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    api("org.openapitools:openapi-generator:7.27.0")
    implementation("com.samskivert:jmustache:1.16")
}
