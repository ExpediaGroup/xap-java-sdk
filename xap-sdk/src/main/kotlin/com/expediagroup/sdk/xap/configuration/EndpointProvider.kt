package com.expediagroup.sdk.xap.configuration

import com.expediagroup.sdk.core.exception.client.ExpediaGroupConfigurationException

internal object EndpointProvider {
    fun getXapApiEndpoint(environment: ClientEnvironment? = null): ApiEndpoint {
        val env = environment ?: ClientEnvironment.PROD

        return try {
            ApiEndpoint(
                XapApiEndpoint.valueOf(env.name).url
            )
        } catch (e: IllegalArgumentException) {
            throw ExpediaGroupConfigurationException(
                """
                Unsupported environment [$environment] for Supply API.
                Supported environments are [${XapApiEndpoint.entries.joinToString(", ")}]
                """
            )
        }
    }
}
