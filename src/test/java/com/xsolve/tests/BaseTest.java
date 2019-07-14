package com.xsolve.tests;

import com.xsolve.helpers.DriverFactory;
import com.xsolve.helpers.DriverType;
import com.xsolve.helpers.NoSuchDriverException;
import org.openqa.selenium.*;
import org.testng.annotations.*;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() throws NoSuchDriverException {
        driver = DriverFactory.getDriver(DriverType.CHROME);
    }

    @AfterMethod
    public void tearDown() {
        //driver.quit();
        DriverFactory.resetDriver();
    }
}
