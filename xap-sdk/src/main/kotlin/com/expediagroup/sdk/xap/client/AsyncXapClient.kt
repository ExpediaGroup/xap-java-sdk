package com.expediagroup.sdk.xap.client

import com.expediagroup.sdk.rest.AsyncRestClient
import com.expediagroup.sdk.rest.AsyncRestExecutor
import com.expediagroup.sdk.rest.model.Response
import com.expediagroup.sdk.rest.trait.operation.JacksonModelOperationResponseBodyTrait
import com.expediagroup.sdk.rest.trait.operation.OperationNoResponseBodyTrait
import com.expediagroup.sdk.xap.configuration.ApiEndpoint
import com.expediagroup.sdk.xap.configuration.AsyncClientBuilder
import com.expediagroup.sdk.xap.configuration.AsyncXapClientConfiguration
import com.expediagroup.sdk.xap.configuration.EndpointProvider
import com.expediagroup.sdk.xap.configuration.XAP_OBJECT_MAPPER
import com.expediagroup.sdk.xap.core.AsyncRequestExecutor
import java.util.concurrent.CompletableFuture

class AsyncXapClient private constructor(
    config: AsyncXapClientConfiguration,
) : AsyncRestClient() {
    private val apiEndpoint: ApiEndpoint = EndpointProvider.getXapApiEndpoint(config.environment)

    override val restExecutor: AsyncRestExecutor =
        AsyncRestExecutor(
            mapper = XAP_OBJECT_MAPPER,
            serverUrl = apiEndpoint.endpoint,
            requestExecutor = AsyncRequestExecutor(configuration = config),
        )

    fun execute(operation: OperationNoResponseBodyTrait): CompletableFuture<Response<Nothing?>> = restExecutor.execute(operation)

    fun <T : Any> execute(operation: JacksonModelOperationResponseBodyTrait<T>): CompletableFuture<Response<T>> = restExecutor.execute(operation)

    companion object {
        class Builder : AsyncClientBuilder<AsyncXapClient>() {
            override fun build(): AsyncXapClient = AsyncXapClient(buildConfig())
        }

        @JvmStatic
        fun builder() = Builder()
    }
}
