package com.xsolve.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmOrderForm extends CheckoutPage {

    @FindBy(xpath = "(//strong[contains(text(), \"Total:\")])[2]/parent::td/following-sibling::td")
    private WebElement totalPaymentField;

    @FindBy(xpath = "//input[@id='button-confirm']")
    private WebElement confirmationButton;

    public ConfirmOrderForm(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void confirmOrder() {
        confirmationButton.click();
    }

    public boolean checkIfTotalPaymentIsCorrect() {
        return helper.waitForElementAndGetText(totalPaymentField).equals("$20,105.00");
    }
}
