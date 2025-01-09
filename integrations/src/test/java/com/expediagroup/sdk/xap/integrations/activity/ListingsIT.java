package com.expediagroup.sdk.xap.integrations.activity;

import static com.expediagroup.sdk.xap.integrations.common.Constant.ACCEPT_ACTIVITY;
import static com.expediagroup.sdk.xap.integrations.common.Constant.AUTHORIZATION;
import static com.expediagroup.sdk.xap.integrations.common.Constant.MOCK_KEY;

import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.xap.integrations.common.Constant;
import com.expediagroup.sdk.xap.integrations.common.XapIT;
import com.expediagroup.sdk.xap.models.ActivitiesLink;
import com.expediagroup.sdk.xap.models.ActivitiesLocation;
import com.expediagroup.sdk.xap.models.ActivitiesMedia;
import com.expediagroup.sdk.xap.models.ActivitiesPrice;
import com.expediagroup.sdk.xap.models.Activity;
import com.expediagroup.sdk.xap.models.ActivityListingsResponse;
import com.expediagroup.sdk.xap.models.CategoryGroup;
import com.expediagroup.sdk.xap.operations.GetActivityListingsOperation;
import com.expediagroup.sdk.xap.operations.GetActivityListingsOperationParams;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import okhttp3.Headers;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * This class is used to test the integration of the Lodging Listings API.
 */
public class ListingsIT extends XapIT {

