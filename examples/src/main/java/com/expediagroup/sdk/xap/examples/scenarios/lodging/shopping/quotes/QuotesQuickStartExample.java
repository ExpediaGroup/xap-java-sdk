package com.expediagroup.sdk.xap.examples.scenarios.lodging.shopping.quotes;

import com.expediagroup.sdk.xap.examples.Constants;
import com.expediagroup.sdk.xap.models.LodgingQuotesResponse;
import com.expediagroup.sdk.xap.models.LodgingRoomType;
import com.expediagroup.sdk.xap.models.Property;
import com.expediagroup.sdk.xap.models.Room;
import com.expediagroup.sdk.xap.operations.GetLodgingQuotesOperation;
import com.expediagroup.sdk.xap.operations.GetLodgingQuotesOperationParams;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example demonstrates how to use quotes api with simple search.
 */
public class QuotesQuickStartExample {

  private static final Logger LOGGER = LoggerFactory.getLogger(QuotesQuickStartExample.class);

  /**
   * Summary: main function.
   */
  public static void main(String[] args) {
    LOGGER.info("========== Start QuickStartExample ==========");

    // This example will get quotes response for mentioned Expedia properties with the following
    // criteria:
    // 1. Occupancy of 1 adult in the first room and 2 adults and 2 children (10 and 12 years old)
    // in the second room;
    // 3. Check-in date 5 days from now, check-out date 10 days from now;
    // 4. Return web links to Expedia website;


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
    LOGGER.info("========== Executing GetLodgingQuotesOperation ==========");
    LodgingQuotesResponse quotesResponse = Constants.XAP_CLIENT.execute(
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

    LOGGER.info("========== GetLodgingQuotesOperation Executed ==========");

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
      LOGGER.info("========== Property End ==========");
    });
  }
}
