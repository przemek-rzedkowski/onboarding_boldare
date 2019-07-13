package com.xsolve.pages;

import com.xsolve.helpers.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    SeleniumHelper helper;
    WebDriver driver;

    @FindBy(xpath = "//input[@value = \"guest\"]")
    private WebElement guestButton;

    @FindBy(xpath = "//input[@id='button-account']")
    private WebElement step1ContinueButton;

    @FindBy(xpath = "//h4[contains(text(), \"Step 2: Billing Details\")]")
    private  WebElement step2Header;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        helper = new SeleniumHelper(driver);
        PageFactory.initElements(driver, this);
    }

    public void setGuestPurchase() {
        helper.waitForElementToBeDisplayed(guestButton);
        guestButton.click();
    }

    public CheckoutPage switchToBillingDetails() {
        step1ContinueButton.click();
        return this;
    }



    public String getGuestButtonStep2Text() {
        return step2Header.getText();
    }
}
