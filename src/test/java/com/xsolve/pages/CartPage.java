package com.xsolve.pages;

import com.xsolve.helpers.SeleniumHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    private SeleniumHelper helper;
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        helper = new SeleniumHelper(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(text(), \" Minimum order amount for Test product 1 is 2!\")]")
    private WebElement minimumOrderAlert;

    @FindBy(xpath = "//td[contains(text(), \"Product 1\")]/following-sibling::td[1]//input")
    private WebElement product1QuantityField;

    @FindBy(xpath = "//td[contains(text(), \"Product 1\")]/following-sibling::td[1]//i[contains(@class, \"fa fa-refresh\")]")
    private WebElement product1RefreshButton;

    @FindBy(xpath = "//div[contains(text(), \" Success: You have modified your shopping cart!\")]")
    private WebElement modificationSuccessAlert;

    @FindBy(xpath = "//a[contains(text(), \"Checkout\")]")
    private WebElement checkoutButton;



    public String getAlertText() {
        return helper.waitForElementAndGetText(minimumOrderAlert);
    }

    public String getSuccessText() {
        return helper.waitForElementAndGetText(modificationSuccessAlert);
    }

    public CartPage changeProduct1Quantity() {
        helper.waitForElementAndSendKeys(product1QuantityField, "2");
        return this;
    }

    public void refreshProduct1Quantity() {
        helper.waitForElementAndClick(product1RefreshButton);
    }

    public void checkout() {
        checkoutButton.click();
    }
}
