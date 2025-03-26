package com.expediagroup.sdk.xap.client

import com.expediagroup.sdk.rest.AsyncRestClient
import com.expediagroup.sdk.rest.AsyncRestExecutor
import com.expediagroup.sdk.rest.model.Response
import com.expediagroup.sdk.rest.trait.operation.JacksonModelOperationResponseBodyTrait
import com.expediagroup.sdk.rest.trait.operation.OperationNoResponseBodyTrait
import com.expediagroup.sdk.xap.configuration.AsyncClientBuilder
import com.expediagroup.sdk.xap.configuration.AsyncXapClientConfiguration
import com.expediagroup.sdk.xap.configuration.Constant.ENDPOINT
import com.expediagroup.sdk.xap.configuration.XAP_OBJECT_MAPPER
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
            mapper = XAP_OBJECT_MAPPER,
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

    /**
     * Executes an operation that expects a response body.
     *
     * @param T The type of the response body.
     * @param operation The operation to execute.
     * @return A CompletableFuture containing the response.
     */
    fun <T : Any> execute(operation: JacksonModelOperationResponseBodyTrait<T>): CompletableFuture<Response<T>> = restExecutor.execute(operation)

    companion object {
        /**
         * Builder for creating an instance of [AsyncXapClient].
         */
        class Builder : AsyncClientBuilder<AsyncXapClient>() {
            override fun build(): AsyncXapClient = AsyncXapClient(buildConfig())
        }

        /**
         * Creates a new builder for [AsyncXapClient].
         *
         * @return A new [Builder] instance.
         */
        @JvmStatic
        fun builder() = Builder()
    }
}
