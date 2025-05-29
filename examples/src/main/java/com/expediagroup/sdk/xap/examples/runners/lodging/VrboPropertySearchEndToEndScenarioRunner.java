package com.expediagroup.sdk.xap.examples.runners.lodging;

import com.expediagroup.sdk.core.auth.basic.BasicAuthCredentials;
import com.expediagroup.sdk.core.auth.common.Credentials;
import com.expediagroup.sdk.xap.examples.runners.ScenarioRunner;
import com.expediagroup.sdk.xap.examples.scenarios.lodging.VrboPropertySearchEndToEndScenario;

public class VrboPropertySearchEndToEndScenarioRunner extends ScenarioRunner {

    public static void main(String[] args) {
        Credentials credentials = new BasicAuthCredentials(System.getenv("VRBO_KEY"), System.getenv("VRBO_SECRET"));
        new VrboPropertySearchEndToEndScenario(createClient(credentials)).run();
    }
}
