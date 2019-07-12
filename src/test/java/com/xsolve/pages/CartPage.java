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



    public String getAlertText() {
        helper.waitForElementToBeDisplayed(minimumOrderAlert);
        return minimumOrderAlert.getText();
    }

    public String getSuccessText() {
        helper.waitForElementToBeDisplayed(modificationSuccessAlert);
        return modificationSuccessAlert.getText();
    }

    public CartPage changeProduct1Quantity() {
        helper.waitForElementToBeDisplayed(product1QuantityField);
        product1QuantityField.clear();
        product1QuantityField.sendKeys("2");
        return this;
    }

    public void refreshProduct1Quantity() {
        //helper.waitForElementToBeDisplayed();
        product1RefreshButton.click();
    }
}
