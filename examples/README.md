# XAP Java SDK Examples

This repository contains examples of how to use the Expedia Group XAP Java SDK. The examples are
written in Java and use Maven for dependency management.

## Examples

The example implementation provided in the examples package demonstrates different scenarios
utilizing various XAP APIs through the SDK.

Currently, the following scenarios are included:

### Lodging

#### Shopping

##### Listings

- [
  `QuickStartExample.java`](src/main/java/com/expediagroup/sdk/xap/examples/scenarios/lodging/shopping/listings/QuickStartExample.java):
  This example demonstrates how to search for properties with a location keyword with filters
  applied.

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
3. Set your API key and secret in the environment variables `XAP_API_KEY` and `XAP_API_PASSWORD`.
   > **Note:** You can also replace the value of `xapApiKey` and `xapApiPassword` in the
   > [`Constants.java`](src/main/java/com/expediagroup/sdk/xap/examples/Constants.java) file, but we
   > recommend using environment variables out of security concerns.
4. Run `mvn clean install` to build the project and install the dependencies including the XAP SDK.

## Running the Examples

To run the examples, simply navigate to the example class you want to run and execute the `main`
method.

## License

This project is licensed under the Apache License v2.0 - see the [LICENSE](../LICENSE) for details.