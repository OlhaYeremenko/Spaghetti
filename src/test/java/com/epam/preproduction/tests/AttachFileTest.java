package com.epam.preproduction.tests;

import com.epam.preproduction.helpers.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.epam.preproduction.pages.LoginPage;
import com.epam.preproduction.pages.MailPage;

/**
 * @author olia
 * @since 22.06.2015
 */
public class AttachFileTest  extends TestSetting{

    private static final String filePath=System.getProperty("user.dir") +Configuration.getConfiguration("file.path");
    LoginPage loginPage;
    MailPage mailPage;

    @BeforeClass
    public void beforeClass() {
        loginPage = new LoginPage(driver);
        mailPage = new MailPage(driver);
    }

    @Test
    public void sentFileWithAttach() {
        loginPage.navigateTo(Configuration.getConfiguration("test.environment"));

        loginPage.loginAction(Configuration.getConfiguration("user1.login"), Configuration.getConfiguration("user1.password"));
        mailPage.composeMailBtnClick().sendMailToUser(Configuration.getConfiguration("user2.login"), SUBJECT, CONTENT).attachFile(filePath).clickSendButton();

    }
}
