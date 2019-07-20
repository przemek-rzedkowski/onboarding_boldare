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

    protected  SeleniumHelper helper;
    protected  WebDriver driver;
    protected  JavascriptExecutor je;


    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        helper = new SeleniumHelper(driver);
        PageFactory.initElements(driver, this);
        je = (JavascriptExecutor) driver;
    }

}
