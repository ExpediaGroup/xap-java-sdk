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
package com.expediagroup.sdk.xap.operations

import com.expediagroup.sdk.core.http.Headers
import com.expediagroup.sdk.rest.trait.operation.HeadersTrait
import com.expediagroup.sdk.rest.trait.operation.JacksonModelOperationResponseBodyTrait
import com.expediagroup.sdk.rest.trait.operation.OperationRequestTrait
import com.expediagroup.sdk.rest.trait.operation.UrlPathTrait
import com.expediagroup.sdk.rest.trait.operation.UrlQueryParamsTrait
import com.expediagroup.sdk.xap.model.ActivityDetailsResponse
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef

/**
 *
 * @property params [GetActivityDetailsOperationParams]
 */
class GetActivityDetailsOperation(
    private val params: GetActivityDetailsOperationParams,
) : OperationRequestTrait,
    UrlPathTrait,
    JacksonModelOperationResponseBodyTrait<ActivityDetailsResponse>,
    UrlQueryParamsTrait,
    HeadersTrait {
    override fun getHttpMethod(): String = "GET"

    override fun getRequestInfo(): OperationRequestTrait = this

    override fun getUrlPath(): String {
        var url = "/activities/details/{offerToken}"

        url =
            url.replace(
                oldValue = "{" + "offerToken" + "}",
                newValue = this.params.offerToken,
                ignoreCase = true,
            )

        return url
    }

    override fun getTypeIdentifier(): TypeReference<ActivityDetailsResponse> = jacksonTypeRef()

    override fun getHeaders(): Headers = this.params.getHeaders()

    override fun getUrlQueryParams() = this.params.getQueryParams()
}
