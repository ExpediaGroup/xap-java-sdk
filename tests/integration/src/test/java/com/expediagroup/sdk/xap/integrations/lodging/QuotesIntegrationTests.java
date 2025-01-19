package com.expediagroup.sdk.xap.integrations.lodging;

import static com.expediagroup.sdk.xap.integrations.common.Constant.ACCEPT_LODGING;
import static com.expediagroup.sdk.xap.integrations.common.Constant.AUTHORIZATION;
import static com.expediagroup.sdk.xap.integrations.common.Constant.MOCK_KEY;
import static com.expediagroup.sdk.xap.integrations.common.Constant.PARTNER_TRANSACTION_ID;

import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.xap.integrations.common.Constant;
import com.expediagroup.sdk.xap.models.LodgingCancellationPolicy;
import com.expediagroup.sdk.xap.models.LodgingQuotesResponse;
import com.expediagroup.sdk.xap.models.LodgingRatePlan;
import com.expediagroup.sdk.xap.models.LodgingRoomType;
import com.expediagroup.sdk.xap.models.LodgingRoomTypePrice;
import com.expediagroup.sdk.xap.models.Property;
import com.expediagroup.sdk.xap.models.Room;
import com.expediagroup.sdk.xap.operations.GetLodgingQuotesOperation;
import com.expediagroup.sdk.xap.operations.GetLodgingQuotesOperationParams;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import okhttp3.Headers;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * This class is used to test the integration of the Lodging Quotes API.
 */
public class QuotesIntegrationTests extends VrboIntegrationTests {

