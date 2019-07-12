package com.xsolve.pages;

import com.xsolve.helpers.SeleniumHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private SeleniumHelper helper;
    private WebDriver driver;

    @FindBy(xpath = "//button[contains(@onclick, \"42\")][1]")
    private WebElement product1AddButton;

    @FindBy(xpath = "//button[contains(@onclick, \"41\")][1]")
    private WebElement product3AddButton;

    @FindBy(xpath = "//span[@id = \"cart-total\"]")
    private WebElement cartButton;

    @FindBy(xpath = "//strong[contains(text(), \" Checkout\")]")
    private WebElement checkoutButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        helper = new SeleniumHelper(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage addProduct1ToCart() {
        product1AddButton.click();
        return this;
    }

    public HomePage addProduct3ToCart() {
        helper.waitForElementToBeDisplayed(product3AddButton);
        product3AddButton.click();
        return this;
    }

    public HomePage openCartDropdown() {
        helper.waitForElementToBeDisplayed(cartButton);
        Actions actions = new Actions(driver);
        actions.moveToElement(cartButton).click().perform();
        //cartButton.click();
        return this;
    }

    public HomePage checkout() {
        helper.waitForElementToBeDisplayed(checkoutButton);
        checkoutButton.click();
        return this;
    }

}
