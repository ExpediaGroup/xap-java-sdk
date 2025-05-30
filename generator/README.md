# XAP Java SDK Generator
The Generator module is a core component of the XAP Java SDK project, responsible for automatically generating SDK code from OpenAPI specifications.
It leverages the [Expedia Group SDK Generator](https://github.com/ExpediaGroup/expediagroup-java-sdk/tree/feature/new-sdk-core/expediagroup-sdk-openapi-plugin) to transform API specifications into type-safe, consistent, and well-documented client code.

The generator is customized to generate **models** and **operations** for the SDK based on the provided OpenAPI spec, while the internal requests execution logic is handled by other [Expedia Group SDK](https://github.com/ExpediaGroup/expediagroup-java-sdk/tree/feature/new-sdk-core) core modules.

---

## 📚 Table of Contents

1. [EG SDK Generator Plugin Configuration](#EG-SDK-Generator-Plugin-Configuration)
2. [Key Tasks](#Key-Tasks)
3. [Generation Workflow](#Generation-Workflow)
4. [Custom Templates](#Custom-Templates)
   1. [Override Default Templates](#override-default-templates)
   2. [Add New Templates](#add-new-templates)
      1. [API Templates](#1-api-templates)
      2. [Supporting Templates](#2-supporting-templates)
5. [Output Directory](#output-directory)
6. [License](#license)

---

## EG SDK Generator Plugin Configuration

The generator plugin configurations are defined in `build.gradle.kts` with the following base settings:
- **namespace**: `xap`
- **basePackage**: `com.expediagroup.sdk.xap`
- **specFilePath**: Path to the XAP merged & transformed OpenAPI spec
- **outputDir**: Directory where generated code will be placed
- **customTemplatesDir**: Directory containing XAP custom Mustache templates
- **objectMapper**: `com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER` Points to the SDK's Jackson ObjectMapper used for serialization/deserialization

## Key Tasks
The module provides several Gradle tasks to facilitate the code generation process:

| Task                | Description                                                                |
|---------------------|----------------------------------------------------------------------------|
| `mergeSpecs`        | Merges multiple OpenAPI spec files into a single `specs.yaml` file         |
| `transformSpecs`    | Transforms the merged spec file with Expedia Group-specific customizations |
| `generateEgSdk`     | Generates the SDK code from the transformed spec file                      |
| `generateAndFormat` | Generates SDK code and formats it with ktlint                              |

## Generation Workflow
XAP Java SDK is generated from a collection of OpenAPI specifications that are merged and transformed into a single spec file.
The final transformed spec file is then fed to the SDK generator. A typical workflow to generate new models and operations involves the following steps:

1. Add or update OpenAPI specifications in the `specs` directory
    - If the spec is a new, it should be added to the `specs/openapi-merge.json` file.
2. Run the Specs merge task to create a merged OpenAPI spec file:
    ```bash
    ./gradlew :generator:mergeSpecs
    ```
   This task merges all OpenAPI spec files into a single `specs.yaml` file.
3. Run the Specs transformation task to apply Expedia Group-specific customizations:
    ```bash
    ./gradlew :generator:transformSpecs
    ```
   This task transforms the merged spec file into a single file `transformed-spec.yaml` suitable for the SDK generator.
4. Run the SDK generation task to generate the SDK code:
    ```bash
    ./gradlew :generator:generateAndFormat
    ```
   This task generates the SDK code based on the transformed spec file and places it in the specified output directory.

## Custom Templates
Expedia Group XAP SDK generator allows users to define custom Mustache templates for generating code. The custom templates
may override the default templates provided by the generator plugin or add new templates for specific use cases.

### Override Default Templates
To override the default templates, the custom templates should match the name and location of the default templates in the generator plugin.
You can find the generator's default templates [here](https://github.com/ExpediaGroup/expediagroup-java-sdk/tree/feature/new-sdk-core/expediagroup-sdk-openapi-plugin/src/main/resources/templates)

As a living example, XAP SDK overrides the [`operations/params/builder.mustache`](src/main/resources/templates/operations/params/builder.mustache) template.

The generator plugin will pick up the custom templates automatically without any additional configuration needed.

### Add New Templates
The OpenAPI generator allows adding new templates for specific use cases. There are two types of templates that can be added:

#### 1. API Templates
Templates that the generator invokes for each API operation.

```kts
egSdkGenerator {
    apiTemplates = listOf(
        ApiTemplate(
            // Template file name - relative to the customTemplatesDir
            templateFile = "my-custom-api-template.mustache",

            // output destination - relative to the outputDir
            destinationPath = "com/expediagroup/sdk/xap/operation/custom",

            // Optional suffix appended to the generated file name. The base name is the operation name.
            fileNameSuffix = "CustomOperation.kt",
        ),
    )
}
```

#### 2. Supporting Templates
Templates that the generator invokes once to generate the supporting code for the SDK.

```kts
egSdkGenerator {
    supportingTemplates = listOf(
        SupportingTemplate(
            // Template file name - relative to the customTemplatesDir
            templateFile = "room.mustache",

            // output destination - relative to the outputDir
            destinationPath = "com/expediagroup/sdk/xap/model",

            // The name of the generated file
            fileName = "Room.kt",
        ),
    )
}
```

## Output Directory
The generator produces code that is integrated into the main `xap-sdk` module (`src/main/kotlin`).


## License
This module is part of the **XAP Java SDK** and distributed under the [Apache 2.0](../LICENSE) license.
