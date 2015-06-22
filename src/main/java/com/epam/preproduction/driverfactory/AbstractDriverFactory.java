package com.epam.preproduction.driverfactory;

import org.openqa.selenium.WebDriver;

public abstract class AbstractDriverFactory
{

	public abstract WebDriver getDriver(String parameter);

}