package com.expediagroup.sdk.xap.examples.runners.activity;

import com.expediagroup.sdk.core.auth.basic.BasicAuthCredentials;
import com.expediagroup.sdk.core.auth.common.Credentials;
import com.expediagroup.sdk.xap.examples.runners.ScenarioRunner;
import com.expediagroup.sdk.xap.examples.scenarios.activity.ActivityListingsQuickStartScenario;

public class ActivityListingsQuickStartScenarioRunner extends ScenarioRunner {

    public static void main(String[] args) {
        Credentials credentials = new BasicAuthCredentials(System.getenv("XAP_KEY"), System.getenv("XAP_SECRET"));
        new ActivityListingsQuickStartScenario(createClient(credentials)).run();
    }
}
