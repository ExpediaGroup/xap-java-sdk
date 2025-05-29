# Expedia Group XAP Java SDK – Example Scenarios

![Java](https://img.shields.io/badge/Java-8%2B-brightgreen)
![Gradle](https://img.shields.io/badge/Build-Gradle-blueviolet)
![License](https://img.shields.io/badge/License-Apache%202.0-yellowgreen)

*A curated set of examples that show how to call the Expedia Group **XAP** APIs with the official **Java SDK**.*

---

## 📚 Table of Contents

1. [Requirements](#requirements)
2. [Scenario Catalogue](#scenario-catalogue)
3. [Running an Example](#running-an-example)
    * [IntelliJ IDEA](#intellij-idea)
    * [Command Line (Gradle)](#command-line-gradle)
    * [Authentication Options](#authentication-options)
4. [Run **all** scenarios](#run-all-scenarios)
5. [License](#license)

---

## Requirements

| Tool      | Version                                    |
|-----------|--------------------------------------------|
| **Java**  |  > 1.8                                     |
| **Gradle** | Wrapper included – no local install needed |
| **XAP Keys** | Contact your Expedia Group account manager |

---

## Scenario Catalogue

Below is a living catalogue of example scenarios.  We are continually adding more – pull requests welcome!

### Lodging

| Scenario                            | Source File                                                                                                                                                      | What it demonstrates                                                                                            |
|-------------------------------------| ---------------------------------------------------------------------------------------------------------------------------------------------------------------- |-----------------------------------------------------------------------------------------------------------------|
| Listings *Quick Start*              | [`ListingsQuickStartScenario.java`](src/main/java/com/expediagroup/sdk/xap/examples/scenarios/lodging/ListingsQuickStartScenario.java)                           | Search for properties by location keyword with basic filters (Lodging Listings API).                            |
| Hotel Ids *End‑to‑End*              | [`HotelIdsSearchEndToEndScenario.java`](src/main/java/com/expediagroup/sdk/xap/examples/scenarios/lodging/ListingsHotelIdsSearchScenario.java)                   | Download accessible property IDs (SDP DownloadURL API) and fetch their content & prices (Lodging Listings API). |
| Quotes *Quick Start*                | [`QuotesQuickStartScenario.java`](src/main/java/com/expediagroup/sdk/xap/examples/scenarios/lodging/QuotesQuickStartScenario.java)                               | Retrieve price quotes for a list of property IDs (Lodging Quotes API).                                          |
| Vrbo Property *End‑to‑End*          | [`VrboPropertySearchEndToEndScenario.java`](src/main/java/com/expediagroup/sdk/xap/examples/scenarios/lodging/VrboPropertySearchEndToEndScenario.java)           | Obtain Vrbo property IDs + location content (SDP DownloadURL), then price them (Lodging Quotes API).            |
| Availability Calendar *Quick Start* | [`AvailabilityCalendarsQuickStartScenario.java`](src/main/java/com/expediagroup/sdk/xap/examples/scenarios/lodging/AvailabilityCalendarsQuickStartScenario.java) | Simple availability calendar request. (Property IDs obtained in the *Quotes Quick Start*).                      |

### Car

| Scenario               | Source File                                                                                                                              | What it demonstrates                                                  |
|------------------------| ---------------------------------------------------------------------------------------------------------------------------------------- |-----------------------------------------------------------------------|
| Listings *Quick Start* | [`CarListingsQuickStartScenario.java`](src/main/java/com/expediagroup/sdk/xap/examples/scenarios/car/CarListingsQuickStartScenario.java) | Search car offers for an airport, with filters (Car Listings API).    |
| Details *Quick Start*  | [`CarDetailsQuickStartScenario.java`](src/main/java/com/expediagroup/sdk/xap/examples/scenarios/car/CarDetailsQuickStartScenario.java)   | Retrieve detailed offer data using an `offerToken` (Car Details API). |

---

## Running an Example

### IntelliJ IDEA

<details>
<summary>Step‑by‑step (click to expand)</summary>

1. **Open** the project in IntelliJ.
2. In the **Project** tool‑window, locate the scenario class you want to run.
3. *Right‑click* ➜ **Modify Run Configuration…**
   Enable **VM options** and paste:

   ```text
   -Dxapkey="${API_KEY}"
   -Dxapsecret="${API_SECRET}"
   ```

   Replace the placeholders with your real credentials. <br/>
   **Vrbo brand?** Use:

   ```text
   -Dvrbokey="${VRBO_KEY}"
   -Dvrbosecret="${VRBO_SECRET}"
   ```
4. Hit **Run** ▶︎ on the `main` method.

</details>

### Command‑Line (Gradle)

```bash
./gradlew :examples:run \
    -Pscenario="path.to.example.Scenario" \
    -Pxapkey="${API_KEY}" \
    -Pxapsecret="${API_SECRET}"
```

For **Vrbo** credentials:

```bash
./gradlew :examples:run \
    -Pscenario="path.to.example.Scenario" \
    -Pvrbokey="${VRBO_KEY}" \
    -Pvrbosecret="${VRBO_SECRET}"
```

### Authentication Options

We support **Basic** (default) **and OAuth 2.0**:

```bash
# OAuth 2.0 – CLI example
./gradlew :examples:run \
    -Pscenario="path.to.example.Scenario" \
    -Pxapkey="${API_KEY}" \
    -Pxapsecret="${API_SECRET}" \
    -Ppartnerkey="${PARTNER_KEY}" \
    -Pauthtype="oauth"
```

> ℹ️`${PARTNER_KEY}` is provided during onboarding. Talk to your account manager if unsure.

---

## Run **all** scenarios

```bash
# Basic auth
./gradlew :examples:run \
    -Pscenario="com.expediagroup.sdk.xap.examples.XapSdkDemoTestRun" \
    -Pxapkey="${API_KEY}" \
    -Pxapsecret="${API_SECRET}" \
    -Pvrbokey="${VRBO_KEY}" \
    -Pvrbosecret="${VRBO_SECRET}"

# OAuth 2.0
./gradlew :examples:run \
    -Pscenario="com.expediagroup.sdk.xap.examples.XapSdkDemoTestRun" \
    -Pxapkey="${API_KEY}" \
    -Pxapsecret="${API_SECRET}" \
    -Pvrbokey="${VRBO_KEY}" \
    -Pvrbosecret="${VRBO_SECRET}" \
    -Ppartnerkey="${PARTNER_KEY}" \
    -Pauthtype="oauth"
```

---

## License

Distributed under the **Apache 2.0** license.
See [`LICENSE`](../LICENSE) for the full text.
