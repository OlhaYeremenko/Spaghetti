package com.epam.preproduction.tests;

import com.epam.preproduction.helpers.Configuration;
import org.testng.annotations.*;
import com.epam.preproduction.pages.LoginPage;
import com.epam.preproduction.pages.MailPage;

/**
 * Created by Olha_Yeremenko on 21-Jun-15.
 */
public class DragAndDropTest extends TestSetting {

    LoginPage loginPage;
    MailPage mailPage;

    @BeforeTest
    public void beforeTest() {
        loginPage = new LoginPage(driver);
        mailPage = new MailPage(driver);
    }

    @Test
    public void checkThatLetterInStarred() {
        loginPage.navigateTo(Configuration.getConfiguration("test.environment"));
        loginPage.loginAction(Configuration.getConfiguration("user1.login"), Configuration.getConfiguration("user1.password"));
        mailPage.composeMailBtnClick().sendMailToUser(Configuration.getConfiguration("user2.login"), SUBJECT, CONTENT).clickSendButton();
        mailPage.addOrSwithUser(Configuration.getConfiguration("user2.login"), Configuration.getConfiguration("user2.password"));
        mailPage.dragAndDropMessage();
        mailPage.goToStarredClick().assertThatLetterMoved(SUBJECT, CONTENT);

    }


    @AfterTest
    public void afterTest() {
        mailPage.logoutAction();
        mailPage = null;
        loginPage = null;
    }
}

