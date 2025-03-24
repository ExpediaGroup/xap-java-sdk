# Overview
The XAP (Expedia API Platform) SDK is structured as a multi-module Gradle project designed to generate a Kotlin/Java SDK from OpenAPI specifications. The architecture follows a code generation approach, where API client code is automatically generated and then published to repositories for consumption by developers.

The repository contains the following key components:
- **OpenAPI Specifications**: Defines the API endpoints, request/response models, and data types.
- **Code Generation**: Utilizes OpenAPI Generator to create the SDK code based on the specifications.
- **Core Integration**: Integrates with the Expedia Group's SDK platform core libraries and frameworks.
- **Post-Processing**: Enhances the generated code with additional features, optimizations, and customizations.
- **Publishing**: Automates the release process to make the SDK available for developers.
- **Examples**: Provides sample applications demonstrating how to use the generated SDK.
- **Documentation**: Contains guides and references for developers to understand and utilize the SDK effectively.
- **Testing**: Includes unit, integration and end-to-end tests to ensure the correctness and reliability of the generated SDK.

# Technology Stack
- **Languages**: Kotlin (primary), Java (compatibility)
- **Build System**: Gradle with Kotlin DSL
- **Code Generation**: OpenAPI Generator
- **Post-Processing**: TypeScript/JavaScript (Node.js)
- **CI/CD**: GitHub Actions
- **Testing Frameworks**: JUnit, MockK, AssertJ
- **Documentation**: Markdown, KDoc
- **Version Control**: Git
- **Package Management**: Maven Central, Sonatype Nexus
- **Dependency Management**: Gradle Kotlin DSL
- **Code Quality**: Ktlint
- **Code Coverage**: Kover

# Modules
The project is organized into several modules, each serving a specific purpose. The main modules include:

## `generator` module
This module is responsible for the core functionality of the SDK generation process. It consumes the OpenAPI specifications and generates the initial SDK code using OpenAPI Generator. It also integrates with the `expediagroup-sdk-openapi-plugin`. The module contains the following components:
- **OpenAPI Generator Configurations**: The core library that generates the SDK code from OpenAPI specifications. Lives in the `build.gradle.kts` file.
- **Custom Templates**: Custom templates for generating Kotlin/Java code. Lives in the `src/main/resources/templates` directory.
- **Post-Processing**: Enhancements and optimizations applied to the generated code. Lives in the `src/main/resources/post-processing` directory.

The generation process can be executed through the `openApiGenerate` Gradle task, which triggers the OpenAPI Generator with the specified configurations. The `openApiGenerate` gradle task workflow is as follows:
1. Locates the OpenAPI specification file through the `inputSpec` environment variable, falling back to `generator/src/main/resources/specs.yaml` if not found.
2. Consumes the OpenAPI specification file to generates code into the `xap-sdk/src/main/kotlin` directory.
3. Generated code overrides the code present `xap-sdk/src/main/kotlin` directory, affecting only the `operations` and `models` packages.
4. The generated code is then post-processed to apply additional customizations and optimizations.
5. Finally, the code is formatted and linted using Ktlint to ensure code quality and consistency.
