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
import com.expediagroup.sdk.xap.models.Amenity
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * Contains list of fare options
 * @param fareName Contains the name of the Fare tagged against the specific fare options.
 * @param segmentIds Contains list of segment Ids to which these FareOptions are provided.
 * @param included Contains list of options that are already included.
 * @param feeApplied Contains list of options that are fee applied.
 * @param notAvailable Contains list of options that are unavailable.
 * @param amenities
 */
data class FareOptions(
    // Contains the name of the Fare tagged against the specific fare options.
    @JsonProperty("FareName")
    @field:NotNull
    @field:Valid
    val fareName: kotlin.String,
    // Contains list of segment Ids to which these FareOptions are provided.
    @JsonProperty("SegmentIds")
    @field:NotNull
    @field:Valid
    val segmentIds: kotlin.collections
        .List<
            kotlin.String
        >,
    // Contains list of options that are already included.
    @JsonProperty("Included")
    @field:NotNull
    @field:Valid
    val included: kotlin.collections
        .List<
            kotlin.String
        >,
    // Contains list of options that are fee applied.
    @JsonProperty("FeeApplied")
    @field:NotNull
    @field:Valid
    val feeApplied: kotlin.collections
        .List<
            kotlin.String
        >,
    // Contains list of options that are unavailable.
    @JsonProperty("NotAvailable")
    @field:Valid
    val notAvailable: kotlin.collections.List<kotlin.String>? = null,
    @JsonProperty("Amenities")
    @field:Valid
    val amenities: Amenity? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var fareName: kotlin.String? = null,
        private var segmentIds: kotlin.collections.List<kotlin.String>? = null,
        private var included: kotlin.collections.List<kotlin.String>? = null,
        private var feeApplied: kotlin.collections.List<kotlin.String>? = null,
        private var notAvailable: kotlin.collections.List<kotlin.String>? = null,
        private var amenities: Amenity? = null
    ) {
        fun fareName(fareName: kotlin.String) = apply { this.fareName = fareName }

        fun segmentIds(segmentIds: kotlin.collections.List<kotlin.String>) = apply { this.segmentIds = segmentIds }

        fun included(included: kotlin.collections.List<kotlin.String>) = apply { this.included = included }

        fun feeApplied(feeApplied: kotlin.collections.List<kotlin.String>) = apply { this.feeApplied = feeApplied }

        fun notAvailable(notAvailable: kotlin.collections.List<kotlin.String>?) = apply { this.notAvailable = notAvailable }

        fun amenities(amenities: Amenity?) = apply { this.amenities = amenities }

        fun build(): FareOptions {
            val instance =
                FareOptions(
                    fareName = fareName!!,
                    segmentIds = segmentIds!!,
                    included = included!!,
                    feeApplied = feeApplied!!,
                    notAvailable = notAvailable,
                    amenities = amenities
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: FareOptions) {
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
            fareName = fareName!!,
            segmentIds = segmentIds!!,
            included = included!!,
            feeApplied = feeApplied!!,
            notAvailable = notAvailable,
            amenities = amenities
        )
}
