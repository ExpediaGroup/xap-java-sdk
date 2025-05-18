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
package com.expediagroup.sdk.xap.examples.scenarios.activity;

import com.expediagroup.sdk.rest.model.Response;
import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.examples.scenarios.XapScenario;
import com.expediagroup.sdk.xap.models.ActivitiesAddress;
import com.expediagroup.sdk.xap.models.ActivitiesCancellationPolicy;
import com.expediagroup.sdk.xap.models.ActivitiesGeoLocation;
import com.expediagroup.sdk.xap.models.ActivitiesLocation;
import com.expediagroup.sdk.xap.models.Activity;
import com.expediagroup.sdk.xap.models.ActivityDetailsResponse;
import com.expediagroup.sdk.xap.models.ActivityListingsResponse;
import com.expediagroup.sdk.xap.operations.GetActivityDetailsOperation;
import com.expediagroup.sdk.xap.operations.GetActivityDetailsOperationParams;
import com.expediagroup.sdk.xap.operations.GetActivityListingsOperation;
import com.expediagroup.sdk.xap.operations.GetActivityListingsOperationParams;
import java.time.LocalDate;
import java.util.Collections;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example demonstrates how to get Activity details.
 */
public class ActivityDetailsQuickStartScenario implements XapScenario {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(ActivityDetailsQuickStartScenario.class);

    public static void main(String[] args) {
        new ActivityDetailsQuickStartScenario().run();
        System.exit(0);
    }

