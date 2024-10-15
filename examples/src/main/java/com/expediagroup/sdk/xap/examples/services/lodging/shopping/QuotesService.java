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

package com.expediagroup.sdk.xap.examples.services.lodging.shopping;

import com.expediagroup.sdk.xap.examples.Constants;
import com.expediagroup.sdk.xap.examples.services.XapService;
import com.expediagroup.sdk.xap.models.LodgingQuotesResponse;
import com.expediagroup.sdk.xap.models.Room;
import com.expediagroup.sdk.xap.operations.GetLodgingQuotesOperation;
import com.expediagroup.sdk.xap.operations.GetLodgingQuotesOperationParams;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Service class to get lodging quotes.
 */
public class QuotesService extends XapService {
  /**
   * Get lodging quotes.

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
            .partnerTransactionId(Constants.PARTNER_TRANSACTION_ID)
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
    return xapClient.execute(
        new GetLodgingQuotesOperation(quotesOperationParams)).getData();
    // If you want to use the async method, you can use the following code:
    // ---------------------------------------------------------------
    // CompletableFuture<Response<LodgingQuotesResponse>> completableFuture =
    //   Constants.XAP_CLIENT.executeAsync(
    //     new GetLodgingQuotesOperation(quotesOperationParams));
    // completableFuture.thenAccept(quotesResponse -> {
    //   // Your code here
    // });
    // ---------------------------------------------------------------
  }
}
