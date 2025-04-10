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

import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.FlightsV3Link
import com.expediagroup.sdk.xap.models.PackagePrice
import com.expediagroup.sdk.xap.models.PackagedOffer
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container for information about the hotel used in this package offer.
 * @param price
 * @param links Container for HATEOAS links to websites and/or API's.
 * @param packagedOffer
 */
data class PackageOffers(
    @JsonProperty("Price")
    @field:NotNull
    @field:Valid
    val price: PackagePrice,
    // Container for HATEOAS links to websites and/or API's.
    @JsonProperty("Links")
    @field:NotNull
    @field:Valid
    val links: kotlin.collections.Map<kotlin.String, FlightsV3Link>,
    @JsonProperty("PackagedOffer")
    @field:NotNull
    @field:Valid
    val packagedOffer: PackagedOffer
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var price: PackagePrice? = null,
        private var links: kotlin.collections.Map<kotlin.String, FlightsV3Link>? = null,
        private var packagedOffer: PackagedOffer? = null
    ) {
        fun price(price: PackagePrice) = apply { this.price = price }

        fun links(links: kotlin.collections.Map<kotlin.String, FlightsV3Link>) = apply { this.links = links }

        fun packagedOffer(packagedOffer: PackagedOffer) = apply { this.packagedOffer = packagedOffer }

        fun build(): PackageOffers {
            val instance =
                PackageOffers(
                    price = price!!,
                    links = links!!,
                    packagedOffer = packagedOffer!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: PackageOffers) {
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
            price = price!!,
            links = links!!,
            packagedOffer = packagedOffer!!
        )
}
