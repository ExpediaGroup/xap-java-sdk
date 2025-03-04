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

import com.expediagroup.sdk.rest.exception.client.PropertyConstraintViolationException
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
* The chain and brand information of hotel.  Only visible by configuration. Please contact your Expedia Account Manager if you need this node.
 * @param chainId The chain id.
 * @param chainName The name of the chain.
 * @param brandId The brand id.
 * @param brandName The name of the brand.
*/
data class ChainAndBrandInfo(
    // The chain id.
    @JsonProperty("ChainId")
    val chainId: kotlin.Int? = null,
    // The name of the chain.
    @JsonProperty("ChainName")
    @field:Valid
    val chainName: kotlin.String? = null,
    // The brand id.
    @JsonProperty("BrandId")
    val brandId: kotlin.Int? = null,
    // The name of the brand.
    @JsonProperty("BrandName")
    @field:Valid
    val brandName: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var chainId: kotlin.Int? = null,
        private var chainName: kotlin.String? = null,
        private var brandId: kotlin.Int? = null,
        private var brandName: kotlin.String? = null
    ) {
        fun chainId(chainId: kotlin.Int?) = apply { this.chainId = chainId }

        fun chainName(chainName: kotlin.String?) = apply { this.chainName = chainName }

        fun brandId(brandId: kotlin.Int?) = apply { this.brandId = brandId }

        fun brandName(brandName: kotlin.String?) = apply { this.brandName = brandName }

        fun build(): ChainAndBrandInfo {
            val instance =
                ChainAndBrandInfo(
                    chainId = chainId,
                    chainName = chainName,
                    brandId = brandId,
                    brandName = brandName
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: ChainAndBrandInfo) {
            val validator =
                Validation
                    .byDefaultProvider()
                    .configure()
                    .messageInterpolator(ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .validator

            val violations = validator.validate(instance)

            if (violations.isNotEmpty()) {
                throw PropertyConstraintViolationException(
                    constraintViolations = violations.map { "${it.propertyPath}: ${it.message}" }
                )
            }
        }
    }

    fun toBuilder() =
        Builder(
            chainId = chainId,
            chainName = chainName,
            brandId = brandId,
            brandName = brandName
        )
}
