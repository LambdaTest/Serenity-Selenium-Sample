package com.lambdatest.cucumber.pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.List;

@DefaultUrl("https://lambdatest.github.io/sample-todo-app/")
public class TodoApp extends PageObject {

    private final static By LIST_ITEMS = By.cssSelector("div[ng-app='sampleApp'] li");

    public void addNewElement(String newItem) {
        $("#sampletodotext").sendKeys(newItem);
        $("#addbutton").click();
    }

    public List<String> listItems() {
        return findAll(LIST_ITEMS).texts();
    }

    public int itemCount() {
        return listItems().size();
    }
}
