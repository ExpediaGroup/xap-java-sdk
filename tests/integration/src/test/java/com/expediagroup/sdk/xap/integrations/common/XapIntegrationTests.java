package com.expediagroup.sdk.xap.integrations.common;

import static com.expediagroup.sdk.xap.integrations.common.Constant.MOCK_KEY;
import static com.expediagroup.sdk.xap.integrations.common.Constant.MOCK_SECRET;

import com.expediagroup.sdk.xap.client.XapClient;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;


/**
 * Extension for setting up the required components for testing.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class XapIntegrationTests {

  protected static XapClient xapClient;
  protected static XapClient mockClient;
  protected static MockWebServer mockWebServer;

  @BeforeAll
  static void setup() {
    String key = System.getProperty("com.expediagroup.xapjavasdk.apikey");
    String secret = System.getProperty("com.expediagroup.xapjavasdk.apisecret");
    if (StringUtils.isBlank(key) || StringUtils.isBlank(secret)) {
      throw new IllegalStateException("Key and secret must be set");
    }
    xapClient = XapClient.builder()
        .key(key)
        .secret(secret)
        .build();

    mockWebServer = new MockWebServer();

    mockClient = XapClient.builder()
        .key(MOCK_KEY)
        .secret(MOCK_SECRET)
        .endpoint(mockWebServer.url("/").toString())
        .build();
  }

  @AfterAll
  static void tearDown() throws Exception {
    // Clean everything after each test to ensure clear state
    if (mockWebServer != null) {
      mockWebServer.shutdown();
      mockWebServer = null;
    }
    xapClient = null;
    mockClient = null;
  }
}
