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
import com.expediagroup.sdk.xap.models.AvailabilityCalendarResponse
import com.expediagroup.sdk.xap.models.LodgingErrors


            import com.fasterxml.jackson.annotation.JsonProperty
            import com.fasterxml.jackson.databind.annotation.JsonDeserialize

            /**
                    * @property partnerTransactionId The `Partner-Transaction-ID` is a required API request header element that is <u>not</u> consumed by Expedia. It will be required in all XAP v3 API request headers and will be mirrored back to the partner in the corresponding API response header.  The `Partner-Transaction-ID` may be any alphanumeric string of the partner's choosing. 
                    * @property propertyIds Comma-separated list of Expedia Property IDs.  The API request supports a maximum of 50 Property IDs in a single request. 
            */
            @JsonDeserialize(builder = GetLodgingAvailabilityCalendarsOperationParams.Builder::class)
            data class GetLodgingAvailabilityCalendarsOperationParams(
                    val partnerTransactionId:     
    kotlin.String

    , 
                    val propertyIds:         kotlin.collections.Set
    <
        
        kotlin.String
    >

?
    = null

            ) {

            init {
                            require(partnerTransactionId != null) { "partnerTransactionId must not be null" }

















            }

            companion object {
            @JvmStatic
            fun builder() = Builder()
            }

            

            class Builder(
        @JsonProperty("Partner-Transaction-Id") private var partnerTransactionId:     
    kotlin.String
? = null
        ,
        @JsonProperty("propertyIds") private var propertyIds:         kotlin.collections.Set
    <
        
        kotlin.String
    >
? = null
        
) {
        /**
        * @param partnerTransactionId The `Partner-Transaction-ID` is a required API request header element that is <u>not</u> consumed by Expedia. It will be required in all XAP v3 API request headers and will be mirrored back to the partner in the corresponding API response header.  The `Partner-Transaction-ID` may be any alphanumeric string of the partner's choosing. 
        */
        fun partnerTransactionId(partnerTransactionId:     
    kotlin.String
) = apply { this.partnerTransactionId = partnerTransactionId }
        /**
        * @param propertyIds Comma-separated list of Expedia Property IDs.  The API request supports a maximum of 50 Property IDs in a single request. 
        */
        fun propertyIds(propertyIds:         kotlin.collections.Set
    <
        
        kotlin.String
    >
) = apply { this.propertyIds = propertyIds }

    fun build(): GetLodgingAvailabilityCalendarsOperationParams {
        val params = GetLodgingAvailabilityCalendarsOperationParams(
                partnerTransactionId = partnerTransactionId!!,
                propertyIds = propertyIds
        )

        return params
    }
}

fun toBuilder() = Builder(
        partnerTransactionId = partnerTransactionId,
        propertyIds = propertyIds
)


            fun getHeaders(): Headers {
return Headers.builder().apply {
    partnerTransactionId?.let {
    add("Partner-Transaction-Id", it)
    }
            add("Accept", "application/vnd.exp-lodging.v3+json")
}.build()
}

            fun getQueryParams(): List<UrlQueryParam> =
    buildList {
        propertyIds?.let {
        val key = "propertyIds"
        val value = buildList {
            addAll(it)
        }

        add(UrlQueryParam(
        key = key,
        value = value,
        stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", stringifyExplode)
        ))
        }
}

            
            }
