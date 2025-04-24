import org.gradle.language.jvm.tasks.ProcessResources

// Retrieve properties from the Gradle project
val artifactName = project.property("ARTIFACT_NAME") as String
val version = project.property("VERSION") as String
val groupId = project.property("GROUP_ID") as String

// Prepare the content for sdk.properties
val sdkPropertiesContent = """
    artifactName=$artifactName
    version=$version
    groupId=$groupId
""".trimIndent()

// Configure the processResources task to include sdk.properties
tasks.named<ProcessResources>("processResources") {
    from(project.resources.text.fromString(sdkPropertiesContent)) {
        rename { "sdk.properties" }
    }
}
