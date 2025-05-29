# XAP Java SDK Examples

This repository contains examples of how to use the Expedia Group XAP Java SDK. The examples are
written in Java and use Gradle for dependency management.

## Examples

The example implementation provided in the examples package demonstrates different scenarios
utilizing various XAP APIs through the SDK.

Currently, the following scenarios are included:

### Lodging

- [`ListingsQuickStartScenario.java`](src/main/java/com/expediagroup/sdk/xap/examples/scenarios/lodging/ListingsQuickStartScenario.java):

  This example demonstrates how to search for properties with a location keyword with filters
  applied in Lodging Listings API.

- [`HotelIdsSearchEndToEndScenario.java`](src/main/java/com/expediagroup/sdk/xap/examples/scenarios/lodging/ListingsHotelIdsSearchScenario.java):

  This example demonstrates how to retrieve accessible property ids from SDP DownloadURL API and
  then get the content and prices of these properties using the Lodging Listings API.

- [`QuotesQuickStartScenario.java`](src/main/java/com/expediagroup/sdk/xap/examples/scenarios/lodging/QuotesQuickStartScenario.java):

  This example demonstrates how to search for property quotes with property IDs in
  Lodging Quotes API.

- [`VrboPropertySearchEndToEndScenario.java`](src/main/java/com/expediagroup/sdk/xap/examples/scenarios/lodging/VrboPropertySearchEndToEndScenario.java):

  This example demonstrates how to retrieve accessible Vrbo property ids and location content from
  SDP DownloadURL API and then get the prices of these properties using the Lodging Quotes API.

- [`AvailabilityCalendarsQuickStartScenario.java`](src/main/java/com/expediagroup/sdk/xap/examples/scenarios/lodging/AvailabilityCalendarsQuickStartScenario.java):

  This example demonstrates how to use Availability Calendar api with simple search.
  In terms of how to get property ids, you can refer to `QuotesQuickStartScenario.java`.

### Car

- [`CarDetailsQuickStartScenario.java`](src/main/java/com/expediagroup/sdk/xap/examples/scenarios/car/CarListingsQuickStartScenario.java):

  This example demonstrates how to search for cars using an airport keyword with filters applied in the Car Listings API.

- [`CarDetailsQuickStartScenario.java`](src/main/java/com/expediagroup/sdk/xap/examples/scenarios/car/CarDetailsQuickStartScenario.java):

  This example demonstrates how to search for car details using the offerToken obtained from the car listing in the Car Details API.

We are continuously adding more scenarios to demonstrate the usage of other XAP APIs.

## Requirements

- Ensure you have a valid API key and secret from Expedia Group.
  Check [Getting started with XAP](https://developers.expediagroup.com/xap/products/xap/set-up/getting-started)
  for more info.
- Java 1.8 or higher

## Run Examples with IntelliJ IDEA
1. Navigate to the example class you want to run
2. Right-click on the class name and select `More Run/Debug` -> `Modify Run Configuration`
3. Check the `Modify options` -> `Add VM options` and add the following VM options:
    ```
    -Dxapkey="{API_KEY}"
    -Dxapsecret="{API_SECRET}"
    ```
    Replace `{API_KEY}` and `{API_SECRET}` with your actual API key and secret.
    > **Note:** If you are running the examples for Vrbo, you would need the following VM options instead:
    > ```
    > -Dvrbokey="{VRBO_KEY}"
    > -Dvrbosecret="{VRBO_SECRET}"
    > ```
    > The key you use must be enabled for Vrbo brand. If you are not sure, please reach out to your account manager.
4. Click `OK` and then run the `main` method of the example class.

## Run Examples with Command Line
Run the following command to run the example class you want to run:
```
./gradlew :examples:run -Pscenario="path.to.example.Scenario" \
    -Pxapkey="{API_KEY}" \
    -Pxapsecret="{API_SECRET}"
```
Replace `path.to.example.Scenario` with the full path to the scenario class you want to run,
and `{API_KEY}` and `{API_SECRET}` with your actual API key and secret.

> **Note:** If you are running the examples for Vrbo, you should use the following command instead:
> ```
> ./gradlew :examples:run -Pscenario="path.to.example.Scenario" \
>     -Pvrbokey="{VRBO_KEY}" \
>     -Pvrbosecret="{VRBO_SECRET}"
> ```
> The key you use must be enabled for Vrbo brand. If you are not sure, please reach out to your account manager.

## Enable OAuth2 in Examples
If you have a valid OAuth key and secret, you can run the examples with OAuth2 authentication.

- `{API_KEY}` - Your **OAuth** API key.
- `{API_SECRET}` - Your **OAuth** API secret.
- `{PARTNER_KEY}` - Your partner key. **Partner API Key** is assigned and configured as part of the API onboarding process

Then you can enable OAuth2 by passing some additional parameters (note the params definitions above):

#### Command Line

```
./gradlew :examples:run -Pscenario="path.to.example.Scenario" \
    -Pxapkey="{API_KEY}" \
    -Pxapsecret="{API_SECRET}" \
    -Ppartnerkey="{PARTNER_KEY}" \
    -Pauthtype="oauth"
```

#### IntelliJ IDEA
Follow the same steps as above to modify the run configuration, but add the following additional VM options:

```
-Dpartnerkey="{PARTNER_KEY}"
-Dauthtype="oauth"
```

## Run All Scenarios
To run all scenarios, you can use the following command:

#### With Basic Authentication

```
./gradlew :examples:run -Pscenario="com.expediagroup.sdk.xap.examples.XapSdkDemoTestRun" \
    -Pxapkey="{API_KEY}" \
    -Pxapsecret="{API_SECRET}" \
    -Pvrbokey="{VRBO_KEY}" \
    -Pvrbosecret="{VRBO_SECRET}"
```

#### With OAuth2

```
./gradlew :examples:run -Pscenario="com.expediagroup.sdk.xap.examples.XapSdkDemoTestRun" \
    -Pxapkey="{API_KEY}" \
    -Pxapsecret="{API_SECRET}" \
    -Pvrbokey="{VRBO_KEY}" \
    -Pvrbosecret="{VRBO_SECRET}" \
    -Ppartnerkey="{PARTNER_KEY}" \
    -Pauthtype="oauth"
```

## License

This project is licensed under the Apache License v2.0 - see the [LICENSE](../LICENSE) for details.
