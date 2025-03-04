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
import com.expediagroup.sdk.xap.models.CarDetails
import com.expediagroup.sdk.xap.models.CarsLink
import com.expediagroup.sdk.xap.models.CarsValidFormsOfPayment
import com.expediagroup.sdk.xap.models.CarsWarning
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
*
 * @param transactionId A unique identifier for this transaction.
 * @param warnings Container for warning codes
 * @param carDetails
 * @param validFormsOfPayment List of all the forms of payment that will be accepted for the booking of this rental transaction.
 * @param links A map of links to other Car APIs. possible link name: ApiBooking
*/
data class CarDetailsResponse(
    // A unique identifier for this transaction.
    @JsonProperty("TransactionId")
    @field:NotNull
    @field:Valid
    val transactionId: kotlin.String,
    // Container for warning codes
    @JsonProperty("Warnings")
    @field:Valid
    val warnings: kotlin.collections.List<CarsWarning>? = null,
    @JsonProperty("CarDetails")
    @field:Valid
    val carDetails: CarDetails? = null,
    // List of all the forms of payment that will be accepted for the booking of this rental transaction.
    @JsonProperty("ValidFormsOfPayment")
    @field:Valid
    val validFormsOfPayment: kotlin.collections.List<CarsValidFormsOfPayment>? = null,
    // A map of links to other Car APIs. possible link name: ApiBooking
    @JsonProperty("Links")
    @field:Valid
    val links: kotlin.collections.Map<kotlin.String, CarsLink>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var transactionId: kotlin.String? = null,
        private var warnings: kotlin.collections.List<CarsWarning>? = null,
        private var carDetails: CarDetails? = null,
        private var validFormsOfPayment: kotlin.collections.List<CarsValidFormsOfPayment>? = null,
        private var links: kotlin.collections.Map<kotlin.String, CarsLink>? = null
    ) {
        fun transactionId(transactionId: kotlin.String) = apply { this.transactionId = transactionId }

        fun warnings(warnings: kotlin.collections.List<CarsWarning>?) = apply { this.warnings = warnings }

        fun carDetails(carDetails: CarDetails?) = apply { this.carDetails = carDetails }

        fun validFormsOfPayment(validFormsOfPayment: kotlin.collections.List<CarsValidFormsOfPayment>?) = apply { this.validFormsOfPayment = validFormsOfPayment }

        fun links(links: kotlin.collections.Map<kotlin.String, CarsLink>?) = apply { this.links = links }

        fun build(): CarDetailsResponse {
            val instance =
                CarDetailsResponse(
                    transactionId = transactionId!!,
                    warnings = warnings,
                    carDetails = carDetails,
                    validFormsOfPayment = validFormsOfPayment,
                    links = links
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: CarDetailsResponse) {
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
            transactionId = transactionId!!,
            warnings = warnings,
            carDetails = carDetails,
            validFormsOfPayment = validFormsOfPayment,
            links = links
        )
}
