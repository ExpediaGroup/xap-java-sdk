package com.expediagroup.sdk.xap.examples.scenarios.car.shopping.listings;

import com.expediagroup.sdk.xap.examples.Constants;
import com.expediagroup.sdk.xap.models.CarListingsResponse;
import com.expediagroup.sdk.xap.operations.GetCarsListingsOperation;
import com.expediagroup.sdk.xap.operations.GetCarsListingsOperationParams;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example demonstrates how to search for car properties with an Airport keyword with filters
 * applied.
 */
public class ListingsQuickStartExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListingsQuickStartExample.class);

    /**
     * Summary: main function.
     */
    public static void main(String[] args) {
        LOGGER.info("========== Start QuickStartExample ==========");
        // This example will search for properties based on the following criteria:
        // 1. The pickup airport is MCO, and the drop-off airport is MCO;
        // 2. The pickup time is "2025-01-15T11:00", and the drop-off time is "2025-01-19T11:00";
        // 3. The results should include API details, web search, and web details link;
        // 4. Limit the results to 5;

        // Create a list to store the types of links to be returned.
        // The added link types include(API details link, Web search link, and Web details link).
        List<GetCarsListingsOperationParams.Links> linksList = new ArrayList<>();
        linksList.add(GetCarsListingsOperationParams.Links.AD);
        linksList.add(GetCarsListingsOperationParams.Links.WS);
        linksList.add(GetCarsListingsOperationParams.Links.WD);

        GetCarsListingsOperationParams getCarsListingsOperationParams = GetCarsListingsOperationParams.builder()
                .partnerTransactionId("EWSCar_Automation")
                //Three letter code for the airport at which the customer would like to pick up the car.
                //Supported values: standard 3 letter IATA Airport Code.
                //Cannot coexist with other pickup parameters, only one pickup parameter is allowed per request.
                .pickupAirport("MCO")
                //Three letter code for the airport at which the customer would like to drop off the car.
                //Supported values: standard 3 letter IATA Airport Code.
                //Cannot coexist with other drop off parameters, only one drop off parameter is allowed per request.
                //If no drop off location is specified, it is assumed that the customer will be dropping the car off at the same location at which they picked it up.
                .dropOffAirport("MCO")
                //Requested car pickup date and time.
                //Date should be ISO8601 Date format.The supported search window is today to 330 days in the future.
                //(Note that each rental counter has different hours of operation. If you select a time in the middle of the night there may be no inventory available as all locations may be closed.)
                .pickupTime("2025-01-15T11:00")
                //Requested car drop off date and time.
                //Date should be ISO8601 Date format.The supported search window is today to 330 days in the future.
                //The drop off datetime should occur at least 2 hours after the pickup datetime.
                .dropOffTime("2025-01-19T11:00")
                //The maximum number of search results that will be returned by the query.
                .limit("5")
                .links(linksList)
                .build();

        // Execute the operation and get the CarListingsResponse
        LOGGER.info("========== Executing GetCarsListingsOperation ==========");
        CarListingsResponse carListingsResponse = Constants.XAP_CLIENT.execute(
                new GetCarsListingsOperation(getCarsListingsOperationParams)).getData();

        LOGGER.info("========== GetCarsListingsOperation Executed ==========");

        if (carListingsResponse == null || carListingsResponse.getCars() == null
                || carListingsResponse.getCars().isEmpty()) {
            throw new IllegalStateException("No cars found.");
        }

        // The CarListingsResponse contains a transaction ID for troubleshooting
        LOGGER.info("Transaction ID: {}", carListingsResponse.getTransactionId());
        // To get the total number of car found
        LOGGER.info("Car Count: {}", carListingsResponse.getCarCount());

        // To access the properties, iterate through the list of car properties
        carListingsResponse.getCars().forEach(car -> {
            LOGGER.info("========== Car Properties Start ==========");

            // Get the unique identifier of the car.
            LOGGER.info("Car Id: {}", car.getId());

            // Get the Vehicle Details of the car, which include (Make, Car Class, Min Doors, Max Doors, Fuel Level, Car Category, etc.).
            LOGGER.info("Car VehicleDetails: {}", car.getVehicleDetails());

            // Get the Supplier of the car, which include (Supplier ID, Supplier Name, Supplier Code, Supplier Logo Image Url.).
            LOGGER.info("Car Supplier: {}", car.getSupplier()) ;

            // Get Pickup information of the car, which include (DateTime, ShuttleCategory, Location, OpenSchedule, Distance).
            LOGGER.info("Pick up Details: {}", car.getPickupDetails());

            // Get Drop off information of the car, which include (DateTime, Location, OpenSchedule, Distance).
            LOGGER.info("Drop Off Details: {}", car.getDropOffDetails());

            // Get the API details link, web search link, and web details link from the links collection.
            if (!car.getLinks().isEmpty()) {
                if (car.getLinks().get("ApiDetails") != null) {
                    LOGGER.info("ApiDetails Link: {}", car.getLinks().get("ApiDetails").getHref());
                }
                if (car.getLinks().get("WebSearch") != null) {
                    LOGGER.info("WebSearch Link: {}", car.getLinks().get("WebSearch").getHref());
                }
                if (car.getLinks().get("WebDetails") != null) {
                    LOGGER.info("WebDetails Link: {}", car.getLinks().get("WebDetails").getHref());
                }
            }

            // Get detailed price information for the car offer,
            // which include (RatePeriod, RateCode, Prepay, CreditCardRequired, MobileRate, Discounts, Mileages).
            if (car.getRateDetails() != null) {
                LOGGER.info("Rate Details: {}", car.getRateDetails());
            }

            // Get the detailed pricing information for the rental of the car offer,
            // which include (RatePeriodUnitPrice, BasePrice, TotalPrice).
            if (car.getPrice() != null) {
                LOGGER.info("Car Price: {}", car.getPrice());
            }

            // Get a list of additional fees, including both mandatory and optional fees.
            if (car.getAdditionalFees() != null) {
                LOGGER.info("Additional Fees: {}", car.getAdditionalFees());
            }

            // Get the cancellation policy for the car offer,
            // which include (FreeCancellationEndDateTime, Cancellable, PenaltyRules, NonCancellableDateTimeRange, FreeCancellation).
            LOGGER.info("Cancellation Policy: {}", car.getCancellationPolicy());

            // Container for no show penalty element.
            if (car.getNoShowPenalty() != null) {
                LOGGER.info("No Show Penalty: {}", car.getNoShowPenalty());
            }

            LOGGER.info("========== Property End ==========");
        });
        LOGGER.info("========== End QuickStartExample ==========");
        System.exit(0);
    }
}