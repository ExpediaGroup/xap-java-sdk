plugins {
    id("java")
}

group = project.property("GROUP_ID") as String

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":xap-sdk"))

    implementation("org.apache.logging.log4j:log4j-api:2.24.3")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.24.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
    implementation("org.apache.commons:commons-lang3:3.17.0")
}
