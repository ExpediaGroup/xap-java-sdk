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

package com.expediagroup.sdk.xap.examples.scenarios.lodging;

import com.expediagroup.sdk.xap.examples.scenarios.XapScenario;
import com.expediagroup.sdk.xap.examples.services.LodgingService;
import com.expediagroup.sdk.xap.models.AvailabilityCalendarResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example demonstrates how to use Availability Calendar api with simple search.
 */
public class GetAvailabilityCalendarsScenario implements XapScenario {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(GetAvailabilityCalendarsScenario.class);

  private final LodgingService lodgingService =
      new LodgingService();

  @Override
  public void run() {
    // This example returns the availability of each day for a range of dates for given Expedia
    // lodging properties.

    LOGGER.info(
        "====================== Running GetAvailabilityCalendarsScenario ======================");

    LOGGER.info(
        "================= Executing GetLodgingAvailabilityCalendars Operation ================");

    // Execute the operation and get the AvailabilityCalendarsResponse
    AvailabilityCalendarResponse availabilityCalendars =
        lodgingService.getAvailabilityCalendars();

    LOGGER.info(
        "================== GetLodgingAvailabilityCalendarsOperation Executed =================");

    if (availabilityCalendars == null || availabilityCalendars.getAvailabilityCalendars() == null
        || availabilityCalendars.getAvailabilityCalendars().isEmpty()) {
      throw new IllegalStateException("No properties found.");
    }

    // The AvailabilityCalendarsResponse contains a transaction ID for troubleshooting
    LOGGER.info("Transaction ID: {}", availabilityCalendars.getTransactionId());

    // To access the properties, iterate through the list of properties
    availabilityCalendars.getAvailabilityCalendars().forEach(availCalendar -> {
      LOGGER.info("========== Property:{} Start ==========", availCalendar.getPropertyId());

      // Availability of property: A string of codes that shows property availability, one for every
      // day in the specified date range.
      // Valid values include Y (available) and N (unavailable).
      LOGGER.info("Availability: {}", availCalendar.getAvailability());

      LOGGER.info(
          "==================================== Property End ===================================");

      LOGGER.info(
          "======================== End GetAvailabilityCalendarsScenario =======================");
    });
  }
}
