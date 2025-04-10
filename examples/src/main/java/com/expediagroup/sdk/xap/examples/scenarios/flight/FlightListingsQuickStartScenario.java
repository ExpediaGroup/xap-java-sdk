package com.expediagroup.sdk.xap.examples.scenarios.flight;

import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.examples.scenarios.XapScenario;
import com.expediagroup.sdk.xap.models.FlightSearchResponse;
import com.expediagroup.sdk.xap.operations.GetFlightListingsOperation;
import com.expediagroup.sdk.xap.operations.GetFlightListingsOperationParams;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.fasterxml.jackson.databind.cfg.CoercionInputShape.Array;

/**
 * This example demonstrates how to search for flight listings for a one-way trip with adults,
 * seniors, and children.
 */
public class FlightListingsQuickStartScenario implements XapScenario {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(FlightListingsQuickStartScenario.class);

    /**
     * Summary: main function.
     */
    public static void main(String[] args) {
        new FlightListingsQuickStartScenario().run();
        System.exit(0);
    }

    @Override
    public void run() {
        LOGGER.info(
                "========================= Running FlightListingsQuickStartScenario ========================");

        LOGGER.info(
                "======================== Executing GetFlightListingsOperation (One-Way) =======================");

        // This example will search for one-way flight offers based on the following criteria:
        // 1. Origin: LAS (Las Vegas)
        // 2. Destination: FLL (Fort Lauderdale)
        // 3. Departure Date: 2025-05-01
        // 4. 1 Adult, 1 Senior, Children aged 2 and 3

        GetFlightListingsOperationParams getFlightListingsOperationParams =
                GetFlightListingsOperationParams.builder()
                        .partnerTransactionID("txn-123-4")
                        .segment1Origin("LAS")
                        .segment1Destination("FLL")
                        .segment1DepartureDate(LocalDate.of(2025, 5, 1))
                        .adult(1)
                        .senior(1)
                        .build();

        XapClient xapClient = createClient();

        // Execute the operation and get the FlightListingsResponse
        FlightSearchResponse flightListingsResponse =
                xapClient.execute(new GetFlightListingsOperation(getFlightListingsOperationParams))
                        .getData();

        LOGGER.info(
                "======================== GetFlightListingsOperation (One-Way) Executed ========================");

        if (flightListingsResponse == null || flightListingsResponse.getSegments() == null
                || flightListingsResponse.getSegments().isEmpty()) {
            throw new IllegalStateException("No flight found.");
        }

        // The FlightListingsResponse contains a transaction ID for troubleshooting
        LOGGER.info("Transaction ID: {}", flightListingsResponse.getTransactionId());

        // To access the flight segments and offers, iterate through the response
        if (flightListingsResponse.getSegments() != null) {
            flightListingsResponse.getSegments().forEach(segment -> {
                LOGGER.info("======================== Flight Segment Start ========================");
                LOGGER.info("Segment ID: {}", segment.getSegmentId());
                LOGGER.info("Flight Duration: {}", segment.getFlightDuration());
                LOGGER.info("Total Stops: {}", segment.getTotalStops());
                LOGGER.info("Departure Arrival Day Difference: {}", segment.getDepartureArrivalDayDifference());
                LOGGER.info("Seats Left: {}", segment.getSeatsLeft());
                LOGGER.info("Fare Type: {}", segment.getFareType());

                if (segment.getLegs() != null) {
                    segment.getLegs().forEach(leg -> {
                        LOGGER.info("-------------------- Leg Start --------------------");
                        if (leg.getDepartureAirport() != null) {
                            LOGGER.info("Departure Airport Code: {}", leg.getDepartureAirport().getCode());
                            LOGGER.info("Departure Airport Name: {}", leg.getDepartureAirport().getName());
                            LOGGER.info("Departure City: {}", leg.getDepartureAirport().getCity());
                            LOGGER.info("Departure Province: {}", leg.getDepartureAirport().getProvince());
                            LOGGER.info("Departure Country: {}", leg.getDepartureAirport().getCountry());
                            LOGGER.info("Departure Latitude: {}", leg.getDepartureAirport().getLatitude());
                            LOGGER.info("Departure Longitude: {}", leg.getDepartureAirport().getLongitude());
                        }
                        if (leg.getArrivalAirport() != null) {
                            LOGGER.info("Arrival Airport Code: {}", leg.getArrivalAirport().getCode());
                            LOGGER.info("Arrival Airport Name: {}", leg.getArrivalAirport().getName());
                            LOGGER.info("Arrival City: {}", leg.getArrivalAirport().getCity());
                            LOGGER.info("Arrival Province: {}", leg.getArrivalAirport().getProvince());
                            LOGGER.info("Arrival Country: {}", leg.getArrivalAirport().getCountry());
                            LOGGER.info("Arrival Latitude: {}", leg.getArrivalAirport().getLatitude());
                            LOGGER.info("Arrival Longitude: {}", leg.getArrivalAirport().getLongitude());
                        }
                        LOGGER.info("Departure Date Time: {}", leg.getDepartureDateTime());
                        LOGGER.info("Arrival Date Time: {}", leg.getArrivalDateTime());
                        LOGGER.info("Flight Number: {}", leg.getFlightNumber());
                        LOGGER.info("Marketing Airline Code: {}", leg.getMarketingAirlineCode());
                        LOGGER.info("Marketing Airline Name: {}", leg.getMarketingAirlineName());
                        LOGGER.info("Equipment Code: {}", leg.getEquipmentCode());
                        LOGGER.info("Flight On Time Percentage: {}", leg.getFlightOnTimePercentage());
                        LOGGER.info("Equipment Name: {}", leg.getEquipmentName());
                        LOGGER.info("Flight Duration (Leg): {}", leg.getFlightDuration());
                       // LOGGER.info("Seat Map Available: {}", leg.isSeatMapAvailable());
                        if (leg.getFlightDistance() != null) {
                            LOGGER.info("Flight Distance Value: {}", leg.getFlightDistance().getValue());
                            LOGGER.info("Flight Distance Unit: {}", leg.getFlightDistance().getUnit());
                        }
                        LOGGER.info("Booking Code: {}", leg.getBookingCode());
                        LOGGER.info("Cabin Class: {}", leg.getCabinClass());
                        LOGGER.info("Fare Basis Code: {}", leg.getFareBasisCode());
                        LOGGER.info("Meal Options: {}", leg.getMealOptions());
                        LOGGER.info("Amenities: {}", leg.getAmenities());
                        LOGGER.info("-------------------- Leg End --------------------");
                    });
                }
                LOGGER.info("======================== Flight Segment End ========================");
            });
        }

        if (flightListingsResponse.getOffers() != null) {
            flightListingsResponse.getOffers().forEach(offer -> {
                LOGGER.info("======================== Flight Offer Start ========================");
               // LOGGER.info("Offer ID: {}", offer.getOfferId());
                //LOGGER.info("Split Ticket: {}", offer.isSplitTicket());
                //LOGGER.info("Opaque Flight: {}", offer.isOpaqueFlight());
               // LOGGER.info("Free 24 Hour Cancellation: {}", offer.isFree24HourCancellation());

                if (offer.getLinks() != null) {
                    offer.getLinks().forEach((key, value) -> LOGGER.info("Link [{}]: {}", key, value));
                }

                LOGGER.info("Segment IDs: {}", offer.getSegmentIds());

                if (offer.getOfferPrice() != null) {
                    if (offer.getOfferPrice().getTotalPrice() != null) {
                        LOGGER.info("Total Price Value: {}", offer.getOfferPrice().getTotalPrice().getValue());
                        LOGGER.info("Total Price Currency: {}", offer.getOfferPrice().getTotalPrice().getCurrency());
                    }
                    if (offer.getOfferPrice().getBasePrice() != null) {
                        LOGGER.info("Base Price Value: {}", offer.getOfferPrice().getBasePrice().getValue());
                        LOGGER.info("Base Price Currency: {}", offer.getOfferPrice().getBasePrice().getCurrency());
                    }
                    if (offer.getOfferPrice().getTotalTaxes() != null) {
                        LOGGER.info("Total Taxes Value: {}", offer.getOfferPrice().getTotalTaxes().getValue());
                        LOGGER.info("Total Taxes Currency: {}", offer.getOfferPrice().getTotalTaxes().getCurrency());
                    }
                    if (offer.getOfferPrice().getTotalTaxesAndFees() != null) {
                        LOGGER.info("Total Taxes and Fees Value: {}",
                                offer.getOfferPrice().getTotalTaxesAndFees().getValue());
                        LOGGER.info("Total Taxes and Fees Currency: {}",
                                offer.getOfferPrice().getTotalTaxesAndFees().getCurrency());
                    }
                    if (offer.getOfferPrice().getAveragePricePerTicket() != null) {
                        LOGGER.info("Average Price Per Ticket Value: {}",
                                offer.getOfferPrice().getAveragePricePerTicket().getValue());
                        LOGGER.info("Average Price Per Ticket Currency: {}",
                                offer.getOfferPrice().getAveragePricePerTicket().getCurrency());
                        LOGGER.info("Average Price Per Ticket Count: {}",
                                offer.getOfferPrice().getAveragePricePerTicket().getCount());
                    }
                    if (offer.getOfferPrice().getPricePerPassengerCategory() != null) {
                        offer.getOfferPrice().getPricePerPassengerCategory().forEach(categoryPrice -> {
                            LOGGER.info("---- Passenger Category Price Start ----");
                            LOGGER.info("Category: {}", categoryPrice.getCategory());
                            LOGGER.info("Count: {}", categoryPrice.getCount());
                            if (categoryPrice.getTotalPrice() != null) {
                                LOGGER.info("Total Price Value: {}", categoryPrice.getTotalPrice().getValue());
                                LOGGER.info("Total Price Currency: {}", categoryPrice.getTotalPrice().getCurrency());
                            }
                            if (categoryPrice.getBasePrice() != null) {
                                LOGGER.info("Base Price Value: {}", categoryPrice.getBasePrice().getValue());
                                LOGGER.info("Base Price Currency: {}", categoryPrice.getBasePrice().getCurrency());
                            }
                            if (categoryPrice.getTotalTaxes() != null) {
                                LOGGER.info("Total Taxes Value: {}", categoryPrice.getTotalTaxes().getValue());
                                LOGGER.info("Total Taxes Currency: {}", categoryPrice.getTotalTaxes().getCurrency());
                            }
                            LOGGER.info("---- Passenger Category Price End ----");
                        });
                    }
                }

               // LOGGER.info("Refundable: {}", offer.isRefundable());

                if (offer.getRefundPenalty() != null) {
                    offer.getRefundPenalty().forEach(penalty -> {
                        LOGGER.info("---- Refund Penalty Start ----");
                      //  LOGGER.info("Segment IDs: {}", penalty.getSegmentIds());
                      //  if (penalty.getPreTripChange() != null) {
                           // LOGGER.info("Pre Trip Change Allow: {}", penalty.getPreTripChange().getAllow());
                      //  }
                        LOGGER.info("---- Refund Penalty End ----");
                    });
                }

              //  LOGGER.info("International: {}", offer.isInternational());
                LOGGER.info("Ticket Type: {}", offer.getTicketType());
                LOGGER.info("======================== Flight Offer End ========================");
            });
        }

        if (flightListingsResponse.getSearchCities() != null) {
            flightListingsResponse.getSearchCities().forEach(city -> {
                LOGGER.info("======================== Search City Start ========================");
                LOGGER.info("City Code: {}", city.getCode());
                LOGGER.info("City Name: {}", city.getCity());
                LOGGER.info("Province: {}", city.getProvince());
                LOGGER.info("Country: {}", city.getCountry());
                LOGGER.info("======================== Search City End ========================");
            });
        }

        if (flightListingsResponse.getValidFormsOfPayment() != null) {
            flightListingsResponse.getValidFormsOfPayment().forEach((key, value) -> {
                LOGGER.info("======================== Valid Forms of Payment Start [{}] ========================", key);
                value.forEach(payment -> {
                    LOGGER.info("---- Payment Method Start ----");
                    LOGGER.info("Payment Method: {}", payment.getPaymentMethod());
                    LOGGER.info("Name: {}", payment.getName());
                    LOGGER.info("Fee: {}", payment.getFee());
                    LOGGER.info("Currency: {}", payment.getCurrency());
                    LOGGER.info("---- Payment Method End ----");
                });
                LOGGER.info("======================== Valid Forms of Payment End [{}] ========================", key);
            });
        }

        LOGGER.info(
                "======================== End FlightListingsQuickStartScenario ========================");

        // --- Example for a Round Trip Search ---
        LOGGER.info(
                "\n======================== Executing GetFlightListingsOperation (Round Trip) ========================");

        GetFlightListingsOperationParams roundTripParams =
                GetFlightListingsOperationParams.builder()
                        .segment1Origin("EWR")
                        .segment1Destination("LAX")
                        .segment1DepartureDate(LocalDate.of(2025, 5, 1))
                        .segment2Origin("LAX")
                        .segment2Destination("EWR")
                        .segment2DepartureDate(String.valueOf(LocalDate.of(2025, 5, 5)))
                        .adult(1)
                        .build();

        FlightSearchResponse roundTripResponse =
                xapClient.execute(new GetFlightListingsOperation(roundTripParams)).getData();

        LOGGER.info(
                "======================== GetFlightListingsOperation (Round Trip) Executed ========================");
        if (roundTripResponse != null && roundTripResponse.getSegments() != null
                && roundTripResponse.getSegments().size() == 2) {
            LOGGER.info("Round trip search returned {} segments.", roundTripResponse.getSegments().size());
        }

        // --- Example for a Multi-Stop Search ---
        LOGGER.info(
                "\n======================== Executing GetFlightListingsOperation (Multi-Stop) ========================");

        GetFlightListingsOperationParams multiStopParams =
                GetFlightListingsOperationParams.builder()
                        .segment1Origin("LAS")
                        .segment1Destination("ATL")
                        .segment1DepartureDate(LocalDate.of(2025, 5, 1))
                        .segment2Origin("ATL")
                        .segment2Destination("ORD")
                        .segment2DepartureDate(String.valueOf(LocalDate.of(2025, 5, 5)))
                        .segment3Origin("ORD")
                        .segment3Destination("SEA")
                        .segment3DepartureDate(String.valueOf(LocalDate.of(2025, 5, 10)))
                        .adult(1)
                        .build();

        FlightSearchResponse multiStopResponse =
                xapClient.execute(new GetFlightListingsOperation(multiStopParams)).getData();

        LOGGER.info(
                "======================== GetFlightListingsOperation (Multi-Stop) Executed ========================");
        if (multiStopResponse != null && multiStopResponse.getSegments() != null
                && multiStopResponse.getSegments().size() == 3) {
            LOGGER.info("Multi-stop search returned {} segments.", multiStopResponse.getSegments().size());
        }
    }
}