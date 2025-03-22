package com.expediagroup.sdk.xap.configuration

/**
 * Enum representing the XAP API endpoints.
 *
 * @property url The URL of the API endpoint.
 */
enum class XapApiEndpoint(
    val url: String,
) {
    /** Production environment endpoint. */
    PROD("https://apim.expedia.com"),
}
