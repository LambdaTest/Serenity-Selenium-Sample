package com.lambdatest.cucumber.steps;

import org.openqa.selenium.JavascriptExecutor;

import com.lambdatest.cucumber.pages.GooglePage;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.webdriver.javascript.JavascriptExecutorFacade;

public class GoogleSearchSteps {
    GooglePage googlePage;
    
    
    @After
    public void afterHook(Scenario scenario) {
    	
    	String name = scenario.getName();
		new JavascriptExecutorFacade(googlePage.getDriver()).executeScript("lambda-name="+ name ); 
		
    	String result = scenario.isFailed() ? "failed":"passed";
		new JavascriptExecutorFacade(googlePage.getDriver()).executeScript("lambda-status="+ result ); 
    }

    @When("^I type query as (.*)$")
    public void search_google_for(String searchWord) throws Throwable {
        googlePage.open();
        googlePage.searchForString(searchWord);
    }

    @Then("^I submit$")
    public void thenSubmit() throws Throwable {
        googlePage.submitForm();
    }

    @Then("^I should see title (.*)$")
    public void matchTitle(String matchTitle) throws Throwable {
        googlePage.titleShouldMatch(matchTitle);
    }
}
