package com.xsolve.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentMethodForm extends CheckoutPage {

    @FindBy(xpath = "//div[@id='collapse-payment-method']//textarea[@name='comment']")              //po deliveryCommentField wpisuje i tu
    private WebElement paymentCommentField;

    @FindBy(xpath = "//input[@name='agree']")
    private WebElement termsAndConditionsAgreementButton;

    @FindBy(xpath = "//input[@id='button-payment-method']")
    private WebElement continueButton;

    public PaymentMethodForm(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public PaymentMethodForm addPaymentComment() {
        helper.waitForElementAndSendKeys(paymentCommentField, "muspi merol");
        return this;
    }

    public PaymentMethodForm checkAgreementBox() {
        termsAndConditionsAgreementButton.click();
        return this;
    }

    public void continueToOrderConfirmation () {
        continueButton.click();
    }
}
