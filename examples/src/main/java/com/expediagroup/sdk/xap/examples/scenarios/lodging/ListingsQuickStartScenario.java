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

package com.expediagroup.sdk.xap.examples.scenarios.lodging;


import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.examples.scenarios.XapScenario;
import com.expediagroup.sdk.xap.models.Hotel;
import com.expediagroup.sdk.xap.models.HotelListingsResponse;
import com.expediagroup.sdk.xap.models.Room;
import com.expediagroup.sdk.xap.models.RoomType;
import com.expediagroup.sdk.xap.operations.GetLodgingListingsOperation;
import com.expediagroup.sdk.xap.operations.GetLodgingListingsOperationParams;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example demonstrates how to search for properties with a location keyword with filters
 * applied.
 */
public class ListingsQuickStartScenario implements XapScenario {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListingsQuickStartScenario.class);

    public static void main(String[] args) {
        new ListingsQuickStartScenario().run();
        System.exit(0);
    }

    @Override
    public void run() {
        // This example will search for properties with the following criteria:
        // 1. Occupancy of 1 adult in the first room and 2 adults and 2 children (10 and 12 years old)
        // in the second room;
        // 2. Properties located within 10 km of the Space Needle in Seattle;
        // 3. Check-in date 5 days from now, check-out date 10 days from now;
        // 4. Return web links to Expedia website;
        // 5. Limit the results to 5 properties;
        // 6. Order the results by price in ascending order.

        LOGGER.info(
            "========================== Running ListingsQuickStartScenario =========================");

        LOGGER.info(
            "======================== Executing GetLodgingListingsOperation =======================");

        // Build the occupancy
        ArrayList<Room> rooms = new ArrayList<>();
        // The first room, with 1 adult
        rooms.add(Room.builder().adults(1L).build());
        // The second room, with 2 adults and 2 children
        ArrayList<Long> childrenAges = new ArrayList<>();
        childrenAges.add(10L);
        childrenAges.add(12L);
        rooms.add(Room.builder().adults(2L).childAges(childrenAges).build());

        // Build the query parameters with GetLodgingListingsOperationParams
        GetLodgingListingsOperationParams getLodgingListingsOperationParams =
            GetLodgingListingsOperationParams.builder()
                .partnerTransactionId(PARTNER_TRANSACTION_ID)
                // The location keyword can be a city, address, airport or a landmark.
                .locationKeyword("Space Needle, Seattle")
                // The radius specifies the size of search area around the location keyword.
                // The default value is 25.
                .radius(10)
                // The unit specifies the unit of the radius. The default value is KM.
                .unit(GetLodgingListingsOperationParams.Unit.KM)
                // Check-in 5 days from now
                .checkIn(LocalDate.now().plusDays(5))
                // Check-out 10 days from now
                .checkOut(LocalDate.now().plusDays(10))
                // The occupancy
                .rooms(rooms)
                // The links to return, WEB includes WS (Web Search Result Page)
                // and WD (Web Details Page)
                .links(Collections.singletonList(GetLodgingListingsOperationParams.Links.WEB))
                // Limit the results to 5 properties
                .limit(5)
                // Order the results by price in ascending order
                .sortType(GetLodgingListingsOperationParams.SortType.PRICE)
                .sortOrder(GetLodgingListingsOperationParams.SortOrder.ASC)
                .build();

        XapClient xapClient = createClient();

        // Execute the operation and get the HotelListingsResponse
        HotelListingsResponse hotelListingsResponse =
            xapClient.execute(new GetLodgingListingsOperation(getLodgingListingsOperationParams))
                .getData();

        // If you want to use the async method, you can use the following code:
        // ---------------------------------------------------------------
        // CompletableFuture<Response<HotelListingsResponse>> completableFuture =
        //   xapClient.executeAsync(
        //     new GetLodgingListingsOperation(getLodgingListingsOperationParams));
        // completableFuture.thenAccept(hotelListingsResponse -> {
        //   // Your code here
        // });
        // ---------------------------------------------------------------

        LOGGER.info(
            "======================== GetLodgingListingsOperation Executed ========================");

        if (hotelListingsResponse == null || hotelListingsResponse.getHotels() == null
            || hotelListingsResponse.getHotels().isEmpty()) {
            throw new IllegalStateException("No properties found.");
        }

        // The HotelListingsResponse contains a transaction ID for troubleshooting
        LOGGER.info("Transaction ID: {}", hotelListingsResponse.getTransactionId());

        // To access the properties, iterate through the list of hotel properties
        hotelListingsResponse.getHotels().forEach(hotel -> {
            // Check if the property is available
            if (Hotel.Status.AVAILABLE != hotel.getStatus()) {
                LOGGER.info("Property {} is not available.", hotel.getId());
                return;
            }
            LOGGER.info(
                "=================================== Property Start ===================================");
            // To get the property name
            if (StringUtils.isNotEmpty(hotel.getName())) {
                LOGGER.info("Property Name: {}", hotel.getName());
            }
            // To get the property address
            if (hotel.getLocation() != null) {
                LOGGER.info("Property Address: {}", hotel.getLocation().getAddress());
            }
            // To get the property thumbnail URL
            if (StringUtils.isNotEmpty(hotel.getThumbnailUrl())) {
                LOGGER.info("Thumbnail URL: {}", hotel.getThumbnailUrl());
            }
            // To get the star rating of the property. The value is between 1.0 and 5.0
            // in a 0.5 increment.
            if (hotel.getStarRating() != null) {
                LOGGER.info("Star Rating: {}", hotel.getStarRating().getValue());
            }
            // To get the guest rating of the property. The value is between 1.0 and 5.0
            // in a 0.1 increment.
            if (StringUtils.isNotEmpty(hotel.getGuestRating())) {
                LOGGER.info("Guest Rating: {}", hotel.getGuestRating());
            }
            // To get the total number of reviews for the property
            if (hotel.getGuestReviewCount() != null) {
                LOGGER.info("Review Count: {}", hotel.getGuestReviewCount());
            }
            if (hotel.getRoomTypes() != null && !hotel.getRoomTypes().isEmpty()) {
                // To get the first room type information
                RoomType roomType = hotel.getRoomTypes().get(0);
                if (StringUtils.isNotEmpty(roomType.getDescription())) {
                    LOGGER.info("Room Type: {}", roomType.getDescription());
                }
                if (roomType.getPrice() != null) {
                    // To get the total price of the room type
                    if (roomType.getPrice().getTotalPrice() != null) {
                        LOGGER.info("Price: {}, Currency: {}",
                            roomType.getPrice().getTotalPrice().getValue(),
                            roomType.getPrice().getTotalPrice().getCurrency());
                    }
                    // To get the average nightly rate of the room type
                    if (roomType.getPrice().getAvgNightlyRate() != null) {
                        LOGGER.info("Average Nightly Rate: {}, Currency: {}",
                            roomType.getPrice().getAvgNightlyRate().getValue(),
                            roomType.getPrice().getAvgNightlyRate().getCurrency());
                    }
                }
                // To get the free cancellation flag of the selected room
                if (roomType.getRatePlans() != null && !roomType.getRatePlans().isEmpty()
                    && roomType.getRatePlans().get(0).getCancellationPolicy() != null) {
                    LOGGER.info("Free Cancellation: {}",
                        roomType.getRatePlans().get(0).getCancellationPolicy().getFreeCancellation());
                }
                if (roomType.getLinks() != null) {
                    // To get the deeplink to the Expedia Web Search Result Page
                    if (roomType.getLinks().getWebSearchResult() != null) {
                        LOGGER.info("WebSearchResult Link: {}",
                            roomType.getLinks().getWebSearchResult().getHref());
                    }
                    // To get the deeplink to the Expedia Web Details Page
                    if (roomType.getLinks().getWebDetails() != null) {
                        LOGGER.info("WebDetails Link: {}", roomType.getLinks().getWebDetails().getHref());
                    }
                }
            }
            LOGGER.info(
                "==================================== Property End ====================================");
        });

        LOGGER.info(
            "============================ End ListingsQuickStartScenario ===========================");
    }
}
