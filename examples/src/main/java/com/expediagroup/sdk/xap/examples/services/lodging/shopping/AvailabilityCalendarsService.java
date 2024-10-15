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
import com.expediagroup.sdk.xap.models.AvailabilityCalendarResponse;
import com.expediagroup.sdk.xap.operations.GetLodgingAvailabilityCalendarsOperation;
import com.expediagroup.sdk.xap.operations.GetLodgingAvailabilityCalendarsOperationParams;

/**
 * Service class to get availability calendars.
 */
public class AvailabilityCalendarsService extends XapService {
  /**
   * Get availability calendars.

   * @return AvailabilityCalendarResponse
   */
  public AvailabilityCalendarResponse getAvailabilityCalendars() {
    // Build the query parameters with GetLodgingAvailabilityCalendarsOperationParams
    GetLodgingAvailabilityCalendarsOperationParams availabilityCalendarsOperationParams =
        GetLodgingAvailabilityCalendarsOperationParams.builder()
            .partnerTransactionId(Constants.PARTNER_TRANSACTION_ID)
            //Comma-separated list of Expedia Property IDs.
            .propertyIds("87704892,36238803")
            .build();

    return xapClient.execute(
            new GetLodgingAvailabilityCalendarsOperation(availabilityCalendarsOperationParams))
        .getData();

    // If you want to use the async method, you can use the following code:
    // ---------------------------------------------------------------
    // CompletableFuture<Response<AvailabilityCalendarResponse>> completableFuture =
    //   Constants.XAP_CLIENT.executeAsync(
    //     new GetLodgingAvailabilityCalendarsOperation(availabilityCalendarsOperationParams));
    // completableFuture.thenAccept(availCalendarResponse -> {
    //   // Your code here
    // });
    // ---------------------------------------------------------------
  }
}
