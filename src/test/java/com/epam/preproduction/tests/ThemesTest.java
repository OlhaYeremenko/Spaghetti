package com.epam.preproduction.tests;

import com.epam.preproduction.helpers.Configuration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
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

    @BeforeClass
    public void beforeClass() {
        loginPage = new LoginPage(driver);
        mailPage= new MailPage(driver);
        themesPage= new ThemesPage(driver);
    }

    @Test
    public void checkThatThemeChanged() {
        loginPage.navigateTo(Configuration.getConfiguration("test.environment"));
        loginPage.loginAction(Configuration.getConfiguration("user1.login"), Configuration.getConfiguration("user1.password"));
        mailPage.openThemeSettings().chooseRandomTheme();
        mailPage.logoutAction();
    }

    @AfterClass
    public void afterClass() {
        mailPage.logoutAction();
        mailPage=null;
        loginPage = null;
    }
}


