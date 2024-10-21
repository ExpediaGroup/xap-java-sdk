/*
 * Copyright (C) 2024 Expedia, Inc.
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

import com.expediagroup.sdk.xap.examples.scenarios.lodging.GetAvailabilityCalendarsScenario;
import com.expediagroup.sdk.xap.examples.scenarios.lodging.GetListingsScenario;
import com.expediagroup.sdk.xap.examples.scenarios.lodging.GetQuotesScenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * XAP SDK demo application.
 */
public class XapSdkDemoApplication {
  private static final Logger logger = LoggerFactory.getLogger(XapSdkDemoApplication.class);

  /**
   * Main method.
   */
  public static void main(String[] args) {
    logger.info(
        "======================================================================================");
    logger.info(
        "======================================================================================");
    logger.info(
        "==                                                                                  ==");
    logger.info(
        "==         Howdy! This is a demonstration of Expedia Group XAP SDK. Enjoy!          ==");
    logger.info(
        "==                                                                                  ==");
    logger.info(
        "======================================================================================");
    logger.info(
        "======================================================================================");

    logger.info(
        "============================== Running Lodging Scenarios =============================");

    GetAvailabilityCalendarsScenario getAvailabilityCalendarsScenario =
        new GetAvailabilityCalendarsScenario();
    getAvailabilityCalendarsScenario.run();

    GetListingsScenario getListingsScenario = new GetListingsScenario();
    getListingsScenario.run();

    GetQuotesScenario getQuotesScenario = new GetQuotesScenario();
    getQuotesScenario.run();

    logger.info(
        "=============================== End of Lodging Scenarios ==============================");

    System.exit(0);
  }
}