    @Override
    public void run() {
        // This example demonstrates how to obtain the Activity Details Deep Link from the Activity
        // Listings.
        // For information on using activity search, refer to Activity ListingsQuickStartExample.
        LOGGER.info(
            "========================== Running DetailsQuickStartScenario =========================");

        // Execute the operation and get the ActivityListingsRespons
        XapClient xapClient = createClient();

        GetActivityListingsOperationParams getActivityListingsOperationParams =
            GetActivityListingsOperationParams.builder()
                .partnerTransactionId(XapScenario.PARTNER_TRANSACTION_ID).location("seattle")
                .links(Collections.singletonList(GetActivityListingsOperationParams.Links.AD))
                .startDate(LocalDate.now().plusDays(5)).endDate(LocalDate.now().plusDays(8))
                .locale("en_US").build();

        Response<ActivityListingsResponse> listingsResponse =
            xapClient.execute(new GetActivityListingsOperation(getActivityListingsOperationParams));

        if (listingsResponse == null || listingsResponse.getData() == null
            || listingsResponse.getData().getActivities() == null) {
            throw new IllegalStateException("No activity details found.");
        }


        Activity listingsActivity = listingsResponse.getData().getActivities().get(0);

        // Get offerToken from APIDetails link url
        String adLink = listingsActivity.getLinks().get("ApiDetails").getHref();
        String offerToken = adLink.substring(adLink.lastIndexOf("/") + 1);

        // Execute the operation and get the ActivityDetailsResponse
        GetActivityDetailsOperationParams getActivityDetailsOperationParams =
            GetActivityDetailsOperationParams.builder()
                .partnerTransactionId(XapScenario.PARTNER_TRANSACTION_ID).offerToken(offerToken)
                .build();

        Response<ActivityDetailsResponse> detalisResponse =
            xapClient.execute(new GetActivityDetailsOperation(getActivityDetailsOperationParams));


        LOGGER.info(
            "======================== GetActivityDetailsOperation Executed ========================");

        if (detalisResponse == null || detalisResponse.getData() == null
            || detalisResponse.getData().getActivityDetails() == null
            || detalisResponse.getData().getActivityDetails() == null) {
            throw new IllegalStateException("No activity details found.");
        }

        // The ActivityListingsResponse contains a transaction ID for troubleshooting
        LOGGER.info("Transaction ID: {}", detalisResponse.getData().getTransactionId());


        LOGGER.info(
            "============================ Activity details Start ============================");
        Activity activity = detalisResponse.getData().getActivityDetails();
        // To get the activity title
        if (StringUtils.isNotEmpty(activity.getTitle())) {
            LOGGER.info("Activity title: {}", activity.getTitle());
        }
        // To get the activity description
        if (StringUtils.isNotEmpty(activity.getDescription())) {
            LOGGER.info("Activity description: {}", activity.getDescription());
        }

        // To get the activity duration
        if (StringUtils.isNotEmpty(activity.getDuration())) {
            LOGGER.info("Activity duration: {}", activity.getDuration());
        }


        // To get the activity image
        if (activity.getMedia() != null) {
            LOGGER.info(
                "============================ Activity Images  ============================ ");
            activity.getMedia().forEach(image -> {
                LOGGER.info("Image title: {}", image.getTitle());
                LOGGER.info("Image url: {}", image.getUrl());
            });
            LOGGER.info(
                "============================ Activity Images ============================ ");
        }

        // To get the activity free cancellation available
        LOGGER.info("Activity free cancellation available: {}", activity.getFreeCancellation());

        // To get the activity price
        if (activity.getPrice() != null) {
            LOGGER.info("Activity price currency: {}", activity.getPrice().getTotalRate().getCurrency());
            LOGGER.info("Activity price value: {}", activity.getPrice().getTotalRate().getValue());
        }


        // To get the activity redemption
        if (activity.getRedemption() != null) {
            LOGGER.info(
                "============================ Activity Redemption  ============================ ");
            if (StringUtils.isNotEmpty(activity.getDuration())) {
                LOGGER.info("Activity Redemption type: {}", activity.getRedemption().getType());
            }

            if (activity.getRedemption().getRedemptionLocations() != null) {
                activity.getRedemption().getRedemptionLocations().forEach(location -> {
                    printLocation(location);
                });
            }
            LOGGER.info(
                "============================ Activity Redemption  ============================ ");
        }

        // To get the activity location
        if (activity.getActivityLocations() != null) {
            activity.getActivityLocations().forEach(location -> {
                printLocation(location);
            });
        }


        // To get the activity cancellation policy
        printCancellationPolicy(activity.getCancellationPolicy());

        // To get activity highlight
        if (activity.getHighlights() != null) {
            LOGGER.info(
                "============================ Activity Highlight  ============================ ");
            activity.getHighlights().forEach(highlight -> {
                LOGGER.info(highlight);
            });
            LOGGER.info(
                "============================ Activity Highlight  ============================ ");
        }

        // To get activity inclusions
        if (activity.getInclusions() != null) {
            LOGGER.info(
                "============================ Activity Inclusions  ============================ ");
            activity.getInclusions().forEach(inclusion -> {
                LOGGER.info(inclusion);
            });
            LOGGER.info(
                "============================ Activity Inclusions  ============================ ");
        }

        // To get activity exclusion
        if (activity.getExclusions() != null) {
            LOGGER.info(
                "============================ Activity Exclusions  ============================ ");
            activity.getExclusions().forEach(exclusion -> {
                LOGGER.info(exclusion);
            });
            LOGGER.info(
                "============================ Activity Exclusions  ============================ ");
        }

        // To get activity knowBeforeYouBook
        if (activity.getKnowBeforeYouBook() != null) {
            LOGGER.info(
                "============================ Activity KnowBeforeYouBook  ============================ ");
            activity.getKnowBeforeYouBook().forEach(knowBeforeYouBook -> {
                LOGGER.info(knowBeforeYouBook);
            });
            LOGGER.info(
                "============================ Activity KnowBeforeYouBook  ============================ ");
        }

        // To get activity offers
        if (activity.getOffers() != null) {
            LOGGER.info(
                "============================ Activity Offers  ============================ ");
            activity.getOffers().forEach(offer -> {
                // To get the Offer title
                if (StringUtils.isNotEmpty(offer.getTitle())) {
                    LOGGER.info("Offer title: {}", offer.getTitle());
                }
                // To get the Offer description
                if (StringUtils.isNotEmpty(offer.getDescription())) {
                    LOGGER.info("Offer description: {}", offer.getDescription());
                }

                // To get the Offer duration
                if (StringUtils.isNotEmpty(offer.getDuration())) {
                    LOGGER.info("Offer duration: {}", offer.getDuration());
                }

                // To get the offer price
                if (offer.getOfferPrice() != null) {
                    LOGGER.info("Offer price currency: {}",
                        offer.getOfferPrice().getTotalRate().getCurrency());
                    LOGGER.info("Offer price value: {}", offer.getOfferPrice().getTotalRate().getValue());
                }

                // To get the Offer availableTimeSlots
                if (offer.getAvailableTimeSlots() != null) {
                    LOGGER.info(
                        "========================= Activity AvailableTimeSlots  =========================");

                    offer.getAvailableTimeSlots().forEach(availableTimeSlot -> {
                        LOGGER.info("DateTime: {}", availableTimeSlot.getDateTime());
                        LOGGER.info("AllDayActivity: {}", availableTimeSlot.getAllDayActivity());
                        printCancellationPolicy(availableTimeSlot.getCancellationPolicy());

                        // To get ticket info
                        if (availableTimeSlot.getTickets() != null) {
                            LOGGER.info(
                                "============================ Activity ticket  ============================= ");
                            availableTimeSlot.getTickets().forEach(ticket -> {
                                LOGGER.info("Code: {}", ticket.getCode());
                                LOGGER.info("Ticket price currency: {}",
                                    ticket.getTicketPrice().getTotalRate().getCurrency());
                                LOGGER.info("Ticket price value: {}",
                                    ticket.getTicketPrice().getTotalRate().getValue());

                            });
                            LOGGER.info(
                                "============================ Activity ticket  ============================ ");
                        }

                    });

                    LOGGER.info(
                        "======================== Activity AvailableTimeSlots  ===========================");
                }
            });
            LOGGER.info(
                "============================ Activity Offers  ============================ ");
        }

        LOGGER.info(
            "============================= Activity End =============================");

        LOGGER.info(
            "============================ End ListingsQuickStartScenario ===========================");
    }

