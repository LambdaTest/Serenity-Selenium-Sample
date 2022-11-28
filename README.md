# Run Selenium Tests With Serenity On LambdaTest

![serenity](https://user-images.githubusercontent.com/70570645/171951371-2dd646a7-bae6-4bba-9dbf-d542d786683d.png)


<p align="center">
  <a href="https://www.lambdatest.com/blog/?utm_source=github&utm_medium=repo&utm_campaign=serenity-selenium-sample" target="_bank">Blog</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.lambdatest.com/support/docs/?utm_source=github&utm_medium=repo&utm_campaign=serenity-selenium-sample" target="_bank">Docs</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.lambdatest.com/learning-hub/?utm_source=github&utm_medium=repo&utm_campaign=serenity-selenium-sample" target="_bank">Learning Hub</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.lambdatest.com/newsletter/?utm_source=github&utm_medium=repo&utm_campaign=serenity-selenium-sample" target="_bank">Newsletter</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.lambdatest.com/certifications/?utm_source=github&utm_medium=repo&utm_campaign=serenity-selenium-sample" target="_bank">Certifications</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.youtube.com/c/LambdaTest" target="_bank">YouTube</a>
</p>
&emsp;
&emsp;
&emsp;

*Learn how to configure and run your Java automation testing scripts on LambdaTest platform using Serenity.*

[<img height="58" width="200" src="https://user-images.githubusercontent.com/70570645/171866795-52c11b49-0728-4229-b073-4b704209ddde.png">](https://accounts.lambdatest.com/register?utm_source=github&utm_medium=repo&utm_campaign=serenity-selenium-sample)

## Table Of Contents

* [Pre-requisites](#pre-requisites)
* [Run Your First Test](#run-your-first-test)
* [Parallel Testing With Serenity](#run-your-parallel-tests-using-serenity)
* [Local Testing With Serenity](#testing-locally-hosted-or-privately-hosted-projects)

## Pre-requisites

Before you can start performing Java automation testing with Serenity, you would need to:

- Install the latest **Java development environment** i.e. **JDK 1.6 to JDK 1.8**. 

- Download the latest **Selenium Client** and its **WebDriver bindings** from the [official website](https://www.selenium.dev/downloads/). Latest versions of Selenium Client and WebDriver are ideal for running your automation script on LambdaTest Selenium cloud grid.

- Install **Maven** which supports **JUnit** framework out of the box. **Maven** can be downloaded and installed following the steps from [the official website](https://maven.apache.org/). Maven can also be installed easily on **Linux/MacOS** using [Homebrew](https://brew.sh/) package manager.

### Cloning Repo And Installing Dependencies

**Step 1:** Clone the LambdaTest’s Serenity-Selenium-Sample repository and navigate to the code directory as shown below:

```bash
git clone https://github.com/LambdaTest/Serenity-Selenium-Sample
cd Serenity-Selenium-Sample
```

You may also want to run the command below to check for outdated dependencies.

```bash
mvn versions:display-dependency-updates
```

### Setting Up Your Authentication

Make sure you have your LambdaTest credentials with you to run test automation scripts. You can get these credentials from the [LambdaTest Automation Dashboard](https://automation.lambdatest.com/build?utm_source=github&utm_medium=repo&utm_campaign=serenity-selenium-sample) or by your [LambdaTest Profile](https://accounts.lambdatest.com/login?utm_source=github&utm_medium=repo&utm_campaign=serenity-selenium-sample).

**Step 2:** Set LambdaTest **Username** and **Access Key** in environment variables.

* For **Linux/macOS**:
  
  ```bash
  export LT_USERNAME="YOUR_USERNAME" 
  export LT_ACCESS_KEY="YOUR ACCESS KEY"
  ```
  * For **Windows**:
  ```bash
  set LT_USERNAME="YOUR_USERNAME" 
  set LT_ACCESS_KEY="YOUR ACCESS KEY"

## Run Your First Test

>**Test Scenario**: To run your first Serenity Test on LambdaTest Selenium Grid, let’s understand our test case scenario, the test case below checks for the word "**LambdaTest**" on Google and tests if the title of the resultant page is "**LambdaTest-Google Search**".

```bash
Feature: Google's Search Functionality
    Scenario: Can find search results
        When I type query as "LambdaTest"
        And I submit
        Then I should see title "LambdaTest - Google Search"
```

Following below is the `GooglePage.java` file for the above Test Case Scenario.

```java title="GooglePage.java"
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

Below is the `LambdaTestSerenityDriver.java` file that shows the integration of Serenity with LambdaTest.

```java title="LambdaTestSerenityDriver.java"
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

You can generate capabilities for your test requirements with the help of our inbuilt [Desired Capability Generator](https://www.lambdatest.com/capabilities-generator/?utm_source=github&utm_medium=repo&utm_campaign=serenity-selenium-sample).


### Executing the Test

**Step 3:** The tests can be executed in the terminal using the following command:

```bash
mvn verify -P single
```

## Run Your Parallel Test Using Serenity


To run parallel tests with Serenity, we will run **single.feature** test case in four different environments Chrome, Firefox, IE, and Safari.

```java title="ParallelChromeTest.java"
//Running Parallel Test On Chrome

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/single.feature")
public class ParallelChromeTest extends LambdaTestSerenityTest {
}
```

Similarly we define the class for the remaining browsers.

### Executing Parallel Tests Using Serenity

To run parallel tests using Serenity, we would have to execute the below commands in the terminal:

```bash
mvn verify -P parallel
```

## Testing Locally Hosted Or Privately Hosted Projects

You can test your locally hosted or privately hosted projects with LambdaTest Selenium grid using LambdaTest Tunnel. All you would have to do is set up an SSH tunnel using tunnel and pass toggle `tunnel = True` via desired capabilities. LambdaTest Tunnel establishes a secure SSH protocol based tunnel that allows you in testing your locally hosted or privately hosted pages, even before they are live.

Refer our [LambdaTest Tunnel documentation](https://www.lambdatest.com/support/docs/testing-locally-hosted-pages/?utm_source=github&utm_medium=repo&utm_campaign=serenity-selenium-sample) for more information.

Here’s how you can establish LambdaTest Tunnel.

Download the binary file of:
* [LambdaTest Tunnel for Windows](https://downloads.lambdatest.com/tunnel/v3/windows/64bit/LT_Windows.zip)
* [LambdaTest Tunnel for macOS](https://downloads.lambdatest.com/tunnel/v3/mac/64bit/LT_Mac.zip)
* [LambdaTest Tunnel for Linux](https://downloads.lambdatest.com/tunnel/v3/linux/64bit/LT_Linux.zip)

Open command prompt and navigate to the binary folder.

Run the following command:

```bash
LT -user {user’s login email} -key {user’s access key}
```
So if your user name is lambdatest@example.com and key is 123456, the command would be:

```bash
LT -user lambdatest@example.com -key 123456
```
Once you are able to connect **LambdaTest Tunnel** successfully, you would just have to pass on tunnel capabilities in the code shown below :

**Tunnel Capability**

```java
DesiredCapabilities capabilities = new DesiredCapabilities();        
        capabilities.setCapability("tunnel", true);
```

## Documentation & Resources :books:

      
Visit the following links to learn more about LambdaTest's features, setup and tutorials around test automation, mobile app testing, responsive testing, and manual testing.

* [LambdaTest Documentation](https://www.lambdatest.com/support/docs/?utm_source=github&utm_medium=repo&utm_campaign=serenity-selenium-sample)
* [LambdaTest Blog](https://www.lambdatest.com/blog/?utm_source=github&utm_medium=repo&utm_campaign=serenity-selenium-sample)
* [LambdaTest Learning Hub](https://www.lambdatest.com/learning-hub/?utm_source=github&utm_medium=repo&utm_campaign=serenity-selenium-sample)    

## LambdaTest Community :busts_in_silhouette:

The [LambdaTest Community](https://community.lambdatest.com/?utm_source=github&utm_medium=repo&utm_campaign=serenity-selenium-sample) allows people to interact with tech enthusiasts. Connect, ask questions, and learn from tech-savvy people. Discuss best practises in web development, testing, and DevOps with professionals from across the globe 🌎

## What's New At LambdaTest ❓

To stay updated with the latest features and product add-ons, visit [Changelog](https://changelog.lambdatest.com/) 
      
## About LambdaTest

[LambdaTest](https://www.lambdatest.com/?utm_source=github&utm_medium=repo&utm_campaign=serenity-selenium-sample) is a leading test execution and orchestration platform that is fast, reliable, scalable, and secure. It allows users to run both manual and automated testing of web and mobile apps across 3000+ different browsers, operating systems, and real device combinations. Using LambdaTest, businesses can ensure quicker developer feedback and hence achieve faster go to market. Over 500 enterprises and 1 Million + users across 130+ countries rely on LambdaTest for their testing needs.    

### Features

* Run Selenium, Cypress, Puppeteer, Playwright, and Appium automation tests across 3000+ real desktop and mobile environments.
* Real-time cross browser testing on 3000+ environments.
* Test on Real device cloud
* Blazing fast test automation with HyperExecute
* Accelerate testing, shorten job times and get faster feedback on code changes with Test At Scale.
* Smart Visual Regression Testing on cloud
* 120+ third-party integrations with your favorite tool for CI/CD, Project Management, Codeless Automation, and more.
* Automated Screenshot testing across multiple browsers in a single click.
* Local testing of web and mobile apps.
* Online Accessibility Testing across 3000+ desktop and mobile browsers, browser versions, and operating systems.
* Geolocation testing of web and mobile apps across 53+ countries.
* LT Browser - for responsive testing across 50+ pre-installed mobile, tablets, desktop, and laptop viewports

    
[<img height="58" width="200" src="https://user-images.githubusercontent.com/70570645/171866795-52c11b49-0728-4229-b073-4b704209ddde.png">](https://accounts.lambdatest.com/register?utm_source=github&utm_medium=repo&utm_campaign=serenity-selenium-sample)

## We are here to help you :headphones:

* Got a query? we are available 24x7 to help. [Contact Us](mailto:support@lambdatest.com)
* For more info, visit - [LambdaTest](https://www.lambdatest.com/?utm_source=github&utm_medium=repo&utm_campaign=serenity-selenium-sample)
