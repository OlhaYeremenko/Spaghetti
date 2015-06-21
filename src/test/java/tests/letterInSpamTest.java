package tests;

import helpers.Configuration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MailPage;

/**
 * Created by Olha_Yeremenko on 21-Jun-15.
 */
public class letterInSpamTest extends TestSetting {

    LoginPage loginPage;
    MailPage mailPage;

    @BeforeClass
    public void beforeClass() {
        loginPage = new LoginPage(driver);
        mailPage = new MailPage(driver);
    }

    @Test
    public void checkThatLetterInSpamFolder() {

        loginPage.navigateTo(Configuration.getConfiguration("test.environment"));

        loginPage.loginAction(Configuration.getConfiguration("user1.login"), Configuration.getConfiguration("user1.password"));
        mailPage.composeMailBtnClick().sendMailToUser(Configuration.getConfiguration("user2.login"), SUBJECT, CONTENT);
        mailPage.addOrSwithUser(Configuration.getConfiguration("user2.login"), Configuration.getConfiguration("user2.password"));

        mailPage.moveToSpam();

        mailPage.addOrSwithUser(Configuration.getConfiguration("user1.login"), Configuration.getConfiguration("user1.password"));

        mailPage.composeMailBtnClick().sendMailToUser(Configuration.getConfiguration("user2.login"), SUBJECT, CONTENT);

        mailPage.addOrSwithUser(Configuration.getConfiguration("user2.login"), Configuration.getConfiguration("user2.password"));

        mailPage.goToFolderSpam().assertThatLetterInSpam(SUBJECT, CONTENT);

        mailPage.logoutAction();
    }


    /* repeated part of test:
1. Login as registered user1
2. Send message to user2
3. Login as registered user2
 */
//    public void LoginSendLetterSwitchUser() {
//
//        loginPage.loginAction(Configuration.getConfiguration("user1.login"), Configuration.getConfiguration("user1.password"));
//
//        mailPage.composeMailBtnClick().sendMailToUser(Configuration.getConfiguration("user2.login"), SUBJECT, CONTENT);
//
//        mailPage.addOrSwithUser(Configuration.getConfiguration("user2.login"), Configuration.getConfiguration("user2.password"));
//
//    }

    @AfterClass
    public void afterClass() {
        loginPage = null;
    }
}


