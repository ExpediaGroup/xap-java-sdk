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
import com.expediagroup.sdk.xap.models.Fee
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Container for baggage fee information of each bag type. The baggage fee can vary for each bag type. The amount can be zero, fixed amount or will be in a range
 * @param bagType Specifies the type of baggage.
 * @param weightUnit Specifies weight unit for this bagType. This will always be kilograms.
 * @param weight Weight of the bag.
 * @param application This element specifies descriptive text useful in displaying baggage fees.
 * @param fixedCharge
 * @param maxCharge
 * @param minCharge
 */
data class BaggageFee(
    // Specifies the type of baggage.
    @JsonProperty("BagType")
    @field:NotNull
    val bagType: BaggageFee.BagType,
    // Specifies weight unit for this bagType. This will always be kilograms.
    @JsonProperty("WeightUnit")
    @field:Valid
    val weightUnit: kotlin.String? = null,
    // Weight of the bag.
    @JsonProperty("Weight")
    @field:Valid
    val weight: kotlin.String? = null,
    // This element specifies descriptive text useful in displaying baggage fees.
    @JsonProperty("Application")
    val application: BaggageFee.Application? = null,
    @JsonProperty("FixedCharge")
    @field:Valid
    val fixedCharge: Fee? = null,
    @JsonProperty("MaxCharge")
    @field:Valid
    val maxCharge: Fee? = null,
    @JsonProperty("MinCharge")
    @field:Valid
    val minCharge: Fee? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var bagType: BaggageFee.BagType? = null,
        private var weightUnit: kotlin.String? = null,
        private var weight: kotlin.String? = null,
        private var application: BaggageFee.Application? = null,
        private var fixedCharge: Fee? = null,
        private var maxCharge: Fee? = null,
        private var minCharge: Fee? = null
    ) {
        fun bagType(bagType: BaggageFee.BagType) = apply { this.bagType = bagType }

        fun weightUnit(weightUnit: kotlin.String?) = apply { this.weightUnit = weightUnit }

        fun weight(weight: kotlin.String?) = apply { this.weight = weight }

        fun application(application: BaggageFee.Application?) = apply { this.application = application }

        fun fixedCharge(fixedCharge: Fee?) = apply { this.fixedCharge = fixedCharge }

        fun maxCharge(maxCharge: Fee?) = apply { this.maxCharge = maxCharge }

        fun minCharge(minCharge: Fee?) = apply { this.minCharge = minCharge }

        fun build(): BaggageFee {
            val instance =
                BaggageFee(
                    bagType = bagType!!,
                    weightUnit = weightUnit,
                    weight = weight,
                    application = application,
                    fixedCharge = fixedCharge,
                    maxCharge = maxCharge,
                    minCharge = minCharge
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: BaggageFee) {
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
            bagType = bagType!!,
            weightUnit = weightUnit,
            weight = weight,
            application = application,
            fixedCharge = fixedCharge,
            maxCharge = maxCharge,
            minCharge = minCharge
        )

    /**
     * Specifies the type of baggage.
     * Values: PREPAID_CARRY_ON,PREPAID_FIRST_BAG,PREPAID_SECOND_BAG,CARRY_ON,FIRST_BAG,SECOND_BAG
     */
    enum class BagType(val value: kotlin.String) {
        @JsonProperty("PREPAID_CARRY_ON")
        PREPAID_CARRY_ON("PREPAID_CARRY_ON"),

        @JsonProperty("PREPAID_FIRST_BAG")
        PREPAID_FIRST_BAG("PREPAID_FIRST_BAG"),

        @JsonProperty("PREPAID_SECOND_BAG")
        PREPAID_SECOND_BAG("PREPAID_SECOND_BAG"),

        @JsonProperty("CARRY_ON")
        CARRY_ON("CARRY_ON"),

        @JsonProperty("FIRST_BAG")
        FIRST_BAG("FIRST_BAG"),

        @JsonProperty("SECOND_BAG")
        SECOND_BAG("SECOND_BAG")
    }

    /**
     * This element specifies descriptive text useful in displaying baggage fees.
     * Values: UPTO,PER
     */
    enum class Application(val value: kotlin.String) {
        @JsonProperty("upto")
        UPTO("upto"),

        @JsonProperty("per")
        PER("per")
    }
}
