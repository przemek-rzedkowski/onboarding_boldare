package com.xsolve.tests;
import com.xsolve.helpers.ExcelHelper;
import com.xsolve.helpers.JSONHelper;
import com.xsolve.helpers.TestListener;
import com.xsolve.pages.*;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import org.json.simple.parser.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Listeners(TestListener.class)
public class OrderProductTest extends BaseTest{

    @Test(dataProvider = "getData")
    public void buyProduct(String firstName, String lastName,
                           String email,
                           String company, String address1,
                           String address2, String city, String postalCode,
                           String country, String zone) {
        //driver.manage().timeouts().implicitlyWait(2L, TimeUnit.SECONDS);
        HomePage homePage = new HomePage(driver);
        homePage.addProduct1ToCart()
                .addProduct3ToCart()
                .openCartDropdown()
                .checkout();

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getAlertText(), "Minimum order amount for Test product 1 is 2!\n×");
        cartPage.changeProduct1Quantity()
                .refreshProduct1Quantity();
        Assert.assertEquals(cartPage.getSuccessText(), "Success: You have modified your shopping cart!\n×");
        cartPage.checkout();

        CheckoutOptionsForm checkoutOptionsForm = new CheckoutOptionsForm(driver);
        checkoutOptionsForm.setGuestPurchase();
        Assert.assertEquals(checkoutOptionsForm.getGuestButtonStep2Text(), "Step 2: Billing Details");
        checkoutOptionsForm.switchToBillingDetails();
        BillingDetailsForm billingDetailsForm = new BillingDetailsForm(driver);
        billingDetailsForm.fillBillingDetailsForm(firstName, lastName, email, company, address1, address2, city, postalCode, country, zone)
                .continueToDeliveryDetails();
        DeliveryMethodForm deliveryMethodForm = new DeliveryMethodForm(driver);
        Assert.assertTrue(deliveryMethodForm.checkIfStep4AutomaticallyOpened());
        deliveryMethodForm.switchToDeliveryDetails();
        DeliveryDetailsForm deliveryDetailsForm = new DeliveryDetailsForm(driver);
        System.out.println("sprawdzam " + firstName);
        Assert.assertTrue(deliveryDetailsForm.checkDeliveryDetailsAutoComplete(firstName, company, zone));
        deliveryDetailsForm.continueToDeliveryMethod();
        deliveryMethodForm.addDeliveryComment()
                .continueToPaymentMethod();
        PaymentMethodForm paymentMethodForm = new PaymentMethodForm(driver);
        paymentMethodForm.addPaymentComment()
                .checkAgreementBox()
                .continueToOrderConfirmation();
        ConfirmOrderForm confirmOrderForm = new ConfirmOrderForm(driver);
        Assert.assertTrue(confirmOrderForm.checkIfTotalPaymentIsCorrect());
        confirmOrderForm.confirmOrder();

        OrderPage orderPage = new OrderPage(driver);
        Assert.assertTrue(orderPage.checkIfOrderIsSuccessful());
        orderPage.continueToNextPage();
        Assert.assertTrue(homePage.checkIfCartIsEmpty());
    }

    /*@DataProvider                                         //for xlsx file
    public Object[][] getData() {
        Object[][] data = null;
        try {
            data = ExcelHelper.readExcelFile(new File("src//main//resources//files//dane_buyProduct.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }*/

    @DataProvider
    public Object[][] getData() {

        Object[][] data = null;
        String filePath = "src//main//resources//files//tmp.json";
        data = JSONHelper.readJSONFile(10, filePath);

        return data;
    }
}
