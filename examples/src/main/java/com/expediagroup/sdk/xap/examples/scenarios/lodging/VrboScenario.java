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

package com.expediagroup.sdk.xap.examples.scenarios.lodging;

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
    return XapClient
        .builder()
        .key(key)
        .secret(secret)
        .build();
  }

}
