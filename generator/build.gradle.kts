plugins {
    id("com.expediagroup.sdk.openapigenerator") version "0.0.9-alpha"
}

group = project.property("GROUP_ID") as String

dependencies {
    api("org.openapitools:openapi-generator:7.11.0")
}
