package tests;

import helpers.Configuration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MailPage;
import pages.MessageFrom;
import pages.ThemesPage;

/**
 * Created by Olha_Yeremenko on 21-Jun-15.
 */
public class ThemesTest extends TestSetting {

    LoginPage loginPage;
    MailPage mailPage;
    ThemesPage themesPage;

    @BeforeClass
    public void beforeClass() {
        loginPage = new LoginPage(driver);
        mailPage= new MailPage(driver);
        themesPage= new ThemesPage(driver);
    }

    @Test
    public void checkThatThemeChanged() {
        loginPage.loginAction(Configuration.getConfiguration("user1.login"), Configuration.getConfiguration("user1.password"));
        mailPage.openThemeSettings().chooseRandomTheme();
        mailPage.logoutAction();
    }

    @AfterClass
    public void afterClass() {
        loginPage = null;
    }
}


