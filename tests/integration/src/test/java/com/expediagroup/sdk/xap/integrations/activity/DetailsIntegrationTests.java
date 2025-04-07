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

package com.expediagroup.sdk.xap.integrations.activity;

import static com.expediagroup.sdk.xap.integrations.common.Constant.ACCEPT_ACTIVITY;
import static com.expediagroup.sdk.xap.integrations.common.Constant.AUTHORIZATION;
import static com.expediagroup.sdk.xap.integrations.common.Constant.MOCK_KEY;

import com.expediagroup.sdk.core.model.Response;
import com.expediagroup.sdk.xap.integrations.common.Constant;
import com.expediagroup.sdk.xap.integrations.common.XapIntegrationTests;
import com.expediagroup.sdk.xap.models.ActivitiesCancellationPolicy;
import com.expediagroup.sdk.xap.models.ActivitiesLink;
import com.expediagroup.sdk.xap.models.ActivitiesLocation;
import com.expediagroup.sdk.xap.models.ActivitiesMedia;
import com.expediagroup.sdk.xap.models.ActivitiesPrice;
import com.expediagroup.sdk.xap.models.ActivitiesSupplier;
import com.expediagroup.sdk.xap.models.Activity;
import com.expediagroup.sdk.xap.models.ActivityDetailsResponse;
import com.expediagroup.sdk.xap.models.AvailableTimeSlot;
import com.expediagroup.sdk.xap.models.Offer;
import com.expediagroup.sdk.xap.models.Restrictions;
import com.expediagroup.sdk.xap.models.Ticket;
import com.expediagroup.sdk.xap.models.exception.ExpediaGroupApiActivitiesErrorsException;
import com.expediagroup.sdk.xap.operations.GetActivityDetailsOperation;
import com.expediagroup.sdk.xap.operations.GetActivityDetailsOperationParams;
import io.hosuaby.inject.resources.junit.jupiter.GivenTextResource;
import io.hosuaby.inject.resources.junit.jupiter.TestWithResources;
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
public class DetailsIntegrationTests extends XapIntegrationTests {

