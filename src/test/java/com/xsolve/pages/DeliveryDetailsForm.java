package com.xsolve.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DeliveryDetailsForm extends CheckoutPage {

    @FindBy(xpath = "//input[@id='input-shipping-firstname']")
    private WebElement deliveryDetailsFirstnameField;

    @FindBy(xpath = "//input[@id='input-shipping-company']")
    private WebElement deliveryDetailsCompanyField;

    @FindBy(xpath = "/html//select[@id='input-shipping-zone']")
    private WebElement deliveryDetailsShippingZoneField;

    @FindBy(xpath = "//input[@id='button-guest-shipping']")
    private WebElement continueButton;


    public DeliveryDetailsForm(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void continueToDeliveryMethod() {
        continueButton.click();
    }

    public boolean checkDeliveryDetailsAutoComplete(String firstName, String company, String zone) {
        helper.waitForElementAndClick(deliveryDetailsFirstnameField);
        Select zoneField = new Select(deliveryDetailsShippingZoneField);
        if (!deliveryDetailsFirstnameField.getAttribute("value").equals(firstName)
                ||! deliveryDetailsCompanyField.getAttribute("value").equals(company)
                || !zoneField.getFirstSelectedOption().getText().equals(zone)
        ) {
            return false;
        }
        return true;
    }

}
