tasks.register("publishSnapshot") {
    val artifactId = project.property("ARTIFACT_NAME") as String
    val version = if (project.findProperty("SNAPSHOT_VERSION") != null) {
        project.findProperty("SNAPSHOT_VERSION") as String
    } else {
        project.property("VERSION") as String
    }

    require(project.tasks.names.contains("publish")) {
        "Publish task must be present to publish a $artifactId snapshot"
    }

    require(version.contains("-SNAPSHOT")) {
        "$artifactId:$version version must contain -SNAPSHOT to publish snapshots."
    }

    dependsOn("${project.name}:publish")
    doLast {
        val url = "https://oss.sonatype.org/content/repositories/snapshots/com/expediagroup/$artifactId/$version/"
        println("📦 Successfully published $artifactId:$version snapshot.")
        println("🌱 Snapshot available at: $url")
    }
}
