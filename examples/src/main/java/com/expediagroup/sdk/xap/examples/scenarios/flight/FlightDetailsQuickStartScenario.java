package com.expediagroup.sdk.xap.examples.scenarios.flight;

import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.examples.scenarios.XapScenario;
import com.expediagroup.sdk.xap.models.FlightDetailsResponse;
import com.expediagroup.sdk.xap.operations.GetFlightDetailsOperation;
import com.expediagroup.sdk.xap.operations.GetFlightDetailsOperationParams;
import com.expediagroup.sdk.xap.operations.GetFlightListingsOperation;
import com.expediagroup.sdk.xap.operations.GetFlightListingsOperationParams;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class demonstrates how to retrieve flight details using the Offer ID
 * obtained from a flight listing.
 */
public class FlightDetailsQuickStartScenario implements XapScenario {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightDetailsQuickStartScenario.class);

    /**
     * Main method to execute the scenario.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        new FlightListingsQuickStartScenario().run();
        new FlightDetailsQuickStartScenario().run();
        System.exit(0);
    }

    @Override
    public void run() {
        LOGGER.info("========== Start FlightDetailsQuickStartScenario ==========");

        XapClient xapClient = createClient();

        // Perform a flight search to get an Offer ID.
        GetFlightListingsOperationParams getFlightsListingsOperationParams =
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

        GetFlightListingsOperation getFlightsListingsOperation =
                new GetFlightListingsOperation(getFlightsListingsOperationParams);

        Response<com.expediagroup.sdk.xap.models.FlightSearchResponse> flightSearchResponse =
                xapClient.execute(getFlightsListingsOperation);

        if (flightSearchResponse.getData() == null
                || flightSearchResponse.getData().getOffers() == null
                || flightSearchResponse.getData().getOffers().isEmpty()) {
            LOGGER.warn("No flight found.");
            return;
        }

        String offerId = flightSearchResponse.getData().getOffers().get(0).getOfferToken();
        LOGGER.info("Retrieved Offer ID from flight listing: {}", offerId);

        // Use the Offer ID to retrieve Flight Details.
        GetFlightDetailsOperationParams getFlightDetailsOperationParams =
                GetFlightDetailsOperationParams.builder()
                        .offerToken(offerId)
                        .build();

        GetFlightDetailsOperation getFlightDetailsOperation =
                new GetFlightDetailsOperation(getFlightDetailsOperationParams);

        Response<FlightDetailsResponse> flightDetailsResponse =
                xapClient.execute(getFlightDetailsOperation);

        LOGGER.info("========== Executing GetFlightDetailsOperation ==========");

        if (flightDetailsResponse.getData() == null) {
            LOGGER.error("Failed to retrieve flight details for Offer ID: {}", offerId);
            return;
        }

        LOGGER.info("========== GetFlightDetailsOperation Executed ==========");

        FlightDetailsResponse details = flightDetailsResponse.getData();

        // Process the Flight Details Response
        LOGGER.info("========== Flight Details ==========");
        LOGGER.info("Transaction ID: {}", details.getTransactionId());

        if (details.getWarnings() != null && !details.getWarnings().isEmpty()) {
            LOGGER.info("Warnings:");
            details.getWarnings().forEach(warning ->
                    LOGGER.info("  Code: {}, Description: {}", warning.getCode(), warning.getDescription()));
        }

        if (details.getSegments() != null && !details.getSegments().isEmpty()) {
            LOGGER.info("Segments:");
            details.getSegments().forEach(segment -> {
                LOGGER.info("  Segment ID: {}", segment.getSegmentId());
                LOGGER.info("  Flight Duration: {}", segment.getFlightDuration());
                if (segment.getLegs() != null && !segment.getLegs().isEmpty()) {
                    LOGGER.info("  Legs:");
                    segment.getLegs().forEach(leg -> {
                        if (leg.getDepartureAirport() != null) {
                            LOGGER.info("    Departure: {} ({}) at {}",
                                    leg.getDepartureAirport().getName(),
                                    leg.getDepartureAirport().getCode(),
                                    leg.getDepartureDateTime());
                        }
                        if (leg.getArrivalAirport() != null) {
                            LOGGER.info("    Arrival: {} ({}) at {}",
                                    leg.getArrivalAirport().getName(),
                                    leg.getArrivalAirport().getCode(),
                                    leg.getArrivalDateTime());
                        }
                        LOGGER.info("    Marketing Airline: {} ({}), Flight Number: {}",
                                leg.getMarketingAirlineName(),
                                leg.getMarketingAirlineCode(),
                                leg.getFlightNumber());
                    });
                }
            });
        }

        if (details.getOffer() != null) {
            LOGGER.info("Offer Details:");
            LOGGER.info("  Offer ID: {}", details.getOffer().getOfferToken());
            LOGGER.info("  Split Ticket: {}", details.getOffer().getSplitTicket());
            if (details.getOffer().getOfferPrice() != null) {
                LOGGER.info("  Price: {} {}",
                        details.getOffer().getOfferPrice().getTotalPrice().getValue(),
                        details.getOffer().getOfferPrice().getTotalPrice().getCurrency());
            }
        }

        if (details.getValidFormsOfPayment() != null && !details.getValidFormsOfPayment().isEmpty()) {
            LOGGER.info("Valid Forms of Payment: {}", details.getValidFormsOfPayment());
        }

        if (details.getLounges() != null && !details.getLounges().isEmpty()) {
            LOGGER.info("Lounges:");
            details.getLounges().forEach((key, value) -> {
                LOGGER.info("  Lounge ID: {}", key);
            });
        }

        LOGGER.info("========== End FlightDetailsQuickStartScenario ==========");
    }
}