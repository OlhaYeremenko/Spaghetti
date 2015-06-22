package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Waiter {
    public static WebElement waitForElementPresent(WebDriver driver, String elementXpath) {
        WebDriverWait waitForOne = new WebDriverWait(driver, 30);

        return waitForOne.until(ExpectedConditions.presenceOfElementLocated(By
                .xpath(elementXpath)));
    }

    public static void delay(Long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
