package com.lambdatest;

import java.net.URL;
import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import net.thucydides.core.webdriver.DriverSource;

public class LambdaTestSerenityDriver implements DriverSource {

	public WebDriver newDriver() {
		EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

		String username = System.getenv("LT_USERNAME");
		if (username == null) {
			username = (String) environmentVariables.getProperty("lt.user");
		}

		String accessKey = System.getenv("LT_ACCESS_KEY");
		if (accessKey == null) {
			accessKey = (String) environmentVariables.getProperty("lt.key");
		}

		String environment = System.getProperty("environment");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("plugin","Serenity LambdaTest Plugin");

		Iterator it = environmentVariables.getKeys().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();

			if (key.equals("lt.user") || key.equals("lt.key") || key.equals("lt.grid")) {
				continue;
			} else if (key.startsWith("lt_")) {
				capabilities.setCapability(key.replace("lt_", ""), environmentVariables.getProperty(key));

			} else if (environment != null && key.startsWith("environment." + environment)) {
				
				capabilities.setCapability(key.replace("environment." + environment + ".", ""),
						environmentVariables.getProperty(key));
			}
		}

		try {
			String url = "https://" + username + ":" + accessKey + "@" + environmentVariables.getProperty("lt.grid")
					+ "/wd/hub";
			return new RemoteWebDriver(new URL(url), capabilities);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public boolean takesScreenshots() {
		return false;
	}
}
