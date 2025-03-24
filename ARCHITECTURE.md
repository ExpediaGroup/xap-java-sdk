# Overview

The XAP (Expedia API Platform) SDK is structured as a multi-module Gradle project designed to generate a Kotlin/Java SDK
from OpenAPI specifications. The architecture follows a code generation approach, where API client code is automatically
generated and then published to repositories for consumption by developers.

The repository contains the following key components:

| Component                       | Description                                                                                                     |
|---------------------------------|-----------------------------------------------------------------------------------------------------------------|
| **OpenAPI Specifications**      | Defines the API endpoints, request/response models, and data types.                                             |
| **Code Generation**             | Utilizes OpenAPI Generator to create the SDK code based on the specifications.                                  |
| **Core Integration**            | Integrates with the Expedia Group's SDK platform core libraries and frameworks.                                 |
| **Post-Processing**             | Enhances the generated code with additional features, optimizations, and customizations.                        |
| **Publishing Tasks**            | Automates the release process to make the SDK available for developers.                                         |
| **Examples**                    | Provides sample applications demonstrating how to use the generated SDK.                                        |
| **Documentation**               | Contains guides and references for developers to understand and utilize the SDK effectively.                    |
| **Testing**                     | Includes unit, integration and end-to-end tests to ensure the correctness and reliability of the generated SDK. |
| **OpenAPI Specification Files** | OpenAPI definitions. Located at `specs` directory in the root of the repository.                                |
| **Gradle Helper Tasks**         | Gradle tasks that automate common tasks.                                                                        |

# Technology Stack

| Capability                | Technology                                                                                                                                      |
|---------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------|
| **Build System**          | [Gradle](https://docs.gradle.org/current/userguide/userguide.html) with [Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) |
| **Code Generation**       | [OpenAPI Generator](https://openapi-generator.tech/)                                                                                            |
| **CI/CD**                 | GitHub Actions                                                                                                                                  |
| **Testing**               | [JUnit5](https://junit.org/junit5/)                                                                                                             |
| **Version Control**       | Git                                                                                                                                             |
| **Code Quality**          | [Kover](https://kotlin.github.io/kotlinx-kover/gradle-plugin/)                                                                                  |
| **Programming Languages** | Kotlin (JVM)                                                                                                                                    |
| **Documentation**         | [KDoc](https://kotlinlang.org/docs/kotlin-doc.html), [Dokka](https://kotlinlang.org/docs/dokka-introduction.html)                               |
| **Target**                | [Java (LTS)](https://www.oracle.com/middleeast/java/technologies/java-se-support-roadmap.html)                                                  |

# Modules

The project is organized into several modules, each serving a specific purpose. The main modules include:

## Root Module

The root module serves as the entry point for the project. It contains the overall project configuration, including
dependency management, build scripts, and common configurations shared across all modules. This module also includes the
`settings.gradle.kts` file, which defines the structure of the multi-module project.
The module defines custom gradle tasks that live in the `gradle-tasks` directory. The defined tasks handle OpenAPI
specification files pre-processing, publishing, as well as for generating and publishing the SDK to a remote repository.
It also includes configurations for code quality checks, such as Ktlint and Kover. All tasks are defined using kotlin
DSL.

| Defined Gradle Task | Description                                                                                                                                                                                                                                                           |
|---------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `mergeSpecs`        | Executes the openapi-merge-cli command using npx to merge OpenAPI specs.                                                                                                                                                                                              |
| `transformSpecs`    | Executes the spec-transformer CLI command using npx to transform the OpenAPI specs.                                                                                                                                                                                   |
| `prepareSpecs`      | A wrapper for the sequential execution of `mergeSpecs` and `transformSpecs`. Generates and copies a final specs.yaml file to the generator module's resources directory                                                                                               |
| `publishSnapshot`   | Triggers the execution of the `publish` task. It verifies the artifact has a valid snapshot version suffix, ensures the publish task exists, executes the publish task, and outputs confirmation messages with the repository URL where the artifact can be accessed. |

## Generator Module

The `generator` module is responsible for the core functionality of the SDK generation process. It consumes the OpenAPI
specifications and generates the initial SDK code using OpenAPI Generator. It also integrates with the
`expediagroup-sdk-openapi-plugin`.

| Generator Component/Asset            | Description                                                                                                                |
|--------------------------------------|----------------------------------------------------------------------------------------------------------------------------|
| **OpenAPI Generator Configurations** | The core library that generates the SDK code from OpenAPI specifications. Lives in the `build.gradle.kts` file.            |
| **Custom Templates**                 | Custom templates for generating Kotlin/Java code. Lives in the `src/main/resources/templates` directory.                   |
| **Post-Processing**                  | Enhancements and optimizations applied to the generated code. Lives in the `src/main/resources/post-processing` directory. |

The generation process can be executed through the `openApiGenerate` Gradle task, which triggers the OpenAPI Generator
with the specified configurations.

```shell
gradle clean build

cd generator
gradle openApiGenerate --stacktrace
```

The `openApiGenerate` gradle task workflow is as follows:

1. Locates the OpenAPI specification file through the `inputSpec` environment variable, falling back to
   `generator/src/main/resources/specs.yaml` if not found.
2. Consumes the OpenAPI specification file to generates code into the `xap-sdk/src/main/kotlin` directory.
3. Generated code overrides the code present `xap-sdk/src/main/kotlin` directory, affecting only the `operations` and
   `models` packages.
4. The generated code is then post-processed to apply additional customizations and optimizations.
5. Finally, the code is formatted and linted using Ktlint to ensure code quality and consistency.

## Build-Logic Module

The `buildSrc` module is
a [Gradle build logic module](https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html#sec:using_buildsrc)
that contains custom Gradle plugins and shared build logic used across the project. It allows for better organization
and reusability of build scripts and configurations.

The `buildSrc` module is used to define OpenAPI Generator customizations, such as custom mustache lambdas. This module
can be used to define custom Gradle tasks, plugins, and other build-related logic that can be reused across the project.
It is automatically included in the build process and can be used to extend the functionality of Gradle.

## XAP SDK Module

The `xap-sdk` module is the main module that contains the generated SDK code. It includes the generated API client code,
along with SDK specific configurations and SDK core libraries integrations. The module is structured as follows:

| Package         | Generated | Description                                                                                        |
|-----------------|-----------|----------------------------------------------------------------------------------------------------|
| `core`          | **No**    | SDK core libraries integrations. Defines the workflow for sync and async operations execution.     |
| `configuration` | **No**    | SDK specific configurations such as endpoints, jackson configurations and others.                  |
| `client`        | **No**    | API clients that are responsible for executing operations.                                         |
| `operations`    | **YES**   | Representations of available API requests. Generated based on the OpenAPI spec defined operations. |
| `models`        | **YES**   | Representation of API models. Generated based on the OpenAPI spec defined schemas                  |
