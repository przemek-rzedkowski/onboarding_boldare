package com.xsolve.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;

import java.io.File;

public class DriverFactory {

    private static WebDriver driver_instance;

    public static WebDriver getDriver(DriverType driver_type) throws NoSuchDriverException{
        if (driver_instance == null) {
            getSpecificDriver(driver_type);
            driver_instance.get("https://rekrutacjaqa2.xsolve.software/index.php?route=common/home");
            driver_instance.manage().window().maximize();
        }

        return driver_instance;
    }

    private static void getSpecificDriver(DriverType driver_type) throws NoSuchDriverException{

        switch (driver_type) {

            case CHROME:
                File chrome_driver_exe = new File("src//main//resources//executables//drivers//chromedriver.exe");
                ChromeDriverService chrome_driver_service = new ChromeDriverService
                        .Builder()
                        .usingDriverExecutable(chrome_driver_exe)
                        .usingAnyFreePort()
                        .build();
                driver_instance = new ChromeDriver(chrome_driver_service);
                break;

            case FIREFOX:
                File ff_driver_exe = new File("src//main//resources//executables//drivers//geckodriver.exe");       // nie dzialala bez zmiennej srodowiskowej
                GeckoDriverService ff_driver_service = new GeckoDriverService
                        .Builder()
                        .usingDriverExecutable(ff_driver_exe)
                        .usingAnyFreePort()
                        .build();
                driver_instance = new FirefoxDriver(ff_driver_service);
                break;

            default:
                System.out.println("brak danego drivera");
                throw new NoSuchDriverException();
        }
    }

    public static void resetDriver() {
        driver_instance = null;
    }
}
