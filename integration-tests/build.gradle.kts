group = "com.expediagroup"
version = "0.0.1-SNAPSHOT"

dependencies {
    testImplementation(project(":xap-sdk"))

    // Testing
    testImplementation("com.expediagroup:expediagroup-sdk-transport-okhttp:0.0.5-alpha")

    testImplementation("io.hosuaby:inject-resources-junit-jupiter:1.0.0")
    testImplementation(platform("org.junit:junit-bom:5.13.4"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("io.mockk:mockk:1.13.16")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
    testImplementation("org.apache.commons:commons-lang3:3.17.0")
    testImplementation("org.apache.logging.log4j:log4j-api:2.24.3")
    testImplementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.24.3")
}
