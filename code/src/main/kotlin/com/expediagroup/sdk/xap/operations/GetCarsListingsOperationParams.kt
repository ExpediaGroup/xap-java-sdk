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
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.ktor.http.Headers
import io.ktor.http.Parameters

/**
 * @property partnerTransactionId [Not consumed by Expedia] Partner-generated identifier.
 * @property pickupAirport Three letter code for the airport at which the customer would like to pick up the car.Supported values: standard 3 letter IATA Airport Code.Please see a full list of Car Vendor Codes and Airport Codes in the Related Links Section below.Cannot coexist with other pickup parameters, only one pickup parameter is allowed per request.
 * @property pickupCity The name of the city in which the customer would like to pick up the car.Search results will include up to 40 rental locations that are closest to the center point of the search.Cannot coexist with other pickup parameters, only one pickup parameter is allowed per request.
 * @property pickupAddress The address of a car rental location where the customer would like to pick up the car.Cannot coexist with other pickup parameters, only one pickup parameter is allowed per request.
 * @property pickupGeoLocation The latitude and longitude that defines where the customer would like to pick up the car.Latitude and longitude are separated by comma.South latitudes and West longitudes are represented by negative values.Cannot coexist with other pickup parameters, only one pickup parameter is allowed per request.
 * @property pickupRadius Radius used in conjunction with a point to define the search area when searching by lat/ long, city or address.See ' unit' parameter below to select miles or kilometers.If no value is specified a default value of 25 will be assumed.
 * @property dropOffAirport Three letter code for the airport at which the customer would like to drop off the car.Supported values: standard 3 letter IATA Airport Code.Please see a full list of Car Vendor Codes and Airport Codes in the Related Links Section below.Cannot coexist with other drop off parameters, only one drop off parameter is allowed per request.If no drop off location is specified, it is assumed that the customer will be dropping the car off at the same location at which they picked it up.
 * @property dropOffCity City name for the location at which the customer would like to drop off the car.Cannot coexist with other drop off parameters, only one drop off parameter is allowed in a request.If no drop off location is specified, it is assumed that the customer will be dropping the car off at the same location at which they picked it up.
 * @property dropOffAddress Address for the location at which the customer would like to drop off the car.Cannot coexist with other drop off parameters, only one drop off parameter is allowed in a request.If no drop off location is specified, it is assumed that the customer will be dropping the car off at the same location at which they picked it up.
 * @property dropOffGeoLocation Latitude and longitude for the location at which the customer would like to drop off the car.Latitude and longitude are separated by comma.South latitudes and West longitudes are represented by negative values.Cannot coexist with other drop off parameters, only one drop off parameter is allowed per request.If no drop off location is specified, it is assumed that the customer will be dropping the car off at the same location at which they picked it up.
 * @property dropOffRadius Radius used in conjunction with a point to define the search area when searching by lat/ long, city or address.See ' unit' parameter below to select miles or kilometers.If no value is specified a default value of 25 will be assumed.Note: The pickup radius value will be used (instead of the the drop-off radius) when the requested pickup and drop-off city/address are exactly the same.
 * @property pickupTime Requested car pickup date and time.Date should be ISO8601 Date format.The default TIME is 10:30:00.The supported search window is today to 330 days in the future.(Note that each rental counter has different hours of operation. If you select a time in the middle of the night there may be no inventory available as all locations may be closed.)
 * @property dropOffTime Requested car drop off date and time. Date should be ISO8601 Date format.The supported search window is today to 330 days in the future.Note: The dropOffTime must be at least 2 hours later than the pickupTime for the request to be valid.
 * @property sortType Method of sorting the car search results.Supported value: Price.If no value is present a sort by 'price' will be assumed.
 * @property sortOrder Order of sorting the car search results.Supported values: ASC, DESCIf no value is present a sort order of 'ascending' will be assumed.
 * @property limit The maximum number of search results that will be returned by the query.
 * @property suppliers A list of supplier ids or supplier names to be used to filter search results.Multiple supplier names or ids may be separated by comma.Please see a full list of Expedia Vendor Codes & Names in the Related Links Section below.The max count of suppliers requested is limited to 20.Note: while you may filter using either supplier name or supplier ID, it is recommended that you use supplier ID, as this value will remain consistent in the event of a merger or other name change by the supplier.
 * @property carClasses A list of car classes to be used to filter search results.Multiple car classes may be separated by comma.Please see Class List in the Related Links Section below for all options.
 * @property discount1Supplier Name or ID of the supplier who issued a coupon or discount code.NOTE: Only ONE discount code per transaction is currently supported by the API. If you enter more than one discount code, only the first one will be honored.Please see a full list of Expedia Vendor Codes & Names in the Related Links Section below.
 * @property discount1Type The type of discount to be applied.Supported values: CorpDiscount | Coupon.
 * @property discount1Code The code of the discount to be applied.
 * @property transmissions A list of car transmission drive codes to be used to filter search results.Multiple car classes may be separated by a comma.Please see a full list of Transmission Drive Codes in the Related Links Section below.
 * @property airConditioning Specify whether to filter for cars that include or exclude air conditioning.
 * @property carTypes A list of car types to be used to filter search results.Multiple car types may be separated by comma.Please see a full list of Car Type Codes in the Related Links Section below.
 * @property unit The distance unit for the radius of a location-based search, or the distance between the center point of a search and the vendor location.Supported values: KM | MI.Default value: KM.
 * @property driverAge The age of the driver that will be renting the car.This value is required in the UK and optional elsewhere.
 * @property links WS = WebSearch, AD = ApiDetails, WD = WebDetails
 * @property source Indicates the source where the request is coming from.The available values for the source as below:browser - The value \"browser\" represents that the client is traditional website.mobile - The value \"mobile\" represents that the client is mobile.all - The value \"all\" indicates that the client includes both browser and mobile.Only one source value may be used at a time.
 */
