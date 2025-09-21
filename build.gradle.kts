import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.time.Duration

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.2.20"
    id("org.jlleitschuh.gradle.ktlint") version "13.1.0"
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
}

group = project.property("GROUP_ID") as String

apply("$rootDir/gradle-tasks/snapshot.gradle")

allprojects {
    repositories {
        mavenCentral()
        mavenLocal()
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    plugins.withId("org.jetbrains.kotlin.jvm") {
        kotlin {
            jvmToolchain(8)
        }
    }

    plugins.withType<KotlinBasePluginWrapper>().configureEach {
        tasks.withType<KotlinCompile>().configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_1_8)
            }
        }
    }

    tasks.test {
        useJUnitPlatform()
    }

    java {
        withSourcesJar()
        withJavadocJar()

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8

        // Gradle 7+ Java toolchain approach (also sets 1.8)
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
    }

    ktlint {
        debug.set(true)
        version.set("1.5.0")
        verbose.set(true)

        additionalEditorconfig.set(
            mapOf(
                "indent_style" to "space",
                "indent_size" to "4",
                "insert_final_newline" to "true",
                "end_of_line" to "lf",
            ),
        )
    }
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))
            username = System.getenv("SONATYPE_USERNAME")
            password = System.getenv("SONATYPE_PASSWORD")
        }
    }

    transitionCheckOptions {
        maxRetries = 60
        delayBetween = Duration.ofMillis(5000)
    }
}
