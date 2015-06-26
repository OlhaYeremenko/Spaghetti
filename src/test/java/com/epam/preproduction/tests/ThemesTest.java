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

    private LoginPage loginPage;
    private MailPage mailPage;
    private ThemesPage themesPage;

    @BeforeClass
    public void beforeClass() {
        loginPage = new LoginPage(driver);
        mailPage = new MailPage(driver);
        themesPage = new ThemesPage(driver);
    }

    @Test
    public void checkThatThemeChanged() {
        loginPage.navigateTo(Configuration.getConfiguration("test.environment"));
        logger.info("open ");
        loginPage.loginAction(USER1_LOGIN, USER1_PASSWORD);
        logger.info("login action preformed successfully");
        mailPage.openThemeSettings().chooseRandomTheme();
        logger.info(" random theme change successfully");
        logger.info("logout");
    }

    @AfterTest
    public void afterTest() {
        mailPage.logoutAction();
    }

}


