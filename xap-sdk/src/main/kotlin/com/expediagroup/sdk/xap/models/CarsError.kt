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

import com.expediagroup.sdk.xap.models.CarsLocationOption

import com.fasterxml.jackson.annotation.JsonProperty

/**
* Container for error list.
    * @param code Error code describing the issue
    * @param description A simple description of what the error is.
    * @param detailCode Detailed error code describing the issue.
    * @param locationKeyword The requested location that caused the error.
    * @param locationOptions List for possible locations from which the customer must choose the best one to be re-submitted in the request. 
*/
data class CarsError(
            /* Error code describing the issue */
@JsonProperty("Code")
val code:
    kotlin.String
,

            /* A simple description of what the error is. */
@JsonProperty("Description")
val description:
    kotlin.String
,

            /* Detailed error code describing the issue. */
@JsonProperty("DetailCode")
val detailCode: kotlin.String? = null,

            /* The requested location that caused the error. */
@JsonProperty("LocationKeyword")
val locationKeyword: kotlin.String? = null,

            /* List for possible locations from which the customer must choose the best one to be re-submitted in the request.  */
@JsonProperty("LocationOptions")
val locationOptions: kotlin.collections.List<CarsLocationOption>? = null
) {
    


    init {
                require(code != null) { "code must not be null" }







        require(description != null) { "description must not be null" }





























    }

    companion object {
    @JvmStatic
    fun builder() = Builder()
    }

        class Builder(
                private var code: kotlin.String? = null,
                private var description: kotlin.String? = null,
                private var detailCode: kotlin.String? = null,
                private var locationKeyword: kotlin.String? = null,
                private var locationOptions: kotlin.collections.List<CarsLocationOption>? = null
        ) {
                fun code(code: kotlin.String) = apply { this.code = code }
                fun description(description: kotlin.String) = apply { this.description = description }
                fun detailCode(detailCode: kotlin.String?) = apply { this.detailCode = detailCode }
                fun locationKeyword(locationKeyword: kotlin.String?) = apply { this.locationKeyword = locationKeyword }
                fun locationOptions(locationOptions: kotlin.collections.List<CarsLocationOption>?) = apply { this.locationOptions = locationOptions }

    fun build(): CarsError {
    val instance = CarsError(
            code = code!!,
            description = description!!,
            detailCode = detailCode,
            locationKeyword = locationKeyword,
            locationOptions = locationOptions
    )

    return instance
    }
    }

    fun toBuilder() = Builder(
            code = code!!,
            description = description!!,
            detailCode = detailCode,
            locationKeyword = locationKeyword,
            locationOptions = locationOptions
    )
}
