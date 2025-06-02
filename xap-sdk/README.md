# xap-sdk

This is the primary XAP SDK module published to Maven Central. It includes generated operations and models, along with manually implemented classes that integrate the SDK with internal [Expedia Group SDK](https://github.com/ExpediaGroup/expediagroup-java-sdk/tree/feature/new-sdk-core) modules for request execution.

## Table of Contents

1. [Installation](#installation)  
2. [Quick Start](#quick-start)  
3. [XAP Asynchronous Client](#xap-asynchronous-client)
4. [Authenticate with OAuth2](#authenticate-with-oauth2)  
5. [Customize the SDK Transport](#customize-the-sdk-transport)
6. [Implement Your Own SDK Transport](#implement-your-own-sdk-transport)
7. [Logging](#logging)
   * [Use SLF4J Implementation](#use-sl4j-implementation)
8. [License](#license)


## Installation

Add **both** the SDK and a transport implementation to your build.

The examples below use the provided OkHttp transport(`expediagroup-sdk-transport-okhttp`).

<details>
<summary>Gradle <code>build.gradle</code></summary>

```groovy
dependencies {
    implementation 'com.expediagroup:xap-sdk:2.0.0-alpha'
    implementation 'com.expediagroup:expediagroup-sdk-transport-okhttp:0.0.5-alpha'
}
```
</details>

<details>
<summary>Maven <code>pom.xml</code></summary>

```xml
<dependency>
  <groupId>com.expediagroup</groupId>
  <artifactId>xap-sdk</artifactId>
  <version>2.0.0-alpha</version>
</dependency>

<dependency>
  <groupId>com.expediagroup</groupId>
  <artifactId>expediagroup-sdk-transport-okhttp</artifactId>
  <version>0.0.5-alpha</version>
</dependency>
```
</details>

> [!NOTE]
> XAP Java SDK requires Java 8 or higher

## Quick Start
The XAP Java SDK provides a set of executable operations for interacting with Expedia Group's XAP APIs. Below is a minimal setup to help you get started.

**1. Instantiate the client with your credentials**
```java
Credentials credentials = new BasicAuthCredentials("username", "password");
XapClientConfiguration config = new XapClientConfiguration(credentials);
XapClient client = new XapClient(config);
```

**2. Build the operation**
```java
GetLodgingAvailabilityCalendarsOperationParams params = GetLodgingAvailabilityCalendarsOperationParams
    .builder()
    .partnerTransactionId("xap-java-sdk-example")
    .propertyIds(new HashSet<>(Arrays.asList("87704892", "36960201")))
    .build();

GetLodgingAvailabilityCalendarsOperation operation = new GetLodgingAvailabilityCalendarsOperation(params);
```

**3. Execute the operation and get the data**
```java
Response<AvailabilityCalendarResponse> response = client.execute(operation);
AvailabilityCalendarResponse data = response.getData();
```

## XAP Asynchronous Client
If you prefer a non-blocking approach to executing operations, the XAP SDK provides an asynchronous client that returns responses as `CompletableFuture` objects. The process of building operations remains identical to the synchronous version.

```java
Credentials credentials = new BasicAuthCredentials("username", "password");
AsyncXapClientConfiguration config = new AsyncXapClientConfiguration(credentials);
AsyncXapClient asyncClient = new AsyncXapClient(credentials);

asyncXapClient.execute(operation).thenAccept(response -> {
    AvailabilityCalendarResponse data = response.getData();
});
```

## Authenticate with OAuth2
The examples above use `BasicAuthCredentials` to authenticate with the XAP APIs; however, the SDK also supports the OAuth2 authentication model. To enable it, simply use `XapOAuthCredentials` instead of `BasicAuthCredentials` when building your configuration.

```java
Credentials credentials = XapOAuthCredentials.builder()
    .key("your_api_key")
    .secret("your_api_secret")
    .partnerKey("your_partner_key")
    .build();

// Everything else remains the same...
```

## Customize the SDK Transport
The XAP SDK uses a pluggable `Transport` abstraction to handle HTTP communication. A `Transport` defines how requests are sent and responses are received, acting as a wrapper around the underlying HTTP client (e.g., OkHttp). This design allows you to swap or customize the HTTP layer without modifying the core SDK logic.

You can customize the default behavior by providing your own `Transport` configurations when constructing the SDK client.
`
For example, the `expediagroup-sdk-transport-okhttp` module provides a `Transport` implementation based on `OkHttp`

**1. Make sure you have the Transport dependency**

If you don't need to customize the default `Transport`, simply adding it as a dependency is sufficient. The XAP SDK will automatically detect and use the available `Transport` implementation in your project via Java’s service loader mechanism.

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
    <version>0.0.5-alpha</version>
</dependency>
<dependency>
    <groupId>com.expediagroup</groupId>
    <artifactId>xap-sdk</artifactId>
    <version>2.0.0-alpha</version>
</dependency>
```

**2. Customize and Configure the Transport**

Continuing with `expediagroup-sdk-transport-okhttp` as the example `Transport`, the following code demonstrates how to customize it and pass it to the client.

```java
/* Configure the OkHttp Client */
OkHttpClientConfiguration okHttpConfig = OkHttpClientConfiguration.builder()
    .callTimeout(100000)
    .connectTimeout(100000)
    .readTimeout(100000)
    .build();

/* Build new Transport with your configuration */
OkHttpTransport transport = new OkHttpTransport(okHttpConfig);

/* Pass the configured Transport instance to the configuration object */
XapClientConfiguration config = new XapClientConfiguration(credentials, transport);

/* Instantiate the client */
XapClient client = XapClient(config);
```

## Implement Your Own SDK Transport
In some cases, you may already have a configured HTTP client in your system and want to reuse it with the SDK for executing requests. The SDK makes this possible by allowing you to implement the `Transport` interface using any HTTP client of your choice, as long as your implementation accepts the SDK’s Request and returns a valid Response.

To do this, you'll need to map the SDK Request to the format expected by your HTTP client and convert the client’s response back into the SDK’s Response format.

```java
class MyTransport implements Transport {

    /* Your HTTP Client of choice */
    private final MyHttpClient myHttpClient;

    MyTransport(MyHttpClient myHttpClient) {
        this.myHttpClient =  myHttpClient;
    }

    @NotNull
    @Override
    public Response execute(@NotNull Request request) {
        /* 1. Map the SDK [request] to your HTTP client request format */
        MyHttpRequest myHttpRequest = MyHttpRequest.fromSdkRequest(request); // for demonstration only

        /* 2. Execute the request */
        MyHttpResponse myHttpResponse = myHttpClient.execute(myHttpRequest);

        /* 3. Map it back to SDK [Response] */
        // You would typically need to set headers, the response body, status code, etc.
        Response sdkResponse = Response.builder().build();

        /* Return the Response */
        return sdkResponse;
    }

    @Override
    public void dispose() {
        /* call the dispose method on your HTTP client if applicable */
    }
}
```

> [!TIP]
> You can implement the `AsyncTransport` interface if you want to use the asynchronous client.
> Just ensure that your HTTP client supports non-blocking calls and that responses are wrapped in `CompletableFuture`.

## Logging
The Expedia Group XAP SDK for Java does not impose a logging framework on clients, and instead supports logging via the SLF4J interface. SLF4J provides an abstraction for various logging frameworks, allowing clients to plug in their desired implementation when building their projects.

Without a logging framework plugged in, the SDK (SLF4J) defaults to a no-operation, discarding all log requests with a single warning message.

### Use SLF4J Implementation
Plug in a particular logging framework by declaring it as a project dependency. By design, SLF4J can use only one framework at a time, and it will emit a warning message if it finds multiple frameworks.

For example, to use the Simple Logger:

**Gradle**
```groovy
dependencies {
  implementation group: 'org.slf4j', name: 'slf4j-simple'
}
```

**Maven**
```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <!-- version -->
</dependency>
```

## License

This project is licensed under the Apache License v2.0 - see the [LICENSE](../LICENSE) for details.
