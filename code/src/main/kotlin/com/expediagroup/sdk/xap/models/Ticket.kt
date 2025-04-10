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
package com.expediagroup.sdk.xap.models

import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.xap.models.ActivitiesPrice
import com.expediagroup.sdk.xap.models.Restrictions
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * The list of Ticket information.
 * @param id The numerical identifier for the ticket.
 * @param code The code for the ticket. Values supported are: Adult Traveler Child Group Senior Infant Student
 * @param count The number of each ticket type to be booked.
 * @param ticketPrice
 * @param restrictions
 */
data class Ticket(
    // The numerical identifier for the ticket.
    @JsonProperty("Id")
    val id: kotlin.Int,
    // The code for the ticket. Values supported are: Adult Traveler Child Group Senior Infant Student
    @JsonProperty("Code")
    @field:NotNull
    @field:Valid
    val code: kotlin.String,
    // The number of each ticket type to be booked.
    @JsonProperty("Count")
    val count: kotlin.Int,
    @JsonProperty("TicketPrice")
    @field:NotNull
    @field:Valid
    val ticketPrice: ActivitiesPrice,
    @JsonProperty("Restrictions")
    @field:Valid
    val restrictions: Restrictions? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.Int? = null,
        private var code: kotlin.String? = null,
        private var count: kotlin.Int? = null,
        private var ticketPrice: ActivitiesPrice? = null,
        private var restrictions: Restrictions? = null
    ) {
        fun id(id: kotlin.Int) = apply { this.id = id }

        fun code(code: kotlin.String) = apply { this.code = code }

        fun count(count: kotlin.Int) = apply { this.count = count }

        fun ticketPrice(ticketPrice: ActivitiesPrice) = apply { this.ticketPrice = ticketPrice }

        fun restrictions(restrictions: Restrictions?) = apply { this.restrictions = restrictions }

        fun build(): Ticket {
            val instance =
                Ticket(
                    id = id!!,
                    code = code!!,
                    count = count!!,
                    ticketPrice = ticketPrice!!,
                    restrictions = restrictions
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: Ticket) {
            val validator =
                Validation
                    .byDefaultProvider()
                    .configure()
                    .messageInterpolator(ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .validator

            val violations = validator.validate(instance)

            if (violations.isNotEmpty()) {
                throw PropertyConstraintViolationException(
                    constraintViolations = violations.map { "${it.propertyPath}: ${it.message}" }
                )
            }
        }
    }

    fun toBuilder() =
        Builder(
            id = id!!,
            code = code!!,
            count = count!!,
            ticketPrice = ticketPrice!!,
            restrictions = restrictions
        )
}
