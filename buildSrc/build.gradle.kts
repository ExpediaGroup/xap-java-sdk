repositories {
    mavenCentral()
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

plugins {
    `kotlin-dsl`
}

dependencies {
    api("org.openapitools:openapi-generator:7.11.0")
    implementation("com.samskivert:jmustache:1.15")
}
