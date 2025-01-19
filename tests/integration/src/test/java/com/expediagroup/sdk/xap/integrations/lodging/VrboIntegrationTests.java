package com.expediagroup.sdk.xap.integrations.lodging;

import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.integrations.common.XapIT;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;

/**
 * Extension for setting up the required components for testing, specifically for Vrbo.
 */
public abstract class VrboIntegrationTests extends XapIntegrationTests {

  @BeforeAll
  static void vrboSetup() {
    String key = System.getProperty("com.expediagroup.xapjavasdk.vrbokey");
    String secret = System.getProperty("com.expediagroup.xapjavasdk.vrbosecret");
    if (StringUtils.isBlank(key) || StringUtils.isBlank(secret)) {
      throw new IllegalStateException("Key and secret must be set");
    }
    // replace the xapClient with the Vrbo client
    xapClient = XapClient.builder()
        .key(key)
        .secret(secret)
        .build();
  }

}
