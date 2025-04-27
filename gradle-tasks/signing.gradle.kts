extensions.configure<SigningExtension> {
    val signingKey = System.getenv("GPG_SECRET")
    val signingPassword = System.getenv("GPG_PASSPHRASE")

    useInMemoryPgpKeys(signingKey, signingPassword)

    val publishing = project.extensions.getByType<PublishingExtension>()
    sign(publishing.publications)
}
