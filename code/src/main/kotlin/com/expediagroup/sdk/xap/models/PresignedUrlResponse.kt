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
import com.expediagroup.sdk.xap.models.Fault
import com.expediagroup.sdk.xap.models.FileInfo
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 *
 * @param transactionId Unique identifier for each API response.
 * @param error
 * @param warning
 * @param bestMatchedFile
 * @param otherFileOptions Container for file Pre-signed download URL and informations.
 */
data class PresignedUrlResponse(
    // Unique identifier for each API response.
    @JsonProperty("transactionId")
    @field:Valid
    val transactionId: kotlin.String? = null,
    @JsonProperty("error")
    @field:Valid
    val error: Fault? = null,
    @JsonProperty("warning")
    @field:Valid
    val warning: Fault? = null,
    @JsonProperty("bestMatchedFile")
    @field:Valid
    val bestMatchedFile: FileInfo? = null,
    // Container for file Pre-signed download URL and informations.
    @JsonProperty("otherFileOptions")
    @field:Valid
    val otherFileOptions: kotlin.collections.List<FileInfo>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var transactionId: kotlin.String? = null,
        private var error: Fault? = null,
        private var warning: Fault? = null,
        private var bestMatchedFile: FileInfo? = null,
        private var otherFileOptions: kotlin.collections.List<FileInfo>? = null
    ) {
        fun transactionId(transactionId: kotlin.String?) = apply { this.transactionId = transactionId }

        fun error(error: Fault?) = apply { this.error = error }

        fun warning(warning: Fault?) = apply { this.warning = warning }

        fun bestMatchedFile(bestMatchedFile: FileInfo?) = apply { this.bestMatchedFile = bestMatchedFile }

        fun otherFileOptions(otherFileOptions: kotlin.collections.List<FileInfo>?) = apply { this.otherFileOptions = otherFileOptions }

        fun build(): PresignedUrlResponse {
            val instance =
                PresignedUrlResponse(
                    transactionId = transactionId,
                    error = error,
                    warning = warning,
                    bestMatchedFile = bestMatchedFile,
                    otherFileOptions = otherFileOptions
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: PresignedUrlResponse) {
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
            transactionId = transactionId,
            error = error,
            warning = warning,
            bestMatchedFile = bestMatchedFile,
            otherFileOptions = otherFileOptions
        )
}
