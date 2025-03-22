import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.time.Duration

plugins {
    kotlin("jvm") version "2.1.10"
    id("signing")
    id("maven-publish")
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
}

group = "com.expediagroup.sdk"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

subprojects {
    apply(plugin = "signing")
    apply(plugin = "maven-publish")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
    }

    kotlin {
        jvmToolchain(21)
        target {
            compilerOptions {
                jvmTarget = JvmTarget.JVM_1_8
            }
        }
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

nexusPublishing {
    repositories {
        sonatype {
            username.set(System.getenv("SONATYPE_USERNAME"))
            password.set(System.getenv("SONATYPE_PASSWORD"))
        }
    }

    transitionCheckOptions {
        maxRetries.set(60)
        delayBetween.set(Duration.ofMillis(5000))
    }
}

kotlin {
    jvmToolchain(21)
}

apply("gradle-tasks/specs.gradle.kts")
