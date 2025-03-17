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


            import com.fasterxml.jackson.annotation.JsonProperty
            import com.fasterxml.jackson.databind.annotation.JsonDeserialize

            /**
                    * @property partnerTransactionId The `Partner-Transaction-ID` is a required API request header element that is <u>not</u> consumed by Expedia. It will be required in all XAP v3 API request headers and will be mirrored back to the partner in the corresponding API response header.  The `Partner-Transaction-ID` may be any alphanumeric string of the partner's choosing. 
                    * @property ecomHotelId The Expedia hotel ID for which the calendar is being requested.  **Note**: Either an Expedia Hotel ID or a Hotels.com Hotel ID must be included in the request. You may use one or the other, but not both. 
                    * @property hcomHotelId The Hotel.com hotel ID for which the calendar is being requested.  **Note**: Either an Expedia Hotel ID or a Hotels.com Hotel ID must be included in the request. You may use one or the other, but not both. 
                    * @property startDate Start date for check-in search range in an ISO 8601 Date format [YYYY-MM-DD].  **Note**: The start date may not be in the past. 
                    * @property endDate End date for check-in search range in an ISO 8601 Date format [YYYY-MM-DD].  **Note**: The end date must be after the start date. The maximum supported search range is 180 days. 
                    * @property lengthOfStay The length of stay to retrieve the lowest price for.
                    * @property currency The requested currency expressed according to ISO 4217.
            */
            @JsonDeserialize(builder = GetLodgingRateCalendarOperationParams.Builder::class)
            data class GetLodgingRateCalendarOperationParams(
                    val partnerTransactionId:     
    kotlin.String

    , 
                    val ecomHotelId:     
    kotlin.String

?
    = null
, 
                    val hcomHotelId:     
    kotlin.String

?
    = null
, 
                    val startDate:     
    java.time.LocalDate

    , 
                    val endDate:     
    java.time.LocalDate

    , 
                    val lengthOfStay:     
    kotlin.Int

?
 =
        1
, 
                    val currency:     
    kotlin.String

?
    = null

            ) {

            init {
                            require(partnerTransactionId != null) { "partnerTransactionId must not be null" }
























            require(startDate != null) { "startDate must not be null" }








            require(endDate != null) { "endDate must not be null" }

























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
        @JsonProperty("ecomHotelId") private var ecomHotelId:     
    kotlin.String
? = null
        ,
        @JsonProperty("hcomHotelId") private var hcomHotelId:     
    kotlin.String
? = null
        ,
        @JsonProperty("startDate") private var startDate:     
    java.time.LocalDate
? = null
        ,
        @JsonProperty("endDate") private var endDate:     
    java.time.LocalDate
? = null
        ,
        @JsonProperty("lengthOfStay") private var lengthOfStay:     
    kotlin.Int
? = null
        ,
        @JsonProperty("currency") private var currency:     
    kotlin.String
? = null
        
) {
        /**
        * @param partnerTransactionId The `Partner-Transaction-ID` is a required API request header element that is <u>not</u> consumed by Expedia. It will be required in all XAP v3 API request headers and will be mirrored back to the partner in the corresponding API response header.  The `Partner-Transaction-ID` may be any alphanumeric string of the partner's choosing. 
        */
        fun partnerTransactionId(partnerTransactionId:     
    kotlin.String
) = apply { this.partnerTransactionId = partnerTransactionId }
        /**
        * @param ecomHotelId The Expedia hotel ID for which the calendar is being requested.  **Note**: Either an Expedia Hotel ID or a Hotels.com Hotel ID must be included in the request. You may use one or the other, but not both. 
        */
        fun ecomHotelId(ecomHotelId:     
    kotlin.String
) = apply { this.ecomHotelId = ecomHotelId }
        /**
        * @param hcomHotelId The Hotel.com hotel ID for which the calendar is being requested.  **Note**: Either an Expedia Hotel ID or a Hotels.com Hotel ID must be included in the request. You may use one or the other, but not both. 
        */
        fun hcomHotelId(hcomHotelId:     
    kotlin.String
) = apply { this.hcomHotelId = hcomHotelId }
        /**
        * @param startDate Start date for check-in search range in an ISO 8601 Date format [YYYY-MM-DD].  **Note**: The start date may not be in the past. 
        */
        fun startDate(startDate:     
    java.time.LocalDate
) = apply { this.startDate = startDate }
        /**
        * @param endDate End date for check-in search range in an ISO 8601 Date format [YYYY-MM-DD].  **Note**: The end date must be after the start date. The maximum supported search range is 180 days. 
        */
        fun endDate(endDate:     
    java.time.LocalDate
) = apply { this.endDate = endDate }
        /**
        * @param lengthOfStay The length of stay to retrieve the lowest price for.
        */
        fun lengthOfStay(lengthOfStay:     
    kotlin.Int
) = apply { this.lengthOfStay = lengthOfStay }
        /**
        * @param currency The requested currency expressed according to ISO 4217.
        */
        fun currency(currency:     
    kotlin.String
) = apply { this.currency = currency }

    fun build(): GetLodgingRateCalendarOperationParams {
        val params = GetLodgingRateCalendarOperationParams(
                partnerTransactionId = partnerTransactionId!!,
                ecomHotelId = ecomHotelId,
                hcomHotelId = hcomHotelId,
                startDate = startDate!!,
                endDate = endDate!!,
                lengthOfStay = lengthOfStay,
                currency = currency
        )

        return params
    }
}

fun toBuilder() = Builder(
        partnerTransactionId = partnerTransactionId,
        ecomHotelId = ecomHotelId,
        hcomHotelId = hcomHotelId,
        startDate = startDate,
        endDate = endDate,
        lengthOfStay = lengthOfStay,
        currency = currency
)


            fun getHeaders(): Headers {
return Headers.builder().apply {
    partnerTransactionId?.let {
    add("Partner-Transaction-Id", it)
    }
            add("Accept", "application/vnd.exp-hotel.v3+json")
}.build()
}

            fun getQueryParams(): List<UrlQueryParam> =
    buildList {
        ecomHotelId?.let {
        val key = "ecomHotelId"
        val value = buildList {
            add(it)
        }

        add(UrlQueryParam(
        key = key,
        value = value,
        stringify = swaggerCollectionFormatStringifier.getOrDefault("", stringifyExplode)
        ))
        }
        hcomHotelId?.let {
        val key = "hcomHotelId"
        val value = buildList {
            add(it)
        }

        add(UrlQueryParam(
        key = key,
        value = value,
        stringify = swaggerCollectionFormatStringifier.getOrDefault("", stringifyExplode)
        ))
        }
        startDate?.let {
        val key = "startDate"
        val value = buildList {
            add(it.toString())
        }

        add(UrlQueryParam(
        key = key,
        value = value,
        stringify = swaggerCollectionFormatStringifier.getOrDefault("", stringifyExplode)
        ))
        }
        endDate?.let {
        val key = "endDate"
        val value = buildList {
            add(it.toString())
        }

        add(UrlQueryParam(
        key = key,
        value = value,
        stringify = swaggerCollectionFormatStringifier.getOrDefault("", stringifyExplode)
        ))
        }
        lengthOfStay?.let {
        val key = "lengthOfStay"
        val value = buildList {
            add(it.toString())
        }

        add(UrlQueryParam(
        key = key,
        value = value,
        stringify = swaggerCollectionFormatStringifier.getOrDefault("", stringifyExplode)
        ))
        }
        currency?.let {
        val key = "currency"
        val value = buildList {
            add(it)
        }

        add(UrlQueryParam(
        key = key,
        value = value,
        stringify = swaggerCollectionFormatStringifier.getOrDefault("", stringifyExplode)
        ))
        }
}

            
            }
