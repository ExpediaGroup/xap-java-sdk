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
import com.expediagroup.sdk.xap.models.CancellationPenaltyRule
import com.expediagroup.sdk.xap.models.NonRefundableDateRange
import com.expediagroup.sdk.xap.models.WaiverPolicy
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * Container for room cancellation policy.
 * @param waiverPolicy
 * @param cancellableOnline Boolean value to identify if the reservation can be cancelled online. If false, the customer will only be able to cancel a refundable room by calling Expedia Customer Service.
 * @param refundable Indicate whether the rate is refundable or not.
 * @param freeCancellation Indicate whether the room can be cancelled free of charge.
 * @param freeCancellationEndDateTime The date and time until which the room can be cancelled free of charge.  This is expressed in the local time of the Hotel.
 * @param cancellationPenaltyRules Container for cancellation penalty details.
 * @param cancelPolicyDescription Additional cancellation policy information available as static text.
 * @param nonRefundableDateRanges A list of dates ranges that are non-refundable.  **Note**: The stay dates in those date ranges will always be charged whenever there is any cancellation  penalty rule.
 */
data class CancellationPolicy(
    @JsonProperty("WaiverPolicy")
    @field:Valid
    val waiverPolicy: WaiverPolicy? = null,
    // Boolean value to identify if the reservation can be cancelled online. If false, the customer will only be able to cancel a refundable room by calling Expedia Customer Service.
    @JsonProperty("CancellableOnline")
    @field:Valid
    val cancellableOnline: kotlin.Boolean? = null,
    // Indicate whether the rate is refundable or not.
    @JsonProperty("Refundable")
    @field:Valid
    val refundable: kotlin.Boolean? = null,
    // Indicate whether the room can be cancelled free of charge.
    @JsonProperty("FreeCancellation")
    @field:Valid
    val freeCancellation: kotlin.Boolean? = null,
    // The date and time until which the room can be cancelled free of charge.  This is expressed in the local time of the Hotel.
    @JsonProperty("FreeCancellationEndDateTime")
    val freeCancellationEndDateTime: java.time.OffsetDateTime? = null,
    // Container for cancellation penalty details.
    @JsonProperty("CancellationPenaltyRules")
    @field:Valid
    val cancellationPenaltyRules: kotlin.collections.List<CancellationPenaltyRule>? = null,
    // Additional cancellation policy information available as static text.
    @JsonProperty("CancelPolicyDescription")
    @field:Valid
    val cancelPolicyDescription: kotlin.String? = null,
    // A list of dates ranges that are non-refundable.  **Note**: The stay dates in those date ranges will always be charged whenever there is any cancellation  penalty rule.
    @JsonProperty("NonRefundableDateRanges")
    @field:Valid
    val nonRefundableDateRanges: kotlin.collections.List<NonRefundableDateRange>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var waiverPolicy: WaiverPolicy? = null,
        private var cancellableOnline: kotlin.Boolean? = null,
        private var refundable: kotlin.Boolean? = null,
        private var freeCancellation: kotlin.Boolean? = null,
        private var freeCancellationEndDateTime: java.time.OffsetDateTime? = null,
        private var cancellationPenaltyRules: kotlin.collections.List<CancellationPenaltyRule>? = null,
        private var cancelPolicyDescription: kotlin.String? = null,
        private var nonRefundableDateRanges: kotlin.collections.List<NonRefundableDateRange>? = null
    ) {
        fun waiverPolicy(waiverPolicy: WaiverPolicy?) = apply { this.waiverPolicy = waiverPolicy }

        fun cancellableOnline(cancellableOnline: kotlin.Boolean?) = apply { this.cancellableOnline = cancellableOnline }

        fun refundable(refundable: kotlin.Boolean?) = apply { this.refundable = refundable }

        fun freeCancellation(freeCancellation: kotlin.Boolean?) = apply { this.freeCancellation = freeCancellation }

        fun freeCancellationEndDateTime(freeCancellationEndDateTime: java.time.OffsetDateTime?) = apply { this.freeCancellationEndDateTime = freeCancellationEndDateTime }

        fun cancellationPenaltyRules(cancellationPenaltyRules: kotlin.collections.List<CancellationPenaltyRule>?) = apply { this.cancellationPenaltyRules = cancellationPenaltyRules }

        fun cancelPolicyDescription(cancelPolicyDescription: kotlin.String?) = apply { this.cancelPolicyDescription = cancelPolicyDescription }

        fun nonRefundableDateRanges(nonRefundableDateRanges: kotlin.collections.List<NonRefundableDateRange>?) = apply { this.nonRefundableDateRanges = nonRefundableDateRanges }

        fun build(): CancellationPolicy {
            val instance =
                CancellationPolicy(
                    waiverPolicy = waiverPolicy,
                    cancellableOnline = cancellableOnline,
                    refundable = refundable,
                    freeCancellation = freeCancellation,
                    freeCancellationEndDateTime = freeCancellationEndDateTime,
                    cancellationPenaltyRules = cancellationPenaltyRules,
                    cancelPolicyDescription = cancelPolicyDescription,
                    nonRefundableDateRanges = nonRefundableDateRanges
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: CancellationPolicy) {
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
            waiverPolicy = waiverPolicy,
            cancellableOnline = cancellableOnline,
            refundable = refundable,
            freeCancellation = freeCancellation,
            freeCancellationEndDateTime = freeCancellationEndDateTime,
            cancellationPenaltyRules = cancellationPenaltyRules,
            cancelPolicyDescription = cancelPolicyDescription,
            nonRefundableDateRanges = nonRefundableDateRanges
        )
}
