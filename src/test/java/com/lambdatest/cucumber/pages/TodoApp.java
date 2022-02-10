package com.lambdatest.cucumber.pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("https://lambdatest.github.io/sample-todo-app/")
public class TodoApp extends PageObject {
    @FindBy(name = "li1")
    WebElementFacade element1;
    @FindBy(name = "li2")
    WebElementFacade element2;
    @FindBy(id = "sampletodotext")
    WebElementFacade inputBox;
    @FindBy(id = "addbutton")
    WebElementFacade addButton;
    @FindBy(xpath = "/html/body/div/div/div/ul/li[6]/span")
    WebElementFacade newElement;

    public void clickOn() {
        element1.click();
        element2.click();
    }

    public void addNewElement(String newElem) {
        inputBox.sendKeys(newElem);
        addButton.click();
    }

    public void assertEqual(String newString) {
        String text = newElement.getText();
        assertThat(newString).isEqualTo(text);
    }
}