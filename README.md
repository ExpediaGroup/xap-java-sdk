# Expedia Group XAP SDK for Java
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Java Version](https://img.shields.io/badge/java-8%2B-orange)](https://www.oracle.com/java/technologies/javase-downloads.html)

Build a branded travel experience with the Expedia Web Services XAP APIs. Choose from an extensive selection of hotels,
vacation rentals, flights, cars, activities, and packages to elevate your traveler’s journey.

You can read more about the XAP API on [Developer Hub for XAP](https://developers.expediagroup.com/xap).

---

## XAP SDK

The XAP SDK makes integrating simple, saving development time so you can focus more on getting your product to market
and less on the technical details of the API.

### Installation
Make sure you have **Java 8** or higher.

**Gradle**
```groovy
dependencies {
    implementation 'com.expediagroup:expediagroup-sdk-transport-okhttp:0.0.5-alpha'
    implementation 'com.expediagroup:xap-sdk:2.0.0-alpha'
}
```

**Maven**
```xml
<dependency>
    <groupId>com.expediagroup</groupId>
    <artifactId>expediagroup-sdk-transport</artifactId>
    <version>0.0.4-alpha</version>
</dependency>
<dependency>
    <groupId>com.expediagroup</groupId>
    <artifactId>xap-sdk</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## SDK Usage and Configuration
See the [xap-sdk](xap-sdk) module for a Quick Start guide, OAuth2 configuration, asynchronous usage, and details on customizing the transport layer.

## Code Generation
To learn how SDK code is generated from OpenAPI specifications, refer to the [generator](generator) module.

## Code Examples
You can find code examples for multiple use cases in the [examples](examples) directory.

## Integration Tests
Integration tests are placed in the [integrations](integration-tests) directory.

---

## Support

If you have any questions or need help with XAP, please refer to the
[XAP Support](https://developers.expediagroup.com/xap/support) page.

If you experience any issues with the SDK, please raise an issue on the
[GitHub repository](https://github.com/ExpediaGroup/xap-java-sdk/issues).

## License
This project is licensed under the [Apache License 2.0](LICENSE).
