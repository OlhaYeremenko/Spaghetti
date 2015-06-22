package com.epam.preproduction.tests;

import com.epam.preproduction.helpers.Configuration;
import org.testng.annotations.*;
import com.epam.preproduction.pages.LoginPage;
import com.epam.preproduction.pages.MailPage;
import com.epam.preproduction.pages.ThemesPage;

/**
 * Created by Olha_Yeremenko on 21-Jun-15.
 */
public class ThemesTest extends TestSetting {

    LoginPage loginPage;
    MailPage mailPage;
    ThemesPage themesPage;

    @BeforeTest
    public void beforeTest() {
        loginPage = new LoginPage(driver);
        mailPage = new MailPage(driver);
        themesPage = new ThemesPage(driver);
    }

    @Test
    public void checkThatThemeChanged() {
        loginPage.navigateTo(Configuration.getConfiguration("test.environment"));
        loginPage.loginAction(Configuration.getConfiguration("user1.login"), Configuration.getConfiguration("user1.password"));
        mailPage.openThemeSettings().chooseRandomTheme();
        mailPage.logoutAction();
    }

    @AfterTest
    public void afterTest() {
        mailPage = null;
       loginPage = null;
    }
}


