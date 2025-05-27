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

import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.examples.scenarios.ExampleScenario;
import com.expediagroup.sdk.xap.model.ActivityListingsResponse;
import com.expediagroup.sdk.xap.operation.GetActivityListingsOperation;
import com.expediagroup.sdk.xap.operation.GetActivityListingsOperationParams;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example demonstrates how to search for properties with a location applied.
 */
public class ActivityListingsQuickStartScenario extends ExampleScenario {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(ActivityListingsQuickStartScenario.class);

    public ActivityListingsQuickStartScenario(XapClient client) {
        super(client);
    }

    @Override
    public void run() {
        // This example will search for activities with the following criteria:
        // 1. Activities located in Seattle;
        // 2. Start date 5 days from now, end date 8 days from now;
        // 3. Return Expedia Web details and api details links.
        LOGGER.info(
            "========================== Running ListingsQuickStartScenario =========================");

        LOGGER.info(
            "======================== Executing GetActivityListingsOperation =======================");


        // Build the query parameters with GetActivityListingsOperationParams
        GetActivityListingsOperationParams getActivityListingsOperationParams =
            GetActivityListingsOperationParams.builder()
                .partnerTransactionId(ExampleScenario.PARTNER_TRANSACTION_ID).location("seattle")
                .links(Arrays.asList(GetActivityListingsOperationParams.Links.WD,
                    GetActivityListingsOperationParams.Links.AD))
                .startDate(LocalDate.now().plusDays(5)).endDate(LocalDate.now().plusDays(8)).build();

        // Execute the operation and get the HotelListingsResponse
        ActivityListingsResponse activityListingsResponse = client
            .execute(new GetActivityListingsOperation(getActivityListingsOperationParams)).getData();

        // If you want to use the async method, you can use the following code:
        // ---------------------------------------------------------------
        // CompletableFuture<Response<ActivityListingsResponse>> completableFuture =
        // xapClient.executeAsync(
        // new GetActivityListingsOperation(getActivityListingsOperationParams));
        // completableFuture.thenAccept(activityListingsResponse -> {
        // // Your code here
        // });
        // ---------------------------------------------------------------

        LOGGER.info(
            "======================== GetActivityListingsOperation Executed ========================");

        if (activityListingsResponse.getActivities() == null || activityListingsResponse.getActivities().isEmpty()) {
            throw new IllegalStateException("No activities found.");
        }

        // The ActivityListingsResponse contains a transaction ID for troubleshooting
        LOGGER.info("Transaction ID: {}", activityListingsResponse.getTransactionId());

        // To access the activities, iterate through the list of activity properties
        activityListingsResponse.getActivities().forEach(activity -> {
            LOGGER.info(
                "=================================== Activity Start ===================================");
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
            activity.getMedia();
            LOGGER.info("Activity image: {}", activity.getMedia().get(0).getUrl());

            // To get the activity review count
            if (activity.getReviewCount() != null) {
                LOGGER.info("Activity review count: {}", activity.getReviewCount());
            }

            // To get the activity review score
            if (activity.getReviewScore() != null) {
                LOGGER.info("Activity review score: {}", activity.getReviewScore());
            }

            // To get the activity free cancellation available
            LOGGER.info("Activity free cancellation available: {}", activity.getFreeCancellation());

            // To get the activity price
            activity.getPrice();
            LOGGER.info("Activity price currency: {}",
                activity.getPrice().getTotalRate().getCurrency());
            LOGGER.info("Activity price value: {}", activity.getPrice().getTotalRate().getValue());

            // Get the API details link and web details link from the links collection.
            if (!Objects.requireNonNull(activity.getLinks()).isEmpty()) {
                if (activity.getLinks().get("ApiDetails") != null) {
                    LOGGER.info("ApiDetails Link: {}", activity.getLinks().get("ApiDetails"));
                }

                if (activity.getLinks().get("WebDetails") != null) {
                    LOGGER.info("WebDetails Link: {}", activity.getLinks().get("WebDetails"));
                }
            }

            LOGGER.info(
                "==================================== Activity End ====================================");
        });

        LOGGER.info(
            "============================ End ListingsQuickStartScenario ===========================");
    }
}
