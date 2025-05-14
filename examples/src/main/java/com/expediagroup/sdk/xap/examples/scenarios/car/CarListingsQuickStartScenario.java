/*
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

package com.expediagroup.sdk.xap.examples.scenarios.car;

import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.examples.scenarios.XapScenario;
import com.expediagroup.sdk.xap.models.CarListingsResponse;
import com.expediagroup.sdk.xap.operations.GetCarsListingsOperation;
import com.expediagroup.sdk.xap.operations.GetCarsListingsOperationParams;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example demonstrates how to search for car properties with an Airport keyword with filters
 * applied.
 */
public class CarListingsQuickStartScenario implements XapScenario {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarListingsQuickStartScenario.class);

    /**
     * Summary: main function.
     */
    public static void main(String[] args) {
        new CarListingsQuickStartScenario().run();
        System.exit(0);
    }

    @Override
    public void run() {
        LOGGER.info(
            "========================= Running CarListingsQuickStartScenario ========================");

        LOGGER.info(
            "======================== Executing GetCarsListingsOperation =======================");
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

        GetCarsListingsOperationParams getCarsListingsOperationParams =
            GetCarsListingsOperationParams.builder().partnerTransactionId("EWSCar_Automation")
                //Three letter code for the airport at which the customer would like to pick up the car.
                //Supported values: standard 3 letter IATA Airport Code.
                //Cannot coexist with other pickup parameters, only one pickup parameter is allowed per
                //request.
                .pickupAirport("MCO")
                //Three letter code for the airport at which the customer would like to drop off the
                //car.
                //Supported values: standard 3 letter IATA Airport Code.
                //Cannot coexist with other drop off parameters, only one drop off parameter is allowed
                //per request.
                //If no drop off location is specified, it is assumed that the customer will be dropping
                //the car off at the same location at which they picked it up.
                .dropOffAirport("MCO")
                //Requested car pickup date and time.
                //Date should be ISO8601 Date format.The supported search window is today to 330 days in
                //the future.
                //(Note that each rental counter has different hours of operation. If you select a time
                //in the middle of the night there may be no inventory available as all locations may be
                //closed.)
                .pickupTime(LocalDateTime.now().plusDays(5))
                //Requested car drop off date and time.
                //Date should be ISO8601 Date format.The supported search window is today to 330 days in
                //the future.
                //The drop-off datetime should occur at least 2 hours after the pickup datetime.
                .dropOffTime(LocalDateTime.now().plusDays(8))
                //The maximum number of search results that will be returned by the query.
                .limit(5).links(linksList).build();
        XapClient xapClient = createClient();

        // Execute the operation and get the CarListingsResponse
        CarListingsResponse carListingsResponse =
            xapClient.execute(new GetCarsListingsOperation(getCarsListingsOperationParams)).getData();
        LOGGER.info(
            "======================== GetCarsListingsOperation Executed ========================");

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
            LOGGER.info("======================== Car Properties Start ========================");

            // Uniquely identifies a Car Offer.
            LOGGER.info("Car Id: {}", car.getId());

            // Specific information for a car.
            LOGGER.info("Car VehicleDetails: {}", car.getVehicleDetails());

            // The supplier of the car being offered.
            LOGGER.info("Car Supplier: {}", car.getSupplier());

            // Get Pickup information of the car.
            LOGGER.info("Pick up Details: {}", car.getPickupDetails());

            // Get Drop off information of the car.
            LOGGER.info("Drop Off Details: {}", car.getDropOffDetails());

            // Get the API details link, web search link, and web details link from the links collection.
            if (!car.getLinks().isEmpty()) {
                if (car.getLinks().get("ApiDetails") != null) {
                    LOGGER.info("ApiDetails Link: {}", car.getLinks().get("ApiDetails"));
                }
                if (car.getLinks().get("WebSearch") != null) {
                    LOGGER.info("WebSearch Link: {}", car.getLinks().get("WebSearch"));
                }
                if (car.getLinks().get("WebDetails") != null) {
                    LOGGER.info("WebDetails Link: {}", car.getLinks().get("WebDetails"));
                }
            }

            // The rate detail information for a car offer.
            if (car.getRateDetails() != null) {
                LOGGER.info("Rate Details: {}", car.getRateDetails());
            }

            // Get the detailed pricing information for the rental of the car offer,
            LOGGER.info("Car Price: {}", car.getPrice());


            // Get a list of additional fees, including both mandatory and optional fees.
            if (car.getAdditionalFees() != null) {
                LOGGER.info("Additional Fees: {}", car.getAdditionalFees());
            }

            // Get the cancellation policy for the car offer,
            LOGGER.info("Cancellation Policy: {}", car.getCancellationPolicy());

            // Container for no show penalty element.
            if (car.getNoShowPenalty() != null) {
                LOGGER.info("No Show Penalty: {}", car.getNoShowPenalty());
            }

            LOGGER.info("======================== Property End ========================");
        });
        LOGGER.info(
            "======================== End CarListingsQuickStartScenario ========================");
    }
}
