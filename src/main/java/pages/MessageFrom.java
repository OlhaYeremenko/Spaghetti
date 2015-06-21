package pages;


import helpers.Configuration;
import helpers.Waiter;
import net.sf.cglib.asm.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import template.AbstractPage;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;

public class MessageFrom extends AbstractPage {

    private static final String FORM_TO_XPATH = "//textarea[@name='to']";
    private static final String FORM_SUBJ_XPATH = "//input[@name='subjectbox']";
    private static final String FORM_TEXT_XPATH = "//div[@role='textbox']";
    private static final String FORM_SENDBTN_XPATH = "//tbody//div[count(div) = 2]/div[@role='button' and @data-tooltip]";
    private static final String FORM_SAVE_AND_QUITBTN_XPATH = ".//img[3]";
	private final static String ATTACH_FILES_BTN = "//div[@command = 'Files']/div/div/div";

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

	private MessageFrom attachFileBthClick(){
		attachFileBth.click();
		return this;
	}

    public MessageFrom sendMailToUser(String userName, String messageSubject,String messageContent){

		Waiter.waitForElementPresent(getDriver(), FORM_TO_XPATH);

		inputSenderReceiver(userName).inputSubject(messageSubject)
				.inputContent(messageContent);

		return this;
	}

	public MessageFrom attachFile(String filePath){
		attachFileBthClick();



		uploadFile(filePath);
		return this;
	}

	private void uploadFile(String filePath) {

		try {
			setClipboardData(filePath);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	private void setClipboardData(String path) {
		StringSelection stringSelection = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

}
