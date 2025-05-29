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

import com.expediagroup.sdk.core.auth.common.Credentials;
import com.expediagroup.sdk.xap.client.XapClient;
import com.expediagroup.sdk.xap.examples.scenarios.activity.ActivityDetailsQuickStartScenario;
import com.expediagroup.sdk.xap.examples.scenarios.activity.ActivityListingsQuickStartScenario;
import com.expediagroup.sdk.xap.examples.scenarios.car.CarDetailsQuickStartScenario;
import com.expediagroup.sdk.xap.examples.scenarios.car.CarListingsQuickStartScenario;
import com.expediagroup.sdk.xap.examples.scenarios.flight.FlightDetailsScenario;
import com.expediagroup.sdk.xap.examples.scenarios.flight.FlightListingScenario;
import com.expediagroup.sdk.xap.examples.scenarios.lodging.AvailabilityCalendarsQuickStartScenario;
import com.expediagroup.sdk.xap.examples.scenarios.lodging.HotelIdsSearchEndToEndScenario;
import com.expediagroup.sdk.xap.examples.scenarios.lodging.ListingsQuickStartScenario;
import com.expediagroup.sdk.xap.examples.scenarios.lodging.QuotesQuickStartScenario;
import com.expediagroup.sdk.xap.examples.scenarios.lodging.VrboPropertySearchEndToEndScenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.expediagroup.sdk.xap.examples.ScenariosHelper.createClient;
import static com.expediagroup.sdk.xap.examples.ScenariosHelper.getVrboCredentials;
import static com.expediagroup.sdk.xap.examples.ScenariosHelper.getXapCredentials;

/**
 * This is an aggregation runner for all the scenarios for test purposes.
 * For reference, see the individual scenarios in the scenarios package.
 */
public class XapSdkDemoTestRun {
    private static final Logger logger = LoggerFactory.getLogger(XapSdkDemoTestRun.class);

    private static final Credentials xapCredentials = getXapCredentials();
    private static final Credentials vrboCredentials = getVrboCredentials();

    private final static XapClient xapScenarioClient = createClient(xapCredentials);
    private final static XapClient vrboScenarioClient = createClient(vrboCredentials);

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
        new FlightListingScenario(xapScenarioClient).run();
        new FlightDetailsScenario(xapScenarioClient).run();
        logger.info("=============================== End of Flight Scenarios ==============================");


        logger.info("=============================== Cleaning up resources ==============================");
        xapScenarioClient.dispose();
        vrboScenarioClient.dispose();
    }
}
