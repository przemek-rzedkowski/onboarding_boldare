package com.xsolve.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class SeleniumHelper {

    private WebDriver driver;

    public SeleniumHelper(WebDriver driver) {
        this.driver = driver;
    }

    public static void takeScreenshot(WebDriver driver) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("src/main/resources/" + LocalTime.now().getNano() + ".png");
        //File destinationFile = new File("G:\\\\screenshot2.png");
        Files.copy(screenshotFile.toPath(), destinationFile.toPath());
    }

    private void waitForElementToBeDisplayed(By locator) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(2000))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void waitForElementToBeDisplayed(WebElement element) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitForSizeOfListOfElements(List<WebElement> elements) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(java.util.NoSuchElementException.class);
        wait.until(webDriver -> elements.size() > 0);
    }

    public void waitForElementAndClick(WebElement element) {
        waitForElementToBeDisplayed(element);
        element.click();
    }

    public void waitForElementAndSendKeys(WebElement element, String keys) {
        waitForElementToBeDisplayed(element);
        element.clear();
        element.sendKeys(keys);
    }

    public String waitForElementAndGetText(WebElement element) {
        waitForElementToBeDisplayed(element);
        return element.getText();
    }

    public boolean checkIfElementIsDisplayed(WebElement element) {
        waitForElementToBeDisplayed(element);
        return element.isDisplayed();
    }

}
