package com.expediagroup.sdk.xap.core

import com.expediagroup.sdk.core.http.Request
import com.expediagroup.sdk.core.pipeline.RequestPipelineStep

class ApiKeyHeaderStep(
    private val apiKey: String
) : RequestPipelineStep {
    override fun invoke(request: Request): Request =
        request.newBuilder()
            .addHeader("key", apiKey)
            .build()
}
