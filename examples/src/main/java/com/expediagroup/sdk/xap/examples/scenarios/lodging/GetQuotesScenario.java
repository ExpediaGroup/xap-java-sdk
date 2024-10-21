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
import com.expediagroup.sdk.xap.models.LodgingQuotesResponse;
import com.expediagroup.sdk.xap.models.LodgingRoomType;
import com.expediagroup.sdk.xap.models.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example demonstrates how to use quotes api with simple search.
 */
public class GetQuotesScenario implements XapScenario {

  private static final Logger LOGGER = LoggerFactory.getLogger(GetQuotesScenario.class);

  public final LodgingService lodgingService = new LodgingService();

  @Override
  public void run() {
    // This example will get quotes response for mentioned Expedia properties with the following
    // criteria:
    // 1. Occupancy of 1 adult in the first room and 2 adults and 2 children (10 and 12 years old)
    // in the second room;
    // 3. Check-in date 5 days from now, check-out date 10 days from now;
    // 4. Return web links to Expedia website;

    LOGGER.info(
        "=============================== Running GetQuotesScenario =============================");

    LOGGER.info(
        "========================= Executing GetLodgingQuotesOperation =========================");

    LodgingQuotesResponse quotesResponse = lodgingService.getQuotes();

    LOGGER.info(
        "========================== GetLodgingQuotesOperation Executed =========================");

    if (quotesResponse == null || quotesResponse.getProperties() == null
        || quotesResponse.getProperties().isEmpty()) {
      throw new IllegalStateException("No properties found.");
    }

    // The LodgingQuotesResponse contains a transaction ID for troubleshooting
    LOGGER.info("Transaction ID: {}", quotesResponse.getTransactionId());

    // To access the properties, iterate through the list of properties
    quotesResponse.getProperties().forEach(quote -> {
      LOGGER.info("========== Property:{} Start ==========", quote.getId());
      if (Property.Status.AVAILABLE != quote.getStatus()) {
        LOGGER.info("Property is not available.");
        return;
      }
      if (quote.getRoomTypes() != null && !quote.getRoomTypes().isEmpty()) {
        // To get the first room type information
        LodgingRoomType roomType = quote.getRoomTypes().get(0);

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
          "=================================== Property End ===================================");
    });

    LOGGER.info(
        "================================ End GetQuotesScenario ===============================");
  }
}
