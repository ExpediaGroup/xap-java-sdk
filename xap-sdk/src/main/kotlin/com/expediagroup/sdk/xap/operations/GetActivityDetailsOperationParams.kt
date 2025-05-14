package com.expediagroup.sdk.xap.operations

import com.expediagroup.sdk.core.http.Headers
import com.expediagroup.sdk.rest.model.UrlQueryParam
import com.expediagroup.sdk.rest.util.UrlQueryParamStringifier.explode
import com.expediagroup.sdk.rest.util.swaggerCollectionFormatStringifier
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

/**
 * @property offerToken the offerToken of a activity
 * @property partnerTransactionId Partner-generated identifier.
 * @property locale locale is composed of language identifier and region identifier, connected by \"_\" that specifies the language in which the response will be returned. example: \"fr_FR\" refers to French as spoken in France, while \"fr_CA\" refers to French as spoken in Canada. For a full list of supported locales please refer to the link at the bottom of the page.
 */
@JsonDeserialize(builder = GetActivityDetailsOperationParams.Builder::class)
data class GetActivityDetailsOperationParams(
    val offerToken: kotlin.String,
    val partnerTransactionId: kotlin.String,
    val locale: kotlin.String? =
        null,
) {
    init {
        require(offerToken != null) { "offerToken must not be null" }

        require(partnerTransactionId != null) { "partnerTransactionId must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("offerToken") private var offerToken: kotlin.String? = null,
        @JsonProperty("Partner-Transaction-Id") private var partnerTransactionId: kotlin.String? = null,
        @JsonProperty("locale") private var locale: kotlin.String? = null,
    ) {
        /**
         * @param offerToken the offerToken of a activity
         */
        fun offerToken(offerToken: kotlin.String) = apply { this.offerToken = offerToken }

        /**
         * @param partnerTransactionId Partner-generated identifier.
         */
        fun partnerTransactionId(partnerTransactionId: kotlin.String) = apply { this.partnerTransactionId = partnerTransactionId }

        /**
         * @param locale locale is composed of language identifier and region identifier, connected by \"_\" that specifies the language in which the response will be returned. example: \"fr_FR\" refers to French as spoken in France, while \"fr_CA\" refers to French as spoken in Canada. For a full list of supported locales please refer to the link at the bottom of the page.
         */
        fun locale(locale: kotlin.String) = apply { this.locale = locale }

        fun build(): GetActivityDetailsOperationParams {
            val params =
                GetActivityDetailsOperationParams(
                    offerToken = offerToken!!,
                    partnerTransactionId = partnerTransactionId!!,
                    locale = locale,
                )

            return params
        }
    }

    fun toBuilder() =
        Builder(
            offerToken = offerToken,
            partnerTransactionId = partnerTransactionId,
            locale = locale,
        )

    fun getHeaders(): Headers =
        Headers
            .builder()
            .apply {
                partnerTransactionId?.let {
                    add("Partner-Transaction-Id", it)
                }
                add("Accept", "application/vnd.exp-activity.v3+json")
            }.build()

    fun getQueryParams(): List<UrlQueryParam> =
        buildList {
            locale?.let {
                val key = "locale"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
        }

    fun getPathParams(): Map<String, String> =
        buildMap {
            offerToken?.also {
                put("offerToken", offerToken)
            }
        }
}
