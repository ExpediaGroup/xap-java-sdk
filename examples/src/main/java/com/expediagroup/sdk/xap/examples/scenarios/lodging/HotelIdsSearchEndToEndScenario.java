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
package com.expediagroup.sdk.xap.examples.scenarios.lodging;


import com.expediagroup.sdk.rest.model.Response;
import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.examples.scenarios.ExampleScenario;
import com.expediagroup.sdk.xap.model.Hotel;
import com.expediagroup.sdk.xap.model.HotelListingsResponse;
import com.expediagroup.sdk.xap.model.PresignedUrlResponse;
import com.expediagroup.sdk.xap.model.RoomType;
import com.expediagroup.sdk.xap.operations.GetFeedDownloadUrlOperation;
import com.expediagroup.sdk.xap.operations.GetFeedDownloadUrlOperationParams;
import com.expediagroup.sdk.xap.operations.GetLodgingListingsOperation;
import com.expediagroup.sdk.xap.operations.GetLodgingListingsOperationParams;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example demonstrates how to retrieve accessible property ids from SDP DownloadURL API and
 * then get the content and prices of these properties using the Lodging Listings API.
 *
 * <p>This is a common scenario for meta site partners. In practice, you can build a cache with the
 * property id list, content and prices to improve respond time of your pages.
 */
public class HotelIdsSearchEndToEndScenario extends ExampleScenario {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(HotelIdsSearchEndToEndScenario.class);
    /**
     * This field limits the number of line to read from the SDP DownloadURL API Listings file to
     * reduce time to run the example.
     * If the first 20 properties from the file are not accessible OR available when you run this
     * example, it may end with "No accessible property ids found." OR NO_RESULT_FOUND. In that case,
     * you can adjust the property count to get more properties.
     */
    private static final int SAMPLE_ITEMS_RESTRICTION = 20;

    public HotelIdsSearchEndToEndScenario(XapClient client) {
        super(client);
    }

    /**
     * Display the result of the operations.
     *
     * @param hotelListingsResponse The response of the Lodging Listings API.
     */
    private static void displayResult(HotelListingsResponse hotelListingsResponse) {
        LOGGER.info("====================== Executing Step III: DisplayResult =======================");
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
        LOGGER.info("====================== Step III: DisplayResult Executed ========================");
    }

    @Override
    public void run() {
        LOGGER.info(
            "======================== Running HotelIdsSearchEndToEndScenario =======================");

        List<String> propertyIds = getPropertyIdsFromDownloadUrl();
        HotelListingsResponse hotelListingsResponse = getPropertiesFromLodgingListings(propertyIds);
        displayResult(hotelListingsResponse);

        LOGGER.info(
            "========================== End HotelIdsSearchEndToEndScenario =========================");
    }

    /**
     * Retrieve accessible property ids from SDP DownloadURL API.
     *
     * @return property ids
     */
    private List<String> getPropertyIdsFromDownloadUrl() {
        LOGGER.info(
            "==================== Executing Step I: getPropertyIdsFromDownloadUrl ===================");

        GetFeedDownloadUrlOperationParams getPropertyIdListParams =
            GetFeedDownloadUrlOperationParams.builder()
                // Use the type LISTINGS to get the list of accessible property ids.
                .type(GetFeedDownloadUrlOperationParams.Type.LISTINGS)
                // Without any filters, this operation will return the information of all lodging
                // properties in en_US by default.
                .build();

        Response<PresignedUrlResponse> downloadUrlListingsResponse =
            client.execute(new GetFeedDownloadUrlOperation(getPropertyIdListParams));

        if (downloadUrlListingsResponse.getData() == null
            || downloadUrlListingsResponse.getData().getBestMatchedFile() == null) {
            throw new IllegalStateException("No listings file found");
        }

        // The download URL points to a zip file containing various jsonl files.
        // Each line in the jsonl files contains a json object representing a property.
        // For demonstration purposes, we will only read a few properties from the file without
        // downloading the entire file.
        String listingsDownloadUrl = downloadUrlListingsResponse.getData()
            .getBestMatchedFile()
            .getDownloadUrl();
        LOGGER.info("Listings Download URL: {}", listingsDownloadUrl);

        // Read property ids from the file.
        List<String> propertyIds = getPropertyIdsFromListingsFile(listingsDownloadUrl);

        if (propertyIds.isEmpty()) {
            throw new IllegalStateException("No accessible property ids found.");
        }
        LOGGER.info("Accessible Property Ids: {}", propertyIds);

        LOGGER.info(
            "==================== Step I: getPropertyIdsFromDownloadUrl Executed ====================");
        return propertyIds;
    }

