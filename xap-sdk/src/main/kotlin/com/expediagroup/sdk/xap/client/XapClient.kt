package com.expediagroup.sdk.xap.client

import com.expediagroup.sdk.rest.RestClient
import com.expediagroup.sdk.rest.RestExecutor
import com.expediagroup.sdk.rest.model.Response
import com.expediagroup.sdk.rest.trait.operation.JacksonModelOperationResponseBodyTrait
import com.expediagroup.sdk.rest.trait.operation.OperationNoResponseBodyTrait
import com.expediagroup.sdk.xap.configuration.ClientBuilder
import com.expediagroup.sdk.xap.configuration.Constant.ENDPOINT
import com.expediagroup.sdk.xap.configuration.XAP_OBJECT_MAPPER
import com.expediagroup.sdk.xap.configuration.XapClientConfiguration
import com.expediagroup.sdk.xap.core.RequestExecutor

/**
 * Synchronous client for XAP API.
 *
 * @property restExecutor The executor for handling REST operations.
 */
class XapClient private constructor(
    config: XapClientConfiguration,
) : RestClient() {
    override val restExecutor: RestExecutor =
        RestExecutor(
            mapper = XAP_OBJECT_MAPPER,
            serverUrl = ENDPOINT,
            requestExecutor = RequestExecutor(config),
        )

    /**
     * Executes an operation that does not expect a response body.
     *
     * @param operation The operation to execute.
     * @return The response of the operation.
     */
    fun execute(operation: OperationNoResponseBodyTrait): Response<Nothing?> = restExecutor.execute(operation)

    /**
     * Executes an operation that expects a response body.
     *
     * @param T The type of the response body.
     * @param operation The operation to execute.
     * @return The response of the operation.
     */
    fun <T : Any> execute(operation: JacksonModelOperationResponseBodyTrait<T>): Response<T> = restExecutor.execute(operation)

    companion object {
        /**
         * Builder for creating an instance of [XapClient].
         */
        class Builder : ClientBuilder<XapClient>() {
            override fun build(): XapClient = XapClient(buildConfig())
        }

        /**
         * Creates a new builder for [XapClient].
         *
         * @return A new [Builder] instance.
         */
        @JvmStatic
        fun builder() = Builder()
    }
}
