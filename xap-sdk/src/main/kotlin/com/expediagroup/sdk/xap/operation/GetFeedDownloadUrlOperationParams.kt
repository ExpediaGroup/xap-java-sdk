/**
 * Copyright (C) 2025 Expedia, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.expediagroup.sdk.xap.operations

import com.expediagroup.sdk.rest.model.UrlQueryParam
import com.expediagroup.sdk.rest.util.UrlQueryParamStringifier.explode
import com.expediagroup.sdk.rest.util.swaggerCollectionFormatStringifier
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

/**
 * @property type The type of file, used to get files by type.
 * @property locale Follow ISO-3166 Country Codes and ISO-639 Language Codes, format: \"{LanguageCode}-{CountryCode}\".Support multiple values, for the feed files that support localization, Use this parameter to help filter out the localization files you want to download. If not using this parameter, then this API will return all locales files for specified type. If the specified type of file is not  supported localization, there is no file will be returned.
 * @property pointOfSupply The point of supply means a country generally. The downloadable files provided after specifying will only contain properties' information for that country.
 * @property lodgingType The lodging type also means structure type, it only can be `CL`(Conventional Lodging) and `VR`(Vacation Rental). The downloadable files provided after specifying will only contain property information for that lodging type.
 * @property brand The downloadable files provided after specifying will only contain property information for that brand.
 */
@JsonDeserialize(builder = GetFeedDownloadUrlOperationParams.Builder::class)
data class GetFeedDownloadUrlOperationParams(
    val type: GetFeedDownloadUrlOperationParams.Type,
    val locale: kotlin.String? =
        null,
    val pointOfSupply: GetFeedDownloadUrlOperationParams.PointOfSupply? =
        null,
    val lodgingType: GetFeedDownloadUrlOperationParams.LodgingType? =
        null,
    val brand: GetFeedDownloadUrlOperationParams.Brand? =
        null,
) {
    init {
        require(type != null) { "type must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    enum class Type(
        val value: kotlin.String,
    ) {
        DESTINATION("DESTINATION"),
        VENDORLOGO("VENDORLOGO"),
        SUMMARY("SUMMARY"),
        LISTINGS("LISTINGS"),
        IMAGES("IMAGES"),
        AMENITIES("AMENITIES"),
        LOCATIONS("LOCATIONS"),
        DESCRIPTIONS("DESCRIPTIONS"),
        POLICIES("POLICIES"),
        GUEST_REVIEW("GUEST_REVIEW"),
        VACATION_RENTAL("VACATION_RENTAL"),
        ALL_REGIONS("ALL_REGIONS"),
        BOUNDING_POLYGON("BOUNDING_POLYGON"),
        HOTEL_TO_REGION_HIERARCHY("HOTEL_TO_REGION_HIERARCHY"),
        ROOM_DETAILS("ROOM_DETAILS"),
    }

    enum class PointOfSupply(
        val value: kotlin.String,
    ) {
        US("US"),
        AT("AT"),
        BR("BR"),
        CA("CA"),
        FR("FR"),
        DE("DE"),
        GR("GR"),
        IT("IT"),
        JA("JA"),
        KR("KR"),
        MX("MX"),
        PT("PT"),
        ES("ES"),
        TR("TR"),
        AE("AE"),
        GB("GB"),
    }

    enum class LodgingType(
        val value: kotlin.String,
    ) {
        CL("CL"),
        VR("VR"),
    }

    enum class Brand(
        val value: kotlin.String,
    ) {
        VRBO("VRBO"),
    }

    class Builder(
        @JsonProperty("type") private var type: GetFeedDownloadUrlOperationParams.Type? = null,
        @JsonProperty("locale") private var locale: kotlin.String? = null,
        @JsonProperty("pointOfSupply") private var pointOfSupply: GetFeedDownloadUrlOperationParams.PointOfSupply? = null,
        @JsonProperty("lodgingType") private var lodgingType: GetFeedDownloadUrlOperationParams.LodgingType? = null,
        @JsonProperty("brand") private var brand: GetFeedDownloadUrlOperationParams.Brand? = null,
    ) {
        /**
         * @param type The type of file, used to get files by type.
         */
        fun type(type: GetFeedDownloadUrlOperationParams.Type) = apply { this.type = type }

        /**
         * @param locale Follow ISO-3166 Country Codes and ISO-639 Language Codes, format: \"{LanguageCode}-{CountryCode}\".Support multiple values, for the feed files that support localization, Use this parameter to help filter out the localization files you want to download. If not using this parameter, then this API will return all locales files for specified type. If the specified type of file is not  supported localization, there is no file will be returned.
         */
        fun locale(locale: kotlin.String) = apply { this.locale = locale }

        /**
         * @param pointOfSupply The point of supply means a country generally. The downloadable files provided after specifying will only contain properties' information for that country.
         */
        fun pointOfSupply(pointOfSupply: GetFeedDownloadUrlOperationParams.PointOfSupply) = apply { this.pointOfSupply = pointOfSupply }

        /**
         * @param lodgingType The lodging type also means structure type, it only can be `CL`(Conventional Lodging) and `VR`(Vacation Rental). The downloadable files provided after specifying will only contain property information for that lodging type.
         */
        fun lodgingType(lodgingType: GetFeedDownloadUrlOperationParams.LodgingType) = apply { this.lodgingType = lodgingType }

        /**
         * @param brand The downloadable files provided after specifying will only contain property information for that brand.
         */
        fun brand(brand: GetFeedDownloadUrlOperationParams.Brand) = apply { this.brand = brand }

        fun build(): GetFeedDownloadUrlOperationParams {
            val params =
                GetFeedDownloadUrlOperationParams(
                    type = type!!,
                    locale = locale,
                    pointOfSupply = pointOfSupply,
                    lodgingType = lodgingType,
                    brand = brand,
                )

            return params
        }
    }

    fun toBuilder() =
        Builder(
            type = type,
            locale = locale,
            pointOfSupply = pointOfSupply,
            lodgingType = lodgingType,
            brand = brand,
        )

    fun getQueryParams(): List<UrlQueryParam> =
        buildList {
            type?.let {
                val key = "type"
                val value =
                    buildList {
                        add(it.value)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
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
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            pointOfSupply?.let {
                val key = "pointOfSupply"
                val value =
                    buildList {
                        add(it.value)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            lodgingType?.let {
                val key = "lodgingType"
                val value =
                    buildList {
                        add(it.value)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            brand?.let {
                val key = "brand"
                val value =
                    buildList {
                        add(it.value)
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
}