    /**
     * Get prices of the properties using the Lodging Listings API.
     *
     * @param propertyIds The property ids to get the prices.
     * @return The response of the Lodging Listings API.
     */
    private HotelListingsResponse getPropertiesFromLodgingListings(List<String> propertyIds) {
        LOGGER.info(
            "================ Step II: Executing getPropertiesFromLodgingListings ===============");

        GetLodgingListingsOperationParams getLodgingListingsOperationParams =
            GetLodgingListingsOperationParams.builder()
                .partnerTransactionId(PARTNER_TRANSACTION_ID)
                // Use the property ids read from the file
                .ecomHotelIds(new HashSet<>(propertyIds))
                // The links to return, WEB includes WS (Web Search Result Page)
                // and WD (Web Details Page)
                .links(Collections.singletonList(GetLodgingListingsOperationParams.Links.WEB))
                // Check-in 5 days from now
                .checkIn(LocalDate.now().plusDays(5))
                // Check-out 10 days from now
                .checkOut(LocalDate.now().plusDays(10))
                // Filter the properties that are available only
                .availOnly(true)
                // Use the default occupancy: 2 adults in one room
                .build();

        HotelListingsResponse hotelListingsResponse =
            client.execute(new GetLodgingListingsOperation(getLodgingListingsOperationParams))
                .getData();

        LOGGER.info(
            "================ Step II: getPropertiesFromLodgingListings Executed ================");
        return hotelListingsResponse;
    }

    /**
     * Reads given number of property ids from the file pointed by the download URL.
     *
     * @param downloadUrl The download URL of the zip file containing the property information.
     * @return A list of property ids read from the file.
     */
    private List<String> getPropertyIdsFromListingsFile(String downloadUrl) {
        List<String> propertyIds = new ArrayList<>();
        HttpURLConnection connection = null;
        try {
            // Open a connection to the URL
            URL url = new URL(downloadUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            try (ZipInputStream zipStream = new ZipInputStream(connection.getInputStream())) {
                ZipEntry entry;
                while ((entry = zipStream.getNextEntry()) != null) {
                    if (entry.getName().endsWith(".jsonl")) {
                        LOGGER.info("Reading property ids from file: {}", entry.getName());
                        try (BufferedReader reader = new BufferedReader(new InputStreamReader(zipStream))) {
                            String line;
                            ObjectMapper objectMapper = new ObjectMapper();
                            while ((line = reader.readLine()) != null
                                && propertyIds.size() < SAMPLE_ITEMS_RESTRICTION) {
                                // Parse the property id from the json object
                                // An example json line from the jsonl file:
                /*
                {
                  "propertyId": {
                    "expedia": "1234567",
                    "hcom": "123456789",
                    "vrbo": "123.1234567.7654321"
                  },
                  "bookable": {
                    "expedia": true,
                    "hcom": true,
                    "vrbo": true
                  },
                  "propertyType": {
                    "id": 16,
                    "name": "Apartment"
                  },
                  "lastUpdated": "10-27-2024 13:41:16",
                  "country": "France",
                  "inventorySource": "vrbo",
                  "referencePrice": {
                    "value": "89.52",
                    "currency": "USD"
                  },
                  "vrboPropertyType": {
                    "instantBook": true
                  }
                }
                */
                                JsonNode jsonNode = objectMapper.readTree(line);
                                // Check if the property is accessible from Lodging Listings API
                                // (Vrbo properties that are not instantBookable are not accessible for now)
                                if (!jsonNode.get("propertyId").get("vrbo").asText().isEmpty()
                                    && jsonNode.has("vrboPropertyType")
                                    && !jsonNode.get("vrboPropertyType").get("instantBook").asBoolean()
                                ) {
                                    // Skip the property if it is not an instant bookable Vrbo property
                                    continue;
                                } else {
                                    // Get the Expedia property id for the Lodging Listings API
                                    propertyIds.add(jsonNode.get("propertyId").get("expedia").asText());
                                }
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            LOGGER.error("Error reading property ids from download URL", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return propertyIds;
    }
}
