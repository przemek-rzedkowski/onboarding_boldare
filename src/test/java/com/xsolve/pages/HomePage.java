package com.xsolve.pages;

import com.xsolve.helpers.SeleniumHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private SeleniumHelper helper;
    private WebDriver driver;
    JavascriptExecutor je;

    @FindBy(xpath = "//button[contains(@onclick, \"42\")][1]")
    private WebElement product1AddButton;

    @FindBy(xpath = "//button[contains(@onclick, \"41\")][1]")
    private WebElement product3AddButton;

    @FindBy(xpath = "//button[@class = \"btn btn-inverse btn-block btn-lg dropdown-toggle\"]")
    private WebElement cartButton;

    @FindBy(xpath = "//strong[contains(text(), \" Checkout\")]")                                        //z tym jest problem
    private WebElement checkoutButton;

    @FindBy(xpath ="//span[@id='cart-total']")
    private WebElement cart_summary_field;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        helper = new SeleniumHelper(driver);
        PageFactory.initElements(driver, this);
        je = (JavascriptExecutor) driver;
    }

    public HomePage addProduct1ToCart() {
        helper.waitForElementToBeDisplayed(product1AddButton);
        product1AddButton.click();
        return this;
    }

    public HomePage addProduct3ToCart() {
        je.executeScript("window.scrollBy(0, 1000)");
        helper.waitForElementToBeDisplayed(product3AddButton);
        //actions.moveToElement(product3AddButton).click().build().perform();
        product3AddButton.click();
        return this;
    }

    public HomePage openCartDropdown() {
        je.executeScript("window.scrollBy(0, -1000)");
        helper.waitForElementToBeDisplayed(cartButton);
        //actions.moveToElement(cartButton).perform();
        cartButton.click();
        return this;
    }

    public HomePage checkout() {
        helper.waitForElementToBeDisplayed(checkoutButton);
        checkoutButton.click();
        return this;
    }

    public boolean checkIfCartIsEmpty() {
        helper.waitForElementToBeDisplayed(cart_summary_field);
        return cart_summary_field.getText().equals("0 item(s) - $0.00") ? true : false;
    }

}
