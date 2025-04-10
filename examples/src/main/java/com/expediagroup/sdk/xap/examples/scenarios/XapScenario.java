/*
 * Copyright (C) 2024 Expedia, Inc.
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

import com.expediagroup.sdk.xap.client.XapClient;

/**
 * Interface for scenarios.
 */
public interface XapScenario {

  String PARTNER_TRANSACTION_ID = "txn-123-4";

  void run();

  /**
   * Create a client.
   *
   * @return XapClient
   */
  default XapClient createClient() {
    String key = "3F2504E0-4F89-11D3-9A0C-0305E82C3301";
    String secret = "Hcqq0UFOv7MACr6x";
    return XapClient
        .builder()
        .key(key)
        .secret(secret)
        .build();
  }
}
