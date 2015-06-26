package com.epam.preproduction.tests;

import com.epam.preproduction.helpers.Configuration;
import org.testng.annotations.*;
import com.epam.preproduction.pages.LoginPage;
import com.epam.preproduction.pages.MailPage;

/**
 * Created by Olha_Yeremenko on 21-Jun-15.
 */
public class DragAndDropTest extends TestSetting {

    private LoginPage loginPage;
    private MailPage mailPage;

    @BeforeTest
    public void beforeTest() {
        loginPage = new LoginPage(driver);
        mailPage = new MailPage(driver);
    }

    @Test
    public void checkThatLetterInStarred() {
        loginPage.navigateTo(Configuration.getConfiguration("test.environment"));
        logger.info("open environment");
        loginPage.loginAction(USER1_LOGIN, USER1_PASSWORD);
        logger.info("login  as user1");
        mailPage.composeMailBtnClick().sendMailToUser(USER1_LOGIN, SUBJECT, CONTENT);
        logger.info("send mail from user1 to user2 with such subject"+SUBJECT);
        mailPage.addOrSwithUser(USER2_LOGIN, USER2_PASSWORD);
        logger.info("login  as user2");
        mailPage.dragAndDropMessage();
        mailPage.goToStarredClick().assertThatLetterMoved(SUBJECT, CONTENT);
        logger.info("drag and drop message successfully");
    }


    @AfterTest
    public void afterTest() {
        mailPage.logoutAction();
    }
}

