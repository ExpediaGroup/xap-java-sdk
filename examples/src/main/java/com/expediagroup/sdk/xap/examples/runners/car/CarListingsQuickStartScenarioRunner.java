package com.expediagroup.sdk.xap.examples.runners.car;

import com.expediagroup.sdk.core.auth.basic.BasicAuthCredentials;
import com.expediagroup.sdk.core.auth.common.Credentials;
import com.expediagroup.sdk.xap.examples.runners.ScenarioRunner;
import com.expediagroup.sdk.xap.examples.scenarios.car.CarListingsQuickStartScenario;

public class CarListingsQuickStartScenarioRunner extends ScenarioRunner {

    public static void main(String[] args) {
        Credentials credentials = new BasicAuthCredentials(System.getenv("XAP_KEY"), System.getenv("XAP_SECRET"));
        new CarListingsQuickStartScenario(createClient(credentials)).run();
    }
}
