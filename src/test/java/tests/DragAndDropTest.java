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
public class DragAndDropTest extends TestSetting {

    LoginPage loginPage;
    MailPage mailPage;

    @BeforeClass
    public void beforeClass() {
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
        mailPage.goToStarredClick().assertThatLetterMovedAndBackToInbox(SUBJECT, CONTENT);

    }


    @AfterClass
    public void afterClass() {
        mailPage.logoutAction();
        mailPage=null;
        loginPage = null;
    }
}

