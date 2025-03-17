package com.expediagroup.sdk.xap.client

import com.expediagroup.sdk.rest.RestClient
import com.expediagroup.sdk.rest.RestExecutor
import com.expediagroup.sdk.rest.model.Response
import com.expediagroup.sdk.rest.trait.operation.JacksonModelOperationResponseBodyTrait
import com.expediagroup.sdk.rest.trait.operation.OperationNoResponseBodyTrait
import com.expediagroup.sdk.xap.configuration.ApiEndpoint
import com.expediagroup.sdk.xap.configuration.ClientBuilder
import com.expediagroup.sdk.xap.configuration.ClientEnvironment
import com.expediagroup.sdk.xap.configuration.EndpointProvider
import com.expediagroup.sdk.xap.configuration.XAP_OBJECT_MAPPER
import com.expediagroup.sdk.xap.configuration.XapClientConfiguration
import com.expediagroup.sdk.xap.core.RequestExecutor

class XapClient private constructor(
    config: XapClientConfiguration,
) : RestClient() {
    private val apiEndpoint: ApiEndpoint = EndpointProvider.getXapApiEndpoint(config.environment)

    override val restExecutor: RestExecutor =
        RestExecutor(
            mapper = XAP_OBJECT_MAPPER,
            serverUrl = apiEndpoint.endpoint,
            requestExecutor =
                RequestExecutor(
                    configuration =
                        XapClientConfiguration(
                            key = config.key,
                            secret = config.secret,
                            environment = ClientEnvironment.PROD,
                            transport = config.transport,
                        ),
                ),
        )

    fun execute(operation: OperationNoResponseBodyTrait): Response<Nothing?> = restExecutor.execute(operation)

    fun <T : Any> execute(operation: JacksonModelOperationResponseBodyTrait<T>): Response<T> = restExecutor.execute(operation)

    companion object {
        class Builder : ClientBuilder<XapClient>() {
            override fun build(): XapClient = XapClient(buildConfig())
        }

        @JvmStatic
        fun builder() = Builder()
    }
}
