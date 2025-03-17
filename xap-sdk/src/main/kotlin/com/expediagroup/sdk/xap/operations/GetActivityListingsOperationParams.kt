package com.expediagroup.sdk.xap.operations

import com.expediagroup.sdk.core.http.Headers
import com.expediagroup.sdk.rest.model.UrlQueryParam
import com.expediagroup.sdk.rest.util.stringifyExplode
import com.expediagroup.sdk.rest.util.swaggerCollectionFormatStringifier
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

/**
 * @property partnerTransactionId Partner-generated identifier.
 * @property location Can be a city name, street address, three-letter IATA Airport Code or a landmark name. (If the value submitted does not clearly identify a single location the API may returne a disambiguation response that lists possible options)
 * @property geoLocation The latitude and longitude values identifying the center point of a search radius (circle). North latitude will be represented by a positive value. South latitude by a negative value. East longitude will be represented by a positive value. West longitude by a negative value. The latitude and longitude values are joined together with a comma (,) character.
 * @property startDate Start date for the activity window in YYY-MM-DD format. If an endDate value is supplied there must also be a startDate. default: currentDate
 * @property endDate End date for the activity window in YYY-MM-DD format. default: \"startDate+14\"
 * @property locale locale is composed of language identifier and region identifier, connected by \"_\" that specifies the language in which the response will be returned. example: \"fr_FR\" refers to French as spoken in France, while \"fr_CA\" refers to French as spoken in Canada. For a full list of supported locales please refer to the link at the bottom of the page.
 * @property links Comma-separated list to specify the types of HATEAOS links returned in the API Response. WD (deep link URL to web infosite) AD (details API query)
 */
@JsonDeserialize(builder = GetActivityListingsOperationParams.Builder::class)
data class GetActivityListingsOperationParams(
    val partnerTransactionId: kotlin.String,
    val location: kotlin.String? =
        null,
    val geoLocation: kotlin.String? =
        null,
    val startDate: java.time.LocalDate? =
        null,
    val endDate: java.time.LocalDate? =
        null,
    val locale: kotlin.String? =
        null,
    val links: kotlin.collections.List<
        GetActivityListingsOperationParams.Links,
    >? =
        null,
) {
    init {
        require(partnerTransactionId != null) { "partnerTransactionId must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    enum class Links(
        val value: kotlin.String,
    ) {
        WD("WD"),
        AD("AD"),
    }

    class Builder(
        @JsonProperty("Partner-Transaction-Id") private var partnerTransactionId: kotlin.String? = null,
        @JsonProperty("location") private var location: kotlin.String? = null,
        @JsonProperty("geoLocation") private var geoLocation: kotlin.String? = null,
        @JsonProperty("startDate") private var startDate: java.time.LocalDate? = null,
        @JsonProperty("endDate") private var endDate: java.time.LocalDate? = null,
        @JsonProperty("locale") private var locale: kotlin.String? = null,
        @JsonProperty("links") private var links: kotlin.collections.List<
            GetActivityListingsOperationParams.Links,
        >? = null,
    ) {
        /**
         * @param partnerTransactionId Partner-generated identifier.
         */
        fun partnerTransactionId(partnerTransactionId: kotlin.String) = apply { this.partnerTransactionId = partnerTransactionId }

        /**
         * @param location Can be a city name, street address, three-letter IATA Airport Code or a landmark name. (If the value submitted does not clearly identify a single location the API may returne a disambiguation response that lists possible options)
         */
        fun location(location: kotlin.String) = apply { this.location = location }

        /**
         * @param geoLocation The latitude and longitude values identifying the center point of a search radius (circle). North latitude will be represented by a positive value. South latitude by a negative value. East longitude will be represented by a positive value. West longitude by a negative value. The latitude and longitude values are joined together with a comma (,) character.
         */
        fun geoLocation(geoLocation: kotlin.String) = apply { this.geoLocation = geoLocation }

        /**
         * @param startDate Start date for the activity window in YYY-MM-DD format. If an endDate value is supplied there must also be a startDate. default: currentDate
         */
        fun startDate(startDate: java.time.LocalDate) = apply { this.startDate = startDate }

        /**
         * @param endDate End date for the activity window in YYY-MM-DD format. default: \"startDate+14\"
         */
        fun endDate(endDate: java.time.LocalDate) = apply { this.endDate = endDate }

        /**
         * @param locale locale is composed of language identifier and region identifier, connected by \"_\" that specifies the language in which the response will be returned. example: \"fr_FR\" refers to French as spoken in France, while \"fr_CA\" refers to French as spoken in Canada. For a full list of supported locales please refer to the link at the bottom of the page.
         */
        fun locale(locale: kotlin.String) = apply { this.locale = locale }

        /**
         * @param links Comma-separated list to specify the types of HATEAOS links returned in the API Response. WD (deep link URL to web infosite) AD (details API query)
         */
        fun links(
            links: kotlin.collections.List<
                GetActivityListingsOperationParams.Links,
            >,
        ) = apply { this.links = links }

        fun build(): GetActivityListingsOperationParams {
            val params =
                GetActivityListingsOperationParams(
                    partnerTransactionId = partnerTransactionId!!,
                    location = location,
                    geoLocation = geoLocation,
                    startDate = startDate,
                    endDate = endDate,
                    locale = locale,
                    links = links,
                )

            return params
        }
    }

    fun toBuilder() =
        Builder(
            partnerTransactionId = partnerTransactionId,
            location = location,
            geoLocation = geoLocation,
            startDate = startDate,
            endDate = endDate,
            locale = locale,
            links = links,
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
            location?.let {
                val key = "location"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", stringifyExplode),
                    ),
                )
            }
            geoLocation?.let {
                val key = "geoLocation"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", stringifyExplode),
                    ),
                )
            }
            startDate?.let {
                val key = "startDate"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", stringifyExplode),
                    ),
                )
            }
            endDate?.let {
                val key = "endDate"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", stringifyExplode),
                    ),
                )
            }
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
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", stringifyExplode),
                    ),
                )
            }
            links?.let {
                val key = "links"
                val value =
                    buildList {
                        addAll(it.map { it.value })
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", stringifyExplode),
                    ),
                )
            }
        }
}
