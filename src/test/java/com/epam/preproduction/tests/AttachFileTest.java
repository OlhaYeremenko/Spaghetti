package com.epam.preproduction.tests;

import com.epam.preproduction.helpers.Configuration;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.epam.preproduction.pages.LoginPage;
import com.epam.preproduction.pages.MailPage;

/**
 * @author olia
 * @since 22.06.2015
 */
public class AttachFileTest extends TestSetting {

    private static final String filePath = System.getProperty("user.dir") + Configuration.getConfiguration("file.path");
    private LoginPage loginPage;
    private MailPage mailPage;

    @BeforeTest
    public void beforeTest() {
        loginPage = new LoginPage(driver);
        mailPage = new MailPage(driver);
    }

    @Test
    public void sentFileWithAttach() {
        loginPage.navigateTo(Configuration.getConfiguration("test.environment"));
        logger.info("open environment");
        loginPage.loginAction(USER1_LOGIN, USER2_PASSWORD);
        logger.info("login  as user1");
        mailPage.composeMailBtnClick().sendMailToUser(USER2_LOGIN, SUBJECT, CONTENT).attachFile(filePath).clickSendButton();
        logger.info("attach file and send");
    }
    @AfterTest
    public void afterTest() {
        mailPage.logoutAction();
    }
}

