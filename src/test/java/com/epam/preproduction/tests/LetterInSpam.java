package com.epam.preproduction.tests;

import com.epam.preproduction.helpers.Configuration;
import com.epam.preproduction.pages.LoginPage;
import com.epam.preproduction.pages.MailPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Olha_Yeremenko on 26-Jun-15.
 */
public class LetterInSpam extends TestSetting{

        private LoginPage loginPage;
        private MailPage mailPage;

        @BeforeClass
        public void beforeClass() {
            loginPage = new LoginPage(driver);
            mailPage = new MailPage(driver);
        }

        @Test
        public void checkThatLetterInSpamFolder() {
            loginPage.navigateTo(Configuration.getConfiguration("test.environment"));
            logger.info("open environment");
            loginPage.loginAction(USER1_LOGIN, USER1_PASSWORD);
            logger.info("login us user1");
            mailPage.composeMailBtnClick().sendMailToUser(USER2_LOGIN, SUBJECT, CONTENT);
            logger.info("send mail from user1 to user2 with such subject"+SUBJECT);
            mailPage.addOrSwithUser(USER2_LOGIN, USER2_PASSWORD);
            logger.info("login us user2");
            mailPage.moveToSpam();
            mailPage.addOrSwithUser(USER1_LOGIN, USER1_PASSWORD);
            logger.info("login us user1");
            mailPage.composeMailBtnClick().sendMailToUser(USER2_LOGIN, SUBJECT, CONTENT);
            logger.info("send mail from user1 to user2 with such subject"+SUBJECT);
            mailPage.addOrSwithUser(USER2_LOGIN, USER2_PASSWORD);
            logger.info("swith user");
            mailPage.goToSpamFolder().assertThatLetterMoved(SUBJECT, CONTENT);
            logger.info("letter in spam");
        }

        @AfterTest
        public void afterClass() {
            mailPage.logoutAction();
        }
    }




