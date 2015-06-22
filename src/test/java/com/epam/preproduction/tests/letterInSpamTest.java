package com.epam.preproduction.tests;

import com.epam.preproduction.helpers.Configuration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.epam.preproduction.pages.LoginPage;
import com.epam.preproduction.pages.MailPage;

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
        mailPage.composeMailBtnClick().sendMailToUser(Configuration.getConfiguration("user2.login"), SUBJECT, CONTENT).clickSendButton();
        mailPage.addOrSwithUser(Configuration.getConfiguration("user2.login"), Configuration.getConfiguration("user2.password"));

        mailPage.moveToSpam();

        mailPage.addOrSwithUser(Configuration.getConfiguration("user1.login"), Configuration.getConfiguration("user1.password"));
        mailPage.composeMailBtnClick().sendMailToUser(Configuration.getConfiguration("user2.login"), SUBJECT, CONTENT).clickSendButton();
        mailPage.addOrSwithUser(Configuration.getConfiguration("user2.login"), Configuration.getConfiguration("user2.password"));

        mailPage.goToFolderSpam().assertThatLetterMoved(SUBJECT, CONTENT);

}



    @AfterClass
    public void afterClass(){
        mailPage.logoutAction();
        mailPage=null;
        loginPage = null;
    }
}


