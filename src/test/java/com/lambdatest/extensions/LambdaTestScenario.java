package com.lambdatest.extensions;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import net.serenitybdd.core.webdriver.RemoteDriver;
import net.serenitybdd.core.webdriver.enhancers.AfterAWebdriverScenario;
import net.serenitybdd.core.webdriver.enhancers.BeforeAWebdriverScenario;
import net.thucydides.model.domain.TestOutcome;
import net.thucydides.model.util.EnvironmentVariables;
import net.thucydides.core.webdriver.SupportedWebDriver;

public class LambdaTestScenario implements AfterAWebdriverScenario, BeforeAWebdriverScenario {

    // 🧠 Resolve the "unrelated defaults" conflict
    @Override
    public boolean isActivated(EnvironmentVariables environmentVariables) {
        return true; // always active, or add your own condition
    }

    // ✅ Updated for BeforeAWebdriverScenario (pre-driver setup)
    @Override
    public MutableCapabilities apply(EnvironmentVariables environmentVariables,
                                     SupportedWebDriver driver,
                                     TestOutcome testOutcome,
                                     MutableCapabilities capabilities) {
        capabilities.setCapability("name", testOutcome.getStoryTitle() + " - " + testOutcome.getTitle());
        return capabilities;
    }

    // ✅ Updated for AfterAWebdriverScenario (post-test update)
    @Override
    public void apply(EnvironmentVariables environmentVariables,
                      TestOutcome testOutcome,
                      WebDriver driver) {

        if (driver == null || !RemoteDriver.isARemoteDriver(driver)) {
            return;
        }

        try {
            String sessionId = RemoteDriver.of(driver).getSessionId().toString();

            String username = System.getenv("LT_USERNAME");
            if (username == null) {
                username = environmentVariables.getProperty("lt.user");
            }

            String accessKey = System.getenv("LT_ACCESS_KEY");
            if (accessKey == null) {
                accessKey = environmentVariables.getProperty("lt.key");
            }

            URI uri = new URI("https://" + username + ":" + accessKey +
                    "@api.lambdatest.com/automation/api/v1/sessions/" + sessionId);

            HttpPatch patchRequest = new HttpPatch(uri);

            String result = testOutcome.isSuccess() ? "passed"
                    : (testOutcome.isFailure() || testOutcome.isError() || testOutcome.isCompromised())
                    ? "failed" : "completed";

            StringEntity entity = new StringEntity(
                    "{\"name\":\"" + testOutcome.getStoryTitle() + " - " + testOutcome.getTitle() +
                            "\",\"status_ind\":\"" + result + "\"}"
            );

            patchRequest.setEntity(entity);
            HttpClientBuilder.create().build().execute(patchRequest);

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
