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

package com.expediagroup.sdk.xap.examples;

import com.expediagroup.sdk.xap.examples.scenarios.activity.ActivityDetailsQuickStartScenario;
import com.expediagroup.sdk.xap.examples.scenarios.activity.ActivityListingsQuickStartScenario;
import com.expediagroup.sdk.xap.examples.scenarios.car.CarDetailsQuickStartScenario;
import com.expediagroup.sdk.xap.examples.scenarios.car.CarListingsQuickStartScenario;
import com.expediagroup.sdk.xap.examples.scenarios.lodging.AvailabilityCalendarsQuickStartScenario;
import com.expediagroup.sdk.xap.examples.scenarios.lodging.HotelIdsSearchEndToEndScenario;
import com.expediagroup.sdk.xap.examples.scenarios.lodging.ListingsQuickStartScenario;
import com.expediagroup.sdk.xap.examples.scenarios.lodging.VrboPropertySearchEndToEndScenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is an aggregation runner for all the scenarios for test purposes.
 * For reference, see the individual scenarios in the scenarios package.
 */
public class XapSdkDemoTestRun {
    private static final Logger logger = LoggerFactory.getLogger(XapSdkDemoTestRun.class);

    /**
     * Main method.
     */
    public static void main(String[] args) {

        logger.info("============================== Running Lodging Scenarios =============================");

        AvailabilityCalendarsQuickStartScenario availabilityCalendarsQuickStartScenario = new AvailabilityCalendarsQuickStartScenario();
        availabilityCalendarsQuickStartScenario.run();

        ListingsQuickStartScenario listingsQuickStartScenario = new ListingsQuickStartScenario();
        listingsQuickStartScenario.run();

        HotelIdsSearchEndToEndScenario hotelIdsSearchEndToEndScenario = new HotelIdsSearchEndToEndScenario();
        hotelIdsSearchEndToEndScenario.run();

        VrboPropertySearchEndToEndScenario vrboPropertySearchEndToEndScenario = new VrboPropertySearchEndToEndScenario();
        vrboPropertySearchEndToEndScenario.run();

        logger.info("=============================== End of Lodging Scenarios ==============================");
        logger.info("============================== Running Car Scenarios =============================");

        CarListingsQuickStartScenario carListingsQuickStartScenario = new CarListingsQuickStartScenario();
        carListingsQuickStartScenario.run();

        CarDetailsQuickStartScenario carDetailsQuickStartScenario = new CarDetailsQuickStartScenario();
        carDetailsQuickStartScenario.run();

        logger.info("=============================== End of Car Scenarios ==============================");
        logger.info("============================== Running Activity Scenarios =============================");

        ActivityListingsQuickStartScenario activityListingsQuickStartScenario = new ActivityListingsQuickStartScenario();
        activityListingsQuickStartScenario.run();

        ActivityDetailsQuickStartScenario activityDetailsQuickStartScenario = new ActivityDetailsQuickStartScenario();
        activityDetailsQuickStartScenario.run();

        logger.info("=============================== End of Activity Scenarios ==============================");

        System.exit(0);
    }
}
