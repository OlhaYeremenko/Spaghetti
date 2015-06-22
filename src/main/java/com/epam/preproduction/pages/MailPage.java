package com.epam.preproduction.pages;


import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.epam.preproduction.helpers.Waiter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.epam.preproduction.template.AbstractPage;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Olha_Yeremenko on 21-Jun-15.
 */
public class MailPage extends AbstractPage {

    private static final String COMPOSE_BTN_XPATH = "(//div[@id and @class]/div/div[@role='button' and @gh])[2]";
    private static final String SENT_MAIL_TAB_XPATH = ".//a[contains(@href,'#sent')]";
    private static final String SPAM_TAB_XPATH = "//a[contains(@href, 'spam')]";
    private static final String TO_SPAM_BTN_XPATH = "//div[@act and @role='button'][2]/div/div";
    private static final String LOGOUT_BTN_XPATH = "//a[contains(@href, 'logout')]";
    private static final String USER_LOGO_XPATH = "//a[contains(@href, 'SignOutOptions')]";
    private static final String CONFIRMATION_XPATH = "//div[@aria-live='assertive' and @role='alert' and @aria-atomic='true']/div/div[2]";
    private final static String SETTINGS = "//div[contains(@role,'button') and (@title='Настройки')]";
    private final static String SETTINGS_THEME = "//div[@id and @role='menu']//div[9]/div ";
    private final static String SECOND_USER = "//div[2]/div[2]/a[1]/img";
    private final static String ADD_NEW_USER = "//a[contains(@href,'https://accounts.google.com/AddSession')]";
    private static final String NEW_LETTER_XPATH = " //div[@role='tabpanel']//table//tbody/tr[1]/td[2]/div/div";
    private static final String MORE_TABS_BUTTON_XPATH = "//span[@id and @class and @role='button']/span/div";
    private static final String ASSERT_LETTER_SUBJECT = "//table[@role='presentation']//h2[@tabindex='-1']";
    private static final String STARRED_TAB_XPATH = "//a[contains(@href, 'starred')]";
    private static final String INBOX_TAB_XPATH = "//a[contains(@href, 'inbox') and @aria-label]";
    private static final String NEW_LETTER_SUBJECT = "//table//tbody//tr[1]/td[6]//span[1]";
    private static final String NEW_LETTER_CONTENT = "//table//tbody//tr[1]/td[6]//span[2]";


    @FindBy(xpath = INBOX_TAB_XPATH)
    private WebElement inboxBtn;


    @FindBy(xpath = ASSERT_LETTER_SUBJECT)
    private WebElement letterSubject;

    @FindBy(xpath = NEW_LETTER_CONTENT)
    private WebElement letterContent;

    @FindBy(xpath = STARRED_TAB_XPATH)
    private WebElement starredTab;

    @FindBy(xpath = MORE_TABS_BUTTON_XPATH)
    private WebElement moreTabsBtn;

    @FindBy(xpath = SPAM_TAB_XPATH)
    private WebElement spamTab;

    @FindBy(xpath = CONFIRMATION_XPATH)
    private WebElement confirmationAlert;

    @FindBy(xpath = TO_SPAM_BTN_XPATH)
    private WebElement toSpamBtn;

    @FindBy(xpath = NEW_LETTER_XPATH)
    private WebElement newLetter;

    @FindBy(xpath = COMPOSE_BTN_XPATH)
    private WebElement composeBTN;

    @FindBy(xpath = SENT_MAIL_TAB_XPATH)
    private WebElement sentMenuItem;

    @FindBy(xpath = USER_LOGO_XPATH)
    private WebElement userLogo;

    @FindBy(xpath = SECOND_USER)
    private WebElement secondUserLogo;

    @FindBy(xpath = LOGOUT_BTN_XPATH)
    private WebElement logOutBTN;

    @FindBy(xpath = ADD_NEW_USER)
    private WebElement addUserBTN;

    @FindBy(xpath = SETTINGS)
    private WebElement settingBTN;

