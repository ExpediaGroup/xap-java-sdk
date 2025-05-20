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
package com.expediagroup.sdk.xap.models

import com.expediagroup.sdk.xap.models.HotelLinksApiRateCalendar
import com.expediagroup.sdk.xap.models.HotelLinksWebSearchResult
import com.expediagroup.sdk.xap.models.Link
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Container for list of **HATEOAS** links to Expedia website to complete booking.  This links section will only return a deeplink to the Website Search Results page by default.  If you have selected AD deeplinks they will only <u>appear</u> within the `RoomTypes` section of the  response, as the Lodging Details API returns details at the room offer level, and not at the property level.
 * @param webSearchResult
 * @param apiRateCalendar
 */
data class HotelLinks(
    @JsonProperty("WebSearchResult")
    val webSearchResult: HotelLinksWebSearchResult? = null,
    @JsonProperty("ApiRateCalendar")
    val apiRateCalendar: HotelLinksApiRateCalendar? = null,
) {
    init {
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var webSearchResult: HotelLinksWebSearchResult? = null,
        private var apiRateCalendar: HotelLinksApiRateCalendar? = null,
    ) {
        fun webSearchResult(webSearchResult: HotelLinksWebSearchResult?) = apply { this.webSearchResult = webSearchResult }

        fun apiRateCalendar(apiRateCalendar: HotelLinksApiRateCalendar?) = apply { this.apiRateCalendar = apiRateCalendar }

        fun build(): HotelLinks {
            val instance =
                HotelLinks(
                    webSearchResult = webSearchResult,
                    apiRateCalendar = apiRateCalendar,
                )

            return instance
        }
    }

    fun toBuilder() =
        Builder(
            webSearchResult = webSearchResult,
            apiRateCalendar = apiRateCalendar,
        )
}
