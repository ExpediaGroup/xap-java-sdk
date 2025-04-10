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
  public static final String ACCEPT_ACTIVITY = "application/vnd.exp-activity.v3+json";
}
