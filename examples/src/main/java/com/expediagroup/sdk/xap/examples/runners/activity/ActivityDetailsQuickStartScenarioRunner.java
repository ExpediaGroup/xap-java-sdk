package com.expediagroup.sdk.xap.examples.runners.activity;

import com.expediagroup.sdk.core.auth.basic.BasicAuthCredentials;
import com.expediagroup.sdk.core.auth.common.Credentials;
import com.expediagroup.sdk.xap.examples.runners.ScenarioRunner;
import com.expediagroup.sdk.xap.examples.scenarios.activity.ActivityDetailsQuickStartScenario;

public class ActivityDetailsQuickStartScenarioRunner extends ScenarioRunner {

    public static void main(String[] args) {
        Credentials credentials = new BasicAuthCredentials(System.getenv("XAP_KEY"), System.getenv("XAP_SECRET"));
        new ActivityDetailsQuickStartScenario(createClient(credentials)).run();
    }
}
