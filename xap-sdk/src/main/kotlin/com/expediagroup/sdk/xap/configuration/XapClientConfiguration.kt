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
package com.expediagroup.sdk.xap.configuration

import com.expediagroup.sdk.core.auth.common.Credentials
import com.expediagroup.sdk.core.transport.AsyncTransport
import com.expediagroup.sdk.core.transport.Transport

/**
 * Configuration data class for XAP client.
 *
 * @property transport The transport mechanism. Defaults to null.
 */
data class XapClientConfiguration @JvmOverloads constructor(
    val credentials: Credentials,
    val transport: Transport? = null,
)

/**
 * Configuration data class for asynchronous XAP client.
 *
 * @property asyncTransport The asynchronous transport mechanism. Defaults to null.
 */
data class AsyncXapClientConfiguration @JvmOverloads constructor(
    val credentials: Credentials,
    val asyncTransport: AsyncTransport? = null,
)
