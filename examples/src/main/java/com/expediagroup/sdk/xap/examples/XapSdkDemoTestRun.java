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
package com.expediagroup.sdk.xap.examples;

import com.expediagroup.sdk.core.auth.basic.BasicAuthCredentials;
import com.expediagroup.sdk.okhttp.OkHttpClientConfiguration;
import com.expediagroup.sdk.okhttp.OkHttpTransport;
import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.configuration.XapClientConfiguration;
import com.expediagroup.sdk.xap.examples.scenarios.activity.ActivityDetailsQuickStartScenario;
import com.expediagroup.sdk.xap.examples.scenarios.activity.ActivityListingsQuickStartScenario;
import com.expediagroup.sdk.xap.examples.scenarios.car.CarDetailsQuickStartScenario;
import com.expediagroup.sdk.xap.examples.scenarios.car.CarListingsQuickStartScenario;
import com.expediagroup.sdk.xap.examples.scenarios.flight.FlightDetailsExample;
import com.expediagroup.sdk.xap.examples.scenarios.flight.FlightListingExample;
import com.expediagroup.sdk.xap.examples.scenarios.lodging.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is an aggregation runner for all the scenarios for test purposes.
 * For reference, see the individual scenarios in the scenarios package.
 */
public class XapSdkDemoTestRun {
    private static final Logger logger = LoggerFactory.getLogger(XapSdkDemoTestRun.class);

    private final static XapClient xapScenarioClient = createClient(System.getenv("XAP_KEY"), System.getenv("XAP_SECRET"));
    private final static XapClient vrboScenarioClient = createClient(System.getenv("VRBO_KEY"), System.getenv("VRBO_SECRET"));

    public static void main(String[] args) {
        logger.info("============================== Running Lodging Scenarios =============================");
        new AvailabilityCalendarsQuickStartScenario(vrboScenarioClient).run();
        new ListingsQuickStartScenario(xapScenarioClient).run();
        new HotelIdsSearchEndToEndScenario(xapScenarioClient).run();
        new VrboPropertySearchEndToEndScenario(vrboScenarioClient).run();
        new QuotesQuickStartScenario(vrboScenarioClient).run();
        logger.info("=============================== End of Lodging Scenarios ==============================");


        logger.info("============================== Running Car Scenarios =============================");
        new CarListingsQuickStartScenario(xapScenarioClient).run();
        new CarDetailsQuickStartScenario(xapScenarioClient).run();
        logger.info("=============================== End of Car Scenarios ==============================");


        logger.info("============================== Running Activity Scenarios =============================");
        new ActivityListingsQuickStartScenario(xapScenarioClient).run();
        new ActivityDetailsQuickStartScenario(xapScenarioClient).run();
        logger.info("=============================== End of Activity Scenarios ==============================");


        logger.info("============================== Running Flight Scenarios =============================");
        new FlightListingExample(xapScenarioClient).run();
        new FlightDetailsExample(xapScenarioClient).run();
        logger.info("=============================== End of Flight Scenarios ==============================");


        logger.info("=============================== Cleaning up resources ==============================");
        xapScenarioClient.dispose();
        vrboScenarioClient.dispose();
    }

    private static XapClient createClient(String key, String secret) {
        OkHttpTransport transport = new OkHttpTransport(
            OkHttpClientConfiguration.builder()
                .callTimeout(100000)
                .connectTimeout(100000)
                .readTimeout(100000)
                .build()
        );

        // Or enable OAuth by passing OAuthCredentials instead:
        // OAuthCredentials credentials = new OAuthCredentials("api-key", "api-secret");
        BasicAuthCredentials credentials = new BasicAuthCredentials(key, secret);
        XapClientConfiguration config = new XapClientConfiguration(credentials, transport);

        return new XapClient(config);
    }
}
