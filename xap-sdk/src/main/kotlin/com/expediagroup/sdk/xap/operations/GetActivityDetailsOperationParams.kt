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
 * @property offerToken the offerToken of a activity
 * @property partnerTransactionId Partner-generated identifier.
 * @property locale locale is composed of language identifier and region identifier, connected by \"_\" that specifies the language in which the response will be returned. example: \"fr_FR\" refers to French as spoken in France, while \"fr_CA\" refers to French as spoken in Canada. For a full list of supported locales please refer to the link at the bottom of the page.
 */
@JsonDeserialize(builder = GetActivityDetailsOperationParams.Builder::class)
data class GetActivityDetailsOperationParams(
    @field:NotNull
    @field:Valid
    val offerToken: kotlin.String,
    @field:NotNull
    @field:Valid
    val partnerTransactionId: kotlin.String,
    @field:Valid
    val locale: kotlin.String? =
        null
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("offerToken") private var offerToken: kotlin.String? = null,
        @JsonProperty("Partner-Transaction-Id") private var partnerTransactionId: kotlin.String? = null,
        @JsonProperty("locale") private var locale: kotlin.String? = null
    ) {
        /**
         * @param offerToken the offerToken of a activity
         */
        fun offerToken(offerToken: kotlin.String) = apply { this.offerToken = offerToken }

        /**
         * @param partnerTransactionId Partner-generated identifier.
         */
        fun partnerTransactionId(partnerTransactionId: kotlin.String) = apply { this.partnerTransactionId = partnerTransactionId }

        /**
         * @param locale locale is composed of language identifier and region identifier, connected by \"_\" that specifies the language in which the response will be returned. example: \"fr_FR\" refers to French as spoken in France, while \"fr_CA\" refers to French as spoken in Canada. For a full list of supported locales please refer to the link at the bottom of the page.
         */
        fun locale(locale: kotlin.String) = apply { this.locale = locale }

        fun build(): GetActivityDetailsOperationParams {
            val params =
                GetActivityDetailsOperationParams(
                    offerToken = offerToken!!,
                    partnerTransactionId = partnerTransactionId!!,
                    locale = locale
                )

            validate(params)

            return params
        }

        private fun validate(params: GetActivityDetailsOperationParams) {
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
            offerToken = offerToken,
            partnerTransactionId = partnerTransactionId,
            locale = locale
        )

    override fun getHeaders(): Headers =
        Headers.build {
            partnerTransactionId?.let {
                append("Partner-Transaction-Id", it)
            }
            append("Accept", "application/vnd.exp-activity.v3+json")
        }

    override fun getQueryParams(): Parameters =
        Parameters.build {
            locale?.let {
                append("locale", it)
            }
        }

    override fun getPathParams(): Map<String, String> =
        buildMap {
            offerToken?.also {
                put("offerToken", offerToken)
            }
        }
}
