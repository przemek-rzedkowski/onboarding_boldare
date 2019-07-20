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

    @FindBy(xpath = "//div[contains(@class,\"product-thumb\")][.//a[text()=\"%s\"]]\n" +
            "//button[contains(@onclick,\"cart.add\")]\n")
    private WebElement productAddButton;

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
        helper.waitForElementAndClick(product1AddButton);
        return this;
    }

    public HomePage addProduct3ToCart() {
        je.executeScript("window.scrollBy(0, 1000)");
        helper.waitForElementAndClick(product3AddButton);
        return this;
    }

    public HomePage openCartDropdown() {
        je.executeScript("window.scrollBy(0, -1000)");
        helper.waitForElementAndClick(cartButton);
        //actions.moveToElement(cartButton).perform();
        return this;
    }

    public HomePage checkout() {
        helper.waitForElementAndClick(checkoutButton);
        return this;
    }

    public boolean checkIfCartIsEmpty() {
        return helper.waitForElementAndGetText(cart_summary_field).equals("0 item(s) - $0.00");
    }

}
