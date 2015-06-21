package pages;

import helpers.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import template.AbstractPage;

import java.util.List;
import java.util.Random;

/**
 * Created by Olha_Yeremenko on 21-Jun-15.
 */

public class ThemesPage extends AbstractPage {

    public static final String THEMES_LIST_XPATH = "//tbody/tr[2]/td/div[@id]/div/div/div/div";
    public static final String THEME_CONFIRMATION_XPATH = "//div[@aria-live='assertive' and @role='alert' and @aria-atomic='true']/div/div[2]";


    @FindBy(xpath = THEMES_LIST_XPATH)
    private List<WebElement> themesList;

    @FindBy(xpath = THEME_CONFIRMATION_XPATH)
    private WebElement themesConfirmation;

    public ThemesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MailPage chooseRandomTheme() {
        Waiter.waitForElementPresent(driver, THEMES_LIST_XPATH);

        Random random = new Random();
        int index = random.nextInt(themesList.size());
        System.out.println(index);
        themesList.get(index).click();
        Waiter.waitForElementPresent(driver, THEME_CONFIRMATION_XPATH);

        return new MailPage(driver);
    }

}
