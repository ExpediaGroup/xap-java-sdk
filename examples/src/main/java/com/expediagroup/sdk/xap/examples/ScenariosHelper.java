/**
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
package com.expediagroup.sdk.xap.examples;

import com.expediagroup.sdk.core.auth.basic.BasicAuthCredentials;
import com.expediagroup.sdk.core.auth.common.Credentials;
import com.expediagroup.sdk.okhttp.OkHttpClientConfiguration;
import com.expediagroup.sdk.okhttp.OkHttpTransport;
import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.configuration.XapClientConfiguration;
import com.expediagroup.sdk.xap.core.model.XapOAuthCredentials;

public class ScenariosHelper {
    public static final String XAP_KEY_PROPERTY = "xapkey";
    public static final String XAP_SECRET_PROPERTY = "xapsecret";
    public static final String VRBO_KEY_PROPERTY = "vrbokey";
    public static final String VRBO_SECRET_PROPERTY = "vrbosecret";
    public static final String AUTH_TYPE_PROPERTY = "authtype";
    public static final String PARTNER_KEY_PROPERTY = "partnerkey";
    public static final String AUTH_TYPE_BASIC = "basic";
    public static final String AUTH_TYPE_OAUTH = "oauth";

    public static XapClient createClient(Credentials credentials) {
        OkHttpClientConfiguration okHttpConfig = OkHttpClientConfiguration.builder()
            .callTimeout(100000)
            .connectTimeout(100000)
            .readTimeout(100000)
            .build();

        OkHttpTransport transport = new OkHttpTransport(okHttpConfig);
        XapClientConfiguration config = new XapClientConfiguration(credentials, transport);
        return new XapClient(config);
    }

    public static Credentials getXapCredentials() {
        String key = System.getProperty(XAP_KEY_PROPERTY);
        String secret = System.getProperty(XAP_SECRET_PROPERTY);
        String authType = System.getProperty(AUTH_TYPE_PROPERTY, AUTH_TYPE_BASIC).toLowerCase();

        if (authType.equals(AUTH_TYPE_OAUTH)) {
            String partnerKey = System.getProperty(PARTNER_KEY_PROPERTY);

            return XapOAuthCredentials.builder()
                .key(key)
                .secret(secret)
                .partnerKey(partnerKey)
                .build();
        }

        return new BasicAuthCredentials(key, secret);
    }

    public static Credentials getVrboCredentials() {
        String key = System.getProperty(VRBO_KEY_PROPERTY);
        String secret = System.getProperty(VRBO_SECRET_PROPERTY);
        String authType = System.getProperty(AUTH_TYPE_PROPERTY, AUTH_TYPE_BASIC).toLowerCase();

        if (authType.equals(AUTH_TYPE_OAUTH)) {
            String partnerKey = System.getProperty(PARTNER_KEY_PROPERTY);

            return XapOAuthCredentials.builder()
                .key(key)
                .secret(secret)
                .partnerKey(partnerKey)
                .build();
        }

        return new BasicAuthCredentials(key, secret);
    }
}