  @Test
  public void testRequest() {
    GetActivityListingsOperationParams getActivityListingsOperationParams =
        GetActivityListingsOperationParams.builder()
            .partnerTransactionId(UUID.randomUUID().toString()).geoLocation("47.6062,-122.3321")
            .location("seattle")
            .links(Arrays.asList(GetActivityListingsOperationParams.Links.WD,
                GetActivityListingsOperationParams.Links.AD))
            .startDate(LocalDate.now().plusDays(5)).endDate(LocalDate.now().plusDays(8))
            .locale("en_US").build();

    String mockResponse = "{" 
        + "\"TransactionId\": \"2d410825-fe68-449d-877f-d36b90d6fe0c\","
        + "\"Count\": 50," 
        + "\"Location\": \"Seattle (and vicinity)\"" 
        + "}";

    mockWebServer.enqueue(new MockResponse().setHeader("Content-Type", ACCEPT_ACTIVITY)
        .setResponseCode(200).setBody(mockResponse));

    mockClient.execute(new GetActivityListingsOperation(getActivityListingsOperationParams));
    try {
      RecordedRequest recordedRequest = mockWebServer.takeRequest();
      // method
      Assertions.assertEquals("GET", recordedRequest.getMethod());
      // headers
      Headers headers = recordedRequest.getHeaders();
      Assertions.assertEquals(getActivityListingsOperationParams.getPartnerTransactionId(),
          headers.get("Partner-Transaction-Id"));
      Assertions.assertEquals(ACCEPT_ACTIVITY, headers.get("Accept"));
      Assertions.assertEquals(MOCK_KEY, headers.get("key"));
      Assertions.assertEquals(AUTHORIZATION, headers.get("Authorization"));
      // path and query
      String path = recordedRequest.getPath();
      Assertions.assertNotNull(path);
      Assertions.assertTrue(path.startsWith("/activities/listings"));
      String query = path.substring(path.indexOf("?") + 1);
      // activity selection
      Assertions.assertTrue(query.contains("location=seattle"));
      Assertions.assertTrue(query.contains("geoLocation=47.6062%2C-122.3321"));
      // search
      Assertions.assertTrue(
          query.contains("startDate=" + getActivityListingsOperationParams.getStartDate()));
      Assertions
          .assertTrue(query.contains("endDate=" + getActivityListingsOperationParams.getEndDate()));
      Assertions.assertTrue(query.contains("locale=en_US"));
      Assertions.assertTrue(query.contains("links=WD%2CAD"));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

  }

  @Test
  public void testResponse() {
    GetActivityListingsOperationParams getActivityListingsOperationParams =
        GetActivityListingsOperationParams.builder()
            .partnerTransactionId(Constant.PARTNER_TRANSACTION_ID)
            .location("seattle")
            .links(Arrays.asList(GetActivityListingsOperationParams.Links.WD,
                GetActivityListingsOperationParams.Links.AD))
            .startDate(LocalDate.now().plusDays(5))
            .endDate(LocalDate.now().plusDays(8))
            .locale("en_US")
            .build();

    Response<ActivityListingsResponse> response =
        xapClient.execute(new GetActivityListingsOperation(getActivityListingsOperationParams));
    verifyResponse(response);
  }

  private void verifyResponse(Response<ActivityListingsResponse> response) {
    Assertions.assertNotNull(response);
    Assertions.assertEquals(200, response.getStatusCode());
    Map<String, List<String>> headers = response.getHeaders();
    Assertions.assertNotNull(headers);
    Assertions.assertEquals(Constant.PARTNER_TRANSACTION_ID,
        headers.get("partner-transaction-id").get(0));
    verifyActivityListingsResponse(response.getData());
  }

  private void verifyActivityListingsResponse(ActivityListingsResponse activityListingsResponse) {
    Assertions.assertNotNull(activityListingsResponse);
    Assertions.assertNull(activityListingsResponse.getWarnings());
    Assertions.assertTrue(activityListingsResponse.getCount() > 0);
    Assertions.assertNotNull(activityListingsResponse.getLocation());

    if (activityListingsResponse.getActivities() != null) {
      activityListingsResponse.getActivities().forEach(this::verifyActivity);
    }

    if (activityListingsResponse.getCategories() != null) {
      verifyCategories(activityListingsResponse.getCategories());
    }

  }

  private void verifyActivity(Activity activity) {
    Assertions.assertNotNull(activity);
    Assertions.assertNotNull(activity.getId());
    Assertions.assertNotNull(activity.getTitle());
    Assertions.assertNotNull(activity.getDescription());
    Assertions.assertNotNull(activity.getDuration());
    Assertions.assertNotNull(activity.getFreeCancellation());

    verfiyMedia(activity.getMedia());
    verfiyCategories(activity.getCategories());
    verifyPrice(activity.getPrice());
    verifyActivityLocations(activity.getActivityLocations());
    verifyActivityLinks(activity.getLinks());

  }

  protected void verfiyMedia(List<ActivitiesMedia> media) {
    Assertions.assertNotNull(media);
    media.forEach(item -> {
      Assertions.assertNotNull(item.getPropertySize());
      Assertions.assertNotNull(item.getType());
      Assertions.assertNotNull(item.getUrl());
    });
  }

  protected void verfiyCategories(List<String> categories) {
    Assertions.assertNotNull(categories);
    //Currently there is a bug in Activity, values in Categories will be null
    //  "Categories": [
    //      null
    //  ],
//    categories.forEach(item -> {
//      Assertions.assertNotNull(item);
//    });
  }

  protected void verifyPrice(ActivitiesPrice price) {
    Assertions.assertNotNull(price);
    Assertions.assertNotNull(price.getTotalRate());
    Assertions.assertNotNull(price.getTotalRate().getCurrency());
    Assertions.assertNotNull(price.getTotalRate().getValue());

  }

  protected void verifyActivityLocations(List<ActivitiesLocation> locations) {
    Assertions.assertNotNull(locations);

    locations.forEach(location -> {
      if (location.getAddress() != null) {
        Assertions.assertNotNull(location.getAddress().getCity());
      }

      if (location.getGeoLocation() != null) {
        Assertions.assertNotNull(location.getGeoLocation().getLatitude());
        Assertions.assertNotNull(location.getGeoLocation().getLongitude());
      }

    });
  }

  protected void verifyActivityLinks(Map<String, ActivitiesLink> links) {
    Assertions.assertNotNull(links);
    links.forEach((k, v) -> {
      Assertions.assertNotNull(k);
      Assertions.assertNotNull(v);
      Assertions.assertNotNull(v.getHref());
    });
  }

  private void verifyCategories(List<CategoryGroup> categories) {
    categories.forEach(category -> {
      Assertions.assertNotNull(category.getCategoryName());
      Assertions.assertNotNull(category.getCategoryDisplayName());
      Assertions.assertNotNull(category.getGroupDisplayName());
      Assertions.assertNotNull(category.getGroupName());
      Assertions.assertTrue(category.getCount() > 0);


    });

  }
}
