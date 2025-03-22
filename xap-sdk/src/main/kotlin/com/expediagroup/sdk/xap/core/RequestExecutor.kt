package com.expediagroup.sdk.xap.core

import com.expediagroup.sdk.core.authentication.basic.BasicAuthenticationManager
import com.expediagroup.sdk.core.authentication.common.Credentials
import com.expediagroup.sdk.core.logging.LoggerDecorator
import com.expediagroup.sdk.core.pipeline.ExecutionPipeline
import com.expediagroup.sdk.core.pipeline.step.BasicAuthenticationStep
import com.expediagroup.sdk.core.pipeline.step.RequestHeadersStep
import com.expediagroup.sdk.core.pipeline.step.RequestLoggingStep
import com.expediagroup.sdk.core.pipeline.step.ResponseLoggingStep
import com.expediagroup.sdk.core.transport.AbstractRequestExecutor
import com.expediagroup.sdk.xap.configuration.XapClientConfiguration
import org.slf4j.LoggerFactory

/**
 * Executor for handling synchronous requests with XAP client configuration.
 *
 * @param configuration The configuration for the XAP client.
 */
class RequestExecutor(
    configuration: XapClientConfiguration,
) : AbstractRequestExecutor(configuration.transport) {
    private val authManager =
        BasicAuthenticationManager(
            credentials = Credentials(configuration.key, configuration.secret),
        )

    /**
     * The execution pipeline for processing requests and responses.
     */
    override val executionPipeline =
        ExecutionPipeline(
            requestPipeline =
                listOf(
                    RequestHeadersStep(),
                    ApiKeyHeaderStep(configuration.key),
                    BasicAuthenticationStep(authManager),
                    RequestLoggingStep(logger),
                ),
            responsePipeline =
                listOf(
                    ResponseLoggingStep(logger),
                ),
        )

    companion object {
        private val logger = LoggerDecorator(LoggerFactory.getLogger(this::class.java.enclosingClass))
    }
}
