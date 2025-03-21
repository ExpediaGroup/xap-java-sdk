tasks.register("mergeSpecs") {
    exec {
        commandLine(
            "npx openapi-merge-cli".split(" ")
        )
        workingDir = File(rootDir, "specs")
    }
}

tasks.register("transformSpecs") {
    dependsOn("mergeSpecs")

    exec {
        commandLine(
            "npx --yes -p @expediagroup/spec-transformer cli --headers --operationIdsToTags -i specs.yaml -o specs.yaml"
        )
        workingDir = File(rootDir, "specs")
    }
}
