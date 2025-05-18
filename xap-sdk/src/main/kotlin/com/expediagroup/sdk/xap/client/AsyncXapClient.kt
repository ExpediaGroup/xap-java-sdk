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
package com.expediagroup.sdk.xap.client

import com.expediagroup.sdk.rest.AsyncRestClient
import com.expediagroup.sdk.rest.AsyncRestExecutor
import com.expediagroup.sdk.rest.model.Response
import com.expediagroup.sdk.rest.trait.operation.OperationNoResponseBodyTrait
import com.expediagroup.sdk.xap.configuration.AsyncXapClientConfiguration
import com.expediagroup.sdk.xap.configuration.Constant.ENDPOINT
import com.expediagroup.sdk.xap.configuration.OBJECT_MAPPER
import com.expediagroup.sdk.xap.core.AsyncRequestExecutor
import java.util.concurrent.CompletableFuture

/**
 * Asynchronous client for XAP API.
 *
 * @property restExecutor The executor for handling REST operations.
 */
class AsyncXapClient private constructor(
    config: AsyncXapClientConfiguration,
) : AsyncRestClient() {
    override val restExecutor: AsyncRestExecutor =
        AsyncRestExecutor(
            mapper = OBJECT_MAPPER,
            serverUrl = ENDPOINT,
            requestExecutor = AsyncRequestExecutor(configuration = config),
        )

    /**
     * Executes an operation that does not expect a response body.
     *
     * @param operation The operation to execute.
     * @return A CompletableFuture containing the response.
     */
    fun execute(operation: OperationNoResponseBodyTrait): CompletableFuture<Response<Nothing?>> = restExecutor.execute(operation)
}
