package pages;


import helpers.Configuration;
import helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import template.AbstractPage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class LoginPage extends AbstractPage {

    private static final String USERNAME_INPUT_XPATH = "//input[@id='Email']";
    private static final String NEXT_BTN_XPATH = "//input[@id='next']";
    private static final String PASSWORD_INPUT_XPATH = "//input[@id='Passwd']";
    private static final String SUBMIT_XPATH = "//input[@id='signIn']";
    private static final String  TITLE = "Google";
    private static final String COMPOSE_BTN_XPATH = "//div[@role='button' and @gh and contains(@class, 'T')]";

	@FindBy(xpath = USERNAME_INPUT_XPATH)
	private WebElement usernameField;

	@FindBy(xpath = PASSWORD_INPUT_XPATH)
	private WebElement passwordField;

	@FindBy(xpath = SUBMIT_XPATH)
	private WebElement submitBTN;

    @FindBy(xpath = NEXT_BTN_XPATH)
	private WebElement nextBtn;

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private LoginPage nextBtnClick() {
		nextBtn.click();
		return this;
	}

    private LoginPage inputUserName(String username) {
       Waiter.waitForElementPresent(getDriver(),USERNAME_INPUT_XPATH);

        if(usernameField.getText()!=null)
        usernameField.clear();

		usernameField.sendKeys(username);
		return this;
	}

    private LoginPage inputPassword(String password) {
        passwordField.clear();
		passwordField.sendKeys(password);
		return this;
	}

    private LoginPage submitLogin() {
        submitBTN.click();
        return this;
    }

    public AbstractPage loginAction(String login, String password) {
        inputUserName(login);
        try {
           nextBtnClick();
        } catch (NoSuchElementException e) {
        } finally {
            (new WebDriverWait(getDriver(), 20))
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath(PASSWORD_INPUT_XPATH)));
           inputPassword(password).submitLogin();
        }
        Waiter.waitForElementPresent(getDriver(), COMPOSE_BTN_XPATH);
        assertThat(getDriver().getTitle(), containsString(login));

        return new MailPage(driver);
    }



}
