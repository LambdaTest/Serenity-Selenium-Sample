package com.lambdatest;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.thucydides.model.environment.SystemEnvironmentVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import net.thucydides.model.util.EnvironmentVariables;
import net.thucydides.core.webdriver.DriverSource;

public class LambdaTestSerenityDriver implements DriverSource {

    @Override
    public WebDriver newDriver() {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

        String username = System.getenv("LT_USERNAME");
        if (username == null) {
            username = environmentVariables.getProperty("lt.user");
        }

        String accessKey = System.getenv("LT_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = environmentVariables.getProperty("lt.key");
        }

        String environment = System.getProperty("environment");

        // ✅ Replaces DesiredCapabilities with ChromeOptions (Selenium 4 standard)
        ChromeOptions options = new ChromeOptions();

        // ✅ Prepare lt:options map for W3C-compliant capabilities
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("plugin", "Serenity LambdaTest Plugin");

        Iterator<String> it = environmentVariables.getKeys().iterator();
        while (it.hasNext()) {
            String key = it.next();

            if (key.equals("lt.user") || key.equals("lt.key") || key.equals("lt.grid")) {
                continue;
            } else if (key.startsWith("lt_")) {
                ltOptions.put(key.replace("lt_", ""), environmentVariables.getProperty(key));

            } else if (environment != null && key.startsWith("environment." + environment)) {
                ltOptions.put(
                        key.replace("environment." + environment + ".", ""),
                        environmentVariables.getProperty(key)
                );
            }
        }

        // ✅ Attach lt:options to ChromeOptions
        options.setCapability("lt:options", ltOptions);

        try {
            String gridUrl = environmentVariables.getProperty("lt.grid", "hub.lambdatest.com");
            String url = "https://" + username + ":" + accessKey + "@" + gridUrl + "/wd/hub";
            return new RemoteWebDriver(new URL(url), options);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create LambdaTest driver: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}
