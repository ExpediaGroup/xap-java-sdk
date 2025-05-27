/**
 * Copyright (C) 2025 Expedia, Inc.
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
package com.expediagroup.sdk.xap.model

import com.expediagroup.sdk.xap.model.Fault
import com.expediagroup.sdk.xap.model.FileInfo
import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param transactionId Unique identifier for each API response.
 * @param error
 * @param warning
 * @param bestMatchedFile
 * @param otherFileOptions Container for file Pre-signed download URL and informations.
 */
@ConsistentCopyVisibility data class PresignedUrlResponse private constructor(
    /* Unique identifier for each API response. */
    @JsonProperty("transactionId")
    val transactionId: kotlin.String? = null,

    @JsonProperty("error")
    val error: Fault? = null,

    @JsonProperty("warning")
    val warning: Fault? = null,

    @JsonProperty("bestMatchedFile")
    val bestMatchedFile: FileInfo? = null,

    /* Container for file Pre-signed download URL and informations. */
    @JsonProperty("otherFileOptions")
    val otherFileOptions: kotlin.collections.List<FileInfo>? = null,
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
        private var otherFileOptions: kotlin.collections.List<FileInfo>? = null,
    ) {
        fun transactionId(transactionId: kotlin.String?) = apply { this.transactionId = transactionId }

        fun error(error: Fault?) = apply { this.error = error }

        fun warning(warning: Fault?) = apply { this.warning = warning }

        fun bestMatchedFile(bestMatchedFile: FileInfo?) = apply { this.bestMatchedFile = bestMatchedFile }

        fun otherFileOptions(otherFileOptions: kotlin.collections.List<FileInfo>?) = apply { this.otherFileOptions = otherFileOptions }

        fun build(): PresignedUrlResponse {
            val instance = PresignedUrlResponse(
                transactionId = transactionId,
                error = error,
                warning = warning,
                bestMatchedFile = bestMatchedFile,
                otherFileOptions = otherFileOptions,
            )

            return instance
        }
    }

    fun toBuilder() = Builder(
        transactionId = transactionId,
        error = error,
        warning = warning,
        bestMatchedFile = bestMatchedFile,
        otherFileOptions = otherFileOptions,
    )
}
