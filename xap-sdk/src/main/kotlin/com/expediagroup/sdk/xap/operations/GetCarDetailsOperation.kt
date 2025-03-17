package com.expediagroup.sdk.xap.operations

import com.expediagroup.sdk.core.http.Headers
import com.expediagroup.sdk.rest.trait.operation.HeadersTrait
import com.expediagroup.sdk.rest.trait.operation.JacksonModelOperationResponseBodyTrait
import com.expediagroup.sdk.rest.trait.operation.OperationRequestTrait
import com.expediagroup.sdk.rest.trait.operation.UrlPathTrait
import com.expediagroup.sdk.rest.trait.operation.UrlQueryParamsTrait
import com.expediagroup.sdk.xap.models.CarDetailsResponse
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef

/**
 * Get Extended information with a single car offer
 * @property params [GetCarDetailsOperationParams]
 */
class GetCarDetailsOperation(
    private val params: GetCarDetailsOperationParams,
) : OperationRequestTrait,
    UrlPathTrait,
    JacksonModelOperationResponseBodyTrait<CarDetailsResponse>,
    UrlQueryParamsTrait,
    HeadersTrait {
    override fun getHttpMethod(): String = "GET"

    override fun getRequestInfo(): OperationRequestTrait = this

    override fun getUrlPath(): String {
        var url = "/cars/details/{offerToken}"

        url =
            url.replace(
                oldValue = "{" + "offerToken" + "}",
                newValue = this.params.offerToken,
                ignoreCase = true,
            )

        return url
    }

    override fun getTypeIdentifier(): TypeReference<CarDetailsResponse> = jacksonTypeRef()

    override fun getHeaders(): Headers = this.params.getHeaders()

    override fun getUrlQueryParams() = this.params.getQueryParams()
}