@JsonDeserialize(builder = GetCarsListingsOperationParams.Builder::class)
data class GetCarsListingsOperationParams(
    val partnerTransactionId: kotlin.String,
    val pickupAirport: kotlin.String? =
        null,
    val pickupCity: kotlin.String? =
        null,
    val pickupAddress: kotlin.String? =
        null,
    val pickupGeoLocation: kotlin.String? =
        null,
    val pickupRadius: kotlin.String? =
        null,
    val dropOffAirport: kotlin.String? =
        null,
    val dropOffCity: kotlin.String? =
        null,
    val dropOffAddress: kotlin.String? =
        null,
    val dropOffGeoLocation: kotlin.String? =
        null,
    val dropOffRadius: kotlin.String? =
        null,
    val pickupTime: kotlin.String? =
        null,
    val dropOffTime: kotlin.String? =
        null,
    val sortType: GetCarsListingsOperationParams.SortType? =
        null,
    val sortOrder: GetCarsListingsOperationParams.SortOrder? =
        null,
    val limit: kotlin.String? =
        null,
    val suppliers: kotlin.String? =
        null,
    val carClasses: kotlin.String? =
        null,
    val discount1Supplier: kotlin.String? =
        null,
    val discount1Type: GetCarsListingsOperationParams.Discount1Type? =
        null,
    val discount1Code: kotlin.String? =
        null,
    val transmissions: kotlin.String? =
        null,
    val airConditioning: GetCarsListingsOperationParams.AirConditioning? =
        null,
    val carTypes: kotlin.String? =
        null,
    val unit: GetCarsListingsOperationParams.Unit? =
        null,
    val driverAge: kotlin.String? =
        null,
    val links: kotlin.collections.List<
        GetCarsListingsOperationParams.Links
    >? =
        null,
    val source: GetCarsListingsOperationParams.Source? =
        null
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    enum class SortType(
        val value: kotlin.String
    ) {
        PRICE("Price")
    }

    enum class SortOrder(
        val value: kotlin.String
    ) {
        ASC("ASC"),
        DESC("DESC")
    }

    enum class Discount1Type(
        val value: kotlin.String
    ) {
        CORP_DISCOUNT("CorpDiscount"),
        COUPON("Coupon")
    }

    enum class AirConditioning(
        val value: kotlin.String
    ) {
        TRUE("true"),
        FALSE("false")
    }

    enum class Unit(
        val value: kotlin.String
    ) {
        KM("KM"),
        MI("MI")
    }

    enum class Links(
        val value: kotlin.String
    ) {
        WS("WS"),
        AD("AD"),
        WD("WD")
    }

    enum class Source(
        val value: kotlin.String
    ) {
        BROWSER("browser"),
        MOBILE("mobile"),
        ALL("all")
    }

    class Builder(
        @JsonProperty("Partner-Transaction-Id") private var partnerTransactionId: kotlin.String? = null,
        @JsonProperty("pickup.airport") private var pickupAirport: kotlin.String? = null,
        @JsonProperty("pickup.city") private var pickupCity: kotlin.String? = null,
        @JsonProperty("pickup.address") private var pickupAddress: kotlin.String? = null,
        @JsonProperty("pickup.geoLocation") private var pickupGeoLocation: kotlin.String? = null,
        @JsonProperty("pickup.radius") private var pickupRadius: kotlin.String? = null,
        @JsonProperty("dropOff.airport") private var dropOffAirport: kotlin.String? = null,
        @JsonProperty("dropOff.city") private var dropOffCity: kotlin.String? = null,
        @JsonProperty("dropOff.address") private var dropOffAddress: kotlin.String? = null,
        @JsonProperty("dropOff.geoLocation") private var dropOffGeoLocation: kotlin.String? = null,
        @JsonProperty("dropOff.radius") private var dropOffRadius: kotlin.String? = null,
        @JsonProperty("pickupTime") private var pickupTime: kotlin.String? = null,
        @JsonProperty("dropOffTime") private var dropOffTime: kotlin.String? = null,
        @JsonProperty("sortType") private var sortType: GetCarsListingsOperationParams.SortType? = null,
        @JsonProperty("sortOrder") private var sortOrder: GetCarsListingsOperationParams.SortOrder? = null,
        @JsonProperty("limit") private var limit: kotlin.String? = null,
        @JsonProperty("suppliers") private var suppliers: kotlin.String? = null,
        @JsonProperty("carClasses") private var carClasses: kotlin.String? = null,
        @JsonProperty("discount1.supplier") private var discount1Supplier: kotlin.String? = null,
        @JsonProperty("discount1.type") private var discount1Type: GetCarsListingsOperationParams.Discount1Type? = null,
        @JsonProperty("discount1.code") private var discount1Code: kotlin.String? = null,
        @JsonProperty("transmissions") private var transmissions: kotlin.String? = null,
        @JsonProperty("airConditioning") private var airConditioning: GetCarsListingsOperationParams.AirConditioning? = null,
        @JsonProperty("carTypes") private var carTypes: kotlin.String? = null,
        @JsonProperty("unit") private var unit: GetCarsListingsOperationParams.Unit? = null,
        @JsonProperty("driverAge") private var driverAge: kotlin.String? = null,
        @JsonProperty("links") private var links: kotlin.collections.List<
            GetCarsListingsOperationParams.Links
        >? = null,
        @JsonProperty("source") private var source: GetCarsListingsOperationParams.Source? = null
    ) {
        /**
         * @param partnerTransactionId [Not consumed by Expedia] Partner-generated identifier.
         */
        fun partnerTransactionId(partnerTransactionId: kotlin.String) = apply { this.partnerTransactionId = partnerTransactionId }

        /**
         * @param pickupAirport Three letter code for the airport at which the customer would like to pick up the car.Supported values: standard 3 letter IATA Airport Code.Please see a full list of Car Vendor Codes and Airport Codes in the Related Links Section below.Cannot coexist with other pickup parameters, only one pickup parameter is allowed per request.
         */
        fun pickupAirport(pickupAirport: kotlin.String) = apply { this.pickupAirport = pickupAirport }

        /**
         * @param pickupCity The name of the city in which the customer would like to pick up the car.Search results will include up to 40 rental locations that are closest to the center point of the search.Cannot coexist with other pickup parameters, only one pickup parameter is allowed per request.
         */
        fun pickupCity(pickupCity: kotlin.String) = apply { this.pickupCity = pickupCity }

        /**
         * @param pickupAddress The address of a car rental location where the customer would like to pick up the car.Cannot coexist with other pickup parameters, only one pickup parameter is allowed per request.
         */
        fun pickupAddress(pickupAddress: kotlin.String) = apply { this.pickupAddress = pickupAddress }

        /**
         * @param pickupGeoLocation The latitude and longitude that defines where the customer would like to pick up the car.Latitude and longitude are separated by comma.South latitudes and West longitudes are represented by negative values.Cannot coexist with other pickup parameters, only one pickup parameter is allowed per request.
         */
        fun pickupGeoLocation(pickupGeoLocation: kotlin.String) = apply { this.pickupGeoLocation = pickupGeoLocation }

        /**
         * @param pickupRadius Radius used in conjunction with a point to define the search area when searching by lat/ long, city or address.See ' unit' parameter below to select miles or kilometers.If no value is specified a default value of 25 will be assumed.
         */
        fun pickupRadius(pickupRadius: kotlin.String) = apply { this.pickupRadius = pickupRadius }

        /**
         * @param dropOffAirport Three letter code for the airport at which the customer would like to drop off the car.Supported values: standard 3 letter IATA Airport Code.Please see a full list of Car Vendor Codes and Airport Codes in the Related Links Section below.Cannot coexist with other drop off parameters, only one drop off parameter is allowed per request.If no drop off location is specified, it is assumed that the customer will be dropping the car off at the same location at which they picked it up.
         */
        fun dropOffAirport(dropOffAirport: kotlin.String) = apply { this.dropOffAirport = dropOffAirport }

        /**
         * @param dropOffCity City name for the location at which the customer would like to drop off the car.Cannot coexist with other drop off parameters, only one drop off parameter is allowed in a request.If no drop off location is specified, it is assumed that the customer will be dropping the car off at the same location at which they picked it up.
         */
        fun dropOffCity(dropOffCity: kotlin.String) = apply { this.dropOffCity = dropOffCity }

        /**
         * @param dropOffAddress Address for the location at which the customer would like to drop off the car.Cannot coexist with other drop off parameters, only one drop off parameter is allowed in a request.If no drop off location is specified, it is assumed that the customer will be dropping the car off at the same location at which they picked it up.
         */
        fun dropOffAddress(dropOffAddress: kotlin.String) = apply { this.dropOffAddress = dropOffAddress }

        /**
         * @param dropOffGeoLocation Latitude and longitude for the location at which the customer would like to drop off the car.Latitude and longitude are separated by comma.South latitudes and West longitudes are represented by negative values.Cannot coexist with other drop off parameters, only one drop off parameter is allowed per request.If no drop off location is specified, it is assumed that the customer will be dropping the car off at the same location at which they picked it up.
         */
        fun dropOffGeoLocation(dropOffGeoLocation: kotlin.String) = apply { this.dropOffGeoLocation = dropOffGeoLocation }

        /**
         * @param dropOffRadius Radius used in conjunction with a point to define the search area when searching by lat/ long, city or address.See ' unit' parameter below to select miles or kilometers.If no value is specified a default value of 25 will be assumed.Note: The pickup radius value will be used (instead of the the drop-off radius) when the requested pickup and drop-off city/address are exactly the same.
         */
        fun dropOffRadius(dropOffRadius: kotlin.String) = apply { this.dropOffRadius = dropOffRadius }

        /**
         * @param pickupTime Requested car pickup date and time.Date should be ISO8601 Date format.The default TIME is 10:30:00.The supported search window is today to 330 days in the future.(Note that each rental counter has different hours of operation. If you select a time in the middle of the night there may be no inventory available as all locations may be closed.)
         */
        fun pickupTime(pickupTime: kotlin.String) = apply { this.pickupTime = pickupTime }

        /**
         * @param dropOffTime Requested car drop off date and time. Date should be ISO8601 Date format.The supported search window is today to 330 days in the future.Note: The dropOffTime must be at least 2 hours later than the pickupTime for the request to be valid.
         */
        fun dropOffTime(dropOffTime: kotlin.String) = apply { this.dropOffTime = dropOffTime }

        /**
         * @param sortType Method of sorting the car search results.Supported value: Price.If no value is present a sort by 'price' will be assumed.
         */
        fun sortType(sortType: GetCarsListingsOperationParams.SortType) = apply { this.sortType = sortType }

        /**
         * @param sortOrder Order of sorting the car search results.Supported values: ASC, DESCIf no value is present a sort order of 'ascending' will be assumed.
         */
        fun sortOrder(sortOrder: GetCarsListingsOperationParams.SortOrder) = apply { this.sortOrder = sortOrder }

        /**
         * @param limit The maximum number of search results that will be returned by the query.
         */
        fun limit(limit: kotlin.String) = apply { this.limit = limit }

        /**
         * @param suppliers A list of supplier ids or supplier names to be used to filter search results.Multiple supplier names or ids may be separated by comma.Please see a full list of Expedia Vendor Codes & Names in the Related Links Section below.The max count of suppliers requested is limited to 20.Note: while you may filter using either supplier name or supplier ID, it is recommended that you use supplier ID, as this value will remain consistent in the event of a merger or other name change by the supplier.
         */
        fun suppliers(suppliers: kotlin.String) = apply { this.suppliers = suppliers }

        /**
         * @param carClasses A list of car classes to be used to filter search results.Multiple car classes may be separated by comma.Please see Class List in the Related Links Section below for all options.
         */
        fun carClasses(carClasses: kotlin.String) = apply { this.carClasses = carClasses }

        /**
         * @param discount1Supplier Name or ID of the supplier who issued a coupon or discount code.NOTE: Only ONE discount code per transaction is currently supported by the API. If you enter more than one discount code, only the first one will be honored.Please see a full list of Expedia Vendor Codes & Names in the Related Links Section below.
         */
        fun discount1Supplier(discount1Supplier: kotlin.String) = apply { this.discount1Supplier = discount1Supplier }

        /**
         * @param discount1Type The type of discount to be applied.Supported values: CorpDiscount | Coupon.
         */
        fun discount1Type(discount1Type: GetCarsListingsOperationParams.Discount1Type) = apply { this.discount1Type = discount1Type }

        /**
         * @param discount1Code The code of the discount to be applied.
         */
        fun discount1Code(discount1Code: kotlin.String) = apply { this.discount1Code = discount1Code }

        /**
         * @param transmissions A list of car transmission drive codes to be used to filter search results.Multiple car classes may be separated by a comma.Please see a full list of Transmission Drive Codes in the Related Links Section below.
         */
        fun transmissions(transmissions: kotlin.String) = apply { this.transmissions = transmissions }

        /**
         * @param airConditioning Specify whether to filter for cars that include or exclude air conditioning.
         */
        fun airConditioning(airConditioning: GetCarsListingsOperationParams.AirConditioning) = apply { this.airConditioning = airConditioning }

        /**
         * @param carTypes A list of car types to be used to filter search results.Multiple car types may be separated by comma.Please see a full list of Car Type Codes in the Related Links Section below.
         */
        fun carTypes(carTypes: kotlin.String) = apply { this.carTypes = carTypes }

        /**
         * @param unit The distance unit for the radius of a location-based search, or the distance between the center point of a search and the vendor location.Supported values: KM | MI.Default value: KM.
         */
        fun unit(unit: GetCarsListingsOperationParams.Unit) = apply { this.unit = unit }

        /**
         * @param driverAge The age of the driver that will be renting the car.This value is required in the UK and optional elsewhere.
         */
        fun driverAge(driverAge: kotlin.String) = apply { this.driverAge = driverAge }

        /**
         * @param links WS = WebSearch, AD = ApiDetails, WD = WebDetails
         */
        fun links(
            links: kotlin.collections.List<
                GetCarsListingsOperationParams.Links
            >
        ) = apply { this.links = links }

        /**
         * @param source Indicates the source where the request is coming from.The available values for the source as below:browser - The value \"browser\" represents that the client is traditional website.mobile - The value \"mobile\" represents that the client is mobile.all - The value \"all\" indicates that the client includes both browser and mobile.Only one source value may be used at a time.
         */
        fun source(source: GetCarsListingsOperationParams.Source) = apply { this.source = source }

        fun build(): GetCarsListingsOperationParams {
            validateNullity()

            return GetCarsListingsOperationParams(
                partnerTransactionId = partnerTransactionId!!,
                pickupAirport = pickupAirport,
                pickupCity = pickupCity,
                pickupAddress = pickupAddress,
                pickupGeoLocation = pickupGeoLocation,
                pickupRadius = pickupRadius,
                dropOffAirport = dropOffAirport,
                dropOffCity = dropOffCity,
                dropOffAddress = dropOffAddress,
                dropOffGeoLocation = dropOffGeoLocation,
                dropOffRadius = dropOffRadius,
                pickupTime = pickupTime,
                dropOffTime = dropOffTime,
                sortType = sortType,
                sortOrder = sortOrder,
                limit = limit,
                suppliers = suppliers,
                carClasses = carClasses,
                discount1Supplier = discount1Supplier,
                discount1Type = discount1Type,
                discount1Code = discount1Code,
                transmissions = transmissions,
                airConditioning = airConditioning,
                carTypes = carTypes,
                unit = unit,
                driverAge = driverAge,
                links = links,
                source = source
            )
        }

        private fun validateNullity() {
            if (partnerTransactionId == null) {
                throw NullPointerException("Required parameter partnerTransactionId is missing")
            }
        }
    }

    fun toBuilder() =
        Builder(
            partnerTransactionId = partnerTransactionId,
            pickupAirport = pickupAirport,
            pickupCity = pickupCity,
            pickupAddress = pickupAddress,
            pickupGeoLocation = pickupGeoLocation,
            pickupRadius = pickupRadius,
            dropOffAirport = dropOffAirport,
            dropOffCity = dropOffCity,
            dropOffAddress = dropOffAddress,
            dropOffGeoLocation = dropOffGeoLocation,
            dropOffRadius = dropOffRadius,
            pickupTime = pickupTime,
            dropOffTime = dropOffTime,
            sortType = sortType,
            sortOrder = sortOrder,
            limit = limit,
            suppliers = suppliers,
            carClasses = carClasses,
            discount1Supplier = discount1Supplier,
            discount1Type = discount1Type,
            discount1Code = discount1Code,
            transmissions = transmissions,
            airConditioning = airConditioning,
            carTypes = carTypes,
            unit = unit,
            driverAge = driverAge,
            links = links,
            source = source
        )

    override fun getHeaders(): Headers {
        return Headers.build {
            partnerTransactionId?.let {
                append("Partner-Transaction-Id", it)
            }
            append("Accept", "application/vnd.exp-car.v3+json,application/vnd.exp-car.v3+xml")
        }
    }

    override fun getQueryParams(): Parameters {
        return Parameters.build {
            pickupAirport?.let {
                append("pickup.airport", it)
            }
            pickupCity?.let {
                append("pickup.city", it)
            }
            pickupAddress?.let {
                append("pickup.address", it)
            }
            pickupGeoLocation?.let {
                append("pickup.geoLocation", it)
            }
            pickupRadius?.let {
                append("pickup.radius", it)
            }
            dropOffAirport?.let {
                append("dropOff.airport", it)
            }
            dropOffCity?.let {
                append("dropOff.city", it)
            }
            dropOffAddress?.let {
                append("dropOff.address", it)
            }
            dropOffGeoLocation?.let {
                append("dropOff.geoLocation", it)
            }
            dropOffRadius?.let {
                append("dropOff.radius", it)
            }
            pickupTime?.let {
                append("pickupTime", it)
            }
            dropOffTime?.let {
                append("dropOffTime", it)
            }
            sortType?.let {
                append("sortType", it.value)
            }
            sortOrder?.let {
                append("sortOrder", it.value)
            }
            limit?.let {
                append("limit", it)
            }
            suppliers?.let {
                append("suppliers", it)
            }
            carClasses?.let {
                append("carClasses", it)
            }
            discount1Supplier?.let {
                append("discount1.supplier", it)
            }
            discount1Type?.let {
                append("discount1.type", it.value)
            }
            discount1Code?.let {
                append("discount1.code", it)
            }
            transmissions?.let {
                append("transmissions", it)
            }
            airConditioning?.let {
                append("airConditioning", it.value)
            }
            carTypes?.let {
                append("carTypes", it)
            }
            unit?.let {
                append("unit", it.value)
            }
            driverAge?.let {
                append("driverAge", it)
            }
            links?.let {
                append("links", it.map { it.value }.joinToString(","))
            }
            source?.let {
                append("source", it.value)
            }
        }
    }

    override fun getPathParams(): Map<String, String> {
        return buildMap {
        }
    }
}
