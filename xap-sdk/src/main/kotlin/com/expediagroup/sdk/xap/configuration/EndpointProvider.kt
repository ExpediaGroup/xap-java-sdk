package com.expediagroup.sdk.xap.configuration

import com.expediagroup.sdk.core.exception.client.ExpediaGroupConfigurationException

/**
 * Provides API endpoints based on the client environment.
 */
internal object EndpointProvider {
    /**
     * Retrieves the API endpoint for the given environment.
     *
     * @param environment The client environment. Defaults to [ClientEnvironment.PROD] if not specified.
     * @return The [ApiEndpoint] for the specified environment.
     * @throws ExpediaGroupConfigurationException If the environment is unsupported.
     */
    fun getXapApiEndpoint(environment: ClientEnvironment? = null): ApiEndpoint {
        val env = environment ?: ClientEnvironment.PROD

        return try {
            ApiEndpoint(
                XapApiEndpoint.valueOf(env.name).url,
            )
        } catch (e: IllegalArgumentException) {
            throw ExpediaGroupConfigurationException(
                """
                Unsupported environment [$environment] for Supply API.
                Supported environments are [${XapApiEndpoint.entries.joinToString(", ")}]
                """,
            )
        }
    }
}
