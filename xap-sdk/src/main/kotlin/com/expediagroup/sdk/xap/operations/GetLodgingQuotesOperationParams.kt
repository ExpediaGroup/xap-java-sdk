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
import com.expediagroup.sdk.rest.model.UrlQueryParam
import com.expediagroup.sdk.rest.util.UrlQueryParamStringifier.explode
import com.expediagroup.sdk.rest.util.swaggerCollectionFormatStringifier
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

/**
 * @property partnerTransactionId The `Partner-Transaction-ID` is a required API request header element that is <u>not</u> consumed by Expedia. It will be required in all XAP v3 API request headers and will be mirrored back to the partner in the corresponding API response header.  The `Partner-Transaction-ID` may be any alphanumeric string of the partner's choosing.
 * @property propertyIds Comma-separated list of Expedia Property IDs.  ***NOTE**: This API supports Expedia property IDs <u>only</u> by design – HCOM and Vrbo property IDs are  <u>not</u> supported.*  *For optimal performance, limiting your request to a maximum of 200 properties is recommended.*
 * @property checkIn Check-in date for property stay in an ISO 8601 Date format.  This parameter should be used in combination with the checkOut parameter.  The maximum advanced search window is 1000 days in the future, and the maximum length of stay is 732.
 * @property checkOut Check-out date for property stay in an ISO 8601 Date format  This parameter should be used in combination with the checkIn parameter.  The maximum advanced search window is 1000 days in the future, and the maximum length of stay is 732.
 * @property currency The requested currency expressed according to ISO 4217.  PoS default currency will be passed if another currency is not specified in the request.
 * @property links Comma-separated list to specify the types of deep links. - WD (link to web infosite) - WS (link to web search result page) - WEB (include all website links)
 * @property travelWithPets Indicates if the search should include pet-friendly properties.  If set to \"True\" only properties that allow pets are returned. Pet fees, if available, are included in TaxesAndFees.  The parameter is only applicable to the Vrbo brand.
 * @property room1Adults Specifies the number of adults staying in a specific room.  Example: `room1.adults` is used to specify the number of adults in the first room.  ***NOTE***: multiple room request is only supported for conventional lodging hotels. Request for Vrbo  properties should only include one room. e.g. 3 adults in room 1 and 3 in room 2, \"room1.adults\" is 6.
 * @property room1ChildAges Comma-separated list that specifies the age(s) for each of the children in each of the rooms.  Example: `room2.childAges=1,3` means two children (1-year old and 3-year old) are staying in room #2.  Children must be accompanied by an adult.  Total number of children is indicated by the number of childAges included.  If there are no children in the party then this value should not be included.  ***NOTE***: multiple room request is only supported for conventional lodging hotels. Request for Vrbo properties should include the ages of all the children in the first room. e.g. 1 child age 10 in room #1 and 1 child age 11 in room #2, \"room1.childAges\" is 10,11.
 * @property room2Adults Specifies the number of adults staying in second room.
 * @property room2ChildAges Comma-separated list that specifies the age(s) for each of the children in second room.
 * @property room3Adults Specifies the number of adults staying in third room.
 * @property room3ChildAges Comma-separated list that specifies the age(s) for each of the children in third room.
 * @property room4Adults Specifies the number of adults staying in fourth room.
 * @property room4ChildAges Comma-separated list that specifies the age(s) for each of the children in fourth room.
 * @property room5Adults Specifies the number of adults staying in fifth room.
 * @property room5ChildAges Comma-separated list that specifies the age(s) for each of the children in fifth room.
 * @property room6Adults Specifies the number of adults staying in sixth room.
 * @property room6ChildAges Comma-separated list that specifies the age(s) for each of the children in sixth room.
 * @property room7Adults Specifies the number of adults staying in seventh room.
 * @property room7ChildAges Comma-separated list that specifies the age(s) for each of the children in seventh room.
 * @property room8Adults Specifies the number of adults staying in eighth room.
 * @property room8ChildAges Comma-separated list that specifies the age(s) for each of the children in eighth room.
 */
