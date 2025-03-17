package com.expediagroup.sdk.xap.operations

        import com.expediagroup.sdk.rest.trait.operation.OperationRequestTrait
        import com.expediagroup.sdk.rest.trait.operation.UrlPathTrait
        
            import com.fasterxml.jackson.core.type.TypeReference
            import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
            import com.expediagroup.sdk.rest.trait.operation.JacksonModelOperationResponseBodyTrait
        
        
            import com.expediagroup.sdk.rest.trait.operation.UrlQueryParamsTrait
            import com.expediagroup.sdk.rest.model.UrlQueryParam
            import com.expediagroup.sdk.rest.util.swaggerCollectionFormatStringifier
            import com.expediagroup.sdk.rest.util.stringifyExplode
            import com.expediagroup.sdk.rest.trait.operation.HeadersTrait
            import com.expediagroup.sdk.core.http.Headers


    import com.expediagroup.sdk.xap.models.APIGatewayError
    import com.expediagroup.sdk.xap.models.Errors
    import com.expediagroup.sdk.xap.models.HotelDetailsResponse

        /**
        * Get Extended information with a single property offer
            * @property params [GetLodgingDetailsOperationParams]
        */
        class GetLodgingDetailsOperation(
            private val params: GetLodgingDetailsOperationParams,
        ) : OperationRequestTrait,
UrlPathTrait

, JacksonModelOperationResponseBodyTrait<HotelDetailsResponse>


, UrlQueryParamsTrait
, HeadersTrait {
        override fun getHttpMethod(): String = "GET"

override fun getRequestInfo(): OperationRequestTrait = this

override fun getUrlPath(): String {
var url = "/hotels/details/{offerToken}"


    url = url.replace(
    oldValue = "{" + "offerToken" + "}",
    newValue = this.params.offerToken,
    ignoreCase = true
    )


return url
}



    override fun getTypeIdentifier(): TypeReference<HotelDetailsResponse> = jacksonTypeRef()

    override fun getHeaders(): Headers = this.params.getHeaders()


    override fun getUrlQueryParams() = this.params.getQueryParams()

        }
