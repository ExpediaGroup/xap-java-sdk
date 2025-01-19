package com.expediagroup.sdk.xap.integrations.common;

import java.util.Base64;
import java.util.UUID;

/**
 * Constants used in the integration tests.
 */
public class Constant {
  public static final String PARTNER_TRANSACTION_ID = "xap-sdk-integration-test";
  public static final String MOCK_KEY = UUID.randomUUID().toString();
  public static final String MOCK_SECRET = "mock";
  public static final String AUTHORIZATION = "Basic " + Base64.getEncoder()
      .encodeToString((MOCK_KEY + ":" + MOCK_SECRET).getBytes());
  public static final String ACCEPT_HOTEL = "application/vnd.exp-hotel.v3+json";
  public static final String ACCEPT_LODGING = "application/vnd.exp-lodging.v3+json";
}