@JsonDeserialize(builder = GetLodgingQuotesOperationParams.Builder::class)
data class GetLodgingQuotesOperationParams(
    val partnerTransactionId: kotlin.String,
    val propertyIds: kotlin.collections.Set<
        kotlin.String,
    >? =
        null,
    val checkIn: java.time.LocalDate? =
        null,
    val checkOut: java.time.LocalDate? =
        null,
    val currency: kotlin.String? =
        null,
    val links: kotlin.collections.List<
        GetLodgingQuotesOperationParams.Links,
    >? =
        null,
    val travelWithPets: kotlin.Boolean? =
        false,
    val room1Adults: kotlin.Long? =
        null,
    val room1ChildAges: kotlin.collections.List<
        kotlin.String,
    >? =
        null,
    val room2Adults: kotlin.Long? =
        null,
    val room2ChildAges: kotlin.collections.List<
        kotlin.String,
    >? =
        null,
    val room3Adults: kotlin.Long? =
        null,
    val room3ChildAges: kotlin.collections.List<
        kotlin.String,
    >? =
        null,
    val room4Adults: kotlin.Long? =
        null,
    val room4ChildAges: kotlin.collections.List<
        kotlin.String,
    >? =
        null,
    val room5Adults: kotlin.Long? =
        null,
    val room5ChildAges: kotlin.collections.List<
        kotlin.String,
    >? =
        null,
    val room6Adults: kotlin.Long? =
        null,
    val room6ChildAges: kotlin.collections.List<
        kotlin.String,
    >? =
        null,
    val room7Adults: kotlin.Long? =
        null,
    val room7ChildAges: kotlin.collections.List<
        kotlin.String,
    >? =
        null,
    val room8Adults: kotlin.Long? =
        null,
    val room8ChildAges: kotlin.collections.List<
        kotlin.String,
    >? =
        null,
) {
    init {
        require(partnerTransactionId != null) { "partnerTransactionId must not be null" }
    }

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    enum class Links(
        val value: kotlin.String,
    ) {
        WD("WD"),
        WS("WS"),
        WEB("WEB"),
    }

    class Builder(
        @JsonProperty("Partner-Transaction-Id") private var partnerTransactionId: kotlin.String? = null,
        @JsonProperty("propertyIds") private var propertyIds: kotlin.collections.Set<
            kotlin.String,
        >? = null,
        @JsonProperty("checkIn") private var checkIn: java.time.LocalDate? = null,
        @JsonProperty("checkOut") private var checkOut: java.time.LocalDate? = null,
        @JsonProperty("currency") private var currency: kotlin.String? = null,
        @JsonProperty("links") private var links: kotlin.collections.List<
            GetLodgingQuotesOperationParams.Links,
        >? = null,
        @JsonProperty("travelWithPets") private var travelWithPets: kotlin.Boolean? = null,
        @JsonProperty("room1.adults") private var room1Adults: kotlin.Long? = null,
        @JsonProperty("room1.childAges") private var room1ChildAges: kotlin.collections.List<
            kotlin.String,
        >? = null,
        @JsonProperty("room2.adults") private var room2Adults: kotlin.Long? = null,
        @JsonProperty("room2.childAges") private var room2ChildAges: kotlin.collections.List<
            kotlin.String,
        >? = null,
        @JsonProperty("room3.adults") private var room3Adults: kotlin.Long? = null,
        @JsonProperty("room3.childAges") private var room3ChildAges: kotlin.collections.List<
            kotlin.String,
        >? = null,
        @JsonProperty("room4.adults") private var room4Adults: kotlin.Long? = null,
        @JsonProperty("room4.childAges") private var room4ChildAges: kotlin.collections.List<
            kotlin.String,
        >? = null,
        @JsonProperty("room5.adults") private var room5Adults: kotlin.Long? = null,
        @JsonProperty("room5.childAges") private var room5ChildAges: kotlin.collections.List<
            kotlin.String,
        >? = null,
        @JsonProperty("room6.adults") private var room6Adults: kotlin.Long? = null,
        @JsonProperty("room6.childAges") private var room6ChildAges: kotlin.collections.List<
            kotlin.String,
        >? = null,
        @JsonProperty("room7.adults") private var room7Adults: kotlin.Long? = null,
        @JsonProperty("room7.childAges") private var room7ChildAges: kotlin.collections.List<
            kotlin.String,
        >? = null,
        @JsonProperty("room8.adults") private var room8Adults: kotlin.Long? = null,
        @JsonProperty("room8.childAges") private var room8ChildAges: kotlin.collections.List<
            kotlin.String,
        >? = null,
    ) {
        /**
         * @param partnerTransactionId The `Partner-Transaction-ID` is a required API request header element that is <u>not</u> consumed by Expedia. It will be required in all XAP v3 API request headers and will be mirrored back to the partner in the corresponding API response header.  The `Partner-Transaction-ID` may be any alphanumeric string of the partner's choosing.
         */
        fun partnerTransactionId(partnerTransactionId: kotlin.String) = apply { this.partnerTransactionId = partnerTransactionId }

        /**
         * @param propertyIds Comma-separated list of Expedia Property IDs.  ***NOTE**: This API supports Expedia property IDs <u>only</u> by design – HCOM and Vrbo property IDs are  <u>not</u> supported.*  *For optimal performance, limiting your request to a maximum of 200 properties is recommended.*
         */
        fun propertyIds(
            propertyIds: kotlin.collections.Set<
                kotlin.String,
            >,
        ) = apply { this.propertyIds = propertyIds }

        /**
         * @param checkIn Check-in date for property stay in an ISO 8601 Date format.  This parameter should be used in combination with the checkOut parameter.  The maximum advanced search window is 1000 days in the future, and the maximum length of stay is 732.
         */
        fun checkIn(checkIn: java.time.LocalDate) = apply { this.checkIn = checkIn }

        /**
         * @param checkOut Check-out date for property stay in an ISO 8601 Date format  This parameter should be used in combination with the checkIn parameter.  The maximum advanced search window is 1000 days in the future, and the maximum length of stay is 732.
         */
        fun checkOut(checkOut: java.time.LocalDate) = apply { this.checkOut = checkOut }

        /**
         * @param currency The requested currency expressed according to ISO 4217.  PoS default currency will be passed if another currency is not specified in the request.
         */
        fun currency(currency: kotlin.String) = apply { this.currency = currency }

        /**
         * @param links Comma-separated list to specify the types of deep links. - WD (link to web infosite) - WS (link to web search result page) - WEB (include all website links)
         */
        fun links(
            links: kotlin.collections.List<
                GetLodgingQuotesOperationParams.Links,
            >,
        ) = apply { this.links = links }

        /**
         * @param travelWithPets Indicates if the search should include pet-friendly properties.  If set to \"True\" only properties that allow pets are returned. Pet fees, if available, are included in TaxesAndFees.  The parameter is only applicable to the Vrbo brand.
         */
        fun travelWithPets(travelWithPets: kotlin.Boolean) = apply { this.travelWithPets = travelWithPets }

        fun rooms(rooms: List<com.expediagroup.sdk.xap.models.Room>) =
            apply {
                if (rooms.size > 8) {
                    throw com.expediagroup.sdk.rest.exception.client.PropertyConstraintViolationException(
                        constraintViolations = listOf("rooms: size must be between 0 and 8"),
                    )
                }

                rooms.elementAtOrNull(0)?.let {
                    this.room1Adults = it.adults
                    this.room1ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(1)?.let {
                    this.room2Adults = it.adults
                    this.room2ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(2)?.let {
                    this.room3Adults = it.adults
                    this.room3ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(3)?.let {
                    this.room4Adults = it.adults
                    this.room4ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(4)?.let {
                    this.room5Adults = it.adults
                    this.room5ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(5)?.let {
                    this.room6Adults = it.adults
                    this.room6ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(6)?.let {
                    this.room7Adults = it.adults
                    this.room7ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(7)?.let {
                    this.room8Adults = it.adults
                    this.room8ChildAges = it.childAges?.map { item -> item.toString() }
                }
            }

        fun build(): GetLodgingQuotesOperationParams {
            val params =
                GetLodgingQuotesOperationParams(
                    partnerTransactionId = partnerTransactionId!!,
                    propertyIds = propertyIds,
                    checkIn = checkIn,
                    checkOut = checkOut,
                    currency = currency,
                    links = links,
                    travelWithPets = travelWithPets,
                    room1Adults = room1Adults,
                    room1ChildAges = room1ChildAges,
                    room2Adults = room2Adults,
                    room2ChildAges = room2ChildAges,
                    room3Adults = room3Adults,
                    room3ChildAges = room3ChildAges,
                    room4Adults = room4Adults,
                    room4ChildAges = room4ChildAges,
                    room5Adults = room5Adults,
                    room5ChildAges = room5ChildAges,
                    room6Adults = room6Adults,
                    room6ChildAges = room6ChildAges,
                    room7Adults = room7Adults,
                    room7ChildAges = room7ChildAges,
                    room8Adults = room8Adults,
                    room8ChildAges = room8ChildAges,
                )

            return params
        }
    }

    fun toBuilder() =
        Builder(
            partnerTransactionId = partnerTransactionId,
            propertyIds = propertyIds,
            checkIn = checkIn,
            checkOut = checkOut,
            currency = currency,
            links = links,
            travelWithPets = travelWithPets,
            room1Adults = room1Adults,
            room1ChildAges = room1ChildAges,
            room2Adults = room2Adults,
            room2ChildAges = room2ChildAges,
            room3Adults = room3Adults,
            room3ChildAges = room3ChildAges,
            room4Adults = room4Adults,
            room4ChildAges = room4ChildAges,
            room5Adults = room5Adults,
            room5ChildAges = room5ChildAges,
            room6Adults = room6Adults,
            room6ChildAges = room6ChildAges,
            room7Adults = room7Adults,
            room7ChildAges = room7ChildAges,
            room8Adults = room8Adults,
            room8ChildAges = room8ChildAges,
        )

    fun getHeaders(): Headers =
        Headers
            .builder()
            .apply {
                partnerTransactionId?.let {
                    add("Partner-Transaction-Id", it)
                }
                add("Accept", "application/vnd.exp-lodging.v3+json")
            }.build()

    fun getQueryParams(): List<UrlQueryParam> =
        buildList {
            propertyIds?.let {
                val key = "propertyIds"
                val value =
                    buildList {
                        addAll(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", explode),
                    ),
                )
            }
            checkIn?.let {
                val key = "checkIn"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            checkOut?.let {
                val key = "checkOut"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            currency?.let {
                val key = "currency"
                val value =
                    buildList {
                        add(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            links?.let {
                val key = "links"
                val value =
                    buildList {
                        addAll(it.map { it.value })
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", explode),
                    ),
                )
            }
            travelWithPets?.let {
                val key = "travelWithPets"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            room1Adults?.let {
                val key = "room1.adults"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            room1ChildAges?.let {
                val key = "room1.childAges"
                val value =
                    buildList {
                        addAll(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", explode),
                    ),
                )
            }
            room2Adults?.let {
                val key = "room2.adults"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            room2ChildAges?.let {
                val key = "room2.childAges"
                val value =
                    buildList {
                        addAll(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", explode),
                    ),
                )
            }
            room3Adults?.let {
                val key = "room3.adults"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            room3ChildAges?.let {
                val key = "room3.childAges"
                val value =
                    buildList {
                        addAll(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", explode),
                    ),
                )
            }
            room4Adults?.let {
                val key = "room4.adults"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            room4ChildAges?.let {
                val key = "room4.childAges"
                val value =
                    buildList {
                        addAll(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", explode),
                    ),
                )
            }
            room5Adults?.let {
                val key = "room5.adults"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            room5ChildAges?.let {
                val key = "room5.childAges"
                val value =
                    buildList {
                        addAll(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", explode),
                    ),
                )
            }
            room6Adults?.let {
                val key = "room6.adults"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            room6ChildAges?.let {
                val key = "room6.childAges"
                val value =
                    buildList {
                        addAll(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", explode),
                    ),
                )
            }
            room7Adults?.let {
                val key = "room7.adults"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            room7ChildAges?.let {
                val key = "room7.childAges"
                val value =
                    buildList {
                        addAll(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", explode),
                    ),
                )
            }
            room8Adults?.let {
                val key = "room8.adults"
                val value =
                    buildList {
                        add(it.toString())
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("", explode),
                    ),
                )
            }
            room8ChildAges?.let {
                val key = "room8.childAges"
                val value =
                    buildList {
                        addAll(it)
                    }

                add(
                    UrlQueryParam(
                        key = key,
                        value = value,
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", explode),
                    ),
                )
            }
        }
}
