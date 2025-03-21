import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.1.10"
    id("java")
    id("signing")
    id("maven-publish")
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = project.property("ARTIFACT_NAME").toString()
            groupId = project.property("GROUP_ID").toString()
            version =
                if (project.hasProperty("SNAPSHOT_VERSION")) {
                    project.property("SNAPSHOT_VERSION").toString()
                } else {
                    project.property("VERSION").toString()
                }
            description = project.findProperty("DESCRIPTION")?.toString()

            pom {
                name.set(project.property("ARTIFACT_NAME").toString())
                description.set(project.findProperty("DESCRIPTION")?.toString())
                url.set(project.property("POM_URL").toString())

                licenses {
                    license {
                        name.set(project.property("LICENSE_NAME").toString())
                        url.set(project.property("LICENSE_URL").toString())
                        distribution.set(project.property("LICENSE_DISTRIBUTION").toString())
                        comments.set(project.property("LICENSE_COMMENTS").toString())
                    }
                }

                developers {
                    developer {
                        name.set(project.property("DEVELOPER_NAME").toString())
                        organization.set(project.property("DEVELOPER_ORG").toString())
                        organizationUrl.set(project.property("DEVELOPER_ORG_URL").toString())
                    }
                }

                scm {
                    url.set(project.property("POM_SCM_URL").toString())
                    connection.set(project.property("POM_SCM_CONNECTION").toString())
                    developerConnection.set(project.property("POM_SCM_DEVELOPER_CONNECTION").toString())
                }
            }
        }
    }
}

signing {
    val signingKey = System.getenv("GPG_SECRET")
    val signingPassword = System.getenv("GPG_PASSPHRASE")

    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
}

dependencies {
    testImplementation(kotlin("test"))
    api("com.expediagroup:expediagroup-sdk-rest:0.0.1-beta-SNAPSHOT")
    implementation("com.expediagroup:expediagroup-sdk-transport-okhttp:0.0.1-beta-SNAPSHOT")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    runtimeOnly("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.2")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
    compilerOptions.jvmTarget.set(JvmTarget.JVM_1_8)
}

java {
    withSourcesJar()
    targetCompatibility = JavaVersion.VERSION_1_8
    sourceCompatibility = JavaVersion.VERSION_1_8
}

ktlint {
    debug = true
    version = "1.5.0"
    verbose = true

    additionalEditorconfig =
        mapOf(
            "max_line_length" to "200",
            "indent_style" to "space",
            "indent_size" to "4",
            "insert_final_newline" to "true",
            "end_of_line" to "lf",
            "ktlint_standard_enum-entry-name-case" to "disabled",
        )
}

gradle.taskGraph.whenReady {
    if (hasTask(":publishSnapshots")) {
        rootProject.version = "1.0.0-SNAPSHOT"
        println("📌 Setting root project version to 1.0.0-SNAPSHOT for publishSnapshots task")
    }
}

tasks.register("publishSnapshots") {
    val snapshotModules =
        rootProject.subprojects.filter { project ->
            project.version.toString().contains("-SNAPSHOT") && project.tasks.names.contains("publish")
        }

    if (snapshotModules.isNotEmpty()) {
        dependsOn(snapshotModules.map { ":${it.name}:publish" })
    }

    doLast {
        if (snapshotModules.isEmpty()) {
            println("❌ No snapshot modules to publish.")
        } else {
            println("📦 Successfully published snapshots for: ${snapshotModules.map { it.name }}")
        }
    }
}
