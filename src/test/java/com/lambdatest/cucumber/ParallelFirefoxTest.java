package com.lambdatest.cucumber;

import com.lambdatest.LambdaTestSerenityTest;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/single.feature")
public class ParallelFirefoxTest extends LambdaTestSerenityTest {
}
