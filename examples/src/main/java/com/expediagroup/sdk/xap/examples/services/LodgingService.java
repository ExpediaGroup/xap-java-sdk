/*
 * Copyright (C) 2024 Expedia, Inc.
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

package com.expediagroup.sdk.xap.examples.services;

import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.models.AvailabilityCalendarResponse;
import com.expediagroup.sdk.xap.models.HotelListingsResponse;
import com.expediagroup.sdk.xap.models.LodgingQuotesResponse;
import com.expediagroup.sdk.xap.models.Room;
import com.expediagroup.sdk.xap.operations.GetLodgingAvailabilityCalendarsOperation;
import com.expediagroup.sdk.xap.operations.GetLodgingAvailabilityCalendarsOperationParams;
import com.expediagroup.sdk.xap.operations.GetLodgingListingsOperation;
import com.expediagroup.sdk.xap.operations.GetLodgingListingsOperationParams;
import com.expediagroup.sdk.xap.operations.GetLodgingQuotesOperation;
import com.expediagroup.sdk.xap.operations.GetLodgingQuotesOperationParams;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

/**
 * Service class to execute lodging operations.
 */
public class LodgingService extends XapService {
  /**
   * Get availability calendars.
   *
   * @return AvailabilityCalendarResponse
   */
  public AvailabilityCalendarResponse getAvailabilityCalendars() {
    // Build the query parameters with GetLodgingAvailabilityCalendarsOperationParams
    GetLodgingAvailabilityCalendarsOperationParams availabilityCalendarsOperationParams =
        GetLodgingAvailabilityCalendarsOperationParams.builder()
            .partnerTransactionId(UUID.randomUUID().toString())
            //Comma-separated list of Expedia Property IDs.
            .propertyIds("87704892,36238803")
            .build();

    String key = System.getProperty("com.expediagroup.xapjavasdk.vrbokey");
    String secret = System.getProperty("com.expediagroup.xapjavasdk.vrbosecret");

    XapClient xapClient = createClient(key, secret);

    return xapClient.execute(
            new GetLodgingAvailabilityCalendarsOperation(availabilityCalendarsOperationParams))
        .getData();

    // If you want to use the async method, you can use the following code:
    // ---------------------------------------------------------------
    // CompletableFuture<Response<AvailabilityCalendarResponse>> completableFuture =
    //   xapClient.executeAsync(
    //     new GetLodgingAvailabilityCalendarsOperation(availabilityCalendarsOperationParams));
    // completableFuture.thenAccept(availCalendarResponse -> {
    //   // Your code here
    // });
    // ---------------------------------------------------------------
  }

  /**
   * Get lodging listings.
   *
   * @return HotelListingsResponse
   */
  public HotelListingsResponse getListings() {
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
            .partnerTransactionId(UUID.randomUUID().toString())
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

    String key = System.getProperty("com.expediagroup.xapjavasdk.apikey");
    String secret = System.getProperty("com.expediagroup.xapjavasdk.apisecret");

    XapClient xapClient = createClient(key, secret);

    // Execute the operation and get the HotelListingsResponse
    return xapClient.execute(
        new GetLodgingListingsOperation(getLodgingListingsOperationParams)).getData();

    // If you want to use the async method, you can use the following code:
    // ---------------------------------------------------------------
    // CompletableFuture<Response<HotelListingsResponse>> completableFuture =
    //   xapClient.executeAsync(
    //     new GetLodgingListingsOperation(getLodgingListingsOperationParams));
    // completableFuture.thenAccept(hotelListingsResponse -> {
    //   // Your code here
    // });
    // ---------------------------------------------------------------
  }

  /**
   * Get lodging quotes.
   *
   * @return LodgingQuotesResponse
   */
  public LodgingQuotesResponse getQuotes() {
    // Build the occupancy
    ArrayList<Room> rooms = new ArrayList<>();
    // The first room, with 2 adult
    rooms.add(Room.builder().adults(2L).childAges(null).build());

    // Build the query parameters with GetLodgingQuotesOperationParams
    GetLodgingQuotesOperationParams quotesOperationParams =
        GetLodgingQuotesOperationParams.builder()
            .partnerTransactionId(UUID.randomUUID().toString())
            // Check-in 5 days from now
            .checkIn(LocalDate.now().plusDays(5))
            // Check-out 10 days from now
            .checkOut(LocalDate.now().plusDays(10))
            // Comma-separated list of Expedia Property IDs.
            .propertyIds("87704892,12410858")
            // The links to return, WEB includes WS (Web Search Result Page) and
            // WD (Web Details Page)
            .links(Collections.singletonList(GetLodgingQuotesOperationParams.Links.WEB))
            .rooms(rooms)
            .build();

    // Execute the operation and get the QuotesResponse
    String key = System.getProperty("com.expediagroup.xapjavasdk.vrbokey");
    String secret = System.getProperty("com.expediagroup.xapjavasdk.vrbosecret");

    XapClient xapClient = createClient(key, secret);

    return xapClient.execute(
        new GetLodgingQuotesOperation(quotesOperationParams)).getData();
    // If you want to use the async method, you can use the following code:
    // ---------------------------------------------------------------
    // CompletableFuture<Response<LodgingQuotesResponse>> completableFuture =
    //   xapClient.executeAsync(
    //     new GetLodgingQuotesOperation(quotesOperationParams));
    // completableFuture.thenAccept(quotesResponse -> {
    //   // Your code here
    // });
    // ---------------------------------------------------------------
  }
}
