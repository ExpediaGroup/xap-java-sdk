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
package com.expediagroup.sdk.xap.examples.scenarios.flight;

import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.examples.scenarios.ExampleScenario;
import com.expediagroup.sdk.xap.model.FlightDetailsResponse;
import com.expediagroup.sdk.xap.operations.GetFlightDetailsOperation;
import com.expediagroup.sdk.xap.operations.GetFlightDetailsOperationParams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example demonstrates how to make Details API Call to get extended information about
 * fare, charges, fees, other terms associated with booking an airline ticket.
 */

public class FlightDetailsExample extends ExampleScenario {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(FlightDetailsExample.class);

    public FlightDetailsExample(XapClient client) {
        super(client);
    }

    @Override
    public void run() {
        LOGGER.info(
                "========= Running FlightDetailsQuickStartScenario =============");

        LOGGER.info(
                "============= Executing GetFlightDetailsOperation =========");

        GetFlightDetailsOperationParams detailsOperationParams = GetFlightDetailsOperationParams
                .builder()
                .offerToken("v5-sos-f7f6baebb159423791c2583b1dfd22b7-14-1~8.S~AQoECL"
                        + "-kBBIICNQEEAEYwB8oAlgBcAB6BXNhYnJliAHczsZQqgEHCgNMQVgQALIBB"
                        + "woDU0VBEAA~AQotCisIwaYBEgQxMzA1GIu4ASCSASiUuPoCML25-gI4T0AA"
                        + "WAFyCE9IMk9YVU1OEgoIARABGAEqAkFTGAEiBAgBEAEoAigDKAQwAg")
                .partnerTransactionID("txn-123-4")
                .price("123.31")
                .locale("en_US")
                .build();

        FlightDetailsResponse flightDetailsResponse =
                client.execute(new GetFlightDetailsOperation(detailsOperationParams))
                        .getData();

        LOGGER.info("========= GetFlightDetailsOperation Executed ========");

        LOGGER.info("Transaction ID: {}", flightDetailsResponse.getTransactionId());

        if (flightDetailsResponse.getSegments() != null) {
            flightDetailsResponse.getSegments().forEach(segment -> {
                LOGGER.info("======================== Flight Segment Start ========================");
                LOGGER.info("Segment ID: {}", segment.getSegmentId());
                LOGGER.info("Flight Duration: {}", segment.getFlightDuration());
                LOGGER.info("Total Stops: {}", segment.getTotalStops());
                LOGGER.info("Departure Arrival Day Difference: {}",
                        segment.getDepartureArrivalDayDifference());
                LOGGER.info("Fare Type: {}", segment.getFareType());
                LOGGER.info("Airport Change: {}", segment.getAirportChange());

                if (segment.getLegs() != null) {
                    segment.getLegs().forEach(leg -> {
                        LOGGER.info("-------------------- Leg Start --------------------");

                        if (leg.getDepartureAirport() != null) {
                            LOGGER.info("Departure Airport Code: {}",
                                    leg.getDepartureAirport().getCode());
                        }

                        if (leg.getArrivalAirport() != null) {
                            LOGGER.info("Arrival Airport Code: {}",
                                    leg.getArrivalAirport().getCode());
                        }
                        LOGGER.info("Departure Date Time: {}",
                                leg.getDepartureDateTime());
                        LOGGER.info("Arrival Date Time: {}",
                                leg.getArrivalDateTime());
                        LOGGER.info("Flight Number: {}",
                                leg.getFlightNumber());
                        LOGGER.info("Marketing Airline Code: {}",
                                leg.getMarketingAirlineCode());
                        LOGGER.info("Marketing Airline Name: {}",
                                leg.getMarketingAirlineName());
                        LOGGER.info("Equipment Code: {}",
                                leg.getEquipmentCode());
                        LOGGER.info("Flight On Time Percentage: {}",
                                leg.getFlightOnTimePercentage());
                        LOGGER.info("Equipment Name: {}",
                                leg.getEquipmentName());
                        LOGGER.info("Flight Duration (Leg): {}",
                                leg.getFlightDuration());

                        if (leg.getFlightDistance() != null) {
                            LOGGER.info("Flight Distance Value: {}",
                                    leg.getFlightDistance().getValue());
                            LOGGER.info("Flight Distance Unit: {}",
                                    leg.getFlightDistance().getUnit());
                        }
                        LOGGER.info("Booking Code: {}",
                                leg.getBookingCode());
                        LOGGER.info("Cabin Class: {}",
                                leg.getCabinClass());
                        LOGGER.info("Fare Basis Code: {}",
                                leg.getFareBasisCode());
                        LOGGER.info("Meal Options: {}",
                                leg.getMealOptions());
                        LOGGER.info("Amenities: {}",
                                leg.getAmenities());
                        LOGGER.info("---------- Leg End ----------");
                    });
                }
                LOGGER.info("=========== Flight Segment End ============");
            });
        }

        if (flightDetailsResponse.getOffer() != null) {
            LOGGER.info("======================== Flight Offer Start ========================");

            if (flightDetailsResponse.getOffer().getLinks() != null) {
                flightDetailsResponse.getOffer().getLinks().forEach((key, value) ->
                        LOGGER.info("Link [{}]: {}", key, value));
            }

            LOGGER.info("Segment IDs: {}", flightDetailsResponse.getOffer().getSegmentIds());
            LOGGER.info("Refundable: {}", flightDetailsResponse.getOffer().getRefundable());
            LOGGER.info("International: {}", flightDetailsResponse.getOffer().getInternational());
            LOGGER.info("UndisclosedGenderSupported: {}",
                    flightDetailsResponse.getOffer().getUndisclosedGenderSupported());
            LOGGER.info("UnspecifiedGenderSupported: {}",
                    flightDetailsResponse.getOffer().getUnspecifiedGenderSupported());

            if (flightDetailsResponse.getOffer().getOfferPrice() != null) {

                if (flightDetailsResponse.getOffer().getOfferPrice().getTotalPrice() != null) {
                    LOGGER.info("Total Price Value: {}",
                            flightDetailsResponse.getOffer().getOfferPrice().getTotalPrice().getValue());
                    LOGGER.info("Total Price Currency: {}",
                            flightDetailsResponse.getOffer().getOfferPrice().getTotalPrice().getCurrency());
                }

                if (flightDetailsResponse.getOffer().getOfferPrice().getBasePrice() != null) {
                    LOGGER.info("Base Price Value: {}",
                            flightDetailsResponse.getOffer().getOfferPrice().getBasePrice().getValue());
                    LOGGER.info("Base Price Currency: {}",
                            flightDetailsResponse.getOffer().getOfferPrice().getBasePrice().getCurrency());
                }

                if (flightDetailsResponse.getOffer().getOfferPrice().getTotalTaxes() != null) {
                    LOGGER.info("Total Taxes Value: {}",
                            flightDetailsResponse.getOffer().getOfferPrice().getTotalTaxes().getValue());
                    LOGGER.info("Total Taxes Currency: {}",
                            flightDetailsResponse.getOffer().getOfferPrice().getTotalTaxes().getCurrency());
                }

                if (flightDetailsResponse.getOffer().getOfferPrice().getTotalTaxesAndFees() != null) {
                    LOGGER.info("Total Taxes and Fees Value: {}",
                            flightDetailsResponse.getOffer()
                                    .getOfferPrice().getTotalTaxesAndFees().getValue());
                    LOGGER.info("Total Taxes and Fees Currency: {}",
                            flightDetailsResponse.getOffer()
                                    .getOfferPrice().getTotalTaxesAndFees().getCurrency());
                }

                if (flightDetailsResponse.getOffer().getOfferPrice().getAveragePricePerTicket() != null) {
                    LOGGER.info("Average Price Per Ticket Value: {}",
                            flightDetailsResponse.getOffer()
                                    .getOfferPrice().getAveragePricePerTicket().getValue());
                    LOGGER.info("Average Price Per Ticket Currency: {}",
                            flightDetailsResponse.getOffer()
                                    .getOfferPrice().getAveragePricePerTicket().getCurrency());
                    LOGGER.info("Average Price Per Ticket Count: {}",
                            flightDetailsResponse.getOffer()
                                    .getOfferPrice().getAveragePricePerTicket().getCount());
                }

                if (flightDetailsResponse.getOffer()
                        .getOfferPrice().getPricePerPassengerCategory() != null) {
                    flightDetailsResponse.getOffer().getOfferPrice().getPricePerPassengerCategory()
                            .forEach(categoryPrice -> {
                                LOGGER.info("---- Passenger Category Price Start ----");
                                LOGGER.info("Category: {}", categoryPrice.getCategory());
                                LOGGER.info("Count: {}", categoryPrice.getCount());

                                if (categoryPrice.getTotalPrice() != null) {
                                    LOGGER.info("Total Price Value: {}",
                                            categoryPrice.getTotalPrice().getValue());
                                    LOGGER.info("Total Price Currency: {}",
                                            categoryPrice.getTotalPrice().getCurrency());
                                }

                                if (categoryPrice.getBasePrice() != null) {
                                    LOGGER.info("Base Price Value: {}",
                                            categoryPrice.getBasePrice().getValue());
                                    LOGGER.info("Base Price Currency: {}",
                                            categoryPrice.getBasePrice().getCurrency());
                                }

                                if (categoryPrice.getTotalTaxes() != null) {
                                    LOGGER.info("Total Taxes Value: {}",
                                            categoryPrice.getTotalTaxes().getValue());
                                    LOGGER.info("Total Taxes Currency: {}",
                                            categoryPrice.getTotalTaxes().getCurrency());
                                }
                                LOGGER.info("---- Passenger Category Price End ----");
                            });
                }
            }


            LOGGER.info("Ticket Type: {}", flightDetailsResponse.getOffer().getTicketType());
            LOGGER.info("======================== Flight Offer End ========================");
        }

        if (flightDetailsResponse.getValidFormsOfPayment() != null) {
            flightDetailsResponse.getValidFormsOfPayment().forEach((value) -> {
                LOGGER.info("========== Valid Forms of Payment End [{}] ============");
                LOGGER.info("---- Payment Method Start ----");
                LOGGER.info("Payment Method: {}", value.getPaymentMethod());
                LOGGER.info("Name: {}", value.getName());
                LOGGER.info("Fee: {}", value.getFee());
                LOGGER.info("Currency: {}", value.getCurrency());
                LOGGER.info("---- Payment Method End ----");
                LOGGER.info("========== Valid Forms of Payment End [{}] ============");
            });
        }

        LOGGER.info(
                "===================== End FlightListingsQuickStartScenario ====================");
    }

}






