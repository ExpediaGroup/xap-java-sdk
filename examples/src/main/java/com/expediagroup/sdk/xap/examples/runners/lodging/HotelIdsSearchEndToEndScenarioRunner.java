package com.expediagroup.sdk.xap.examples.runners.lodging;

import com.expediagroup.sdk.core.auth.basic.BasicAuthCredentials;
import com.expediagroup.sdk.core.auth.common.Credentials;
import com.expediagroup.sdk.xap.examples.runners.ScenarioRunner;
import com.expediagroup.sdk.xap.examples.scenarios.lodging.HotelIdsSearchEndToEndScenario;

public class HotelIdsSearchEndToEndScenarioRunner extends ScenarioRunner {

    public static void main(String[] args) {
        Credentials credentials = new BasicAuthCredentials(System.getenv("XAP_KEY"), System.getenv("XAP_SECRET"));
        new HotelIdsSearchEndToEndScenario(createClient(credentials)).run();
    }
}
