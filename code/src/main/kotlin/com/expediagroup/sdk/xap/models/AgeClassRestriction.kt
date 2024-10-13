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
/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.expediagroup.sdk.xap.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * Container for room occupancy rules based on the age of the guests.
 * @param ageClass Categories for hotel guests, based on age.
 * @param ageMinimum The minimum age defined in a particular `AgeClass`.
 * @param ageMaximum The maximum age defined in a particular `AgeClass`.  If not specified, the `AgeClass` has no upper bound.
 * @param maxGuestCount The max guest count allowed in a particular `AgeClass`.
 */
data class AgeClassRestriction(
    // Categories for hotel guests, based on age.
    @JsonProperty("AgeClass")
    val ageClass: AgeClassRestriction.AgeClass? = null,
    // The minimum age defined in a particular `AgeClass`.
    @JsonProperty("AgeMinimum")
    val ageMinimum: kotlin.Int? = null,
    // The maximum age defined in a particular `AgeClass`.  If not specified, the `AgeClass` has no upper bound.
    @JsonProperty("AgeMaximum")
    val ageMaximum: kotlin.Int? = null,
    // The max guest count allowed in a particular `AgeClass`.
    @JsonProperty("MaxGuestCount")
    val maxGuestCount: kotlin.Int? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var ageClass: AgeClassRestriction.AgeClass? = null,
        private var ageMinimum: kotlin.Int? = null,
        private var ageMaximum: kotlin.Int? = null,
        private var maxGuestCount: kotlin.Int? = null
    ) {
        fun ageClass(ageClass: AgeClassRestriction.AgeClass?) = apply { this.ageClass = ageClass }

        fun ageMinimum(ageMinimum: kotlin.Int?) = apply { this.ageMinimum = ageMinimum }

        fun ageMaximum(ageMaximum: kotlin.Int?) = apply { this.ageMaximum = ageMaximum }

        fun maxGuestCount(maxGuestCount: kotlin.Int?) = apply { this.maxGuestCount = maxGuestCount }

        fun build(): AgeClassRestriction {
            return AgeClassRestriction(
                ageClass = ageClass,
                ageMinimum = ageMinimum,
                ageMaximum = ageMaximum,
                maxGuestCount = maxGuestCount
            )
        }
    }

    fun toBuilder() =
        Builder(
            ageClass = ageClass,
            ageMinimum = ageMinimum,
            ageMaximum = ageMaximum,
            maxGuestCount = maxGuestCount
        )

    /**
     * Categories for hotel guests, based on age.
     * Values: ALL_AGES,SENIOR,ADULT,CHILD,INFANT,OTHER
     */
    enum class AgeClass(val value: kotlin.String) {
        @JsonProperty("All Ages")
        ALL_AGES("All Ages"),

        @JsonProperty("Senior")
        SENIOR("Senior"),

        @JsonProperty("Adult")
        ADULT("Adult"),

        @JsonProperty("Child")
        CHILD("Child"),

        @JsonProperty("Infant")
        INFANT("Infant"),

        @JsonProperty("Other")
        OTHER("Other")
    }
}
