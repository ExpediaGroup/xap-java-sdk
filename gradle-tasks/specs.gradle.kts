tasks.register<Exec>("mergeSpecs") {
    commandLine("npx", "openapi-merge-cli")
    workingDir = File(rootDir, "specs")
}

tasks.register("transformSpecs") {
    dependsOn("mergeSpecs")

    exec {
        commandLine(
            "npx --yes -p @expediagroup/spec-transformer cli --headers --operationIdsToTags -i specs.yaml -o specs.yaml"
                .split(" ")
        )
        workingDir = File(rootDir, "specs")
    }
}

tasks.register("prepareSpecs") {
    dependsOn("mergeSpecs", "transformSpecs")

    doLast {
        File("$rootDir", "specs/specs.yaml").copyTo(
            File("$rootDir", "generator/src/main/resources/transformedSpecs.yaml"),
        )
    }
}
