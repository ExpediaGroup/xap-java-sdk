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
package com.expediagroup.sdk.xap.operations

import com.expediagroup.sdk.core.model.OperationParams
import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.infrastructure.*
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.ktor.http.Headers
import io.ktor.http.Parameters
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * @property partnerTransactionID Partner-generated identifier.
 * @property accept Accept header for the request.
 * @property acceptEncoding Accept encoding for the request.
 */
@JsonDeserialize(builder = PostFlightLinksOperationParams.Builder::class)
data class PostFlightLinksOperationParams(
    @field:NotNull
    @field:Valid
    val partnerTransactionID: kotlin.String,
    @field:NotNull
    @field:Valid
    val accept: kotlin.String,
    @field:Valid
    val acceptEncoding: kotlin.String? =
        null
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("Partner-Transaction-ID") private var partnerTransactionID: kotlin.String? = null,
        @JsonProperty("Accept") private var accept: kotlin.String? = null,
        @JsonProperty("Accept-Encoding") private var acceptEncoding: kotlin.String? = null
    ) {
        /**
         * @param partnerTransactionID Partner-generated identifier.
         */
        fun partnerTransactionID(partnerTransactionID: kotlin.String) = apply { this.partnerTransactionID = partnerTransactionID }

        /**
         * @param accept Accept header for the request.
         */
        fun accept(accept: kotlin.String) = apply { this.accept = accept }

        /**
         * @param acceptEncoding Accept encoding for the request.
         */
        fun acceptEncoding(acceptEncoding: kotlin.String) = apply { this.acceptEncoding = acceptEncoding }

        fun build(): PostFlightLinksOperationParams {
            val params =
                PostFlightLinksOperationParams(
                    partnerTransactionID = partnerTransactionID!!,
                    accept = accept!!,
                    acceptEncoding = acceptEncoding
                )

            validate(params)

            return params
        }

        private fun validate(params: PostFlightLinksOperationParams) {
            val validator =
                Validation
                    .byDefaultProvider()
                    .configure()
                    .messageInterpolator(ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .validator

            val violations = validator.validate(params)

            if (violations.isNotEmpty()) {
                throw PropertyConstraintViolationException(
                    constraintViolations = violations.map { "${it.propertyPath}: ${it.message}" }
                )
            }
        }
    }

    fun toBuilder() =
        Builder(
            partnerTransactionID = partnerTransactionID,
            accept = accept,
            acceptEncoding = acceptEncoding
        )

    override fun getHeaders(): Headers =
        Headers.build {
            partnerTransactionID?.let {
                append("Partner-Transaction-ID", it)
            }
            accept?.let {
                append("Accept", it)
            }
            acceptEncoding?.let {
                append("Accept-Encoding", it)
            }
            append("Accept", "application/vnd.exp-flight.v3+json")
        }

    override fun getQueryParams(): Parameters =
        Parameters.build {
        }

    override fun getPathParams(): Map<String, String> =
        buildMap {
        }
}
