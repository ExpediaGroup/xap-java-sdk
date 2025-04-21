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

import com.expediagroup.sdk.rest.model.Response;
import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.examples.scenarios.XapScenario;
import com.expediagroup.sdk.xap.models.CarDetails;
import com.expediagroup.sdk.xap.models.CarDetailsResponse;
import com.expediagroup.sdk.xap.models.CarListingsResponse;
import com.expediagroup.sdk.xap.models.VehicleDetails;
import com.expediagroup.sdk.xap.operations.GetCarDetailsOperation;
import com.expediagroup.sdk.xap.operations.GetCarDetailsOperationParams;
import com.expediagroup.sdk.xap.operations.GetCarsListingsOperation;
import com.expediagroup.sdk.xap.operations.GetCarsListingsOperationParams;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example demonstrates how to retrieve CarDetails information using the Car Details DeepLink
 * obtained from the car listing.
 */
public class CarDetailsQuickStartScenario implements XapScenario {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarDetailsQuickStartScenario.class);

    /**
     * Summary: main function.
     */
    public static void main(String[] args) {
        new CarListingsQuickStartScenario().run();
        new CarDetailsQuickStartScenario().run();
        System.exit(0);
    }

    /**
     * Summary: split URL into components.
     */
    public static String[] splitUrl(String url) {
        String[] parts = url.split("\\?");
        String base = parts[0];
        String query = parts[1];

        String offerToken = base.substring(base.lastIndexOf("/") + 1);
        String[] queryParams = query.split("&");
        String price = queryParams[0].split("=")[1];
        String currency = queryParams[1].split("=")[1];

        return new String[] {offerToken, price, currency};
    }

    @Override
    public void run() {
        LOGGER.info("========== Start QuickStartScenario ==========");

        LOGGER.info("========== Car Listing Start ==========");

        // This example demonstrates how to obtain the Car Details Deep Link from the CarListing.
        // For information on using car search, refer to
        // car/shopping/listings/ListingsQuickStartExample.
        List<GetCarsListingsOperationParams.Links> linksList = new ArrayList<>();
        linksList.add(GetCarsListingsOperationParams.Links.AD);
        linksList.add(GetCarsListingsOperationParams.Links.WS);
        linksList.add(GetCarsListingsOperationParams.Links.WD);
        GetCarsListingsOperationParams getCarsListingsOperationParams =
                GetCarsListingsOperationParams.builder()
                        .partnerTransactionId("EWSCar_Automation")
                        .dropOffAirport("MCO")
                        .pickupAirport("MCO")
                        .pickupTime(
                                LocalDateTime.now()
                                        .withNano(0)
                                        .withSecond(0)
                                        .withHour(10)
                                        .plusDays(10)
                                        .plusMonths(1)
                        )
                        .dropOffTime(
                                LocalDateTime.now()
                                        .withNano(0)
                                        .withSecond(0)
                                        .withHour(10)
                                        .plusDays(15)
                                        .plusMonths(1)
                        )
                        .limit(1)
                        .links(linksList)
                        .build();

        XapClient xapClient = createClient();
        GetCarsListingsOperation getCarsListingsOperation =
                new GetCarsListingsOperation(getCarsListingsOperationParams);
        Response<CarListingsResponse> carListingsResponse = xapClient.execute(getCarsListingsOperation);

        LOGGER.info("========== Car Listing Property End ==========");

        // Iterate through the car listings and retrieve the Car Details Deep Link.
        LOGGER.info("========== Car Details Start ==========");
        Objects.requireNonNull(carListingsResponse.getData().getCars()).forEach(car -> {
            if (!car.getLinks().get("ApiDetails").getHref().isEmpty()) {
                // Retrieve the Car Details Deep Link from the car listing.
                LOGGER.info("Car Details Deep Link: " + car.getLinks().get("ApiDetails").getHref());
                String[] strings = splitUrl(car.getLinks().get("ApiDetails").getHref());

                // Retrieve the Car Details information using the Car Details Deep Link, which
                // includes (offerToken, price, currency)
                GetCarDetailsOperationParams getCarDetailsOperationParams =
                        GetCarDetailsOperationParams.builder().partnerTransactionId("EWSCar_Automation")
                                .offerToken(strings[0]).price(strings[1]).currency(strings[2]).build();

                // Execute the operation and get the CarDetailsResponse
                LOGGER.info("========== Executing GetCarDetailsOperation ==========");
                CarDetailsResponse carDetailsResponse = xapClient.execute(
                        new GetCarDetailsOperation(getCarDetailsOperationParams)).getData();
                LOGGER.info("========== GetCarDetailsOperation Executed ==========");

                if (carDetailsResponse == null || carDetailsResponse.getLinks() == null) {
                    throw new IllegalStateException("No car found.");
                }

                LOGGER.info("========== Car Properties Start ==========");

                // The CarDetailsResponse contains a transaction ID for troubleshooting
                LOGGER.info("Transaction ID: {}", carDetailsResponse.getTransactionId());

                // List Container for warning messages
                if (carDetailsResponse.getWarnings() != null) {
                    LOGGER.info("Warnings: {}", carDetailsResponse.getWarnings());
                }

                // Details of requested car.
                // Details refer to the CarDetails Section table below.
                if (carDetailsResponse.getValidFormsOfPayment() != null) {
                    LOGGER.info("Valid Forms Of Payment: {}", carDetailsResponse.getValidFormsOfPayment());
                }

                // A map of links to other Car APIs.
                if (carDetailsResponse.getLinks() != null) {
                    LOGGER.info("Links: {}", carDetailsResponse.getLinks());
                }

                // Specific information for a car.
                CarDetails carDetails = carDetailsResponse.getCarDetails();
                VehicleDetails vehicleDetails = carDetails.getVehicleDetails();
                if (vehicleDetails.getMake() != null) {
                    //Car manufacturer and model.
                    LOGGER.info("Make: {}", vehicleDetails.getMake());
                }

                // Car category and type.
                LOGGER.info("Car Class: {}", vehicleDetails.getCarClass());

                // Minimal car door count.
                if (vehicleDetails.getMinDoors() != null) {
                    LOGGER.info("Min Doors: {}", vehicleDetails.getMinDoors());
                }

                // Maximum car door count.
                if (vehicleDetails.getMaxDoors() != null) {
                    LOGGER.info("Max Doors: {}", vehicleDetails.getMaxDoors());
                }

                // Car fuel information.
                if (vehicleDetails.getFuelLevel() != null) {
                    // Fuel level of the car.
                    LOGGER.info("Fuel Level: {}", vehicleDetails.getFuelLevel());
                }

                // Car category.
                LOGGER.info("Car Category: {}", vehicleDetails.getCarCategory());

                // Car type.
                LOGGER.info("Car Type: {}", vehicleDetails.getCarType());

                // Car transmission and drive.
                LOGGER.info("Transmission Drive: {}", vehicleDetails.getTransmissionDrive());

                // Car fuel type and whether Air Conditioning is included.
                LOGGER.info("Fuel AC: {}", vehicleDetails.getFuelAC());

                // Capacity for car's properties, which include AdultCount, ChildCount, SmallLuggageCount
                // and LargeLuggageCount.
                if (vehicleDetails.getCapacity() != null) {
                    LOGGER.info("Capacity: {}", vehicleDetails.getCapacity());
                }

                // Car rental supplier.
                LOGGER.info(" : {}", carDetails.getSupplier());

                // Pickup information
                LOGGER.info("Pickup Details: {}", carDetails.getPickupDetails());

                // Drop off information, include drop off date time and drop off location information.
                LOGGER.info("Drop Off Details: {}", carDetails.getDropOffDetails());

                // The rate information for a car product.
                LOGGER.info("Rate Details: {}", carDetails.getRateDetails());

                // Base price per rate period.
                LOGGER.info("Price: {}", carDetails.getPrice());

                // List of TaxesAndFees Details.
                if (carDetails.getTaxesAndFeesDetails() != null) {
                    LOGGER.info("Taxes And Fees Details: {}", carDetails.getTaxesAndFeesDetails());
                }

                // List of ExtraFeesDetails
                if (carDetails.getExtraFeesDetails() != null) {
                    LOGGER.info("Extra Fees Details: {}", carDetails.getExtraFeesDetails());
                }

                // ReferencePrice is the totalPrice for the comparable standalone car, when there is
                // a discounted car or need to show strike through pricing.
                if (carDetails.getReferencePrice() != null) {
                    LOGGER.info("Reference Price: {}", carDetails.getReferencePrice());
                }

                // List of additional fees including both mandatory and optional fees such as young driver
                // fee/drop off fee /CollisionDamageWaiver.
                if (carDetails.getAdditionalFees() != null) {
                    LOGGER.info("Additional Fees: {}", carDetails.getAdditionalFees());
                }

                // Description and costs of any optional special equipment that may be rented with the car.
                if (carDetails.getSpecialEquipments() != null) {
                    LOGGER.info("Special Equipments: {}", carDetails.getSpecialEquipments());
                }

                // Limitations that are part of this rental agreement.
                if (carDetails.getRentalLimits() != null) {
                    LOGGER.info("Rental Limits: {}", carDetails.getRentalLimits());
                }

                // Cancellation Policy Container.
                LOGGER.info("Cancellation Policy: {}", carDetails.getCancellationPolicy());

                // Container for no show penalty
                if (carDetails.getNoShowPenalty() != null) {
                    LOGGER.info("No Show Penalty: {}", carDetails.getNoShowPenalty());
                }

                // A list of policies that apply to this car rental.
                if (carDetails.getCarPolicies() != null) {
                    LOGGER.info("Policies: {}", carDetails.getCarPolicies());
                }

                // List of image resources of the car product.
                if (carDetails.getImages() != null) {
                    LOGGER.info("Images: {}", carDetails.getImages());
                }

                LOGGER.info("========== Property End ==========");
            }

        });

        LOGGER.info("========== End QuickStartExample ==========");
    }

}
