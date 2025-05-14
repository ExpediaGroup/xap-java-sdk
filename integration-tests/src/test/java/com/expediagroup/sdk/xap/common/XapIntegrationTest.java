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

package com.expediagroup.sdk.xap.common;

import static com.expediagroup.sdk.xap.common.Constant.MOCK_KEY;
import static com.expediagroup.sdk.xap.common.Constant.MOCK_SECRET;

import com.expediagroup.sdk.core.auth.basic.BasicAuthCredentials;
import com.expediagroup.sdk.core.auth.common.Credentials;
import com.expediagroup.sdk.xap.client.XapClient;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

/**
 * Extension for setting up the required components for testing.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class XapIntegrationTest {

  protected XapClient xapClient;
  protected MockWebServer mockWebServer;

  @BeforeEach
  void setup() {
    mockWebServer = new MockWebServer();

    Credentials credentials = new BasicAuthCredentials(MOCK_KEY, MOCK_SECRET);

    xapClient = XapClient.builder()
        .credentials(credentials)
        .host(mockWebServer.url("/").toString())
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
