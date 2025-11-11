package com.lambdatest.cucumber.steps;

import com.lambdatest.cucumber.pages.TodoApp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class TodoAppSteps {
    TodoApp todo;

    @When("^I click on checkboxes$")
    public void search_google_for() throws Throwable {
        todo.open();
        todo.clickOn();
    }

    @And("^I add new Element \"([^\"]*)\"$")
    public void thenSubmit(String newText) throws Throwable {
        todo.addNewElement(newText);
    }

    @Then("^I Compare the new added element text with \"([^\"]*)\"$")
    public void matchTitle(String matchElem) throws Throwable {
        todo.assertEqual(matchElem);
    }
}