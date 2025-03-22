package com.expediagroup.sdk.xap.core

import com.expediagroup.sdk.core.http.Request
import com.expediagroup.sdk.core.pipeline.RequestPipelineStep

/**
 * A pipeline step that adds an API key header to the request.
 *
 * @property apiKey The API key to be added to the request header.
 */
class ApiKeyHeaderStep(
    private val apiKey: String,
) : RequestPipelineStep {
    /**
     * Adds the API key header to the request.
     *
     * @param request The original request.
     * @return The modified request with the API key header added.
     */
    override fun invoke(request: Request): Request =
        request
            .newBuilder()
            .addHeader("key", apiKey)
            .build()
}
