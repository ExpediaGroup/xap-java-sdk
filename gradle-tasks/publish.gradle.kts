project.extensions.configure<PublishingExtension> {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = project.property("ARTIFACT_NAME") as String
            groupId = project.property("GROUP_ID") as String
            version = if (project.findProperty("SNAPSHOT_VERSION") != null) {
                project.findProperty("SNAPSHOT_VERSION") as String
            } else {
                project.property("VERSION") as String
            }
            description = project.findProperty("DESCRIPTION") as String?

            pom {
                name.set(project.property("ARTIFACT_NAME") as String)
                description.set(project.findProperty("DESCRIPTION") as String?)
                url.set(project.property("POM_URL") as String)

                licenses {
                    license {
                        name.set(project.property("LICENSE_NAME") as String)
                        url.set(project.property("LICENSE_URL") as String)
                        distribution.set(project.property("LICENSE_DISTRIBUTION") as String)
                        comments.set(project.property("LICENSE_COMMENTS") as String)
                    }
                }

                developers {
                    developer {
                        name.set(project.property("DEVELOPER_NAME") as String)
                        organization.set(project.property("DEVELOPER_ORG") as String)
                        organizationUrl.set(project.property("DEVELOPER_ORG_URL") as String)
                    }
                }

                scm {
                    url.set(project.property("POM_SCM_URL") as String)
                    connection.set(project.property("POM_SCM_CONNECTION") as String)
                    developerConnection.set(project.property("POM_SCM_DEVELOPER_CONNECTION") as String)
                }
            }
        }
    }
}
