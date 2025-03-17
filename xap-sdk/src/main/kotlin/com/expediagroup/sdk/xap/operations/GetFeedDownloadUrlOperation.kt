package com.expediagroup.sdk.xap.operations

import com.expediagroup.sdk.rest.trait.operation.JacksonModelOperationResponseBodyTrait
import com.expediagroup.sdk.rest.trait.operation.OperationRequestTrait
import com.expediagroup.sdk.rest.trait.operation.UrlPathTrait
import com.expediagroup.sdk.rest.trait.operation.UrlQueryParamsTrait
import com.expediagroup.sdk.xap.models.PresignedUrlResponse
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef

/**
 *
 * @property params [GetFeedDownloadUrlOperationParams]
 */
class GetFeedDownloadUrlOperation(
    private val params: GetFeedDownloadUrlOperationParams,
) : OperationRequestTrait,
    UrlPathTrait,
    JacksonModelOperationResponseBodyTrait<PresignedUrlResponse>,
    UrlQueryParamsTrait {
    override fun getHttpMethod(): String = "GET"

    override fun getRequestInfo(): OperationRequestTrait = this

    override fun getUrlPath(): String {
        var url = "/feed/v1/download-url"

        return url
    }

    override fun getTypeIdentifier(): TypeReference<PresignedUrlResponse> = jacksonTypeRef()

    override fun getUrlQueryParams() = this.params.getQueryParams()
}
