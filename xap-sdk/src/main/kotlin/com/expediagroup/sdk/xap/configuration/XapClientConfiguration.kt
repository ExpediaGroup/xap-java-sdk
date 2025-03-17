package com.expediagroup.sdk.xap.configuration

import com.expediagroup.sdk.core.transport.AsyncTransport
import com.expediagroup.sdk.core.transport.Transport
import com.expediagroup.sdk.rest.AsyncRestClient
import com.expediagroup.sdk.rest.RestClient

data class XapClientConfiguration(
    val key: String,
    val secret: String,
    val environment: ClientEnvironment? = null,
    val transport: Transport? = null,
)

data class AsyncXapClientConfiguration(
    val key: String,
    val secret: String,
    val environment: ClientEnvironment? = null,
    val asyncTransport: AsyncTransport? = null,
)

abstract class ClientBuilder<T : RestClient> {
    private var key: String? = null
    private var secret: String? = null
    private var environment: ClientEnvironment? = null
    private var transport: Transport? = null

    fun key(key: String) = apply { this.key = key }

    fun secret(secret: String) = apply { this.secret = secret }

    fun environment(environment: ClientEnvironment) = apply { this.environment = environment }

    fun transport(transport: Transport) = apply { this.transport = transport }

    abstract fun build(): T

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

abstract class AsyncClientBuilder<T : AsyncRestClient> {
    private var key: String? = null
    private var secret: String? = null
    private var environment: ClientEnvironment? = null
    private var asyncTransport: AsyncTransport? = null

    fun key(key: String) = apply { this.key = key }

    fun secret(secret: String) = apply { this.secret = secret }

    fun environment(environment: ClientEnvironment) = apply { this.environment = environment }

    fun asyncTransport(asyncTransport: AsyncTransport) = apply { this.asyncTransport = asyncTransport }

    abstract fun build(): T

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
