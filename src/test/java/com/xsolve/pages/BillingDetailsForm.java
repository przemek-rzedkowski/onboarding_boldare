package com.xsolve.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BillingDetailsForm extends CheckoutPage {

    @FindBy(xpath = "//input[@name = \"firstname\"]")
    private WebElement firstnameInput;

    @FindBy(xpath = "//input[@name = \"lastname\"]")
    private WebElement lastnameInput;

    @FindBy(xpath = "//input[@id= \"input-payment-email\"]")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@name = \"telephone\"]")
    private WebElement telephoneInput;

    @FindBy(xpath = "//input[@name = \"company\"]")
    private WebElement companyInput;

    @FindBy(xpath = "//input[@name = \"address_1\"]")
    private WebElement address1Input;

    @FindBy(xpath = "//input[@name = \"address_2\"]")
    private WebElement address2Input;

    @FindBy(xpath = "//input[@name = \"city\"]")
    private WebElement cityInput;

    @FindBy(xpath = "//input[@name = \"postcode\"]")
    private WebElement postcodeInput;

    @FindBy(xpath = "//select[@name = \"country_id\"]")
    private WebElement countryInput;

    @FindBy(xpath = "//select[@name = \"zone_id\"]")
    private WebElement zoneInput;

    @FindBy(xpath = "//input[@id='button-guest']")
    private WebElement continueButton;


    public BillingDetailsForm(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public BillingDetailsForm fillBillingDetailsForm(String firstName, String lastName,
                                               String email,
                                               String company, String address1,
                                               String address2, String city, String postalCode,
                                               String country, String zone) {
        //public CheckoutPage fillBillingDetailsForm(String firstName) {
        helper.waitForElementAndSendKeys(firstnameInput, "Mariusz");
        lastnameInput.sendKeys(lastName);
        helper.waitForElementAndSendKeys(emailInput, email);
        telephoneInput.sendKeys("123456789");
        companyInput.sendKeys(company);
        address1Input.sendKeys(address1);
        address2Input.sendKeys(address2);
        cityInput.sendKeys(city);
        postcodeInput.sendKeys(postalCode);
        Select countrySelect = new Select(countryInput);
        countrySelect.selectByVisibleText(country);
        Select zoneSelect = new Select(zoneInput);
        zoneSelect.selectByVisibleText(zone);
        return this;
    }

    public void continueToDeliveryDetails() {
        helper.waitForElementAndClick(continueButton);
    }
}
