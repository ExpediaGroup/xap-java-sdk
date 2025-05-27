/*
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

package com.expediagroup.sdk.xap.activity;

import static com.expediagroup.sdk.xap.common.Constant.ACCEPT_ACTIVITY;
import static com.expediagroup.sdk.xap.common.Constant.AUTHORIZATION;
import static com.expediagroup.sdk.xap.common.Constant.MOCK_KEY;

import com.expediagroup.sdk.rest.model.Response;
import com.expediagroup.sdk.xap.common.Constant;
import com.expediagroup.sdk.xap.common.XapIntegrationTest;
import com.expediagroup.sdk.xap.model.ActivitiesLink;
import com.expediagroup.sdk.xap.model.ActivitiesLocation;
import com.expediagroup.sdk.xap.model.ActivitiesMedia;
import com.expediagroup.sdk.xap.model.ActivitiesPrice;
import com.expediagroup.sdk.xap.model.Activity;
import com.expediagroup.sdk.xap.model.ActivityListingsResponse;
import com.expediagroup.sdk.xap.model.CategoryGroup;
import com.expediagroup.sdk.xap.operation.GetActivityListingsOperation;
import com.expediagroup.sdk.xap.operation.GetActivityListingsOperationParams;
import io.hosuaby.inject.resources.junit.jupiter.GivenTextResource;
import io.hosuaby.inject.resources.junit.jupiter.TestWithResources;
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
@TestWithResources
public class ListingsIntegrationTest extends XapIntegrationTest {
  @Test
  public void testRequest(
      @GivenTextResource("activities/GetActivityListingsResponse.json") String mockedResponse) {
    GetActivityListingsOperationParams getActivityListingsOperationParams =
        GetActivityListingsOperationParams.builder()
            .partnerTransactionId(UUID.randomUUID().toString())
            .location("seattle")
            .links(
                Arrays.asList(
                    GetActivityListingsOperationParams.Links.WD,
                    GetActivityListingsOperationParams.Links.AD
                )
            )
            .startDate(LocalDate.now().plusDays(5))
            .endDate(LocalDate.now().plusDays(8))
            .locale("en_US")
            .build();

    mockWebServer.enqueue(
        new MockResponse()
            .setHeader("Content-Type", ACCEPT_ACTIVITY)
            .setResponseCode(200)
            .setBody(mockedResponse)
    );

    xapClient.execute(new GetActivityListingsOperation(getActivityListingsOperationParams));
    try {
      RecordedRequest recordedRequest = mockWebServer.takeRequest();
      // method
      Assertions.assertEquals("GET", recordedRequest.getMethod());
      // headers
      Headers headers = recordedRequest.getHeaders();
      Assertions.assertEquals(
          getActivityListingsOperationParams.getPartnerTransactionId(),
          headers.get("Partner-Transaction-Id")
      );
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
      // search
      Assertions.assertTrue(
          query.contains("startDate=" + getActivityListingsOperationParams.getStartDate()));
      Assertions
          .assertTrue(query.contains("endDate=" + getActivityListingsOperationParams.getEndDate()));
      Assertions.assertTrue(query.contains("locale=en_US"));
      Assertions.assertTrue(query.contains("links=WD,AD"));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testResponse(
      @GivenTextResource("activities/GetActivityListingsResponse.json") String mockedResponse) {
    GetActivityListingsOperationParams getActivityListingsOperationParams =
        GetActivityListingsOperationParams
            .builder()
            .partnerTransactionId(Constant.PARTNER_TRANSACTION_ID)
            .location("seattle")
            .links(
                Arrays.asList(
                    GetActivityListingsOperationParams.Links.WD,
                    GetActivityListingsOperationParams.Links.AD
                )
            )
            .startDate(LocalDate.now().plusDays(5))
            .endDate(LocalDate.now().plusDays(8))
            .locale("en_US")
            .build();

    mockWebServer.enqueue(
        new MockResponse()
            .setHeader("Content-Type", ACCEPT_ACTIVITY)
            .setHeader("Partner-Transaction-Id", Constant.PARTNER_TRANSACTION_ID)
            .setResponseCode(200)
            .setBody(mockedResponse)
    );

    Response<ActivityListingsResponse> response =
        xapClient.execute(new GetActivityListingsOperation(getActivityListingsOperationParams));
    verifyResponse(response);
  }

  private void verifyResponse(Response<ActivityListingsResponse> response) {
    Assertions.assertNotNull(response);
    com.expediagroup.sdk.core.http.Headers headers = response.getHeaders();
    Assertions.assertNotNull(headers);
    Assertions.assertEquals(
        Constant.PARTNER_TRANSACTION_ID,
        headers.get("partner-transaction-id")
    );
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
    Assertions.assertNotNull(activity.getTitle());
    Assertions.assertNotNull(activity.getDescription());
    Assertions.assertNotNull(activity.getDuration());
    Assertions.assertNotNull(activity.getFreeCancellation());

    verifyMedia(activity.getMedia());
    verifyPrice(activity.getPrice());
    verifyActivityLocations(activity.getActivityLocations());
    verifyActivityLinks(activity.getLinks());
  }

  protected void verifyMedia(List<ActivitiesMedia> media) {
    Assertions.assertNotNull(media);
    media.forEach(item -> {
      Assertions.assertNotNull(item.getPropertySize());
      Assertions.assertNotNull(item.getType());
      Assertions.assertNotNull(item.getUrl());
    });
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
