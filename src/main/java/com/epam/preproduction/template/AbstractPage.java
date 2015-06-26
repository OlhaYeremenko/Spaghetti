package com.epam.preproduction.template;


import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class AbstractPage {
    protected final WebDriver driver;
    public static final Logger logger = Logger.getLogger(AbstractPage.class);

    public WebDriver getDriver() {
        return driver;
    }

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public AbstractPage navigateTo(String url) {
        driver.get(url);
        return this;
    }
}
