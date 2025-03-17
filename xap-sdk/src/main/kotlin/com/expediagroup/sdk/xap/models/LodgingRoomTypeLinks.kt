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

import com.expediagroup.sdk.xap.models.LodgingLink
import com.expediagroup.sdk.xap.models.LodgingRoomTypeLinksWebDetails
import com.expediagroup.sdk.xap.models.LodgingRoomTypeLinksWebSearchResult
import com.fasterxml.jackson.annotation.JsonProperty

/**
* Container for list of HATEOAS links to either Expedia website or additional Expedia APIs to complete booking of the selected offer.  Which links are returned in this section are defined by the links parameter in the Search API query. Available links are: - WebDetails (link to web infosite) - WebSearchResult (link to web search result page)
 * @param webSearchResult
 * @param webDetails
*/
data class LodgingRoomTypeLinks(
    @JsonProperty("WebSearchResult")
    val webSearchResult: LodgingRoomTypeLinksWebSearchResult? = null,
    @JsonProperty("WebDetails")
    val webDetails: LodgingRoomTypeLinksWebDetails? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var webSearchResult: LodgingRoomTypeLinksWebSearchResult? = null,
        private var webDetails: LodgingRoomTypeLinksWebDetails? = null,
    ) {
        fun webSearchResult(webSearchResult: LodgingRoomTypeLinksWebSearchResult?) = apply { this.webSearchResult = webSearchResult }

        fun webDetails(webDetails: LodgingRoomTypeLinksWebDetails?) = apply { this.webDetails = webDetails }

        fun build(): LodgingRoomTypeLinks {
            val instance =
                LodgingRoomTypeLinks(
                    webSearchResult = webSearchResult,
                    webDetails = webDetails,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            webSearchResult = webSearchResult,
            webDetails = webDetails,
        )
}