  @Test
  public void testRequest() {
    ArrayList<Room> rooms = new ArrayList<>();
    rooms.add(Room.builder().adults(1L).build());
    rooms.add(Room.builder().adults(2L).childAges(Arrays.asList(10L, 12L)).build());
    rooms.add(Room.builder().adults(1L).childAges(Collections.singletonList(5L)).build());
    rooms.add(Room.builder().adults(6L).build());
    rooms.add(Room.builder().adults(2L).childAges(Arrays.asList(3L, 4L)).build());
    rooms.add(Room.builder().adults(3L).childAges(Arrays.asList(1L, 2L, 3L, 4L, 5L)).build());
    rooms.add(Room.builder().adults(2L).childAges(Arrays.asList(1L, 2L, 3L, 4L)).build());
    rooms.add(Room.builder().adults(1L).build());
    // nine rooms here, the ninth room should be ignored
    rooms.add(Room.builder().adults(2L).build());

    GetLodgingQuotesOperationParams getLodgingQuotesOperationParams =
        GetLodgingQuotesOperationParams.builder()
            .partnerTransactionId(UUID.randomUUID().toString())
            .propertyIds(new HashSet<>(Arrays.asList("123", "456", "789")))
            .checkIn(LocalDate.now().plusDays(5))
            .checkOut(LocalDate.now().plusDays(10))
            .currency("USD")
            .links(Collections.singletonList(GetLodgingQuotesOperationParams.Links.WEB))
            .rooms(rooms)
            .travelWithPets(true)
            .build();

    mockWebServer.enqueue(
        new MockResponse()
            .setHeader("Content-Type", ACCEPT_LODGING)
            .setResponseCode(200)
            .setBody("{}"));

    mockClient.execute(new GetLodgingQuotesOperation(getLodgingQuotesOperationParams));
    try {
      RecordedRequest recordedRequest = mockWebServer.takeRequest();
      // method
      Assertions.assertEquals("GET", recordedRequest.getMethod());
      // headers
      Headers headers = recordedRequest.getHeaders();
      Assertions.assertEquals(getLodgingQuotesOperationParams.getPartnerTransactionId(),
          headers.get("Partner-Transaction-Id"));
      Assertions.assertEquals(ACCEPT_LODGING, headers.get("Accept"));
      Assertions.assertEquals(MOCK_KEY, headers.get("key"));
      Assertions.assertEquals(AUTHORIZATION, headers.get("Authorization"));
      // path and query
      String path = recordedRequest.getPath();
      Assertions.assertNotNull(path);
      Assertions.assertTrue(path.startsWith("/lodging/quotes"));
      String query = path.substring(path.indexOf("?") + 1);
      Assertions.assertTrue(query.contains("propertyIds=123%2C456%2C789"));
      Assertions.assertTrue(query.contains(
          "checkIn=" + getLodgingQuotesOperationParams.getCheckIn()));
      Assertions.assertTrue(query.contains(
          "checkOut=" + getLodgingQuotesOperationParams.getCheckOut()));
      Assertions.assertTrue(query.contains("currency=USD"));
      Assertions.assertTrue(query.contains("links=WEB"));
      Assertions.assertTrue(query.contains("room1.adults=1"));
      Assertions.assertTrue(query.contains("room2.adults=2"));
      Assertions.assertTrue(query.contains("room2.childAges=10%2C12"));
      Assertions.assertTrue(query.contains("room3.adults=1"));
      Assertions.assertTrue(query.contains("room3.childAges=5"));
      Assertions.assertTrue(query.contains("room4.adults=6"));
      Assertions.assertTrue(query.contains("room5.adults=2"));
      Assertions.assertTrue(query.contains("room5.childAges=3%2C4"));
      Assertions.assertTrue(query.contains("room6.adults=3"));
      Assertions.assertTrue(query.contains("room6.childAges=1%2C2%2C3%2C4%2C5"));
      Assertions.assertTrue(query.contains("room7.adults=2"));
      Assertions.assertTrue(query.contains("room7.childAges=1%2C2%2C3%2C4"));
      Assertions.assertTrue(query.contains("room8.adults=1"));
      Assertions.assertFalse(query.contains("room9"));
      Assertions.assertTrue(query.contains("travelWithPets=true"));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testResponse() {
    GetLodgingQuotesOperationParams getLodgingQuotesOperationParams =
        GetLodgingQuotesOperationParams.builder()
            .partnerTransactionId(PARTNER_TRANSACTION_ID)
            .propertyIds(new HashSet<>(Arrays.asList("87704892", "83418323", "75028284", "75030107",
                "91799474")))
            .checkIn(LocalDate.now().plusDays(5))
            .checkOut(LocalDate.now().plusDays(10))
            .links(Collections.singletonList(GetLodgingQuotesOperationParams.Links.WEB))
            .build();

    Response<LodgingQuotesResponse> response =
        xapClient.execute(new GetLodgingQuotesOperation(getLodgingQuotesOperationParams));
    verifyResponse(response);
  }

  private void verifyResponse(Response<LodgingQuotesResponse> response) {
    Assertions.assertNotNull(response);
    Assertions.assertEquals(200, response.getStatusCode());
    Map<String, List<String>> headers = response.getHeaders();
    Assertions.assertNotNull(headers);
    Assertions.assertEquals(Constant.PARTNER_TRANSACTION_ID, headers.get("partner-transaction-id")
        .get(0));
    Assertions.assertTrue(headers.containsKey("txnid"));
    verifyLodgingQuotesResponse(response.getData());
  }

  private void verifyLodgingQuotesResponse(LodgingQuotesResponse lodgingQuotesResponse) {
    Assertions.assertNotNull(lodgingQuotesResponse);
    Assertions.assertNull(lodgingQuotesResponse.getWarnings());
    Assertions.assertTrue(lodgingQuotesResponse.getCount() > 0);
    Assertions.assertTrue(lodgingQuotesResponse.getTotalPropertyCount() > 0);
    Assertions.assertNotNull(lodgingQuotesResponse.getTransactionId());
    Assertions.assertNotNull(lodgingQuotesResponse.getStayDates());
    Assertions.assertTrue(lodgingQuotesResponse.getLengthOfStay() > 0);
    Assertions.assertNotNull(lodgingQuotesResponse.getOccupants());
    Assertions.assertFalse(lodgingQuotesResponse.getOccupants().isEmpty());
    Assertions.assertNotNull(lodgingQuotesResponse.getProperties());
    Assertions.assertFalse(lodgingQuotesResponse.getProperties().isEmpty());
    List<Property> availProperties = lodgingQuotesResponse.getProperties()
        .stream()
        .filter(property -> Property.Status.AVAILABLE.equals(property.getStatus()))
        .collect(Collectors.toList());
    if (availProperties.isEmpty()) {
      Assertions.fail("No available properties found");
    }
    availProperties.forEach(this::verifyProperty);
  }

  private void verifyProperty(Property property) {
    Assertions.assertNotNull(property);
    Assertions.assertNotNull(property.getId());
    Assertions.assertNotNull(property.getStatus());
    Assertions.assertNotNull(property.getRoomTypes());
    Assertions.assertFalse(property.getRoomTypes().isEmpty());
    property.getRoomTypes().forEach(this::verifyRoomType);
  }

  private void verifyRoomType(LodgingRoomType roomType) {
    Assertions.assertNotNull(roomType);
    LodgingRoomTypePrice price = roomType.getPrice();
    Assertions.assertNotNull(price);
    Assertions.assertNotNull(price.getTotalPrice());
    Assertions.assertNotNull(price.getAvgNightlyRate());
    Assertions.assertNotNull(price.getTotalPriceWithPropertyFees());
    List<LodgingRatePlan> ratePlans = roomType.getRatePlans();
    Assertions.assertNotNull(ratePlans);
    Assertions.assertFalse(ratePlans.isEmpty());
    ratePlans.forEach(this::verifyRatePlan);
  }

  private void verifyRatePlan(LodgingRatePlan ratePlan) {
    LodgingCancellationPolicy cancellationPolicy = ratePlan.getCancellationPolicy();
    Assertions.assertNotNull(cancellationPolicy);
    Assertions.assertNotNull(cancellationPolicy.getFreeCancellation());
    Assertions.assertNotNull(cancellationPolicy.getRefundable());
  }
}
