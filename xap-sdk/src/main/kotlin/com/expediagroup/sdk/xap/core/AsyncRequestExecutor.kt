package com.expediagroup.sdk.xap.core

import com.expediagroup.sdk.core.authentication.basic.BasicAuthenticationManager
import com.expediagroup.sdk.core.authentication.common.Credentials
import com.expediagroup.sdk.core.logging.LoggerDecorator
import com.expediagroup.sdk.core.pipeline.ExecutionPipeline
import com.expediagroup.sdk.core.pipeline.step.BasicAuthenticationStep
import com.expediagroup.sdk.core.pipeline.step.RequestHeadersStep
import com.expediagroup.sdk.core.pipeline.step.RequestLoggingStep
import com.expediagroup.sdk.core.pipeline.step.ResponseLoggingStep
import com.expediagroup.sdk.core.transport.AbstractAsyncRequestExecutor
import com.expediagroup.sdk.xap.configuration.AsyncXapClientConfiguration
import org.slf4j.LoggerFactory

class AsyncRequestExecutor(
    configuration: AsyncXapClientConfiguration,
) : AbstractAsyncRequestExecutor(configuration.asyncTransport) {
    private val authManager =
        BasicAuthenticationManager(
            credentials = Credentials(configuration.key, configuration.secret),
        )

    override val executionPipeline =
        ExecutionPipeline(
            requestPipeline =
                listOf(
                    RequestHeadersStep(),
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
