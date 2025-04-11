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
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * List of image resources of the car product.
 * @param type Resource typeSupported values :Thumbnail - (70 pixels wide)Image - (165 pixels wide)
 * @param propertySize Size of imageSupported values :s - (165 pixels wide)t - (70 pixels wide)
 * @param href URL for the image.
 */
data class Image(
    // Resource typeSupported values :Thumbnail - (70 pixels wide)Image - (165 pixels wide)
    @JsonProperty("Type")
    @field:NotNull
    @field:Valid
    val type: kotlin.String,
    // Size of imageSupported values :s - (165 pixels wide)t - (70 pixels wide)
    @JsonProperty("Size")
    @field:NotNull
    @field:Valid
    val propertySize: kotlin.String,
    // URL for the image.
    @JsonProperty("Href")
    @field:NotNull
    @field:Valid
    val href: kotlin.String
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: kotlin.String? = null,
        private var propertySize: kotlin.String? = null,
        private var href: kotlin.String? = null
    ) {
        fun type(type: kotlin.String) = apply { this.type = type }

        fun propertySize(propertySize: kotlin.String) = apply { this.propertySize = propertySize }

        fun href(href: kotlin.String) = apply { this.href = href }

        fun build(): Image {
            val instance =
                Image(
                    type = type!!,
                    propertySize = propertySize!!,
                    href = href!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Image) {
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
            type = type!!,
            propertySize = propertySize!!,
            href = href!!
        )
}
