package com.epam.preproduction.tests;

import com.epam.preproduction.driverfactory.WebDriverFactory;
import com.epam.preproduction.helpers.Configuration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class TestSetting {
    public WebDriver driver;

    public static final String SUBJECT = "letter from " + Configuration.getConfiguration("user1.login");
    public static final String CONTENT = "Content of letter from " + Configuration.getConfiguration("user1.login");

    @BeforeTest
    public void beforeSuite() {
        String browser = "chrome";
        WebDriverFactory driverFactory = new WebDriverFactory();
        driver = driverFactory.getDriver(browser);
    }

    @AfterTest
    public void afterSuite() {
        driver.quit();
    }
}
