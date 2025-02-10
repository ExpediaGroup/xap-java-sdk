package com.expediagroup.sdk.xap.integrations.common;

import static com.expediagroup.sdk.xap.integrations.common.Constant.MOCK_KEY;
import static com.expediagroup.sdk.xap.integrations.common.Constant.MOCK_SECRET;

import com.expediagroup.sdk.xap.client.XapClient;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;


/**
 * Extension for setting up the required components for testing.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class XapIntegrationTests {

  protected XapClient xapClient;
  protected MockWebServer mockWebServer;

  @BeforeEach
  void setup() {
    mockWebServer = new MockWebServer();

    xapClient = XapClient.builder()
        .key(MOCK_KEY)
        .secret(MOCK_SECRET)
        .endpoint(mockWebServer.url("/").toString())
        .build();
  }

  @AfterEach
  void tearDown() throws Exception {
    // Clean everything after each test to ensure clear state
    if (mockWebServer != null) {
      mockWebServer.shutdown();
      mockWebServer = null;
    }
    xapClient = null;
  }
}