    private void printCancellationPolicy(ActivitiesCancellationPolicy cancellationPolicy) {
        if (cancellationPolicy != null) {
            LOGGER.info("Activity free cancellation available: {}",
                cancellationPolicy.getFreeCancellation());

            LOGGER.info("FreeCancellationMinHours: {}", cancellationPolicy.getFreeCancellationMinHours());
            LOGGER.info("FreeCancellationEndDateTime: {}",
                cancellationPolicy.getFreeCancellationEndDateTime());
            LOGGER.info("CancelPolicyDescription: {}", cancellationPolicy.getCancelPolicyDescription());

        }
    }

    private void printLocation(ActivitiesLocation location) {
        if (location != null) {
            if (location.getAddress() != null) {
                ActivitiesAddress address = location.getAddress();
                if (StringUtils.isNotEmpty(address.getCountry())) {
                    LOGGER.info("Country: {}", address.getCountry());
                }

                if (StringUtils.isNotEmpty(address.getProvince())) {
                    LOGGER.info("Province: {}", address.getProvince());
                }

                if (StringUtils.isNotEmpty(address.getCity())) {
                    LOGGER.info("City: {}", address.getCity());
                }

                if (StringUtils.isNotEmpty(address.getAddress1())) {
                    LOGGER.info("Address1: {}", address.getAddress1());
                }

                if (StringUtils.isNotEmpty(address.getAddress2())) {
                    LOGGER.info("Address2: {}", address.getAddress2());
                }

            }

            if (location.getGeoLocation() != null) {
                ActivitiesGeoLocation geoLocation = location.getGeoLocation();
                if (StringUtils.isNotEmpty(geoLocation.getLatitude())) {
                    LOGGER.info("Latitude: {}", geoLocation.getLatitude());
                }

                if (StringUtils.isNotEmpty(geoLocation.getLongitude())) {
                    LOGGER.info("Longitude: {}", geoLocation.getLongitude());
                }
            }

        }

    }
}
