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
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * List of lounges
 * @param name Name of the lounge corresponding to a lounge key
 * @param isConferenceRoomAvailable Indicates whether the lounge has a conference room.
 * @param phoneNumber Phone number of the lounge (if available)
 * @param description Free text field with any extra information about the lounge
 */
@ConsistentCopyVisibility data class Lounge private constructor(
    /* Name of the lounge corresponding to a lounge key */
    @JsonProperty("Name")
    val name: kotlin.String,

    /* Indicates whether the lounge has a conference room. */
    @JsonProperty("IsConferenceRoomAvailable")
    val isConferenceRoomAvailable: kotlin.Boolean? = null,

    /* Phone number of the lounge (if available) */
    @JsonProperty("PhoneNumber")
    val phoneNumber: kotlin.String? = null,

    /* Free text field with any extra information about the lounge */
    @JsonProperty("Description")
    val description: kotlin.String? = null,
) {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var name: kotlin.String? = null,
        private var isConferenceRoomAvailable: kotlin.Boolean? = null,
        private var phoneNumber: kotlin.String? = null,
        private var description: kotlin.String? = null,
    ) {
        fun name(name: kotlin.String) = apply { this.name = name }

        fun isConferenceRoomAvailable(isConferenceRoomAvailable: kotlin.Boolean?) = apply { this.isConferenceRoomAvailable = isConferenceRoomAvailable }

        fun phoneNumber(phoneNumber: kotlin.String?) = apply { this.phoneNumber = phoneNumber }

        fun description(description: kotlin.String?) = apply { this.description = description }

        fun build(): Lounge {
            val name = this.name.getOrThrow {
                IllegalArgumentException("name must not be null")
            }

            val instance = Lounge(
                name = name,
                isConferenceRoomAvailable = isConferenceRoomAvailable,
                phoneNumber = phoneNumber,
                description = description,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        name = name,
        isConferenceRoomAvailable = isConferenceRoomAvailable,
        phoneNumber = phoneNumber,
        description = description,
    )
}
