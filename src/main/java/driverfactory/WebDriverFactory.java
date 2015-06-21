package driverfactory;


import helpers.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class WebDriverFactory extends AbstractDriverFactory {

    @Override
    public WebDriver getDriver(String parameter) {
        switch (parameter) {
            case "firefox":
                FirefoxProfile firefoxProfile= new FirefoxProfile();
                firefoxProfile.setEnableNativeEvents(true);
                return new FirefoxDriver(firefoxProfile);
            case "ie":
                System.setProperty("webdriver.ie.driver",
                        Configuration.getConfiguration("ie.driver"));
                return new InternetExplorerDriver();
            case "opera":
                System.setProperty("webdriver.opera.driver",
                        Configuration.getConfiguration("opera.driver"));
                return new OperaDriver();
            case "chrome":

                System.setProperty("webdriver.chrome.driver",
                        Configuration.getConfiguration("chrome.driver"));
                return new ChromeDriver();
            default:
                throw new IllegalArgumentException("This browser is undefined!");
        }
    }

}
