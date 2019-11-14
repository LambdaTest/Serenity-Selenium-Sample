# Serenity-Selenium-Sample

[Serenity](http://www.thucydides.info/docs/serenity/) Integration with LambdaTest.

![LambdaTest Logo](https://www.lambdatest.com/images/logo.svg)

<img src="http://www.thucydides.info/docs/serenity/images/serenity-logo.png" height = "100">


1. Global Dependencies
   * Install [Jdk 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
   
2. LambdaTest Credentials
   * Set LambdaTest username and access key in environment variables. It can be obtained from [LambdaTest dashboard](https://automation.lambdatest.com/)    
    example:
   - For linux/mac
    ```
    export LT_USERNAME="YOUR_USERNAME"
    export LT_ACCESS_KEY="YOUR ACCESS KEY"

    ```
    - For Windows
    ```
    set LT_USERNAME="YOUR_USERNAME"
    set LT_ACCESS_KEY="YOUR ACCESS KEY"

    ```
    
3. Setup
	* Clone the repo
	* Install dependencies `mvn install`
	* You can set environment variables or update `serenity.properties` file with your [LambdaTest Username and Access Key](https://automation.lambdatest.com)

## Running your tests
- To run a single test, run `mvn verify -P single`
- To run parallel tests, run `mvn verify -P parallel`

Know how many concurrent sessions needed by using our [Concurrency Test Calculator](https://www.lambdatest.com/concurrency-calculator?ref=github)


## Notes
* You can view your test results on the [LambdaTest Automate dashboard](https://automation.lambdatest.com)
* You can export the environment variables for the Username and Access Key of your LambdaTest account
  
###  Routing traffic through your local machine
 - Set tunnel value to `true` in test capabilities
 > OS specific instructions to download and setup tunnel binary can be found at the following links.
 >    - [Windows](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+Windows)
 >    - [Mac](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+MacOS)
 >    - [Linux](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+Linux)

 ### Important Note:
 Some Safari & IE browsers, doesn't support automatic resolution of the URL string "localhost". Therefore if you test on URLs like "http://localhost/" or "http://localhost:8080" etc, you would get an error in these browsers. A possible solution is to use "localhost.lambdatest.com" or replace the string "localhost" with machine IP address. For example if you wanted to test "http://localhost/dashboard" or, and your machine IP is 192.168.2.6 you can instead test on "http://192.168.2.6/dashboard" or "http://localhost.lambdatest.com/dashboard".


## Notes
* You can view your test results on the [LambdaTest Automation Dashboard](https://www.automation.lambdatest.com)
* To test on a different set of browsers, check out our [Capabilities Generator](https://www.lambdatest.com/capabilities-generator)

## About LambdaTest

[LambdaTest](https://www.lambdatest.com/) is a cloud based selenium grid infrastructure that can help you run automated cross browser compatibility tests on 2000+ different browser and operating system environments. All test data generated during testing including Selenium command logs, screenshots generated in testing, video logs, selenium logs, network logs, console logs, and metadata logs can be extracted using [LambdaTest automation APIs](https://www.lambdatest.com/support/docs/api-doc/). This data can then be used for creating custom reports.

  
## Additional Resources
### [SeleniumHQ Documentation](http://www.seleniumhq.org/docs/)
### [Serenity Documentation](http://thucydides.info/docs/serenity-staging/)
