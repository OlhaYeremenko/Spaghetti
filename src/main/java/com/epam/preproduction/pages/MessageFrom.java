package com.epam.preproduction.pages;


import com.epam.preproduction.helpers.Constants;
import com.epam.preproduction.helpers.FileLoader;
import com.epam.preproduction.helpers.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.epam.preproduction.template.AbstractPage;

import java.awt.*;

public class MessageFrom extends AbstractPage {

    private static final String FORM_TO_XPATH = "//textarea[@name='to']";
    private static final String FORM_SUBJ_XPATH = "//input[@name='subjectbox']";
    private static final String FORM_TEXT_XPATH = "//div[@role='textbox']";
    private static final String FORM_SENDBTN_XPATH = "//tbody//div[count(div) = 2]/div[@role='button' and @data-tooltip]";
    private static final String FORM_SAVE_AND_QUITBTN_XPATH = ".//img[3]";
    private final static String ATTACH_FILES_BTN = "//div[@command = 'Files']/div/div/div";
    private static final String PROGRESS_BAR_LINK = "//div[@role='progressbar']";
    private Robot robot;

    @FindBy(xpath = ATTACH_FILES_BTN)
    private WebElement attachFileBth;

    @FindBy(xpath = FORM_TO_XPATH)
    private WebElement toField;

    @FindBy(xpath = FORM_SUBJ_XPATH)
    private WebElement subjectField;

    @FindBy(xpath = FORM_TEXT_XPATH)
    private WebElement textField;

    @FindBy(xpath = FORM_SENDBTN_XPATH)
    private WebElement sendBTN;

    @FindBy(xpath = FORM_SAVE_AND_QUITBTN_XPATH)
    private WebElement saveAndQuitBTN;

    public MessageFrom(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private Robot getRobot() {
        try {
            return new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return null;
    }

    private MessageFrom inputSenderReceiver(String email) {
        toField.sendKeys(email);
        return this;
    }

    private MessageFrom inputSubject(String subject) {
        subjectField.sendKeys(subject);
        return this;
    }

    private MessageFrom inputContent(String text) {
        textField.sendKeys(text);
        return this;
    }

    public MailPage clickSendButton() {
        sendBTN.click();
        return new MailPage(driver);
    }

    private MessageFrom attachFileBthClick() {
        attachFileBth.click();
        return this;
    }

    public MessageFrom sendMailToUser(String userName, String messageSubject, String messageContent) {
        Waiter.waitForElementPresent(getDriver(), FORM_TO_XPATH);
        inputSenderReceiver(userName).inputSubject(messageSubject)
                .inputContent(messageContent);
        return this;
    }

    public MessageFrom attachFile(String filePath) {
        attachFileBthClick();
        getRobot().delay(Integer.parseInt(Constants.MIDDLE_DELAY.toString()));
        FileLoader.uploadFile(filePath);
        Waiter.waitForElementPresent(driver, PROGRESS_BAR_LINK);
        return this;
    }
}
