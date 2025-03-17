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


import com.fasterxml.jackson.annotation.JsonProperty

/**
* Container for information about the property manager. It will be shown for Vacation Rental hotel only.
    * @param name The name of the property manager.
    * @param calendarLastUpdated The latest updated date.
    * @param photoUrl The URL for property manager's photo.
*/
data class PropertyManager(
            /* The name of the property manager. */
@JsonProperty("Name")
val name: kotlin.String? = null,

            /* The latest updated date. */
@JsonProperty("CalendarLastUpdated")
val calendarLastUpdated: java.time.LocalDate? = null,

            /* The URL for property manager's photo. */
@JsonProperty("PhotoUrl")
val photoUrl: kotlin.String? = null
) {
    


    init {
        





















    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var name: kotlin.String? = null,
                private var calendarLastUpdated: java.time.LocalDate? = null,
                private var photoUrl: kotlin.String? = null
        ) {
                fun name(name: kotlin.String?) = apply { this.name = name }
                fun calendarLastUpdated(calendarLastUpdated: java.time.LocalDate?) = apply { this.calendarLastUpdated = calendarLastUpdated }
                fun photoUrl(photoUrl: kotlin.String?) = apply { this.photoUrl = photoUrl }

    fun build(): PropertyManager {
    val instance = PropertyManager(
            name = name,
            calendarLastUpdated = calendarLastUpdated,
            photoUrl = photoUrl
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            name = name,
            calendarLastUpdated = calendarLastUpdated,
            photoUrl = photoUrl
    )
}
