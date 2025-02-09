package com.expediagroup.sdk.xap.integrations.lodging;

import static com.expediagroup.sdk.xap.integrations.common.Constant.ACCEPT_HOTEL;
import static com.expediagroup.sdk.xap.integrations.common.Constant.ACCEPT_LODGING;
import static com.expediagroup.sdk.xap.integrations.common.Constant.AUTHORIZATION;
import static com.expediagroup.sdk.xap.integrations.common.Constant.MOCK_KEY;
import static com.expediagroup.sdk.xap.integrations.common.Constant.PARTNER_TRANSACTION_ID;

import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.xap.integrations.common.XapIntegrationTests;
import com.expediagroup.sdk.xap.models.AvailabilityCalendar;
import com.expediagroup.sdk.xap.models.AvailabilityCalendarResponse;
import com.expediagroup.sdk.xap.models.DateRange;
import com.expediagroup.sdk.xap.operations.GetLodgingAvailabilityCalendarsOperation;
import com.expediagroup.sdk.xap.operations.GetLodgingAvailabilityCalendarsOperationParams;
import io.hosuaby.inject.resources.junit.jupiter.GivenTextResource;
import io.hosuaby.inject.resources.junit.jupiter.TestWithResources;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import okhttp3.Headers;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * This class is used to test the integration of the Lodging Availability Calendars API.
 */
@TestWithResources
public class AvailabilityCalendarsIntegrationTests extends XapIntegrationTests {
  @Test
  public void testRequest() {
    GetLodgingAvailabilityCalendarsOperationParams availabilityCalendarsOperationParams =
        GetLodgingAvailabilityCalendarsOperationParams.builder()
            .partnerTransactionId(UUID.randomUUID().toString())
            .propertyIds(new HashSet<>(Arrays.asList("123", "456", "789")))
            .build();

    mockWebServer.enqueue(
        new MockResponse()
            .setHeader("Content-Type", ACCEPT_LODGING)
            .setResponseCode(200)
            .setBody("{}"));

    xapClient.execute(
        new GetLodgingAvailabilityCalendarsOperation(availabilityCalendarsOperationParams));
    try {
      RecordedRequest recordedRequest = mockWebServer.takeRequest();
      // method
      Assertions.assertEquals("GET", recordedRequest.getMethod());
      // headers
      Headers headers = recordedRequest.getHeaders();
      Assertions.assertEquals(availabilityCalendarsOperationParams.getPartnerTransactionId(),
          headers.get("Partner-Transaction-Id"));
      Assertions.assertEquals(ACCEPT_LODGING, headers.get("Accept"));
      Assertions.assertEquals(MOCK_KEY, headers.get("key"));
      Assertions.assertEquals(AUTHORIZATION, headers.get("Authorization"));
      // path and query
      String path = recordedRequest.getPath();
      Assertions.assertNotNull(path);
      Assertions.assertTrue(path.startsWith("/lodging/availabilityCalendars"));
      String query = path.substring(path.indexOf("?") + 1);
      Assertions.assertTrue(query.contains("propertyIds=123%2C456%2C789"));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testResponse(@GivenTextResource("GetLodgingAvailabilityCalendarsResponse.json") String mockedResponse) {
    GetLodgingAvailabilityCalendarsOperationParams params =
        GetLodgingAvailabilityCalendarsOperationParams.builder()
            .partnerTransactionId(PARTNER_TRANSACTION_ID)
            .propertyIds(new HashSet<>(Arrays.asList("87704892", "83418323", "75028284",
                "75030107", "91799474")))
            .build();

    mockWebServer.enqueue(
        new MockResponse()
            .setHeader("Content-Type", ACCEPT_HOTEL)
            .setHeader("partner-transaction-id", PARTNER_TRANSACTION_ID)
            .setHeader("txnid", UUID.randomUUID().toString())
            .setResponseCode(200)
            .setBody(mockedResponse));

    Response<AvailabilityCalendarResponse> response =
        xapClient.execute(new GetLodgingAvailabilityCalendarsOperation(params));
    verifyResponse(response);
  }

  private void verifyResponse(Response<AvailabilityCalendarResponse> response) {
    Assertions.assertNotNull(response);
    Assertions.assertEquals(200, response.getStatusCode());
    Map<String, List<String>> headers = response.getHeaders();
    Assertions.assertNotNull(headers);
    Assertions.assertEquals(PARTNER_TRANSACTION_ID, headers.get("partner-transaction-id")
        .get(0));
    Assertions.assertTrue(headers.containsKey("txnid"));
    verifyAvailabilityCalendarResponse(response.getData());
  }

  private void verifyAvailabilityCalendarResponse(
      AvailabilityCalendarResponse availabilityCalendarResponse) {
    Assertions.assertNotNull(availabilityCalendarResponse);
    Assertions.assertNull(availabilityCalendarResponse.getWarnings());
    Assertions.assertNotNull(availabilityCalendarResponse.getTransactionId());
    List<AvailabilityCalendar> availabilityCalendars =
        availabilityCalendarResponse.getAvailabilityCalendars();
    Assertions.assertNotNull(availabilityCalendars);
    if (availabilityCalendars.isEmpty()) {
      Assertions.fail("No availability calendars found.");
    }
    availabilityCalendars.forEach(this::verifyAvailabilityCalendar);
  }

  private void verifyAvailabilityCalendar(AvailabilityCalendar availabilityCalendar) {
    Assertions.assertNotNull(availabilityCalendar);
    Assertions.assertNotNull(availabilityCalendar.getPropertyId());
    DateRange dateRange = availabilityCalendar.getDateRange();
    Assertions.assertNotNull(dateRange);
    Assertions.assertNotNull(dateRange.getStartDate());
    Assertions.assertNotNull(dateRange.getEndDate());
    Assertions.assertTrue(StringUtils.isNotBlank(availabilityCalendar.getAvailability()));
    Assertions.assertTrue(StringUtils.isNotBlank(availabilityCalendar.getChangeOver()));
    Assertions.assertTrue(StringUtils.isNotBlank(availabilityCalendar.getMinPriorNotify()));
    Assertions.assertTrue(StringUtils.isNotBlank(availabilityCalendar.getMinStay()));
    Assertions.assertTrue(StringUtils.isNotBlank(availabilityCalendar.getMaxStay()));
  }
}
