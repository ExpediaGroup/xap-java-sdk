package com.expediagroup.sdk.xap.examples.scenarios;

import com.expediagroup.sdk.xap.client.XapClient;

/**
 * Interface for scenarios.
 */
public interface XapScenario {

    String PARTNER_TRANSACTION_ID = "xap-java-sdk-examples";

    void run();

    /**
     * Create a client.
     *
     * @return XapClient
     */
    default XapClient createClient() {
        String key = System.getProperty("com.expediagroup.xapjavasdk.apikey");
        String secret = System.getProperty("com.expediagroup.xapjavasdk.apisecret");
        return XapClient
                .builder()
                .key(key)
                .secret(secret)
                .build();
    }
}
