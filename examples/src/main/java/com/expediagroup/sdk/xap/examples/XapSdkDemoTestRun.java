package com.expediagroup.sdk.xap.examples;


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

        logger.info(
                "============================== Running Lodging Scenarios =============================");

        AvailabilityCalendarsQuickStartScenario availabilityCalendarsQuickStartScenario =
                new AvailabilityCalendarsQuickStartScenario();
        availabilityCalendarsQuickStartScenario.run();

        ListingsQuickStartScenario listingsQuickStartScenario = new ListingsQuickStartScenario();
        listingsQuickStartScenario.run();

        HotelIdsSearchEndToEndScenario hotelIdsSearchEndToEndScenario =
                new HotelIdsSearchEndToEndScenario();
        hotelIdsSearchEndToEndScenario.run();

        VrboPropertySearchEndToEndScenario vrboPropertySearchEndToEndScenario =
                new VrboPropertySearchEndToEndScenario();
        vrboPropertySearchEndToEndScenario.run();

        logger.info(
                "=============================== End of Lodging Scenarios ==============================");

        logger.info(
                "============================== Running Car Scenarios =============================");
        CarListingsQuickStartScenario carListingsQuickStartScenario =
                new CarListingsQuickStartScenario();

        carListingsQuickStartScenario.run();

        CarDetailsQuickStartScenario carDetailsQuickStartScenario = new CarDetailsQuickStartScenario();
        carDetailsQuickStartScenario.run();
        logger.info(
                "=============================== End of Car Scenarios ==============================");

        System.exit(0);
    }
}
