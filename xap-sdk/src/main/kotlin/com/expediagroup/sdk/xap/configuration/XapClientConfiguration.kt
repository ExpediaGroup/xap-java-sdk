package com.expediagroup.sdk.xap.configuration

import com.expediagroup.sdk.core.transport.AsyncTransport
import com.expediagroup.sdk.core.transport.Transport
import com.expediagroup.sdk.rest.AsyncRestClient
import com.expediagroup.sdk.rest.RestClient

/**
 * Configuration data class for XAP client.
 *
 * @property key The API key.
 * @property secret The API secret.
 * @property environment The client environment. Defaults to null.
 * @property transport The transport mechanism. Defaults to null.
 */
data class XapClientConfiguration(
    val key: String,
    val secret: String,
    val environment: ClientEnvironment? = null,
    val transport: Transport? = null,
)

/**
 * Configuration data class for asynchronous XAP client.
 *
 * @property key The API key.
 * @property secret The API secret.
 * @property environment The client environment. Defaults to null.
 * @property asyncTransport The asynchronous transport mechanism. Defaults to null.
 */
data class AsyncXapClientConfiguration(
    val key: String,
    val secret: String,
    val environment: ClientEnvironment? = null,
    val asyncTransport: AsyncTransport? = null,
)

/**
 * Abstract builder class for creating instances of [RestClient].
 *
 * @param T The type of [RestClient] to build.
 */
abstract class ClientBuilder<T : RestClient> {
    private var key: String? = null
    private var secret: String? = null
    private var environment: ClientEnvironment? = null
    private var transport: Transport? = null

    /**
     * Sets the API key.
     *
     * @param key The API key.
     * @return The builder instance.
     */
    fun key(key: String) = apply { this.key = key }

    /**
     * Sets the API secret.
     *
     * @param secret The API secret.
     * @return The builder instance.
     */
    fun secret(secret: String) = apply { this.secret = secret }

    /**
     * Sets the client environment.
     *
     * @param environment The client environment.
     * @return The builder instance.
     */
    fun environment(environment: ClientEnvironment) = apply { this.environment = environment }

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
        require(key != null) {
            "key is required"
        }

        require(secret != null) {
            "secret is required"
        }

        return XapClientConfiguration(
            key = key!!,
            secret = secret!!,
            environment = environment,
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
    private var key: String? = null
    private var secret: String? = null
    private var environment: ClientEnvironment? = null
    private var asyncTransport: AsyncTransport? = null

    /**
     * Sets the API key.
     *
     * @param key The API key.
     * @return The builder instance.
     */
    fun key(key: String) = apply { this.key = key }

    /**
     * Sets the API secret.
     *
     * @param secret The API secret.
     * @return The builder instance.
     */
    fun secret(secret: String) = apply { this.secret = secret }

    /**
     * Sets the client environment.
     *
     * @param environment The client environment.
     * @return The builder instance.
     */
    fun environment(environment: ClientEnvironment) = apply { this.environment = environment }

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
        require(key != null) {
            "key is required"
        }

        require(secret != null) {
            "secret is required"
        }

        return AsyncXapClientConfiguration(
            key = key!!,
            secret = secret!!,
            environment = environment,
            asyncTransport = asyncTransport,
        )
    }
}
