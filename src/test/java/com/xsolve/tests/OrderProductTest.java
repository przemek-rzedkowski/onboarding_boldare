package com.xsolve.tests;

import com.xsolve.pages.CartPage;
import com.xsolve.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;

public class OrderProductTest extends BaseTest{

    @Test
    public void buyProduct() {
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

    }
}
