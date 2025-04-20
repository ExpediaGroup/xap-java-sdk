# XAP Java SDK Examples

This repository contains examples of how to use the Expedia Group XAP Java SDK. The examples are
written in Java and use Maven for dependency management.

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

- [
  `AvailabilityCalendarsQuickStartScenario.java`](src/main/java/com/expediagroup/sdk/xap/examples/scenarios/lodging/AvailabilityCalendarsQuickStartScenario.java):

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
- Maven

## Setup

1. Clone the repository.
2. Navigate to the project directory `examples`.
3. Run `mvn clean install` to build the project and install the dependencies including the XAP SDK.

## Running the Examples

### Run with IntelliJ IDEA
1. Navigate to the example class you want to run
2. Right-click on the class name and select `More Run/Debug` -> `Modify Run Configuration`
3. Check the `Modify options` -> `Add VM options` and add the following VM options:
    ```
    -Dcom.expediagroup.xapjavasdk.apikey="{API_KEY}"
    -Dcom.expediagroup.xapjavasdk.apisecret="{API_SECRET}"
    ```
    Replace `{API_KEY}` and `{API_SECRET}` with your actual API key and secret.
    > **Note:** If you are running the examples for Vrbo, you would need the following VM options instead:
    > ```
    > -Dcom.expediagroup.xapjavasdk.vrbokey="{VRBO_KEY}"
    > -Dcom.expediagroup.xapjavasdk.vrbosecret="{VRBO_SECRET}"
    > ```
    > The key you use must be enabled for Vrbo brand. If you are not sure, please reach out to your account manager.
4. Click `OK` and then run the `main` method of the example class.

### Run with Command Line
Run the following command to run the example class you want to run:
```
mvn exec:java -Dexec.mainClass="path.to.example.ExampleClassName" \
    -Dcom.expediagroup.xapjavasdk.apikey="{API_KEY}" \
    -Dcom.expediagroup.xapjavasdk.apisecret="{API_SECRET}"
```
Replace `path.to.example.ExampleClassName` with the full path to the example class you want to run,
and `{API_KEY}` and `{API_SECRET}` with your actual API key and secret.

> **Note:** If you are running the examples for Vrbo, you should use the following command instead:
> ```
> mvn exec:java -Dexec.mainClass="path.to.example.ExampleClassName" \
>     -Dcom.expediagroup.xapjavasdk.vrbokey="{VRBO_KEY}" \
>     -Dcom.expediagroup.xapjavasdk.vrbosecret="{VRBO_SECRET}"
> ```
> The key you use must be enabled for Vrbo brand. If you are not sure, please reach out to your account manager.

## License

This project is licensed under the Apache License v2.0 - see the [LICENSE](../LICENSE) for details.