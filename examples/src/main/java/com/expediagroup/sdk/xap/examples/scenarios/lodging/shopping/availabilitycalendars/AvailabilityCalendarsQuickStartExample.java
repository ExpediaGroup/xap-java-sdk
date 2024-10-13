package com.expediagroup.sdk.xap.examples.scenarios.lodging.shopping.availabilitycalendars;

import com.expediagroup.sdk.xap.examples.Constants;
import com.expediagroup.sdk.xap.models.AvailabilityCalendarResponse;
import com.expediagroup.sdk.xap.operations.GetLodgingAvailabilityCalendarsOperation;
import com.expediagroup.sdk.xap.operations.GetLodgingAvailabilityCalendarsOperationParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example demonstrates how to use Availability Calendar api with simple search.
 */
public class AvailabilityCalendarsQuickStartExample {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(AvailabilityCalendarsQuickStartExample.class);

  /**
   * Summary: main function.
   */
  public static void main(String[] args) {
    LOGGER.info("========== Start QuickStartExample ==========");

    // This example Returns the availability of each day for a range of dates for given Expedia
    // lodging properties.

    // Build the query parameters with GetLodgingAvailabilityCalendarsOperationParams
    GetLodgingAvailabilityCalendarsOperationParams availabilityCalendarsOperationParams =
        GetLodgingAvailabilityCalendarsOperationParams.builder()
            .partnerTransactionId(Constants.PARTNER_TRANSACTION_ID)
            //Comma-separated list of Expedia Property IDs.
            .propertyIds("87704892,36238803")
            .build();

    // Execute the operation and get the AvailabilityCalendarsResponse
    LOGGER.info("========== Executing GetLodgingAvailabilityCalendarsOperation ==========");

    AvailabilityCalendarResponse availCalendarResponse = Constants.XAP_CLIENT.execute(
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

    LOGGER.info("========== GetLodgingAvailabilityCalendarsOperation Executed ==========");

    if (availCalendarResponse == null || availCalendarResponse.getAvailabilityCalendars() == null
        || availCalendarResponse.getAvailabilityCalendars().isEmpty()) {
      throw new IllegalStateException("No properties found.");
    }

    // The AvailabilityCalendarsResponse contains a transaction ID for troubleshooting
    LOGGER.info("Transaction ID: {}", availCalendarResponse.getTransactionId());

    // To access the properties, iterate through the list of properties
    availCalendarResponse.getAvailabilityCalendars().forEach(availCalendar -> {
      LOGGER.info("========== Property:{} Start ==========", availCalendar.getPropertyId());

      // Availability of property: A string of codes that shows property availability, one for every
      // day in the specified date range.
      // Valid values include Y (available) and N (unavailable).
      LOGGER.info("Availability: {}", availCalendar.getAvailability());

      LOGGER.info("========== Property End ==========");
    });
  }
}