    @FindBy(xpath = SETTINGS_THEME)
    private WebElement settingThemeBTN;

    private WebElement getLetterSubject() {
        try {
            return letterSubject;
        } catch (ElementNotVisibleException | ElementNotFoundException e) {
            return getDriver().findElement(By.xpath(NEW_LETTER_SUBJECT + "/b"));
        }
    }

    private WebElement getLetterContent() {

        return letterContent;
    }

    public MailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private MailPage spamTabClick() {
        Waiter.waitForElementPresent(getDriver(), SPAM_TAB_XPATH);
        spamTab.click();
        return this;
    }

    public MailPage goToStarredClick() {
        starredTab.click();
        return new MailPage(driver);
    }

    private MailPage moreTabsBtnClick() {
        moreTabsBtn.click();
        return this;
    }

    private MailPage toSpamBtnClick() {
        toSpamBtn.click();
        return this;
    }

    private MailPage newLetterClick() {
        newLetter.click();
        return this;
    }

    public MessageFrom composeMailBtnClick() {
        composeBTN.click();
        return new MessageFrom(driver);
    }

    private LoginPage logOutBtnClick() {
        logOutBTN.click();
        return new LoginPage(driver);
    }

    private MailPage userLogoClick() {
        userLogo.click();
        return this;
    }

    private LoginPage addUserBTNClick() {
        Waiter.waitForElementPresent(getDriver(), ADD_NEW_USER);
        addUserBTN.click();
        return new LoginPage(driver);
    }

    private MailPage secondUserLogoClick() {
        secondUserLogo.click();
        return this;
    }

    private MailPage settingBTNClick() {
        settingBTN.click();
        return this;
    }

    private MailPage settingThemeBTNClick() {
        Waiter.waitForElementPresent(getDriver(), SETTINGS_THEME);
        settingThemeBTN.click();
        return this;
    }

    public ThemesPage openThemeSettings() {
        settingBTNClick();
        settingThemeBTNClick();
        return new ThemesPage(driver);
    }

    public MailPage logoutAction() {

        userLogoClick();
        Waiter.waitForElementPresent(driver, LOGOUT_BTN_XPATH);
        try {
            logOutBtnClick();
        } catch (UnhandledAlertException e) {
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            } catch (AWTException e1) {
                e1.printStackTrace();
            }
        }
        return this;


    }

    public MailPage addOrSwithUser(String login, String password) {
        Waiter.waitForElementPresent(getDriver(), USER_LOGO_XPATH);

        userLogoClick();
        try {
            Waiter.waitForElementPresent(getDriver(), SECOND_USER);
            secondUserLogoClick();
            ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(newTab.get(2));
        } catch (NoSuchElementException | ElementNotVisibleException e) {
            addUser(login, password);
        }
        return this;
    }

    private MailPage addUser(String login, String password) {
        String old = driver.getWindowHandle();
        addUserBTNClick();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        new LoginPage(driver).loginAction(login, password);
        return this;
    }

    public MailPage moveToSpam() {
        newLetterClick();
        toSpamBtnClick();
        Assert.assertNotNull(confirmationAlert);
        return this;
    }

    public MailPage goToSpamFolder() {
        moreTabsBtnClick();
        Waiter.delay(3000L);

        spamTabClick();
        Waiter.delay(1000L);
        return this;
    }

    public MailPage dragAndDropMessage() {

        Waiter.waitForElementPresent(getDriver(), NEW_LETTER_XPATH);
        Actions actions = new Actions(driver);

        actions.dragAndDrop(newLetter, starredTab).release().perform();
        Waiter.delay(6000L);
        Assert.assertNotNull(confirmationAlert);
        return this;
    }


    public MailPage assertThatLetterMoved(String subject, String content) {
        Waiter.waitForElementPresent(getDriver(), NEW_LETTER_SUBJECT);
        letterSubject.click();
        Waiter.waitForElementPresent(getDriver(), ASSERT_LETTER_SUBJECT);
        assertThat(letterSubject.getText(), containsString(subject));
        return this;
    }


}


