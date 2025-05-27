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
package com.expediagroup.sdk.xap.model

import com.expediagroup.sdk.core.common.getOrThrow
import com.expediagroup.sdk.xap.model.GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInner
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * List for possible locations from which the customer must choose the best one to be re-submitted in the request.
 * @param requestedLocation Location used in partner request.
 * @param locations Container for list of possible locations that could be used to disambiguate the query.
 */
@ConsistentCopyVisibility data class GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOption private constructor(
    /* Location used in partner request. */
    @JsonProperty("RequestedLocation")
    val requestedLocation: kotlin.String,

    /* Container for list of possible locations that could be used to disambiguate the query. */
    @JsonProperty("Locations")
    val locations: kotlin.collections
        .List<
            GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInner,
            >,

) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var requestedLocation: kotlin.String? = null,
        private var locations: kotlin.collections.List<GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInner>? = null,
    ) {
        fun requestedLocation(requestedLocation: kotlin.String) = apply { this.requestedLocation = requestedLocation }

        fun locations(locations: kotlin.collections.List<GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOptionLocationsInner>) = apply { this.locations = locations }

        fun build(): GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOption {
            val requestedLocation = this.requestedLocation.getOrThrow {
                IllegalArgumentException("requestedLocation must not be null")
            }

            val locations = this.locations.getOrThrow {
                IllegalArgumentException("locations must not be null")
            }

            val instance = GetFlightFlexsearch400ResponseErrorsInnerErrorLocationOptionsInnerLocationOption(
                requestedLocation = requestedLocation,
                locations = locations,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        requestedLocation = requestedLocation,
        locations = locations,
    )
}
