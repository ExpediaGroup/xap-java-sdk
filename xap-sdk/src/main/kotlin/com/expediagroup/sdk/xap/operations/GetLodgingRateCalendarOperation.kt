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
    import com.expediagroup.sdk.xap.models.RateCalendarResponse

        /**
        * Get rate calendar of a property
            * @property params [GetLodgingRateCalendarOperationParams]
        */
        class GetLodgingRateCalendarOperation(
            private val params: GetLodgingRateCalendarOperationParams,
        ) : OperationRequestTrait,
UrlPathTrait

, JacksonModelOperationResponseBodyTrait<RateCalendarResponse>


, UrlQueryParamsTrait
, HeadersTrait {
        override fun getHttpMethod(): String = "GET"

override fun getRequestInfo(): OperationRequestTrait = this

override fun getUrlPath(): String {
var url = "/hotels/rateCalendar"



return url
}



    override fun getTypeIdentifier(): TypeReference<RateCalendarResponse> = jacksonTypeRef()

    override fun getHeaders(): Headers = this.params.getHeaders()


    override fun getUrlQueryParams() = this.params.getQueryParams()

        }
