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
* Car transmission and drive. Please find list of Car Transmission Drive Codes in https://expediaintegration.zendesk.com/hc/en-us/articles/115005380028
    * @param code Car transmission and drive code.
    * @param `value` Car transmission and drive value.
*/
data class TransmissionDrive(
            /* Car transmission and drive code. */
@JsonProperty("Code")
val code:
    kotlin.String
,

            /* Car transmission and drive value. */
@JsonProperty("Value")
val `value`:
    kotlin.String

) {
    


    init {
                require(code != null) { "code must not be null" }







        require(`value` != null) { "`value` must not be null" }








    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var code: kotlin.String? = null,
                private var `value`: kotlin.String? = null
        ) {
                fun code(code: kotlin.String) = apply { this.code = code }
                fun `value`(`value`: kotlin.String) = apply { this.`value` = `value` }

    fun build(): TransmissionDrive {
    val instance = TransmissionDrive(
            code = code!!,
            `value` = `value`!!
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            code = code!!,
            `value` = `value`!!
    )
}
