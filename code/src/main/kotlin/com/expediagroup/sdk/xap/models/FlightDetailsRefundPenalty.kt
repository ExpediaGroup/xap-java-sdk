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
import com.expediagroup.sdk.xap.models.RefundPenaltyDetail
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Contains refund penalty information
 * @param segmentIds Contains a list of segment Ids
 * @param preTripChange
 * @param preTripCancel
 * @param enrouteChange
 * @param enrouteCancel
 * @param transferable
 * @param nameChangeAllowed
 */
data class FlightDetailsRefundPenalty(
    // Contains a list of segment Ids
    @JsonProperty("SegmentIds")
    @field:NotNull
    @field:Valid
    val segmentIds: kotlin.collections
        .List<
            kotlin.String
        >,
    @JsonProperty("PreTripChange")
    @field:Valid
    val preTripChange: RefundPenaltyDetail? = null,
    @JsonProperty("PreTripCancel")
    @field:Valid
    val preTripCancel: RefundPenaltyDetail? = null,
    @JsonProperty("EnrouteChange")
    @field:Valid
    val enrouteChange: RefundPenaltyDetail? = null,
    @JsonProperty("EnrouteCancel")
    @field:Valid
    val enrouteCancel: RefundPenaltyDetail? = null,
    @JsonProperty("Transferable")
    val transferable: FlightDetailsRefundPenalty.Transferable? = null,
    @JsonProperty("NameChangeAllowed")
    val nameChangeAllowed: FlightDetailsRefundPenalty.NameChangeAllowed? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var segmentIds: kotlin.collections.List<kotlin.String>? = null,
        private var preTripChange: RefundPenaltyDetail? = null,
        private var preTripCancel: RefundPenaltyDetail? = null,
        private var enrouteChange: RefundPenaltyDetail? = null,
        private var enrouteCancel: RefundPenaltyDetail? = null,
        private var transferable: FlightDetailsRefundPenalty.Transferable? = null,
        private var nameChangeAllowed: FlightDetailsRefundPenalty.NameChangeAllowed? = null
    ) {
        fun segmentIds(segmentIds: kotlin.collections.List<kotlin.String>) = apply { this.segmentIds = segmentIds }

        fun preTripChange(preTripChange: RefundPenaltyDetail?) = apply { this.preTripChange = preTripChange }

        fun preTripCancel(preTripCancel: RefundPenaltyDetail?) = apply { this.preTripCancel = preTripCancel }

        fun enrouteChange(enrouteChange: RefundPenaltyDetail?) = apply { this.enrouteChange = enrouteChange }

        fun enrouteCancel(enrouteCancel: RefundPenaltyDetail?) = apply { this.enrouteCancel = enrouteCancel }

        fun transferable(transferable: FlightDetailsRefundPenalty.Transferable?) = apply { this.transferable = transferable }

        fun nameChangeAllowed(nameChangeAllowed: FlightDetailsRefundPenalty.NameChangeAllowed?) = apply { this.nameChangeAllowed = nameChangeAllowed }

        fun build(): FlightDetailsRefundPenalty {
            val instance =
                FlightDetailsRefundPenalty(
                    segmentIds = segmentIds!!,
                    preTripChange = preTripChange,
                    preTripCancel = preTripCancel,
                    enrouteChange = enrouteChange,
                    enrouteCancel = enrouteCancel,
                    transferable = transferable,
                    nameChangeAllowed = nameChangeAllowed
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FlightDetailsRefundPenalty) {
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
            segmentIds = segmentIds!!,
            preTripChange = preTripChange,
            preTripCancel = preTripCancel,
            enrouteChange = enrouteChange,
            enrouteCancel = enrouteCancel,
            transferable = transferable,
            nameChangeAllowed = nameChangeAllowed
        )

    /**
     *
     * Values: TRUE,FALSE,UNKNOWN
     */
    enum class Transferable(val value: kotlin.String) {
        @JsonProperty("YES")
        YES("YES"),

        @JsonProperty("NO")
        NO("NO"),

        @JsonProperty("UNKNOWN")
        UNKNOWN("UNKNOWN")
    }

    /**
     *
     * Values: TRUE,FALSE,UNKNOWN
     */
    enum class NameChangeAllowed(val value: kotlin.String) {
        @JsonProperty("YES")
        YES("YES"),

        @JsonProperty("NO")
        NO("NO"),

        @JsonProperty("UNKNOWN")
        UNKNOWN("UNKNOWN")
    }
}