  @Test
  public void testRequest(
      @GivenTextResource("activities/GetActivityDetailsResponse.json") String mockedResponse
  ) {
    String offerToken = "CgYxOTMzNjASCjIwMjUtMDEtMDIaCjIwMjUtMDEtMDM";

    GetActivityDetailsOperationParams getActivityDetailsOperationParams =
        GetActivityDetailsOperationParams
            .builder()
            .partnerTransactionId(UUID.randomUUID().toString())
            .offerToken(offerToken)
            .locale("en_US")
            .build();

    mockWebServer.enqueue(
        new MockResponse()
            .setHeader("Content-Type", ACCEPT_ACTIVITY)
            .setResponseCode(200)
            .setBody(mockedResponse)
    );

    xapClient.execute(new GetActivityDetailsOperation(getActivityDetailsOperationParams));
    try {
      RecordedRequest recordedRequest = mockWebServer.takeRequest();
      // method
      Assertions.assertEquals("GET", recordedRequest.getMethod());
      // headers
      Headers headers = recordedRequest.getHeaders();
      Assertions.assertEquals(
          getActivityDetailsOperationParams.getPartnerTransactionId(),
          headers.get("Partner-Transaction-Id")
      );
      Assertions.assertEquals(ACCEPT_ACTIVITY, headers.get("Accept"));
      Assertions.assertEquals(MOCK_KEY, headers.get("key"));
      Assertions.assertEquals(AUTHORIZATION, headers.get("Authorization"));
      // path and query
      String path = recordedRequest.getPath();
      Assertions.assertNotNull(path);
      Assertions.assertTrue(path.startsWith("/activities/details/" + offerToken));
      String query = path.substring(path.indexOf("?") + 1);
      Assertions.assertTrue(query.contains("locale=en_US"));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testResponse(
      @GivenTextResource("activities/GetActivityDetailsResponse.json") String mockedDetailsResponse
  ) {

    String offerToken = "CgYxOTMzNjASCjIwMjUtMDEtMDIaCjIwMjUtMDEtMDM";

    GetActivityDetailsOperationParams getActivityDetailsOperationParams =
        GetActivityDetailsOperationParams
            .builder()
            .partnerTransactionId(Constant.PARTNER_TRANSACTION_ID)
            .offerToken(offerToken)
            .build();

    mockWebServer.enqueue(
        new MockResponse()
            .setHeader("Content-Type", ACCEPT_ACTIVITY)
            .setHeader("Partner-Transaction-Id", Constant.PARTNER_TRANSACTION_ID)
            .setResponseCode(200)
            .setBody(mockedDetailsResponse)
    );

    try {
      Response<ActivityDetailsResponse> detailsResponse =
          xapClient.execute(new GetActivityDetailsOperation(getActivityDetailsOperationParams));
      verifyResponse(detailsResponse);
    } catch (Exception ex) {
      if (!(ex instanceof ExpediaGroupApiActivitiesErrorsException)) {
        Assertions.fail(ex.getMessage());
      }
    }
  }

  private void verifyResponse(Response<ActivityDetailsResponse> response) {
    Assertions.assertNotNull(response);
    Assertions.assertEquals(200, response.getStatusCode());
    Map<String, List<String>> headers = response.getHeaders();
    Assertions.assertNotNull(headers);
    Assertions.assertEquals(
        Constant.PARTNER_TRANSACTION_ID,
        headers.get("partner-transaction-id").get(0)
    );
    verifyActivityDetailsResponse(response.getData());
  }

  private void verifyActivityDetailsResponse(ActivityDetailsResponse activityListingsResponse) {
    Assertions.assertNotNull(activityListingsResponse);
    Assertions.assertNull(activityListingsResponse.getWarnings());
    Assertions.assertNotNull(activityListingsResponse.getLocation());

    if (activityListingsResponse.getActivityDetails() != null) {
      verifyActivity(activityListingsResponse.getActivityDetails());
    }
  }

  private void verifyActivity(Activity activity) {
    Assertions.assertNotNull(activity);
    Assertions.assertNotNull(activity.getId());
    Assertions.assertNotNull(activity.getTitle());
    Assertions.assertNotNull(activity.getDescription());
    Assertions.assertNotNull(activity.getDuration());

    verifyMedia(activity.getMedia());
    verifyPrice(activity.getPrice());
    verifyActivityLocations(activity.getActivityLocations());
    verifyCancellationPolicy(activity.getCancellationPolicy());
    verifySupplier(activity.getSupplier());
    verifyOffers(activity.getOffers());
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

  private void verifyCancellationPolicy(ActivitiesCancellationPolicy cancellationPolicy) {
    Assertions.assertNotNull(cancellationPolicy);
    Assertions.assertTrue(cancellationPolicy.getFreeCancellation());
  }

  private void verifySupplier(ActivitiesSupplier supplier) {
    Assertions.assertNotNull(supplier);
    Assertions.assertNotNull(supplier.getName());
  }

  private void verifyOffers(List<Offer> offers) {
    Assertions.assertNotNull(offers);
    offers.forEach(this::verifyOffer);
  }

  private void verifyOffer(Offer offer) {
    Assertions.assertNotNull(offer);
    Assertions.assertNotNull(offer.getId());
    Assertions.assertNotNull(offer.getTitle());
    Assertions.assertNotNull(offer.getDuration());

    verifyActivityLinks(offer.getLinks());
    verifyAvailableTimeSlots(offer.getAvailableTimeSlots());
  }

  protected void verifyActivityLinks(Map<String, ActivitiesLink> links) {
    Assertions.assertNotNull(links);
    links.forEach((k, v) -> {
      Assertions.assertNotNull(k);
      Assertions.assertNotNull(v);
      Assertions.assertNotNull(v.getHref());
    });
  }

  private void verifyAvailableTimeSlots(List<AvailableTimeSlot> timeSlots) {
    Assertions.assertNotNull(timeSlots);
    timeSlots.forEach(timeSlot -> {
      Assertions.assertTrue(timeSlot.getAllDayActivity());
      Assertions.assertNotNull(timeSlot.getDateTime());
      verifyCancellationPolicy(timeSlot.getCancellationPolicy());
      verifyTickets(timeSlot.getTickets());
    });
  }

  private void verifyTickets(List<Ticket> tickets) {
    Assertions.assertNotNull(tickets);
    tickets.forEach(ticket -> {
      Assertions.assertNotNull(ticket.getCode());
      Assertions.assertNotNull(ticket.getId());
      verifyPrice(ticket.getTicketPrice());

      if (ticket.getRestrictions() != null) {
        verifyRestrictions(ticket.getRestrictions());
      }
    });
  }

  private void verifyRestrictions(Restrictions restrictions) {
    Assertions.assertNotNull(restrictions.getMax());
    Assertions.assertNotNull(restrictions.getMin());
    Assertions.assertNotNull(restrictions.getType());
  }
}
