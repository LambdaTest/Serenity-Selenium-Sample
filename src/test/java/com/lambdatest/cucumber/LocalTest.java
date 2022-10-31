package com.lambdatest.cucumber;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

import com.lambdatest.LambdatestSerenityTest;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/local.feature")
public class LocalTest extends LambdatestSerenityTest {
}
