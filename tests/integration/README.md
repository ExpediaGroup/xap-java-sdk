# XAP Java SDK Integrations

This repository contains integration tests of the Expedia Group XAP Java SDK.

_**The following instructions apply if you want to run the integration tests locally.**_

## Requirements

- Ensure you have a valid API key and secret from Expedia Group.
  Check [Getting started with XAP](https://developers.expediagroup.com/xap/products/xap/set-up/getting-started)
  for more info.
- Java 1.8 or higher
- Maven

## Setup

1. Clone the repository.
2. Navigate to the project directory `integrations`.
3. Run `mvn clean install` to build the project and install the dependencies including the XAP SDK.

## Running the Integration Tests

### Run with IntelliJ IDEA

1. Navigate to the test class you want to run
2. Right-click on the class name and select `More Run/Debug` -> `Modify Run Configuration`
3. Check the `Modify options` -> `Add VM options` and add the following VM options:
    ```
    -Dcom.expediagroup.xapjavasdk.apikey="{API_KEY}"
    -Dcom.expediagroup.xapjavasdk.apisecret="{API_SECRET}"
    ```
   Replace `{API_KEY}` and `{API_SECRET}` with your actual API key and secret.
   > **Note:** If you are running the tests for Vrbo, you would need the following VM options
   instead:
   > ```
    > -Dcom.expediagroup.xapjavasdk.vrbokey="{VRBO_KEY}"
    > -Dcom.expediagroup.xapjavasdk.vrbosecret="{VRBO_SECRET}"
    > ```
   > The key you use must be enabled for Vrbo brand. If you are not sure, please reach out to your
   account manager.
4. Click `OK` and then run the test class.

### Run with Command Line

Run the following command to run all integration tests:

```
mvn failsafe:integration-test \
 -Dcom.expediagroup.xapjavasdk.apikey="{API_KEY}" \
 -Dcom.expediagroup.xapjavasdk.apisecret="{API_SECRET}" \
 -Dcom.expediagroup.xapjavasdk.vrbokey="{VRBO_KEY}" \
 -Dcom.expediagroup.xapjavasdk.vrbosecret="{VRBO_SECRET}" \
 -f pom.xml
```

If your key is enabled for both Expedia and Vrbo brands, you may use the same key and secret
pair for the placeholders. Otherwise, you may run the tests separately by providing only the
required key and secret. For example:

```
mvn failsafe:integration-test \
 -Dit.test=TestClassName#testMethodName \
 -Dcom.expediagroup.xapjavasdk.apikey="{API_KEY}" \
 -Dcom.expediagroup.xapjavasdk.apisecret="{API_SECRET}" \
 -f pom.xml
```

Replace `TestClassName` and `testMethodName` with the actual test class name and method name,
and `{API_KEY}` and `{API_SECRET}` with your actual API key and secret.
> If `#testMethodName` is omitted, all test methods in the class will be executed.

If you are running the tests for Vrbo, you should use the following command instead:

```
mvn failsafe:integration-test \
 -Dit.test=TestClassName#testMethodName \
 -Dcom.expediagroup.xapjavasdk.vrbokey="{VRBO_KEY}" \
 -Dcom.expediagroup.xapjavasdk.vrbosecret="{VRBO_SECRET}"
 -f pom.xml
```

The key you use must be enabled for Vrbo brand. If you are not sure, please reach out to your
account manager.

## License

This project is licensed under the Apache License v2.0 - see the [LICENSE](../../LICENSE) for details.
