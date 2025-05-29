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


import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.examples.scenarios.ExampleScenario;
import com.expediagroup.sdk.xap.model.AvailabilityCalendarResponse;
import com.expediagroup.sdk.xap.operation.GetLodgingAvailabilityCalendarsOperation;
import com.expediagroup.sdk.xap.operation.GetLodgingAvailabilityCalendarsOperationParams;

import java.util.Arrays;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example demonstrates how to use Availability Calendar api with simple search.
 * In terms of how to get property ids, you can refer to {@link VrboPropertySearchEndToEndScenario}.
 *
 * <p>Note: this is a Vrbo scenario. You need a key that is enabled for Vrbo brand to run this.
 */
public class AvailabilityCalendarsQuickStartScenario extends ExampleScenario {

    public AvailabilityCalendarsQuickStartScenario(XapClient client) {
        super(client);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(AvailabilityCalendarsQuickStartScenario.class);

    @Override
    public void run() {
        // This example returns the availability of each day for a range of dates for given Expedia
        // lodging properties.

        LOGGER.info("=================== Running AvailabilityCalendarsQuickStartScenario ===================");

        LOGGER.info("================= Executing GetLodgingAvailabilityCalendars Operation ================");


        // Build the query parameters with GetLodgingAvailabilityCalendarsOperationParams
        GetLodgingAvailabilityCalendarsOperationParams availabilityCalendarsOperationParams =
            GetLodgingAvailabilityCalendarsOperationParams.builder()
                .partnerTransactionId(PARTNER_TRANSACTION_ID)
                // Set of Expedia Property IDs.
                .propertyIds(new HashSet<>(Arrays.asList("87704892", "36960201")))
                .build();

        // Execute the operation and get the AvailabilityCalendarsResponse
        AvailabilityCalendarResponse availabilityCalendarResponse =
            client.execute(new GetLodgingAvailabilityCalendarsOperation(
                availabilityCalendarsOperationParams)).getData();

        LOGGER.info(
            "================== GetLodgingAvailabilityCalendarsOperation Executed =================");

        if (availabilityCalendarResponse.getAvailabilityCalendars() == null || availabilityCalendarResponse.getAvailabilityCalendars().isEmpty()) {
            throw new IllegalStateException("No properties found.");
        }

        // The AvailabilityCalendarsResponse contains a transaction ID for troubleshooting
        LOGGER.info("Transaction ID: {}", availabilityCalendarResponse.getTransactionId());

        // To access the properties, iterate through the list of properties
        availabilityCalendarResponse.getAvailabilityCalendars().forEach(availCalendar -> {
            LOGGER.info("========== Property:{} Start ==========", availCalendar.getPropertyId());

            // Availability of property: A string of codes that shows property availability, one for every
            // day in the specified date range.
            // Valid values include Y (available) and N (unavailable).
            LOGGER.info("Availability: {}", availCalendar.getAvailability());

            LOGGER.info(
                "==================================== Property End ===================================");

            LOGGER.info(
                "===================== End AvailabilityCalendarsQuickStartScenario ====================");
        });
    }
}
