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

package com.expediagroup.sdk.xap.examples.services;

import com.expediagroup.sdk.xap.client.XapClient;

/**
 * Base class for services to access capabilities.
 */
public abstract class XapService {

  protected static final XapClient xapClient = XapClient
      .builder()
      .key(System.getProperty("com.expediagroup.xapjavasdk.apikey", "your_api_key"))
      .secret(System.getProperty("com.expediagroup.xapjavasdk.apisecret", "your_api_secret"))
      .requestTimeout(10000)
      .build();
}
