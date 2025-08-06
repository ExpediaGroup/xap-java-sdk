group = project.property("GROUP_ID") as String

plugins {
    application
    checkstyle
    id("com.github.hierynomus.license") version "0.16.1"
}

application {
    mainClass.set(project.findProperty("scenario") as String?)
    applicationDefaultJvmArgs +=
        listOf(
            "-Dxapkey=${findProperty("xapkey")}",
            "-Dxapsecret=${findProperty("xapsecret")}",
            "-Dvrbokey=${findProperty("vrbokey")}",
            "-Dvrbosecret=${findProperty("vrbosecret")}",
            "-Dpartnerkey=${findProperty("partnerkey")}",
            "-Dauthtype=${findProperty("authtype")}",
        )
}

license {
    header = File("$rootDir/LICENSE-HEADER.txt")
    strictCheck = true
    includes(
        listOf(
            "**/*.kt",
            "**/*.java",
        ),
    )
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

    implementation("com.expediagroup:expediagroup-sdk-transport-okhttp:1.0.0")

    implementation("org.apache.logging.log4j:log4j-api:2.25.1")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.25.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.19.2")
    implementation("org.apache.commons:commons-lang3:3.18.0")
}
