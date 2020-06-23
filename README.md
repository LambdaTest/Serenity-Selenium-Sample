# Serenity-Selenium-Sample

[Serenity](http://www.thucydides.info/docs/serenity/) Integration with [LambdaTest](https://www.lambdatest.com).

![LambdaTest Logo](https://www.lambdatest.com/images/logo.svg)

<img src="http://www.thucydides.info/docs/serenity/images/serenity-logo.png" height = "50">


## Prerequisites for running tests using Serenity automation framework :

## Environment Setup 

### 1. Java Installation
   
   i.   For Windows :
   
   You can download Java for Windows from [here](http://www.java.com/en/download/manual.jsp)
   
   Run the installer and follow the setup wizard to install Java.
   
   create a new JAVA_HOME environment variable and set variable value as path value to JDK folder.
   
   #### This is Windows environment variable location :
   Control Panel > All Control Panel Items > System > Advanced system settings > Environment Variables
   
   ![altext](https://github.com/keshavissar001/selenide-testng-sample/blob/keshavissar001-patch-1/Img1.png)
   
   ii. For Linux :
   
   use this command :
   ```
   sudo apt-get install openjdk-8-jre
   ```
   iii. For Mac
   
   Java should already be present on Mac OS X by default
   
   ### 2. Maven Installation
   
   Install Maven from [here](https://maven.apache.org/install.html)

### 3. LambdaTest Authentication Credentials
   
  Make sure you have your LambdaTest credentials with you to run test automation scripts with Jest on LambdaTest Selenium Grid. You can obtain these credentials from the [LambdaTest Automation Dashboard](https://automation.lambdatest.com/) or through [LambdaTest Profile](https://accounts.lambdatest.com/detail/profile).
  Set LambdaTest Username and Access Key in environment variables.
* For Linux/macOS:
 `export LT_USERNAME="YOUR_USERNAME"
  export LT_ACCESS_KEY="YOUR ACCESS KEY"`

* For Windows:
 `set LT_USERNAME="YOUR_USERNAME"
  set LT_ACCESS_KEY="YOUR ACCESS KEY"`
  
## Setting Up The First Project For Serenity Automation Testing 

   **Step 1:** Clone this [GitHub repository for Serenity framework](https://github.com/LambdaTest/Serenity-Selenium-Sample). To clone the file, run the below command in your terminal or command prompt:
   
   `https://github.com/LambdaTest/Serenity-Selenium-Sample.git` 
 
   **Step 2:** After cloning, you'll have a zip file downloaded in your system. Right click on the zip file and extract files in your desired location.
   
   **Step 3:** Open terminal or command prompt and bring the pointer to the same folder where you extracted the cloned repository.  
	![altext](https://github.com/keshavissar001/images/blob/master/SerenityPath.png)

  **Step 4:** Under the folder **“Serenity-Selenium-Sample-master”**, add your LambdaTest `username` and `accessKey` to the `serenity.properties` file in “Serenity-Selenium-Sample-master”
 [For Lambdatest Credentials, Go to Lambdatest Profile Page](https://accounts.lambdatest.com/profile) 

**Note:** The *serenity.properties* files will help you to specify the configurations/capabilities over which you wish your Serenity test scripts to run. It is necessary to put your LambdaTest authentication credentials i.e. ***your LambdaTest Access Key*** & ***your LambdaTest Username*** in *serenity.properties* file to run your Serenity script over LambdaTest Selenium Grid. 

 
 This is `serenity.properties` file to setup mandatory details to run at LambdaTest :
 
 ```
 
 webdriver.driver = provided
webdriver.provided.type = mydriver
webdriver.provided.mydriver = com.lambdatest.LambdaTestSerenityDriver
thucydides.driver.capabilities = mydriver

serenity.extension.packages = com.lambdatest.extensions

webdriver.timeouts.implicitlywait = 5000
serenity.use.unique.browser = false
serenity.dry.run=false
#serenity.take.screenshots=AFTER_EACH_STEP

lt.user=LT_USERNAME		// Add your username here
lt.key=LT_ACCESS_KEY		// Add your access key here
lt.grid=hub.lambdatest.com

#You can add more capability with a prefix 'lt_' as below
#For example to use lt_network as true use below capability
lt_build=Serenity-Selenium-Sample
lt_debug=true
lt_console=false
lt_visual=false

#You can add more capability with a prefix 'environment.{environment}.' as below
#Check valid capabilities here - https://www.lambdatest.com/capabilities-generator/

environment.single.browser=Chrome
environment.single.browserVersion=78
environment.single.platform=Windows 10

environment.parallel_1.browser=Chrome
environment.parallel_1.browserVersion=78
environment.parallel_1.platform=Windows 10

environment.parallel_2.browser=firefox
environment.parallel_2.browserVersion=68
environment.parallel_2.platform=Windows 8.1

environment.parallel_3.browser=Safari
environment.parallel_3.browserVersion=12
environment.parallel_3.platform=MacOS Mojave

environment.parallel_4.browser=Internet Explorer
environment.parallel_4.browserVersion=11
environment.parallel_4.platform=Windows 7

#environment.parallel_5.deviceName=Galaxy S10

```

## Running your tests

Let’s start with a simple Selenium Remote Webdriver test first. This Selenide script below tests whether the expected title is same as that of given page.

This is Single.feature file that is defining the scenario of the test : 

```
Feature: Google's Search Functionality

    Scenario: Can find search results
        When I type query as "LambdaTest"
        And I submit
        Then I should see title "LambdaTest - Google Search"
```

This is LambdaTestSerenityTest.java file that is executed before and after class to setup and teardown the remote webdriver : 

```
package com.lambdatest;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class LambdaTestSerenityTest {

	@BeforeClass
	public static void setUp() throws Exception {
	}

	@AfterClass
	public static void tearDown() throws Exception {
	}
}
```
This is LambdaTestSerenityDriver.java file that sets up the remote webdriver for your test: 

```
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
```

This is GoogleSearchSteps.java file that is defining the steps of searching and matching the titles :

```
package com.lambdatest.cucumber.steps;

import com.lambdatest.cucumber.pages.GooglePage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GoogleSearchSteps {
    GooglePage googlePage;

    @When("^I type query as \"([^\"]*)\"$")
    public void search_google_for(String searchWord) throws Throwable {
        googlePage.open();
        googlePage.searchForString(searchWord);
    }

    @Then("^I submit$")
    public void thenSubmit() throws Throwable {
        googlePage.submitForm();
    }

    @Then("^I should see title \"([^\"]*)\"$")
    public void matchTitle(String matchTitle) throws Throwable {
        googlePage.titleShouldMatch(matchTitle);
    }
}

```

This is GooglePage.java file that is helping in searching and comparing the titles :

```
package com.lambdatest.cucumber.pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("https://www.google.com/ncr")
public class GooglePage extends PageObject {

    @FindBy(name = "q")
    WebElementFacade search;

    @FindBy(name = "btnK")
    WebElementFacade searchButton;

    public void searchForString(String searchString) {
        search.sendKeys(searchString);
    }

    public void submitForm() throws Exception {
        searchButton.click();
        Thread.sleep(5000);
    }

    public void titleShouldMatch(String matchTitle) {
        assertThat(this.getTitle()).containsIgnoringCase(matchTitle);
    }
}

```

## Executing Single Test In Serenity Automation Framework :

This is SingleTest.java file : 

```

package com.lambdatest.cucumber;

import com.lambdatest.LambdaTestSerenityTest;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/single.feature")
public class SingleTest extends LambdaTestSerenityTest {
}

```

- To run a single test, use this command :
	 `mvn verify -P single`

These are the screenshots of the output :

![altext](https://github.com/keshavissar001/images/blob/master/SerenitySingleResult1.png)

![altext](https://github.com/keshavissar001/images/blob/master/SerenitySingleResult3.png)

## Executing Parallel Tests In Serenity Automation Framework :

One of the most important features of LambdaTest Selenium grid is the ability to run your test cases in parallel. What that means is that if you have more than one concurrent session, you can run your test cases on more than one machine at a time, which greatly cuts down your test times. To put it in perspective, if you have 100 test cases each with an average run time of 1 minute, without parallel testing it would take 100 minutes to execute. However, with 2 concurrent sessions, you can run 2 test cases in parallel at a time and can cut down the build’s test time to 50 minutes. With four concurrent sessions, it would cut down to 25 minutes. With eight, well you got the picture.

This is ParallelChromeTest.java file :

```
package com.lambdatest.cucumber;

import com.lambdatest.LambdaTestSerenityTest;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/single.feature")
public class ParallelChromeTest extends LambdaTestSerenityTest {
}

```

This is ParallelFirefoxTest.java file :

```
package com.lambdatest.cucumber;

import com.lambdatest.LambdaTestSerenityTest;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/single.feature")
public class ParallelFirefoxTest extends LambdaTestSerenityTest {
}

```

This is ParallelIETest.java file :

```
package com.lambdatest.cucumber;

import com.lambdatest.LambdaTestSerenityTest;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/single.feature")
public class ParallelIETest extends LambdaTestSerenityTest {
}

```

This is ParallelSafariTest.java file :

```
package com.lambdatest.cucumber;

import com.lambdatest.LambdaTestSerenityTest;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/single.feature")
public class ParallelSafariTest extends LambdaTestSerenityTest {
}
```

- To run parallel tests,  use this command :
	 `mvn verify -P parallel`

These are the screenshots of the output :

![altext](https://github.com/keshavissar001/images/blob/master/SerenityParallelResult1.png)

![altext](https://github.com/keshavissar001/images/blob/master/SerenityParallelResult2.png)

Know how many concurrent sessions needed by using our [Concurrency Test Calculator](https://www.lambdatest.com/concurrency-calculator?ref=github)

Below we see a screenshot that depicts our Selenide code is running over different browsers i.e Chrome, IE on the LambdaTest Selenium Grid Platform. The results of the test script execution along with the logs can be accessed from the LambdaTest Automation dashboard.

![altext](https://github.com/keshavissar001/images/blob/master/Serenity_Automation_Timeline.png)

## Notes
* You can view your test results on the [LambdaTest Automate dashboard](https://automation.lambdatest.com)
* You can export the environment variables for the Username and Access Key of your LambdaTest account
  
##  Testing Locally Hosted or Privately Hosted Projects

To help you perform cross browser testing of your locally stored web pages, LambdaTest provides an SSH(Secure Shell) tunnel connection with the name Lambda Tunnel. With Lambda Tunnel, you can test your locally hosted files before you make them live over the internet. You could even perform cross browser testing from different IP addresses belonging to various geographic locations. You can also use LambdaTest Tunnel to test web-apps and websites that are permissible inside your corporate firewall.

* Set tunnel value to True in test capabilities
> OS specific instructions to download and setup tunnel binary can be found at the following links.
>    - [Windows](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+Windows)
>    - [Mac](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+MacOS)
>    - [Linux](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+Linux)

After setting tunnel you can also see the active tunnel in our LambdaTest dashboard:


![altext](https://github.com/keshavissar001/images/blob/master/tunnel.PNG)

 ### Important Note:
 Some Safari & IE browsers, doesn't support automatic resolution of the URL string "localhost". Therefore if you test on URLs like "http://localhost/" or "http://localhost:8080" etc, you would get an error in these browsers. A possible solution is to use "localhost.lambdatest.com" or replace the string "localhost" with machine IP address. For example if you wanted to test "http://localhost/dashboard" or, and your machine IP is 192.168.2.6 you can instead test on "http://192.168.2.6/dashboard" or "http://localhost.lambdatest.com/dashboard".


## Notes
* You can view your test results on the [LambdaTest Automation Dashboard](https://www.automation.lambdatest.com)
* To test on a different set of browsers, check out our [Capabilities Generator](https://www.lambdatest.com/capabilities-generator)

## About LambdaTest

[LambdaTest](https://www.lambdatest.com/) is a cloud based selenium grid infrastructure that can help you run automated cross browser compatibility tests on 2000+ different browser and operating system environments. LambdaTest supports all programming languages and frameworks that are supported with Selenium, and have easy integrations with all popular CI/CD platforms. It's a perfect solution to bring your [selenium automation testing](https://www.lambdatest.com/selenium-automation) to cloud based infrastructure that not only helps you increase your test coverage over multiple desktop and mobile browsers, but also allows you to cut down your test execution time by running tests on parallel.
  
## Additional Resources
* [SeleniumHQ Documentation](http://www.seleniumhq.org/docs/)
* [Serenity Documentation](http://thucydides.info/docs/serenity-staging/)
* [LambdaTest Documentation](https://www.lambdatest.com/support/docs/getting-started-with-lambdatest-automation/)
