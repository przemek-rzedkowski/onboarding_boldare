package com.xsolve.pages;

import com.xsolve.helpers.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {

    private SeleniumHelper helper;
    private WebDriver driver;

    @FindBy(xpath = "//h1[.='Your order has been placed!']")
    private WebElement orderSuccesfulField;

    @FindBy(xpath = "//div[@class = \"buttons\"]//a")
    private WebElement continueButton;

    public OrderPage(WebDriver driver) {
        helper = new SeleniumHelper(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void continueToNextPage() {
        helper.waitForElementAndClick(continueButton);
    }

    public boolean checkIfOrderIsSuccessful() {
        return helper.waitForElementAndGetText(orderSuccesfulField).equals("Your order has been placed!");
    }
}
