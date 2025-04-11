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
package com.expediagroup.sdk.xap.operations

import com.expediagroup.sdk.core.model.OperationParams
import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.infrastructure.*
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.ktor.http.Headers
import io.ktor.http.Parameters
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * @property offerToken A Hotel Natural Key from the Lodging Search API -> Hotels -> RoomTypes -> OfferId. It is a concatenated string of multiple values that defines a hotel offer.
 * @property partnerTransactionId The `Partner-Transaction-ID` is a required API request header element that is <u>not</u> consumed by Expedia. It will be required in all XAP v3 API request headers and will be mirrored back to the partner in the corresponding API response header.  The `Partner-Transaction-ID` may be any alphanumeric string of the partner's choosing.
 * @property price The total price of the selected property.  This value will be used to determine if there is a price change between the details and booking request.  The `price` element will be automatically included in the ApiDetails HATEOAS link which is returned along with the Lodging Details API response.
 * @property currency Value should be a standard ISO 3 letter currency code.  The currency code that is associated to the `TotalPrice` element for the selected property's rate plan from the Lodging Listing API response.  The `currency` element will be automatically included in the ApiDetails HATEOAS link which is returned along with the Lodging Details response.
 * @property locale `locale` is composed of language identifier and region identifier, connected by \"_\" that specifies the language in which the response will be returned.  The `locale` value used in the Lodging Details API query should match the `locale` value that was used in the Lodging Search API query.
 * @property imageSizes Indicate what size of image will be returned.  The available image sizes are: - t : Thumbnail - s : Small - b : Big - y : 500x500v - z : 1000x1000v  Resolution from smallest to largest is t < s < b < y < z.  If no `imageSizes` is specified, the t size images will be returned.  If an image is missing in one size, we will try to fall back to the same image with lower resolution.  (If image \"_z\" is not available, we will try to return image \"_y\", and if it is also unavailable, we will return image \"_b\", and so on.)  Only one image size is allowed.
 * @property groupedAmenities Specifies whether to return the grouped amenities. If `groupedAmenities` is true, `DescriptiveAmenities` node will be returned in response, otherwise `Amenities` node will be returned.
 */
@JsonDeserialize(builder = GetLodgingDetailsOperationParams.Builder::class)
data class GetLodgingDetailsOperationParams(
    @field:NotNull
    @field:Valid
    val offerToken: kotlin.String,
    @field:NotNull
    @field:Valid
    val partnerTransactionId: kotlin.String,
    @field:Valid
    val price: kotlin.String? =
        null,
    @field:Valid
    val currency: kotlin.String? =
        null,
    @field:Valid
    val locale: kotlin.String? =
        null,
    val imageSizes: GetLodgingDetailsOperationParams.ImageSizes? =
        ImageSizes.T,
    @field:Valid
    val groupedAmenities: kotlin.Boolean? =
        null
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    enum class ImageSizes(
        val value: kotlin.String
    ) {
        T("t"),
        S("s"),
        B("b"),
        Y("y"),
        Z("z")
    }

    class Builder(
        @JsonProperty("offerToken") private var offerToken: kotlin.String? = null,
        @JsonProperty("Partner-Transaction-Id") private var partnerTransactionId: kotlin.String? = null,
        @JsonProperty("price") private var price: kotlin.String? = null,
        @JsonProperty("currency") private var currency: kotlin.String? = null,
        @JsonProperty("locale") private var locale: kotlin.String? = null,
        @JsonProperty("imageSizes") private var imageSizes: GetLodgingDetailsOperationParams.ImageSizes? = null,
        @JsonProperty("groupedAmenities") private var groupedAmenities: kotlin.Boolean? = null
    ) {
        /**
         * @param offerToken A Hotel Natural Key from the Lodging Search API -> Hotels -> RoomTypes -> OfferId. It is a concatenated string of multiple values that defines a hotel offer.
         */
        fun offerToken(offerToken: kotlin.String) = apply { this.offerToken = offerToken }

        /**
         * @param partnerTransactionId The `Partner-Transaction-ID` is a required API request header element that is <u>not</u> consumed by Expedia. It will be required in all XAP v3 API request headers and will be mirrored back to the partner in the corresponding API response header.  The `Partner-Transaction-ID` may be any alphanumeric string of the partner's choosing.
         */
        fun partnerTransactionId(partnerTransactionId: kotlin.String) = apply { this.partnerTransactionId = partnerTransactionId }

        /**
         * @param price The total price of the selected property.  This value will be used to determine if there is a price change between the details and booking request.  The `price` element will be automatically included in the ApiDetails HATEOAS link which is returned along with the Lodging Details API response.
         */
        fun price(price: kotlin.String) = apply { this.price = price }

        /**
         * @param currency Value should be a standard ISO 3 letter currency code.  The currency code that is associated to the `TotalPrice` element for the selected property's rate plan from the Lodging Listing API response.  The `currency` element will be automatically included in the ApiDetails HATEOAS link which is returned along with the Lodging Details response.
         */
        fun currency(currency: kotlin.String) = apply { this.currency = currency }

        /**
         * @param locale `locale` is composed of language identifier and region identifier, connected by \"_\" that specifies the language in which the response will be returned.  The `locale` value used in the Lodging Details API query should match the `locale` value that was used in the Lodging Search API query.
         */
        fun locale(locale: kotlin.String) = apply { this.locale = locale }

        /**
         * @param imageSizes Indicate what size of image will be returned.  The available image sizes are: - t : Thumbnail - s : Small - b : Big - y : 500x500v - z : 1000x1000v  Resolution from smallest to largest is t < s < b < y < z.  If no `imageSizes` is specified, the t size images will be returned.  If an image is missing in one size, we will try to fall back to the same image with lower resolution.  (If image \"_z\" is not available, we will try to return image \"_y\", and if it is also unavailable, we will return image \"_b\", and so on.)  Only one image size is allowed.
         */
        fun imageSizes(imageSizes: GetLodgingDetailsOperationParams.ImageSizes) = apply { this.imageSizes = imageSizes }

        /**
         * @param groupedAmenities Specifies whether to return the grouped amenities. If `groupedAmenities` is true, `DescriptiveAmenities` node will be returned in response, otherwise `Amenities` node will be returned.
         */
        fun groupedAmenities(groupedAmenities: kotlin.Boolean) = apply { this.groupedAmenities = groupedAmenities }

        fun build(): GetLodgingDetailsOperationParams {
            val params =
                GetLodgingDetailsOperationParams(
                    offerToken = offerToken!!,
                    partnerTransactionId = partnerTransactionId!!,
                    price = price,
                    currency = currency,
                    locale = locale,
                    imageSizes = imageSizes,
                    groupedAmenities = groupedAmenities
                )

            validate(params)

            return params
        }

        private fun validate(params: GetLodgingDetailsOperationParams) {
            val validator =
                Validation
                    .byDefaultProvider()
                    .configure()
                    .messageInterpolator(ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .validator

            val violations = validator.validate(params)

            if (violations.isNotEmpty()) {
                throw PropertyConstraintViolationException(
                    constraintViolations = violations.map { "${it.propertyPath}: ${it.message}" }
                )
            }
        }
    }

    fun toBuilder() =
        Builder(
            offerToken = offerToken,
            partnerTransactionId = partnerTransactionId,
            price = price,
            currency = currency,
            locale = locale,
            imageSizes = imageSizes,
            groupedAmenities = groupedAmenities
        )

    override fun getHeaders(): Headers =
        Headers.build {
            partnerTransactionId?.let {
                append("Partner-Transaction-Id", it)
            }
            append("Accept", "application/vnd.exp-hotel.v3+json")
        }

    override fun getQueryParams(): Parameters =
        Parameters.build {
            price?.let {
                append("price", it)
            }
            currency?.let {
                append("currency", it)
            }
            locale?.let {
                append("locale", it)
            }
            imageSizes?.let {
                append("imageSizes", it.value)
            }
            groupedAmenities?.let {
                append("groupedAmenities", it.toString())
            }
        }

    override fun getPathParams(): Map<String, String> =
        buildMap {
            offerToken?.also {
                put("offerToken", offerToken)
            }
        }
}
