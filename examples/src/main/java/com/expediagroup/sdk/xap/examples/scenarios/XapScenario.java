/*
 * Copyright (C) 2025 Expedia, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.expediagroup.sdk.xap.examples.scenarios;

import com.expediagroup.sdk.core.auth.basic.BasicAuthCredentials;
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
        String key = System.getenv("XAP_KEY");
        String secret = System.getenv("XAP_SECRET");

        BasicAuthCredentials credentials = new BasicAuthCredentials(key, secret);

        // Or enable OAuth by passing OAuthCredentials instead:
        // OAuthCredentials credentials = new OAuthCredentials("api-key", "api-secret");

        return XapClient
            .builder()
            .credentials(credentials)
            .build();
    }
}
