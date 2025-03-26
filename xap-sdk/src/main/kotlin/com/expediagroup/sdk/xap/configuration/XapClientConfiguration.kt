package com.expediagroup.sdk.xap.configuration

import com.expediagroup.sdk.core.auth.common.Credentials
import com.expediagroup.sdk.core.transport.AsyncTransport
import com.expediagroup.sdk.core.transport.Transport
import com.expediagroup.sdk.rest.AsyncRestClient
import com.expediagroup.sdk.rest.RestClient

/**
 * Configuration data class for XAP client.
 *
 * @property transport The transport mechanism. Defaults to null.
 */
data class XapClientConfiguration(
    val credentials: Credentials,
    val transport: Transport? = null,
)

/**
 * Configuration data class for asynchronous XAP client.
 *
 * @property asyncTransport The asynchronous transport mechanism. Defaults to null.
 */
data class AsyncXapClientConfiguration(
    val credentials: Credentials,
    val asyncTransport: AsyncTransport? = null,
)

/**
 * Abstract builder class for creating instances of [RestClient].
 *
 * @param T The type of [RestClient] to build.
 */
abstract class ClientBuilder<T : RestClient> {
    private var credentials: Credentials? = null
    private var transport: Transport? = null

    fun credentials(credentials: Credentials) = apply { this.credentials = credentials }

    /**
     * Sets the transport mechanism.
     *
     * @param transport The transport mechanism.
     * @return The builder instance.
     */
    fun transport(transport: Transport) = apply { this.transport = transport }

    /**
     * Builds the [RestClient] instance.
     *
     * @return The built [RestClient] instance.
     */
    abstract fun build(): T

    /**
     * Builds the configuration for the XAP client.
     *
     * @return The built [XapClientConfiguration] instance.
     * @throws IllegalArgumentException If the key or secret is not provided.
     */
    protected fun buildConfig(): XapClientConfiguration {
        require(credentials != null) {
            "credentials is required"
        }

        return XapClientConfiguration(
            credentials = credentials!!,
            transport = transport,
        )
    }
}

/**
 * Abstract builder class for creating instances of [AsyncRestClient].
 *
 * @param T The type of [AsyncRestClient] to build.
 */
abstract class AsyncClientBuilder<T : AsyncRestClient> {
    private var credentials: Credentials? = null
    private var asyncTransport: AsyncTransport? = null

    fun credentials(credentials: Credentials) = apply { this.credentials = credentials }

    /**
     * Sets the asynchronous transport mechanism.
     *
     * @param asyncTransport The asynchronous transport mechanism.
     * @return The builder instance.
     */
    fun asyncTransport(asyncTransport: AsyncTransport) = apply { this.asyncTransport = asyncTransport }

    /**
     * Builds the [AsyncRestClient] instance.
     *
     * @return The built [AsyncRestClient] instance.
     */
    abstract fun build(): T

    /**
     * Builds the configuration for the asynchronous XAP client.
     *
     * @return The built [AsyncXapClientConfiguration] instance.
     * @throws IllegalArgumentException If the key or secret is not provided.
     */
    protected fun buildConfig(): AsyncXapClientConfiguration {
        require(credentials != null) {
            "credentials is required"
        }

        return AsyncXapClientConfiguration(
            credentials = credentials!!,
            asyncTransport = asyncTransport,
        )
    }
}
