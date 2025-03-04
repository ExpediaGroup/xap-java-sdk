package com.expediagroup.sdk.xap.operations

import com.expediagroup.sdk.core.http.Headers
import com.expediagroup.sdk.rest.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.rest.model.UrlQueryParam
import com.expediagroup.sdk.rest.util.stringifyExplode
import com.expediagroup.sdk.rest.util.swaggerCollectionFormatStringifier
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * @property partnerTransactionId The `Partner-Transaction-ID` is a required API request header element that is <u>not</u> consumed by Expedia. It will be required in all XAP v3 API request headers and will be mirrored back to the partner in the corresponding API response header.  The `Partner-Transaction-ID` may be any alphanumeric string of the partner's choosing.
 * @property propertyIds Comma-separated list of Expedia Property IDs.  The API request supports a maximum of 50 Property IDs in a single request.
 */
@JsonDeserialize(builder = GetLodgingAvailabilityCalendarsOperationParams.Builder::class)
data class GetLodgingAvailabilityCalendarsOperationParams(
    @field:NotNull
    @field:Valid
    val partnerTransactionId: kotlin.String,
    @field:Valid
    val propertyIds: kotlin.collections.Set<
        kotlin.String
    >? =
        null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("Partner-Transaction-Id") private var partnerTransactionId: kotlin.String? = null,
        @JsonProperty("propertyIds") private var propertyIds: kotlin.collections.Set<
            kotlin.String
        >? = null
    ) {
        /**
         * @param partnerTransactionId The `Partner-Transaction-ID` is a required API request header element that is <u>not</u> consumed by Expedia. It will be required in all XAP v3 API request headers and will be mirrored back to the partner in the corresponding API response header.  The `Partner-Transaction-ID` may be any alphanumeric string of the partner's choosing.
         */
        fun partnerTransactionId(partnerTransactionId: kotlin.String) = apply { this.partnerTransactionId = partnerTransactionId }

        /**
         * @param propertyIds Comma-separated list of Expedia Property IDs.  The API request supports a maximum of 50 Property IDs in a single request.
         */
        fun propertyIds(
            propertyIds: kotlin.collections.Set<
                kotlin.String
            >
        ) = apply { this.propertyIds = propertyIds }

        fun build(): GetLodgingAvailabilityCalendarsOperationParams {
            val params =
                GetLodgingAvailabilityCalendarsOperationParams(
                    partnerTransactionId = partnerTransactionId!!,
                    propertyIds = propertyIds
                )

            validate(params)

            return params
        }

        private fun validate(params: GetLodgingAvailabilityCalendarsOperationParams) {
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
            partnerTransactionId = partnerTransactionId,
            propertyIds = propertyIds
        )

    fun getHeaders(): Headers =
        Headers.builder().apply {
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
                        stringify = swaggerCollectionFormatStringifier.getOrDefault("csv", stringifyExplode)
                    )
                )
            }
        }
}
