package com.xsolve.tests;

import com.xsolve.pages.CartPage;
import com.xsolve.pages.CheckoutPage;
import com.xsolve.pages.HomePage;
import com.xsolve.pages.OrderPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class OrderProductTest extends BaseTest{

    @Test
    public void buyProduct() {
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

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.setGuestPurchase();
        Assert.assertEquals(checkoutPage.getGuestButtonStep2Text(), "Step 2: Billing Details");
        checkoutPage.switchToBillingDetails()
                .fillBillingDetailsForm()
                .continueToDeliveryDetails();
        Assert.assertTrue(checkoutPage.checkIfStep4AutomaticallyOpened());
        checkoutPage.switchToDeliveryDetails();
        Assert.assertTrue(checkoutPage.checkDeliveryDetailsAutoComplete());
        checkoutPage.continueToDeliveryMethod()
                .addDeliveryComment()
                .continueToPaymentMethod()
                .addPaymentComment()
                .checkAgreementBox()
                .continueToOrderConfirmation();
        Assert.assertTrue(checkoutPage.checkIfTotalPaymentIsCorrect());
        checkoutPage.confirmOrder();
        OrderPage orderPage = new OrderPage(driver);
        Assert.assertTrue(orderPage.checkIfOrderIsSuccessful());
        orderPage.continueToNextPage();
    }
}
