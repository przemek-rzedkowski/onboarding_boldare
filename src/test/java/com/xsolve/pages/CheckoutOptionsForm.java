package com.xsolve.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOptionsForm extends CheckoutPage{

    @FindBy(xpath = "//h4[contains(text(), \"Step 2: Billing Details\")]")
    private WebElement step2LabelButton;

    @FindBy(xpath = "//input[@value = \"guest\"]")
    private WebElement guestButton;

    @FindBy(xpath = "//input[@id='button-account']")
    private WebElement continueButton;

    public CheckoutOptionsForm(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void setGuestPurchase() {
        helper.waitForElementAndClick(guestButton);
    }

    public void switchToBillingDetails() {
        continueButton.click();
    }

    public String getGuestButtonStep2Text() {
        return step2LabelButton.getText();
    }
}
