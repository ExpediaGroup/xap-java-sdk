package com.expediagroup.sdk.xap.examples.scenarios.lodging;

import com.expediagroup.sdk.core.auth.basic.BasicCredentials;
import com.expediagroup.sdk.core.auth.oauth.OAuthCredentials;
import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.examples.scenarios.XapScenario;

/**
 * Interface for Vrbo scenarios. Scenarios that implement this interface would need a key
 * that is enabled for Vrbo brand to run.
 */
public interface VrboScenario extends XapScenario {

    /**
     * Create a client with Vrbo key and secret.
     *
     * @return XapClient
     */
    @Override
    default XapClient createClient() {
        String key = System.getProperty("com.expediagroup.xapjavasdk.vrbokey");
        String secret = System.getProperty("com.expediagroup.xapjavasdk.vrbosecret");

        BasicCredentials credentials = new BasicCredentials(key, secret);

        // Or enable OAuth by passing OAuthCredentials instead:
        // OAuthCredentials credentials = new OAuthCredentials("api-key", "api-secret");

        return XapClient
            .builder()
            .credentials(credentials)
            .build();
    }

}
