group = project.property("GROUP_ID") as String

plugins {
    application
    checkstyle
}

application {
    mainClass.set("com.expediagroup.sdk.xap.examples.XapSdkDemoTestRun")
}

checkstyle {
    val archive =
        configurations.checkstyle.get().resolve().filter {
            it.name.startsWith("checkstyle")
        }

    config = resources.text.fromArchiveEntry(archive, "google_checks.xml")
}

dependencies {
    api(project(":xap-sdk"))

    implementation("com.expediagroup:expediagroup-sdk-transport-okhttp:0.0.4-alpha")

    implementation("org.apache.logging.log4j:log4j-api:2.24.3")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.24.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
    implementation("org.apache.commons:commons-lang3:3.17.0")
}
