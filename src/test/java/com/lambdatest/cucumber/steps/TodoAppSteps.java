package com.lambdatest.cucumber.steps;

import com.lambdatest.cucumber.pages.TodoApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class TodoAppSteps {
    TodoApp todo;

    @Given("I have opened the app")
    @When("I open the app")
    public void iOpenTheApp() {
        todo.open();
    }

    @When("I add new item {string}")
    public void iAddNewItem(String item) {
        todo.addNewElement(item);
    }

    @Then("I should see {int} items")
    public void iShouldSeeItems(int expectedItems) {
        assertThat(todo.itemCount()).isEqualTo(expectedItems);
    }
}
