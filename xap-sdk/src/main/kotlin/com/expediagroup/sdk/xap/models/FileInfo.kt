/*
 * Copyright (C) 2022 Expedia, Inc.
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
package com.expediagroup.sdk.xap.models

import com.expediagroup.sdk.xap.models.FileSize
import com.expediagroup.sdk.xap.models.FilterConditions
import com.expediagroup.sdk.xap.models.SdpLink
import com.fasterxml.jackson.annotation.JsonProperty

/**
*
 * @param partner The partner associated with the file. List of partners: `Bing`, `Criteo`, `Facebook`, `Google`, `Retarget`, `Snapchat`.
 * @param brand The brand associated with the file content. List of brand: `Expedia`, `Hotels`, `Hotwire`, `Vrbo`, `HomeAway`, `Abritel`, `Bookabach`, `Stayz`, `Ebbokers`, `Travalocity`, `Orbitz`, `Wotif`.
 * @param fileContentType The type associated with the file content. List of types: `Amenities`, `Descriptions`, `Images`, `Listings`, `Locations`, `Policies`, `Regions`, `Reviews`, `Summary`, `VacationRental`
 * @param locale The locale associated with the file content.
 * @param fileName File name.
 * @param propertySize
 * @param fileLastUpdated The time about the file last updated. The format is uuuu-MM-dd'T'HH:mm:ss.SSSX
 * @param downloadUrl Pre-signed URL is a self signed URL generated for a resource in S3 with a set expiration time.
 * @param downloadUrlExpires The time about the download Url expires. The format is uuuu-MM-dd'T'HH:mm:ss.SSSX
 * @param filterConditions
 * @param bestMatchedLink
*/
data class FileInfo(
    // The partner associated with the file. List of partners: `Bing`, `Criteo`, `Facebook`, `Google`, `Retarget`, `Snapchat`.
    @JsonProperty("partner")
    val partner: kotlin.String? = null,
    // The brand associated with the file content. List of brand: `Expedia`, `Hotels`, `Hotwire`, `Vrbo`, `HomeAway`, `Abritel`, `Bookabach`, `Stayz`, `Ebbokers`, `Travalocity`, `Orbitz`, `Wotif`.
    @JsonProperty("brand")
    val brand: kotlin.String? = null,
    // The type associated with the file content. List of types: `Amenities`, `Descriptions`, `Images`, `Listings`, `Locations`, `Policies`, `Regions`, `Reviews`, `Summary`, `VacationRental`
    @JsonProperty("fileContentType")
    val fileContentType: kotlin.String? = null,
    // The locale associated with the file content.
    @JsonProperty("locale")
    val locale: kotlin.String? = null,
    // File name.
    @JsonProperty("fileName")
    val fileName: kotlin.String? = null,
    @JsonProperty("size")
    val propertySize: FileSize? = null,
    // The time about the file last updated. The format is uuuu-MM-dd'T'HH:mm:ss.SSSX
    @JsonProperty("fileLastUpdated")
    val fileLastUpdated: kotlin.String? = null,
    // Pre-signed URL is a self signed URL generated for a resource in S3 with a set expiration time.
    @JsonProperty("downloadUrl")
    val downloadUrl: kotlin.String? = null,
    // The time about the download Url expires. The format is uuuu-MM-dd'T'HH:mm:ss.SSSX
    @JsonProperty("downloadUrlExpires")
    val downloadUrlExpires: kotlin.String? = null,
    @JsonProperty("filterConditions")
    val filterConditions: FilterConditions? = null,
    @JsonProperty("bestMatchedLink")
    val bestMatchedLink: SdpLink? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var partner: kotlin.String? = null,
        private var brand: kotlin.String? = null,
        private var fileContentType: kotlin.String? = null,
        private var locale: kotlin.String? = null,
        private var fileName: kotlin.String? = null,
        private var propertySize: FileSize? = null,
        private var fileLastUpdated: kotlin.String? = null,
        private var downloadUrl: kotlin.String? = null,
        private var downloadUrlExpires: kotlin.String? = null,
        private var filterConditions: FilterConditions? = null,
        private var bestMatchedLink: SdpLink? = null,
    ) {
        fun partner(partner: kotlin.String?) = apply { this.partner = partner }

        fun brand(brand: kotlin.String?) = apply { this.brand = brand }

        fun fileContentType(fileContentType: kotlin.String?) = apply { this.fileContentType = fileContentType }

        fun locale(locale: kotlin.String?) = apply { this.locale = locale }

        fun fileName(fileName: kotlin.String?) = apply { this.fileName = fileName }

        fun propertySize(propertySize: FileSize?) = apply { this.propertySize = propertySize }

        fun fileLastUpdated(fileLastUpdated: kotlin.String?) = apply { this.fileLastUpdated = fileLastUpdated }

        fun downloadUrl(downloadUrl: kotlin.String?) = apply { this.downloadUrl = downloadUrl }

        fun downloadUrlExpires(downloadUrlExpires: kotlin.String?) = apply { this.downloadUrlExpires = downloadUrlExpires }

        fun filterConditions(filterConditions: FilterConditions?) = apply { this.filterConditions = filterConditions }

        fun bestMatchedLink(bestMatchedLink: SdpLink?) = apply { this.bestMatchedLink = bestMatchedLink }

        fun build(): FileInfo {
            val instance =
                FileInfo(
                    partner = partner,
                    brand = brand,
                    fileContentType = fileContentType,
                    locale = locale,
                    fileName = fileName,
                    propertySize = propertySize,
                    fileLastUpdated = fileLastUpdated,
                    downloadUrl = downloadUrl,
                    downloadUrlExpires = downloadUrlExpires,
                    filterConditions = filterConditions,
                    bestMatchedLink = bestMatchedLink,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            partner = partner,
            brand = brand,
            fileContentType = fileContentType,
            locale = locale,
            fileName = fileName,
            propertySize = propertySize,
            fileLastUpdated = fileLastUpdated,
            downloadUrl = downloadUrl,
            downloadUrlExpires = downloadUrlExpires,
            filterConditions = filterConditions,
            bestMatchedLink = bestMatchedLink,
        )
}
