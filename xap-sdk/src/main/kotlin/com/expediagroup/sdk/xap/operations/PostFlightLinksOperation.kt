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
import com.expediagroup.sdk.rest.trait.operation.ContentTypeTrait
import com.expediagroup.sdk.rest.trait.operation.HeadersTrait
import com.expediagroup.sdk.rest.trait.operation.JacksonModelOperationResponseBodyTrait
import com.expediagroup.sdk.rest.trait.operation.OperationRequestBodyTrait
import com.expediagroup.sdk.rest.trait.operation.OperationRequestTrait
import com.expediagroup.sdk.rest.trait.operation.UrlPathTrait
import com.expediagroup.sdk.xap.models.FlightLinksRequest
import com.expediagroup.sdk.xap.models.FlightLinksResponse
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef

/**
 * Request for flight links
 * @property requestBody [FlightLinksRequest]
 * @property params [PostFlightLinksOperationParams]
 */
class PostFlightLinksOperation(
    private val params: PostFlightLinksOperationParams,
    private val requestBody: FlightLinksRequest,
) : OperationRequestTrait,
    UrlPathTrait,
    OperationRequestBodyTrait<FlightLinksRequest>,
    JacksonModelOperationResponseBodyTrait<FlightLinksResponse>,
    ContentTypeTrait,
    HeadersTrait {
    override fun getHttpMethod(): String = "POST"

    override fun getRequestInfo(): OperationRequestTrait = this

    override fun getUrlPath(): String {
        var url = "/flights/links"

        return url
    }

    override fun getRequestBody(): FlightLinksRequest = requestBody

    override fun getTypeIdentifier(): TypeReference<FlightLinksResponse> = jacksonTypeRef()

    override fun getHeaders(): Headers = this.params.getHeaders()

    override fun getContentType(): String = "application/json"
}
