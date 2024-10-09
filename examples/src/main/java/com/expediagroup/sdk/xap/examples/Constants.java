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

package com.expediagroup.sdk.xap.examples;

import static com.expediagroup.sdk.dependencies.org.hibernate.validator.internal.util.Contracts.assertNotEmpty;

import com.expediagroup.sdk.xap.client.XapClient;

/**
  Constants.
 **/
public class Constants {

  public static final XapClient XAP_CLIENT;
  public static final String PARTNER_TRANSACTION_ID = "xap-java-sdk-examples";

  static {
    String xapApiKey = System.getenv("XAP_API_KEY");
    String xapApiPassword = System.getenv("XAP_API_PASSWORD");
    assertNotEmpty(xapApiKey, "XAP_API_KEY is missing, please set it in your environment");
    assertNotEmpty(xapApiPassword,
        "XAP_API_PASSWORD is missing, please set it in your environment");
    XAP_CLIENT = XapClient.builder()
        .key(System.getenv("XAP_API_KEY"))
        .secret(System.getenv("XAP_API_PASSWORD"))
        .build();
  }

  private Constants() {
  }

}
