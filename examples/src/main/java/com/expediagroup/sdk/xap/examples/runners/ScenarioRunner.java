package com.expediagroup.sdk.xap.examples.runners;

import com.expediagroup.sdk.core.auth.common.Credentials;
import com.expediagroup.sdk.okhttp.OkHttpClientConfiguration;
import com.expediagroup.sdk.okhttp.OkHttpTransport;
import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.configuration.XapClientConfiguration;

public abstract class ScenarioRunner {

    static protected XapClient createClient(Credentials credentials) {
        OkHttpTransport transport = new OkHttpTransport(
            OkHttpClientConfiguration.builder()
                .callTimeout(100000)
                .connectTimeout(100000)
                .readTimeout(100000)
                .build()
        );

        XapClientConfiguration config = new XapClientConfiguration(credentials, transport);

        return new XapClient(config);
    }
}
