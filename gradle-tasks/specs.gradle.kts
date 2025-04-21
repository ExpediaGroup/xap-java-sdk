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
            "npx --yes -p @expediagroup/spec-transformer cli --headers --operationIdsToTags --input specs.yaml --output specs.yaml"
                .split(" ")
        )
        workingDir = File(rootDir, "specs")
    }
}

tasks.register("prepareSpecs") {
    dependsOn("mergeSpecs", "transformSpecs")

    doLast {
        File("$rootDir", "specs/specs.yaml").copyTo(
            File("$rootDir", "generator/src/main/resources/specs-copied.yaml"),
        )
    }
}
