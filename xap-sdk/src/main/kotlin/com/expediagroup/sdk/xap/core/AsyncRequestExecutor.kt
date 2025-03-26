package com.expediagroup.sdk.xap.core

import com.expediagroup.sdk.core.auth.basic.BasicAuthManager
import com.expediagroup.sdk.core.auth.basic.BasicCredentials
import com.expediagroup.sdk.core.auth.oauth.OAuthAsyncManager
import com.expediagroup.sdk.core.auth.oauth.OAuthCredentials
import com.expediagroup.sdk.core.exception.client.ExpediaGroupConfigurationException
import com.expediagroup.sdk.core.logging.LoggerDecorator
import com.expediagroup.sdk.core.pipeline.ExecutionPipeline
import com.expediagroup.sdk.core.pipeline.RequestPipelineStep
import com.expediagroup.sdk.core.pipeline.ResponsePipelineStep
import com.expediagroup.sdk.core.pipeline.step.BasicAuthStep
import com.expediagroup.sdk.core.pipeline.step.OAuthStep
import com.expediagroup.sdk.core.pipeline.step.RequestHeadersStep
import com.expediagroup.sdk.core.pipeline.step.RequestLoggingStep
import com.expediagroup.sdk.core.pipeline.step.ResponseLoggingStep
import com.expediagroup.sdk.core.transport.AbstractAsyncRequestExecutor
import com.expediagroup.sdk.xap.configuration.AsyncXapClientConfiguration
import com.expediagroup.sdk.xap.configuration.Constant.AUTH_ENDPOINT
import org.slf4j.LoggerFactory

/**
 * Executor for handling asynchronous requests with XAP client configuration.
 *
 * @param configuration The configuration for the asynchronous XAP client.
 */
class AsyncRequestExecutor(
    private val configuration: AsyncXapClientConfiguration,
) : AbstractAsyncRequestExecutor(configuration.asyncTransport) {
    override val executionPipeline =
        ExecutionPipeline(
            requestPipeline = getRequestPipeline(),
            responsePipeline = getResponsePipeline(),
        )

    private fun getRequestPipeline(): List<RequestPipelineStep> =
        when (configuration.credentials) {
            is BasicCredentials ->
                listOf(
                    RequestHeadersStep(),
                    ApiKeyHeaderStep(configuration.credentials.username),
                    BasicAuthStep(BasicAuthManager(configuration.credentials)),
                    RequestLoggingStep(logger),
                )

            is OAuthCredentials ->
                listOf(
                    RequestHeadersStep(),
                    ApiKeyHeaderStep(configuration.credentials.key),
                    OAuthStep(
                        OAuthAsyncManager(
                            credentials = configuration.credentials,
                            asyncTransport = asyncTransport,
                            authUrl = AUTH_ENDPOINT,
                        ),
                    ),
                    RequestLoggingStep(logger),
                )

            else -> throw ExpediaGroupConfigurationException("Unsupported credentials type: ${configuration.credentials.javaClass.name}")
        }

    private fun getResponsePipeline(): List<ResponsePipelineStep> =
        listOf(
            ResponseLoggingStep(logger),
        )

    companion object {
        private val logger = LoggerDecorator(LoggerFactory.getLogger(this::class.java.enclosingClass))
    }
}
