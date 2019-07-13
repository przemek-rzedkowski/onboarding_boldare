package com.xsolve.pages;

import com.xsolve.helpers.SeleniumHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import javax.xml.xpath.XPath;

public class CheckoutPage {

    SeleniumHelper helper;
    WebDriver driver;
    JavascriptExecutor je;

    //step 1: checkout options

    @FindBy(xpath = "//input[@value = \"guest\"]")
    private WebElement guestButton;

    @FindBy(xpath = "//input[@id='button-account']")
    private WebElement step1ContinueButton;

    //step 2: billing details

    @FindBy(xpath = "//h4[contains(text(), \"Step 2: Billing Details\")]")
    private  WebElement step2LabelButton;

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
    private WebElement step2ContinueButton;

    //step 3: delivery details

    @FindBy(xpath = "//h4/a[contains(text(), \"Step 3: Delivery Details\")]")
    private WebElement step3LabelButton;

    @FindBy(xpath = "//input[@id='input-shipping-firstname']")
    private WebElement deliveryDetailsFirstnameField;

    @FindBy(xpath = "//input[@id='input-shipping-company']")
    private WebElement deliveryDetailsCompanyField;

    @FindBy(xpath = "/html//select[@id='input-shipping-zone']")
    private WebElement deliveryDetailsShippingZoneField;

    @FindBy(xpath = "//input[@id='button-guest-shipping']")
    private WebElement step3ContinueButton;

    //step4: delivery method

    @FindBy(xpath = "//div[@id='collapse-shipping-method']//p[1]")
    private WebElement step4Paragraph;

    @FindBy(xpath = "//div[@id=\"collapse-shipping-method\"]//textarea[@name='comment']")
    private WebElement deliveryCommentField;

    @FindBy(xpath = "//input[@id='button-shipping-method']")
    private WebElement step4ContinueButton;

    //step5: payment method

    @FindBy(xpath = "//div[@id='collapse-payment-method']//textarea[@name='comment']")
    private WebElement paymentCommentField;

    @FindBy(xpath = "//input[@name='agree']")
    private WebElement termsAndConditionsAgreementButton;

    @FindBy(xpath = "//input[@id='button-payment-method']")
    private WebElement step5ContinueButton;

    //step6: confirm order

    @FindBy(xpath = "(//strong[contains(text(), \"Total:\")])[2]/parent::td/following-sibling::td")
    private WebElement totalPaymentField;

    @FindBy(xpath = "//input[@id='button-confirm']")
    private WebElement confirmationButton;



    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        helper = new SeleniumHelper(driver);
        PageFactory.initElements(driver, this);
        je = (JavascriptExecutor) driver;
    }

    public void setGuestPurchase() {
        helper.waitForElementToBeDisplayed(guestButton);
        guestButton.click();
    }

    public CheckoutPage switchToBillingDetails() {
        step1ContinueButton.click();
        return this;
    }

    public CheckoutPage fillBillingDetailsForm() {
        helper.waitForElementToBeDisplayed(firstnameInput);
        firstnameInput.sendKeys("Mariusz");
        lastnameInput.sendKeys("Maciak");
        helper.waitForElementToBeDisplayed(emailInput);
        emailInput.sendKeys("maciak@email.com");
        telephoneInput.sendKeys("123456789");
        companyInput.sendKeys("mma");
        address1Input.sendKeys("Olszowa 2");
        address2Input.sendKeys("some");
        cityInput.sendKeys("Rawa");
        postcodeInput.sendKeys("99-999");
        Select countrySelect = new Select(countryInput);
        countrySelect.selectByVisibleText("Poland");
        Select zoneSelect = new Select(zoneInput);
        zoneSelect.selectByVisibleText("Lodzkie");
        return this;
    }

    public void switchToDeliveryDetails() {
        step3LabelButton.click();
    }

    public CheckoutPage continueToDeliveryDetails() {
        helper.waitForElementToBeDisplayed(step2ContinueButton);
        step2ContinueButton.click();
        return this;
    }

    public CheckoutPage continueToDeliveryMethod() {
        step3ContinueButton.click();
        return this;
    }

    public CheckoutPage addDeliveryComment() {
        helper.waitForElementToBeDisplayed(deliveryCommentField);
        deliveryCommentField.sendKeys("lorem ipsum");
        return this;
    }

    public CheckoutPage continueToPaymentMethod() {
        step4ContinueButton.click();
        return this;
    }

    public CheckoutPage addPaymentComment() {
        helper.waitForElementToBeDisplayed(paymentCommentField);
        paymentCommentField.sendKeys("muspi merol");
        return this;
    }

    public CheckoutPage checkAgreementBox() {
        termsAndConditionsAgreementButton.click();
        return this;
    }

    public void continueToOrderConfirmation () {
        step5ContinueButton.click();
    }

    public void confirmOrder() {
        confirmationButton.click();
    }

    public boolean checkIfTotalPaymentIsCorrect() {
        helper.waitForElementToBeDisplayed(totalPaymentField);
        return totalPaymentField.getText().equals("$20,105.00") ? true : false;
    }

    public boolean checkDeliveryDetailsAutoComplete() {
        helper.waitForElementToBeDisplayed(deliveryDetailsFirstnameField);
        Select zoneField = new Select(deliveryDetailsShippingZoneField);
        if (!deliveryDetailsFirstnameField.getAttribute("value").equals("Mariusz")
            ||! deliveryDetailsCompanyField.getAttribute("value").equals("mma")
            || !zoneField.getFirstSelectedOption().getText().equals("Lodzkie")
        ) {
            return false;
        }
        return true;
    }

    public String getGuestButtonStep2Text() {
        return step2LabelButton.getText();
    }

    public boolean checkIfStep4AutomaticallyOpened() {
        helper.waitForElementToBeDisplayed(step4Paragraph);
        return step4Paragraph.isDisplayed();
    }
}
