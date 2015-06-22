package com.epam.preproduction.template;

import org.openqa.selenium.WebDriver;


public class AbstractPage
{
    protected final WebDriver driver;
    public WebDriver getDriver()
    {
        return driver;
    }

    public AbstractPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public AbstractPage navigateTo(String url)
    {
        driver.get(url);
        return this;
    }
}
