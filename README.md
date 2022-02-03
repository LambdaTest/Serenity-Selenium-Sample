# Serenity-Selenium-Sample
![Java](https://opengraph.githubassets.com/4208cab6737ed9b029839980270f512d12a48c066f3b6e26a69281ea2624f0a4/LambdaTest/Serenity-Selenium-Sample)

### Prerequisites
1. Install and set environment variable for java.
    * Windows - https://www.oracle.com/java/technologies/downloads/
    * Linux - ```  sudo apt-get install openjdk-8-jre  ```
    * MacOS - Java should already be present on Mac OS X by default.
2 Install and set environment varibale for Maven.
    * Windows - https://maven.apache.org/install.html
    * Linux/ MacOS -  [Homebrew](http://brew.sh/) (Easier)
    ```
     install maven
    ```
    
### Run your First Test
1. Clone the Serenity-Selenium-Sample repository. 
```
git clone https://github.com/LambdaTest/Serenity-Selenium-Sample
```
2. Next get into Serenity-Selenium-Sample folder, and import Lamabdatest Credentials. You can get these from lambdatest automation dashboard.
   <p align="center">
   <b>For Linux/macOS:</b>:
 
```
export LT_USERNAME="YOUR_USERNAME"
export LT_ACCESS_KEY="YOUR ACCESS KEY"
```
<p align="center">
   <b>For Windows:</b>

```
set LT_USERNAME="YOUR_USERNAME"
set LT_ACCESS_KEY="YOUR ACCESS KEY"
```

Step 3. Run single test.
```
mvn verify -P single
```

### Result of the Test
You can see the test running on LambdaTest [Automation Dashboard](https://automation.lambdatest.com/build)
### Run Test Parallel.
One of the most important features of LambdaTest Selenium grid is the ability to run your test cases in parallel. To run parallel test.
```
mvn verify -P parallel
```

##  Testing Locally Hosted or Privately Hosted Projects

To help you perform cross browser testing of your locally stored web pages, LambdaTest provides an SSH(Secure Shell) tunnel connection with the name Lambda Tunnel. With Lambda Tunnel, you can test your locally hosted files before you make them live over the internet. You could even perform cross browser testing from different IP addresses belonging to various geographic locations. You can also use LambdaTest Tunnel to test web-apps and websites that are permissible inside your corporate firewall.

* Set tunnel value to True in test capabilities
> OS specific instructions to download and setup tunnel binary can be found at the following links.
>    - [Windows](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+Windows)
>    - [Mac](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+MacOS)
>    - [Linux](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+Linux)

After setting tunnel you can also see the active tunnel in our LambdaTest dashboard:


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
