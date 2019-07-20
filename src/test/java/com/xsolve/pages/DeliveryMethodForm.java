package com.xsolve.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeliveryMethodForm extends CheckoutPage{

    @FindBy(xpath = "//h4/a[contains(text(), \"Step 3: Delivery Details\")]")
    private WebElement step3LabelButton;

    @FindBy(xpath = "//div[@id='collapse-shipping-method']//p[1]")
    private WebElement step4Paragraph;

    @FindBy(xpath = "//div[@id=\"collapse-shipping-method\"]//textarea[@name='comment']")
    private WebElement deliveryCommentField;

    @FindBy(xpath = "//input[@id='button-shipping-method']")
    private WebElement continueButton;


    public DeliveryMethodForm(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void switchToDeliveryDetails() {
        step3LabelButton.click();
    }

    public DeliveryMethodForm addDeliveryComment() {
        helper.waitForElementAndSendKeys(deliveryCommentField, "lorem ipsum");
        return this;
    }

    public void continueToPaymentMethod() {
        continueButton.click();
    }

    public boolean checkIfStep4AutomaticallyOpened() {
        return helper.checkIfElementIsDisplayed(step4Paragraph);
    }
}
